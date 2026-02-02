<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add GeoRegistration</title>
</head>
<body style="background-color:#ffc87a;">

<h1 align="center" style="color:white">Adatbevitel</h1><hr><br>

<form method="POST" action="addGeoReg" accept-charset="UTF-8">


Projekt neve:<br> <textarea  name="settlement" cols="51" rows="5"></textarea><br>
Helyszín:<br> <textarea  name="place" cols="51" rows="5"></textarea><br>
Feladat:<br> <textarea  name="method" cols="51" rows="5"></textarea><br>
Munka dátuma:<br> <textarea  name="date" cols="51" rows="5"></textarea><br>
Megbízó:<br> <textarea  name="manager" cols="51" rows="5"></textarea><br>
Munkaszám:<br> <textarea  name="investor" cols="51" rows="5"></textarea><br>
Megjegyzés:<br> <textarea  name="comment" cols="51" rows="5"></textarea><br><br>
Befejezve: <input type="checkbox" value="true" name="ready" style="cursor: pointer"/><hr><br>
         
<input type="submit" value="Küldés" style="float:left;cursor: pointer" />

</form>

<a href="geostart.jsp" style="float:right;"><button style="cursor: pointer">Vissza</button></a>

<input type="hidden" value="${msg}" id="msg">

</body>

<script src="geoscript/geoinput.js" charset="UTF-8"></script>

</html>