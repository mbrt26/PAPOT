<html>

    <%@ taglib uri="/WEB-INF/tlds/listaPluCategoria" prefix="lst" %>

    <jsp:useBean id="fachadaCategoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" />

    <head>
        <title>Seleccione Linea</title>
    </head>

    <%
    String xIdBodega = "1";
    %>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoReferencia.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr bgcolor="=blue">
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <th width="34%" align="center" class="letraTitulo"><%=fachadaCategoriaBean.getNombreLocal()%></th>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr><td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td></tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REFERENCIAS</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo"><%=fachadaCategoriaBean.getNombreCategoria()%></td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="1" width="90%" id="tablaTitulo">

                <tr bgcolor="green">
                    <th width="5%" align="right" class="letraTitulo">PLU</th>
                    <th width="30%" align="left" class="letraTitulo">REFERENCIA</th>
                    <th width="5%" align="center" class="letraTitulo">UNIDAD DE <br>DESPACHO</th>
                    <th width="5%" align="center" class="letraTitulo">TIPO</th>
                    <th width="5%" align="center" class="letraTitulo">PRECIO <br>COSTO</th>
                    <th width="5%" align="center" class="letraTitulo">COSTO <br>SIN IVA</th>
                    <th width="5%" align="center" class="letraTitulo">VENTA <br>LISTA 1</th>
                    <th width="5%" align="center" class="letraTitulo">MARGEN <br>LISTA 1</th>
                    <th width="5%" align="center" class="letraTitulo">VENTA <br>LISTA 2</th>
                    <th width="5%" align="center" class="letraTitulo">MARGEN <br>LISTA 2</th>
                    <th width="5%" align="center" class="letraTitulo">VENTA <br>LISTA 3</th>
                    <th width="5%" align="center" class="letraTitulo">MARGEN <br>LISTA 3</th>
                    <th width="15%" align="center" class="letraTitulo">MARCA</th>
                    <th width="5%" align="center" class="letraTitulo">PORCENTAJE <br>IVA</th>
                    <th width="5%" align="center" class="letraTitulo">INVENTARIO <br>ACTUAL</th>
                </tr>
                <lst:listaPluCategoria idLineaTag="<%=fachadaCategoriaBean.getIdLineaStr()%>"
                                       idCategoriaTag="<%=fachadaCategoriaBean.getIdCategoriaStr()%>"
                                       idLocalTag="<%=fachadaCategoriaBean.getIdLocalStr()%>"
                                       idBodegaTag="<%=xIdBodega%>" >
                    <tr>
                        <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        
                        <td width="35%" align="left" class="<%=letraEstiloVar%>">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="5%" align="center" class="letraDetalle"><%=factorDespachoVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=idTipoVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=vrCostoDf2Var%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=vrCostoINDDf2Var%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=vrGeneralDf0Var%></td>
                        <td width="5%" align="center" class="<%=letraEstiloVar%>"><%=margenVrGeneralVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=vrMayoristaDf0Var%></td>
                        <td width="5%" align="center" class="<%=letraEstiloVar%>"><%=margenVrMayoristaVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=vrSucursalDf0Var%></td>
                        <td width="5%" align="center" class="<%=letraEstiloVar%>"><%=margenVrSucursalVar%></td>
                        <td width="20%" align="center" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=porcentajeIvaVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=existenciaDf2Var%></td>
                        
                        
                        
                    </tr>

                </lst:listaPluCategoria>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>