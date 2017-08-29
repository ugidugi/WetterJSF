
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
                    ['Time', 'Luftdruck',{ role: 'style' }],
              <%int n=1;for(Weather weather: IndexServlet.services.getQueueList()){%>                                
                                ['<%=weather.getGrafDate()%>', <%if(n==IndexServlet.services.getQueueList().size())%><%=1000 %><%else%><%=weather.getPressure()%><%;%>, '#6e9022'],
                    <%n++;}%>
                ]);

                var options = {
                    title:'P in hPa',
                    fill: '#6e9022',
                    vAxis:{ticks:[970,990,1010,1030], baseline:970,minValue:970,maxValue:1030}
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
                    <tr><td><font>Luftdruck:</font> <b><%=IndexServlet.services.getWeatherToday().getPressure()%> hPa</td></tr>
                    <tr><td style="font-size: 9pt"><font>Durchschnitt pro Tag:</font> <b><%=IndexServlet.services.getAvageWeatherPerDay().getPressure() %> hPa</td></tr>
                    <tr><td style="font-size: 10pt"><font>Maximum:</font> <%=IndexServlet.services.getWd().maxPressure()%></td></tr>
                    <tr><td style="font-size: 10pt"><font>Minimum:</font> <%=IndexServlet.services.getWd().minPressure()%></td></tr>
                </table>
                <div id="chart_div" style="width: 250px; height: 200px;margin: 0%;"></div>
                </div>
                <form action="http://localhost:8080/wetter/pressuregraf.jsp" align='center' style="margin:15%">
                    <input type="submit" value="Vergrößern" id="getGraf"> 
                </form>
            </div>
            <div id='text'>
                <h3> 
                    Barogeber
                </h3>
            <p>
                <img src="<%=request.getContextPath()%>/images/barogeber.jpg">
                Der Barogeber Typ 5004 ist mit einem piezoresistiven 
                Sensorelement ausgestattet, das den Druck in eine Spannung 
                umwandelt. Unter dem piezoresistiven Effekt versteht man die 
                Änderung des elektrischen Widerstandes eines Materiales unter
                der Einwirkung einer mechanischen Spannung, die dabei den 
                Umgebungsdruck darstellt. In vereinfachter skalarer Schreibweise
                ist der Zusammenhang zwischen relativer Widerstandsänderung und
                der anliegenden mechanischen Spannung gegeben.
                Damit ändert sich der elektrische Widerstand proportional zum 
                Luftdruck. Die daraus resultierende Spannungsänderung bildet
                somit den Messbereich. Bei dem verwendeten Barogeber wird am 
                Spannungsausgang ein Messwertbereich von 900-1050 hPa realisiert.
                Damit ist der Messbereich groß genug für die zu erwartenden 
                Luftdrücke in dem zu messenden Bereich.
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
                <tr><td><font>></font><a>Luftdruck</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/precipitation'>Niederschlag</a></td></tr>
            </table>
            <form align='center' action='<%=request.getContextPath()%>/pressure' method='post' style='border-spacing: 5px'>
                <input type='date' name='start' style='border-radius: 8px;margin-bottom:3%;border: 1px solid gray;font-weight: bold'/><br>
                <input type='date' name='end' style=' border-radius: 8px;border: 1px solid gray; font-weight: bold'/><br>
                <input type="submit" name='name' value="Download" style=' border-radius: 4px; background-color: #e0e2e3; margin-top: 3%;'><br>
            </form>
        </div>
    </body>
</html>