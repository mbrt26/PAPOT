<html>
    <%@ taglib uri="/WEB-INF/tlds/listaAllClienteOT.tld" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionCambio.tld" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaEscala" prefix="lst" %>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <jsp:useBean id="fachadaJobProgramaPlusFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobProgramaPlusFicha" />

    <%
                String xVrEscalaStr = "1";
    %>

    <head>
        <title>Programa Producción</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorOTPrograma.jsp">
            <input type="hidden" name="xIdEscala" value="<%=fachadaJobProgramaPlusFicha.getIdEscala()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTitulo"><jsp:include page="/jsp/comboLocal.jsp"/></td>
                    <td width="34%" align="center" class="letraTitulo">PROGRAMA PRODUCCIÓN</td>
                    <td width="33%" class="letraTitulo"><jsp:include page="/jsp/comboFechaHoy.jsp"/></td>
                </tr>                
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">OPERACION</td>
                    <td width="33%" align="center" class="letraTitulo">FECHA PROGRAMA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraResaltadaTitulo">
                        <select name='xIdOperacion' onchange=location.href="GralControladorServlet?nombrePaginaRequest=/potPermisoProgramacionOTProduccion.ctr&xIdOperacion="+xIdOperacion.value+"&xFechaPrograma="+xFechaPrograma.value>
                                <lsu:listaOperacionCambio idOperacionTag="<%=fachadaJobProgramaPlusFicha.getIdOperacionStr()%>">
                                    <option value="<%=idOperacionVar%>">
                                        <%=nombreOperacionVar%>
                                    </option>
                                </lsu:listaOperacionCambio>
                        </select>
                    </td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">
                        <input type="text" name="xFechaPrograma" id="xFechaPrograma" readonly="readonly"
                               value="<%=fachadaJobProgramaPlusFicha.getFechaProgramaCorta()%>"/>
                        <img src="./img/img.gif" id="selectororden" />
                    </td>
                    <td width="33%" class="letraTitulo">
                        <select name=xVrEscala>
                            <lst:listaEscala idEscalaTag="<%=fachadaJobProgramaPlusFicha.getIdEscalaStr()%>"
                            itemTag="<%=xVrEscalaStr%>">
                                <option value="<%=itemVar%>">
                                    <%=nombreItemVar%>
                                </option>
                            </lst:listaEscala>
                        </select>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="letraDetalle">
                <tr>
                    <td width="25%" align="center" class="letraDetalle">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <input type="submit" value="Elaborar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <input type="submit" value="Listar" name="accionContenedor">
                        
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

<script type="text/javascript">


    Calendar.setup(
    {
        inputField: "xFechaPrograma",
        ifFormat:   "%Y/%m/%d",
        button:     "selectororden",
        date: new Date()
    }
);

</script>