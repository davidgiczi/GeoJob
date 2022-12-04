var year = document.getElementById("empty").value;
var search = document.getElementById("searched").value;
var invalid = document.getElementById("invalid").value;

if(year !== "")
alert(year + " évben nem található munka.");

if(search !== "")
alert("\"" + search + "\" kifejezés keresése alapján nem található munka.");

if(invalid === "true")
alert("Érvénytelen bemeneti adat.");