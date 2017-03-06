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
		<%@page import="model.Automobile"%>
		<%
		    Automobile auto = (Automobile) session.getAttribute("auto");
		    if (auto != null) {
		%>
		<%
		    String mm = auto.getMake() + " " + auto.getModel();
		%>
		<tr>
			<td align="left"><%=mm%></td>
			<td align="left">base price</td>
			<td align="right"><%=auto.getBasePrice()%></td>
		</tr>
		<%@ page import="java.util.*"%>
		<%
		    ArrayList<String> setname = auto.getOptionSetName();
		        for (int i = 0; i < setname.size(); i++) {
		            if (setname.get(i) != null) {
		                String tmp = setname.get(i);
		                String optname = auto.getOptionChoice(tmp);
		                float price = auto.getOptionChoicePrice(tmp);
		                if (optname != null) {
		%>
		<tr>
			<td align="left"><%=tmp%></td>
			<td align="left"><%=optname%></td>
			<td align="right"><%=price%></td>
		</tr>
		<%
		    }
		            }
		        }
		%>
		<tr>
			<%
			    float total = auto.getTotalPrice();
			        String money = "$" + Float.toString(total);
			%>
			<td align="left"><b>Total Cost</b></td>
			<td align="right"><b><%=money%></b></td>
		</tr>
		<%
		    }
		%>
	</table>
</body>
</html>