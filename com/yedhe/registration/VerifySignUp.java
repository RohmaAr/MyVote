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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.KerbCallback;
import com.microsoft.sqlserver.jdbc.SQLServerStatement;

import com.yedhe.registration.VerifySignUp;

/**
 * Servlet implementation class VerifySignUp
 */
@WebServlet("/VerifySignUp")
public class VerifySignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifySignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dburl="jdbc:sqlserver://DESKTOP-VVFDP90\\SQLEXPRESS:1433;databaseName=myvote;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
			Connection connection=DriverManager.getConnection(dburl);
			SQLServerStatement stat= (SQLServerStatement) connection.createStatement();
			String query="select name,cnic from citizens where access is null";
			ResultSet rs=((java.sql.Statement) stat).executeQuery(query); 
		    response.setContentType("text/html");
		    PrintWriter out=response.getWriter();
		    out.println("<link rel=\"stylesheet\" href=\"css/verifysignup.css\">");
		    out.println("<form action=\"VerifySignUp\" method=\"post\">");
		    out.println("<table>");

		    // Iterate through the ResultSet and generate a new row for each record
		    while (rs.next()) {
		        String name = rs.getString("name");
		        String cnic = rs.getString("CNIC");

		        // Generate a new row in the table for each record
		        out.println("<tr>");
		        out.println("<td>" + name + "</td><td>" + cnic + "</td>");

		        // Create two radio buttons with unique names that correspond to the data for this row
		        out.println("<td><input type=\"radio\" name=\"" + cnic + "\" value=\"accept\">Accept</td>");
		        out.println("<td><input type=\"radio\" name=\"" + cnic + "\" value=\"deny\">Deny</td>");
		        out.println("</tr>");
		    }

		    out.println("</table>");

		    // Add a hidden input field to store the value of the selected radio button
		    out.println("<input type=\"hidden\" name=\"selectedRadio\" value=\"\">");

		    // Add a submit button to submit the form
		    out.println("<input type=\"submit\" value=\"Submit\">");
		    out.println("</form>");

		    // Add an event listener to each radio button that stores its value in the hidden input field when it is clicked
		    out.println("<script>");
		    out.println("var radioButtons = document.getElementsByTagName('input');");
		    out.println("for (var i = 0; i < radioButtons.length; i++) {");
		    out.println("if (radioButtons[i].type === 'radio') {");
		    out.println("radioButtons[i].addEventListener('click', function() {");
		    out.println("document.getElementsByName('selectedRadio')[0].value = this.value;");
		    out.println("});");
		    out.println("}");
		    out.println("}");
		    out.println("</script>");
			connection.close();
		} catch (ClassNotFoundException e1) {
			Logger.getLogger(VerifySignUp.class.getName()).log(null);
			e1.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("Driver Manger or some shit");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String dburl="jdbc:sqlserver://DESKTOP-VVFDP90\\SQLEXPRESS:1433;databaseName=myvote;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
				Connection connection=DriverManager.getConnection(dburl);
				SQLServerStatement stat= (SQLServerStatement) connection.createStatement();
				String query="select cnic from citizens where access is null";
				ResultSet rs=((java.sql.Statement) stat).executeQuery(query);
				ArrayList<String> cnic= new ArrayList<String>();
			    while (rs.next()) {
			    	cnic.add(rs.getString("cnic"));
			   }
			    int rowsAffected=0;
			    PreparedStatement stat2=connection.prepareStatement("UPDATE citizens SET access=? WHERE cnic=?");
			    for (int i = 0; i <cnic.size(); i++) {
			        stat2.setString(1, request.getParameter(cnic.get(i)));
			        stat2.setString(2, cnic.get(i));
			        // Execute the update query
			        rowsAffected += stat2.executeUpdate();
			        
			    }

		        PrintWriter outPrintWriter=response.getWriter();
		        outPrintWriter.print(rowsAffected);
			    connection.close();
		 	} catch (ClassNotFoundException e1) {
				Logger.getLogger(VerifySignUp.class.getName()).log(null);
				e1.printStackTrace();
			} catch (SQLException e) {
				
				System.out.println("Driver Manger or some shit");// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
