package com.update;

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

@WebServlet("/updatestudent")
public class updateStudentServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("this is handled by post method ");
//		
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//special code for ID
		String idstr = request.getParameter("id");
		if(idstr==null  || idstr.trim().isEmpty()) {
			response.getWriter().println("ID is Madetiory");
			return;
		}
		int id = Integer.parseInt(idstr); 
		
		String newName = request.getParameter("name");
		String newEmail = request.getParameter("email");
		String newPhone_No = request.getParameter("phone_no");
		String newCourse = request.getParameter("course");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver Loaded..!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","ro28");
			PreparedStatement ps = con.prepareStatement("UPDATE students SET name = ?, email = ?, phone_no=?, course=? WHERE id = ?");
			
			ps.setString(1,newName);
			ps.setString(2,newEmail);
			ps.setString(3,newPhone_No);
			ps.setString(4,newCourse);
			ps.setInt(5, id);
			int rowaffected = ps.executeUpdate();
			if(rowaffected>0) {
				out.print(" STUDENT DATA IS UPDATED SUCESSFULLY....");
			}
			else {
				out.print("UPDATATION FAILED...TRY AGAIN...!");
			}
		}
		catch(Exception e) {
			out.print(e.getMessage());	
		}	
	}
}
