
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
                    ['Date', 'Windgeschwindigkeit',{ role: 'style' }],
                    <%for(Weather weather: IndexServlet.services.getQueueList()){%>                                
                        ['<%=weather.getGrafDate()%>',  <%=weather.getSpeedWind()%>, '#6e9022'],
                    <%}%>
                ]);

                var options = {
                    title:'V in m/s',
                    fill: '#6e9022',
                    vAxis: {minValue: 0}
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
                    <tr><td><font>Windgeschwindigkeit:</font> <b><%=IndexServlet.services.getWeatherToday().getSpeedWind()%> m/s</td></tr>
                    <tr><td style="font-size: 10pt"><font>Durchschnitt pro Tag:</font> <b><%=IndexServlet.services.getAvageWeatherPerDay().getSpeedWind() %> m/s</td></tr>
                    <tr><td style="font-size: 10pt"><font>Maximum:</font> <%=IndexServlet.services.getWd().maxSpeedWind()%></td></tr>
                    <tr><td style="font-size: 10pt"><font>Minimum:</font> <%=IndexServlet.services.getWd().minSpeedWind()%></td></tr>
                </table>
                <div id="chart_div" style="width: 250px; height: 200px;margin: 0%;"></div>
                </div>                              
                <form action="http://localhost:8080/wetter/speedwindgraf.jsp" align='center' style="margin:15%">
                    <input type="submit" value="Vergrößern" id="getGraf"> 
                </form>
            </div>
            <div id='text'>
                <h3>
                    Anemometer
                </h3>
                <p>
                    <img src="<%=request.getContextPath()%>/images/speedwind.jpg">
                    Der Messwertgeber Typ 4035 dient zur Erfassung und elektrischen 
                    Messwertübertragung der Windgeschwindigkeit. Das Gerät ist als 
                    Schalensternanemometer gebaut.  Es ist konzipiert für einen 
                    breiten Einsatzbereich in der Meteorologie und im Umweltschutz, 
                    beispielweise in automatischen Wetterstationen, auf Flughäfen, 
                    Forschungsschiffen, Industrieanlagen, mobilen Messsystemen usw. 
                    Aufgrund seiner robusten Konstruktion, seiner wasser- und 
                    schmutzabweisenden Oberfläche und der optional verfügbaren 
                    Zusatzheizung eignet sich das Gerät auch besonders für erschwerte 
                    Einsatzbedingungen, z.B. im Bereich der Windenergiemessungen 
                    oder an klimatisch extremen Standorten. Durch mehrere unterschiedliche, 
                    gleichzeitig verfügbare Ausgänge und weitere Optionen ist das Gerät
                    besonders vielseitig verwendbar
                </p>
            </div>
        </div>
        <div id='table'>
            <table id = "tableUtl"'>
                <tr><td style='font-size: 11pt; color: black; background-color: #e0e2e3; font-family: Lucida Grande,Lucida Sans Unicode,Lucida Sans,Lucida,sans-serif;'>Auswahl:</td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/index'>Gesamtdaten:</a></td></tr>
                <tr><td><font>></font><a>Windgeschwindigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/directionwind'>Windrichtung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/temperature'>Temperatur</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/humidity'>Luftfeuchtigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/radiation'>Globalstrahlung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/pressure'>Luftdruck</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/precipitation'>Niederschlag</a></td></tr>
            </table>
            <form align='center' action='<%=request.getContextPath()%>/speedwind' method='post' style='border-spacing: 5px'>
                <input type='date' name='start' style='border-radius: 8px;margin-bottom:3%;border: 1px solid gray;font-weight: bold'/><br>
                <input type='date' name='end' style=' border-radius: 8px;border: 1px solid gray; font-weight: bold'/><br>
                <input type="submit" name='name' value="Download" style='border-radius: 4px; background-color: #e0e2e3; margin-top: 3%;'><br>
            </form>
        </div>
    </body>
</html>
