<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" %>

<%

            //
            String xIdLocal       = request.getParameter("xIdLocal");
            String xFechaInicial  = request.getParameter("xFechaInicial");
            String xFechaFinal    = request.getParameter("xFechaFinal");
            String xIdLog         = request.getParameter("xIdLog");


            // Bean de fachada
            FachadaDctoOrdenDetalleBean fachadaBean;

            //
            DctoOrdenDetalleBean dctoOrdenDetalleBean
                                 = new DctoOrdenDetalleBean();

            //
            dctoOrdenDetalleBean.setIdLocal(xIdLocal);

            //
            Vector vectorObjetos =  dctoOrdenDetalleBean.listaResurtido(xFechaInicial,
                                                                        xFechaFinal);

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
                Results.append("<idPlu>" + fachadaBean.getIdPlu() + "</idPlu>");
                Results.append("<cantidad>" + fachadaBean.getCantidadStr() + "</cantidad>");
                Results.append("<existencia>" + fachadaBean.getExistenciaStr() + "</existencia>");
                Results.append("<idLog>" + xIdLog + "</idLog>");

                //
                Results.append("</local>");
            }
            Results.append("</documento>");

            out.println(Results.toString());

%>