<%@ page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
HttpSession sesionTerceroTipoIdTercero = request.getSession(true);
FachadaTerceroBean fachadaTerceroTipoIdTercero = (FachadaTerceroBean)sesionTerceroTipoIdTercero.getAttribute("fachadaTerceroTipoIdTercero");
String strTipoIdTercero = fachadaTerceroTipoIdTercero.getTipoIdTercero();
%>

<select name=tipoIdTercero>
<%
     if(strTipoIdTercero.equals("C"))
     {  %>
        <option value="C" selected>Cedula</option>
        <option value="A" >Nit</option>
<%    }
      else
     {   %>
        <option value="C" >Cedula</option>
        <option value="A" selected>Nit</option>
<%    }  %>
</select>