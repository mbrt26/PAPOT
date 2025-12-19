<html>
<%-- gralErrReporteVisita.jsp --%>

<jsp:useBean id="validacionAgendaLogSoporteBean"
  scope="request"
  type="com.solucionesweb.losbeans.utilidades.ValidacionAgendaLogSoporteBean" />

<h2>
 Observaciones de la entrada del usuario
</h2>

<h3>Codigo Error: </h3>
 <%=validacionAgendaLogSoporteBean.getCodigoError()%>

<h3>Descripcion Error: </h3>
<%=validacionAgendaLogSoporteBean.getDescripcionError()%>

<h3>Nombre Campo: </h3>
<%=validacionAgendaLogSoporteBean.getNombreCampo()%>

<h3>Valor Campo: </h3>
<%=validacionAgendaLogSoporteBean.getValorCampo()%>

<h3>Solucion: </h3>
<%=validacionAgendaLogSoporteBean.getSolucion()%>

<hr>
  <br>Por Favor <a href="GralControladorServlet?nombrePaginaRequest=/vtaPermisosClienteReportarVisita.ctr">presione este vinculo</a> para intentar entrar de nuevo</br>
</hr>
<html>