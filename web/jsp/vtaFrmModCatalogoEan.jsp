<html>
    <%@ taglib uri="/WEB-INF/tlds/listaEan" prefix="lst" %>

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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoEan.jsp">
            <input type="hidden" name="xIdPlu" value=<%=fachadaPluBean.getIdPluStr()%>>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA REFERENCIA</td>
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
                    <td width="33%" class="letraDetalle">CODIGO</td>
                    <td width="34%" align="left" class="letraDetalle"><%=fachadaPluBean.getIdPluStr()%>                   </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DESCRIPCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaPluBean.getNombreCategoria()%>
                        <%=fachadaPluBean.getNombrePlu()%>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">MARCA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaPluBean.getNombreMarca()%>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">EAN</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xEan" id="xEan" value="" size="20">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

            </table>
            
            <script type="text/javascript">
                document.getElementById('xEan').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="center" class="letraTitulo">RETIRA</td>
                    <td width="45%" align="center" class="letraTitulo">EAN</td>
                    <td width="50%" class="letraTitulo">&nbsp;</td>
                </tr>

                <lst:listaEan idPluTag = "<%=fachadaPluBean.getIdPluStr()%>">

                    <tr>
                        <td width="5%" align="center" nowrap class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmModCatalogoEan.jsp&accionContenedor=retira&xIdPlu=<%=idPluVar%>&xEan=<%=eanVar%>">R</a>
                        </td>
                        <td width="45%" align="center" class="letraDetalle"><%=eanVar%></td>
                        <td width="50%" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lst:listaEan>

            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
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