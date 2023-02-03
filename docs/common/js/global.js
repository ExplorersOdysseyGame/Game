function capitaliseFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

document.addEventListener('DOMContentLoaded', function() {
	// JS Metadata Tags
    jsdats = document.body.getElementsByTagName("jsdat")
    for (let i = 0; i < jsdats.length; i++) {
    	jsdat = jsdats[i]
    	jsdatExtra = jsdat.getAttribute("jdataExtra")
    	if (jsdatExtra == null) {jsdatExtra = ""}
    	jshtml = `<${jsdat.getAttribute("jname")}>${capitaliseFirstLetter(jsdat.getAttribute("jdata"))}${jsdatExtra}</${jsdat.getAttribute("jname")}>`
    	document.head.innerHTML += jshtml
    	if (jsdat.getAttribute("jhtml") != null) {
    		document.body.innerHTML = jshtml.replace(jsdat.getAttribute("jname"), jsdat.getAttribute("jhtml")).replace(jsdatExtra, "") + document.body.innerHTML
    	}
	}
	for (let i = 0; i < jsdats.length; i++) {jsdats[i].remove()}
	// JS Reference Tags
	jsrefs = document.body.getElementsByTagName("jsrel")
	for (let i = 0; i < jsrefs.length; i++) {
    	jsref = jsrefs[i]
    	jsrel = jsref.getAttribute("jrel")
    	if (jsrel == null) {jsrel = ["", ""]} else {jsrel = jsrel.split(":")}
    	jsref.outerHTML = `<span>${jsref.getAttribute("jdata")}</span>`.replace("[]", `<a href=${jsrel[0]}>${jsrel[1]}</a>`)
	}
	for (let i = 0; i < jsrefs.length; i++) {jsrefs[i].remove()}
}, false);