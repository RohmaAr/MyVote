<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
 <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-g4DlUxR6U5FziyB8skrW9bwv7CGcR/08Yz7iN0wCUDN23vc1b/k0yq3ryOJW9iVnnZP44zZuz2O8uGwA04Vx+w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   <style>
   @import url('https://fonts.googleapis.com/css2?family=Lora:wght@400;600&display=swap');
   
   body{
   font-family: 'Lora', sans serif }
    /* Styles for the navigation bar */
    nav {
       background-color:#00420D; 
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
  
  
  .item-select > a{
  display: none;
  }
  
   .item-select:hover a{
  display: block;
  }
  
  .item-opt{
  width:6rem;
  margin-left:2rem;
  display:block;
  background-color: white;
  padding: 0.5rem;
  }
  .dropbtn {
  font-family: 'Lora', sans serif;
    background-color:white;
    font-size: 20px;
    width: 100%;
    height:100%;
    text-align: center;
    border:none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
.dropdown-content a:hover {background-color: #ddd;}
/*.dropdown-content .sub-menu {
  position: absolute;
  top: 0;
  left: 100%;
  display: none;
}

.dropdown-content a:hover + .sub-menu {
  display: block;
}*/
.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: 808080;}
  </style>
<body>
  <nav>
    <a href="#">Home</a>
    <a href="#">About</a>
    <a href="#">Contact</a>
    <div class="right">
     <a href="#">Election Commission</a>
  </div>
  </nav>
  <div><img src="./assets/ElectionCommission.png"></div> 
  <div class="menu">
    
     <div class="menu-item">
    <div class="dropdown">
  <button class="dropbtn">Schedule Election</button>
  <div class="dropdown-content">
     <div class="sub-menu">
      <select onchange="location = this.value;">
        <option value="">Provincial</option>
        <option value="ScheduleElection?option=provincialp">Punjab</option>
        <option value="ScheduleElection?option=provincials">Sindh</option>
        <option value="ScheduleElection?option=provincialb">Balochistan</option>
         <option value="ScheduleElection?option=provincialk">Khyber Pakhtunkwa</option>
      </select>
    </div>
    <a href="ScheduleElection?option=national">National </a>
    <a href="ScheduleElection?option=union">Union</a>
  </div>
</div>
</div>
    <div class="menu-item"> <a href="AppointPO">Appoint Presiding Officer </a></div>
    <div class="menu-item"> <a href="https://www.google.com">Item 3 </a></div>
    <div class="menu-item"><a href="https://www.google.com">Item 4 </a></div>
    <div class="menu-item"><a href="https://www.google.com">Item 5 </a></div>
     <div class="menu-item"> <a href="https://www.google.com">Item 6 </a></div>
   
  </div>
</body>
</html>