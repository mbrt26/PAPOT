<html>

    <%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

    <%@ page import="com.solucionesweb.losbeans.negocio.TerceroBean" %>
    <%@ page import="java.util.ArrayList" %>

    <%
    HttpSession sesion = request.getSession(true);
    FachadaTerceroBean fachadaTerceroBean = 
            (FachadaTerceroBean)sesion.getAttribute("fachadaTerceroBean");
    %>


    <head>
        <title>Modifica Cliente</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <% System.out.println("Consultando Valores Facturacion Electronica Cliente"); 
               TerceroBean tercero = new TerceroBean();
             ArrayList<FachadaTerceroBean> listaFachadaCliente = tercero.listaOperaciones();
             ArrayList<FachadaTerceroBean> listaFachadaCliente2 = tercero.listaResponsabilidades();
            tercero.setIdCliente(fachadaTerceroBean.getIdCliente());
             ArrayList<FachadaTerceroBean> listaFachadaValoresSeleccionados = tercero.listaSeleccionCliente();
            
            
        %>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoCliente.jsp">
            <input type="hidden" name="xIdTipoTercero" value="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
            <input type="hidden" name="xIdCliente" value="<%=fachadaTerceroBean.getIdCliente()%>">
            <input name="xIdListaPrecio" type="hidden" value="<%=fachadaTerceroBean.getIdListaPrecio()%>">
            <input name="xNombreEmpresa" type="hidden" value="<%=fachadaTerceroBean.getNombreEmpresa()%>">
            <input name="xIdVendedor" type="hidden" value="<%=fachadaTerceroBean.getIdVendedorDi0()%>">
            <input name="xIndicador" type="hidden" value="<%=fachadaTerceroBean.getIndicador()%>">
            <input name="xNombreEmpresa" type="hidden" value="<%=fachadaTerceroBean.getNombreEmpresa()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA CLIENTE</td>
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
                        <input name="nombreTercero" type="text" value="<%=fachadaTerceroBean.getNombreTercero()%>" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">NIT/CC</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaTerceroBean.getIdTerceroFormatStr()%>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DIGITO VERIFICACION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xDigitoVerificacion" value="<%=fachadaTerceroBean.getDigitoVerificacionDi0()%>" type="text" id="xDigitoVerificacion" size="1" maxlength="1">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <!--tr>
                    <td width="33%" class="letraDetalle">EMPRESA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xNombreEmpresa" type="text" value="<%=fachadaTerceroBean.getNombreEmpresa()%>" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr-->

                <tr>
                    <td width="33%" class="letraDetalle">TIPO DCTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroTipoIdTercero.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">TIPO PERSONA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroIdPersona.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">FORMA PAGO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="idFormaPago" type="text" size="6" maxlength="6" value="<%=fachadaTerceroBean.getIdFormaPagoStr()%>">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>

                    <td width="33%" class="letraDetalle">ESTADO CLIENTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboEstadoTercero.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">AGENTE RETENCION FUENTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroIdAutoRetenedor.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">MONTO RETENCION FUENTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroIdRteFuenteVrBase.jsp" %>
                    </td>
                    <td width="34%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraResaltadaDetalle">AGENTE RETENCION IVA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroIdRteIva.jsp" %></td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>

                    <td width="33%" class="letraResaltadaDetalle">MONTO RETENCION IVA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroIdRteIvaVrBase.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">ACTIVIDAD ECONOMICA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdRteCree" type="text" id="xIdRteCree" size="6" maxlength="6" value="<%=fachadaTerceroBean.getIdRteCreeStr()%>">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>    

                <tr>
                    <td width="33%" class="letraDetalle">REGIMEN</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="/jsp/comboTerceroUnIdRegimen.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DIRECCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="direccionTercero" type="text" size="100" maxlength="100" value="<%=fachadaTerceroBean.getDireccionTercero()%>">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">DPTO/CIUDAD</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%@ include file="./comboTerceroUnDptoCiudad.jsp" %>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO FIJO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="telefonoFijo" type="text" value="<%=fachadaTerceroBean.getTelefonoFijo()%>" size="20" maxlength="30">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO CELULAR</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="telefonoCelular" type="text" value="<%=fachadaTerceroBean.getTelefonoCelular()%>" size="25" maxlength="30">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO FAX</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="telefonoFax" type="text" id="telefonoFax" value="<%=fachadaTerceroBean.getTelefonoFax()%>" size="20" maxlength="30">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">EMAIL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="email" type="text" id="email" value="<%=fachadaTerceroBean.getEmail()%>" size="40" maxlength="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">CONTACTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xContactoTercero" type="text" size="40" value="<%=fachadaTerceroBean.getContactoTercero()%>"  maxlength="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <!--tr>
                    <td width="33%" class="letraDetalle">LISTA PRECIO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdListaPrecio" type="text" value="<%=fachadaTerceroBean.getIdListaPrecio()%>" size="4" maxlength="4">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr-->

                <!--tr>
                    <td width="33%" class="letraDetalle">NOMBRE EMPRESA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xNombreEmpresa" type="text" value="<%=fachadaTerceroBean.getNombreEmpresa()%>" size="40" maxlength="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr-->

                <tr>
                    <td width="33%" class="letraDetalle">CUPO CREDITO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xCupoCredito" type="text" value="<%=fachadaTerceroBean.getCupoCreditoSf0()%>" size="10" maxlength="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">FECHA MÁXIMA FACTURACIÓN</td> 
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
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>


                <tr>
                    <td width="33%" class="letraDetalle">TIPO OPERACION</td>
                    <td width="33%" class="letraDetalle">
                        <select name="xIdOp" id="xIdOp">
                            <%  
                                  String[] idRes = new String[10];
                       for (FachadaTerceroBean listaTercero : listaFachadaCliente){
                            int selectedOP = 0;
                          
                          if( listaFachadaValoresSeleccionados.size() > 0)  
                                 
                          {  selectedOP = listaFachadaValoresSeleccionados.get(0).getIdOperacionFactura();
                            idRes = listaFachadaValoresSeleccionados.get(0).getIdResponsabilidades();
                          }
                            if(selectedOP == listaTercero.getIdOperacionFactura()){
                            %>
                            <option selected value="<%= listaTercero.getIdOperacionFactura() %>" ><%= listaTercero.getIdOperacionFactura()+" - "+ listaTercero.getNombreOperacionFactura()%></option>
                            <%  
                                                       }else{
                            %>
                            <option value="<%= listaTercero.getIdOperacionFactura() %>" ><%= listaTercero.getIdOperacionFactura()+" - "+ listaTercero.getNombreOperacionFactura()%></option>
                            <% }
                                
                                }%>
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>

                </tr>
                <%  for(int i = 1; i < 11; i++) { %>
                <tr>
                    <td width="33%" class="letraDetalle">RESPONSABILIDAD FISCAL <input  disabled="true" class = "text-md-center" style="width: 25px;" value="<%=i%>"></td>
                    <td width="33%" class="letraDetalle">
                        <select name="xIdRes<%=i%>" id="xIdRes<%=i%>">
                            <%  
                       for (FachadaTerceroBean listaTercero2 : listaFachadaCliente2){
                           String resSelected = idRes[i-1]; 
                           if(resSelected==null)resSelected ="0";
                           if(resSelected.equals(listaTercero2.getIdResponsabilidad()))
                            {
                            %>
                            <option selected value="<%= listaTercero2.getIdResponsabilidad() %>" ><%= listaTercero2.getIdResponsabilidad()+" - "+ listaTercero2.getNombreResponsabilidad()%></option>
                            <%                             
                                
                            }else{
String descripcionResponsabilidad ="SELECCIONE";
                            if(listaTercero2.getIdResponsabilidad()!= null){
descripcionResponsabilidad = listaTercero2.getIdResponsabilidad()+" - "+ listaTercero2.getNombreResponsabilidad();
}
                            %>
                            <option value="<%= listaTercero2.getIdResponsabilidad() %>" ><%=descripcionResponsabilidad%></option>
                            <%
                                }
                                }%>
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>

                </tr>
                <%  } %>


            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
