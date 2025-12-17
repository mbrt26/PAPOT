<%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>

<select name=xIdListaPrecio>
  <lst:listaPrecio idLocalTag = "<%=fachadaTerceroBean.getIdLocalStr()%>"
             idListaPrecioTag = "<%=fachadaTerceroBean.getIdListaPrecioStr()%>"
     <option value="<%=idListaPrecioVar%>">
                    <%=nombreListaVar%>
     </option>
  </lst:listaPrecio>
</select>

