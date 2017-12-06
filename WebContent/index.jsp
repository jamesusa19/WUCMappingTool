<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ngc.util.wuc.*,java.util.List,com.google.common.collect.Lists" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Wuc Mapping Tool</title>
	<link rel="stylesheet" type="text/css" href="./css/index.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath}/WucMappingGenerator" method="post">
		<p>Pubs Folder Location: <input name="pubs" id="pubs" /><button type="button" name="pubs" onclick="browseFolder(this)">Browse Folder</button></p>
		<p>Baseline Report File Location: <input name="baseline" id="baseline"/><button type="button" name="baseline" onclick="browseFolder(this)">Browse File</button></p>
		<p>Output Mapping File Name: <input name="csv" /></p>
		<p>Log File Name: <input name="log" /></p>
	    <input type="submit" name="run" value="Run Generator" />
	</form>
</body>
<script src="./js/index.js"></script>
</html>