<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaCentro" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<html>
    <lst:listaCentro idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
        <%=nombreLocalVar%>
     </lst:listaCentro>
</html>
