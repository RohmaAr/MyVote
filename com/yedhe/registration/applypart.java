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
import java.util.function.IntToDoubleFunction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.jasper.Options;

/**
 * Servlet implementation class applypart
 */
@WebServlet("/applypart")
public class applypart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applypart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option=request.getParameter("option");
		System.out.println(option);
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
				    ArrayList<String> electionDate = new ArrayList<>();
				    try {
						stat=connection.prepareStatement("select eno,date from election where date>getdate() and type=?");
							stat.setString(1,option);
				    ResultSet rs=null;
				    rs=stat.executeQuery();
						while(rs.next())
						{
							electionNoArrayList.add(rs.getInt(1));
							electionDate.add(rs.getString(2));
						}
						System.out.println("Printing all about to happen "+option+" elections");
						for(int i=0;i<electionNoArrayList.size();i++)
						{
							System.out.println(electionNoArrayList.get(i)+" "+electionDate.get(i));
						}
						System.out.println();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    ArrayList<Integer> myelections=electionNoArrayList;
				    HttpSession session = request.getSession();
					String cnic=(String) session.getAttribute("cnic");
				    try {
				    	if(option.equals("n") || option.equals("p")) {

				    		 ArrayList<Integer> seatsArrayList=new ArrayList<>() ;
						   for(int i=0; i<electionNoArrayList.size();i++) {

				    	    	seatsArrayList.clear();
							   stat=connection.prepareStatement("select sid from onseats where eno=?");
				    		 ResultSet rs=null;
				    		 stat.setInt(1, electionNoArrayList.get(i));
							    rs=stat.executeQuery();
									while(rs.next())
									{
										seatsArrayList.add(rs.getInt(1));
										//System.out.println("sidis"+rs.getInt(1)+" "+electionNoArrayList.get(i));
									}
									System.out.println("printing seats");
									for(int k=0;k<seatsArrayList.size();k++)
									{
										System.out.println(seatsArrayList.get(k)+" "+electionNoArrayList.get(i));
									}
									for(int j=0;j<seatsArrayList.size();j++) {
						    		CallableStatement cStatement=connection.prepareCall("{call has_election( @eno=?, @sid=?, @cnic=?, @yes=?)}");
						    		cStatement.setInt(1, electionNoArrayList.get(i));
							    	 cStatement.setInt(2, seatsArrayList.get(j));
							    	 cStatement.setString(3, cnic);
							    	    cStatement.registerOutParameter(4, Types.INTEGER);
							    	    cStatement.execute();
							    	    int output = cStatement.getInt(4);
							    	    if(output==0 )
							    	    {
							    	    	if(j==seatsArrayList.size()-1) {
							    	    		myelections.remove(i);
							    	    		electionDate.remove(i);
							    	    	}
							    	    }
							    	    else {
											break;
										}
									}
										
											
								   }

						   }

				    	else {
				    		for(int i=0;i<electionNoArrayList.size();i++) {
				    		CallableStatement cStatement=connection.prepareCall("{call has_election( @eno=?, @sid=0, @cnic=?, @yes=?)}");
				    		cStatement.setInt(1, electionNoArrayList.get(i));
					    	 cStatement.setString(2, cnic);
					    	    cStatement.registerOutParameter(3, Types.INTEGER);
					    	    cStatement.execute();
					    	    int output = cStatement.getInt(4);
					    	    if(output==0 )
					    	    {
					    	    	myelections.remove(i);
					    	    	electionDate.remove(i);
					    	    }
				    		}
				    	}
				    	for(int i=0;i<electionDate.size();i++)
				    	{
				    		System.out.println(electionDate.get(i)+ " "+electionNoArrayList.get(i));
				    	}
				    	for(int i=0;i<myelections.size();i++)
				    	{
				    		System.out.println("my elections "+myelections.get(i));
				    	}
				    	 request.setAttribute("Elections", myelections);
						    request.setAttribute("ElectionsDate", electionDate);
						    request.setAttribute("etype", option);
						   RequestDispatcher dispatcher = request.getRequestDispatcher("ApplyPart.jsp");
					    	  dispatcher.forward(request, response);

				    	}
				    	catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

//						   
				       					 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
