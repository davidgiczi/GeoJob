document.getElementById("back").addEventListener("click", goBackToPlanRegister);
document.getElementById("input").addEventListener("click", goGeoInput);
document.getElementById("list").addEventListener("click", goGeoList);
document.getElementById("save").addEventListener("click", goGeoSave);
document.getElementById("map").addEventListener("click", goGoogleMap);
document.getElementById("tr").addEventListener("click", goTransformation);
document.getElementById("calendar").addEventListener("click", openCalendar);
document.getElementById("setup").addEventListener("click", goSetup);
document.getElementById("tetris").addEventListener("click", goTetris);
var filenotfound = document.getElementById("msg").value;


if(filenotfound === "tetris") {
	
	alert("\"Tetris.jar\" fájl nem található a \"C:\\Tetris\" mappában.");
	
}
else if(filenotfound !== "") {
	
	alert("\"" + pdf +  "\" fájl nem található a beállított mappában.")
}


function goBackToPlanRegister() {
	
document.getElementById("data").submit();

}

function goGeoInput() {
	
	location.href = "geoinput.jsp";
	
}

function goGeoList() {
	
	document.getElementById("init").submit();
	
}

function goGeoSave() {
	
	location.href = "initGeoRegSave";
	
}

function goGoogleMap() {
	
	var startPlace = prompt("Indulási, vagy a keresendő hely megadása:");

	if(startPlace == null || startPlace === ""){
		return;
	}
	
	var stopPlace = prompt("Úticél megadása útvonaltervezés esetén:");
	

	if (startPlace != null && stopPlace == null) {

		window.open("https://www.google.hu/maps/dir/" + startPlace);
	
	}
	else if(startPlace != null && stopPlace != null) {

		window.open("https://www.google.hu/maps/dir/" + startPlace + "/" + stopPlace);
	}
}


function goTransformation() {
	
	window.open("http://pf-prg.hu/trafo/trafo-4.php");
}

function openCalendar() {
	
	window.open(location.protocol + "//"+ location.host + "/Calendar");
	
}

function goSetup() {
	
	document.getElementById("gosetup").submit();
}

function goTetris() {
	
	document.getElementById("opentetris").submit();
}