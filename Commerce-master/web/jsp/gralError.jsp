<html>
<%-- gralErrPedido.jsp --%>
<jsp:useBean id="validacion" scope="request" type="com.solucionesweb.losbeans.utilidades.Validacion" />

    <head>
        <title>Selecciona Cotizacion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

<body>

<h3 class="letraResaltadaGrande">ERROR            : <%=validacion.getDescripcionError()%></h3>
<h3 class="letraResaltadaGrande">Nombre Campo     :  <%=validacion.getNombreCampo()%></h3>
<h3 class="letraResaltadaGrande">Valor Campo      : <%=validacion.getValorCampo()%></h3>
<h3 class="letraResaltadaGrande">Solución         : <%=validacion.getSolucion()%></h3>

<p><small><small><small><font size="2" face="Verdana">Por Favor <a href="javascript:window.history.back()">presione este vinculo</a> para intentar de nuevo
</font></small></small></small></p>
</body>
</html>