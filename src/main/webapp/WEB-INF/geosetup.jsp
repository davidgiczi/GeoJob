<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Setup</title>
</head>
<body style="background-color: #ffc87a;">

	<h1 align="center" style="color: white">Beállítások</h1>
	<hr>
	<br>

	<form method="POST" action="lookup" accept-charset="UTF-8">

		<h4 style="color: white;">1) Adja meg a számítógépén a geodéziai munkák
			mentési mappáját:</h4>
		<div style="text-align: center;">
			<input type="submit" id="workfolder" value="Tallózás..." style="cursor: pointer"> <input
				type="hidden" value="url1" name="url"> <input type="text"
				size="100" value="${url1}" readonly="readonly">
				
		</div>
	</form>


	<br>

	<form method="POST" action="lookup" accept-charset="UTF-8">
		<h4 style="color: white;">2) Adja meg a számítógépén a pdf formátum
			megjelenítéséhez használni kívánt program indító fájlját:</h4>
		<div style="text-align: center;">
			<input type="submit" value="Tallózás..." style="cursor: pointer">
			 <input type="hidden" value="url2" name="url">
			 <input type="text" size="100" value="${url2}" readonly="readonly">
			
		</div>

	</form>

	<br>
	
	<form method="POST" action="lookup" accept-charset="UTF-8">
		<h4 style="color: white;">2) Adja meg a számítógépén az e-mailek
			megjelenítéséhez használni kívánt program indító fájlját:</h4>
		<div style="text-align: center;">
			<input type="submit" value="Tallózás..." style="cursor: pointer">
			 <input type="hidden" value="url3" name="url">
			 <input type="text" size="100" value="${url3}" readonly="readonly">
			
		</div>

	</form>
	
	<br>
	
	<form method="POST" action="lookup" accept-charset="UTF-8">
	<h4 style="color: white;">4) Adjon meg a számítógépén mentési mappát:</h4>
	<div style="text-align: center;">
		<input type="submit" value="Tallózás..." style="cursor: pointer"> 
		<input type="hidden" value="url4" name="url">
		<input type="text" size="100" value="${url4}" readonly="readonly">
		
	</div>
	
	</form>
	
	<br>
	<br>
	<form method="POST" action="addProperties" accept-charset="UTF-8">
	
	<div style="text-align: center;">
	<input type="submit" value="Ok" style="cursor: pointer">
	</div>
	
	</form>
	
	<br>
	<hr>

<input type="hidden" value="${msg}" id="msg">

<form method="POST" action="goSetup" accept-charset="UTF-8"></form>

<c:if test="${empty init}">
<a href="clearSession">  <button style="float: right;cursor: pointer">Vissza</button></a>
</c:if>


<script src="geoscript/geosetup.js" charset="UTF-8"></script>
</body>
</html>