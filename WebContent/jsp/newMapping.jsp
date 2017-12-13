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
		<p>Pubs Folder Location: <input name="pubs"/></p>
		<p>Baseline Report File Location: <input name="baseline"/></p>
		<p>Output Mapping File Name: <input name="csv" /></p>
		<p>Log File Name: <input name="log" /></p>
	    <input type="submit" name="run" value="Run Generator" />
	</form>
</body>
</html>