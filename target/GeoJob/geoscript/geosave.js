var savedRows = document.getElementById("saved").value;
var validJobs = document.getElementById("valid").value;
var loadedJobs = document.getElementById("loaded").value;
document.getElementById("allBtn").addEventListener("click", sendDataForAllGeoJobs);
document.getElementById("byYearBtn").addEventListener("click", sendDataForYearGeoJobs);


if(loadedJobs !== "") {
	alert(validJobs + " db beolvasható munkából " + loadedJobs + " db új munka létrehozva.");
}

if(validJobs === "true") {
	alert("A kiválasztott fájlban nem található beolvasható munka.")
}

if (savedRows !== "") {
	
	if(savedRows == -1){
		
	}
	else if (savedRows == -2)
		alert("A megadott dátumok alapján nem található munka.")
	else if(savedRows == -3)
		alert("Létező fájlnév, ezen a néven nem menthető fájl.")
	else
		alert(savedRows + " db munka mentve.")
}


function sendDataForAllGeoJobs() {
	var url = location.protocol + "//" + location.host + "/GeoJob/saveAllGeoRegs";
	document.getElementById("data").action = url;
	document.getElementById("data").submit();
	
}


function sendDataForYearGeoJobs() {
	var url = location.protocol + "//" + location.host + "/GeoJob/saveGeoRegsByYear";
	document.getElementById("data").action = url;
	document.getElementById("data").submit();
	
}