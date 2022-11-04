package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import daos.PizzaDAO;
import daos.PizzaToppingsDAO;
import daos.UserDAO;
import models.Pizza;
import models.PizzaToppings;
import models.User;

public class PizzaServlet extends HttpServlet{
	class Login {
		public String username;
		public String password;
	}
	UserDAO udao = new UserDAO();
	PizzaDAO pdao = new PizzaDAO();
	PizzaToppingsDAO ptdao = new PizzaToppingsDAO();
	private Gson gson = new Gson();
	public static HttpSession session;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String uri = request.getRequestURI();
		uri = uri.substring("/PizzaManagementBackend/PizzaServlet".length());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");
		session = request.getSession();
		switch(uri) {
		case"/getInfo":
			User u = (User)session.getAttribute("login");
			response.getWriter().append(gson.toJson(u));
			break;
			default:
				System.out.println("Default case");
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String uri = request.getRequestURI();
		uri = uri.substring("/PizzaManagementBackend/PizzaServlet".length());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");
		session = request.getSession();
		switch(uri) {
		case "/login":
			Login info = this.gson.fromJson(request.getReader(), Login.class);
			User u = udao.Login(info.username, info.password);
			session.setAttribute("login", u);
			response.getWriter().append(gson.toJson(u));
			break;
		case "/getPizzas":
			ArrayList<Pizza> pizzas = new ArrayList<>();
			pizzas = pdao.getAllPizzas();
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().append(gson.toJson(pizzas));
			break;
		case "/getPizza":
			String pizzaName = this.gson.fromJson(request.getReader(), String.class);
			Pizza pizza = pdao.getPizza(pizzaName);
			response.getWriter().append(gson.toJson(pizza));
			break;
		case "/createPizza":
			Pizza newPizza = new Pizza();
			newPizza.setPizzaName(this.gson.fromJson(request.getReader(), String.class));
			ArrayList<PizzaToppings> pizzaToppings = new ArrayList<>();
			pizzaToppings.addAll((Collection<? extends PizzaToppings>) this.gson.fromJson(request.getReader(),PizzaToppings.class));
			pdao.createPizza(newPizza.getPizzaName(), pizzaToppings);
			response.getWriter().append(newPizza + "Has been created with: " + pizzaToppings);
			break;
		case"/getPizzaId":
			String nameToFind = this.gson.fromJson(request.getReader(), String.class);
			int id = pdao.getId(nameToFind);
			response.getWriter().append(id + "Pizza id for: " + nameToFind);
			break;
		case"/updateToppingsOnPizza":
			ArrayList<PizzaToppings> pt = this.gson.fromJson(request.getReader(), ArrayList.class);
			int pizzaId = this.gson.fromJson(request.getReader(), Integer.class);
			boolean b = pdao.updateToppingsOnPizza(pizzaId, pt);
			response.getWriter().append( "PizzaToppings has been updated for pizza with id of: " +pizzaId + "with a response of: "+ b);
			break;
		case "/updatePizzaName":
			String namePizza = this.gson.fromJson(request.getReader(), String.class);
			int pid = this.gson.fromJson(request.getReader(), Integer.class);
			boolean bool = pdao.updatePizzaName(pid, namePizza);
			response.getWriter().append("Updated pizza name with name: " + namePizza + "With a repsnse of: " + bool);
			break;
		case "/updateToppingsAndName":
			String nameToChange = this.gson.fromJson(request.getReader(), String.class);
			ArrayList<PizzaToppings> toppingsToChange = this.gson.fromJson(request.getReader(), ArrayList.class);
			int idOfChange = this.gson.fromJson(request.getReader(), Integer.class);
			boolean check = pdao.updatePizzaNameAndToppings(idOfChange, nameToChange, toppingsToChange);
			response.getWriter().append("Updated toppings and pizzas with : " + nameToChange + " " + toppingsToChange + "with response " + check);
			break;
		case "/deletePizza":
			int idToDelete = this.gson.fromJson(request.getReader(), Integer.class);
			boolean deleted = pdao.deletePizza(idToDelete);
			response.getWriter().append("Deleted pizza with id of: " + idToDelete + "with response " + deleted);
			break;
		case "/createTopping":
			String toppingName = this.gson.fromJson(request.getReader(), String.class);
			boolean toppingCreated = ptdao.createTopping(toppingName);
			response.getWriter().append("Creating topping with name :" +toppingName + "with response of :" + toppingCreated);
			break;
		case "/getToppingId":
			String toppingToFind = this.gson.fromJson(request.getReader(), String.class);
			int toppingId = ptdao.getToppingId(toppingToFind);
			response.getWriter().append("The Id for that topping is: " + toppingId);
			break;
		case "/getAllToppings":
			ArrayList<PizzaToppings> pizzatoppings = new ArrayList<>();
			pizzatoppings = ptdao.getAllToppings();
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().append(gson.toJson(pizzatoppings));
			break;
		case"/updateTopping":
			String newToppingName = this.gson.fromJson(request.getReader(), String.class);
			int toppingIdToChange = this.gson.fromJson(request.getReader(), Integer.class);
			boolean updateToppingSuccess = ptdao.updateTopping(newToppingName, toppingIdToChange);
			response.getWriter().append(gson.toJson("Topping was changed to " + newToppingName + "with response of " + updateToppingSuccess));
			break;
		case "/deleteTopping":
			int toppingIdToDelete = this.gson.fromJson(request.getReader(), Integer.class);
			boolean deleteToppingSuccess = ptdao.deleteTopping(toppingIdToDelete);
			response.getWriter().append(gson.toJson("The following topping was deleted with id of " + toppingIdToDelete + "with response: " + deleteToppingSuccess));
			break;
		default :{
			System.out.println("Default case");
		}
		}
		
			
	}
}
