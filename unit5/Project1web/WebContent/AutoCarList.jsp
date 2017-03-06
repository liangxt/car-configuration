<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auto Car List</title>
</head>
<body>
	<%@ page import="java.util.*"%>
	<%@page import="model.Automobile"%>

	<h1 align="left">Auto Car List</h1>
	<form action="GetAutoCarList.do" method="post">
		<select name="autoCarKey">
			<%
			    LinkedHashMap<String, Automobile> linkMap = (LinkedHashMap<String, Automobile>) request.getSession()
			            .getAttribute("autoList");
			    for (String key : linkMap.keySet()) {
			%>
			<option value="<%=key%>"><%=key%></option>

			<%
			    }
			%>


		</select>
		<input type="submit" value="Submit">
	</form>
</body>
</html>