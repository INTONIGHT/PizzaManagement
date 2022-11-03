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
		case"/updateToppingsOnPizza":
			ArrayList<PizzaToppings> pt = this.gson.fromJson(request.getReader(), ArrayList.class);
			String name = this.gson.fromJson(request.getReader(), String.class);
			boolean b = pdao.createPizza(name, pt);
			response.getWriter().append(pdao.getPizza(name) + " Has been created with a response of " + b);
			break;
		}
		
			
	}
}
