<%@ taglib uri="/WEB-INF/tlds/listaRegimen" prefix="comboTerceroIdRegimen" %>
<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionIdRegimen = request.getSession(true);
FachadaTerceroBean fachadaTerceroIdRegimen = (FachadaTerceroBean)sesionIdRegimen.getAttribute("fachadaTerceroIdRegimen");
String strIdRegimen = fachadaTerceroIdRegimen.getIdRegimen();
%>

<select name=idRegimen>
  <comboTerceroIdRegimen:listaRegimen>

  <%
   if (idRegimenVar.equals(strIdRegimen)) {
  %>
     <option value="<%=idRegimenVar%>" selected>
                    <%=nombreRegimenVar%>
     </option>
<% }else {  %>
     <option value="<%=idRegimenVar%>">
                    <%=nombreRegimenVar%>
     </option>
<% }  %>

  </comboTerceroIdRegimen:listaRegimen>
</select>