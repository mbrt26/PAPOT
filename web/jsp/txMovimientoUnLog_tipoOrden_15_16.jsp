<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.DctoContableTx" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaDctoBean" %>

<%
            // Bean de fachada
            FachadaDctoBean fachadaBean;

            //
            String xIdLocal     = request.getParameter("xIdLocal");
            String xIdTipoOrden = request.getParameter("xIdTipoOrden");
            String xIdLog       = request.getParameter("xIdLog");

            //
            DctoContableTx dctoContableTx
                                 = new DctoContableTx();

            //
            dctoContableTx.setIdLocal(xIdLocal);
            dctoContableTx.setIdTipoOrden(xIdTipoOrden);
            dctoContableTx.setIdLog(xIdLog);

            //
            Vector vectorObjetos =  dctoContableTx.listaUnLog_tipoOrden_15_16();

            //
            Iterator iteratorObjetos;
            iteratorObjetos = vectorObjetos.iterator();

            // output the vote results
            StringBuffer Results = new StringBuffer();
            Results.append("<documento>");

            //
            while (iteratorObjetos.hasNext()) {

                //
                fachadaBean = (FachadaDctoBean) iteratorObjetos.next();

                //
                Results.append("<log>");
                Results.append("<idLocal>" + fachadaBean.getIdLocal() + "</idLocal>");
                Results.append("<idTipoOrden>" + fachadaBean.getIdTipoOrden() + "</idTipoOrden>");
                Results.append("<idDcto>" + fachadaBean.getIdDctoSf0() + "</idDcto>");
                Results.append("<idCliente>" + fachadaBean.getIdCliente() + "</idCliente>");
                Results.append("<fechaDcto>" + fachadaBean.getFechaDcto() + "</fechaDcto>");
                Results.append("<vrCostoMV>" + fachadaBean.getVrCostoMV() + "</vrCostoMV>");
                Results.append("<vrDescuento>" + fachadaBean.getVrDescuento() + "</vrDescuento>");
                Results.append("<vrRteFuente>" + fachadaBean.getVrRteFuente() + "</vrRteFuente>");
                Results.append("<porcentajeIva>" + fachadaBean.getPorcentajeIva() + "</porcentajeIva>");

                //
                Results.append("</log>");
            }
            Results.append("</documento>");

            out.println(Results.toString());

%>