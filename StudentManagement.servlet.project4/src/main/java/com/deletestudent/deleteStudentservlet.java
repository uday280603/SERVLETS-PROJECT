package com.deletestudent;

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

@WebServlet("/deletestudent")
public class deleteStudentservlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("handlet by post method...");	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String idstr = request.getParameter("id");
		if(idstr==null  || idstr.trim().isEmpty()) {
			response.getWriter().println("ID is Madetiory");
			return;
		}
		int id = Integer.parseInt(idstr); 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","ro28");
			PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?");
			ps.setInt(1,id);
			int rowaffect = ps.executeUpdate();
			if(rowaffect>0) {
				out.print("<h2><i>STUDENT DATA DELETED........</i></h2>");
			}
			else {
				out.print("<h2>REQUEST FAILED..TRY AGAIN..!!</h2>");
			}
		}
		catch(Exception e) {
			out.print(e.getMessage());
			
		}
		
	
	}

}
