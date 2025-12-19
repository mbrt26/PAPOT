<html>

    <jsp:useBean id="fachadaContableCree"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaContableCree" />    

    <head>
        <title>Modifica RetCree</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmTraReteCree.jsp">
            <input type="hidden" value="<%=fachadaContableCree.getIdRteCree()%>" name="xIdRteCree">    

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">MODIFICA RETCREE</td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td colspan="3" width="33%" class="letraTitulo" colspan="2">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <th width="10%" align="center" class="letraTitulo">CODIGO</th>
                    <th width="30%" align="left" class="letraTitulo">ACTIVIDAD<br>ECONOMICA</th>
                    <th width="10%" align="right" class="letraTitulo">PORCENTAJE</th>
                    <th width="10%" align="right" class="letraTitulo">VALOR<br>BASE</th>
                </tr>        
                <tr>
                    <th width="10%" align="center" class="letraDetalle">
                    <%=fachadaContableCree.getIdRteCreeStr()%>
                    </th>
                    <th width="30%" align="left" class="letraDetalle">
                        <textarea rows="4" cols="50" readonly> 
                            <%=fachadaContableCree.getNombreRteCree()%>
                        </textarea> 
                    </th>
                    <th width="10%" align="right" class="letraDetalle">
                    <input type="text" name="xPorcentajeRteCree" value="<%=fachadaContableCree.getPorcentajeRteCreeStr()%>">
                    </th>
                    <th width="10%" align="right" class="letraDetalle">
                    <input type="text" name="xVrBaseRteCree" value="<%=fachadaContableCree.getVrBaseRteCreeStr()%>">                                            
                    </th>
                </tr>
            </table>                     
            <table border="0" width="90%" id="tablaTitulo">                                    
                    <td colspan="3" width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>