function capitaliseFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

document.addEventListener('DOMContentLoaded', function() {
    jsdats = document.body.getElementsByTagName("jsdat")
    for (let i = 0; i < jsdats.length; i++) {
    	jsdat = jsdats[i]
    	jsdatExtra = jsdat.getAttribute("jdataExtra")
    	if (jsdatExtra == null) {jsdatExtra = ""}
    	jshtml = `<${jsdat.getAttribute("jname")}>${capitaliseFirstLetter(jsdat.getAttribute("jdata"))}${jsdatExtra}</${jsdat.getAttribute("jname")}>`
    	document.head.innerHTML += jshtml
    	if (jsdat.getAttribute("jhtml") != null) {
    		document.body.innerHTML += jshtml.replace(jsdat.getAttribute("jname"), jsdat.getAttribute("jhtml")).replace(jsdatExtra, "")
    	}
	}
	for (let i = 0; i < jsdats.length; i++) {jsdats[i].remove()}
}, false);