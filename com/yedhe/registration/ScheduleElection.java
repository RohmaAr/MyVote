package com.yedhe.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;

import Notifs.DisplayErrorMessage;

/**
 * Servlet implementation class ScheduleElection
 */
@WebServlet("/ScheduleElection")
public class ScheduleElection extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleElection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String selectedOption = request.getParameter("option");
		    System.out.println(selectedOption);
		    ArrayList<String> electionsOnArrayList = new ArrayList<>();
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
			String queryString=null;
			 PreparedStatement stat = null;
		    if (selectedOption != null) {
		        
		         if (selectedOption.equals("national")) {
		        	 request.setAttribute("election","National");
		        	 queryString="select distinct nconsti from seats";
		        } else if (selectedOption.equals("union")) {
		        	request.setAttribute("election","Union");
		        	 queryString="select distinct region from voter";
		        }
		        else {
		        	queryString="select pconsti from seats where prov=?";
			         
				}
		        ResultSet rs=null;
				
				try {
					stat=connection.prepareStatement(queryString);
					if (selectedOption.equals("provincialp"))
	        		{
						request.setAttribute("election","Punjab");
						stat.setString(1, "punjab");
	        		}
	        	else if (selectedOption.equals("provincials"))
	        	{
	        		request.setAttribute("election","Sindh");
	        		stat.setString(1, "sindh");
	        	}
	        	else if( selectedOption.equals("provincialb"))
	        		{

	        		request.setAttribute("election","Balochistan");
	        		stat.setString(1, "balochistan");
	        		}
	        	else if (selectedOption.equals("provincialk")) {

	        		request.setAttribute("election","Khyber Pakhtunkwa");
	        		stat.setString(1, "Khyber pakhtunkwa");
	        		}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					 try {
						   rs=stat.executeQuery();
						   while(rs.next())   //Entered Id not found in candidates table
				    	  {
							electionsOnArrayList.add(rs.getString(1)); 
				          }
//						  for(int i=0;i<electionsOnArrayList.size();i++)
//						  {
//							  System.out.println(electionsOnArrayList.get(i));
//						  }
						    					 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 request.setAttribute("electionOn",electionsOnArrayList);
			    	  RequestDispatcher dispatcher = request.getRequestDispatcher("electionform.jsp");
			    	  dispatcher.forward(request, response);
		    }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  int rows=0;
		 String selectedDate = request.getParameter("date");
		  String electionType = request.getParameter("ElectionType");
		  String[] selectedOptions = request.getParameterValues("option");
		  String elect=request.getParameter("election");
		  System.out.println();
		  System.out.println("In do post of schedule ");
		  System.out.println("Date" + selectedDate);
		  System.out.println("Elc ty"+ electionType);
		  System.out.println("Elections "+elect);
//		  for(int i=0;i<selectedOptions.length;i++)
//		  {
//			  System.out.println(selectedOptions[i]);
//		  }
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
		
		    if (elect.equals("National")) {
		    	try {
					stat=connection.prepareStatement("exec save_nationalelection ?,?");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					stat.setString(1,selectedDate);
					stat.setString(2, electionType);
					rows=stat.executeUpdate();
					for(int i=0;i<selectedOptions.length;i++)
					{
						stat=connection.prepareStatement("exec save_SeatsElection ?,?");
						stat.setString(1,selectedOptions[i]);
						stat.setString(2, elect);
						rows+=stat.executeUpdate();
					}
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    else if(elect.equals("Union"))
		    {
		    	try {
					stat=connection.prepareStatement("exec save_regionalElection ?,?");
					stat.setString(1,selectedDate);
					stat.setString(2, selectedOptions[0]);
					rows=stat.executeUpdate();
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    }
		    else {
		    	try {
					stat=connection.prepareStatement("exec save_provincialElection ?,?,?");
					stat.setString(1, selectedDate);
					stat.setString(2, electionType);
					stat.setString(3, elect);
					rows=stat.executeUpdate();
		    	    for(int i=0;i<selectedOptions.length;i++)
				    {
						stat=connection.prepareStatement("exec save_SeatsElection ?,?");
						stat.setString(1,selectedOptions[i]);
					    stat.setString(2, elect);					
					    rows+=stat.executeUpdate();
				    }
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    System.out.print(rows);
		    if(rows>0)
		    {
		    	response.setContentType("text/html");
		    	PrintWriter t=response.getWriter();
		    	DisplayErrorMessage d=new DisplayErrorMessage(t, "New election has been saved");
		    }
		   	  RequestDispatcher dispatcher = request.getRequestDispatcher("ElectionCommissionHome.jsp");
	    	  dispatcher.forward(request, response);
    
//					stat=connection.prepareStatement(queryString);
//					if (selectedOption.equals("provincialp"))
//	        		{
//						request.setAttribute("election","Punjab");
//						stat.setString(1, "punjab");
//	        		}
//	        	else if (selectedOption.equals("provincials"))
//	        	{
//	        		request.setAttribute("election","Sindh");
//	        		stat.setString(1, "sindh");
//	        	}
//	        	else if( selectedOption.equals("provincialb"))
//	        		{
//
//	        		request.setAttribute("election","Balochistan");
//	        		stat.setString(1, "balochistan");
//	        		}
//	        	else if (selectedOption.equals("provincialk")) {
//
//	        		request.setAttribute("election","Khyber Pakhtunkwa");
//	        		stat.setString(1, "Khyber pakhtunkwa");
//	        		}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
	}

}
