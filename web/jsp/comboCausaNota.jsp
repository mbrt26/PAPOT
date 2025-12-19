<html>

    <%@ taglib uri="/WEB-INF/tlds/listaCausaNotaOpcion" prefix="lsa" %>
       <select name=xIdCausa>
           <lsa:listaCausaNotaOpcion>

               <option value="<%=idCausaVar%>">
                   <%=nombreCausaVar%>
               </option>
           </lsa:listaCausaNotaOpcion>
       </select>
</html>
