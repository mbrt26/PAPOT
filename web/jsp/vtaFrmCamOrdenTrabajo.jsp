<%@page import="java.text.DecimalFormat"%>
<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <head>
        <title>Modificar/Retirar Referencia</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <script src="./js/jquery-1.5.1.min.js"></script>
        
        <script type="text/javascript">
            function subir() {
               var objHidden = document.formulario.nombre;
               objHidden.value = document.formulario.archivo.value;
               document.formulario.target = "null";
               document.formulario.action = "SubirArchivo";
               document.formulario.submit();
            }                        
        </script>
    </head>
    <body>

        <form method="POST" action="GralControladorServlet" name="formulario">           


            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmCamOrdenTrabajo.jsp">
            <input type="hidden" name="xIdFicha" value="<%=fachadaPluFicha.getIdFichaStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">Modificar/Retirar Referencia</td>
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
                        <input type="hidden" id="nit" name="xCliente" value="<%=idClienteVar%>">
                        
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">IDFICHA</td>
                    <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="30%" align="left" class="letraTitulo">REF.CLIENTE</td>
                </tr>

                <tr>
                    <td width="5%" align="right" class="letraDetalle" id="nombreReferencia">
                        <%=fachadaPluFicha.getIdFichaSf0()%>
                    </td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="10%" align="left" class="letraDetalle"><%=fachadaPluFicha.getReferencia()%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=fachadaPluFicha.getReferenciaCliente()%></td>
                </tr>

                <tr>
                    <input type="hidden" id="nit" name="xFicha" value="<%=fachadaPluFicha.getIdFichaSf0()%>">
                    
                    <td width="5%" align="right" class="letraDetalle" id="nombreReferencia">
                        <%=fachadaPluFicha.getIdFichaSf0()%>
                    </td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="10%" align="left" class="letraDetalle">
                        <%=fachadaPluFicha.getReferencia()%>
                    </td>
                    <td width="10%" align="left" class="letraDetalle">
                        <input type="text" value="<%=fachadaPluFicha.getReferenciaCliente()%>" name="xReferenciaClente" size="50" maxlength="50">
                    </td>
                </tr>
                <tr>
                    <td width="5%" align="right" class="letraDetalle" id="nombreReferencia">
                        <%=fachadaPluFicha.getIdFichaSf0()%>
                    </td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="10%" align="left" class="letraDetalle">
                        <%=fachadaPluFicha.getReferencia()%>
                    </td>
                    <td width="10%" align="left" class="letraDetalle">
                        <input type="file"  name="archivo" ><input type="button" onclick="subir()" value="Subir Archivo" />
                    </td>
                <input type="hidden" name="nombre" value="" />
                </tr>

            </table>
            <script type="text/javascript">
                document.getElementById('nombreReferencia').focus();
            </script>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Cambiar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Eliminar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
            <iframe  name="null" style="display: none;" />

        </form>
    </body>
</html>