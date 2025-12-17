<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" %>

<%

            //
            String xIdLocalOrigen       = request.getParameter("xIdLocalOrigen");
            String xIdTipoOrden   = request.getParameter("xIdTipoOrden");
            String xIdTipoOrdenOrigen   = request.getParameter("xIdTipoOrdenOrigen");
           


            // Bean de fachada
            FachadaDctoOrdenDetalleBean fachadaBean;

            //
            DctoOrdenDetalleBean dctoOrdenDetalleBean
                                 = new DctoOrdenDetalleBean();

            //
            dctoOrdenDetalleBean.setIdLocalOrigen(xIdLocalOrigen);
            dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
            dctoOrdenDetalleBean.setIdTipoOrdenOrigen(xIdTipoOrdenOrigen);

            //xFechaInicial
            Vector vectorObjetos =  dctoOrdenDetalleBean.listaDetalleLocalOrigen();

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

                Results.append("<IDLOCAL>" + fachadaBean.getIdLocal() + "</IDLOCAL>");
                Results.append("<IDTIPOORDEN>" + fachadaBean.getIdTipoOrden() + "</IDTIPOORDEN>");
                Results.append("<IDORDEN>" + fachadaBean.getIdOrden() + "</IDORDEN>");
                Results.append("<IDPLU>" + fachadaBean.getIdPlu() + "</IDPLU>");
                Results.append("<CANTIDAD>" + fachadaBean.getCantidadStr() + "</CANTIDAD>");             
                Results.append("<VRCOSTO>" + fachadaBean.getVrCostoStr() + "</VRCOSTO>");
                Results.append("<vrCostoNegociado>" + fachadaBean.getVrCostoNegociado() + "</vrCostoNegociado>");
                Results.append("<vrCostoResurtido>" + fachadaBean.getVrCostoResurtido() + "</vrCostoResurtido>");       
                Results.append("<vrCostoIND>" + fachadaBean.getVrCostoIND() + "</vrCostoIND>");
                Results.append("<idOrdenOrigen>" + fachadaBean.getIdOrdenOrigen() + "</idOrdenOrigen>");
                Results.append("<idLocalOrigen>" + fachadaBean.getIdLocalOrigen() + "</idLocalOrigen>");
                Results.append("<idTipoOrdenOrigen>" + fachadaBean.getIdTipoOrdenOrigen() + "</idTipoOrdenOrigen>");

                //
                Results.append("</local>");
            }
            Results.append("</documento>");

            out.println(Results.toString());

%>