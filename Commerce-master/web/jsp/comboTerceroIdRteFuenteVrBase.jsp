<%@ taglib uri="/WEB-INF/tlds/listaAgenteRteFuenteVrBase" prefix="lrf" %>

<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroIdRteFuenteVrBase = request.getSession(true);
FachadaTerceroBean fachadaTerceroIdRteFuenteVrBase = (FachadaTerceroBean)sesionTerceroIdRteFuenteVrBase.getAttribute("fachadaTerceroIdRteFuenteVrBase");
String xIdRteFuenteVrBaseStr = fachadaTerceroIdRteFuenteVrBase.getIdRteFuenteVrBaseStr();
%>
       <select name="xIdRteFuenteVrBase">
           <lrf:listaAgenteRteFuenteVrBase idRteFuenteVrBaseTag ="<%=xIdRteFuenteVrBaseStr%>">
               <option value="<%=idRteFuenteVrBaseVar%>">
                   <%=nombreRteFuenteVrBaseVar%>
               </option>
           </lrf:listaAgenteRteFuenteVrBase>
       </select>
