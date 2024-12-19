<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Register for Geodesy</title>
</head>
<body style="background-color:#ffc87a;">

<h1 align="center" style="color:white">Geodézia</h1>
<a id="input" style="float: left ;color: white;cursor: pointer">Adatbevitel&nbsp;&nbsp;&nbsp;</a>
<a id="list" style="float: left ;color: white;cursor: pointer">Listázás/Keresés&nbsp;&nbsp;&nbsp;</a>
<a id="save" style="float: left ;color: white;cursor: pointer">Mentés/Beolvasás&nbsp;&nbsp;&nbsp;</a>
<a id="map" style="float: left ;color: white;cursor: pointer"><span style="color: #4285F4;">G</span>
															 <span style="color: #DB4437;">o</span>
															 <span style="color: #F4B400;">o</span>
															 <span style="color: #4285F4;">g</span>
															 <span style="color: #0F9D58;">l</span>
															 <span style="color: #DB4437;">e</span>
															 Maps&nbsp;&nbsp;&nbsp;</a>
<a id="tr" style="float: left ;color: white;cursor: pointer">Transzformáció&nbsp;&nbsp;&nbsp;</a>
<a id="calendar" style="float: left ;color: white;cursor: pointer">Naptár&nbsp;&nbsp;&nbsp;</a>
<a id="setup" style="float: left ;color: white;cursor: pointer">Beállítások&nbsp;&nbsp;&nbsp;</a>
<a id="tetris" style="float: left ;color: white;cursor: pointer">Tetris&nbsp;&nbsp;&nbsp;</a>



<br>
<hr>
<button id="back" style="float: right;cursor: pointer">Kilépés</button>

<form id="init" action="InitGeoList" accept-charset="UTF-8"></form>

<form id="data" method="POST" action="init" accept="UTF-8">
<input  type="hidden" name="user" value="">
<input  type="hidden" name="pass" value="">
</form>

<form id="open" action="openMonth" accept-charset="UTF-8"></form>
<form id="gosetup" action="goSetup" accept-charset="UTF-8"></form>
<form id="opentetris" action="openTetris" accept-charset="UTF-8"></form>
<input id="msg" type="hidden" value="${missed}">


<script src="geoscript/geostart.js" charset="UTF-8"></script>

</body>
</html>