<%@ taglib uri="/WEB-INF/tlds/listaTerceroOperacion" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
<select name="xIdTercero">
    <lst:listaTerceroOperacion idTipoTerceroTag="<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
     <option value="<%=idTerceroVar%>">
                    <%=nombreTerceroVar%>
     </option>
  </lst:listaTerceroOperacion>
</select>
