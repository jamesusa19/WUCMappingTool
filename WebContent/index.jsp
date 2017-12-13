<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ngc.util.wuc.*,java.util.List,com.google.common.collect.Lists" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Wuc Mapping Tool</title>
	<link rel="stylesheet" type="text/css" href="./css/index.css" />
</head>
<body>
	<header>
		<h1>WUC Mapping Tool</h1>
	</header>
	<button id="newMapping">New Mapping</button>
	<button id="newMapping">Open Mapping</button>
	<button id="newMapping">Delete</button>
	<button id="newMapping">Save</button>
	<table>
		<tr>
			<td>Select</td>
			<td>Mapping Name</td>
			<td>Location</td>
			<td>Baseline Report</td>
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>map1</td>
			<td>nsiv/pubs</td>
			<td>baselineReport1.txt</td>
		</tr>
	</table>
</body>
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/index.js"></script>
</html>