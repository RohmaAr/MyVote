<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appoint PO</title>

</head>
<body>

  <%@ page import= "java.util.ArrayList" %>
 <form action="AppointPO2" method="post">
<table>
  <thead>
    <tr>
      <th>Seat</th>
      <th>PO</th>
      <th>Select</th>
    </tr>
  </thead>
  <tbody>
    <%
      ArrayList<Integer> seat = new ArrayList<Integer>();
    seat=(ArrayList<Integer>)request.getAttribute("seats");// replace with your actual ArrayList
      ArrayList<String> po = new ArrayList<String>(); // replace with your actual ArrayList
      po=(ArrayList<String>)request.getAttribute("PO");
      for (int i = 0; i < seat.size(); i++) {
    	  Integer seatValue = seat.get(i);
        String poValue = po.get(i);
    %>
      <tr>
        <td><%= seatValue %></td>
        <td><%= poValue %></td>
        <td><input type="checkbox" name="selectedSeats" value="<%= seatValue %>-<%= poValue %>"></td>
      </tr>
    <%
      }
    %>
  </tbody>
</table>
<button type="submit">Set Presiding Officers</button>
</form>
</body>
</html>