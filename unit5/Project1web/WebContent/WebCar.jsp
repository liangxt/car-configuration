<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Car Choice</title>
</head>
<body>

	<h1 align="left">Basic Car Choice</h1>
	<form action="GetOptionSets.do" method="post">
		<table>
			<%@page import="model.Automobile"%>
			<%
			    Automobile auto = (Automobile) session.getAttribute("auto");
			    if (auto != null) {
			%>
			<tr>
				<td align="center"><b>Make/Model:</b></td>
				<%
				    String mm = auto.getMake() + " " + auto.getModel();
				%>
				<td align="left"><%=mm%></td>
			</tr>

			<%@ page import="java.util.*"%>
			<%
			    ArrayList<String> setname = auto.getOptionSetName();
			        for (int i = 0; i < setname.size(); i++) {
			            if (setname.get(i) != null) {
			                String tmp = setname.get(i);
			%>
			<tr>
				<td align="center"><b><%=tmp%>:</b></td>
				<td align="left"><select name="<%=tmp%>">
						<%
						    ArrayList<String> optname = auto.getOptionName(tmp);
						                for (int j = 0; j < optname.size(); j++) {
						                    if (optname.get(j) != null) {
						                        String name = optname.get(j);
						%>
						<option value="<%=name%>"><%=name%></option>
						<%
						    }
						                }
						%>
				</select></td>
			</tr>
			<%
			    }
			        }
			    }
			%>
			<tr>
				<td align="right"><input type="submit" value="Done"></td>
			</tr>
		</table>
	</form>
</body>
</html>