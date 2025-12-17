package com.solucionesweb.lasayudas;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

//
import org.kxml.parser.XmlParser;

// Importa la clase que contiene el ContableTerceroTx
import com.solucionesweb.losbeans.negocio.ContableTerceroTx;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el DctoContableTx
import com.solucionesweb.losbeans.negocio.DctoContableTx;

// Importa la clase que contiene el LocalIpBean
import com.solucionesweb.losbeans.negocio.LocalIpBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;

//
import com.solucionesweb.losbeans.negocio.TipoOrdenBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el ContableTerceroTx
import com.solucionesweb.losbeans.negocio.ContableMovimientoTx;


public class ProcesoImportaContable extends Thread {

    // xmlByteArray
    byte[] xmlByteArray = null;
    ByteArrayInputStream xmlStream = null;
    InputStreamReader xmlReader = null;
    // inicializamos las variables no gestionadas por el compilador NetBeans 3.6
    private URL destino = null;

    //
    public void guarda(int xIdLocal) throws IOException {

                //
                int xIdOrigenDcto = 1;
                int xIdOrigenPago = 2;

                //
                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                //
                LocalIpBean localIpBean       = new LocalIpBean();

                //
                localIpBean.setIdLocal(xIdLocal);

                //
                fachadaLocalIp = localIpBean.listaUnLocal();

                //
                String xHostName       = fachadaLocalIp.getIp();
                String xIdPuertoLocal  = ":" + fachadaLocalIp.getPuertoHttp();
                String xBdNameContable = fachadaLocalIp.getBdNameContable();
                int xIdLocalPadre      = fachadaLocalIp.getIdLocalPadre();

                //
                String xPagina = xHostName
                        + xIdPuertoLocal
                        + "/Commerce/jsp/"
                        + "txMovimientoLog.jsp"
                        + "?xIdLocal=" + xIdLocal;

                //
                String xmlString = enviar(xPagina);
                destino = null;

                // xmlString
                xmlByteArray = xmlString.getBytes();
                xmlStream = new ByteArrayInputStream(xmlByteArray);
                xmlReader = new InputStreamReader(xmlStream);
                XmlParser parser = new XmlParser(xmlReader);

                //
                ContableTerceroTx contableTerceroTx = new ContableTerceroTx();

                //
                DctoContableTx dctoContableTx = new DctoContableTx();

                //
                try {

                    //
                    FachadaTipoOrden fachadaTipoOrden = new FachadaTipoOrden();

                    //
                    TipoOrdenBean tipoOrdenBean = new TipoOrdenBean();

                    //
                    Vector vectorBean =
                            contableTerceroTx.traverse_TerceroTx(parser, 
                                                                 "",
                                                               xBdNameContable);

                    //
                    Iterator iteratorBean = vectorBean.iterator();

                    //
                    FachadaTerceroBean fachadaTerceroLog;

                    //
                    while (iteratorBean.hasNext()) {

                        //
                        boolean xOkIngreso = false;

                        //
                        fachadaTerceroLog =
                                (FachadaTerceroBean) iteratorBean.next();

                        //
                        tipoOrdenBean.setIdTipoOrden(
                                fachadaTerceroLog.getIdTipoOrden());

                        //
                        fachadaTipoOrden = tipoOrdenBean.listaUnFCH();


                        //
                        dctoContableTx.setIdLocal(
                                fachadaTerceroLog.getIdLocal());

                        //
                        dctoContableTx.retiraDcto();


                        //
                        int xVrAjuste = 0;

                        // xIdOrigenPago----------------------------------------
                        if (fachadaTerceroLog.getIdOrigenLog()
                                == xIdOrigenPago) {

                            //
                            destino = null;

                            //
                            switch (fachadaTerceroLog.getIdTipoOrden()) {

                                //
                                case 1:

                                    //
                                    destino = null;

                                    //
                                    xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLog_tipoOrden_Pago_1.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    //
                                    String xmlStringLog = enviar(xPagina);

                                    //
                                    byte[] xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                    ByteArrayInputStream xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                    InputStreamReader xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                    XmlParser parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    FachadaDctoBean fachadaLog;

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                    Vector vectorLog =
                                            dctoContableTx.traverse_DctoContableOrdenPago_1_Tx(
                                            parserLog, "");

                                    //
                                    Iterator iteratorLog = vectorLog.iterator();

                                    //
                                    boolean xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        //
                                        double xVrCxC  = fachadaLog.getVrCostoMV()   +
                                                         fachadaLog.getVrDescuento() +
                                                         fachadaLog.getVrRteFuente() ;

                                        // +
                                        String xNombreSubcuenta = "vrCxC";
                                        double xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCxC);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        double xVrPago =
                                                fachadaLog.getVrCostoMV();

                                        // +
                                               xNombreSubcuenta = "vrPago";
                                               xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrPago);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        double xVrDsctoFcro =
                                                    fachadaLog.getVrDescuento();
                                        xNombreSubcuenta = "vrDsctoFcro";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrDsctoFcro);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        double xVrRteFuente =
                                                    fachadaLog.getVrRteFuente();
                                        xNombreSubcuenta = "vrRteFuente";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrRteFuente);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                    }

