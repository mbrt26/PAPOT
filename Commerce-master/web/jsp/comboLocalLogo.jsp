<html>

<%@ page import="com.solucionesweb.losbeans.fachada.FachadaLocalBean" %>
<%@ page import="com.solucionesweb.losbeans.negocio.LocalBean" %>

    <%

        int xEstadoLogo = 1;
       //
       FachadaLocalBean fachadaLocalBean = new FachadaLocalBean();

       //
       LocalBean localBean               = new LocalBean();

       //
       localBean.setEstado(xEstadoLogo);

       fachadaLocalBean                  = localBean.listaLocalLogo();



    %>
    <%=fachadaLocalBean.getNombreLocal()%>
</html>

