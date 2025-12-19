<%@ taglib uri="/WEB-INF/tlds/listaPluTipoOpcion" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenDetalleBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />
<select name="xIdPlu">
    <lst:listaPluTipoOpcion idTipoTag="<%=fachadaDctoOrdenDetalleBean.getIdTipoStr()%>">
        <option value="<%=idPluVar%>">
            <%=nombreCategoriaVar%> <%=nombrePluVar%>
        </option>
    </lst:listaPluTipoOpcion>
</select>
