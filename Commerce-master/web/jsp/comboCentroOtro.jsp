<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaCentroOtro" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<html>
    <lst:listaCentroOtro idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
        <%=nombreLocalVar%>
     </lst:listaCentroOtro>
</html>
