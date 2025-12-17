<html>
<%-- gralErrFinalizaCotizacion.jsp --%>

<jsp:useBean id="validacion"
  scope="request"
  type="com.solucionesweb.losbeans.utilidades.Validacion" />

<h2><font face="Verdana" size="6" color="#008000">Error de Entrada</font>
</h2>

<div align="center">
  <center>
  <table border="1" cellspacing="1" width="100%">
    <tr>
      <td width="26%"><b><font size="2" face="Verdana">Código Error&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b></td>
      <td width="74%"> <b><font face="Verdana" size="2" color="#0000FF"> <%=validacion.getCodigoError()%></font></b></td>
    </tr>
    <tr>
      <td width="26%"><b><font size="2" face="Verdana">Descripción Error</font></b></td>
      <td width="74%"><b><font face="Verdana" size="2" color="#0000FF"><%=validacion.getDescripcionError()%></font></b></td>
    </tr>
    <tr>
      <td width="26%"><b><font size="2" face="Verdana">Nombre Campo&nbsp;&nbsp;&nbsp;</font></b></td>
      <td width="74%"><b><font face="Verdana" size="2" color="#0000FF"><%=validacion.getNombreCampo()%></font></b></td>
    </tr>
    <tr>
      <td width="26%"><b><font size="2" face="Verdana"><fontface="Verdana">Valor
        Campo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b></td>
      <td width="74%"><b><font face="Verdana" size="2" color="#0000FF"><%=validacion.getValorCampo()%></font></b></td>
    </tr>
    <tr>
      <td width="26%"><b><font size="2" face="Verdana">Solución&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b></td>
      <td width="74%"><b><font face="Verdana" size="2" color="#0000FF"><%=validacion.getSolucion()%></font></b></td>
    </tr>
  </table>
  </center>
</div>

<font face="Verdana">
<br><b><font size="1">Por favor<font color="#008000"> <a href="javascript:history.back(1)">Presione este vínculo</a>
</font> para intentar
de nuevo</font></b></font>
<html>