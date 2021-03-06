    
<%@page import="logic.Weather"%>
<%@page import="servlets.IndexServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            <%@include file='css/page.css'%>
        </style>
            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <script type="text/javascript">
              google.charts.load('current', {'packages':['corechart']});
              google.charts.setOnLoadCallback(drawChart);
              function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Time', 'Globalstrahlung',{ role: 'style' }],
                    <%for(Weather weather: IndexServlet.services.getQueueList()){%>                                
                        ['<%=weather.getGrafDate()%>',  <%=weather.getRadiation()%>, '#6e9022'],
                    <%}%>
                ]);

                var options = {
                    title:'G in W/m2',
                    fill: '#6e9022',
                    vAxis: {minValue: 0},
                    curveType: 'function',
                    chartArea:{left:60,right:0}
                };

                var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
                chart.draw(data, options);
              }
        </script>
    </head>
    <body>	
        <div style='width: 700px;height: 340px; float: left'>
            <div id="chart_div" style="width: 700px; height: 340px;margin: 0%;"></div>        
            <form action="http://localhost:8080/wetter/radiation" align='center' style="margin:1%">
                <input type="submit" value="zurück" id="getGraf"> 
             </form>        
        </div>
        <div id='table'>
            <table id = "tableUtl"'>
                <tr><td style='font-size: 11pt; color: black; background-color: #e0e2e3; font-family: Lucida Grande,Lucida Sans Unicode,Lucida Sans,Lucida,sans-serif;'>Auswahl:</td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/index'>Gesamtdaten:</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/speedwind'>Windgeschwindigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/directionwind'>Windrichtung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/temperature'>Temperatur</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/humidity'>Luftfeuchtigkeit</a></td></tr>
                <tr><td><font>></font><a>Globalstrahlung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/pressure'>Luftdruck</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/precipitation'>Niederschlag</a></td></tr>
            </table>
            <form align='center' action='<%=request.getContextPath()%>/radiation' method='post' style='border-spacing: 5px'>
                <input type='date' name='start' style='border-radius: 8px;margin-bottom:3%;border: 1px solid gray;font-weight: bold'/><br>
                <input type='date' name='end' style=' border-radius: 8px;border: 1px solid gray; font-weight: bold'/><br>
                <input type="submit" name='name' value="Download" style=' border-radius: 4px; background-color: #e0e2e3; margin-top: 3%;'><br>
            </form>
        </div>
    </body>
</html>
