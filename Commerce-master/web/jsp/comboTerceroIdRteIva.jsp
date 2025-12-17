<%@ taglib uri="/WEB-INF/tlds/listaAgenteRetencionIva" prefix="lri" %>

<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroIdRteIva = request.getSession(true);
FachadaTerceroBean fachadaTerceroIdRteIva = (FachadaTerceroBean)sesionTerceroIdRteIva.getAttribute("fachadaTerceroIdRteIva");
String xIdRteIvaStr = fachadaTerceroIdRteIva.getIdRteIvaStr();
%>
       <select name="xIdRteIva">
           <lri:listaAgenteRetencionIva idRteIvaTag="<%=xIdRteIvaStr%>">
               <option value="<%=idRteIvaVar%>">
                   <%=nombreRteIvaVar%>
               </option>
           </lri:listaAgenteRetencionIva>
       </select>
