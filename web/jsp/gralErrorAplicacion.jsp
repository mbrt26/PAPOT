<%-- gralErrorAplicacion.jsp --%>

<jsp:useBean id="errorAplicacionBean" 
  scope="request" 
  type="com.solucionesweb.losbeans.utilidades.ErrorAplicacionBean" />

<h4>
 Error de Aplicacion
</h4>

<h4>Señor(a) Usuario(a): Se ha presentado el siguiente Error:</h4>

<h4>Pagina:</h4> 
 <%=errorAplicacionBean.getPagina()%>

<h4>Codigo Error:</h4> 
 <%=errorAplicacionBean.getCodigo()%>

<h4>Descripcion Error:</h4> 
 <%=errorAplicacionBean.getDescripcion()%>

<h4>Nombre Campo:</h4> 
 <%=errorAplicacionBean.getNombreCampo()%>

<h4>Valor Campo:</h4> 
 <%=errorAplicacionBean.getValorCampo()%>
 
<h4>Solucion:</h4> 
 <%=errorAplicacionBean.getSolucion()%>
 
