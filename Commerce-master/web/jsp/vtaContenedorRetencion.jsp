<html>
    <%@taglib uri="/WEB-INF/tlds/listaRetencion" prefix="lst"%>

    <head>
        <title>Retencion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorRetencion.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">Seleccione Retención</td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td colspan="3" width="33%" class="letraTitulo" colspan="2">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <th width="10%" align="center" class="letraTitulo">IDORDEN</th>
                    <th width="10%" align="center" class="letraTitulo">IDSUBCUENTA</th>
                    <th width="10%" align="center" class="letraTitulo">IDPERSONA</th>
                    <th width="20%" align="left" class="letraTitulo">NOMBRE<br>CONCEPTO</th>
                    <th width="10%" align="right" class="letraTitulo">PORCENTAJE</th>
                    <th width="10%" align="right" class="letraTitulo">VR.BASE</th>                    
                    <th width="10%" align="center" class="letraTitulo">ALCANCE</th>   
                </tr>


                <lst:listaRetencion>
                    <tr>
                        <td width="10%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorRetencion.jsp&accionContenedor=Traer&xIdConcepto=<%=idConceptoVar%>&xIdSubcuenta=<%=idSubcuentaVar%>&xIdPersona=<%=idPersonaVar%>"><%=idConceptoVar%></a>
                        </td>
                        <td width="10%" align="center" class="letraDetalle"><%=idSubcuentaVar%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=idPersonaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombreConceptoVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=porcentajeRetencionVar%></td>                        
                        <td width="10%" align="right" class="letraDetalle"><%=vrBaseRetencionVar%></td>                                                
                        <td width="10%" align="center" class="letraDetalle"><%=idTipoOrdenAlcanceVar%></td>                                                                        
                    </tr>

                </lst:listaRetencion>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>