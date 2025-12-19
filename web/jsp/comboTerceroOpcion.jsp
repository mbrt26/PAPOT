<%@ taglib uri="/WEB-INF/tlds/listaTerceroOpcion" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<select name="xIdTercero">
    <lst:listaTerceroOpcion idTipoTerceroTag="<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
     <option value="<%=idTerceroVar%>">
                    <%=nombreTerceroVar%>
     </option>
  </lst:listaTerceroOpcion>
</select>
