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
    <jsp:useBean id="fachadaDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />
    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

   
   

    <head>
        <title>Contable Comprobante</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngRevComprobante.jsp">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xDescripcion" value="<%=fachadaDctoOrdenBean.getObservacionMayuscula()%>">
            <input type="hidden" name="xIdDctoNitCC" value="<%=fachadaDctoBean.getIdDctoNitCC()%>">
            <input type="hidden" name="xIdAlcance" value="<%=fachadaTipoOrden.getIdAlcance()%>"
            <input type="hidden" name="xIndicador" value="<%=fachadaDctoBean.getIndicadorStr()%>">
            <input type="hidden" name="xIdOrden" value="<%=fachadaDctoOrdenBean.getIdOrdenStr()%>">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REVERSA INGRESO</td>
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
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoBean.getIdDctoNitCC()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoBean.getNombreTercero()%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getObservacionMayuscula()%></td>
                    </tr>
                </table>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">SUBCUENTA</td>
                    <td width="34%" align="center" class="letraTitulo">NOMBRE SUBCUENTA</td>
                    <td width="33%" align="center" class="letraTitulo">V.COMPROBANTE</td>
                </tr>
                <lst:listaOrdenSubcuenta idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
                    <input type="hidden" name="xIdSubcuenta" value="<%=idSubcuentaVar%>">
                    <input type="hidden" name="xVrUnitario" value="<%=fachadaDctoBean.getVrBase()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=idSubcuentaVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=nombreSubcuentaVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=fachadaDctoBean.getVrBaseDf0()%></td>
                    </tr>
                </lst:listaOrdenSubcuenta>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>