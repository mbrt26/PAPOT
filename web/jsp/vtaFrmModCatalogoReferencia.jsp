<%@page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <%@ taglib uri="/WEB-INF/tlds/listaCategoriaSeleccion" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaMarcaSeleccion" prefix="lsu"%>
    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoReferencia.jsp">
            <input type="hidden" name="xIdPlu" value=<%=fachadaPluBean.getIdPluStr()%>>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA REFERENCIA</td>
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
                    <td width="33%" class="letraDetalle">CODIGO</td>
                    <td width="34%" align="left" class="letraDetalle"><%=fachadaPluBean.getIdPluStr()%>                   </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">CATEGORIA</td>
                    <td width="34%" align="left" class="letraDetalle">

                        <select name='xIdLineaCategoria'>
                            <lst:listaCategoriaSeleccion idLineaTag="<%=fachadaPluBean.getIdLineaStr()%>"
                            idCategoriaTag="<%=fachadaPluBean.getIdCategoriaStr()%>">
                                <option value="<%=idLineaCategoriaVar%>">
                                    <%=nombreCategoriaVar%>
                                </option>
                            </lst:listaCategoriaSeleccion>
                        </select>

                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DESCRIPCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xNombrePlu" value="<%=fachadaPluBean.getNombrePlu()%>" maxlength="50" size="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">LISTA#1</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xVrGeneral" value="<%=fachadaPluBean.getVrGeneralSf0()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">LISTA#2</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xVrMayorista" value="<%=fachadaPluBean.getVrMayoristaSf0()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">LISTA#3</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xVrSucursal" value="<%=fachadaPluBean.getVrSucursalSf0()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">%IVA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xPorcentajeIva" value="<%=fachadaPluBean.getPorcentajeIvaStr()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">TIPO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xIdTipo" value="<%=fachadaPluBean.getIdTipoStr()%>" maxlength="4" size="4">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">VR.COSTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xVrCosto" value="<%=fachadaPluBean.getVrCostoSf2()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">VR.COSTO.IND</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xVrCostoIND" value="<%=fachadaPluBean.getVrCostoINDSf2()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">VR.IMPOCONSUMO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xVrImpoconsumo" value="<%=fachadaPluBean.getVrImpoconsumoStr()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaDetalle">UND.DESPACHO</td>
                    <td width="34%" align="left" class="letraResaltadaDetalle">
                        <input type="text" name="xFactorDespacho" value="<%=fachadaPluBean.getFactorDespachoStr()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraResaltadaDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">FACTOR DENSIDAD</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xFactorDensidad" value="<%=fachadaPluBean.getFactorDensidadStr()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                
                   </tr>
                   <tr>
                    <td width="33%" class="letraDetalle">FACTOR</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xFactor" value="<%=fachadaPluBean.getFactorStr()%>" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">MARCA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name='xIdMarca'>
                            <lsu:listaMarcaSeleccion idMarcaTag="<%=fachadaPluBean.getIdMarcaStr()%>">
                                <option value="<%=idMarcaVar%>">
                                    <%=nombreMarcaVar%>
                                </option>
                            </lsu:listaMarcaSeleccion>
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">REFERENCIA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xReferencia" value="<%=fachadaPluBean.getReferencia()%>" maxlength="50" size="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">PROVEEDOR PRINCIPAL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select id ="listaProveedores" name = "listaProveedores" >
                            <option value="0">SELECCIONE PROVEEDOR</option
                            <%  Vector listaProveedor = (Vector) request.getAttribute("listaProveedores");
                                final double sk_tercero = fachadaPluBean.getSk_proveedor();
                                Iterator iterator = listaProveedor.iterator();
                                while (iterator.hasNext()) {
                                    FachadaTerceroBean fachadaBean = (FachadaTerceroBean) iterator.next();
                                    if (sk_tercero == fachadaBean.getIdTercero()) {
                            %>
                            <option selected value="<%=fachadaBean.getIdTercero()%>"><%=fachadaBean.getNombreTercero()%></option>
                            <%
                            } else {
                            %>
                            <option value="<%=fachadaBean.getIdTercero()%>"><%=fachadaBean.getNombreTercero()%></option>
                            <%
                                    }
                                }%>
                        </select>

                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">DOLARIZADO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name="xDolarizado" oninvalid="this.setCustomValidity('Por favor seleccione una opción')" oninput="this.setCustomValidity('')">
                            <%
                          if (fachadaPluBean.getDolarizado()==0) {  %>
                            <option selected value= "0" >No</option>
                            <option value= "1" >Si</option>
                            <%
                            } else { %>
                            
                            <option value= "0" >No</option>
                            <option selected value= "1" >Si</option>
                            <%
                              }  
                            %>
                    </select>
                <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
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
