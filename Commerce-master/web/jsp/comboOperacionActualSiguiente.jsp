<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaOperacionActualSiguiente" prefix="lst" %>

<jsp:useBean id="fachadaPluFicha"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
<html>
    <lst:listaOperacionActualSiguiente idOperacionTag="<%=fachadaPluFicha.getIdOperacionStr()%>"
                                      idFichaTag="<%=fachadaPluFicha.getIdFichaStr()%>">
        <%=nombreOperacionSiguienteVar%><br>
     </lst:listaOperacionActualSiguiente>
</html>