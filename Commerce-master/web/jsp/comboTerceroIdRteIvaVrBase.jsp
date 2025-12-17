<%@ taglib uri="/WEB-INF/tlds/listaAgenteRteIvaVrBase" prefix="lrr" %>

<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroIdRteIvaVrBase = request.getSession(true);
FachadaTerceroBean fachadaTerceroIdRteIvaVrBase = (FachadaTerceroBean)sesionTerceroIdRteIvaVrBase.getAttribute("fachadaTerceroIdRteIvaVrBase");
String xIdRteIvaVrBaseStr = fachadaTerceroIdRteIvaVrBase.getIdRteIvaVrBaseStr();
%>
       <select name="xIdRteIvaVrBase">
           <lrr:listaAgenteRteIvaVrBase idRteIvaVrBaseTag ="<%=xIdRteIvaVrBaseStr%>">
               <option value="<%=idRteIvaVrBaseVar%>">
                   <%=nombreRteIvaVrBaseVar%>
               </option>
           </lrr:listaAgenteRteIvaVrBase>
       </select>
