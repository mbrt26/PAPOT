    <%@ taglib uri="/WEB-INF/tlds/listaTerceroClase" prefix="lsa" %>

    <% String xIdEstado = "1";%>
    <% String xIdTipoTercero = "1";%>

    
       <select name=idClase>
           <lsa:listaTerceroClase  idEstadoTag="<%=xIdEstado%>"
                                   idTipoTerceroTag="<%=xIdTipoTercero%>">

               <option value="<%=idClaseVar%>">
                   <%=nombreClaseVar%>
               </option>
           </lsa:listaTerceroClase>
       </select>



