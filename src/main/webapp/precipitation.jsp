
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
          google.charts.load("current", {packages:['corechart']});
          google.charts.setOnLoadCallback(drawChart);
          function drawChart() {
            var data = google.visualization.arrayToDataTable([
                     ['Time', 'Niederschlag',{ role: 'style' }],
                    <%int c = 0;for(Weather weather: IndexServlet.services.getPrecipitationList()){%>                                
                        ['<%=weather.getDate() %>',  <%= weather.getPrecipitation() %>, '#6e9022'],
                    <%c++; if(c==30)break;}%>
                ]);

            var view = new google.visualization.DataView(data);
            view.set
            var options = {
                bar: {groupWidth: "95%"},
                legend: { position: "none" },              
                title:'Niederschlag in mm',
                fill: '#6e9022',
                vAxis: {minValue: 0},
                curveType: 'function',
                chartArea:{left:60,right:0}
            };
            var chart = new google.visualization.ColumnChart(document.getElementById("chart_div"));
            chart.draw(view, options);
        }
        </script>
    </head>
    <body>	
        <div id='test'>
            <div id='show'>
                <div id='titleShow'>
                    <h3 id='titleH3'>Aktuelle Daten vom:</h3>
                    <p align='center' style=' background-color: #e0e2e3;'><%=IndexServlet.services.getWeatherToday().getDate().substring(0,19)%></p>
                </div>
                <div id='weatherShow'>
                <table id='index'>                    
                    <tr><td><font>Niederschlag:</font> <b><%=IndexServlet.services.getWeatherToday().getPrecipitation()%> mm</b></td></tr>
                    <tr><td style="font-size: 10pt"><font>Durchschnitt pro Tag:</font> <b><%=IndexServlet.services.getAvageWeatherPerDay().getPrecipitation() %> mm</td></tr>
                    <tr><td style="font-size: 10pt"><font>Maximum:</font> <%=IndexServlet.services.getWd().maxPrecipitation()%></td></tr>
                    <tr><td style="font-size: 10pt"><font>Minimum:</font> <%=IndexServlet.services.getWd().minPrecipitation()%></td></tr>
                </table>
                <div id="chart_div" style="width: 250px; height: 200px;margin: 0%;"></div>
                </div>
                <form action="http://localhost:8080/wetter/precipitationgraf.jsp" align='center' style="margin:15%">
                    <input type="submit" value="Vergrößern" id="getGraf"> 
                </form>
            </div>
            <div id='text'>
                <h3>
                    Niederschlagsgeber
                </h3>
                <p>
                    <img src="<%=request.getContextPath()%>/images/niederschlag.jpg">
                    Der Niederschlagsgeber Typ 7041 dient zur Messung von Regenmenge
                    und Regenintensität und arbeitet nach dem Prinzip der Kippwaage.
                    In der Ausführung mit eingebauter Heizung können im  begrenzten
                    Umfang auch feste Niederschläge (Schnee, Hagel) erfasst werden.
                    Die Auffangfläche des Geräts beträgt ca. 200 cm² und ist damit
                    den Richtlinien des Deutschen Wetterdienstes angelehnt. Der
                    entsprechende Wippeninhalt beträgt 4 cm³, was einer Auflösung
                    von 0,2 mm pro m2 Niederschlag entspricht.

                </p>
            </div>
        </div>
        <div id='table'>
            <table id = "tableUtl"'>
                <tr><td style='font-size: 11pt; color: black; background-color: #e0e2e3; font-family: Lucida Grande,Lucida Sans Unicode,Lucida Sans,Lucida,sans-serif;'>Auswahl:</td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/index'>Gesamtdaten:</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/speedwind'>Windgeschwindigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/directionwind'>Windrichtung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/temperature'>Temperatur</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/humidity'>Luftfeuchtigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/radiation'>Globalstrahlung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/pressure'>Luftdruck</a></td></tr>
                <tr><td><font>></font><a>Niederschlag</a></td></tr>
            </table>
            <form align='center' action='<%=request.getContextPath()%>/precipitation' method='post' style='border-spacing: 5px'>
                <input type='date' name='start' style='border-radius: 8px;margin-bottom:3%;border: 1px solid gray;font-weight: bold'/><br>
                <input type='date' name='end' style=' border-radius: 8px;border: 1px solid gray; font-weight: bold'/><br>
                <input type="submit" name='name' value="Download" style=' border-radius: 4px; background-color: #e0e2e3; margin-top: 3%;'><br>
            </form>
        </div>
    </body>
</html>