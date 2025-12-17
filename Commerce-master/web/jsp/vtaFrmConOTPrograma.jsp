<html>
    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTPrograma.tld" prefix="lsu" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <jsp:useBean id="fachadaJobProgramaPlusFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobProgramaPlusFicha" />

    <jsp:useBean id="fachadaJobEscala"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobEscala" />
    
    <head>
        <title>Programa Orden Trabajo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConOTPrograma.jsp">
            <input type="hidden" name="xFechaPrograma" value="<%=fachadaJobProgramaPlusFicha.getFechaProgramaCorta()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaPluFicha.getIdOperacionStr()%>">
            <input type="hidden" name="xIdEscala" value="<%=fachadaJobEscala.getIdEscalaStr()%>">
            <input type="hidden" name="xVrEscala" value="<%=fachadaJobProgramaPlusFicha.getVrEscalaStr()%>">
            
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">
                        OPERACION <jsp:include page="./comboUnaOperacion.jsp"/>
                        <br>FECHA PROGRAMA <%=fachadaJobProgramaPlusFicha.getFechaProgramaCorta()%>
                        <br>NOMBRE MAQUINA <%=fachadaJobEscala.getNombreItem()%>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">PROGRAMA ORDEN TRABAJO</td>
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


            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="!%" align="right" class="letraTitulo">ORDEN
                        <br>EJECUCION</td>
                    <td width="5%" align="right" class="letraTitulo">OT</td>
                    <td width="20%" align="left" class="letraTitulo">CLIENTE</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="20%" align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="5%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PEN</td>
                    <td width="5%" align="right" class="letraTitulo">KG.PEN</td>
                </tr>
                <lsu:listaOTPrograma idLocalTag  = "<%=fachadaPluFicha.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaPluFicha.getIdTipoOrdenStr()%>"
                idOperacionTag = "<%=fachadaPluFicha.getIdOperacionStr()%>"
                fechaProgramaTag  = "<%=fachadaJobProgramaPlusFicha.getFechaProgramaCorta()%>"
                vrEscalaTag  = "<%=fachadaJobProgramaPlusFicha.getVrEscalaStr()%>">
                    <input type="hidden" name="xLocalTipoOrdenOrdenItem" value="<%=idLocalVar%>~<%=idTipoOrdenVar%>~<%=idOrdenVar%>~<%=itemPadreVar%>"/>
                    <input type="hidden" name="xCantidadPendiente" value="<%=cantidadPendienteStrVar%>"/>
                    <input type="hidden" name="xPesoPendiente" value="<%=pesoPendienteStrVar%>"/>
                    <tr>
                        <td width="1%" align="center" class="letraDetalle">
                            <input type="text" name="xIdOrdenPrograma" value="<%=idOrdenProgramaVar%>" size="2" maxlength="2"/>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%>-<%=itemPadreVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=referenciaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadPendienteVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoPendienteVar%></td>
                    </tr>
                </lsu:listaOTPrograma>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
