
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
    </head>
    <body>	
        <div id='test'>
            <div id='show'>
                <div id='titleShow'>
                    <h3 class="title_h3">Aktuelle Daten vom:</h3>
                    <p style=' background-color: #e0e2e3'><%=IndexServlet.services.getWeatherToday().getDate().substring(0,19)%></p>
                </div>
                <div id='weatherShow'>
                <table id='index'>                    
                    <tr><td><font>Windgeschwindigkeit: </font> <b><%=IndexServlet.services.getWeatherToday().getSpeedWind() %> m/s</td></tr>
                    <tr><td><font>Windrichtung: </font> <b><%=IndexServlet.services.getWeatherToday().getDirectionWind()%>°</b></td></tr>
                    <tr><td><font>Temperatur: </font> <b><%=IndexServlet.services.getWeatherToday().getTemperature()%> °C</b></td></tr>
                    <tr><td><font>Luftfeuchtigkeit: </font> <b><%=IndexServlet.services.getWeatherToday().getHumidity() %> %</b></td></tr>
                    <tr><td><font>Globalstrahlung: </font> <b><%=IndexServlet.services.getWeatherToday().getRadiation()%> W/m2</b></td></tr>
                    <tr><td><font>Luftdruck: </font> <b><%=IndexServlet.services.getWeatherToday().getPressure()%> hPa</b></td></tr>
                    <tr><td><font>Niederschlag: </font> <b><%=IndexServlet.services.getWeatherToday().getPrecipitation()%> mm</b></td></tr>
                </table>
                </div>
            </div>
            <div id='text'>
                <h3>
                    Überblick
                </h3>
                <p>               
                    Zwei wichtige alternative Ressourcen mit wachsender Bedeutung sind Wind- und 
                    Sonnenenergie. Jedoch stellt die Verfügbarkeit dieser Energiequellen ein Problem
                    dar. Denn nicht überall ist eine effiziente Nutzung von Wind- und Sonnenenergie
                    möglich. Um optimale Standorte für Wind- und Photovoltaikanlagen feststellen zu
                    können, benötigt man verlässliche meteorologische Daten. Die benötigten Daten 
                    können mit geeigneten Sensoren eines meteorologischen Messsystem aufgenommen und
                    mit dazu gehörenden Programmen ausgewertet werden.
                </p>
                <p>
                    Im Juli 2005 wurde auf dem Dach
                    des Gebäudes 10 eine Wetterstation installiert. Mit der erworbenen Anlage kann man
                    Langzeitmessungen durchführen, die dann für Forschungszwecke verwendet werden können.
                </p>
            </div>
        </div>
        <div id='table'>
            <table id = "tableUtl">
                <tr><td style='font-size: 11pt; color: black; background-color: #e0e2e3; font-family: Lucida Grande,Lucida Sans Unicode,Lucida Sans,Lucida,sans-serif;'>Auswahl:</td></tr>
                <tr><td><font>></font><a>Gesamtdaten:</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/speedwind'>Windgeschwindigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/directionwind'>Windrichtung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/temperature'>Temperatur</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/humidity'>Luftfeuchtigkeit</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/radiation'>Globalstrahlung</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/pressure'>Luftdruck</a></td></tr>
                <tr><td><font>></font><a href='http://localhost:8080/wetter/precipitation'>Niederschlag</a></td></tr>
            </table>
            <form align='center' action='<%=request.getContextPath()%>/index' method='post' style='border-spacing: 5px'>
                <input type='date' name='start' style='border-radius: 8px;margin-bottom:3%;border: 1px solid gray;font-weight: bold'/><br>
                <input type='date' name='end' style='border-radius: 8px;border: 1px solid gray; font-weight: bold'/><br>
                <input type="submit" name='name' value="Download" style=' border-radius: 4px; background-color: #e0e2e3; margin-top: 3%;'><br>
            </form>
        </div>
    </body>
</html>
