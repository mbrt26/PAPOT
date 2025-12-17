package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOT;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.HashMap;
import java.util.Map;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaOT;

//
import com.solucionesweb.losbeans.utilidades.JhFormat;
import java.util.Collection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeneraPDF_Pedido implements GralManejadorRequest {

    //
    private int numeroOrden;
    private int itemPadre;
    private String nombreReporte;
    private String tituloReporte;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        JhFormat jhFormat = new JhFormat();

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //
        String reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\"
                + getNombreReporte();

        //
        String pathPDF =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        FachadaPluFicha fachadaPluFichaENC = new FachadaPluFicha();

        //
        ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setNumeroOrden(getNumeroOrden());

        //1-Llenar el datasource con la informacion de la base de datos
        fachadaPluFichaENC = colaboraOrdenTrabajo.listaUnOTFCH(getItemPadre());

        //---
        pars.put("p_numeroOrden", fachadaPluFichaENC.getNumeroOrden());
        pars.put("p_itemPadre", fachadaPluFichaENC.getItemPadre());
        pars.put("p_nombreTercero", fachadaPluFichaENC.getNombreTercero());
        pars.put("p_referenciaCliente",
                                     fachadaPluFichaENC.getReferenciaCliente());
        pars.put("p_idFicha", fachadaPluFichaENC.getIdFicha());
        pars.put("p_item", fachadaPluFichaENC.getItem());
        pars.put("p_referencia", fachadaPluFichaENC.getReferencia());
        pars.put("p_nombrePlu", fachadaPluFichaENC.getNombrePlu());
        pars.put("p_fechaEntrega", fachadaPluFichaENC.getFechaEntregaCorta());
        pars.put("p_fechaOrden", fachadaPluFichaENC.getFechaOrdenCorta());
        pars.put("p_cantidad", fachadaPluFichaENC.getCantidad());
        pars.put("p_idCliente", fachadaPluFichaENC.getIdCliente());
        pars.put("p_vrVentaUnitarioSinIva",
                                 fachadaPluFichaENC.getVrVentaUnitarioSinIva());
        pars.put("p_direccionDespacho",
                                     fachadaPluFichaENC.getDireccionDespacho());
        pars.put("p_contacto", fachadaPluFichaENC.getContacto());
        pars.put("p_ordenCompra", fachadaPluFichaENC.getOrdenCompra());
        pars.put("p_idFormaPago", fachadaPluFichaENC.getIdFormaPagoStr());
        pars.put("p_nombreVendedor", fachadaPluFichaENC.getNombreVendedor());
        pars.put("p_observacion", fachadaPluFichaENC.getObservacion());

        //----------------------------------------------------------------------
        int xIdOperacionPedido = 1;

        //
        FachadaOT fachadaOTExtrusion = new FachadaOT();

        //
        ColaboraOT colaboraOT = new ColaboraOT();

        //
        colaboraOT.setIdOperacion(xIdOperacionPedido);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaPedidoUnOT_TipoEscala_1();

        //
        pars.put("p_nombreReferencia", fachadaOTExtrusion.getNombreReferencia());
        pars.put("p_anchoPedido", fachadaOTExtrusion.getAnchoPedido());
        pars.put("p_largoPedido", fachadaOTExtrusion.getLargoPedido());
        pars.put("p_calibrePedido", fachadaOTExtrusion.getCalibrePedido());
        pars.put("p_costoFotopolimeroPedido",
                               fachadaOTExtrusion.getCostoFotopolimeroPedido());
        pars.put("p_porcentajeFotopolimeroClientePedido",
                   fachadaOTExtrusion.getPorcentajeFotopolimeroClientePedido());
        pars.put("p_porcentajeFotopolimeroUnionPedido",
                   fachadaOTExtrusion.getPorcentajeFotopolimeroUnionPedido());
        pars.put("p_tipoEmbobinadoPedido",
                                  fachadaOTExtrusion.getTipoEmbobinadoPedido());

        //
        fachadaOTExtrusion =
                colaboraOT.listaPedidoUnOT_TipoEscala_2();

        //
        pars.put("p_colorPedido",
                                     fachadaOTExtrusion.getColorPedido());
        pars.put("p_certificadoCalidadPedido",
                              fachadaOTExtrusion.getCertificadoCalidadPedido());
        pars.put("p_tipoTroquelPedido",
                              fachadaOTExtrusion.getTipoTroquelPedido());


        //
        fachadaOTExtrusion =
                colaboraOT.listaPedidoUnOT_TipoEscala_3();

        //
        pars.put("p_observacionPedido",
                                     fachadaOTExtrusion.getObservacionPedido());
        pars.put("p_terminacionPedido",
                                     fachadaOTExtrusion.getTerminacionPedido());

        //----------------------------------------------------------------------
        int xIdOperacionImpresion = 3;

        //
        colaboraOT.setIdOperacion(xIdOperacionImpresion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaImpresionUnOT_TipoEscala_1();

        //
        pars.put("p_colorImpresion", fachadaOTExtrusion.getColorImpresion());
        pars.put("p_repeticionImpresion", fachadaOTExtrusion.getRepeticionImpresion());
        pars.put("p_rodilloImpresion", fachadaOTExtrusion.getRodilloImpresion());
        pars.put("p_alturaCara1Impresion", fachadaOTExtrusion.getAlturaCara1Impresion());
        pars.put("p_alturaCara2Impresion", fachadaOTExtrusion.getAlturaCara2Impresion());
        pars.put("p_codigoBarraImpresion", fachadaOTExtrusion.getCodigoBarraImpresion());
        pars.put("p_cyrelCara1Impresion", fachadaOTExtrusion.getCyrelCara1Impresion());
        pars.put("p_cyrelCara2Impresion", fachadaOTExtrusion.getCyrelCara2Impresion());
        pars.put("p_cyrelCara2Impresion", fachadaOTExtrusion.getCyrelCara2Impresion());
        pars.put("p_tipoEmbobinadoImpresion", fachadaOTExtrusion.getTipoEmbobinadoImpresion());

        //
        colaboraOT.setIdOperacion(xIdOperacionImpresion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaImpresionUnOT_TipoEscala_2();

        //
        pars.put("p_maquinaImpresion", fachadaOTExtrusion.getMaquinaImpresion());
        pars.put("p_destinoImpresion", fachadaOTExtrusion.getDestinoImpresion());


        //
        fachadaOTExtrusion =
                colaboraOT.listaImpresionUnOT_TipoEscala_3();

        //
        pars.put("p_observacionImpresion", fachadaOTExtrusion.getObservacionExtrusion());

        //----------------------------------------------------------------------
        int xIdOperacionRefilado = 4;

        //
        colaboraOT.setIdOperacion(xIdOperacionRefilado);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaRefiladoUnOT_TipoEscala_1();

        //
        pars.put("p_anchoRefilado", fachadaOTExtrusion.getAnchoRefilado());
        pars.put("p_alturaRefilado", fachadaOTExtrusion.getAlturaRefilado());
        pars.put("p_tipoRefilado", fachadaOTExtrusion.getTipoRefilado());
        pars.put("p_metroRefilado", fachadaOTExtrusion.getMetroRefilado());
        pars.put("p_pesoRolloRefilado", fachadaOTExtrusion.getPesoRolloRefilado());
        pars.put("p_tipoRolloRefilado", fachadaOTExtrusion.getTipoRolloRefilado());
        pars.put("p_tipoEmbobinadoRefilado", fachadaOTExtrusion.getTipoEmbobinadoRefilado());

        //----------------------------------------------------------------------
        JRBeanCollectionDataSource dataSource;

        //
        colaboraOrdenTrabajo.setNumeroOrden(getNumeroOrden());

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraOrdenTrabajo.listaUnOT(getItemPadre());

        //
        dataSource = new JRBeanCollectionDataSource(lista, false);


        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciïón (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);

            //4-Exportamos el reporte a pdf y lo guardamos en disco
            //JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF +
            //        fachadaOrdenVenta.getLocalOrdenVenta() +     ".pdf");

            //
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint,
                    servletOutputStream);

            //
            servletOutputStream.flush();
            servletOutputStream.close();

            //
            System.out.println("Done!");


        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        //
        return "";

    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public void setNumeroOrden(String numeroOrdenStr) {
        this.numeroOrden = new Integer(numeroOrdenStr).intValue();
    }

    public int getItemPadre() {
        return itemPadre;
    }

    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    public void setItemPadre(String itemPadreStr) {
        this.itemPadre = new Integer(itemPadreStr).intValue();
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }
}
