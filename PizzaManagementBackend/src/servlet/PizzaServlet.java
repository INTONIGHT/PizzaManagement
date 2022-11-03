package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import daos.PizzaDAO;
import daos.PizzaToppingsDAO;
import daos.UserDAO;

public class PizzaServlet extends HttpServlet{
	class User{
		public String username;
		public String password;
	}
	class PizzaToppings{
		public int id;
		public String toppingName;
	}
	class Pizza{
		public int id;
		public String pizzaName, pizzaToppings;
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
	
}
