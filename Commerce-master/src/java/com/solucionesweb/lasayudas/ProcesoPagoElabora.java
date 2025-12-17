package com.solucionesweb.lasayudas;

//
import java.util.Iterator;
import java.util.Vector;

// Importa la clase que contiene el PagoBean
import com.solucionesweb.losbeans.negocio.PagoBean;

// Importa la clase que contiene el PagoReciboBean
import com.solucionesweb.losbeans.negocio.PagoMedioBean;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

import com.solucionesweb.losbeans.fachada.FachadaPagoMedioBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoPagoElabora {

    // Metodo contructor por defecto, es decir, sin parametros
    public ProcesoPagoElabora() {
    }

    public void confirma(String xIdDcto,
            String xIdTipoOrdenCotizacion,
            String xIdTipoOrden,
            String xIdLocal,
            String xIdLogActual,
            String xIdVendedor,
            double xVrPago,
            int xIndicador) {

        //
        int xIdTipoOrdenProceso    =
                            new Integer(xIdTipoOrdenCotizacion).intValue();

        // Importa la clase que contiene el DctoBean
        PagoBean pagoBean          = new PagoBean();

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrden);

        //
        int idMaximaPlanilla        = pagoBean.maximaPlanilla() + 1;

        //
        FachadaPagoBean fachadaPagoBean
                                    = new FachadaPagoBean();

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoBean.setIdLog(xIdLogActual);

        //
        fachadaPagoBean             = pagoBean.listaUnPagoFCH();

        //
        FachadaPagoMedioBean fachadaPagoMedioBean
                                    = new FachadaPagoMedioBean();

        //
        PagoMedioBean pagoMedioBean = new PagoMedioBean();

        //
        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoMedioBean.setIdLog(xIdLogActual);

        //
        fachadaPagoMedioBean        = pagoMedioBean.liquidaPagoLogFCH();

        //
        double xVrMedio             = fachadaPagoMedioBean.getVrMedio();

        //
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

        //
        colaboraDctoBean.setIdLocal(fachadaPagoBean.getIdLocal());
        colaboraDctoBean.setIdCliente(fachadaPagoBean.getNitCC());
        colaboraDctoBean.setIdTipoOrden(xIdTipoOrden);
        colaboraDctoBean.setIdDcto(fachadaPagoBean.getIdDcto());

        //
        FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

        //
        fachadaDctoPagoBean =
                    colaboraDctoBean.listaCuentaDetalladoClienteFCH();

        //
        double xVSaldoDctoActualizado =
                    fachadaDctoPagoBean.getVrSaldo();

        //
        pagoBean.setIdTipoOrden(xIdTipoOrden);
        pagoBean.setIdLocal(fachadaPagoBean.getIdLocal());
        pagoBean.setIndicador(xIndicador);

        //
        int xIdReciboMAX      =
                    pagoBean.maximoReciboIdLocalxIndicador() + 1;

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoBean.setIdLog(xIdLogActual);

        //
        pagoBean.setIdPlanilla(idMaximaPlanilla);
        pagoBean.setIdDcto(fachadaPagoBean.getIdDcto());
        pagoBean.setIndicador(xIndicador);
        pagoBean.setIdRecibo(xIdReciboMAX);
        pagoBean.setVrSaldo(xVSaldoDctoActualizado);
        pagoBean.setIdVendedor(xIdVendedor);

        //
       /* pagoBean.ingresaPagoInsertLog(new Integer(xIdTipoOrden).intValue(),
                                xIdReciboMAX,
                                xIndicador,
                                new Integer(xIdDcto).intValue(),
                                xIdVendedor,
                                fachadaPagoBean.getVrPago(),
                                idMaximaPlanilla);
*/
        pagoBean.ingresaPagoInsertLog(new Integer(xIdTipoOrden).intValue(),
                                xIdReciboMAX,
                                xIndicador,
                                xIdDcto,
                                xIdVendedor,
                                fachadaPagoBean.getVrPago(),
                                idMaximaPlanilla);



        //
        double xVrPagoCambio     = 0;

        //
        if (xVrPago<xVrMedio) {

           //
           xVrPagoCambio = xVrMedio - xVrPago;

        }

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrden);
        pagoBean.setIdRecibo(xIdReciboMAX);
        pagoBean.setIndicador(xIndicador);
        pagoBean.setIdPlanilla(idMaximaPlanilla);
        pagoBean.setVrPago(xVrPago);
        pagoBean.setVrPagoCambio(xVrPagoCambio);

        //
        pagoBean.actualizaPagoCambio();

        //
        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoMedioBean.setIdLog(xIdLogActual);

        //
        Vector vecPagoTmp     = pagoMedioBean.listaPagoProceso();

        //
        Iterator iteratorBean = vecPagoTmp.iterator();

        //
        while (iteratorBean.hasNext()) {

            //
            fachadaPagoMedioBean = (FachadaPagoMedioBean) iteratorBean.next();

            //
            int xIdReciboOld     = fachadaPagoMedioBean.getIdRecibo();

            //
            pagoMedioBean.setIdLocal(xIdLocal);
            pagoMedioBean.setIdTipoOrden(xIdTipoOrdenProceso);
            pagoMedioBean.setIdLog(xIdLogActual);
            pagoMedioBean.setIdRecibo(xIdReciboOld);

            //
            pagoMedioBean.ingresaPagoLog(new Integer(xIdTipoOrden).intValue(),
                    xIdReciboMAX,
                    xIndicador);

        }

        //
        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoMedioBean.setIdLog(xIdLogActual);

        //
        pagoMedioBean.retiraPagoTemporalLog();

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoBean.setIdLog(xIdLogActual);

        //
        pagoBean.retiraPagoTemporalLog();

    }
}