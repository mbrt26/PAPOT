
<html>

    <%@ taglib uri="/WEB-INF/tlds/liquidaOT" prefix="lsu" %>

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

    <% boolean xTituloOT = true;%>

    <head>
        <title>Registra Produccion</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link type="text/css" href="./styles/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="./js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-timepicker-addon.js"></script>
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmManOTProductoTouch.jsp">

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
                    <td valign="top" width="100">&nbsp;</td>
                    <td valign="top" width="100" align="center">
                        <div style="height:400px; overflow:auto">
                        <table border="0" width="90%" cellpadding="15" cellspacing="3">
                            <lsu:liquidaOT idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                            idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                            idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                            idOperacionTag="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>"
                            itemPadreTag="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

                                 <% if (xTituloOT) {%>
                                    <tr>
                                        <td width="5%" align="center" bgcolor="#FFFFFF">#</td>
                                        <td align="12%" align="right" bgcolor="#FFFFFF">PESO TERM.</td>
                                        <td align="12%" align="right" bgcolor="#FFFFFF">PESO TARA</td>
                                        <td align="12%" align="right" bgcolor="#FFFFFF">CANT TERM.</td>
                                        <td align="12%" align="right" bgcolor="#FFFFFF">PESO RETAL</td>
                                    </tr>

                                    <% xTituloOT = false;
                                         }%>
                                    <tr>

                                <tr>
                                    <td width="12%" align="center"  bgcolor="#FFFFFF">
                                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmManOTProductoTouch.jsp&accionContenedor=imprimir&xIdLocal=<%=fachadaDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=fachadaDctoOrdenBean.getIdLogStr()%>&xItem=<%=itemVar%>&xIdOperacion=<%=fachadaDctoOrdenBean.getIdOperacionStr()%>&xItemPadre=<%=fachadaDctoOrdenBean.getItemPadreStr()%>"><%=itemVar%></a>
                                    </td>
                                    <td width="12%" align="right" bgcolor="#FFFFFF"><%=pesoTerminadoVar%></td>
                                    <td width="12%" align="right" bgcolor="#FFFFFF"><%=pesoTaraVar%></td>
                                    <td width="12%" align="right" bgcolor="#FFFFFF"><%=cantidadTerminadaVar%></td>
                                    <td width="12%" align="right" bgcolor="#FFFFFF"><%=pesoPerdidoVar%></td>
                                </tr>
                            </lsu:liquidaOT>
                        </table>
                        </div>
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
                                    <input type="submit" style="width:120px; height:50px" name="accionContenedor" value="Regresar" />
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
