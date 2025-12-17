<html>
    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTOperacion.tld" prefix="lsu" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
    <head>
        <title>Selecciona Orden Trabajo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConOTProducto.jsp">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">
                        <jsp:include page="./comboUnaOperacion.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONA ORDEN TRABAJO</td>
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
            </table>


            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">OT</td>
                    <td width="5%" align="center" class="letraTitulo">ITEM</td>
                    <td width="20%" align="left" class="letraTitulo">CLIENTE</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="20%" align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PED</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.TER</td>
                    <td width="5%" align="right" class="letraTitulo">%</td>
                </tr>
                <lsu:listaOTOperacion idLocalTag  = "<%=fachadaPluFicha.getIdLocalStr()%>"
                            idTipoOrdenTag = "<%=fachadaPluFicha.getIdTipoOrdenStr()%>"
                           idOperacionTag  = "<%=fachadaPluFicha.getIdOperacionStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=itemPadreVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadDf0Var%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaDf0Var%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=porcentajeTerminadoDf0Var%></td>
                    </tr>
                </lsu:listaOTOperacion>
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
</html>