<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Car Choice</title>
</head>
<body>

	<h2>Here is what you selected:</h2>
	<table>
 		 
		<tr>
			<td align="left"><%=request.getAttribute("model")%></td>
			<td align="left">base price</td>
			<td align="right"><%=request.getAttribute("basePrice")%></td>
		</tr>
		 
		 
		<tr>
			<td align="left">Color</td>
			<td align="left"><%=request.getAttribute("colorValue")%></td>
			<td align="right"><%=request.getAttribute("colorPrice")%></td>
		</tr>
		 
		 		<tr>
			<td align="left">Transmission</td>
			<td align="left"><%=request.getAttribute("transmissionValue")%></td>
			<td align="right"><%=request.getAttribute("transmissionPrice")%></td>
		</tr>
		 
		 		<tr>
			<td align="left">ABS/Traction Control</td>
			<td align="left"><%=request.getAttribute("brakesValue")%></td>
			<td align="right"><%=request.getAttribute("brakesPrice")%></td>
		</tr>
		 
		 		<tr>
			<td align="left">Side Impact Air Bags</td>
			<td align="left"><%=request.getAttribute("sideImpactValue")%></td>
			<td align="right"><%=request.getAttribute("sideImpactPrice")%></td>
		</tr>
		 
		 		<tr>
			<td align="left">Power MoonRoof</td>
			<td align="left"><%=request.getAttribute("powerMoonRoofValue")%></td>
			<td align="right"><%=request.getAttribute("powerMoonRoofPrice")%></td>
		</tr>
		 
		<tr>
			<td align="left"><b>Total Cost</b></td>
			<td align="right"><b><%=request.getAttribute("totalPrice")%></b></td>
		</tr>
		 
	</table>
</body>
</html>