<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaEscalaBase" prefix="lsb" %>
    <%@ taglib uri="/WEB-INF/tlds/listaEscala" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaPluTipo" prefix="lsp" %>
     <%@ taglib uri="/WEB-INF/tlds/listaPluCategoriaOpcion" prefix="lsa" %>

    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <%
                String xIdTipoMaterial = "4";
                String xIdOperacion = "1";
                String xItem = "1";
                
   String xIdLineaMP = "1";
   String idplu ="0";
   String xIdCategoriaOtraRef = "22";
   String xIdCategoriaOtraRef1 = "23";
    %>

    <head>
        <title>Crear Referencia</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <script src="./js/jquery-1.5.1.min.js"></script>
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmCreOrdenTrabajo.jsp">
            <input type="hidden" name="xIdLog" value="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CREAR REFERENCIA</td>
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
                    <td width="10%" align="left" class="letraTitulo">PARAMETO</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="left" class="letraTitulo">VALOR</td>
                    <td width="70%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="10%" align="left" class="letraDetalle">MATERIAL</td>
                    <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="10%" align="left" class="letraDetalle">
                        <select name=xIdPlu>
                            <lsp:listaPluTipo idTipoTag="<%=xIdTipoMaterial%>">
                                <option value="<%=idPluVar%>">
                                    <%=referenciaVar%>
                                </lsp:listaPluTipo>
                        </select>
                    </td>
                    <td width="70%" align="right" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="10%" align="left" class="letraDetalle">REFERENCIA
                        <br>CLIENTE</td>
                    <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="10%" align="left" class="letraDetalle">
                        <input name="xReferenciaClente" size="50" maxlength="50">
                    </td>
                    <td width="70%" align="right" class="letraDetalle">&nbsp;</td>
                </tr>
                <lsb:listaEscalaBase idOperacionTag="<%=xIdOperacion%>">
                    <tr>
                        <td width="10%" align="left" class="letraDetalle"><%=nombreEscalaVar%></td>
                        <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="10%" align="left" class="letraDetalle">

                            <% if (idTipoEscalaVar.compareTo("2") == 0) {%>
                            <select name=<%=idEscalaIndexVar%>>
                                <lsu:listaEscala idEscalaTag="<%=idEscalaVar%>"
                                itemTag="<%=xItem%>">
                                    <option value="<%=itemVar%>">
                                        <%=nombreItemVar%>
                                    </option>
                                </lsu:listaEscala>
                            </select>
                            <% }%>

                                 <% if (idTipoEscalaVar.compareTo("8") == 0) {%>
                              <select name=<%=idEscalaIndexVar%>>
                                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaMP%>"
                                                idCategoriaTag="<%=xIdCategoriaOtraRef%>"
                                                idPluTag="<%=idplu%>">
                                                    <option value="<%=idPluVar%>">
                                                        <%=nombrePluVar%>
                                                    </option>
                                                </lsa:listaPluCategoriaOpcion>
                                            </select>
                                            <input hidden name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=0%>">
                            <% }%>
                                    <% if (idTipoEscalaVar.compareTo("10") == 0) {%>
                              <select name=<%=idEscalaIndexVar%>>
                                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaMP%>"
                                                idCategoriaTag="<%=xIdCategoriaOtraRef1%>"
                                                idPluTag="<%=idplu%>">
                                                    <option value="<%=idPluVar%>">
                                                        <%=nombrePluVar%>
                                                    </option>
                                                </lsa:listaPluCategoriaOpcion>
                                            </select>
                                            <input hidden name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=0%>">
                            <% }%>
                            
                            <% if (idTipoEscalaVar.compareTo("5") == 0) {%>
                            <select name=<%=idEscalaIndexVar%>>
                                <lsu:listaEscala idEscalaTag="<%=idEscalaVar%>"
                                itemTag="<%=xItem%>">
                                    <option value="<%=itemVar%>">
                                        <%=nombreItemVar%>
                                    </option>
                                </lsu:listaEscala>
                            </select>
                            PANTONE <input name="<%=idTextoEscalaIndexVar%>" size="10" maxlength="10" value="">
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("3") == 0) {%>
                            <textarea name="<%=idEscalaIndexVar%>" rows="4" cols="50"></textarea>
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("1") == 0) {%>
                            <input name="<%=idEscalaIndexVar%>" size="10" maxlength="10" value="0">
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("4") == 0) {%>
                            <input name="<%=idEscalaIndexVar%>" size="10" maxlength="10" value="">
                            REF <input name="<%=idTextoEscalaIndexVar%>" size="10" maxlength="10" value="0">
                            <% }%>

                        </td>
                        <td width="70%" align="right" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lsb:listaEscalaBase>
                    <!--tr>
                         <td width="10%" align="left" class="letraDetalle">ARTE</td>
                         <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                         <td width="10%"  align="left" class="letraDetalle">
                            <input type="file"  name="archivo"  ><input type="button" onclick="subir()" value="Subir Archivo" />
                        </td>
                        <td width="70%" align="right" class="letraDetalle">&nbsp;</td>
                        
                        <input type="hidden" name="nombre" value="" />
                    </tr-->
            </table>            
            <table border="0" width="90%" id="tablaTitulo">
                <tr>                    
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="submit" value="Confirmar Referencia" name="accionContenedor">
                    </td>
                    <td width="50%" align="left" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                </tr>
            </table>
                    <!--iframe  name="null" style="display: none;" /-->
        </form>
    </body>
</html>
