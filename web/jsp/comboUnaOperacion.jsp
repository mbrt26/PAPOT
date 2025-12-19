<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaUnOperacion" prefix="lst" %>

<jsp:useBean id="fachadaPluFicha"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
<html>
    <lst:listaUnOperacion idOperacionTag="<%=fachadaPluFicha.getIdOperacionStr()%>">
        <%=nombreOperacionVar%>
     </lst:listaUnOperacion>
</html>
