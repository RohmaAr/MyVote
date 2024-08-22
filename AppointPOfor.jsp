<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appoint PO for</title>
</head>
<body>

  <%@ page import= "java.util.ArrayList" %>
<h1>Pick elections for which you want to select Po:</h1>

<%

ArrayList<Integer> election = (ArrayList<Integer>)request.getAttribute("Elections");
ArrayList<String> electiond = (ArrayList<String>)request.getAttribute("ElectionsDate");
ArrayList<String> electiont = (ArrayList<String>)request.getAttribute("Electiontype");
%>
<% for (int i=0;i<election.size();i++) { 
	String e;
if(electiont.get(i).equals("n"))
{
	e="National Election";}
else if(electiont.get(i).equals("p"))
{
	e="Provincial Election";}
else
{
	e="Union Election";}%>
<a href="AppointPO2?option=<%=election.get(i)%>&option2=<%=electiont.get(i)%>" ><%=e%> <%=electiond.get(i) %></a>
<input type="hidden" id="custId" name="election" value="<%=electiont.get(i)%>">
    <br>
<% }%>
</body>
</html>