<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete GeoRegistration</title>

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

	<h1 align="center" style="color: white">Biztosan törölni akarod a
		kiválasztott rekordot?</h1>
	<hr>
	<br>



	<table style="width: 100%">
		<tr>
			<th style="background-color: WhiteSmoke;">Sorszám</th>
			<th style="background-color: WhiteSmoke;">Településnév</th>
			<th style="background-color: WhiteSmoke;">Helyszín</th>
			<th style="background-color: WhiteSmoke;">Feladat</th>
			<th style="background-color: WhiteSmoke;">Dátum</th>
			<th style="background-color: WhiteSmoke;">Megbízó</th>
			<th style="background-color: WhiteSmoke;">Beruházó</th>
			<th style="background-color: WhiteSmoke;">Megjegyzés</th>
			<th style="background-color: WhiteSmoke;">Mérés kezdete-vége,
				hossza [óra, perc, mperc]</th>
			<th style="background-color: WhiteSmoke;">Mért pontok száma [db]</th>
			<th style="background-color: WhiteSmoke;">Mért távolság [m]</th>
			<th style="background-color: WhiteSmoke;">Elkészült</th>
			<th style="background-color: WhiteSmoke;">E-mail</th>
		</tr>

		<tr>


			<td>1</td>
			<td>${geojob.settlementNameOfWork}</td>
			<td>${geojob.placeOfWork}</td>
			<td>${geojob.method}</td>
			<td>${geojob.date}</td>
			<td>${geojob.investmentManager}</td>
			<td>${geojob.investorCompany}</td>
			<td>${geojob.comment}</td>
			<td>${geojob.measTime}</td>
			<td>${geojob.measPointNumber}</td>
			<td>${geojob.measDist}</td>


			<c:if test="${geojob.isReady}">
				<td>Igen</td>
			</c:if>
			<c:if test="${!geojob.isReady}">
				<td>Nem</td>
			</c:if>
			<td><form action="lookup" accept-charset="UTF-8">
					<input type="submit" value="Tallózás..." style="cursor: pointer">
				</form></td>


		</tr>

	</table>
	<br>
	<form method="POST" action="deleteGeoJob" accept-charset="UTF-8">
		<input type="hidden" value="${geojob.id}" name="geojobid"> <input
			type="submit" value="Törlés" style="cursor: pointer">
	</form>

	<form action="clearSession" accept-charset="UTF-8">
		<input type="submit" value="Mégsem" style="float: right;cursor: pointer">
	</form>


</body>
</html>