package com.solucionesweb.lasayudas;


// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoBean;

// Importa la clase que contiene el PagoBean
import com.solucionesweb.losbeans.negocio.PagoBean;

// Importa la clase que contiene el PagoReciboBean
import com.solucionesweb.losbeans.negocio.PagoMedioBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoPagoMostrador {

    // Metodo contructor por defecto, es decir, sin parametros
    public ProcesoPagoMostrador() { }

    //
    public void confirma(DctoBean dctoBean,
                         int xIdLog,
                         String xIdMedio) {

        //
        int xIdTipoOrden           = dctoBean.getIdTipoOrden();
        int xIdLocal               = dctoBean.getIdLocal();
        int xIndicador             = dctoBean.getIndicador();
        double xVrPago             = dctoBean.getVrPago();

        // Importa la clase que contiene el DctoBean
        PagoBean pagoBean          = new PagoBean();

        //
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrden);

        //
        int idMaximaPlanilla       = pagoBean.maximaPlanilla() + 1;

        //
        pagoBean.setIdTipoOrden(xIdTipoOrden);
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIndicador(xIndicador);

        //
        int xIdReciboMAX = pagoBean.maximoReciboIdLocalxIndicador() + 1;

        //
        pagoBean.setIdTipoOrden(xIdTipoOrden);
        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIndicador(xIndicador);
        pagoBean.setIdRecibo(xIdReciboMAX);
        pagoBean.setFechaPago(dctoBean.getFechaDcto());
        pagoBean.setVrPago(dctoBean.getVrPago());
        pagoBean.setNitCC(dctoBean.getIdCliente());
        pagoBean.setEstado(dctoBean.getEstado());
        pagoBean.setIdUsuario(dctoBean.getIdUsuario());
        pagoBean.setVrRteFuente(dctoBean.getVrRteFuente());
        pagoBean.setVrDescuento(dctoBean.getVrDescuento());
        pagoBean.setVrRteIva(dctoBean.getVrRteIva());
        pagoBean.setVrRteIca(dctoBean.getVrRteIca());
        pagoBean.setIdPeriodo("0");
        pagoBean.setIdDcto(dctoBean.getIdDcto());
        pagoBean.setIdDctoNitCC(dctoBean.getIdDctoDf0());
        pagoBean.setIdPlanilla(idMaximaPlanilla);
        pagoBean.setVrSaldo(0);
        pagoBean.setIdLog(xIdLog);
        pagoBean.setIdVendedor(dctoBean.getIdUsuario());
        pagoBean.setIdReciboCruce(0);
        pagoBean.setVrPagoCambio(0);

        //
        pagoBean.ingresaPago();

        //
        PagoMedioBean pagoMedioBean = new PagoMedioBean();

        //
        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrden);
        pagoMedioBean.setIdLog(xIdLog);
        pagoMedioBean.setIdRecibo(xIdReciboMAX);
        pagoMedioBean.setIndicador(xIndicador);
        pagoMedioBean.setIdMedio(xIdMedio);
        pagoMedioBean.setVrMedio(xVrPago);
        pagoMedioBean.setFechaCobro(dctoBean.getFechaDcto());
        pagoMedioBean.setIdBanco(0);
        pagoMedioBean.setIdDctoMedio("");
        pagoMedioBean.setEstado(dctoBean.getEstado());
        pagoMedioBean.setIdLog(xIdLog);

        //
        pagoMedioBean.ingresar();

    }
}