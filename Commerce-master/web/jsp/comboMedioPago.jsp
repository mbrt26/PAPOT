<%@ taglib uri="/WEB-INF/tlds/listaMedio" prefix="lst" %>

<select name=xIdMedio>
  <lst:listaMedio>
     <option value="<%=idMedioVar%>">
                    <%=nombreMedioVar%>
     </option>
  </lst:listaMedio>
</select>
