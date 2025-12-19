<%@ taglib uri="/WEB-INF/tlds/listaUnAllCategoria" prefix="lst" %>

<select name='xIdLineaCategoria'>
   
        <lst:listaUnAllCategoria>
            <option value="<%=idLineaCategoriaVar%>">
            <%=nombreCategoriaVar%>
        </option>
        </lst:listaUnAllCategoria>
</select>