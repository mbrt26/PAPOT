<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnDptoCiudad" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaTerceroLocal" prefix="lsv" %>

    <%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

    <%
        HttpSession sesion = request.getSession(true);
        FachadaTerceroBean fachadaTerceroBean = (FachadaTerceroBean) sesion.getAttribute("fachadaTerceroBean");
    %>


    <head>
        <title>Modifica Cliente Local</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoClienteLocal.jsp">
            <input type="hidden" name="xIdCliente" value="<%=fachadaTerceroBean.getIdCliente()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA CLIENTE LOCAL</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraDetalle">NOMBRE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getNombreTercero()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <%=fachadaTerceroBean.getNombreTercero()%>
                    </td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">NIT/CC</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getIdTerceroFormatStr()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <%=fachadaTerceroBean.getIdTerceroFormatStr()%>
                    </td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">EMPRESA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getNombreEmpresa()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xNombreEmpresa" type="text" size="40" maxlength="40">
                    </td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">FORMA PAGO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getIdFormaPagoStr()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <%=fachadaTerceroBean.getIdFormaPagoStr()%>
                    </td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DIRECCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getDireccionTercero()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xDireccionTercero" type="text" size="50" maxlength="50" value="">
                    </td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO FIJO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getTelefonoFijo()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xTelefonoFijo" type="text" value="" size="30" maxlength="30">
                    </td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO CELULAR</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getTelefonoCelular()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xTelefonoCelular" type="text" value="" size="30" maxlength="30">
                    </td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO FAX</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getTelefonoFax()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xTelefonoFax" type="text" id="telefonoFax" value="" size="30" maxlength="30">
                    </td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">CONTACTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getContactoTercero()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xContactoTercero" type="text" size="40" value=""  maxlength="40">
                        &nbsp;
                    </td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">EMAIL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getEmail()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <input name="xEmail" type="text" id="email" value="" size="40" maxlength="40">
                    </td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">DPTO/CIUDAD</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <lsu:listaUnDptoCiudad idCiudadTag="<%=fachadaTerceroBean.getIdDptoCiudadStr()%>">
                            <%=nombreCiudadVar%>/<%=nombreDptoVar%>
                        </lsu:listaUnDptoCiudad>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <%@ include file="./comboTerceroUnDptoCiudad.jsp" %>
                    </td>
                </tr>
                <tr hidden="true">
                    <td width="33%" align="left" class="letraDetalle">FECHA MAXIMA FACTURACION</td>
                    <td width="34%" align="left" class="letraDetalle">
                          <%=fachadaTerceroBean.getDiaMaxFacturacion()%>
                    </td>
                    <td width="33%" class="letraDetalle">
                        <select name="xIdFechaMax" id="xIdFechaMax">
                             <% 
                       int selected = fachadaTerceroBean.getDiaMaxFacturacion();
                                 for (int i = 1;i < 32; i++){
                       if(selected == i){      
                    %>
                    <option selected value="<%= i %>" ><%= i %></option>
                    <%}else{%>
                    <option value="<%= i %>" ><%= i %></option>
                    <%}
                    
                     }
                    %>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="center" class="letraTitulo">#LOCAL</td>
                    <td width="30%" align="left" class="letraTitulo">NOMBRE LOCAL</td>
                    <td width="30%" align="left" class="letraTitulo">DIRECCION</td>
                    <td width="20%" align="left" class="letraTitulo">CIUDAD/DPTO</td>
                    <td width="10%" align="left" class="letraTitulo">TELELFONO</td>
                </tr>
                <lsv:listaTerceroLocal  idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>">
                <tr>
                    <td width="10%" align="center" class="letraDetalle">
                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmModCatalogoClienteLocal.jsp&accionContenedor=cambia&xIdTipoTercero=<%=fachadaTerceroBean.getIdTipoTerceroStr()%>&xIdCliente=<%=fachadaTerceroBean.getIdCliente()%>&xIdLocalTercero=<%=idLocalTerceroVar%>"><%=idLocalTerceroVar%></a>
                        </td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreEmpresaVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=direccionTerceroVar%></td>
                    <td width="20%" align="left" class="letraDetalle">
                        <lsu:listaUnDptoCiudad idCiudadTag="<%=idDptoCiudadVar%>">
                            <%=nombreCiudadVar%>/<%=nombreDptoVar%>
                        </lsu:listaUnDptoCiudad>                    
                        </td>
                    <td width="10%" align="left" class="letraDetalle"><%=telefonoFijoVar%></td>
                </tr>
                </lsv:listaTerceroLocal>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
