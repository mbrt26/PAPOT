package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOT;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;
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
import com.solucionesweb.losbeans.negocio.LocalBean;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.JhFormat;
import java.util.Collection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeneraPDF_OTOperacion implements GralManejadorRequest {

    //
    private int idLocal;
    private int numeroOrden;
    private int itemPadre;
    private String nombreReporte;
    private String tituloReporte;
    private int ordenServicio;
    private String nombreTercero;
    private String fechaServicio;

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

        //----------------------------------------------------------------------
        FachadaLocalBean fachadaLocalBean = new FachadaLocalBean();

        //
        LocalBean  localBean  = new LocalBean();

        //
        localBean.setIdLocal(getIdLocal());

        //
        fachadaLocalBean = localBean.listaUnLocal();

        //
        String xNombreLocal = fachadaLocalBean.getNombreLocal().toUpperCase();

        //----------------------------------------------------------------------
        FachadaPluFicha fachadaPluFichaENC = new FachadaPluFicha();

        //
        ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setNumeroOrden(getNumeroOrden());

        //1-Llenar el datasource con la informacion de la base de datos
        fachadaPluFichaENC = colaboraOrdenTrabajo.listaUnOTFCH(getItemPadre());

        //---
        pars.put("p_tituloReporte", getTituloReporte());
        pars.put("p_ordenServicio", getOrdenServicio());
        pars.put("p_nombreLocal", xNombreLocal);
        pars.put("p_numeroOrden", fachadaPluFichaENC.getNumeroOrden());
        pars.put("p_itemPadre", fachadaPluFichaENC.getItemPadre());
        pars.put("p_nombreTercero", getNombreTercero());
        pars.put("p_referenciaCliente", fachadaPluFichaENC.getReferenciaCliente());
        pars.put("p_idFicha", fachadaPluFichaENC.getIdFicha());
        pars.put("p_item", fachadaPluFichaENC.getItem());
        pars.put("p_referencia", fachadaPluFichaENC.getReferencia());
        pars.put("p_nombrePlu", fachadaPluFichaENC.getNombrePlu());
        pars.put("p_fechaEntrega", getFechaServicio());
        pars.put("p_cantidad", fachadaPluFichaENC.getCantidad());
        pars.put("p_idCliente", fachadaPluFichaENC.getIdCliente());

        //----------------------------------------------------------------------
        int xIdOperacionExtrucion = 2;

        //
        FachadaOT fachadaOTExtrusion = new FachadaOT();

        //
        ColaboraOT colaboraOT = new ColaboraOT();

        //
        colaboraOT.setIdOperacion(xIdOperacionExtrucion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaExtrusionUnOT_TipoEscala_1();

        //
        pars.put("p_maquinaExtrusion", fachadaOTExtrusion.getMaquinaExtrusion());
        pars.put("p_calibreExtrusion", fachadaOTExtrusion.getCalibreExtrusion());
        pars.put("p_anchoExtrusion", fachadaOTExtrusion.getAnchoExtrusion());
        pars.put("p_fuelle_1Extrusion", fachadaOTExtrusion.getFuelle_1Extrusion());
        pars.put("p_fuelle_2Extrusion", fachadaOTExtrusion.getFuelle_2Extrusion());
        pars.put("p_tipoRolloExtrusion", fachadaOTExtrusion.getTipoRolloExtrusion());
        pars.put("p_numeroRolloExtrusion", fachadaOTExtrusion.getNumeroRolloExtrusion());
        pars.put("p_tratadoDinaExtrusion", fachadaOTExtrusion.getTratadoDinaExtrusion());
        pars.put("p_tratadoCaraExtrusion", fachadaOTExtrusion.getTratadoCaraExtrusion());
        pars.put("p_mpLineal", fachadaOTExtrusion.getMpLineal());
        pars.put("p_mpBaja", fachadaOTExtrusion.getMpBaja());
        pars.put("p_mpAlta", fachadaOTExtrusion.getMpAlta());
        pars.put("p_mpPP", fachadaOTExtrusion.getMpPP());

        //
        colaboraOT.setIdOperacion(xIdOperacionExtrucion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //--
        fachadaOTExtrusion =
                colaboraOT.listaExtrusionUnOT_TipoEscala_2();

        //
        pars.put("p_maquinaExtrusion", fachadaOTExtrusion.getMaquinaExtrusion());
        pars.put("p_tipoRolloExtrusion", fachadaOTExtrusion.getTipoRolloExtrusion());
        pars.put("p_tipoSelladoExtrusion", fachadaOTExtrusion.getTipoSelladoExtrusion());
        pars.put("p_destinoExtrusion", fachadaOTExtrusion.getDestinoExtrusion());

        //
        colaboraOT.setIdOperacion(xIdOperacionExtrucion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaExtrusionUnOT_TipoEscala_3();

        //
        pars.put("p_observacionExtrusion", fachadaOTExtrusion.getObservacionExtrusion());

        //
        colaboraOT.setIdOperacion(xIdOperacionExtrucion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion.setMpOtraRef(
                                  colaboraOT.listaExtrusionUnOT_TipoEscala_4());

        //
        pars.put("p_mpOtraRef", fachadaOTExtrusion.getMpOtraRef());

        //----------------------------------------------------------------------
        FichaTecnica fichaTecnica = new FichaTecnica();

        //
        FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

        //
        double xFactorDensidad = fichaTecnica.factorDensidad(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha());

        //
        fachadaPluFicha.setFactorDensidad(xFactorDensidad);

        //
        double xPesoPedido = fichaTecnica.pesoPedido(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha(),
                new Double(fachadaPluFichaENC.getCantidad()).doubleValue());

        //
        fachadaPluFicha.setPesoPedido(xPesoPedido);


        //
        double xPesoMillar = fichaTecnica.pesoMillar(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha()) * 1000;

        //
        fachadaPluFicha.setPesoMillar(xPesoMillar);


        //
        double xPesoComplemento = fichaTecnica.pesoComplemento(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha());

        //
        fachadaPluFicha.setPesoComplemento(xPesoComplemento);


        //
        double xMetroPedido = fichaTecnica.metroPedido(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha(),
                new Double(fachadaPluFichaENC.getCantidad()).doubleValue());

        //
        fachadaPluFicha.setMetroPedido(xMetroPedido);

        //
        double xMetroRollo = fichaTecnica.metroRollo(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha(),
                new Double(fachadaPluFichaENC.getCantidad()).doubleValue());

        //
        fachadaPluFicha.setMetroRollo(xMetroRollo);


        //
        double xPesoRollo = fichaTecnica.pesoRollo(
                fachadaPluFichaENC.getIdCliente(),
                fachadaPluFichaENC.getIdFicha(),
                new Double(fachadaPluFichaENC.getCantidad()).doubleValue());

        //
        fachadaPluFicha.setPesoRollo(xPesoRollo);

        //
        pars.put("p_factorDensidad", fachadaPluFicha.getFactorDensidadDf5());
        pars.put("p_pesoPedido", fachadaPluFicha.getPesoPedidoDf0());
        pars.put("p_pesoMillar", fachadaPluFicha.getPesoMillarDf2());
        pars.put("p_pesoComplemento", fachadaPluFicha.getPesoComplementoDf2());
        pars.put("p_metroPedido", fachadaPluFicha.getMetroPedidoDf0());
        pars.put("p_metroRollo", fachadaPluFicha.getMetroRolloDf0());
        pars.put("p_pesoRollo", fachadaPluFicha.getPesoRolloDf2());


        //----------------------------------------------------------------------
        int xIdOperacionImpresion = 3;

        //
        colaboraOT.setIdOperacion(xIdOperacionImpresion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaImpresionUnOT_TipoEscala_1();

        //
        pars.put("p_colorImpresion", fachadaOTExtrusion.getColorImpresionSf0());
        pars.put("p_repeticionImpresion", fachadaOTExtrusion.getRepeticionImpresionSf0());
        pars.put("p_rodilloImpresion", fachadaOTExtrusion.getRodilloImpresion());
        pars.put("p_codigoBarraImpresion", fachadaOTExtrusion.getCodigoBarraImpresionSf0());
        pars.put("p_cyrelCara1Impresion", fachadaOTExtrusion.getCyrelCara1ImpresionSf0());
        pars.put("p_cyrelCara2Impresion", fachadaOTExtrusion.getCyrelCara2ImpresionSf0());
        pars.put("p_tipoEmbobinadoImpresion", fachadaOTExtrusion.getTipoEmbobinadoImpresionSf0());

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
        colaboraOT.setIdOperacion(xIdOperacionImpresion);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaImpresionUnOT_TipoEscala_5();

        //
        pars.put("p_cara1Color1Pantone", fachadaOTExtrusion.getCara1Color1Pantone());
        pars.put("p_cara1Color2Pantone", fachadaOTExtrusion.getCara1Color2Pantone());
        pars.put("p_cara1Color3Pantone", fachadaOTExtrusion.getCara1Color3Pantone());
        pars.put("p_cara1Color4Pantone", fachadaOTExtrusion.getCara1Color4Pantone());
        pars.put("p_cara1Color5Pantone", fachadaOTExtrusion.getCara1Color5Pantone());
        pars.put("p_cara1Color6Pantone", fachadaOTExtrusion.getCara1Color6Pantone());

        //
        pars.put("p_cara2Color1Pantone", fachadaOTExtrusion.getCara2Color1Pantone());
        pars.put("p_cara2Color2Pantone", fachadaOTExtrusion.getCara2Color2Pantone());
        pars.put("p_cara2Color3Pantone", fachadaOTExtrusion.getCara2Color3Pantone());
        pars.put("p_cara2Color4Pantone", fachadaOTExtrusion.getCara2Color4Pantone());
        pars.put("p_cara2Color5Pantone", fachadaOTExtrusion.getCara2Color5Pantone());
        pars.put("p_cara2Color6Pantone", fachadaOTExtrusion.getCara2Color6Pantone());

        //
        fachadaOTExtrusion =
                colaboraOT.listaImpresionUnOT_TipoEscala_3();

        //
        pars.put("p_observacionImpresion", fachadaOTExtrusion.getObservacionImpresion());
        pars.put("p_alturaCara1Impresion", fachadaOTExtrusion.getAlturaCara1Impresion());
        pars.put("p_alturaCara2Impresion", fachadaOTExtrusion.getAlturaCara2Impresion());


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
        pars.put("p_metroRefilado", fachadaOTExtrusion.getMetroRefilado());
        pars.put("p_pesoRolloRefilado", fachadaOTExtrusion.getPesoRolloRefilado());
        pars.put("p_tipoRefilado", fachadaOTExtrusion.getTipoRefilado());
        pars.put("p_tipoEmbobinadoRefilado", fachadaOTExtrusion.getTipoEmbobinadoRefilado());

        //
        colaboraOT.setIdOperacion(xIdOperacionRefilado);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaRefiladoUnOT_TipoEscala_2();

        //
        pars.put("p_maquinaRefilado", fachadaOTExtrusion.getMaquinaRefilado());
        pars.put("p_tipoRolloRefilado", fachadaOTExtrusion.getTipoRolloRefilado());
        pars.put("p_destinoRefilado", fachadaOTExtrusion.getDestinoRefilado());

        //
        fachadaOTExtrusion =
                colaboraOT.listaRefiladoUnOT_TipoEscala_3();

        //
        pars.put("p_observacionRefilado", fachadaOTExtrusion.getObservacionRefilado());

        //----------------------------------------------------------------------
        int xIdOperacionSellado = 5;

        //
        colaboraOT.setIdOperacion(xIdOperacionSellado);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaSelladoUnOT_TipoEscala_1();

        //
        pars.put("p_anchoSellado", fachadaOTExtrusion.getAnchoSellado());
        pars.put("p_largoSellado", fachadaOTExtrusion.getLargoSellado());
        pars.put("p_calibreSellado", fachadaOTExtrusion.getCalibreSellado());
        pars.put("p_solapaSellado", fachadaOTExtrusion.getSolapaSellado());
        pars.put("p_fuelle1Sellado", fachadaOTExtrusion.getFuelle1Sellado());
        pars.put("p_fuelle2Sellado", fachadaOTExtrusion.getFuelle2Sellado());
        pars.put("p_alturaSellado", fachadaOTExtrusion.getAlturaSellado());
        pars.put("p_golpeSellado", fachadaOTExtrusion.getGolpeSellado());
        pars.put("p_bultoSellado", fachadaOTExtrusion.getBultoSellado());
        pars.put("p_paqueteSellado", fachadaOTExtrusion.getPaqueteSellado());
        pars.put("p_solapaSellado", fachadaOTExtrusion.getSolapaSellado());

        //
        colaboraOT.setIdOperacion(xIdOperacionSellado);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaSelladoUnOT_TipoEscala_2();

        pars.put("p_maquinaSellado", fachadaOTExtrusion.getMaquinaSellado());
        pars.put("p_destinoSellado", fachadaOTExtrusion.getDestinoSellado());
        pars.put("p_tipoSellado", fachadaOTExtrusion.getTipoSellado());
        pars.put("p_tipoSolapaSellado", fachadaOTExtrusion.getTipoSolapaSellado());
        pars.put("p_tipoTroquelSellado", fachadaOTExtrusion.getTipoTroquelSellado());
        pars.put("p_destinoSellado", fachadaOTExtrusion.getDestinoSellado());
        pars.put("p_tipoFuelleSellado", fachadaOTExtrusion.getTipoFuelleSellado());

        //
        fachadaOTExtrusion =
                colaboraOT.listaSelladoUnOT_TipoEscala_3();

        //
        pars.put("p_observacionSellado", fachadaOTExtrusion.getObservacionSellado());

        //----------------------------------------------------------------------
        int xIdOperacionManualidad = 6;

        //
        colaboraOT.setIdOperacion(xIdOperacionManualidad);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        colaboraOT.setIdOperacion(xIdOperacionManualidad);
        colaboraOT.setNumeroOrden(fachadaPluFichaENC.getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaManualidadUnOT_TipoEscala_2();

        pars.put("p_maquinaManualidad", fachadaOTExtrusion.getMaquinaManualidad());
        pars.put("p_destinoManualidad", fachadaOTExtrusion.getDestinoManualidad());

        //
        fachadaOTExtrusion =
                colaboraOT.listaManualidadUnOT_TipoEscala_3();

        //
        pars.put("p_observacionManualidad", fachadaOTExtrusion.getObservacionManualidad());

        //
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

    public int getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(int ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public void setOrdenServicio(String ordenServicioStr) {
        this.ordenServicio = new Integer(ordenServicioStr).intValue();
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        this.fechaServicio = fechaServicio;
    }
}
