
<html>
    <head>
        <title>Traslado Inventario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaOperacionOpcion.tld" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaAllOpcion" prefix="lsv" %>
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <% String xIdOperacionCadena = "2,3,4,5,6,666,888,999";
       String xIdTipoOrdenOpcion = "4,18";      
   %>
    
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInventarioMovimiento.jsp">
            <input type="hidden" name="xIdCliente" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">TRASLADO INVENTARIO</td>
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
                    <td width="25%" align="center" class="letraTitulo">MOVIMIENTO</td>
                    <td width="25%" align="center" class="letraTitulo">BODEGA ORIGEN</td>
                    <td width="25%" align="center" class="letraTitulo">BODEGA DESTINO</td>
                    <td width="25%" align="center" class="letraTitulo"># O.T.</td>
                </tr>
                <tr>
                    <td width="25%" align="center" class="letraDetalle">
                        <select name="xIdTipoOrden">
                            <lsv:listaAllOpcion idTipoOrdenOpcionTag="<%=xIdTipoOrdenOpcion%>">
                                <option value="<%=idTipoOrdenVar%>">
                                    <%=nombreTipoOrdenVar%>
                                </option>
                            </lsv:listaAllOpcion>
                        </select>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <select name='xIdBodegaOrigen'>
                            <lst:listaOperacionOpcion idOperacionCadenaTag="<%=xIdOperacionCadena%>">
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lst:listaOperacionOpcion>
                        </select>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <select name='xIdBodegaDestino'>
                            <lst:listaOperacionOpcion idOperacionCadenaTag="<%=xIdOperacionCadena%>">
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lst:listaOperacionOpcion>
                        </select>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <input type="text" name="xNumeroOrden" value="0" id="xNumeroOrden" size="6" maxlength="6">
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">TERCERO</td>
                </tr>                  
                <tr>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">
                        <jsp:include page="./comboTerceroOperacion.jsp"/>
                    </td>
                </tr>                  
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Elaborar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>