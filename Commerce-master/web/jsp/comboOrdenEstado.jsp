<%@ taglib uri="/WEB-INF/tlds/listaOrdenEstado" prefix="lst" %>

<select name=xIdEstado>
  <lst:listaOrdenEstado>
     <option value="<%=idEstadoVar%>">
                    <%=nombreEstadoVar%>
     </option>
  </lst:listaOrdenEstado>
</select>