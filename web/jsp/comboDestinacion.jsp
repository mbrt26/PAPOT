<%@ taglib uri="/WEB-INF/tlds/destinacion" prefix="combobox" %>

<select name="idDestinacion">
  <combobox:destinacion>
     <option value="<%=idDestinacion%>">
                    <%=idDestinacion%>
     </option>
  </combobox:destinacion>
</select>
