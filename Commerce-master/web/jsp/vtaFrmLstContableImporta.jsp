<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
    <head>
        <title>Contable Importar</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaLocal" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaContableCuenta" prefix="lsc" %>
    <%@ taglib uri="/WEB-INF/tlds/listaContableDcto" prefix="lsd" %>

    <jsp:useBean id="fachadaTipoOrdenSubcuenta"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstContableImporta.jsp">
            <input type="hidden" name="xFechaInicial" value="<%=fachadaTipoOrdenSubcuenta.getFechaInicial()%>">
            <input type="hidden" name="xFechaFinal" value="<%=fachadaTipoOrdenSubcuenta.getFechaFinal()%>">
            <input type="hidden" name="xIdComprobante" value="<%=fachadaTipoOrdenSubcuenta.getIdComprobanteStr()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaTipoOrdenSubcuenta.getIdLocalStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CONTABLE IMPORTAR</td>
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
                    <td width="25%" align="center" class="letraTitulo">FECHA INICIAL</td>
                    <td width="25%" align="center" class="letraTitulo">FECHA FINAL</td>
                    <td width="25%" align="center" class="letraTitulo">CENTRO OPERATIVO</td>
                    <td width="25%" align="center" class="letraTitulo">COMPROBANTE</td>
                </tr>
                <tr>
                    <td width="25%" align="center" class="letraDetalle">
                        <%=fachadaTipoOrdenSubcuenta.getFechaInicial()%>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <%=fachadaTipoOrdenSubcuenta.getFechaFinal()%>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                            <lst:listaLocal idLocalTag ="<%=fachadaTipoOrdenSubcuenta.getIdLocalStr()%>">
                                    <%=nombreLocalVar%>
                            </lst:listaLocal>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                              <%=fachadaTipoOrdenSubcuenta.getIdComprobanteStr()%>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="left" class="letraTitulo">SUBCUENTA</td>
                    <td width="25%" align="left" class="letraTitulo">NOMBRE SUBCUENTA</td>
                    <td width="25%" align="right" class="letraTitulo">VR.DEBITO</td>
                    <td width="25%" align="right" class="letraTitulo">VR.CREDITO</td>
                </tr>
                <lsc:listaContableCuenta idLocalTag ="<%=fachadaTipoOrdenSubcuenta.getIdLocalStr()%>"
                                         fechaInicialTag ="<%=fachadaTipoOrdenSubcuenta.getFechaInicial()%>"
                                         fechaFinalTag ="<%=fachadaTipoOrdenSubcuenta.getFechaFinal()%>"
                                                                  >
                <tr>
                    <td width="25%" align="left" class="letraDetalle"><%=idSubcuentaVar%></td>
                    <td width="25%" align="left" class="letraDetalle"><%=nombreSubcuentaVar%></td>

                    <% if (new Integer(idAsientoVar).intValue() == 1) { %>
                       <td width="25%" align="right" class="letraDetalle"><%=vrMovimientoVar%></td>
                       <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                     <% } else { %>
                       <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                       <td width="25%" align="right" class="letraDetalle"><%=vrMovimientoVar%></td>
                     <% } %>

                </tr>
                </lsc:listaContableCuenta>
                <tr>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="16%" align="left" class="letraTitulo">DCTO</td>
                    <td width="16%" align="right" class="letraTitulo">NIT/CC</td>
                    <td width="16%" align="center" class="letraTitulo">FEC.DCTO</td>
                    <td width="16%" align="right" class="letraTitulo">VR.DEBITO</td>
                    <td width="16%" align="right" class="letraTitulo">VR.CREDITO</td>
                    <td width="16%" align="right" class="letraTitulo">VR.DIFERENCIA</td>
                </tr>
                <lsd:listaContableDcto idLocalTag ="<%=fachadaTipoOrdenSubcuenta.getIdLocalStr()%>"
                                         fechaInicialTag ="<%=fachadaTipoOrdenSubcuenta.getFechaInicial()%>"
                                         fechaFinalTag ="<%=fachadaTipoOrdenSubcuenta.getFechaFinal()%>">
                <tr>
                    <td width="16%" align="left" class="letraDetalle"><%=idDctoNitCCVar%></td>
                    <td width="16%" align="right" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="16%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="16%" align="right" class="letraDetalle"><%=vrDebitoVar%></td>
                    <td width="16%" align="right" class="letraDetalle"><%=vrCreditoVar%></td>
                    <td width="16%" align="right" class="letraDetalle"><%=vrDiferenciaVar%></td>
                </tr>
                </lsd:listaContableDcto>
                <tr>
                    <td width="16%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="16%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="16%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="16%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="16%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="16%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
