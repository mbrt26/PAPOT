<%@page import="co.linxsi.modelo.facturacionelectronica.FEPdfDTO"%>
<%@page import="co.linxsi.modelo.facturacionelectronica.FacturacionElectronicaDAO"%>
<html>
    <%@ taglib uri="/WEB-INF/tlds/listaAllClienteOT.tld" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionOpcion.tld" prefix="lsu" %>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <% String xIdOperacionCadena = "8,999";%>

    <head>
        <title>Remision/Factura</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body onload="ImprimirPdf()">
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorRemisionFactura.jsp">
            <input type="hidden" name="xPDF" id="xPDF" value="<%=fachadaDctoOrdenBean.getPdfFE()%>">
            <input type="hidden" name="xorden" id="xorden" value="<%=fachadaDctoOrdenBean.getIdOrden()%>">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REMISION / FACTURA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaTitulo">
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
                    <td width="33%" align="center" class="letraTitulo">OPERACION</td>
                    <td width="33%" align="center" class="letraTitulo">NOMBRE CLIENTE (+)</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraResaltadaTitulo">
                        <select name='xIdOperacion'>
                            <lsu:listaOperacionOpcion idOperacionCadenaTag="<%=xIdOperacionCadena%>">
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsu:listaOperacionOpcion>
                        </select>
                    </td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">
                        <select name='xIdCliente'>
                            <lsv:listaAllClienteOT idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                            idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                            estadoTag = "<%=fachadaDctoOrdenBean.getEstadoStr()%>"
                            idTipoTerceroTag = "<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
                                <option value="<%=idClienteVar%>">
                                    <%=nombreTerceroVar%>
                                </option>
                            </lsv:listaAllClienteOT>
                        </select>
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <%
                        if (fachadaDctoOrdenBean.getPdfFE() == 2) {
                            FacturacionElectronicaDAO fedao = new FacturacionElectronicaDAO();
                            FEPdfDTO fepdto = fedao.ListaPdf(fachadaDctoOrdenBean.getIdOrden());

                    %>
                <a href="<%=fepdto.getUrl()%>"  id="pdflink"></a>
                <%}%>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo" >
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Remisionar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Facturar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>                
                </tr>
            </table>
        </form>
        <script>
            function ImprimirPdf() {
                document.getElementById('pdflink').click();
            }

        </script>
    </body>
</html>