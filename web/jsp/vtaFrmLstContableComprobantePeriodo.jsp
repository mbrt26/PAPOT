<html>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaComprobantePeriodo" prefix="lst" %>

    <jsp:useBean id="fachadaTipoOrdenSubcuenta"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta" />

    <head>
        <title>Contable Lista Comprobante</title>
 
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstContableComprobantePeriodo.jsp">
            <input type="hidden" name="xIdTipoOrden" value="0">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CONTABLE LISTA COMPROBANTE</td>
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
                    <td width="5%" align="right" class="letraTitulo">#CPTE</td>
                    <td width="5%" align="right" class="letraTitulo">#DCTO</td>
                    <td width="10%" align="center" class="letraTitulo">FECHA</td>
                    <td width="25%" align="left" class="letraTitulo">COMPROBANTE</td>
                    <td width="25%" align="left" class="letraTitulo">BENEFICIARIO</td>
                    <td width="5%" align="right" class="letraTitulo">VALOR</td>
                    <td width="25%" align="left" class="letraTitulo">DESCRICPCION</td>
                </tr>
                <lst:listaComprobantePeriodo idLocalTag="<%=fachadaTipoOrdenSubcuenta.getIdLocalStr()%>"
                                             fechaDctoTag="<%=fachadaTipoOrdenSubcuenta.getFechaDcto()%>"
                                             idAlcanceTag="<%=fachadaTipoOrdenSubcuenta.getIdAlcanceStr()%>"
                                             fechaInicialTag="<%=fachadaTipoOrdenSubcuenta.getFechaInicial()%>"
                                             fechaFinalTag="<%=fachadaTipoOrdenSubcuenta.getFechaFinal()%>">

                    <tr>
                        <td width="5%" align="right" class="letraTitulo"><%=idDctoVar%></td>
                        <td width="5%" align="right" class="letraTitulo"><%=idDctoNitCCVar%></td>
                        <td width="10%" align="center" class="letraTitulo"><%=fechaDctoVar%></td>
                        <td width="25%" align="left" class="letraTitulo"><%=nombreTipoOrdenVar%></td>
                        <td width="25%" align="left" class="letraTitulo"><%=nombreTerceroVar%></td>
                        <td width="5%" align="right" class="letraTitulo"><%=vrUnitarioVar%></td>
                        <td width="25%" align="left" class="letraTitulo"><%=observacionVar%></td>
                    </tr>
                </lst:listaComprobantePeriodo>
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
