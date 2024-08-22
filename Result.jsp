<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <style>
  		.chart{
			left:120px;
  			width:80%;
  			position:relative;
  			
  		}
  		.title{
    text-align: center;
    margin: 0 auto 80px;
    position: relative;
    line-height: 60px;
    color: #555;
}

.title::after{
    content: '';
    background: #324a34;
    width: 30%;
    height: 5px;
    border-radius: 5px;
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
}
  </style>
  </head>
  <body>
  <div>
  	<h1 class="title">
  		Results of Elections
  	</h1>
  </div>
  
  <div class="chart">
  
  
  		  <canvas id="myChart"></canvas>
    <script>
      
      const data = [70, 30, 60, 40, 50,80,20,100,90]; // it's the data in y-axis

      
      const ctx = document.getElementById('myChart').getContext('2d');
      const myChart = new Chart(ctx, {
        type: 'bar', 
        data: {
          labels: ['candidate 1', 'candidate 2', 'candidate 3', 'candidate 4', 'candidate 5','candidate 6','candidate 7','candidate 8','candidate 9'], // Set the labels for the x-axis
          datasets: [{
            label: 'Votes',
            data: data, 
            backgroundColor: 'rgba(255, 99, 132, 0.2)', 
            borderColor: 'rgba(255, 99, 132, 1)', 
            borderWidth: 1 
          }]
        },
        options: {}
      });
    </script>
  </div>
  
  </body>
</html>
