<%@ taglib uri="/WEB-INF/tlds/listaLinea" prefix="lst" %>

<select name=idLinea>
  <lst:listaLinea>
     <option value="<%=idLineaVar%>">
                    <%=nombreLineaVar%> 
     </option>
  </lst:listaLinea>
</select>