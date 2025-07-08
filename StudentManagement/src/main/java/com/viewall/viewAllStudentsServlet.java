package com.viewall;

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


@WebServlet("/viewallstudent")
public class viewAllStudentsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded...!");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","ro28");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM students"); 
			ResultSet rs = ps.executeQuery();
			out.print("            STUDENT DATA=====          ");
			if(rs.next()) {
				out.println("NAME : "+rs.getString("name")+" || ");
				out.println("EMAIL : "+rs.getString("email")+" || ");
				out.println("PHONE_NO : "+rs.getString("phone_no")+" || ");
				out.println("COURSE : "+rs.getString("course")+" || ");
			}
			else {
				out.print("THERE IS NO DATA");
			}
		}
		catch(Exception e) {
			
			
		}
	
	}

}
