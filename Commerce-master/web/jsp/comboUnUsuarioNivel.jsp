<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/listaUnUsuarioNivel" prefix="lst" %>

<html>
<select name="xIdNivel">
  <lst:listaUnUsuarioNivel idNivelTag  = "<%=fachadaTerceroBean.getIdLocalStr()%>">
     <option value="<%=idNivelVar%>">
                    <%=nombreNivelVar%>
     </option>
  </lst:listaUnUsuarioNivel>
</select>
</html>
