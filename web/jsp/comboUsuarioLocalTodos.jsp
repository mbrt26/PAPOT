<html>

    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioLocalTodos" prefix="lst" %>

    <%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>
    <%@ page import="com.solucionesweb.losbeans.fachada.FachadaLocalBean" %>
    <%@ page import="com.solucionesweb.losbeans.negocio.LocalBean" %>

    <%
        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        FachadaLocalBean fachadaLocalBean = new FachadaLocalBean();

        //
        LocalBean localBean = new LocalBean();

        //
        localBean.setIdLocal(usuarioBean.getIdLocalUsuario());

        fachadaLocalBean = localBean.listaUnLocal();

        //
        String xEstadoActivo    = "1";
        String xIdNivelVendedor = "5";
        String xIdUsuarioActivo = usuarioBean.getIdUsuarioStr();
    %>
       <select name=xIdVendedor>
           <lst:listaUsuarioLocalTodos idUsuarioTag="<%=xIdUsuarioActivo%>"
                                         idNivelTag="<%=xIdNivelVendedor%>"
                                           estadoTag="<%=xEstadoActivo%>"
                                   idLocalUsuarioTag="<%=fachadaLocalBean.getIdLocalStr()%>"
                                        estadoTag="<%=xEstadoActivo%>">
               <option value="<%=idUsuarioVar%>">
                   <%=nombreUsuarioVar%>
               </option>
           </lst:listaUsuarioLocalTodos>
       </select>
</html>