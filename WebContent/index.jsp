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
	<table id="mappings">
		<tr>
			<td>Select</td>
			<td>Mapping CSV</td>
			<td>Log File</td>
			<td>Baseline Report</td>
			<td>Pubs Location</td>
		</tr>
	</table>
	<button id="test">Add Rows</button>
</body>
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="./js/index.js"></script>
</html>