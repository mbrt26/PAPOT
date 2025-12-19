<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean" %>


<%

            //
            String xIdLocal     = request.getParameter("xIdLocal");
            String xIdTipoOrden = request.getParameter("xIdTipoOrden");
            String xIdLog       = request.getParameter("xIdLog");

            //
            int xIdEstadoError  = 99;

            //
            AgendaLogVisitaBean agendaLogVisita
                                = new AgendaLogVisitaBean();

            //
            agendaLogVisita.setIdLocal(xIdLocal);
            agendaLogVisita.setIdTipoOrden(xIdTipoOrden);
            agendaLogVisita.setIdLog(xIdLog);
            agendaLogVisita.setIdEstadoTx(xIdEstadoError);

            //
            agendaLogVisita.actualizaEstadoTx();

%>