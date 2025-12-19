<%@ taglib uri="/WEB-INF/tlds/listaAllMarca" prefix="lsm" %>

<select multiple="multiple" size="10" name='xIdMarca'>

        <lsm:listaAllMarca>
            <option value="<%=idMarcaVar%>">
            <%=nombreMarcaVar%>
        </option>
        </lsm:listaAllMarca>
</select>
