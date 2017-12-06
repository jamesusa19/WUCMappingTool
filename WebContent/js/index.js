/**
 * 
 */

function browseFolder(element) {
	var xmlHttpReq = new XMLHttpRequest();
	xmlHttpReq.onreadystatechange = function(){
		if (xmlHttpReq.readyState==4 && xmlHttpReq.status==200){	
			document.getElementById(element.getAttribute("name")).value = xmlHttpReq.responseText;
		}
	}
	xmlHttpReq.open("GET", "./BrowseFolder", true);
	xmlHttpReq.send();
}