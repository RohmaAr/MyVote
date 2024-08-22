<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
   <link rel="stylesheet" href="css/login.css">
<title>Sign Up</title>
</head>
<body>
 <div class="log-bg">
       
        <div class="right-log">

            <div class="form">

                <form action="SignUp" method="post">

                    <div class="container">
                        <h2>Sign Up</h2>
                        <label for="uname"><b>Name</b></Label>
                        <input type="text" placeholder="Enter name" name="uname" pattern="[A-Za-z]+" required>
                        <label for="ucnic"><b>Cnic</b></label>
                        <input type="text" placeholder="Enter Cnic Number" name="ucnic" title="for e.g. 12345-1234567-1"pattern="\d{5}-\d{7}-\d" required>

                        <label for="psw"><b>Password</b></label>
                        <input type="text" placeholder="Enter Password" name="psw" maxlength="8" required>

                        <button type="submit">Sign Up</button>
                    </div>
              

                </form>
                
            </div>
        </div>
       <div class="left-log">
            <img src="./assets/download.png" alt="">
              <a href="SignIn.jsp">I already have an account</a>
        </div>
    </div>
</body>
</html>