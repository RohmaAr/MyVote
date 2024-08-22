
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.Console"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <link rel="shortcut icon" href="http://MY-CDN.example.com/favicon.ico" />
    <title>Sign In</title>
</head>

<body>
 
<%
System.out.println("signin.jsp loaded");
String errorMessage = new String();
errorMessage =(String) request.getAttribute("errorMessage");
if (errorMessage != null) {
%>
<script>
    alert("<%= errorMessage %>");

</script>
<%
}
else
	System.out.print("its null");
%>


    <div class="log-bg">
        <div class="left-log">
            <img src="./assets/download.png" alt="">
            <a href="SignUp.jsp">Create account</a>
        </div>
        <div class="right-log">

            <div class="form">

                <form action="MatchesIDPassword" method="get">

                    <div class="container">
                        <h2>Sign in</h2>
                        <label for="uname"><b>Cnic</b></label>
                        <input type="text" placeholder="Enter Cnic Number" name="uname" title="for e.g. 12345-1234567-1"pattern="\d{5}-\d{7}-\d" required>

                        <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="psw" maxlength="8" required><br><br>
                     	<select name="person" id="person" required>
					    <option value="voter">Voter</option>
					    <option value="candidate" >candidate</option>
					    <option value="PO">PO</option>
					    <option value="other">other</option>
					    </select><br><br>
                        <button type="submit">Login</button>
                    </div>

                </form>
            </div>
        </div>

    </div>
</body>

</html>