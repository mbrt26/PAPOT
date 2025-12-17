<html>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaComprobanteCuadre" prefix="lst" %>

    <jsp:useBean id="fachadaDctoCuadre"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoCuadre" />

    <head>
        <title>Estado Cuadre</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstCierreCaja.jsp">
            <input type="hidden" name="xIdTipoOrden" value="0">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REPORTE ESTADO CUADRE</td>
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

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="center" class="letraTitulo">FECHA CUADRE</td>
                    <td width="5%" align="center" class="letraTitulo">ESTADO</td>
                    <td width="20%" align="center" class="letraTitulo">SALDO INICIAL</td>
                    <td width="20%" align="center" class="letraTitulo">VR INGRESOS</td>
                    <td width="20%" align="center" class="letraTitulo">VR EGRESOS</td>
                    <td width="20%" align="center" class="letraTitulo">SALDO FINAL</td>
                    <td width="5%" align="center" class="letraTitulo">FECHA CIERRE</td>
                </tr>
                <lst:listaComprobanteCuadre indicadorInicialTag="<%=fachadaDctoCuadre.getIndicadorInicial()%>"
                                    indicadorFinalTag="<%=fachadaDctoCuadre.getIndicadorFinal()%>">

                    <tr>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaCuadreVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=nombreEstadoVar%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=saldoInicialVar%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=vrIngresoVar%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=vrEgresoVar%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=saldoFinalVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaOperacionVar%></td>
                    </tr>
                </lst:listaComprobanteCuadre>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>