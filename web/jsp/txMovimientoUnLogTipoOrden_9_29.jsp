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
            Vector vectorObjetos =  dctoContableTx.listaUnLog_tipoOrden_9_29();

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
                Results.append("<vrBase>" + fachadaBean.getVrBaseSf0() + "</vrBase>");
                Results.append("<vrPago>" + fachadaBean.getVrPagoSf0() + "</vrPago>");
                Results.append("<vrIva>" + fachadaBean.getVrIvaSf0() + "</vrIva>");
                Results.append("<idTipoNegocio>" + fachadaBean.getIdTipoNegocio() + "</idTipoNegocio>");
                Results.append("<vrRteFuente>" + fachadaBean.getVrRteFuenteSf0() + "</vrRteFuente>");
                Results.append("<vrRteIva>" + fachadaBean.getVrRteIvaSf0() + "</vrRteIva>");
                Results.append("<vrRteIca>" + fachadaBean.getVrRteIcaSf0() + "</vrRteIca>");
                Results.append("<nombreTercero>" + fachadaBean.getNombreTercero() + "</nombreTercero>");
                Results.append("<idDctoNitCC>" + fachadaBean.getIdDctoNitCC() + "</idDctoNitCC>");
                Results.append("<vrDsctoFcro>" + fachadaBean.getVrDsctoFcroSf0() + "</vrDsctoFcro>");
                Results.append("<vrCostoMV>" + fachadaBean.getVrCostoMVSf0() + "</vrCostoMV>");
                Results.append("<vrImpoconsumo>" + fachadaBean.getVrImpoconsumoSf0() + "</vrImpoconsumo>");
                Results.append("<porcentajeIva>" + fachadaBean.getPorcentajeIva() + "</porcentajeIva>");
                Results.append("<prefijo>" + fachadaBean.getPrefijo() + "</prefijo>");
                Results.append("<vrVentaSinIva>" + fachadaBean.getVrVentaSinIvaSf0() + "</vrVentaSinIva>");
                Results.append("<vrIvaVenta>" + fachadaBean.getVrIvaVentaSf0() + "</vrIvaVenta>");

                //
                Results.append("</log>");
            }
            Results.append("</documento>");

            out.println(Results.toString());

%>