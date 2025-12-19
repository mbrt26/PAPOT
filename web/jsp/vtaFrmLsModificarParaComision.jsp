<html>
    <head>

        <title>Modifica Comision Recaudo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
       
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
        
    </head>
    
     <jsp:useBean id="fachadaParametroComisionBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaParametroComisionBean" />
    

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLsModificarParaComision.jsp">
            <input type="hidden" name="xIdLucro" value="<%=fachadaParametroComisionBean.getIdLucroStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA COMISION RECAUDO</td>
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
                    
                    <td width="5%" align="left" class="letraTitulo">ID</td>
                    <td width="10%" align="left" class="letraTitulo"><%=fachadaParametroComisionBean.getIdLucroStr()%></td>
                    <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                    
                </tr>   
                <tr> 
                    
                    <td width="5%" align="left" class="letraTitulo">NOMBRE RANGO</td>
                    <td width="10%" align="left" class="letraTitulo">
                        <input type="text" name="xNombreRango" value="<%=fachadaParametroComisionBean.getNombreLucro()%>" />   
                    </td>
                    <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    
                    <td width="5%" align="left" class="letraTitulo">DIA INICIAL</td>
                    <td width="10%" align="left" class="letraTitulo">
                        <input type="text" name="xDiaInicial" value="<%=fachadaParametroComisionBean.getDiaInicialDf0()%>" />   
                    </td>
                    <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    
                    <td width="5%" align="left" class="letraTitulo">DIA FINAL</td>
                    <td width="10%" align="left" class="letraTitulo">
                        <input type="text" name="xDiaFinal" value="<%=fachadaParametroComisionBean.getDiaFinalDf0()%>" />   
                    </td>
                    <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    
                    <td width="5%" align="left" class="letraTitulo">PORCENTAJE</td>
                    <td width="10%" align="left" class="letraTitulo">
                        <input type="text" name="xPorcentaje" value="<%=fachadaParametroComisionBean.getPorcentajeStr()%>" />   
                    </td>
                    <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
                
                              
            </table>
                    
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    
                </tr>
            </table>
        </form>
    </body>
</html>

