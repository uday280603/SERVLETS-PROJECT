package com.addstudent;

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

@WebServlet("/addstudent")
public class AddStudentservlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone_No = request.getParameter("phone_no");
		String course = request.getParameter("course");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Lodaed sucessfully..!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","ro28");
			PreparedStatement ps = con.prepareStatement("INSERT INTO students (name, email, phone_no, course)VALUES(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone_No);
			ps.setString(4, course);
			int rowaffect=ps.executeUpdate();
			if(rowaffect>0) {
				out.print("STUDENT SUCESSFULLY ADDED INTO SCHOOL DATABASE...");
			}
			else {
				out.print("STUDENT NOT ADDED...TRY ADAIN..!");
			}
			
		}
		catch(Exception e) {
			out.print(e.getMessage());
			
		}
		
		
	}

}
