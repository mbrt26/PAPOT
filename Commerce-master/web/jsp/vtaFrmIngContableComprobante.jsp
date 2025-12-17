<html>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaOrdenSubcuenta" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <jsp:useBean id="fachadaTipoOrden"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTipoOrden" />
    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <% int xIndicador = 1; %>

    <head>
        <title>Contable Comprobante</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngContableComprobante.jsp">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xDescripcion" value="<%=fachadaDctoOrdenBean.getObservacionMayuscula()%>">
            <input type="hidden" name="xIdDctoNitCC" value="<%=fachadaDctoOrdenBean.getIdDctoNitCC()%>">
            <input type="hidden" name="xIdAlcance" value="<%=fachadaDctoOrdenBean.getIdAlcanceStr()%>"
            <input type="hidden" name="xIndicadorMostrador" value="<%=xIndicador%>">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CONTABLE COMPROBANTE</td>
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
                <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="20%" align="center" class="letraTitulo">CONCEPTO</td>
                        <td width="20%" align="center" class="letraTitulo">DCTO.REF</td>
                        <td width="20%" align="center" class="letraTitulo">FECHA CUADRE</td>
                        <td width="20%" align="center" class="letraTitulo">TERCERO</td>
                        <td width="20%" align="center" class="letraTitulo">DESCRIPCION</td>
                    </tr>
                    <tr>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaTipoOrden.getNombreTipoOrden()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getIdDctoNitCC()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaTerceroBean.getNombreTercero()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getObservacionMayuscula()%></td>
                    </tr>
                </table>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">SUBCUENTA</td>
                    <td width="34%" align="left" class="letraTitulo">NOMBRE SUBCUENTA</td>
                    <td width="33%" align="center" class="letraTitulo">V.COMPROBANTE</td>
                </tr>
                <lst:listaOrdenSubcuenta idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
                    <input type="hidden" name="xIdSubcuenta" value="<%=idSubcuentaVar%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=idSubcuentaVar%></td>
                        <td width="34%" align="left" class="letraDetalle"><%=nombreSubcuentaVar%></td>
                        <td width="33%" align="center" class="letraDetalle">
                            <input type="text" value="0" name="xVrUnitario" size="12" maxlength="12">
                        </td>
                    </tr>
                </lst:listaOrdenSubcuenta>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>