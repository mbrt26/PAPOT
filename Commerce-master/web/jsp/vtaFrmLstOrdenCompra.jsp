<html>
    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoEmpresa" prefix="lst" %>
    <head>
        <title>Resurtido Crear Orden Compra</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
<head>
    <title>Reporte Ventas Iva</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">

    <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
    <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
    <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
    <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
</head>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstOrdenCompra.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xDiasHistoria" value="<%=fachadaDctoOrdenBean.getDiasHistoriaStr()%>">
            <input type="hidden" name="xDiasInventario" value="<%=fachadaDctoOrdenBean.getDiasInventarioStr()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaTerceroBean.getIdCliente()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO CREAR ORDEN COMPRA</td>
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
                    <td width="33%" align="center" class="letraTitulo">NIT/CC</td>
                    <td width="34%" align="center" class="letraTitulo">PROVEEDOR</td>
                    <td width="33%" align="center" class="letraTitulo">DIAS PLAZO</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle"><%=fachadaTerceroBean.getIdCliente()%></td>
                    <td width="34%" align="center" class="letraDetalle"><%=fachadaTerceroBean.getNombreTercero()%></td>
                    <td width="33%" align="center" class="letraDetalle"><%=fachadaTerceroBean.getIdFormaPago()%></td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="left" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="25" align="right" class="letraResaltadaTitulo">#ARTICULOS</td>
                    <td width="25%" align="right" class="letraResaltadaTitulo">VR.ORDEN COMPRA</td>
                </tr>
                <tr>
                    <td width="50%" align="left" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="25" align="right" class="letraResaltadaGrande"><%=fachadaDctoOrdenDetalleBean.getCantidadArticulos()%></td>
                    <td width="25%" align="right" class="letraResaltadaGrande"><%=fachadaDctoOrdenDetalleBean.getVrCostoNegociadoDf0()%></td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="30%" align="left" class="letraTitulo">NOMBREPLU</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.ULT</td>
                    <td width="5%" align="center" class="letraTitulo">CTO.NEG</td>
                    <td width="5%" align="center" class="letraTitulo">CAN.PED</td>
                    <td width="5%" align="center" class="letraTitulo">CAN.BON</td>
                    <td width="2%" align="right" class="letraTitulo">UD</td>
                    <td width="5%" align="right" class="letraTitulo">CP.UV</td>
                    <td width="5%" align="right" class="letraTitulo">CP.UD</td>
                    <td width="5%" align="right" class="letraTitulo">INV.ALL</td>
                    <td width="5%" align="right" class="letraTitulo">INV.BOD</td>
                    <td width="5%" align="right" class="letraTitulo">INV.MAX</td>
                    <!--td width="5%" align="right" class="letraTitulo">PVD</td-->
                </tr>
                <lst:listaResurtidoEmpresa idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                diasInventarioTag="<%=fachadaDctoOrdenBean.getDiasInventarioStr()%>">
                    <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
                    <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
                    <input type="hidden" value="<%=idPluVar%>" name="xIdPlu">
                    <tr>
                        <td width="5%" align="right" class="<%=letraEstiloVar%>"><%=idPluVar%></td>
                        <td width="30%" align="left" class="<%=letraEstiloVar%>"><%=nombrePluVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=vrCostoVar%></td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xVrCostoNegociado" value="<%=vrCostoNegociadoVar%>" id="xVrCostoNegociado" size="10" maxlength="10">
                        </td>                        
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidadPedido" value="<%=cantidadPedidaVar%>" size="6" maxlength="6">
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" value="<%=cantidadBonificadaVar%>" name="xCantidadBonificada" size="6" maxlength="6">
                        </td>
                        <td width="2%" align="right" class="letraDetalle"><%=factorDespachoVar%></td>
                        <td width="5%" align="right" class="<%=letraEstiloVar%>"><%=cantidadPedidoUVVar%></td>
                        <td width="5%" align="right" class="<%=letraEstiloVar%>"><%=cantidadPedidoUDVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=existenciaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=existenciaBodegaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadInventarioMaximoVar%></td>
                        <!--td width="5%" align="right" class="letraDetalle"><%=cantidadPvdVar%></td-->
                    </tr>
                </lst:listaResurtidoEmpresa>

            </table>
            <script type="text/javascript">
                document.getElementById('xVrCostoNegociado').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="center" class="letraResaltadaGrande">OBSERVACIONES (max 100 caracteres)</td>
                    <td width="50%" align="center" class="letraResaltadaGrande">FECHA ENTREGA</td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="letraResaltadaGrande">
                        <textarea name="xObservacion" rows="2" cols="50"><%=fachadaDctoOrdenBean.getObservacion()%></textarea>
                    </td>
                    <td width="50%" align="center" class="letraResaltadaGrande">
                    <input type="text" name="xFechaEntrega" id="xFechaEntrega" readonly="readonly"
                            value="<%=fachadaDctoOrdenBean.getFechaEntregaCorta()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Guardar" name="accionContenedor">
                    </td>
                    <td width="35%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
    <script type="text/javascript">


  Calendar.setup(
  {
    inputField: "xFechaEntrega",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorinicial",
    date: new Date()
 }
);

</script>