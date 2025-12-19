<html>

<jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
    <jsp:setProperty name="jhDate" property="*" />
</jsp:useBean>

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
<%@ taglib uri="/WEB-INF/tlds/listaEmpresaNombre.tld" prefix="lista" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

<head>
    <title>Confirmar Cliente</title>
    <link href="/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/styles/estiloLetra.css" rel="stylesheet" type="text/css">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<body>
<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaSelecciona.jsp">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">SELECCIONA CLIENTE</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraResaltadaTitulo">
            <jsp:include page="./comboLocal.jsp"/>
            </td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">
           <jsp:include page="./comboFechaHoy.jsp"/>
        </td>
    </tr>

    <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                      idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
        <tr>
        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
        </tr>

        <tr>
            <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
            <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
            <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
        </tr>
    </lst:listaClienteControlAgendaNit>
</table>
<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="submit" value="Buscar" name="accionContenedor">
        </td>
        <td width="34%" align="center" class="letraTitulo">
            <input type="submit" value="Regresar" name="accionContenedor">
        </td>
        <td width="33%" class="letraTitulo">
            &nbsp;
        </td>
    </tr>
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="text" value="" name="xNombreTercero" id="xNombreTercero" value="<%=fachadaTerceroBean.getNombreTercero()%>" size="40">
        </td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
</table>
<script type="text/javascript">
    document.getElementById('xNombreTercero').focus();
</script>
<table border="0" width="90%" id="tablaDetalle">
    <tr>
        <td width="10%" align="left" class="letraDetalle">CODIGO</td>
        <td width="5%" align="center" class="letraDetalle">LOCAL</td>
        <td width="25%" align="left" class="letraDetalle">NOMBRE CLIENTE</td>
        <td width="25%" align="left" class="letraDetalle">NOMBRE EMPRESA</td>
        <td width="20%" align="left" class="letraDetalle">CIUDAD</td>
        <td width="10%" align="left" class="letraDetalle">TELEFONO</td>

    </tr>

    <lista:listaEmpresaNombre idTipoTerceroTag = "<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                              idUsuarioTag = "<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>"
                              nombreTerceroTag = "<%=fachadaTerceroBean.getNombreTercero()%>">
        <tr>
            <td width="10%" align="left" class="letraDetalle">
                <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaSelecciona.jsp&accionContenedor=Confirmar&radIdCliente=<%=idClienteSinFormatoVar%>&xIdLocalTercero=<%=idLocalTerceroVar%>"><%=idClienteVar%></a>
            </td>
            <td width="5%" align="center" class="letraDetalle"><%=idLocalTerceroVar%>
            <td width="25%" align="left" class="letraDetalle"><%=nombreTerceroVar%>
            <td width="25%" align="left" class="letraDetalle"><%=nombreEmpresaVar%>
            <td width="20%" align="left" class="letraDetalle"><%=ciudadTerceroVar%>
            <td width="10%" align="left" class="letraDetalle"><%=telefonoFijoVar%>

            </td>
        </tr>
    </lista:listaEmpresaNombre>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
</table>

</form>
</body>

</html>