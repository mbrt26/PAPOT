<html>
    <%@ taglib uri="/WEB-INF/tlds/listaEscalaMaquina" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionMaquina" prefix="lst" %>

    <jsp:useBean id="fachadaJobOperacionOperario"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario" />

    <head>
        <title>Catalogo Maquina</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoPotMaquina.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO MAQUINA</td>
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
                    <td width="20%" class="letraTitulo">&nbsp;</td>                    
                    <td width="20%" class="letraTitulo">OPERACION</td>
                    <td width="20%" align="left" class="letraTitulo">NOMBRE MAQUINA</td>
                    <td width="20%" align="center" class="letraTitulo">ACCION</td>
                    <td width="20%" class="letraTitulo">&nbsp;</td>                    
                </tr>

                <tr>
                    <td width="20%" class="letraDetalle">&nbsp;</td>                    
                    <td width="20%" class="letraDetalle">
                        <select name='xIdEscalaNew'>
                            <lsv:listaEscalaMaquina>
                                <option value="<%=idEscalaVar%>">
                                    <%=nombreEscalaVar%>
                                </option>
                            </lsv:listaEscalaMaquina>
                        </select>
                    </td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input type="text" maxlength="50" size="50" value="" name="xNombreMaquina">
                    </td>
                    <td width="20%" align="center" class="letraDetalle">
                       <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="20%" class="letraDetalle">&nbsp;</td>                    
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                
                <tr>
                    <td width="20%" class="letraTitulo">&nbsp;</td>                    
                    <td width="20%" class="letraTitulo">OPERACION</td>
                    <td width="20%" align="left" class="letraTitulo">NOMBRE MAQUINA</td>
                    <td width="20%" align="center" class="letraTitulo">ACCION</td>
                    <td width="20%" class="letraTitulo">&nbsp;</td>                    
                </tr>
                <lst:listaOperacionMaquina idLocalTag="<%=fachadaJobOperacionOperario.getIdLocalStr()%>">
                <tr>
                    <td width="20%" class="letraDetalle">&nbsp;</td>                    
                    <td width="20%" class="letraDetalle"><%=nombreEscalaVar%></td>
                    <td width="20%" align="left" class="letraDetalle"><%=nombreMaquinaVar%></td>
                    <td width="20%" align="center" class="letraDetalle">
                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorCatalogoPotMaquina.jsp&accionContenedor=cambia&xIdLocal=<%=fachadaJobOperacionOperario.getIdLocalStr()%>&xIdOperacion=<%=idOperacionVar%>&xIdEscala=<%=idEscalaVar%>&xItem=<%=itemVar%>">Cambiar</a>
                    </td>
                    <td width="20%" class="letraDetalle">&nbsp;</td>                    
                </tr>                
                </lst:listaOperacionMaquina>                                
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>