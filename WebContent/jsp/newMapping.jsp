<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ngc.util.wuc.*,java.util.List,com.google.common.collect.Lists" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>New Mapping</title>
	<link rel="stylesheet" type="text/css" href="./css/index.css" />
</head>
<body>
	<header>
		<h1>Generate New Mapping</h1>
	</header>
	<form action="${pageContext.request.contextPath}/WucMappingGenerator" method="post">
		<p>Pubs Folder Location: <input name="pubs" required/></p>
		<p>Baseline Report File Location: <input name="baseline" required/></p>
		<p>Output Mapping File Name: <input name="csv" required /></p>
		<p>Log File Name: <input name="log" required/></p>
	    <input type="submit" name="run" value="Run Generator" />
	</form>
	<input id="json" name="json" type="hidden" value='${runParams}' />
	<script src="./js/jquery-3.2.1.min.js"></script>
	<script src="./js/newMapping.js"></script>
</body>
</html>