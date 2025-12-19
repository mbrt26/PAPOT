<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoDetalleTx" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

    <head>
        <title>RESURTIDO DESPACHO</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLegResurtidoDespachoTx.jsp">
            <input type="hidden" name="xIdTercero" value="<%=fachadaColaboraDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaColaboraDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%>">
            <input type="hidden" name="xIdOrdenOrigen" value="<%=fachadaColaboraDctoOrdenBean.getIdOrdenOrigenStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO DESPACHO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="25%" align="left" class="letraTitulo">&nbsp;</td>
                        <td width="25%" align="center" class="letraTitulo">#ORDEN</td>
                        <td width="25%" align="center" class="letraTitulo">FECHA ORDEN</td>
                        <td width="25%" align="right" class="letraTitulo">#ARTICULOS</td>
                        <td width="25%" align="right" class="letraTitulo">VR.COMPRA</td>
                    </tr>

                    <tr>
                        <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                        <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getCantidadArticulosStr()%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrCostoBaseDf0()%></td>
                    </tr>

                </table>
            </table>
                <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="right" class="letraTitulo">CANT</td>
                    <td width="10%" align="right" class="letraTitulo">CAN.PED</td>
                    <td width="10%" align="right" class="letraTitulo">EXISTENCIA</td>   
                    <td width="10%" align="right" class="letraTitulo">C.CIAL</td>                  
                    <td width="10%" align="right" class="letraTitulo">C.IND</td>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="45%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    
                </tr>

                <lst:listaResurtidoDetalleTx idLocalTag="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>"
                                       idTipoOrdenTag="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>"
                                       idLogTag="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>"
                                       idOrdenOrigenTag="<%=fachadaColaboraDctoOrdenBean.getIdOrdenOrigenStr()%>">
                     <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                     <input type="hidden" name="xVrCosto" value="<%=xvrCostoVar%>">
                     <input type="hidden" name="xVrCostoIND" value="<%=xvrCostoINDVar%>">
                    <tr>
                        <td width="10%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidad" value="0" size="6" maxlength="6">
                        </td>
                        <td width="10%" align="right" class="letraDetalle"><%=cantidadPedidoVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=existenciaVar%></td> 
                        
                         <!--td width="10%" align="right" class="letraDetalle">
                            <input type="text" name="xVrCosto" value="0" size="10" maxlength="10">
                        </td-->
                        <td width="10%" align="right" class="letraDetalle"><%=vrCostoVar%></td>
                        <!--td width="10%" align="right" class="letraDetalle">
                            <input type="text" name="xVrCostoIND" value="0" size="10" maxlength="10">
                        </td-->
                        <td width="10%" align="right" class="letraDetalle"><%=vrCostoINDVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="45%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                    </tr>
                </lst:listaResurtidoDetalleTx>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="30%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    
                    <td width="30%" align="center" class="letraTitulo">
                        <input type="submit" value="Finalizar" name="accionContenedor">
                        </td>
                    
                    <td width="30%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>
