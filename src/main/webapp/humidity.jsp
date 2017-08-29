
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
                    ['Time', 'Luftfeuchtigkeit',{ role: 'style' }],
                    <%for(Weather weather: IndexServlet.services.getQueueList()){%>                                
                        ['<%=weather.getGrafDate()%>',  <%if(weather.getHumidity()>99){%><%=99%><%} else {%><%=weather.getHumidity()%><%}%>, '#6e9022'],
                    <%}%>
                ]);

                var options = {
                    title:'φ in Prozent',
                    fill: '#6e9022',
                    vAxis: {minValue: 0, ticks:[25,50,75,100]}
                };

                var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
                chart.draw(data, options);
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
                    <tr><td><font>Luftfeuchtigkeit:</font> <b><%=IndexServlet.services.getWeatherToday().getHumidity()%>%</td></tr>
                    <tr><td style="font-size: 10pt"><font>Durchschnitt pro Tag:</font> <b><%=IndexServlet.services.getAvageWeatherPerDay().getHumidity() %>%</td></tr>
                    <tr><td style="font-size: 10pt"><font>Maximum:</font> <%=IndexServlet.services.getWd().maxHumidity() %></td></tr>
                    <tr><td style="font-size: 10pt"><font>Minimum:</font> <%=IndexServlet.services.getWd().minHumidity() %></td></tr>
                </table>
                <div id="chart_div" style="width: 250px; height: 200px;margin: 0%;"></div>
                </div>
                <form action="http://localhost:8080/wetter/humiditygraf.jsp" align='center' style="margin:15%">
                    <input type="submit" value="Vergrößern" id="getGraf"> 
                </form>
            </div>
            <div id='text'>
                <h3>
                    Luftfeuchtigkeitsensor
                </h3>
                <p>
                <img src="<%=request.getContextPath()%>/images/temp.jpg" style="margin: 1%; margin-top: 0%; float: left;" width="120" height="160">
                    Der Messwertgeber Typ 3030 wird zur Messung der relativen Feuchte
                    und Lufttemperatur eingesetzt. Als Sensoren dienen ein kapazitives 
                    Messelement zur Messung der relativen Feuchte und 
                    Platinwiderstand Pt 100 zur Messung der Lufttemperatur. Dabei wird
                    für die Temperaturmessung die Abhängigkeit des Widerstands von der
                    Temperatur genutzt. Beide Sensoren befinden sich in einer Messsonde
                    geschützt durch einen Membranfilter. Die Messsonde ist in eine
                    Lamellen-Strahlungsschutzhülle eingebaut, damit sich keine
                    Fremdkörper auf den Sensoren ablagern. Die Strahlungsschutzhütte
                    besteht aus 12 durchgefärbten weißen Lamellen, deren aerodynamisch
                    optimierte Form eine besonders effiziente Luftdurchströmung ermöglicht.
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
                <tr><td><font>></font><a>Luftfeuchtigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/radiation'>Globalstrahlung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/pressure'>Luftdruck</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/precipitation'>Niederschlag</a></td></tr>
            </table>
            <form align='center' action='<%=request.getContextPath()%>/humidity' method='post' style='border-spacing: 5px'>
                <input type='date' name='start' style='border-radius: 8px;margin-bottom:3%;border: 1px solid gray;font-weight: bold'/><br>
                <input type='date' name='end' style=' border-radius: 8px;border: 1px solid gray; font-weight: bold'/><br>
                <input type="submit" name='name' value="Download" style=' border-radius: 4px; background-color: #e0e2e3; margin-top: 3%;'><br>
            </form>
        </div>
    </body>
</html>
