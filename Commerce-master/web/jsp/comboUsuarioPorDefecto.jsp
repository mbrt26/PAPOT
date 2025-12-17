<html>

    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioDefectoOpcion" prefix="lsa" %>

    <% String xIdNivelMostrarCombo      ="1,2,51,52,10"; %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"/>

    <jsp:useBean id="fachadaAgendaLogVisitaBean" scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean"/>

       <select name=xIdVendedor>
           <lsa:listaUsuarioDefectoOpcion  idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                           idNivelTag="<%=xIdNivelMostrarCombo%>"
                                           idLocalUsuarioTag="<%=fachadaAgendaLogVisitaBean.getIdLocal()%>">

               <option value="<%=idUsuarioVar%>">
                   <%=nombreUsuarioVar%>
               </option>
           </lsa:listaUsuarioDefectoOpcion>
       </select>
</html>
