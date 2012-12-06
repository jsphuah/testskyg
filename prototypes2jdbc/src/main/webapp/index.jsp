<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JSP Verification Service</title>
</head>
<body>

<form action="./serviceControlServlet?" method="post">

<table>

	<tr>
		<td>Do you want to XML format <input type="checkbox" value="xml" name="format" checked="checked"/></td>
	</tr>
	<tr>
		<td>Input ServiceName<input type="text" value="SearchAirPortMaster" name="serviceName"/></td>
	</tr>
	<tr>
		<td><textarea rows="40" cols="60" name="param"><?xml version="1.0" encoding="utf-8"?><serviceRequestSearchAirPortMaster><clientId>iwaki</clientId><requestId>ryosuke</requestId><airPort><aptCd>HND</aptCd></airPort></serviceRequestSearchAirPortMaster></textarea></td>
	</tr>
		<td><input type="submit" value="Start Verification"></td>
</table>

</form>


</body>
</html>