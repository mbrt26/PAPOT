<%@ taglib uri="/WEB-INF/tlds/listaDptoCiudad" prefix="lst" %>
<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionIdDptoCiudad = request.getSession(true);
FachadaTerceroBean fachadaTerceroDptoCiudad = (FachadaTerceroBean)sesionIdDptoCiudad.getAttribute("fachadaTerceroDptoCiudad");
String strIdDptoCiudad = fachadaTerceroDptoCiudad.getIdDptoCiudadStr();
%>

<select name=idDptoCiudad>
  <lst:listaDptoCiudad>

  <%
   if (idCiudadVar.equals(strIdDptoCiudad)) {
  %>
     <option value="<%=idCiudadVar%>" selected>
                    <%=nombreCiudadVar%> / <%=nombreDptoVar%>
     </option>

<% }else {  %>
     <option value="<%=idCiudadVar%>">
                    <%=nombreCiudadVar%> / <%=nombreDptoVar%>
     </option>

<% }  %>

  </lst:listaDptoCiudad>
</select>