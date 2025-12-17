<html>
<%-- gralErrAgendaIngresarCliente.jsp --%>
<jsp:useBean id="validacion" scope="request" type="com.solucionesweb.losbeans.utilidades.Validacion" />

<head>
<title></title>
</head>

<body>

<h2><small><small><small><font size="2" face="Verdana">Observaciones de la entrada del usuario
</font></small></small></small></h2>

<h3><small><small><small><font size="2" face="Verdana">Código Error:
</font></small></small></small></h3>

<h3><small><small><small><font size="2" face="Verdana">Descripción Error:
</font> </small></small></small></h3>
<font face="Verdana" size="2">
<%=validacion.getDescripcionError()%>

</font>

<h3><small><small><small><font size="2" face="Verdana">Nombre Campo: </font></small></small></small></h3>
<font face="Verdana" size="2">
<%=validacion.getNombreCampo()%>

</font>

<h3><small><small><small><font size="2" face="Verdana">Valor Campo: </font></small></small></small></h3>
<font face="Verdana" size="2">
<%=validacion.getValorCampo()%>

</font>

<h3><small><small><small><font size="2" face="Verdana">Solución: </font> </small></small></small></h3>
<font face="Verdana" size="2">
<%=validacion.getSolucion()%>

</font>

<hr>

<p><small><small><small><font size="2" face="Verdana">Por Favor <a href="javascript:window.history.back()">presione este vinculo</a> para intentar de nuevo
</font></small></small></small></p>
</body>
</html>