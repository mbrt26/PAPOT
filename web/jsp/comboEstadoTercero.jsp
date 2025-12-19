<%@ taglib uri="/WEB-INF/tlds/listaEstadoTercero" prefix="lsb" %>

<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroEstado = request.getSession(true);
FachadaTerceroBean fachadaEstadoTercero = (FachadaTerceroBean)sesionTerceroEstado.getAttribute("fachadaTerceroIdAutoRetenedor");
String estadoStr = fachadaEstadoTercero.getEstadoStr();
%>
       <select name="xEstado">
           <lsb:listaEstadoTercero estadoTag="<%=estadoStr%>">
               <option value="<%=estadoVar%>">
                   <%=nombreEstadoVar%>
               </option>
           </lsb:listaEstadoTercero>
       </select>
