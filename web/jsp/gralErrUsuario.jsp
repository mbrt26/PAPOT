<%-- gralErrUsuario.jsp --%>

<jsp:useBean id="validacionUsuarioBean"
  scope="request"
  type="com.solucionesweb.losbeans.utilidades.ValidacionUsuarioBean" />

<h2>
 Observaciones de la entrada del usuario
</h2>

<h3>Codigo Error: </h3>
 <%=validacionUsuarioBean.getCodigoError()%>

<h3>Descripcion Error: </h3>
<%=validacionUsuarioBean.getDescripcionError()%>

<h3>Nombre Campo: </h3>
<%=validacionUsuarioBean.getNombreCampo()%>

<h3>Valor Campo: </h3>
<%=validacionUsuarioBean.getValorCampo()%>

<h3>Solucion: </h3>
<%=validacionUsuarioBean.getSolucion()%>

<hr>
  <br>Por favor <a href="javascript:window.history.back()"> presione este vinculo</a> para intentar de nuevo</br>
</hr>