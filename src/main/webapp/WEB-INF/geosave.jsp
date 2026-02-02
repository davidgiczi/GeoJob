<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GeoRegistration I/O</title>
<style>
body {text-align: center;}
</style>
</head>
<body style="background-color: #ffc87a;">

	<h1 align="center" style="color: white">Mentés/Beolvasás</h1>
	<hr>
	<br>

	<form method="POST" action="" accept-charset="UTF-8" id="data">

		<c:forEach begin="0" end="10" varStatus="i">

			<select name="labels" style="cursor: pointer">

				<option value="-1">-</option>

				<c:if test="${i.index == 0}">
					<option value="0" selected="selected">Településnév</option>
				</c:if>
				<c:if test="${i.index != 0}">
					<option value="0">Településnév</option>
				</c:if>

				<c:if test="${i.index == 1}">
					<option value="1" selected="selected">Helyszín</option>
				</c:if>
				<c:if test="${i.index != 1}">
					<option value="1">Helyszín</option>
				</c:if>

				<c:if test="${i.index == 2}">
					<option value="2" selected="selected">Feladat</option>
				</c:if>
				<c:if test="${i.index != 2}">
					<option value="2">Feladat</option>
				</c:if>

				<c:if test="${i.index == 3}">
					<option value="3" selected="selected">Dátum</option>
				</c:if>
				<c:if test="${i.index != 3}">
					<option value="3">Dátum</option>
				</c:if>

				<c:if test="${i.index == 4}">
					<option value="4" selected="selected">Megbízó</option>
				</c:if>
				<c:if test="${i.index != 4}">
					<option value="4">Megbízó</option>
				</c:if>

				<c:if test="${i.index == 5}">
					<option value="5" selected="selected">Beruházó</option>
				</c:if>
				<c:if test="${i.index != 5}">
					<option value="5">Beruházó</option>
				</c:if>


				<c:if test="${i.index == 6}">
					<option value="6" selected="selected">Megjegyzés</option>
				</c:if>
				<c:if test="${i.index != 6}">
					<option value="6">Megjegyzés</option>
				</c:if>


				<c:if test="${i.index == 7}">
					<option value="7" selected="selected">Mérés ideje</option>
				</c:if>
				<c:if test="${i.index != 7}">
					<option value="7">Mérés ideje</option>
				</c:if>


				<c:if test="${i.index == 8}">
					<option value="8" selected="selected">Mért pontok száma</option>
				</c:if>
				<c:if test="${i.index != 8}">
					<option value="8">Mért pontok száma</option>
				</c:if>


				<c:if test="${i.index == 9}">
					<option value="9" selected="selected">Mért távolság</option>
				</c:if>
				<c:if test="${i.index != 9}">
					<option value="9">Mért távolság</option>
				</c:if>


				<c:if test="${i.index == 10}">
					<option value="10" selected="selected">Elkészült</option>
				</c:if>
				<c:if test="${i.index != 10}">
					<option value="10">Elkészült</option>
				</c:if>
			</select>

		</c:forEach>
		<br>
		<br>
		<br> 

	<input type="submit" id="allBtn" value="Összes munka mentése" style="cursor: pointer">

	
	<br>
	<br>
	<br>


	<select name="startyear" style="cursor: pointer">
		<c:forEach items="${years}" var="year">
			<option value="${year}">${year}</option>
		</c:forEach>
	</select> -

	<select name="startmonth" style="cursor: pointer">


		<c:forEach begin="1" end="12" varStatus="i">
			<option value="${i.index}">${i.index}</option>
		</c:forEach>
	</select> -

	<select name="startday" style="cursor: pointer">

		<c:forEach begin="1" end="31" varStatus="i">
			<option value="${i.index}">${i.index}</option>
		</c:forEach>
	</select> <h4 style="color: white;">kezdő/végdátum megadása</h4>
	
	<select name="stopyear" style="cursor: pointer">
		<c:forEach items="${years}" var="year">
			<option value="${year}">${year}</option>
		</c:forEach>
	</select> -

	<select name="stopmonth" style="cursor: pointer">


		<c:forEach begin="1" end="12" varStatus="i">
			<option value="${i.index}">${i.index}</option>
		</c:forEach>
	</select> -

	<select name="stopday" style="cursor: pointer">

		<c:forEach begin="1" end="31" varStatus="i">
			<option value="${i.index}">${i.index}</option>
		</c:forEach>
	</select>
	
</form>
	

<h4 style="color: white;">kezdő/végdátum megadása</h4>

<button id="byYearBtn" style="cursor: pointer">Munkák mentése dátum alapján</button><br><br><br>

<form action="loadGeoRegs" accept-charset="UTF-8">
<input type="submit" value="Munkák beolvasása" style="cursor: pointer">
</form>
<hr>

<a href="geostart.jsp" style="float: right;"><button style="cursor: pointer">Vissza</button></a>

<input type="hidden" value="${savedjobs}" id="saved">
<input type="hidden" value="${validjobs}" id="valid">
<input type="hidden" value="${loadedjobs}" id="loaded">


</body>
<script src="geoscript/geosave.js"></script>
</html>