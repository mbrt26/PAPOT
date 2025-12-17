
<html>

    <%@ taglib uri="/WEB-INF/tlds/listaOperacionTouch" prefix="lsv" %>
    <%//@ taglib uri="/WEB-INF/tlds/listaOTProgramaTouch" prefix="lst" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <% boolean xTituloOT = true;%>

    <head>
        <meta http-equiv="Content-Language" content="es">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Pagina nueva 1</title>
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorOTProductoTouch.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaPluFicha.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaPluFicha.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaPluFicha.getIdOperacionStr()%>">

            <table border="0" cellpadding="0" cellspacing="0" width="800" height="400" bgcolor="#000000">
                <!--table b order="0" cellpadding="0" cellspacing="0" width="800" height="400"-->
                <!-- MSTableType="layout" -->
                <tr>
                    <td valign="top" colspan="3" height="80">
                        <!-- MSCellType="ContentHead" -->
                        caja superior</td>
                </tr>
                <tr>
                    <td valign="top" width="100">
                        <table cellpadding="15" cellspacing="3">
                            <lsv:listaOperacionTouch>
                                <tr><td bgcolor="<%=idColorVar%>" align="center">
                                        <!--button type="submit" style= "cursor:pointer" name = "botonOperacion" value="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOTProductoTouch.jsp&accionContenedor=inicia&xIdLocal=<%=fachadaPluFicha.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaPluFicha.getIdTipoOrdenStr()%>&xIdOperacion=<%=idOperacionVar%>"><%=nombreOperacionVar%></button-->
                                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOTProductoTouch.jsp&accionContenedor=inicia&xIdLocal=<%=fachadaPluFicha.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaPluFicha.getIdTipoOrdenStr()%>&xIdOperacion=<%=idOperacionVar%>"onclick="colocarCursor()"><%=nombreOperacionVar%></a>
                                    </td>
                                </tr>
                            </lsv:listaOperacionTouch>
                        </table>
                    </td>
                    <td valign="top" width="700"">
                        <% if (fachadaPluFicha.getIdOperacion() > 0) {%>
                        <div style="height:400px; overflow:auto">
                            <table>
                                <%  if(fachadaPluFicha.getIdOperacion()==2)%> <tr><td><input id="campoOrden" onkeypress="return pulsar(event)" style="width:160px; height:25px"  placeholder="Ingrese OT Extrusion" maxlength="10" bgcolor="#FFFFFF" title="Ingrese OT Extrusion" ></<input type="text" value="Button"><input id="BotonBuscarOrden" type="button" style="width:90px; height:25px" name="accionContenedor" onclick="buscarOT('<%=fachadaPluFicha.getIdOperacion()%>')" value="Buscar OT" autofocus /></input></td></tr>
                                <%  if(fachadaPluFicha.getIdOperacion()==3)%> <tr><td><input  id="campoOrden" onkeypress="return pulsar(event)" style="width:160px; height:25px" placeholder="Ingrese OT Impresion" maxlength="10" bgcolor="#FFFFFF" title="Ingrese OT Impresion" ></<input type="text" value="Button"><input id="BotonBuscarOrden" type="button" style="width:90px; height:25px" name="accionContenedor" onclick="buscarOT('<%=fachadaPluFicha.getIdOperacion()%>')" value="Buscar OT" autofocus /></input></td></tr>
                                <%  if(fachadaPluFicha.getIdOperacion()==6)%> <tr><td><input id="campoOrden" onkeypress="return pulsar(event)"  style="width:160px; height:25px" placeholder="Ingrese OT Manual" maxlength="10" bgcolor="#FFFFFF" title="Ingrese OT Manual" ></<input type="text" value="Button"><input id="BotonBuscarOrden" type="button" style="width:90px; height:25px" name="accionContenedor" onclick="buscarOT('<%=fachadaPluFicha.getIdOperacion()%>')" value="Buscar OT" autofocus /></input></td></tr>
                                <%  if(fachadaPluFicha.getIdOperacion()==4)%> <tr><td><input id="campoOrden" onkeypress="return pulsar(event)"  style="width:160px; height:25px" placeholder="Ingrese OT Refilado" maxlength="10" bgcolor="#FFFFFF" title="Ingrese OT Refilado" ></<input type="text" value="Button"><input id="BotonBuscarOrden" type="button" style="width:90px; height:25px" name="accionContenedor" onclick="buscarOT('<%=fachadaPluFicha.getIdOperacion()%>')" value="Buscar OT" autofocus/></input></td></tr>
                                <%  if(fachadaPluFicha.getIdOperacion()==5)%> <tr><td><input id="campoOrden"onkeypress="return pulsar(event)"  style="width:160px; height:25px" placeholder="Ingrese OT Sellado" maxlength="10" bgcolor="#FFFFFF" title="Ingrese OT Sellado" ></<input type="text" value="Button"><input id="BotonBuscarOrden" type="button" style="width:90px; height:25px" name="accionContenedor" onclick="buscarOT('<%=fachadaPluFicha.getIdOperacion()%>')" value="Buscar OT" autofocus/></input></td></tr>


                            </table>
                            <table>
                                <div id="detalleOrdenTrabajo"></div>
                            </table>

                        </div>
                        <%}%>
                    </td>
                </tr>
                <tr>
                    <td valign="top" colspan="3" height="80" align="center">
                        <!-- MSCellType="ContentFoot" -->
                        <table border="0" width="90%" cellpadding="15" cellspacing="3">
                            <tr>
                                <td width="20%" align="center">&nbsp;</td>
                                <td width="20%" align="center">
                                    <input type="submit" style="width:96px; height:50px" name="accionContenedor" value="Producir" />
                                </td>
                                <td width="20%" align="center">
                                    <input type="submit" style="width:96px; height:50px" name="accionContenedor" value="Etiquetar" />
                                </td>
                                <td width="20%" align="center">
                                    <input type="submit" style="width:96px; height:50px" name="accionContenedor" value="Retal" />
                                </td>
                                <td width="20%" align="center">
                                    <input type="submit" style="width:96px; height:50px" name="accionContenedor" value="Suministrar" />
                                </td>
                                <td width="20%" align="center">
                                    <input type="submit" style="width:96px; height:50px" name="accionContenedor" value="Paros" />
                                </td>
                            </tr>
                        </table>

                    </td>
                </tr>
            </table>
        </form>
    </body>
    <script src="resourcs/js/touch/principal.js"></script>
    <script src="resourcs/js/jquery.min.js"></script>

    <style type="text/css">
        button {
            background: transparent;
            border: none !important;
        }
        #more {

            border:none;
            color:#FFF;
            font-family:Verdana, Geneva, sans-serif;
            cursor: pointer;
        }
    </style>
    <script>
                                    function pulsar(e) {
                                        // averiguamos el código de la tecla pulsada (keyCode para IE y which para Firefox)
                                        tecla = (document.all) ? e.keyCode : e.which;
                                        if (tecla == 13) {
                                            buscarOT('<%=fachadaPluFicha.getIdOperacion()%>');
                                        }
                                        // si la tecla no es 13 devuelve verdadero,  si es 13 devuelve false y la pulsación no se ejecuta
                                        return (tecla != 13);
                                    }
    </script>  
</html>