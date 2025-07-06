package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     //Get data from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		//Set responce type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		//Display Result
		
		out.println("<h2> Login Recived</h2>");
		out.println("<p>Username: "+ username +"</p>");
		out.println("<p>Password: "+ password +"</p>");
		
		
		
	}

}
