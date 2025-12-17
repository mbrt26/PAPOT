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
public class ProcesoPagoContado {

    // Metodo contructor por defecto, es decir, sin parametros
    public ProcesoPagoContado() {
    }

    public void confirma(String xIdDcto,
            String xIdTipoOrdenCotizacion,
            String xIdTipoOrden,
            String xIdLocal,
            String xIdLogActual,
            String xIdVendedor,
            double xVrPago) {

        //
        int xIdTipoOrdenProceso    =
                            new Integer(xIdTipoOrdenCotizacion).intValue() + 50;

        // Importa la clase que contiene el DctoBean
        PagoBean pagoBean          = new PagoBean();

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrden);

        //
        int idMaximaPlanilla       = pagoBean.maximaPlanilla() + 1;

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrdenProceso);
        pagoBean.setIdLog(xIdLogActual);

        //
        Vector vecPagoTmp = pagoBean.listaPagoProceso();

        //
        Iterator iteratorBean = vecPagoTmp.iterator();

        //
        FachadaPagoBean fachadaPagoBean;

        boolean okPago = false;

        //
        while (iteratorBean.hasNext()) {

            //
            fachadaPagoBean = (FachadaPagoBean) iteratorBean.next();

            //
            int xIdReciboOld = fachadaPagoBean.getIdRecibo();
            int xIndicador   = fachadaPagoBean.getIndicador();

            //
            pagoBean.setIdTipoOrden(xIdTipoOrden);
            pagoBean.setIdLocal(xIdLocal);
            pagoBean.setIndicador(xIndicador);

            //
            int xIdReciboMAX =
                    pagoBean.maximoReciboIdLocalxIndicador() + 1;

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
            pagoBean.setIdPlanilla(idMaximaPlanilla);
            pagoBean.setIdLog(xIdLogActual);
            pagoBean.setIdDcto(fachadaPagoBean.getIdDcto());
            pagoBean.setIdTipoOrden(xIdTipoOrden);
            pagoBean.setIdLocal(xIdLocal);
            pagoBean.setIndicador(fachadaPagoBean.getIndicador());
            pagoBean.setIdRecibo(xIdReciboMAX);
            pagoBean.setVrSaldo(xVSaldoDctoActualizado);
            pagoBean.setIdVendedor(xIdVendedor);

            //
            pagoBean.setIdLocal(xIdLocal);
            pagoBean.setIdTipoOrden(xIdTipoOrdenProceso);
            pagoBean.setIdLog(xIdLogActual);
            pagoBean.setIdRecibo(xIdReciboOld);

            //
            pagoBean.ingresaPagoLog(new Integer(xIdTipoOrden).intValue(),
                    xIdReciboMAX,
                    xIndicador,
                    fachadaPagoBean.getIdDcto(),
                    xIdVendedor,
                    fachadaPagoBean.getVrPago(),
                    idMaximaPlanilla);

            //
            PagoMedioBean pagoMedioBean = new PagoMedioBean();

            //
            pagoMedioBean.setIdLocal(xIdLocal);
            pagoMedioBean.setIdTipoOrden(xIdTipoOrdenProceso);
            pagoMedioBean.setIdLog(xIdLogActual);
            pagoMedioBean.setIdRecibo(xIdReciboOld);

            //
            pagoMedioBean.ingresaPagoLog(new Integer(xIdTipoOrden).intValue(),
                    xIdReciboMAX,
                    xIndicador);

            //
            FachadaPagoMedioBean fachadaPagoMedioBean
                                     = new FachadaPagoMedioBean();

            //
            pagoMedioBean.setIdLocal(xIdLocal);
            pagoMedioBean.setIdLog(xIdLogActual);
            pagoMedioBean.setIdTipoOrden(xIdTipoOrden);

            //
            fachadaPagoMedioBean     = pagoMedioBean.liquidaPagoLogFCH();

            double xVrMedio          = fachadaPagoMedioBean.getVrMedio();
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
            pagoMedioBean.setIdRecibo(xIdReciboOld);
            pagoMedioBean.setIdTipoOrden(xIdTipoOrdenProceso);
            pagoMedioBean.setIndicador(xIndicador);
            pagoMedioBean.setIdLocal(xIdLocal);

            //
            pagoMedioBean.retiraReciboTemporal();

            //
            pagoBean.setIdRecibo(xIdReciboOld);
            pagoBean.setIdTipoOrden(xIdTipoOrdenProceso);
            pagoMedioBean.setIndicador(xIndicador);
            pagoMedioBean.setIdLocal(xIdLocal);

            //
            pagoBean.retiraReciboTemporal();


        }

   }
}