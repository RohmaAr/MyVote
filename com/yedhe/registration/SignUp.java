package com.yedhe.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String n=request.getParameter("uname");
		String c=request.getParameter("ucnic");
		String p=request.getParameter("psw");
		RequestDispatcher dis= null;
		
		Connection connection=null;
		//doGet(request, response);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dburl="jdbc:sqlserver://DESKTOP-VVFDP90\\SQLEXPRESS:1433;databaseName=myvote;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
			connection=DriverManager.getConnection(dburl);
			PreparedStatement stat=connection.prepareStatement("Insert into citizens(name,password,cnic) values(?,?,?)");
			
			stat.setString(1,n);
			stat.setString(2, p);
			stat.setString(3, c);
		int rowCount=stat.executeUpdate();
		
		dis=request.getRequestDispatcher("SignUp.jsp");      

		if(rowCount>0)
		{
			request.setAttribute("Status", "success");
		}
		else {
			request.setAttribute("Status","failed");
		}
		dis.forward(request,response); 
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("Driver Manger or some shit");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		try {
			connection.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doGet(request, response);
		}
	}

}
