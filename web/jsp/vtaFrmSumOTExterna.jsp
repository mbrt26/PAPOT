<html>
    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTExternaAllSumnistro.tld" prefix="lsu" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <head>
        <title>Detalle O.T. Externa Suministro</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSumOTExterna.jsp">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DETALLE O.T. EXTERNA SUMINISTRO</td>
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

            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="5%" align="left" class="letraTitulo">NOMBRE SUMINISTRO</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="5%" align="right" class="letraTitulo">KG</td>
                     <td width="20%" align="left" class="letraTitulo">TERCERO</td>
                 </tr>
                <lsu:listaOTExternaAllSumnistro idLocalTag  = "<%=fachadaPluFicha.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaPluFicha.getIdTipoOrdenStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                         <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                         <td width="20%" align="left" class="letraResaltadaGrande"><%=nombreTerceroOperacionVar%></td>
                     </tr>
               </lsu:listaOTExternaAllSumnistro>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>

</html>