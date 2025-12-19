<HTML>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <title>Detallar Medio Pago</title>
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaMedio" prefix="lsu" %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean" scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean"/>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"/>

    <jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
        <jsp:setProperty name="day" property="*"/>
    </jsp:useBean>

    <jsp:useBean id="fachadaPagoBean" scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPagoBean"/>

    <BODY>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModPagoCxPPlanilla.jsp">
            <input type="hidden" name="idLog" value="<%=fachadaAgendaLogVisitaBean.getIdLog()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaPagoBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaPagoBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIndicador" value="<%=fachadaPagoBean.getIndicadorStr()%>">
            <input type="hidden" name="xIdDcto" value="<%=fachadaPagoBean.getIdDctoStr()%>">
            <input type="hidden" name="xVrPago" value="<%=fachadaPagoBean.getVrPagoStrSF()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DETALLAR MEDIO PAGO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                  idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="left" class="letraDetalle">#BANCO</td>
                    <td width="25%" align="left" class="letraDetalle">#CHEQUE</td>
                    <td width="25%" align="left" class="letraDetalle">FECHA COBRO</td>
                    <td width="25%" align="left" class="letraDetalle">MEDIO PAGO</td>
                </tr>

                <tr>
                    <td width="25%" align="left" class="letraDetalle">
                        <input type="text" name="xIdBanco" value="" size="2" maxlength="2">
                    </td>
                    <td width="25%" align="left" class="letraDetalle">
                        <input type="text" name="xIdDctoMedio" value="" size="20" maxlength="20">
                    </td>

                    <td width="25%" align="left" class="letraDetalle">
                        <input type="text" name="xFechaCobro" value="<%=fachadaPagoBean.getFechaPagoCorta()%>" size="10" maxlength="10">
                    </td>

                    <td width="25%" align="left" class="letraDetalle">
                       <select name='xIdMedio'>
                       <lsu:listaMedio>
                          <option value="<%=idMedioVar%>">
                                   <%=nombreMedioVar%>
                          </option>
                        </lsu:listaMedio>
                        </select>
                        </td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('xIdBanco').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Guardar Medio" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp</td>
                </tr>
            </table>
        </form>
    </BODY>
</HTML>