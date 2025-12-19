<%@page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Vector"%>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
    <head>
        <title>Activar o Inactivar Referncias Clientes</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <script src="./js/jquery-1.5.1.min.js"></script> 

        <%  Vector v = (Vector) request.getAttribute("vector");
            Iterator iteratorObjetos;
            Iterator xiteratorObjetos;
            xiteratorObjetos = v.iterator();
            iteratorObjetos = v.iterator();
        %>             
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInaRefClient.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>                
                    <td width="34%"  colspan="3"align="center" class="letraTitulo">ACTIVA Ó INACTIVA REFERENCIAS CLIENTES</td>                
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

                    <td width="25%" colspan="3" align="center" class="letraTitulo">SELECCIONE CLIENTE</td>
                </tr>
                <tr>


                    <td width="25%" colspan="3" align="center" class="letraDetalle">
                        <select name="xIdTercero">
                            <option value="-1">--</option>

                            <%
                                FachadaTerceroBean column;
                                column = (FachadaTerceroBean) iteratorObjetos.next();
                            %>


                            <%
                                FachadaTerceroBean fachadaBean;
                                while (xiteratorObjetos.hasNext()) {
                                    fachadaBean = (FachadaTerceroBean) xiteratorObjetos.next();
                            %>                       
                            <option value="<%=fachadaBean.getIdCliente()  %>" ><%=fachadaBean.getNombreTercero()%></option>                    

                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="25%" colspan="3" align="center" class="letraTitulo">
                        <input type="submit" value="Consultar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>