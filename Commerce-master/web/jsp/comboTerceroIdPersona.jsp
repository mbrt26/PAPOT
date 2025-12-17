<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroIdPersona = request.getSession(true);
FachadaTerceroBean fachadaTerceroIdPersona = (FachadaTerceroBean)sesionTerceroIdPersona.getAttribute("fachadaTerceroIdPersona");
int intIdPersona = fachadaTerceroIdPersona.getIdPersona();
%>

<select name=idPersona>
<%
     if(intIdPersona == 0)
     {  %>
        <option value="0" selected>Natural</option>
        <option value="1" >Juridica</option>
<%    }
      else
     {   %>
        <option value="0" >Natural</option>
        <option value="1" selected>Juridica</option>
<%    }  %>
</select>