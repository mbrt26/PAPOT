<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" %>

<%

            //
            String xIdLocalOrigen       = request.getParameter("xIdLocalOrigen");
            String xIdTipoOrden   = request.getParameter("xIdTipoOrden");
            String xIdTipoOrdenNew   = request.getParameter("xIdTipoOrdenNew");
            String xEstado        = request.getParameter("xEstado");
            String xIdOrden        = request.getParameter("xIdOrden");
            String xIdLocal      = request.getParameter("xIdLocal");


            // Bean de fachada
            FachadaDctoOrdenDetalleBean fachadaBean;

            //
            DctoOrdenDetalleBean dctoOrdenDetalleBean
                                 = new DctoOrdenDetalleBean();

            //
            dctoOrdenDetalleBean.setIdLocal(xIdLocalOrigen);
            dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
            dctoOrdenDetalleBean.setIdCliente(xIdLocal);
            dctoOrdenDetalleBean.setEstado(xEstado);

            //xFechaInicial
            Vector vectorObjetos =  dctoOrdenDetalleBean.listaDetalleLocal();

            //
            Iterator iteratorObjetos;
            iteratorObjetos = vectorObjetos.iterator();

            // output the vote results
            StringBuffer Results = new StringBuffer();
            Results.append("<documento>");

            //
            while (iteratorObjetos.hasNext()) {

                //
                fachadaBean = (FachadaDctoOrdenDetalleBean) iteratorObjetos.next();

                //
                Results.append("<local>");
                Results.append("<idLocal>" + fachadaBean.getIdLocal() + "</idLocal>");
                Results.append("<idTipoOrden>" + fachadaBean.getIdTipoOrden() + "</idTipoOrden>");
                Results.append("<idOrden>" + fachadaBean.getIdOrden() + "</idOrden>");
                Results.append("<idPlu>" + fachadaBean.getIdPlu() + "</idPlu>");
                Results.append("<cantidadPedida>" + fachadaBean.getCantidadPedida() + "</cantidadPedida>");
                Results.append("</local>");

            }
            Results.append("</documento>");

            out.println(Results.toString());

%>