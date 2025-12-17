<%@ taglib uri="/WEB-INF/tlds/listaPluTipo" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenDetalleBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />
<select name="xIdPlu">
    <lst:listaPluTipo idTipoTag="<%=fachadaDctoOrdenDetalleBean.getIdTipoStr()%>">
        <option value="<%=idPluVar%>">
            <%=nombreCategoriaVar%> <%=nombrePluVar%>
        </option>
    </lst:listaPluTipo>
</select>
