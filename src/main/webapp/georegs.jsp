<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GeoRegistrations</title>

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
}
</style>


</head>
<body style="background-color: #ffc87a;">


	<table style="width: 100%">
		<tr>
			<th style="background-color: WhiteSmoke;">Sorszám</th>
			<th style="background-color: WhiteSmoke;">Projekt</th>
			<th style="background-color: WhiteSmoke;">Helyszín</th>
			<th style="background-color: WhiteSmoke;">Feladat</th>
			<th style="background-color: WhiteSmoke;">Dátum</th>
			<th style="background-color: WhiteSmoke;">Megbízó</th>
			<th style="background-color: WhiteSmoke;">Munkaszám</th>
			<th style="background-color: WhiteSmoke;">Megjegyzés</th>
			<th style="background-color: WhiteSmoke;">Mérés kezdete-vége,
				hossza [óra, perc, mperc]</th>
			<th style="background-color: WhiteSmoke;">Mért pontok száma [db]</th>
			<th style="background-color: WhiteSmoke;">Mért távolság [m]</th>
			<th style="background-color: WhiteSmoke;">Elkészült</th>
			<th style="background-color: WhiteSmoke;">Pdf fájl megnyitás</th>
		</tr>

		<c:forEach items="${geoJobs}" var="reg" varStatus="loop">

			<c:if test="${loop.count%2==0}">

				<c:if test="${!reg.isReady}">

					<tr>

						<td style="background-color: WhiteSmoke;">${loop.count}</td>
						<td style="background-color: WhiteSmoke;">${reg.settlementNameOfWork}</td>
						<td style="background-color: WhiteSmoke;">${reg.placeOfWork}</td>
						<td style="background-color: WhiteSmoke;">${reg.method}</td>
						<td style="background-color: WhiteSmoke;">${reg.date}</td>
						<td style="background-color: WhiteSmoke;">${reg.investmentManager}</td>
						<td style="background-color: WhiteSmoke;">${reg.investorCompany}</td>
						<td style="background-color: WhiteSmoke;">${reg.comment}</td>
						<td style="background-color: WhiteSmoke;">${reg.measTime}</td>
						<td style="background-color: WhiteSmoke;">${reg.measPointNumber}</td>
						<td style="background-color: WhiteSmoke;">${reg.measDist}</td>
				</c:if>


				<c:if test="${reg.isReady}">


					<td style="background-color: #87e187;">${loop.count}</td>
					<td style="background-color: #87e187;">${reg.settlementNameOfWork}</td>
					<td style="background-color: #87e187;">${reg.placeOfWork}</td>
					<td style="background-color: #87e187;">${reg.method}</td>
					<td style="background-color: #87e187;">${reg.date}</td>
					<td style="background-color: #87e187;">${reg.investmentManager}</td>
					<td style="background-color: #87e187;">${reg.investorCompany}</td>
					<td style="background-color: #87e187;">${reg.comment}</td>
					<td style="background-color: #87e187;">${reg.measTime}</td>
					<td style="background-color: #87e187;">${reg.measPointNumber}</td>
					<td style="background-color: #87e187;">${reg.measDist}</td>

				</c:if>

				<c:if test="${reg.isReady}">
					<td style="background-color: #87e187;">Igen</td>
					<td style="background-color: #87e187;"><form
							action="lookup" accept-charset="UTF-8">
							<input type="submit" value="Tallózás..." style="cursor: pointer">
						</form></td>
				</c:if>
				<c:if test="${!reg.isReady}">
					<td style="background-color: WhiteSmoke;">Nem</td>
					<td style="background-color: WhiteSmoke;"><form
							action="lookup" accept-charset="UTF-8">
							<input type="submit" value="Tallózás..." style="cursor: pointer">
						</form></td>
				</c:if>



			</c:if>


			<c:if test="${loop.count%2==1}">
				<tr>

					<c:if test="${!reg.isReady}">

						<td>${loop.count}</td>
						<td>${reg.settlementNameOfWork}</td>
						<td>${reg.placeOfWork}</td>
						<td>${reg.method}</td>
						<td>${reg.date}</td>
						<td>${reg.investmentManager}</td>
						<td>${reg.investorCompany}</td>
						<td>${reg.comment}</td>
						<td>${reg.measTime}</td>
						<td>${reg.measPointNumber}</td>
						<td>${reg.measDist}</td>

					</c:if>


					<c:if test="${reg.isReady}">

						<td style="background-color: #87e187;">${loop.count}</td>
						<td style="background-color: #87e187;">${reg.settlementNameOfWork}</td>
						<td style="background-color: #87e187;">${reg.placeOfWork}</td>
						<td style="background-color: #87e187;">${reg.method}</td>
						<td style="background-color: #87e187;">${reg.date}</td>
						<td style="background-color: #87e187;">${reg.investmentManager}</td>
						<td style="background-color: #87e187;">${reg.investorCompany}</td>
						<td style="background-color: #87e187;">${reg.comment}</td>
						<td style="background-color: #87e187;">${reg.measTime}</td>
						<td style="background-color: #87e187;">${reg.measPointNumber}</td>
						<td style="background-color: #87e187;">${reg.measDist}</td>

					</c:if>

					<c:if test="${reg.isReady}">
						<td style="background-color: #87e187;">Igen</td>
						<td style="background-color: #87e187;"><form
								action="lookup" accept-charset="UTF-8">
								<input type="submit" value="Tallózás..." style="cursor: pointer">
							</form></td>
					</c:if>
					<c:if test="${!reg.isReady}">
						<td>Nem</td>
						<td><form action="lookup" accept-charset="UTF-8">
								<input type="submit" value="Tallózás..." style="cursor: pointer">
							</form></td>
					</c:if>


				</tr>
			</c:if>

		</c:forEach>
	</table>
	<br>


	<form method="POST" action="validator">
	<h3 style="color: white">Add meg a rekord sorszámát:</h3>
	<input type="text" name="recordNumber" size="1">
	<input type="hidden" value="modify" name="operation"> 
	<input type="submit" value="Módosítás" style="cursor: pointer"/>
	</form><br>
	
	<form method="POST" action="validator">
	<input type="text" name="recordNumber" size="1">
	<input type="hidden" value="del" name="operation"> 	
	<input type="submit" value="Törlés" style="cursor: pointer"/>
	</form><br>
	
	<form method="POST" action="validator">
	<input type="text" name="recordNumber" size="1">
	<input type="hidden" value="createcoord" name="operation"> 
	<input type="submit" value="Koordináta-jegyzék készítés" style="cursor: pointer"/>	
	</form><br><hr>

 	<form id="backForm" action="InitGeoList" accept-charset="UTF-8"></form> 

	 <button onclick="goBackToGeoList()" style="float: right;cursor: pointer">Vissza</button>

	<input type="hidden" value="${msg}" id="invalid">
</body>

<script charset="UTF-8">

var info = "${msg}";
var text = ["A megadott adat nem szám.", "Nem megfelelő a megadott rekord értéke: 1 <= rekord érték <= " + info,
			"Koordináta-jegyzék létrehozva.\n\n\"coords_kit.txt\" néven található a munka \"projekt\" nevű mappájában.",
			"Koordináta-jegyzék készítése sikertelen:\n\nLétezik \"coords_kit.txt\" fájl, " + 
			"vagy nem található \"coords.txt\" fájl a munka \"projekt\" nevű mappájában."];


if(info === "notnumber"){
alert(text[0]);
}
else if(parseInt(info) > 0 ){
alert(text[1]);
}
else if(info === "true"){
alert(text[2]);	
}
else if(info === "false"){
alert(text[3]);
}


function goBackToGeoList() {
	
	document.getElementById("backForm").submit();
	
}

</script>

</html>