<%@ taglib uri="/WEB-INF/tlds/listaTercero" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<select name="xIdTercero">
    <lst:listaTercero idTipoTerceroTag="<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
     <option value="<%=idTerceroVar%>">
                    <%=nombreTerceroVar%>
     </option>
  </lst:listaTercero>
</select>
