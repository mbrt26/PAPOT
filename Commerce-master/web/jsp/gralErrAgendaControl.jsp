<%-- gralErrAgendaControl.jsp --%>

<jsp:useBean id="validacionAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.utilidades.ValidacionAgendaControlBean" />

<h2>
 Observaciones de la entrada del usuario
</h2>

<h3>Codigo Error: </h3>
 <%=validacionAgendaControlBean.getCodigoError()%>

<h3>Descripcion Error: </h3>
<%=validacionAgendaControlBean.getDescripcionError()%>

<h3>Nombre Campo: </h3>
<%=validacionAgendaControlBean.getNombreCampo()%>

<h3>Valor Campo: </h3>
<%=validacionAgendaControlBean.getValorCampo()%>

<h3>Solucion: </h3>
<%=validacionAgendaControlBean.getSolucion()%>

<hr>
  <br>Por favor <a href="javascript:window.history.back()"> presione este vinculo</a> para intentar de nuevo</br>
</hr>