package com.yedhe.registration;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppointPO
 */
@WebServlet("/AppointPO")
public class AppointPO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointPO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		    try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	    		}
				String dburl="jdbc:sqlserver://DESKTOP-VVFDP90\\SQLEXPRESS:1433;databaseName=myvote;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
	    		Connection connection = null;
				try {
					connection= DriverManager.getConnection(dburl);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					if(connection==null) {
			    	  request.setAttribute("errorMessage","We are facing some issues, please try again later.");
			    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
			    	  dispatcher.forward(request, response);
				}
					e1.printStackTrace();
				}
			    PreparedStatement stat = null;
			    ArrayList<Integer> electionNoArrayList = new ArrayList<>();
			    ArrayList<String> electiont = new ArrayList<>();
			    ArrayList<String> electionDate = new ArrayList<>();
			    try {
					stat=connection.prepareStatement("select eno,type,date from election where date>getdate()");
			    ResultSet rs=null;
			    rs=stat.executeQuery();
					while(rs.next())
					{
						electionNoArrayList.add(rs.getInt(1));
						electiont.add(rs.getString(2));
						electionDate.add(rs.getString(3));
					}
					for(int i=0;i<electionNoArrayList.size();i++)
					{
						System.out.println(electionNoArrayList.get(i)+" "+electionDate.get(i)+" "+electiont.get(i));
					}
					System.out.println();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    try {
			    	for(int i=0;i<electionNoArrayList.size();i++) {
			    	CallableStatement cStatement=connection.prepareCall("{call null_po(@eno = ?,@null_yes =?)}");
			    	 cStatement.setInt(1, electionNoArrayList.get(i));
			    	    cStatement.registerOutParameter(2, Types.INTEGER);
			    	    cStatement.execute();
			    	    int output = cStatement.getInt(2);
			    	    System.out.println( electionNoArrayList.get(i)+" "+output);
			    	   if(output==0)
			    	   {
			    		electionDate.remove(i);
			    		electionNoArrayList.remove(i);
			    		electiont.remove(i);
			    	    }
			    	}
					for(int i=0;i<electionNoArrayList.size();i++)
					{
						System.out.println(electionNoArrayList.get(i)+" "+electionDate.get(i)+" "+electiont.get(i));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    request.setAttribute("Elections", electionNoArrayList);
			    request.setAttribute("ElectionsDate", electionDate);
			    request.setAttribute("Electiontype", electiont);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("AppointPOfor.jsp");
		    	  dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
