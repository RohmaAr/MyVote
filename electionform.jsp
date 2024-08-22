<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Election Form</title>
</head>
<body>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.time.LocalDateTime" %>
<%String election=(String)request.getAttribute("election");
%>
<h1><%=election%> Election</h1>
<form action="ScheduleElection" method="post">
<input type="hidden" id="custId" name="election" value="<%=election%>">
  <label for="date">*Select a date:</label>
  <input type="date" id="date" name="date" min="<%= java.time.LocalDate.now().plusDays(1) %>" required><br><br>
<%
if(!election.equals("Union")){
%>
  <label>*Election Type:</label><br>
  <input type="radio" id="By" name="ElectionType" value="By" required>
  <label for="option1">By Election</label><br>
  <input type="radio" id="General" name="ElectionType" value="General">
  <label for="option2">General Election</label><br><br>
  <%} %>
  <label for="option">*Pick seats/regions :</label><br><br>
<%
ArrayList<String> electionOn = new ArrayList<String>();
electionOn= (ArrayList<String>)request.getAttribute("electionOn");
 if(!election.equals("Union")){%>
<% for (int i=0;i<electionOn.size();i++) { %>
    <input type="checkbox" name="option" value="<%= electionOn.get(i)%>">
    <%=electionOn.get(i)%><br>
<% }}
 else{
	 for(int i=0;i<electionOn.size();i++) {%>
		 <input type="radio" id="General" name="option" value="<%=electionOn.get(i) %>" required><label for="region"><%=electionOn.get(i)%></label><br>
	 <% }
 }%>
<br><br>

  <input type="submit" value="Submit">
</form>
</body>
</html>