package com.signup.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/signup")
public class signup extends HttpServlet {
	
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException ,IOException  {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h2> This Servlet only handels post request..</h2>");
		
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mobileno = request.getParameter("phone");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded !");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","ro28");
		PreparedStatement ps = con.prepareStatement("INSERT INTO user_info(firstname, lastname, phoneNo, email, username, password) VALUES(?,?,?,?,?,?)"); 
		ps.setString(1,firstname);
		ps.setString(2,lastname);
		ps.setString(3,mobileno);
		ps.setString(4, email);
		ps.setString(5, username);
		ps.setString(6, password);
		 int row = ps.executeUpdate();
		 if(row>0) {
			 out.println("<h2>Successfully Registred...!</h2>");
		 }
		 else {
			 out.println("Failed to Register..!");
		
		 }
		 con.close();
	}
	catch( Exception e) {
		out.println("Error: "+e.getMessage());
		
	}
	 
	}

}
