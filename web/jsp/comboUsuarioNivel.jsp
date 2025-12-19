<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaUsuarioNivel" prefix="lst" %>

<html>
<select name="xIdNivel">
  <lst:listaUsuarioNivel>
     <option value="<%=idNivelVar%>">
                    <%=nombreNivelVar%>
     </option>
  </lst:listaUsuarioNivel>
</select>
</html>
