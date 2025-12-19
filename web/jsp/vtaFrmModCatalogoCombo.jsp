<html>
    <%@ taglib uri="/WEB-INF/tlds/listaPluCombo" prefix="lst" %>

    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoCombo.jsp">
            <input type="hidden" name="xIdPluCombo" value="<%=fachadaPluBean.getIdPluStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA COMBO</td>
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
            </table>

            <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="8%" align="right" class="letraTitulo">PLU</td>
                        <td width="30%" align="left" class="letraTitulo">REFERENCIA</td>
                        <td width="8%" align="right" class="letraTitulo">V.L#1</td>
                        <td width="8%" align="right" class="letraTitulo">V.L#2</td>
                        <td width="8%" align="right" class="letraTitulo">V.L#3</td>
                    <td width="5%" align="right" class="letraTitulo">COSTO</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.IND</td>
                        <td width="14%" align="left" class="letraTitulo">MARCA</td>
                        <td width="8%" align="right" class="letraTitulo">%IVA</td>
                    </tr>

                    <tr>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getIdPluStr()%></td>
                        <td width="30%" align="left" class="letraDetalle"><%=fachadaPluBean.getNombreCategoria()%> <%=fachadaPluBean.getNombrePlu()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrGeneralSf0()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrMayoristaSf0()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrSucursalSf0()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrCostoDf2()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrCostoINDDf2()%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=fachadaPluBean.getNombreMarca()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getPorcentajeIvaStr()%></td>
                    </tr>

             </table>

            <table border="0" width="90%" id="tablaTitulo">
                    <tr>
                        <td width="10%" align="right" class="letraTitulo">CODIGO</td>
                        <td width="10%" align="right" class="letraTitulo">CANTIDAD</td>
                        <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    </tr>
 
                <lst:listaPluCombo idPluComboTag="<%=fachadaPluBean.getIdPluStr()%>">
                    <tr>
                        <td width="10%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmModCatalogoCombo.jsp&accionContenedor=TraerCambio&xIdPluCombo=<%=fachadaPluBean.getIdPluStr()%>&xIdPlu=<%=idPluVar%>"><%=idPluVar%></a>
                            </td>
                        <td width="10%" align="right" class="letraDetalle"><%=cantidadSf4Var%></td>
                        <td width="40%" align="left" class="letraDetalle"><%=nombreCategoriaVar%>
                                                                          <%=nombrePluVar%></td>
                    </tr>
               </lst:listaPluCombo>
            </table>

            <table border="0" width="90%" id="tablaTitulo">

                <tr>
                    <td width="10%" class="letraTitulo" align="right">
                        <input type="text" name="xIdPlu" value="" size="10">
                    </td>
                    <td width="10%" class="letraTitulo" align="left">
                        <input type="text" name="xCantidad" value="" size="10">
                    </td>
                        <td width="40%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
                </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>

</html>