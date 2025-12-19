<html>

    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />
    <jsp:useBean id="fachadaPluCombo"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluCombo" />

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstCatalogoCombo.jsp">
            <input type="hidden" name="xIdPluCombo" value="<%=fachadaPluCombo.getIdPluComboStr()%>">
            <input type="hidden" name="xIdPlu" value="<%=fachadaPluCombo.getIdPluStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA PLU/COMBO</td>
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
                        <td width="30%" align="left" class="letraTitulo">REFERENCIA</td>
                        <td width="8%" align="right" class="letraTitulo">VR.L#1</td>
                        <td width="8%" align="right" class="letraTitulo">VR.L#2</td>
                        <td width="8%" align="right" class="letraTitulo">VR.L#3</td>
                        <td width="8%" align="right" class="letraTitulo">VR.COSTO</td>
                        <td width="8%" align="right" class="letraTitulo">CODIGO</td>
                        <td width="14%" align="left" class="letraTitulo">MARCA</td>
                        <td width="8%" align="right" class="letraTitulo">%IVA</td>
                        <td width="8%" align="right" class="letraTitulo">EXISTENCIA</td>
                    </tr>

                    <tr>
                        <td width="30%" align="left" class="letraDetalle"><%=fachadaPluBean.getNombreCategoria()%> <%=fachadaPluBean.getNombrePlu()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrGeneralSf0()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrMayoristaSf0()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrSucursalSf0()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getVrCostoDf2()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getIdPluStr()%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=fachadaPluBean.getNombreMarca()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getPorcentajeIvaStr()%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=fachadaPluBean.getExistenciaDf2()%></td>
                    </tr>

             </table>

            <table border="0" width="90%" id="tablaTitulo">
                    <tr>
                        <td width="10%" align="right" class="letraTitulo">CODIGO</td>
                        <td width="10%" align="right" class="letraTitulo">CANTIDAD</td>
                        <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    </tr>

                    <tr>
                        <td width="10%" align="right" class="letraDetalle">
                            <%=fachadaPluCombo.getIdPluStr()%>
                            </td>
                        <td width="10%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidad" value="<%=fachadaPluCombo.getCantidadStr()%>" size="10">
                            </td>
                        <td width="40%" align="left" class="letraDetalle">
                            <%=fachadaPluCombo.getNombreCategoria()%>
                            <%=fachadaPluCombo.getNombrePlu()%>
                            </td>
                    </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Retirar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                </tr>
            </table>
            
        </form>
    </body>

</html>