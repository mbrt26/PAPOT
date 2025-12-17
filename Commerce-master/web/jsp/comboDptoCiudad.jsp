<%@ taglib uri="/WEB-INF/tlds/listaDptoCiudad" prefix="lst" %>

<select name=idDptoCiudad>
  <lst:listaDptoCiudad>
     <option value="<%=idCiudadVar%>">
                    <%=nombreCiudadVar%> / <%=nombreDptoVar%>
     </option>
  </lst:listaDptoCiudad>
</select>