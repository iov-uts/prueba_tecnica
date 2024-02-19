package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDao;


@WebServlet("/home")
public class PokeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	UserDao userDao;
       
    
    public PokeController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String go = request.getParameter("go");	
		if (go != null && go.equals("login")) {
			response.sendRedirect("login.jsp");
		} else if (go != null && go.equals("signup")) {
			response.sendRedirect("signup.jsp");
		} else if (go != null && go.equals("pokemon")) {
			String urlPokemon = request.getParameter("id");	
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("pokemon.jsp");
				request.setAttribute("urlPokemon", urlPokemon);
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("index.jsp");
			}
		} else if (go != null && go.equals("logout")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("index.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newUser = request.getParameter("new");
		
		if (newUser != null && newUser.equals("y")) {
			try {
				userDao = new UserDao();
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				ArrayList<User> users = userDao.getUsers();
				if (users != null) {
					for (User u: users) {
						if (u.getEmail().equals(email)) {
							response.sendRedirect("signup.jsp?redirect=y");
						}
					}
				}
				
				
				User user = new User(name, email, password);
				if (userDao.addUser(user)) {
					PrintWriter out = response.getWriter();
					out.print("<html>"
							+ "<h1 style='text-align:center;padding:20px;'>Succesful Record</h1>"
							+ "</html>");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (newUser != null && newUser.equals("n")) {
			try {
				userDao = new UserDao();
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				User user = userDao.getUser(email, password);
				
				if (user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					response.sendRedirect("newSession.jsp");
				} else {
					response.sendRedirect("login.jsp?redirect=y");
				}
				
				
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
