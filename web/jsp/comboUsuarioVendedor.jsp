<html>

    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioVendedor" prefix="lsp" %>

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
        String xEstadoActivo = "1";
        String xIdNivelVendedor = "10,2,52";
        String xIdUsuarioActivo = usuarioBean.getIdUsuarioStr();
    %>
    <select name=xIdVendedor>
            <option value="0">
                --SELECCIONA--
            </option>
            <option value="0">
                NINGUNO
            </option>
        <lsp:listaUsuarioLocal idUsuarioTag="<%=xIdUsuarioActivo%>"
        idNivelTag="<%=xIdNivelVendedor%>"
        idLocalUsuarioTag="<%=fachadaLocalBean.getIdLocalStr()%>"
        estadoTag="<%=xEstadoActivo%>">                                                                                    
            <option value="<%=idUsuarioVar%>">
                <%=nombreUsuarioVar%>
            </option>
        </lsp:listaUsuarioLocal>
    </select>
</html>