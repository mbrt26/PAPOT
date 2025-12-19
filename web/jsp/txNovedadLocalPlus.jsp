<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.PluBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaPluBean" %>

<%

            // Bean de fachada
            FachadaPluBean fachadaPluBean = new FachadaPluBean();

            PluBean PluBean = new PluBean();
            String idSeqStr = request.getParameter("idSeq");

            //
            PluBean.setIdSeq(idSeqStr);

            Vector vectorObjetos = PluBean.listaPluTx();

            //
            Iterator iteratorObjetos;
            iteratorObjetos = vectorObjetos.iterator();

            // output the vote results
            StringBuffer Results = new StringBuffer();

            Results.append("<documento>");

            while (iteratorObjetos.hasNext()) {

                fachadaPluBean = (FachadaPluBean) iteratorObjetos.next();

                Results.append("<plu>");
                Results.append("<idPlu>" + fachadaPluBean.getIdPluStr() + "</idPlu>");
                Results.append("<nombrePlu>" + fachadaPluBean.getNombrePlu() + "</nombrePlu>");
                Results.append("<vrGeneral>" + fachadaPluBean.getVrGeneralStr() + "</vrGeneral>");
                Results.append("<vrMayorista>" + fachadaPluBean.getVrMayoristaStr() + "</vrMayorista>");
                Results.append("<porcentajeIva>" + fachadaPluBean.getPorcentajeIvaStr() + "</porcentajeIva>");
                Results.append("<idTipo>" + fachadaPluBean.getIdTipoStr() + "</idTipo>");
                Results.append("<idLinea>" + fachadaPluBean.getIdLineaStr() + "</idLinea>");
                Results.append("<idUCompra>" + fachadaPluBean.getIdUCompraStr() + "</idUCompra>");
                Results.append("<idUVenta>" + fachadaPluBean.getIdUVentaStr() + "</idUVenta>");
                Results.append("<vrCosto>" + fachadaPluBean.getVrCostoStr() + "</vrCosto>");
                Results.append("<idCategoria>" + fachadaPluBean.getIdCategoriaStr() + "</idCategoria>");
                Results.append("<idMarca>" + fachadaPluBean.getIdMarcaStr() + "</idMarca>");
                Results.append("<vrSucursal>" + fachadaPluBean.getVrSucursalStr() + "</vrSucursal>");
                Results.append("<factorVenta>" + fachadaPluBean.getFactorVentaStr() + "</factorVenta>");
                Results.append("<factorDespacho>" + fachadaPluBean.getFactorDespachoStr() + "</factorDespacho>");
                Results.append("<estado>" + fachadaPluBean.getEstadoStr() + "</estado>");
                Results.append("<idSeq>" + fachadaPluBean.getIdSeqStr() + "</idSeq>");
                Results.append("<referencia>" + fachadaPluBean.getReferencia() + "</referencia>");
                Results.append("<vrImpoconsumo>" + fachadaPluBean.getVrImpoconsumoStr() + "</vrImpoconsumo>");
                Results.append("<vrCostoIND>" + fachadaPluBean.getVrCostoINDStr() + "</vrCostoIND>");

                Results.append("</plu>");

            }
            Results.append("</documento>");

            out.println(Results.toString());

%>