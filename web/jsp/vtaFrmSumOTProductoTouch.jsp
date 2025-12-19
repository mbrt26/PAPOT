
<html>

    <%@ taglib uri="/WEB-INF/tlds/listaOperacionTouch" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioOperacion" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaJobCausa" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaPluTipoOpcion" prefix="lsx" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />
    <jsp:useBean id="fachadaPluFichaAcumula"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <jsp:useBean id="fachadaDctoOrdenProgreso"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso" />

    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <head>
        <title>Suministrar Produccion</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link type="text/css" href="./styles/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="./js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-timepicker-addon.js"></script>
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSumOTProductoTouch.jsp">

            <input type="hidden" name="xIdLocal" value="<%=fachadaPluFicha.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaPluFicha.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaPluFicha.getIdLogStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaPluFicha.getItemPadreStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaPluFicha.getIdOperacionStr()%>">
            <input type="hidden" name="xFechaFin" value="<%=fachadaDctoOrdenProgreso.getFechaFin()%>">

            <table border="0" cellpadding="0" cellspacing="0" width="800" height="400" bgcolor="#000000">
                <!--table border="0" cellpadding="0" cellspacing="0" width="800" height="400"-->
                <!-- MSTableType="layout" -->
                <tr>
                    <td valign="top" colspan="3" height="80" align="center">
                        <!-- MSCellType="ContentHead" -->
                        <table>
                            <tr>
                                <td width="25%" align="center" bgcolor="#FFFFFF">
                                    PEDIDO <%=fachadaDctoOrdenBean.getNumeroOrden()%>
                                </td>
                                <td width="25%" align="left" bgcolor="#FFFFFF">
                                    REFERENCIA CLIENTE
                                    <br><%=fachadaPluFicha.getReferenciaCliente()%>
                                    <br>REFERENCIA
                                    <br><%=fachadaPluFicha.getReferencia()%>
                                </td>
                                <td width="25%" align="left" bgcolor="#FFFFFF">
                                    CLIENTE
                                    <br><%=fachadaTerceroBean.getNombreTercero()%>
                                </td>
                                <td width="25%" align="left" bgcolor="#FFFFFF">
                                    KG.PEDIDO <%=fachadaPluFichaAcumula.getPesoPendienteDf0()%>
                                    <br>CANTIDAD PEDIDA <%=fachadaPluFichaAcumula.getCantidadPendienteDf0()%>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" width="100">
                        <div style="height:400px; overflow:auto">
                            <table cellpadding="15" cellspacing="3">
                                <lst:listaUsuarioOperacion idLocalTag="<%=fachadaPluFicha.getIdLocalStr()%>"
                                idOperacionTag="<%=fachadaPluFicha.getIdOperacionStr()%>">
                                    <tr>
                                        <td align="left" bgcolor="#FFFFFF">
                                            <input type="radio" name="xIdOperario" value="<%=idUsuarioVar%>" style="width:30px;height:30px;border-width:thin;border-style:solid;border-color:green;color:#000000;"/>
                                            <br><%=nombreUsuarioVar%>
                                        </td>
                                    </tr>
                                </lst:listaUsuarioOperacion>
                            </table>
                        </div>
                    </td>
                    <td valign="top" width="100" align="center">
                        <table>
                            <tr>
                                <td width="20%" align="center">&nbsp;</td>
                                <td width="40%" align="center" bgcolor="#FFFFFF">PESO(KG)
                                    <br>SUMINISTRO
                                    <br><input name="xPesoTerminado" size="10" maxlength="10" value="0">
                                    <table>
                                        <tr>
                                            <td width="100%" align="left"  bgcolor="#FFFFFF">
                                                <select name="xIdPlu" size="10">
                                                    <lsx:listaPluTipoOpcion idTipoTag="<%=fachadaDctoOrdenDetalleBean.getIdTipoStr()%>">
                                                        <option value="<%=idPluVar%>">
                                                            <%=nombreCategoriaVar%> <%=nombrePluVar%>
                                                        </option>
                                                    </lsx:listaPluTipoOpcion>
                                                </select
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" colspan="3" height="80" align="center">
                        <!-- MSCellType="ContentFoot" -->
                        <table border="0" width="90%" cellpadding="15" cellspacing="3">
                            <tr>
                                <td width="33%" align="center">&nbsp;</td>
                                <td width="34%" align="center">&nbsp;</td>
                                <td width="33%" align="center">
                                    <input type="submit" style="width:120px; height:50px" name="accionContenedor" value="Guardar" />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>

</html>

<script type="text/javascript">
    $(function(){
        $('#xFechaHoraInicio').datetimepicker({
            showSecond: true,
            timeFormat: 'hh:mm:ss'
        });
    });
</script>
