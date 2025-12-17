<%@ taglib uri="/WEB-INF/tlds/listaRegimen" prefix="combo" %>

<select name=idRegimen>
  <combo:listaRegimen>
     <option value="<%=idRegimenVar%>">
                    <%=nombreRegimenVar%>
     </option>
  </combo:listaRegimen>
</select>
