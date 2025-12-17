<html>
    <head>
        <title>Movimiento Ajuste O.T.</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">

        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaAllOpcion" prefix="lsv" %>    
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionOpcion" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaTipoOrdenAlcance" prefix="lsu" %>
    
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />    
    
    <% String xIdOperacionCadena = "2,3,4,5,6,666,888,999";
       String xIdTipoOrdenOpcion = "15,16"; %>    

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInventarioAjusteOT.jsp">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
 
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MOVIMIENTO AJUSTE O.T.</td>
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
                    <td width="25%" align="center" class="letraTitulo">FECHA CORTE</td>
                    <td width="25%" align="center" class="letraTitulo">BODEGA ORIGEN</td>
                    <td width="25%" align="center" class="letraTitulo">MOVIMIENTO</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>

                </tr>

                <tr>

                    <td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaCorte" id="xFechaCorte" readonly="readonly"
                            value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
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
                        <select name="xIdTipoOrden">
                            <lsv:listaAllOpcion idTipoOrdenOpcionTag="<%=xIdTipoOrdenOpcion%>">
                                <option value="<%=idTipoOrdenVar%>">
                                    <%=nombreTipoOrdenVar%>
                                </option>
                            </lsv:listaAllOpcion>
                        </select>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Elaborar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>

<script type="text/javascript">
  Calendar.setup(
  {
    inputField: "xFechaCorte",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorinicial",
    date: new Date()
 }
);
</script>