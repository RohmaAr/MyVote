package com.yedhe.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;


/**
 * Servlet implementation class login
 */
@WebServlet("/MatchesIDPassword")
public class MatchesIDPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchesIDPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String n=request.getParameter("person");
		System.out.println(n);
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
				String cnic=request.getParameter("uname");
				String pas=request.getParameter("psw");
				PreparedStatement stat=null;
				if(n.equals("voter"))
				{
					 ResultSet rs=null;
					 try {
     					stat=connection.prepareStatement("select * from citizens where cnic=?");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						try {
							stat.setString(1, cnic);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 try {
							   rs=stat.executeQuery();
							   if(!rs.next())   //Entered Id not found in candidates table
					    	  {
								   request.setAttribute("errorMessage","You have not created an account, please sign up.");
							    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
							    	  dispatcher.forward(request, response);
					        	}
							    else {
								PreparedStatement stat2=null;
								stat2=connection.prepareStatement("select * from citizens where cnic=? and password=?");
								stat2.setString(1, cnic);
								stat2.setString(2, pas);
								ResultSet rss=null;
						    	rss=stat2.executeQuery();
						    	   if(!rss.next())
						    	   {
						    		   request.setAttribute("errorMessage","Wrong Password!Try Again.");
								    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
								    	  dispatcher.forward(request, response);
						    	   }
						    	   else {
						    		PreparedStatement stat3=null;
									stat3=connection.prepareStatement("select access from citizens where cnic=? and password=?");
									stat3.setString(1, cnic);
									stat3.setString(2, pas);
									ResultSet rs2=null;
							    	rs2=stat3.executeQuery();
							    	 String access = null;
							         while(rs2.next())
							         {
							        	 access=rs2.getString(1);
							         }
										//out.print(access);
							    	if(access==null)
							    	{
							    		 request.setAttribute("errorMessage","We're sorry. You can't sign up yet. Try again later");
								    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
								    	  dispatcher.forward(request, response);
							    	}
							    	else {
							    		
									    if(access.equals("deny"))
									    {
									    	  request.setAttribute("errorMessage","Your cnic is invalid. Please contact NADRA for help.");
									    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
									    	  dispatcher.forward(request, response);
									          
									    }
									    else {
									        stat2=null;
									    	stat2=connection.prepareStatement("select * from voter where cit_id=?");
									    	stat2.setString(1, cnic);
									    	rss=null;
									    	HttpSession session = request.getSession();
									    	session.setAttribute("cnic", cnic);
									    	session.setAttribute("password", pas);
									    	rss=stat2.executeQuery();
									    	if(!rss.next())
									    	{
										    	try {  
										    	  SQLServerStatement state= (SQLServerStatement) connection.createStatement();
													String query="select pconsti from seats";
													ResultSet res=((java.sql.Statement) state).executeQuery(query);
													ArrayList<String> nameArrayList = new ArrayList<String>();
													while(res.next())
												     {
														nameArrayList.add(res.getString(1));
										             }
													connection.close();
													res.close();
													state.close();
													rs.close();
													stat2.close();
													stat.close();
													stat3.close();
													rs2.close();
													request.setAttribute("Pconsti", nameArrayList);
													RequestDispatcher dispatcher = request.getRequestDispatcher("EnterResidence.jsp");
													dispatcher.forward(request, response);
											       
												} catch (SQLException e) {
													
													System.out.println("Driver Manger or some shit");// TODO Auto-generated catch block
													e.printStackTrace();
												}

									       }
									    	else {
									    		String name=null;
									    		stat2=connection.prepareStatement("select name,region,pconsti,nconsti,province from citizens join voter on citizens.cnic=voter.cit_id where citizens.cnic=?");
										    	stat2.setString(1, cnic);
										    	rss=null;
										    	rss=stat2.executeQuery();
										    	String reg=null,prov=null,pcon=null,nco=null;
										    	while(rss.next())
										    	{
										    		name=rss.getString(1);
										    		reg=rss.getString(2);
										    		pcon=rss.getString(3);
										    		nco=rss.getString(4);
										    		prov=rss.getString(5);
										    	}
									    		session.setAttribute("name", name);
									    		session.setAttribute("region", reg);
										    	session.setAttribute("province", prov);
										    	session.setAttribute("pconsti", pcon);
										    	session.setAttribute("nconsti", nco);
										    	System.out.println("AREAAA:"+" "+nco+" "+reg+" "+prov+" "+pcon+" " );
										    	rs.close();
									    		rss.close();
									    		stat2.close();
									    		connection.close();
									    		stat.close();
									    		RequestDispatcher dispatcher = request.getRequestDispatcher("VoterHome.jsp");
												dispatcher.forward(request, response);
											}
						    	}
							 }
				    	   }
						  }
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
  
				}
				 else if(n.equals("candidate"))
					{
					 ResultSet rs=null;
					 try {
      					stat=connection.prepareStatement("select * from candidates where cit_id=?");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						try {
							stat.setString(1, cnic);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 try {
							rs=stat.executeQuery();
							if(!rs.next())   //Entered Id not found in candidates table
					    	{
								request.setAttribute("errorMessage","You're not a candidate!");
						    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
						    	  dispatcher.forward(request, response);
					    	}
							else {
								PreparedStatement stat2=null;
								stat2=connection.prepareStatement("select * from candidates where cit_id=? and password=?");
								stat2.setString(1, cnic);
								stat2.setString(2, pas);
								ResultSet rss=null;
						    	rss=stat2.executeQuery();
						    	if(!rss.next())
						    	{
						    		request.setAttribute("errorMessage","Wrong Password!Try Again.");
							    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
							    	  dispatcher.forward(request, response);
						    	}
						    	else {
						    		rs.close();
						    		rss.close();
						    		stat2.close();
						    		connection.close();
						    		stat.close();
						    		HttpSession session = request.getSession();
							    	session.setAttribute("cnic", cnic);
							    	session.setAttribute("password", pas);
						    		//Candidate menu
						    	}
							}	
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else if(n.equals("PO"))
					{
						ResultSet rs=null;
						 try {
	      					stat=connection.prepareStatement("select * from po where cit_id=?");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							try {
								stat.setString(1, cnic);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 try {
								rs=stat.executeQuery();
								if(!rs.next())   //Entered Id not found in candidates table
						    	{
									request.setAttribute("errorMessage","You're not a PO!");
							    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
							    	  dispatcher.forward(request, response);
						    	}
								else {
									PreparedStatement stat2=null;
									stat2=connection.prepareStatement("select * from PO where cit_id=? and password=?");
									stat2.setString(1, cnic);
									stat2.setString(2, pas);
									ResultSet rss=null;
							    	rss=stat2.executeQuery();
							    	if(!rss.next())
							    	{
							    		request.setAttribute("errorMessage","Wrong Password!Try Again.");
								    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
								    	  dispatcher.forward(request, response);
							    	}
							    	else {
							    		rs.close();
							    		rss.close();
							    		stat2.close();
							    		connection.close();
							    		stat.close();
							    		HttpSession session = request.getSession();
								    	session.setAttribute("cnic", cnic);
								    	session.setAttribute("password", pas);
							    		//PO menu
							    	}
								}	
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					}	
					else if(n.equals("other")){ 
						ResultSet rs=null;
						 try {
							 System.out.println(cnic+" "+pas);
	      					stat=connection.prepareStatement("select * from noncitizens where id=?");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							try {
								stat.setString(1, cnic);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 try {
								rs=stat.executeQuery();
								if(!rs.next())   //Entered Id not found 
						    	{
									System.out.println("nulllll");
									request.setAttribute("errorMessage","You're not an admin or an election commission employee!");
							    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
							    	  dispatcher.forward(request, response);
						    	}
								else {
									PreparedStatement stat2=null;
									stat2=connection.prepareStatement("select nc from noncitizens where id=? and password=?");
									stat2.setString(1, cnic);
									stat2.setString(2, pas);
									ResultSet rss=null;
							    	rss=stat2.executeQuery();

						    		int nc=0;
							    
									System.out.println("should be executed");
							    	if(!rss.next())
							    	{
							    		request.setAttribute("errorMessage","Wrong Password!Try Again.");
								    	  RequestDispatcher dispatcher = request.getRequestDispatcher("SignIn.jsp");
								    	  dispatcher.forward(request, response);
							    	}
							    	else {
							    			nc=rss.getInt(1);
							    			System.out.println(nc);

										System.out.println(" not nulllll");
							    		HttpSession session = request.getSession();
								    	session.setAttribute("cnic", cnic);
								    	session.setAttribute("password", pas);
							    		System.out.println(nc);
							    		rs.close();
							    		rss.close();
							    		stat2.close();
							    		connection.close();
							    		stat.close();
							    		if(nc==1)
							    		{
							    		     session = request.getSession();
									    	session.setAttribute("cnic", cnic);
									    	session.setAttribute("password", pas);
									    	RequestDispatcher dispatcher = request.getRequestDispatcher("ElectionCommissionHome.jsp");
											dispatcher.forward(request, response);
										
							    		}
							    		else {
							    			session = request.getSession();
									    	session.setAttribute("cnic", cnic);
									    	session.setAttribute("password", pas);
									    	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
											dispatcher.forward(request, response);
//ECelection
										}
							    	}
								}	
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
				
		//doGet(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("MatchesIDPassword Post");
		
		
		String reg=request.getParameter("region");
		String pcon=request.getParameter("pconsti");
		String nco = null;
		String prov = null;
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
		 ResultSet rs=null;
		 PreparedStatement stat=null;
		try {
				stat=connection.prepareStatement("select prov,nconsti from seats where pconsti=?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			try {
				stat.setString(1, pcon);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
			
				rs=stat.executeQuery();
				while(rs.next())
				{
					prov=rs.getString(1);
					nco=rs.getString(2);
				}
				HttpSession session = request.getSession();
				String nString=(String) session.getAttribute("cnic");
				stat=connection.prepareStatement("select name from citizens where cnic=?");
				stat.setString(1, nString);
				rs=stat.executeQuery();
				String nameString=null;
				while(rs.next())
				{
					nameString=rs.getString(1);
				}
				session.setAttribute("name", nameString);
				System.out.println(nameString+" "+nString+" "+nco+" "+prov);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat=connection.prepareStatement("insert into voter(cit_id,region,pconsti,nconsti,province) values(?,?,?,?,?)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			String nString=(String) session.getAttribute("cnic");
			try {
				stat.setString(1,nString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.setString(2, reg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.setString(3, pcon);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.setString(4, nco);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.setString(5, prov);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				int rows=stat.executeUpdate();
				System.out.println("Number of rows updated are : "+rows);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	session.setAttribute("region", reg);
	    	session.setAttribute("province", prov);
	    	session.setAttribute("pconsti", pcon);
	    	session.setAttribute("nconsti", nco);
	    	System.out.println("AREAAA:"+" "+nco+" "+reg+" "+prov+" "+pcon+" " );
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("VoterHome.jsp");
				dispatcher.forward(request, response);
		//doGet(request, response);
	}

}
