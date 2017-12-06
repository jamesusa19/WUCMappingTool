<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ngc.util.wuc.*,java.util.List,com.google.common.collect.Lists" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/WucMappingGenerator" method="post">
		<p>Pubs Location: <input name="pubs" id="pubs" /><button type="button" onclick="browseFolder()">Browse</button></p>
		<p>Baseline Report Location: <input name="baseline" /></p>
		<p>Output Mapping File Name: <input name="csv" /></p>
		<p>Log File Name: <input name="log" /></p>
	    <input type="submit" name="run" value="Run Generator" />
	</form>
</body>
<script>
	function browseFolder() {
		var xmlHttpReq = new XMLHttpRequest();
		xmlHttpReq.onreadystatechange = function(){
			if (xmlHttpReq.readyState==4 && xmlHttpReq.status==200){
				document.getElementById("pubs").value = xmlHttpReq.responseText;
			}
		}
		xmlHttpReq.open("GET", "./BrowseFolder", true);
		xmlHttpReq.send();
	}
</script>
</html>