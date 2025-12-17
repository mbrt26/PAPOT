<%@page import="com.solucionesweb.losbeans.fachada.FachadaPluFicha"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Vector"%>
<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">  

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />
    <head>
        <title>ADICIONAR PRODUCTOS</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <script src="./js/jquery-1.5.1.min.js"></script>
        <script src="./js/jquery.fixedtableheader.min.js"></script>
        <%  Vector v = (Vector) request.getAttribute("vector");
            Iterator iteratorObjetos;
            Iterator xiteratorObjetos;
            xiteratorObjetos = v.iterator();
            iteratorObjetos = v.iterator();
        %>
        <script type="text/javascript">
                $(document).ready(function() { 
                   $('.tbl').fixedtableheader({ 
                    highlightclass: 'rowhlite' 
                   }); 
                }); 
        </script>
        <script>
            function validar(_ficha) {
                var cliente = <%=fachadaTerceroBean.getIdCliente()%>;
                $.post('Comun', {opcion: 'activar', xficha: _ficha, xcliente: cliente}, function(e) {
                    if (e == 'Error') {
                        alert('Error de activacion');
                    } else {
                        document.getElementById(_ficha).innerHTML = e;
                    }
                });
            }

            function inactivar(_xficha) {
                var cliente = <%=fachadaTerceroBean.getIdCliente()%>;
                $.post('Comun', {opcion: 'inactivar', xficha: _xficha, xcliente: cliente}, function(e) {
                    if (e == 'Error') {
                        alert('Error!!!');
                    } else {
                        document.getElementById(_xficha).innerHTML = e;
                    }
                });
            }
        </script>

    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaAdmInaRefClient.jsp">

            <table border="0" width="90%" id="tablaTitulo" class="tbl">
                <tr>                
                    <th width="34%"  colspan="3"align="center" class="letraTitulo">ACTIVA Ó INACTIVA REFERENCIAS CLIENTES</th>                
                </tr>
                <tr>
                    <th width="33%" class="letraResaltadaTitulo"><jsp:include page="./comboLocal.jsp"/></th>
                    <th width="34%" align="center" class="letraTitulo"> <%=fachadaTerceroBean.getNombreTercero()%></th>
                    <th width="33%" class="letraTitulo"><jsp:include page="./comboFechaHoy.jsp"/></th>
                </tr>                              
            </table>


            <table border="0" width="90%" id="tablaTitulo" class="tbl">                
                <tr>
                    <th width="5%"  align="right" class="letraTitulo">FICHA</th>
                    <th width="10%" align="left" class="letraTitulo">REFERENCIA</th>
                    <th width="30%"  align="left" class="letraTitulo">REF.CLIENTE</th>                    
                    <th width="20%" align="left" class="letraTitulo">MATERIAL</th>
                    <th width="10%"  align="left" class="letraTitulo">ESTADO</th>
                    <th width="5%"  align="left" class="letraTitulo">&nbsp;</th>
                </tr>
                <%
                    FachadaPluFicha column;
                    column = (FachadaPluFicha) iteratorObjetos.next();
                %>


                <%
                    FachadaPluFicha fachadaBean;
                    String estado = "null";
                    while (xiteratorObjetos.hasNext()) {
                        fachadaBean = (FachadaPluFicha) xiteratorObjetos.next();

                        if (fachadaBean.getEstado() == 1) {
                            estado = "ACTIVO";
                        } else {
                            estado = "INACTIVO";
                        }
                %> 
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=fachadaBean.getIdFichaSf0()%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=fachadaBean.getReferencia()%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=fachadaBean.getReferenciaCliente()%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=fachadaBean.getNombrePlu()%></td>
                    <td width="20%" align="left" id="<%=fachadaBean.getIdFichaSf0()%>" class="letraDetalle"><%=estado%></td>
                    <td width="5%" align="left" class="letraDetalle"> <a href="#" onclick="validar(<%=fachadaBean.getIdFichaSf0()%>)"  ><img src="./imagenes/activo.png" width="16" height="16"></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="inactivar(<%=fachadaBean.getIdFichaSf0()%>)"><img src="./imagenes/inactivo.png" width="16" height="16"  ></a> </td>

                </tr>
                <%}%>

            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" colspan="5" align="center" class="letraTitulo">
                        <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>