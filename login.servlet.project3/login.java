package com.login_usingsignup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		     response.setContentType("text/html");
		     PrintWriter out = response.getWriter();
		    
		     String username = request.getParameter("username");
		     String password = request.getParameter("password");
		     
		     try {
		    	 Class.forName("com.mysql.cj.jdbc.Driver");
		    	 System.out.println("driver Loaded..!");
		    	 Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","ro28");
		    	 
		    	 PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_info WHERE username=? AND password=?"); 
		    	 ps.setString(1,username);
		    	 ps.setString(2, password);
		    	 ResultSet rs = ps.executeQuery();
		    	 if(rs.next()) {
		    		 out.print("<h2><i>LOGIN SUCCESSFULLY..!<i></h2>");
		    	 }
		    	 else {
		    		 out.print("<h2>INVALID USERNAME OR PASSWORD..!<h2>");
		    	 }
		    	  
		    	 conn.close();
		     }
		     catch(Exception e) {
		    	 out.print("Error :"+e.getMessage());
		     }
	}
}
