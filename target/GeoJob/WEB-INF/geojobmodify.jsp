<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GeoRegistration Modifying</title>
</head>
<body style="background-color:#ffc87a;">

<h1 align="center" style="color:white">Módosítás</h1><hr><br>

<form method="POST" action="modifyGeoJob" accept-charset="UTF-8">

<input type="hidden" value="${geojob.id}" name="geojobid"> 

Projekt neve:<br> <textarea  name="settlement" cols="51" rows="5">${geojob.settlementNameOfWork}</textarea><br>
Helyszín:<br> <textarea  name="place" cols="51" rows="5">${geojob.placeOfWork}</textarea><br>
Feladat:<br> <textarea  name="method" cols="51" rows="5">${geojob.method}</textarea><br>
Munka dátuma:<br> <textarea  name="date" cols="51" rows="5">${geojob.date}</textarea><br>
Megbízó:<br> <textarea  name="manager" cols="51" rows="5">${geojob.investmentManager}</textarea><br>
Munkaszám:<br> <textarea  name="investor" cols="51" rows="5">${geojob.investorCompany}</textarea><br>
Megjegyzés:<br> <textarea  name="comment" cols="51" rows="5">${geojob.comment}</textarea><br><br> 

<c:if test="${geojob.isReady}">
Befejezve: <input type="checkbox" value="true" name="ready" checked="checked" style="cursor: pointer"/><hr><br>
</c:if>

<c:if test="${!geojob.isReady}">
Befejezve: <input type="checkbox" value="true" name="ready" style="cursor: pointer"/><hr><br>
</c:if>
         
<input type="submit" value="Módosítás" style="float:left;cursor: pointer"/>

</form>

<form action="clearSession" accept-charset="UTF-8">
<input type="submit" style="float: right;cursor: pointer" value="Mégsem">
</form>


</body>

<script>



</script>
</html>