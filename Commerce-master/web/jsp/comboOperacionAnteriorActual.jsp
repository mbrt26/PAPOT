<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaOperacionAnteriorActual" prefix="lst" %>

<jsp:useBean id="fachadaPluFicha"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
<html>
    <lst:listaOperacionAnteriorActual idOperacionTag="<%=fachadaPluFicha.getIdOperacionStr()%>"
                                      idFichaTag="<%=fachadaPluFicha.getIdFichaStr()%>">
        <%=nombreOperacionAnteriorVar%>
     </lst:listaOperacionAnteriorActual>
</html>