<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-g4DlUxR6U5FziyB8skrW9bwv7CGcR/08Yz7iN0wCUDN23vc1b/k0yq3ryOJW9iVnnZP44zZuz2O8uGwA04Vx+w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   <style>
   @import url('https://fonts.googleapis.com/css2?family=Lora:wght@400;600&display=swap');
   
   body{
   font-family: 'Lora', sans serif }
    /* Styles for the navigation bar */
    nav {
       background-color:#365895; 
      overflow: hidden;
      padding: 4px 4px 4px 4px;
    }
    img{
    width: 100%;
    height: 35rem;
    }
    
    a{
    color: black;
    text-decoration: none;
    }
    nav a {
      float: left;
      color: #f2f2f2;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
      font-size: 17px;
    }
    nav a:hover {
      background-color: #ddd;
      color: black;
    }
    nav .right {
      float: right;
    }
    /* Styles for the grid menu */
    
    .menu{
   margin:0;
  display: grid;
  grid-template-columns: auto auto auto;
 
    
    }
    
    .menu-item{
  
    margin:0;
     margin-top:5px;
    font-size: 20px;
    width: 80%;
    height:50px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0px 4px 8px #555;
    padding:30px;
    text-align: center;
  }
  </style>
<body>
  <nav>
    <a href="#">Home</a>
    <a href="#">About</a>
    <a href="#">Contact</a>
    <div class="right">
     <a href="#">Admin</a>
  </div>
  </nav>
  <div><img src="./assets/ecpic.png"></div> 
  <div class="menu">
    
    <div class="menu-item"> <a href="VerifySignUp">Verify SignUp </a></div>
    <div class="menu-item"> <a href="https://www.google.com">Item 2 </a></div>
    <div class="menu-item"> <a href="https://www.google.com">Item 3 </a></div>
   
  </div>
</body>
</html>