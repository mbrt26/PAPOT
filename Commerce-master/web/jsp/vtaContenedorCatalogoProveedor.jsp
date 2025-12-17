<html>
    <head>
        <title>Catalogo Cliente</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoProveedor.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO PROVEEDOR</td>                    
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
                    <td width="33%" class="letraDetalle">NOMBRE TERCERO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="nombreTercero" type="text" id="nombreTercero" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">NIT/CC</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdCliente" type="text" id="xIdCliente" size="16" maxlength="16">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">DIGITO VERIFICACION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xDigitoVerificacion" value="" type="text" id="xDigitoVerificacion" size="1" maxlength="1">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">TIPO DCTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="tipoIdTercero" id="tipoIdTercero">
                            <option value="C">Cedula
                            <option value="A">Nit
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">TIPO PERSONA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="idPersona" id="idPersona">
                            <option value="0">Natural
                            <option value="1">Juridica
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">FORMA PAGO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="idFormaPago" value="0" type="text" size="6" maxlength="6">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">AGENTE RETENCION FUENTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="idAutoRetenedor" id="idAutoRetenedor">
                            <option value="0">Si
                            <option value="1">No
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">MONTO RETENCION FUENTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="xIdRteFuenteVrBase" id="xIdRteFuenteVrBase">
                            <option value="0">VR.TOPE
                            <option value="1">VR.COMPLETO
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">AGENTE RETENCION IVA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="xIdRteIva" id="xIdRteIva">
                            <option value="0">Si
                            <option value="1">No
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">MONTO RETENCION IVA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="xIdRteIvaVrBase" id="xIdRteIvaVrBase">
                            <option value="0">VR.TOPE
                            <option value="1">VR.COMPLETO
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>                

                <tr>
                    <td width="33%" class="letraDetalle">ACTIVIDAD ECONOMICA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdRteCree" type="text" id="xIdRteCree" size="6" maxlength="6">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">REGIMEN</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="./comboRegimen.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DIRECCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="direccionTercero" type="text" id="direccionTercero" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">DPTO/CIUDAD</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="./comboDptoCiudad.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO FIJO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="telefonoFijo" type="text" id="telefonoFijo" size="20" maxlength="30">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO CELULAR</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="telefonoCelular" type="text" id="telefonoCelular" size="25" maxlength="30">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO FAX</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="telefonoFax" type="text" id="telefonoFax" size="20" maxlength="30">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">EMAIL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="email" type="text" id="email" size="40" maxlength="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CONTACTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xContactoTercero" type="text" size="40" maxlength="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">LISTA PRECIO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <!--input name="xIdListaPrecio" value="0" type="text" size="4" maxlength="4"-->
                        <select name="xIdListaPrecio" id="xIdListaPrecio">
                            <option value="1">1
                            <option value="2">2
                            <option value="3">3
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">NOMBRE EMPRESA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xNombreEmpresa" type="text" size="40" maxlength="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CUPO CREDITO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xCupoCredito" value="0" type="text" size="10" maxlength="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CANAL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="./comboTerceroClase.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">VENDEDOR</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="./comboUsuarioVendedor.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                    <td width="34%"  class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
