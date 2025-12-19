<html>

    <%@ taglib uri="/WEB-INF/tlds/seleccionaTerceroxIdTipoTercero" prefix="lista" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <head>
        <title>Catalogo Cliente</title>
       
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstSelCatalogoCliente.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%"  colspan="2">&nbsp;</td>
                    <td width="34%" align="center"  colspan="2">LISTA CLIENTE</td>
                    <td width="33%"  colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%"  colspan="2">&nbsp;</td>
                    <td width="34%" align="center"  colspan="2">&nbsp;</td>
                    <td width="33%"  colspan="4">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <tr>
                    <td width="25%" align="left" >NOMBRE</td>
                    <td width="5%" align="right" >NIT/CC</td>
                    <td width="20%" align="left" >DIRECCION</td>
                    <td width="10%" align="left" >CIUDAD</td>
                    <td width="10%" align="left" >TEL.FIJO</td>
                    <td width="5%" align="left" >PLAZO</td>
                    <td width="5%" align="left" >CUPO</td>
                    <td width="10%" align="left" >REGIMEN</td>
                </tr>
                <lista:seleccionaTerceroxIdTipoTercero nombreTerceroTag="<%=fachadaTerceroBean.getNombreTercero()%>"
                idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">

                    <tr>
                        <td width="25%" align="left" ><%=nombreTerceroVar%></td>
                        <td width="5%" align="right" ><%=idTerceroFormatVar%></td>
                        <td width="20%" align="left" ><%=direccionTerceroVar%></td>
                        <td width="10%" align="left" ><%=nombreCiudadVar%></td>
                        <td width="10%" align="left" ><%=telefonoFijoVar%></td>
                        <td width="5%" align="left" ><%=idFormaPagoVar%></td>
                        <td width="5%" align="left" ><%=cupoCreditoVar%></td>
                        <td width="10%" align="left" ><%=nombreRegimenVar%></td>
                    </tr>

                </lista:seleccionaTerceroxIdTipoTercero>


              
            </table>

        </form>
    </body>
</html>