<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lst" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <head>
        <title>Datos Cliente x NIT</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaDato.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DATOS CLIENTE</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <td width="33%" class="letraResaltadaTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
                </td>
                <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="33%" class="letraTitulo">
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </td>
            </table>

            <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">

                <table border="0" width="90%" id="tablaTitulo">

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
                    <tr>
                        <td width="33%" class="letraTitulo">&nbsp;</td>
                        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                        <td width="33%" class="letraTitulo">&nbsp;</td>
                    </tr>

                </table>


                <table border="0" width="90%" id="tablaDetalle">
                    <tr>
                        <td width="33%" align="left" class="letraDetalle">CODIGO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">NOMBRE CLIENTE</td>
                        <td width="34%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">ESTADO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=estadoVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">NIT</td>
                        <td width="34%" align="left" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">DIRECCION</td>
                        <td width="34%" align="left" class="letraDetalle"><%=direccionTerceroVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">TELEFONO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=telefonoFijoVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>


                    <tr>
                        <td width="33%" align="left" class="letraDetalle">FAX</td>
                        <td width="34%" align="left" class="letraDetalle"><%=telefonoFaxVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">CELULAR</td>
                        <td width="34%" align="left" class="letraDetalle"><%=telefonoCelularVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">CIUDAD</td>
                        <td width="34%" align="left" class="letraDetalle"><%=ciudadTerceroVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">CUPO CREDITO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=cupoCreditoVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">LISTA PRECIO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=listaPrecioVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">CONTACTO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=contactoTerceroVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">EMAIL</td>
                        <td width="34%" align="left" class="letraDetalle"><%=emailVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>

                    <tr>
                        <td width="33%" align="left" class="letraDetalle">FORMA PAGO</td>
                        <td width="34%" align="left" class="letraDetalle"><%=idFormaPagoVar%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    </tr>
                    <tr >
                        <td width="33%" align="left" class="letraDetalle">FECHA MÁXIMA FACTURACIÓN</td>
                        <td width="34%" align="left" class="letraDetalle"><%=fachadaTerceroBean.getDiaMaxFacturacion()%></td>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>

                        </td>
                    </tr>

                </table>


            </lista:listaClienteControlAgendaNit>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DATOS CXC</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="33%" align="left" class="letraDetalle">TIPO CXC</td>
                    <td width="34%" align="right" class="letraDetalle">#DCTOS</td>
                    <td width="33%" align="right" class="letraDetalle">$SALDO</td>
                </tr>

                <lst:listaCuentaLocalTotal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                           idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                           idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                           idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="left" class="letraDetalle"><%=tipoCarteraVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=numeroDctosVar%></td>
                        <td width="33%" align="right" class="letraDetalle"><%=vrSaldoVar%></td>
                    </tr>
                </lst:listaCuentaLocalTotal>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>