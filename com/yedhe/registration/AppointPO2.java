package com.yedhe.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.StackWalker.Option;
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
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppointPO2
 */
@WebServlet("/AppointPO2")
public class AppointPO2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointPO2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.println("doget of appount po2");
		  String selectedOption = request.getParameter("option");
		  String selectedOption2 = request.getParameter("option2");
		  System.out.println(selectedOption+" "+selectedOption2);
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
			    ArrayList<Integer> seats = new ArrayList<>();
			    ArrayList<String> Po = new ArrayList<>();
			    try {
			    	if(selectedOption2.equals("n") || selectedOption2.equals("p")) {
			    	stat=connection.prepareStatement("select sid from onseats where eno=? and po is null");
			    	stat.setString(1, selectedOption);
			    ResultSet rs=null;
			    rs=stat.executeQuery();
					while(rs.next())
					{
						seats.add(rs.getInt(1));
					}
					  for(int i=0;i<seats.size();i++)
					  {
						CallableStatement cStatement = connection.prepareCall("{call find_po(@sid = ?, @eno = ?, @cit_id = ?)}");
						cStatement.setInt(1, seats.get(i));
						cStatement.setInt(2, Integer.parseInt(selectedOption));
						cStatement.registerOutParameter(3, Types.VARCHAR);
						cStatement.execute();
						String output =null;
						output=cStatement.getString(3);
						if(output==null) {
						   Po.add("");
						}
						else {
							Po.add(output);
						}
				    	    System.out.println( "after finding po"+seats.get(i)+" "+output);		    	
					   }
			       }
	               else {
	            	   CallableStatement cStatement=connection.prepareCall("{call find_po(@sid = 0,@eno =?,@cit_id=?)}");
				    	 cStatement.setString(1, selectedOption);
				    	    cStatement.registerOutParameter(2, Types.INTEGER);
				    	    cStatement.execute();
				    	    String output = cStatement.getString(3);
				    	    if(output==null) {
								   Po.add("");
								}
								else {
									Po.add(output);
								}
						    	    System.out.println( "after finding po"+output);		    	
						}
			    	stat=connection.prepareStatement("select region from regional where eno=?");
			    			stat.setInt(1, Integer.parseInt(selectedOption));
			    	ResultSet rs=null;
			        rs=stat.executeQuery();
					while(rs.next())
					{
						seats.add(rs.getInt(1));
					}
							  
						      request.setAttribute("seats",seats);
							  request.setAttribute("PO", Po);
							  request.setAttribute("type", selectedOption2);
							   RequestDispatcher dispatcher = request.getRequestDispatcher("AppointPO.jsp");
						      dispatcher.forward(request, response);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
