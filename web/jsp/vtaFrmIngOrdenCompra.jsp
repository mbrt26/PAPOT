<html>
    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoLocal" prefix="lst" %>
    <head>
        <title>Resurtido Crear Orden Compra</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngOrdenCompra.jsp">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xDiasHistoria" value="<%=fachadaDctoOrdenBean.getDiasHistoriaStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO CREAR ORDEN COMPRA</td>
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
                    <td width="33%" align="left" class="letraDetalle">#DIAS HISTORIA</td>
                    <td width="34" align="left" class="letraDetalle"><%=fachadaDctoOrdenBean.getDiasHistoriaStr()%></td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">FECHA CORTE</td>
                    <td width="34%" align="left" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">#DIAS INVENTARIO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xDiasInventario" size="4" value="<%=fachadaDctoOrdenBean.getDiasInventario()%>" id="xDiasInventario"  maxlength="4">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">PROVEEDOR</td>
                    <td width="34%" align="left" class="letraDetalle">
                               <jsp:include page="./comboTercero.jsp"/>
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33" align="left" class="letraDetalle">MARCA</td>
                    <td width="34%" align="left" class="letraDetalle">
                               <jsp:include page="./comboAllMarcaMultiple.jsp"/>
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Elaborar" name="accionContenedor">
                    </td>
                    <td width="35%" align="center" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                </tr>
            </table>
            <script type="text/javascript">
                document.getElementById('xDiasInventario').focus();
            </script>
        </form>
    </body>
</html>
 