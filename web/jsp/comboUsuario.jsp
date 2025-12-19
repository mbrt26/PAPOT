<%@ taglib uri="/WEB-INF/tlds/listaUsuarioTodos" prefix="combo" %>

<select name=idUsuario>
  <combo:listaUsuarioTodos>
     <option value="<%=idUsuarioVar%>">
                    <%=nombreUsuarioVar%>
     </option>
  </combo:listaUsuarioTodos>
</select>