                                    //
                                    break;

                                //
                                case 9:
                                case 29:

                                    //
                                    destino = null;

                                    //
                                    xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLog_tipoOrden_Pago_9.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    //
                                           xmlStringLog = enviar(xPagina);

                                    //
                                           xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                                         xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                                      xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                              parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                           vectorLog =
                                            dctoContableTx.traverse_DctoContableOrden_4_Tx(
                                            parserLog, "");

                                    //
                                             iteratorLog = vectorLog.iterator();

                                    //
                                            xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        //
                                        double xVrCxC  = fachadaLog.getVrCostoMV()   +
                                                         fachadaLog.getVrDescuento() +
                                                         fachadaLog.getVrRteFuente() ;

                                        // +
                                        String xNombreSubcuenta = "vrCxC";
                                        double xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCxC);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPrefijo(fachadaLog.getPrefijo());
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        double xVrPago =
                                                fachadaLog.getVrCostoMV();

                                        // +
                                               xNombreSubcuenta = "vrPago";
                                               xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrPago);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPrefijo(fachadaLog.getPrefijo());
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        double xVrDsctoFcro =
                                                    fachadaLog.getVrDescuento();
                                        xNombreSubcuenta = "vrDsctoFcro";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrDsctoFcro);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPrefijo(fachadaLog.getPrefijo());
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        double xVrRteFuente =
                                                    fachadaLog.getVrRteFuente();
                                        xNombreSubcuenta = "vrRteFuente";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrRteFuente);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPrefijo(fachadaLog.getPrefijo());
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                    }

                                    //
                                    break;

                                default:

                                    //
                                    break;

                            }
                        }

                        // xIdOrigenDcto----------------------------------------
                        if (fachadaTerceroLog.getIdOrigenLog()
                                == xIdOrigenDcto) {

                            //
                            destino = null;

                            //
                            switch (fachadaTerceroLog.getIdTipoOrden()) {

                                //
                                //
                                case 1:
                                case 21:
                                //
                                case 9:
                                case 29:

                                    //
                                    if ((fachadaTerceroLog.getIdTipoOrden() == 9) ||
                                       (fachadaTerceroLog.getIdTipoOrden() == 29)) {

                                       //
                                       xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLogTipoOrden_9_29.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    }

                                    //
                                    if ((fachadaTerceroLog.getIdTipoOrden() == 1) ||
                                       (fachadaTerceroLog.getIdTipoOrden() == 21)) {

                                    //
                                        xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLogTipoOrden_1_21.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    }

                                    //
                                    String xmlStringLog = enviar(xPagina);

                                    //
                                    byte[] xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                    ByteArrayInputStream xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                    InputStreamReader xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                    XmlParser parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    FachadaDctoBean fachadaLog;

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                    Vector vectorLog =
                                            dctoContableTx.traverse_DctoContableOrden_9_29_Tx(
                                            parserLog, "");

                                    //
                                    Iterator iteratorLog = vectorLog.iterator();

                                    //
                                    boolean xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        //
                                        if (!xAsiendo) {

                                            // +
                                            double xVrBase =
                                                    fachadaLog.getVrVentaSinIva();
                                            String xNombreSubcuenta = "vrBase";
                                            double xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrBase);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            double xVrIva =
                                                    fachadaLog.getVrIvaVenta();
                                            xNombreSubcuenta = "vrIva";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrIva);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                        }

                                        //
                                        if (xAsiendo) {

                                            //
                                            xAsiendo = false;

                                            //
                                            double xVrRteFuente = 0;
                                            double xVrFra = 0;
                                            String xNombreSubcuenta = "";

                                          //
                                            xVrFra =
                                                    fachadaLog.getVrBase()
                                                    + fachadaLog.getVrIva()
                                                    + fachadaLog.getVrImpoconsumo()
                                                    - (fachadaLog.getVrRteFuente()
                                                    + fachadaLog.getVrRteIva()
                                                    + fachadaLog.getVrRteIca());

                                            //
                                            if (fachadaLog.getIdTipoNegocio() == 1) {

                                                // +
                                                xNombreSubcuenta = "vrPago";

                                            } else {

                                                // +
                                                xNombreSubcuenta = "vrCxC";

                                            }

                                            //---
                                            double xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrFra);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //---
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            xVrRteFuente =
                                                    fachadaLog.getVrRteFuente();
                                            xNombreSubcuenta = "vrRteFuente";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrRteFuente);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            double xVrBase =
                                                    fachadaLog.getVrVentaSinIva();
                                            xNombreSubcuenta = "vrBase";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrBase);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            double xVrIva =
                                                    fachadaLog.getVrIvaVenta();
                                            xNombreSubcuenta = "vrIva";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrIva);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            double xVrInventario =
                                                    fachadaLog.getVrCostoMV();
                                            xNombreSubcuenta = "vrInventario";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrInventario);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            double xVrCostoV =
                                                    fachadaLog.getVrCostoMV();
                                            xNombreSubcuenta = "vrCostoMV";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrCostoV);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);
                                            dctoContableTx.setPrefijo(fachadaLog.getPrefijo());

                                            //
                                            dctoContableTx.ingresaDcto();

                                            // +
                                            double xVrImpoconsumo =
                                                    fachadaLog.getVrImpoconsumo();
                                            xNombreSubcuenta = "vrImpoconsumo";
                                            xPorcentajeIva =
                                                    fachadaLog.getPorcentajeIva();

                                            //
                                            dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                            dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                            dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                            dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                            dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                            dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                            dctoContableTx.setVrBase(xVrImpoconsumo);
                                            dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                            dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                            dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                            //
                                            dctoContableTx.ingresaDcto();   

                                        }

                                    }

                                    //
                                    xAsiendo = true;

                                    //
                                    xVrAjuste = dctoContableTx.valorAjuste();

                                    //
                                    break;

                                case 4:

                                    //
                                    destino = null;

                                    //
                                    xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLog_tipoOrden_4.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    //
                                    xmlStringLog = enviar(xPagina);

                                    //
                                    xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                    xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                    xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                    parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                    vectorLog =
                                            dctoContableTx.traverse_DctoContableOrden_4_Tx(
                                            parserLog, "");

                                    //
                                    iteratorLog = vectorLog.iterator();

                                    //
                                    xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        // +
                                        double xVrCostoMV =
                                                fachadaLog.getVrCostoMV();
                                        String xNombreSubcuenta = "vrCosto";
                                        double xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCostoMV);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        xVrCostoMV =
                                                fachadaLog.getVrCostoMV();
                                        xNombreSubcuenta = "vrInventario";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCostoMV);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                    }


                                    //
                                    xVrAjuste = 0;

                                    //
                                    break;

                                case 15:
                                case 16:

                                    //
                                    destino = null;

                                    //
                                    xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLog_tipoOrden_15_16.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    //
                                    xmlStringLog = enviar(xPagina);

                                    //
                                    xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                    xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                    xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                    parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                    vectorLog =
                                            dctoContableTx.traverse_DctoContableOrden_4_Tx(
                                            parserLog, "");

                                    //
                                    iteratorLog = vectorLog.iterator();

                                    //
                                    xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        // +
                                        double xVrCostoMV =
                                                fachadaLog.getVrCostoMV();
                                        String xNombreSubcuenta = "vrCosto";
                                        double xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCostoMV);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        xVrCostoMV =
                                                fachadaLog.getVrCostoMV();
                                        xNombreSubcuenta = "vrInventario";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCostoMV);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();


                                    }


                                    //
                                    xVrAjuste = 0;

                                    //
                                    break;

                                //
                                case 2:
                                case 3:
                                case 5:
                                case 10:
                                case 11:
                                case 12:

                                    //
                                    destino = null;

                                    //
                                    xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLog_tipoOrden_5.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    //
                                    xmlStringLog = enviar(xPagina);

                                    //
                                    xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                    xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                    xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                    parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                    vectorLog =
                                            dctoContableTx.traverse_DctoContableOrden_5_Tx(
                                            parserLog, "");

                                    //
                                    iteratorLog = vectorLog.iterator();

                                    //
                                    xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        // +
                                        double xVrCostoMV =
                                                fachadaLog.getVrCostoMV();
                                        String xNombreSubcuenta = "vrCosto";
                                        double xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCostoMV);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        xVrCostoMV =
                                                fachadaLog.getVrCostoMV();
                                        xNombreSubcuenta = "vrInventario";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrCostoMV);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();


                                    }


                                    //
                                    xVrAjuste = 0;

                                    //
                                    break;

                                //
                                case 401:
                                case 402:
                                case 403:
                                case 404:
                                case 405:
                                case 406:
                                case 407:
                                case 408:
                                case 409:
                                case 410:
                                case 411:
                                case 412:
                                case 413:
                                case 414:
                                case 415:
                                case 416:
                                case 417:
                                case 418:
                                case 419:
                                case 420:
                                case 421:
                                case 422:
                                case 423:
                                case 424:
                                case 425:
                                case 426:
                                case 427:
                                case 428:
                                case 429:
                                case 430:
                                case 501:
                                case 502:
                                case 503:
                                case 504:
                                case 505:
                                case 506:
                                case 507:
                                case 508:
                                case 509:
                                case 510:
                                case 511:
                                case 512:
                                case 513:
                                case 514:
                                case 515:
                                case 516:
                                case 517:
                                case 518:
                                case 519:
                                case 520:
                                case 521:
                                case 522:
                                case 523:
                                case 524:
                                case 525:
                                case 526:
                                case 527:
                                case 528:
                                case 529:
                                case 530:
                                case 531:
                                case 532:
                                case 533:
                                case 534:
                                case 535:
                                case 536:
                                case 537:
                                case 538:
                                case 539:
                                case 540:
                                case 541:
                                case 542:
                                case 543:
                                case 544:
                                case 545:
                                case 546:
                                case 547:
                                case 548:
                                case 549:
                                case 550:
                                case 551:
                                case 552:
                                case 553:
                                case 554:
                                case 555:
                                case 556:
                                case 557:
                                case 558:
                                case 559:
                                case 560:
                                case 561:
                                case 562:
                                case 563:
                                case 564:
                                case 565:
                                case 566:
                                case 567:
                                case 568:
                                case 569:
                                case 570:

                                    //
                                    destino = null;

                                    //
                                    xPagina = xHostName
                                            + xIdPuertoLocal
                                            + "/Commerce/jsp/"
                                            + "txMovimientoUnLog_tipoOrden_500.jsp"
                                            + "?xIdLocal="
                                            + fachadaTerceroLog.getIdLocalStr()
                                            + "&xIdTipoOrden="
                                            + fachadaTerceroLog.getIdTipoOrdenStr()
                                            + "&xIdLog="
                                            + fachadaTerceroLog.getIdLogStr();

                                    //
                                    xmlStringLog = enviar(xPagina);

                                    //
                                    xmlByteArrayLog = xmlStringLog.getBytes();

                                    //
                                    xmlStreamLog =
                                            new ByteArrayInputStream(xmlByteArrayLog);

                                    //
                                    xmlReaderLog =
                                            new InputStreamReader(xmlStreamLog);

                                    //
                                    parserLog =
                                            new XmlParser(xmlReaderLog);

                                    //
                                    dctoContableTx.setIdLocal(
                                            fachadaTerceroLog.getIdLocal());

                                    //
                                    vectorLog =
                                            dctoContableTx.traverse_DctoContableOrden_500_Tx(
                                            parserLog, "");

                                    //
                                    iteratorLog = vectorLog.iterator();

                                    //
                                    xAsiendo = true;

                                    //
                                    while (iteratorLog.hasNext()) {

                                        //
                                        fachadaLog =
                                                (FachadaDctoBean) iteratorLog.next();

                                        // +
                                        double xVrBase =
                                                fachadaLog.getVrBase();
                                        String xNombreSubcuenta = "vrBase";
                                        double xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrBase);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                        // +
                                        xVrBase =
                                                fachadaLog.getVrBase();
                                        xNombreSubcuenta = "vrPago";
                                        xPorcentajeIva =
                                                fachadaLog.getPorcentajeIva();

                                        //
                                        dctoContableTx.setIdLocal(fachadaLog.getIdLocal());
                                        dctoContableTx.setIdTipoOrden(fachadaLog.getIdTipoOrden());
                                        dctoContableTx.setIdDcto(fachadaLog.getIdDcto());
                                        dctoContableTx.setIdDctoNitCC(fachadaLog.getIdDctoNitCC());
                                        dctoContableTx.setIdCliente(fachadaLog.getIdCliente());
                                        dctoContableTx.setFechaDcto(fachadaLog.getFechaDcto());
                                        dctoContableTx.setVrBase(xVrBase);
                                        dctoContableTx.setNombreTercero(fachadaLog.getNombreTercero());
                                        dctoContableTx.setNombreSubcuenta(xNombreSubcuenta);
                                        dctoContableTx.setPorcentajeIva(xPorcentajeIva);

                                        //
                                        dctoContableTx.ingresaDcto();

                                    }

                                    //
                                    xVrAjuste = 0;

                                    //
                                    break;

                                default:

                                    //
                                    break;

                            }

                        }

                        //
                        double xCeroDouble = 0.0;
                        int xCeroInt = 0;

                        //
                        ContableMovimientoTx contableMovimientoTx
                                                   = new ContableMovimientoTx();

                        //
                        FachadaDctoBean fachadaDctoMov = new FachadaDctoBean();

                        //
                        Vector vectorMov  = new Vector();

                        //
                        if (fachadaTerceroLog.getIdOrigenLog()
                                == xIdOrigenPago) {

                                //
                                vectorMov = dctoContableTx.listaSubcuentaUnLogPago(
                                xVrAjuste,
                                fachadaTipoOrden.getNombreTipoOrdenMayuscula());

                        }

                        //
                        if (fachadaTerceroLog.getIdOrigenLog()
                                == xIdOrigenDcto) {

                                //
                                vectorMov = dctoContableTx.listaSubcuentaUnLog(
                                xVrAjuste,
                                fachadaTipoOrden.getNombreTipoOrdenMayuscula());

                        }

                        //
                        Iterator iteratorMov = vectorMov.iterator();

                        //
                        while (iteratorMov.hasNext()) {

                            //
                            fachadaDctoMov =
                                    (FachadaDctoBean) iteratorMov.next();

                            //
                            contableMovimientoTx.setIdLocal(
                                    fachadaDctoMov.getIdLocal());
                            contableMovimientoTx.setIdTipoOrden(
                                    fachadaDctoMov.getIdTipoOrden());
                            contableMovimientoTx.setIdDcto(
                                    fachadaDctoMov.getIdDcto());
                            contableMovimientoTx.setIdDctoNitCC(
                                    fachadaDctoMov.getIdDctoNitCC());
                            contableMovimientoTx.setIdCliente(
                                    fachadaDctoMov.getIdCliente());
                            contableMovimientoTx.setFechaDcto(
                                    fachadaDctoMov.getFechaDcto());
                            contableMovimientoTx.setItemMovimiento(
                                            fachadaDctoMov.getItemMovimiento());
                            contableMovimientoTx.setVrBase(
                                                    fachadaDctoMov.getVrBase());
                            contableMovimientoTx.setNombreTercero(
                                             fachadaDctoMov.getNombreTercero());
                            contableMovimientoTx.setIdSubcuenta(
                                               fachadaDctoMov.getIdSubcuenta());
                            contableMovimientoTx.setIdComprobanteContable(
                                     fachadaDctoMov.getIdComprobanteContable());
                            contableMovimientoTx.setNombreAsiento(
                                             fachadaDctoMov.getNombreAsiento());
                            contableMovimientoTx.setVrSaldo(xCeroDouble);
                            contableMovimientoTx.setNumeroDctos(xCeroInt);
                            contableMovimientoTx.setComentarioMovimiento(
                                      fachadaDctoMov.getComentarioMovimiento());
                            contableMovimientoTx.setPrefijo(
                                                   fachadaDctoMov.getPrefijo());
                            contableMovimientoTx.setVrBaseContable(
                                            fachadaDctoMov.getVrBaseContable());

                            //
                            if ((fachadaDctoMov.getIdTipoOrden() == 9 ) &&
                                    (fachadaTerceroLog.getIdOrigenLog()
                                                         == xIdOrigenDcto)) {

                              //
                              xOkIngreso  =
                               contableMovimientoTx.ingresaPrefijo_GTERCEROS(
                                                               xBdNameContable);


                            } else {
                                
                                   // no compra
                                   if (fachadaDctoMov.getIdTipoOrden() == 1) {


                                      // invierte idDcto y idDctoNitCC
                                      xOkIngreso  =
                                      contableMovimientoTx.ingresa_GTERCEROS_1(
                                                               xBdNameContable);

                                   } else {

                                      // 2, 3, 4, 5, 7, , 400, 500
                                      xOkIngreso  =
                                      contableMovimientoTx.ingresa_GTERCEROS(
                                                               xBdNameContable);
 
                                   }
                            }
                        }

                        // xOkIngreso
                        if (xOkIngreso) {

                            //
                            destino = null;

                            //
                            xPagina = xHostName
                                    + xIdPuertoLocal
                                    + "/Commerce/jsp/"
                                    + "txMovimientoActualizaLog.jsp"
                                    + "?xIdLocal="
                                    + fachadaTerceroLog.getIdLocalStr()
                                    + "&xIdTipoOrden="
                                    + fachadaTerceroLog.getIdTipoOrdenStr()
                                    + "&xIdLog="
                                    + fachadaTerceroLog.getIdLogStr();

                            //
                            enviar(xPagina);

                        } else {

                            //
                            destino = null;

                            // LogError
                            xPagina = xHostName
                                    + xIdPuertoLocal
                                    + "/Commerce/jsp/"
                                    + "txMovimientoActualizaLogError.jsp"
                                    + "?xIdLocal="
                                    + fachadaTerceroLog.getIdLocalStr()
                                    + "&xIdTipoOrden="
                                    + fachadaTerceroLog.getIdTipoOrdenStr()
                                    + "&xIdLog="
                                    + fachadaTerceroLog.getIdLogStr();

                            //
                            enviar(xPagina);

                        }

                        // UN CLICLO
                        break;

                    }

                } catch (Exception ex) {
                    Logger.getLogger(ProcesoImportaContable.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    // la tarea a ejecutar es independiente del interfaz de usuario
    public String enviar(String xPagina) {

        try {
            // inicializamos el destino si es necesario
            if (destino == null) {

                String SERVLET_URL = "http://";

                String url = SERVLET_URL + xPagina;

                System.out.println(" url " + url);

                destino = new URL(url);
            }

            // abrimos la conexión al servidor
            HttpURLConnection enlace = (HttpURLConnection) destino.openConnection();

            //
            String contenido = recuperaContenido(enlace);

            //
            enlace.disconnect();
            return contenido;

        } catch (Exception e) {
            return "Error " + e.getMessage() + "\n";
        }

    }

    // esta función recupera el texto de la página
    String recuperaContenido(HttpURLConnection enlace) {
        try {
            String respuesta = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(enlace.getInputStream()));

            String cadena = "";

            while (cadena != null) {
                cadena = in.readLine();
                if (cadena != null) {
                    respuesta += ("\n" + cadena);
                }
            }

            in.close();
            return respuesta;

        } catch (Exception e) {
            return "Error " + e.getMessage() + "\n";
        }
    }
}
