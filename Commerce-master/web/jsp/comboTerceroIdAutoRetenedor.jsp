<%@ taglib uri="/WEB-INF/tlds/listaAgenteRetencion" prefix="lsa" %>

<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroIdAutoRetenedor = request.getSession(true);
FachadaTerceroBean fachadaTerceroIdAutoRetenedor = (FachadaTerceroBean)sesionTerceroIdAutoRetenedor.getAttribute("fachadaTerceroIdAutoRetenedor");
String idAutoRetenedorStr = fachadaTerceroIdAutoRetenedor.getIdAutoRetenedorStr();
%>
       <select name="idAutoRetenedor">
           <lsa:listaAgenteRetencion idAutoRetenedorTag="<%=idAutoRetenedorStr%>">
               <option value="<%=idAutoRetenedorVar%>">
                   <%=nombreAutoRetenedorVar%>
               </option>
           </lsa:listaAgenteRetencion>
       </select>
