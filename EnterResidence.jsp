<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
  <head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Residence info</title>
    <style>
      .container {
        width: 50%;
        margin: 0 auto;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        border-radius: 20px;
        padding: 20px;
        margin-top:10rem;
      }

      input[type="text"], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
        border-radius: 5rem;
      }
      button{
      background-color:#365895; 
      color: white; 
      border-radius: 5rem; 
      padding: 12px 20px; 
      border: none; 
      cursor: pointer;
      }
    </style>
  </head>
  <body>
  <%@ page import= "java.util.ArrayList" %>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <div class="container">
    <h1>Enter your residence info as mentioned on your cnic</h1>
      <form action="MatchesIDPassword" method="post" >
        <label for="region">Enter Region:</label>
        <input type="text" name="region" id="text-input">

        <label for="pconsti">Select your provincial Constituency:</label>
        <select name="pconsti" id="select-input">
        <% 
        ArrayList<String> myList=new ArrayList<>();
        myList=(ArrayList<String>)request.getAttribute("Pconsti");
        for (int i=0;i<myList.size();i++) { %>
          <option value="<%=myList.get(i) %>">
   <%=myList.get(i) %></option><br>
<% } %>
        </select>
      <button type="submit" >Submit</button>
      </form>
    </div>
</body>
</html>