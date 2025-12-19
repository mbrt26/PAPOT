<%@ taglib uri="/WEB-INF/tlds/listaCategoria" prefix="lst" %>

<select name=idLinea>
  <lst:listaCategoria>
     <option value="<%=idCategoriaVar%>">
                    <%=nombreCategoriaVar%>
     </option>
  </lst:listaCategoria>
</select>