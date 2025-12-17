<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTProducto.tld" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />


    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <head>
        <title>Selecciona O.T. Externa</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstOTExterna.jsp">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">
                        <jsp:include page="./comboUnaOperacion.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONA O.T. EXTERNA</td>
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
                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaDctoOrdenBean.getIdCliente()%>"
                idTipoTerceroTag="<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraResaltadaGrande"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraResaltadaGrande"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraResaltadaGrande"><%=nombreTerceroVar%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>


            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">OT</td>
                    <td width="5%" align="right" class="letraTitulo">ITEM</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">FICHA</td>
                    <td width="10%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="20%" align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PED</td>
                </tr>
                <lsu:listaOTProducto idLocalTag  = "<%=fachadaPluFicha.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaPluFicha.getIdTipoOrdenStr()%>"
                numeroOrdenTag  = "<%=fachadaPluFicha.getNumeroOrdenStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstOTExterna.jsp&accionContenedor=trae&xIdLocal=<%=fachadaPluFicha.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaPluFicha.getIdTipoOrdenStr()%>&xIdOperacion=<%=fachadaPluFicha.getIdOperacionStr()%>&xIdLog=<%=idLogVar%>&xNumeroOrden=<%=numeroOrdenVar%>&xItem=<%=itemVar%>"><%=numeroOrdenVar%></a>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=itemVar%></td>
                        <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="5%" align="right" class="letraDetalle"><%=idFichaVar%></td>
                        <td width="10%" align="left" class="letraResaltadaGrande"><%=referenciaVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                        <td width="20%" align="left" class="letraResaltadaGrande"><%=referenciaClienteVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadDf0Var%></td>
                    </tr>

                </lsu:listaOTProducto>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

</html>