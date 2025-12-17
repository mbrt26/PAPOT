<html>

<%@ taglib uri="/WEB-INF/tlds/listaContacto" prefix="contacto" %>

<head>
<title>Contacto Visita</title>
</head>

<% String idLog = request.getParameter("idLog"); %>


<body>
<b><font size="5" face="Verdana" color="#0000A0">Contacto Visita</font></b>

<table border="1" cellspacing="0" width="90%">

  <contacto:listaContacto idLogTag = "<%=idLog%>">

	<tr>
		<td><b><font face="Verdana" size="2">Nombre Contacto</font></b></td>
	</tr>
	<tr>
		<td><%=nombreContactoVar%></td>
	</tr>
	<tr>
		<td><b><font face="Verdana" size="2">Teléfono Contacto</font></b></td>
	</tr>
	<tr>
		<td><%=telefonoContactoVar%></td>
	</tr>

  </contacto:listaContacto>

</table>

</body>

</html>