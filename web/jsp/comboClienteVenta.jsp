<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaAudtoriaRepRentabilidadCliente" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />
<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>
<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>
<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>
<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>
<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>
<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenINIStr()%>
<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenFINStr()%>

 <select name=xIdCliente>
    <lst:listaAudtoriaRepRentabilidadCliente
                            idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
		            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idTipoOrdenInicialTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenINIStr()%>"
                            idTipoOrdenFinalTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenFINStr()%>">

                                <option value ="<%=idClienteVar%>">
                                                <%=nombreTerceroVar%>
                                </option>
    </lst:listaAudtoriaRepRentabilidadCliente>
 </select>
</html>