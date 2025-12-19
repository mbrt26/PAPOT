<html>

    <jsp:useBean id="fachadaContableRetencionBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaContableRetencionBean" />    

    <head>
        <title>Retencion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmTraRetencion.jsp">
            <input type="hidden" value="<%=fachadaContableRetencionBean.getIdConceptoStr()%>" name="xIdConcepto">    
            <input type="hidden" value="<%=fachadaContableRetencionBean.getIdSubcuenta()%>" name="xIdSubcuenta">                                               
            <input type="hidden" value="<%=fachadaContableRetencionBean.getIdPersona()%>" name="xIdPersona">                                    

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">Modifica Retención</td>
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
                    <th width="10%" align="right" class="letraTitulo">VALOR<br>BASE</th>                    
                    <th width="10%" align="center" class="letraTitulo">ALCANCE</th>   
                </tr>
                <tr>
                    <td width="10%" align="center" class="letraDetalle">
                        <%=fachadaContableRetencionBean.getIdConceptoStr()%>
                    </td>
                    <td width="10%" align="center" class="letraDetalle">
                        <%=fachadaContableRetencionBean.getIdSubcuenta()%>
                    </td>
                    <td width="10%" align="center" class="letraDetalle">
                        <%=fachadaContableRetencionBean.getIdPersona()%>
                    </td>
                    <td width="20%" align="left" class="letraDetalle">                      
                        <input type="text" value="<%=fachadaContableRetencionBean.getNombreConcepto()%>" name="xNombreConcepto" size="50" maxlength="50" >                                                
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaContableRetencionBean.getPorcentajeRetencionStr()%>" name="xPorcentajeRetencion" size="4" maxlength="4" >                                                
                    </td>                        
                    <td width="10%" align="right" class="letraDetalle">                        
                        <input type="text" value="<%=fachadaContableRetencionBean.getVrBaseRetencionStr()%>" name="xVrBaseRetencion" size="8" maxlength="8" > 
                    </td>                                                                    
                    <td width="10%" align="center" class="letraDetalle">
                        <input type="text" value="<%=fachadaContableRetencionBean.getIdTipoOrdenAlcanceStr()%>" name="xIdTipoOrdenAlcance" size="4" maxlength="4" >                                                
                    </td>                                                                        
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>