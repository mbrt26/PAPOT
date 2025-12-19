<html>
    <head>

        <title>Parametro Comision Recaudo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
       
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
        
    </head>
    
    <%@ taglib uri="/WEB-INF/tlds/listaParametroComision" prefix="lst" %>   

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorParametroComision.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">PARAMETRO COMISION RECAUDO</td>
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
                    <td width="5%" align="center" class="letraTitulo">ID</td>
                    <td width="30%" align="center" class="letraTitulo">NOMBRE RANGO</td>
                    <td width="15%" align="center" class="letraTitulo">DIA INICIAL</td>
                    <td width="15%" align="center" class="letraTitulo">DIA FINAL</td>
                    <td width="10%" align="center" class="letraTitulo">PORCENTAJE</td>
                </tr>
                
                <lst:listaParametroComision>
                <tr>
                    <td width="5%" align="left" class="letraDetalle">
                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLsModificarParaComision.jsp&accionContenedor=Seleccionar&xIdLucro=<%=idLucroVar%>"><%=idLucroVar%></a>
                        </td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreLucroVar%></td>
                    <td width="15%" align="left" class="letraDetalle"><%=diaInicialVar%></td>
                    <td width="15%" align="left" class="letraDetalle"><%=diaFinalVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=porcentajeVar%></td>
                    
                </tr>
                </lst:listaParametroComision>                
            </table>
                    
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="100%" align="center" class="letraTitulo">
                        &nbsp;
                    </td>
                    
                </tr>
            </table>
        </form>
    </body>
</html>

