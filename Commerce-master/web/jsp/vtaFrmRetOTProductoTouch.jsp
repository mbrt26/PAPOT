
<html>

    <%@ taglib uri="/WEB-INF/tlds/listaOperacionTouch" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioOperacion" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaJobCausa" prefix="lsu" %>

    <jsp:useBean id="fachadaDctoOrdenProgreso"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso" />

    <% String xIdTipoCausaRetal = "2";%>

        <head>
        <title>Registra Retal</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link type="text/css" href="./styles/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="./js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-timepicker-addon.js"></script>
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmRetOTProductoTouch.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenProgreso.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenProgreso.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenProgreso.getIdOperacionStr()%>">
            <input type="hidden" name="xFechaFin" value="<%=fachadaDctoOrdenProgreso.getFechaFin()%>">
            <table border="0" cellpadding="0" cellspacing="0" width="800" height="400" bgcolor="#000000">
                <!--table border="0" cellpadding="0" cellspacing="0" width="800" height="400"-->
                <!-- MSTableType="layout" -->
                <tr>
                    <td valign="top" colspan="3" height="80" align="center">
                        <!-- MSCellType="ContentHead" -->
                    </td>
                </tr>
                <tr>
                    <td valign="top" width="100">
                        <div style="height:400px; overflow:auto">
                            <table cellpadding="15" cellspacing="3">
                                <lst:listaUsuarioOperacion idLocalTag="<%=fachadaDctoOrdenProgreso.getIdLocalStr()%>"
                                idOperacionTag="<%=fachadaDctoOrdenProgreso.getIdOperacionStr()%>">
                                    <tr>
                                        <td align="left" bgcolor="#FFFFFF">
                                            <input type="radio" name="xIdOperario" value="<%=idUsuarioVar%>" style="width:30px;height:30px;border-width:thin;border-style:solid;border-color:green;color:#000000;"/>
                                            <br><%=nombreUsuarioVar%>       <tr>
                    <td valign="top" width="100">
              
                                        </td>
                                    </tr>
                                </lst:listaUsuarioOperacion>
                            </table>
                        </div>
                    </td>
                    <td valign="top" width="100" align="center">
                        <table>
                            <tr>
                                <td width="20%" align="center" bgcolor="#FFFFFF"># O.T.
                                <br><input name="xNumeroOrden" size="10" maxlength="10" value="0">
                                </td>
                                <td width="40%" align="center" bgcolor="#FFFFFF">PESO(KG)
                                    <br>RETAL
                                    <br><input name="xPesoPerdido" size="10" maxlength="10" value="0">
                                    <table>
                                        <tr>
                                            <td width="100%" align="center"  bgcolor="#FFFFFF">
                                                 CAUSA RETAL
                                                <select name="xIdCausa">
                                                    <lsu:listaJobCausa idTipoCausaTag = "<%=xIdTipoCausaRetal%>">
                                                        <option value ="<%=idCausaVar%>">
                                                            <%=nombreCausaVar%>
                                                        </option>
                                                    </lsu:listaJobCausa>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="20%" align="center" bgcolor="#000000">&nbsp;</td>
                                <td width="20%" align="center" bgcolor="#000000">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" colspan="3" height="80" align="center">
                        <!-- MSCellType="ContentFoot" -->
                        <table border="0" width="90%" cellpadding="15" cellspacing="3">
                            <tr>
                                <td width="33%" align="center">&nbsp;</td>
                                <td width="34%" align="center">&nbsp;</td>
                                <td width="33%" align="center">
                                    <input type="submit" style="width:120px; height:50px" name="accionContenedor" value="Ingresar" />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>

</html>