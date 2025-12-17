<HTML>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <title>Confirmar Datos</title>
    </head>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaPagoLog" prefix="lsp" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaPagoMedio" prefix="lsq" %>
    <%@ taglib uri="/WEB-INF/tlds/listaMedio" prefix="lsu" %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean" scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean"/>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"/>

    <jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
        <jsp:setProperty name="day" property="*"/>
    </jsp:useBean>

    <BODY>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmFinEmpresaVenta.jsp">
            <input type="hidden" name="idLog" value="<%=fachadaAgendaLogVisitaBean.getIdLog()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CONFIRMAR DATOS PEDIDO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
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
                    <td width="33%" class="letraDetalle">FECHA DESPACHO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="fechaEntrega" value="<%=day.getFechaFormateada()%>" size="10" maxlength="10">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">VENDEDOR</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <jsp:include page="./comboUsuarioLocalOpcion.jsp"/>
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="left" class="letraDetalle">CONTACTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="contacto" value="<%=fachadaTerceroBean.getContactoTercero()%>" size="30" maxlength="50">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="left" class="letraDetalle">OBSERVACIONES</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="observacion" size="100" maxlength="100">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="left" class="letraDetalle">NOMBRE TERCERO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xNombreTercero" value="<%=fachadaTerceroBean.getNombreTercero() + "/" +
                fachadaTerceroBean.getNombreEmpresa()%>" size="50" maxlength="50">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">VALOR PAGO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" value="0" name="xVrPago" size="12" id="xVrPago" maxlength="12">
                        <select name='xIdMedio' onchange=location.href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelEmpresaFinalizaPedido.jsp&accionContenedor=GUARDAR&idLog=<%=fachadaAgendaLogVisitaBean.getIdLog()%>&xVrPago="+xVrPago.value+'&xIdMedio='+xIdMedio.value>
                                <lsu:listaMedio>
                                    <option value="<%=idMedioVar%>">
                                    <%=nombreMedioVar%>
                                </option>
                            </lsu:listaMedio>
                        </select>
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;   </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="right" class="letraTitulo">VALOR FACTURA</td>
                    <td width="34%" align="left" class="letraTitulo">VALOR RECIBIDO</td>
                    <td width="33%" align="left" class="letraTitulo">VALOR CAMBIO</td>
                </tr>
                <lsq:liquidaPagoMedio idLocalTag="<%=fachadaAgendaLogVisitaBean.getIdLocalStr()%>"
                                      idTipoOrdenTag="<%=fachadaAgendaLogVisitaBean.getIdTipoOrdenStr()%>"
                                      idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">
                    <tr>
                        <td width="33%" align="right" class="letraResaltadaGrande"><%=vrFacturaVar%></td>
                        <td width="34%" align="left" class="letraResaltada"><%=vrPagoVar%></td>
                        <td width="33%" align="left" class="letraResaltada"><%=vrCambioVar%></td>
                    </tr>
                </lsq:liquidaPagoMedio>
            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="20%" align="left" class="letraTitulo">NOMBRE MEDIO</td>
                    <td width="20%" align="right" class="letraTitulo">VALOR MEDIO</td>
                    <td width="20%" align="left" class="letraTitulo">BANCO</td>
                    <td width="20%" align="left" class="letraTitulo">#CHEQUE/CONS</td>
                    <td width="20%" align="left" class="letraTitulo">FEC.CHEQUE/CONS</td>
                </tr>
                <lsp:listaPagoLog idLocalTag="<%=fachadaAgendaLogVisitaBean.getIdLocalStr()%>"
                                  idTipoOrdenTag="<%=fachadaAgendaLogVisitaBean.getIdTipoOrdenStr()%>"
                                  idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">
                    <tr>
                        <td width="5%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelEmpresaFinalizaPedido.jsp&accionContenedor=retiraMedio&xIdLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIdRecibo=<%=idReciboVar%>&xIdLocal=<%=idLocalVar%>">R</a>
                        </td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombreMedioVar%></td>
                        <td width="20%" align="right" class="letraDetalle"><%=vrMedioDf0Var%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombreBancoVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=idDctoMedioVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=fechaCobroCortaVar%></td>
                    </tr>
                </lsp:listaPagoLog>
            </table>

            <script type="text/javascript">
                document.getElementById('xVrPago').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="CONFIRMAR" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="REGRESAR" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>


        </form>
    </BODY>
</HTML>