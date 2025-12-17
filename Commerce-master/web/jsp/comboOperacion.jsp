<%@ taglib uri="/WEB-INF/tlds/listaOperacion" prefix="lst" %>

<select name='xIdOperacion'>
        <lst:listaOperacion>
            <option value="<%=idOperacionVar%>">
            <%=nombreOperacionVar%>
        </option>
        </lst:listaOperacion>
</select>