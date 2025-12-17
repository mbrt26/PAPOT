<%@ taglib uri="/WEB-INF/tlds/listaAllTercero" prefix="lst" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<select name="xIdTercero" width = "250" style = "width: 250px">
    <lst:listaAllTercero>
     <option value="<%=idTerceroVar%>">
                    <%=nombreTerceroVar%>
     </option>
  </lst:listaAllTercero>
</select>
