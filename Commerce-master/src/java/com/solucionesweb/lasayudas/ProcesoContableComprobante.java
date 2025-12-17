package com.solucionesweb.lasayudas;

import com.solucionesweb.losbeans.negocio.DctoContableBean;

import com.solucionesweb.losbeans.negocio.LocalIpBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalIp;

import com.solucionesweb.losbeans.negocio.ContableTerceroTx;

import com.solucionesweb.losbeans.negocio.ContableMovimientoTx;

import java.util.Iterator;
import java.util.Vector;

public class ProcesoContableComprobante {

    //
    private int idLocal;
    private int indicadorInicial;
    private int indicadorFinal;
    private int idComprobante;
    private String  fechaInicial;
    private String fechaFinal;
    private String  bdNameContable;

    //
    public void validaComprobante() {

        //
        DctoContableBean dctoContableBean = new DctoContableBean();

        //---------------------------------------------------------------
        dctoContableBean.borraTmpDctosContable();

        //
        dctoContableBean.borraTmpDctosContableDetalle();

        //
        int xIdTipoOrdenINI = 0;
        int xIdTipoOrdenFIN = 0;
        int xIdTipoTercero  = 0;

        switch (getIdComprobante()) {

            case 4:

                //
                xIdTipoOrdenINI = 9;
                xIdTipoOrdenFIN = 29;
                xIdTipoTercero  = 1;

                //
                dctoContableBean.setIdLocal(getIdLocal());
                dctoContableBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
                dctoContableBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                dctoContableBean.setIndicadorInicial(getIndicadorInicial());
                dctoContableBean.setIndicadorFinal(getIndicadorFinal());
                dctoContableBean.setFechaInicialStr(getFechaInicial());
                dctoContableBean.setFechaFinalStr(getFechaFinal());

               //---------------------------------------------------------------

               dctoContableBean.ingresaTmpDctosContableVenta(xIdTipoTercero);

                //
                dctoContableBean.ingresaTmpDctosContableDetalle(
                        new Integer(getIdComprobante()).intValue());

                //
                dctoContableBean.borraTmpDctosContablePago();

                //
                dctoContableBean.borraTmpDctosContableCxC();

                //
                dctoContableBean.borraTmpDctosContableImpoconsumo();

                //
                dctoContableBean.borraTmpDctosContableRteFuente();

                //
                dctoContableBean.borraTmpDctosContableIva();

                //
                dctoContableBean.actualizaTmpDctosContableVrMovimiento();

                //
                dctoContableBean.actualizaTmpDctosContableVrImpoconsumo();

                //
                dctoContableBean.actualizaTmpVrRteFuente();

                //
                dctoContableBean.actualizaTmpDctosContableInventario();

                //
                dctoContableBean.actualizaTmpDctosContableIva();

                //
                dctoContableBean.actualizaTmpDctosContableBase();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaDebito();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaCredito();

                break;

            case 2:

                //
                xIdTipoOrdenINI = 1;
                xIdTipoOrdenFIN = 21;
                xIdTipoTercero  = 2;

                //
                dctoContableBean.setIdLocal(getIdLocal());
                dctoContableBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
                dctoContableBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                dctoContableBean.setIndicadorInicial(getIndicadorInicial());
                dctoContableBean.setIndicadorFinal(getIndicadorFinal());
                dctoContableBean.setFechaInicialStr(getFechaInicial());
                dctoContableBean.setFechaFinalStr(getFechaFinal());

                //---------------------------------------------------------------
                dctoContableBean.ingresaTmpDctosContableCompra(xIdTipoTercero);

                //
                dctoContableBean.ingresaTmpDctosContableDetalle(
                        new Integer(getIdComprobante()).intValue());

                //
                dctoContableBean.borraTmpDctosContablePago();

                //
                dctoContableBean.borraTmpDctosContableCxC();

                //
                dctoContableBean.borraTmpDctosContableImpoconsumo();

                //
                dctoContableBean.borraTmpDctosContableRteFuente();

                //
                dctoContableBean.borraTmpDctosContableIva();

                //
                dctoContableBean.actualizaTmpDctosContableVrMovimiento();

                //
                dctoContableBean.actualizaTmpDctosContableVrImpoconsumo();

                //
                dctoContableBean.actualizaTmpVrRteFuente();

                //
                dctoContableBean.actualizaTmpDctosContableInventarioCompra();

                //
                dctoContableBean.actualizaTmpDctosContableIva();

                //
                dctoContableBean.actualizaTmpDctosContableBase();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaDebito();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaCredito();

                break;

            case 12:

                //
                xIdTipoOrdenINI = 9;
                xIdTipoOrdenFIN = 29;
                xIdTipoTercero  = 1;

                //
                dctoContableBean.setIdLocal(getIdLocal());
                dctoContableBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
                dctoContableBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                dctoContableBean.setIndicadorInicial(getIndicadorInicial());
                dctoContableBean.setIndicadorFinal(getIndicadorFinal());
                dctoContableBean.setFechaInicialStr(getFechaInicial());
                dctoContableBean.setFechaFinalStr(getFechaFinal());

                //---------------------------------------------------------------
                dctoContableBean.ingresaTmpDctosContablePago(xIdTipoTercero);

                //
                dctoContableBean.ingresaTmpDctosContableDetallePago_12(
                        new Integer(getIdComprobante()).intValue());

                //
                dctoContableBean.borraTmpDctosContableRteFuente();

                //
                dctoContableBean.borraTmpDctosContableDsctoFcro();

                //
                dctoContableBean.actualizaTmpDctosContableVrMovimiento();

                //
                dctoContableBean.actualizaTmpDescuento();

                //
                dctoContableBean.actualizaTmpVrRteFuente();

                //
                dctoContableBean.actualizaTmpVrRteIva();

                //
                dctoContableBean.actualizaTmpVrRteIca();

                //
                dctoContableBean.actualizaTmpMovimientoDescuentoRteFuenteRteIvaRteIca();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaDebito();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaCredito();

                //
                dctoContableBean.borraTmpDctosContableVrCero();

                break;

            case 13:

                //
                xIdTipoOrdenINI = 1;
                xIdTipoOrdenFIN = 21;
                xIdTipoTercero  = 2;

                //
                dctoContableBean.setIdLocal(getIdLocal());
                dctoContableBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
                dctoContableBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                dctoContableBean.setIndicadorInicial(getIndicadorInicial());
                dctoContableBean.setIndicadorFinal(getIndicadorFinal());
                dctoContableBean.setFechaInicialStr(getFechaInicial());
                dctoContableBean.setFechaFinalStr(getFechaFinal());

                //---------------------------------------------------------------
                dctoContableBean.ingresaTmpDctosContablePago(xIdTipoTercero);

                //
                dctoContableBean.ingresaTmpDctosContableDetallePago_13(
                        new Integer(getIdComprobante()).intValue());

                //
                dctoContableBean.borraTmpDctosContableRteFuente();

                //
                dctoContableBean.borraTmpDctosContableDsctoFcro();

                //
                dctoContableBean.actualizaTmpDctosContableVrMovimiento();

                //
                dctoContableBean.actualizaTmpDescuento();

                //
                dctoContableBean.actualizaTmpVrRteFuente();

                //
                dctoContableBean.actualizaTmpVrRteIva();

                //
                dctoContableBean.actualizaTmpVrRteIca();

                //
                dctoContableBean.actualizaTmpMovimientoDescuentoRteFuenteRteIvaRteIca();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaDebito();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaCredito();

                //
                dctoContableBean.borraTmpDctosContableVrCero();

                break;

            case 5:

                //
                xIdTipoOrdenINI = 400;
                xIdTipoOrdenFIN = 999;
                xIdTipoTercero  = 0;

                //
                dctoContableBean.setIdLocal(getIdLocal());
                dctoContableBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
                dctoContableBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                dctoContableBean.setIndicadorInicial(getIndicadorInicial());
                dctoContableBean.setIndicadorFinal(getIndicadorFinal());
                dctoContableBean.setFechaInicialStr(getFechaInicial());
                dctoContableBean.setFechaFinalStr(getFechaFinal());

                //---------------------------------------------------------------
                dctoContableBean.ingresaTmpDctosContableEgresoIngreso();

                //
                dctoContableBean.ingresaTmpDctosContableDetalleInventario(
                        new Integer(getIdComprobante()).intValue());

                //
                dctoContableBean.actualizaTmpDctosContableEgresoIngresoVrMovimiento();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaDebito();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaCredito();


                break;

            case 6:

                //
                xIdTipoOrdenINI = 0;
                xIdTipoOrdenFIN = 0;
                xIdTipoTercero  = 0;

                //
                dctoContableBean.setIdLocal(getIdLocal());
                dctoContableBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
                dctoContableBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                dctoContableBean.setIndicadorInicial(getIndicadorInicial());
                dctoContableBean.setIndicadorFinal(getIndicadorFinal());
                dctoContableBean.setFechaInicialStr(getFechaInicial());
                dctoContableBean.setFechaFinalStr(getFechaFinal());

                //---------------------------------------------------------------
                dctoContableBean.ingresaTmpDctosContableInventario();

                //
                dctoContableBean.ingresaTmpDctosContableDetalleInventario(
                        new Integer(getIdComprobante()).intValue());

                //
                dctoContableBean.borraTmpDctosContableAjusteNegativo();

                //
                dctoContableBean.borraTmpDctosContableAjustePositivo();

                //
                dctoContableBean.actualizaTmpDctosContableInventarioVrMovimiento();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaDebito();

                //
                dctoContableBean.actualizaTmpDctosNaturalezaCredito();
                
            default:

        };
    }

    //
    public void ingresaDctoComprobante() {


        //----------------------------------------------------------------------
        FachadaLocalIp fachadaLocalIp     = new FachadaLocalIp();

        //
        LocalIpBean localIpBean           = new LocalIpBean();

        //
        localIpBean.setIdLocal(getIdLocal());

        //
        fachadaLocalIp                    = localIpBean.listaUnLocal();

        //
        String xBdNameContable            = fachadaLocalIp.getBdNameContable();

        //
        ContableMovimientoTx contableMovimientoTx
                                          = new ContableMovimientoTx();

        //
        DctoContableBean dctoContableBean = new DctoContableBean();
        
        //
        Vector vecDcto                    =    dctoContableBean.listaDcto();

        //
        FachadaDctoBean fachadaDctoBean;

        // Iterador de objetos
        Iterator iterDcto                 = vecDcto.iterator();

        //
        while (iterDcto.hasNext()) {

            //
            fachadaDctoBean = (FachadaDctoBean)iterDcto.next();

            contableMovimientoTx.setFechaDcto(fachadaDctoBean.getFechaDcto());
            contableMovimientoTx.setIdLocal(fachadaDctoBean.getIdLocalStr());
            contableMovimientoTx.setIdTipoOrden(
                                           fachadaDctoBean.getIdTipoOrdenStr());
            contableMovimientoTx.setIdComprobanteContable(
                                    fachadaDctoBean.getIdComprobanteContable());
            contableMovimientoTx.setPrefijo(fachadaDctoBean.getPrefijo());
            contableMovimientoTx.setIdDcto(fachadaDctoBean.getIdDcto());
            contableMovimientoTx.setIdDctoNitCC(
                                              fachadaDctoBean.getIdDctoNitCC());
            contableMovimientoTx.setItemMovimiento(
                                           fachadaDctoBean.getItemMovimiento());
            contableMovimientoTx.setIdSubcuenta(
                    fachadaDctoBean.getIdSubcuenta());
            contableMovimientoTx.setIdCliente(fachadaDctoBean.getIdCliente());
            contableMovimientoTx.setVrBaseContable(
                                           fachadaDctoBean.getVrBaseContable());
            contableMovimientoTx.setVrBase(fachadaDctoBean.getVrBase());
            contableMovimientoTx.setVrTotal(fachadaDctoBean.getVrTotal());
            contableMovimientoTx.setNombreAsiento(
                                            fachadaDctoBean.getNombreAsiento());
            contableMovimientoTx.setComentarioMovimiento(
                                     fachadaDctoBean.getComentarioMovimiento());

            //-- Compras / Pagos Compras
            if ((getIdComprobante() == 2) ||
                (getIdComprobante() == 13))  {

               //
               contableMovimientoTx.ingresa_GTERCEROS_1(xBdNameContable);

            } else {

               if (getIdComprobante() == 12) {

                                    //
                 contableMovimientoTx.ingresaPrefijo_GTERCEROS_12(xBdNameContable);


               } else {

                 //
                 contableMovimientoTx.ingresaPrefijo_GTERCEROS(xBdNameContable);

               }
            }
        }

    }

    //
    public void ingresaDctoComprobantePago() {


        //----------------------------------------------------------------------
        FachadaLocalIp fachadaLocalIp     = new FachadaLocalIp();

        //
        LocalIpBean localIpBean           = new LocalIpBean();

        //
        localIpBean.setIdLocal(getIdLocal());

        //
        fachadaLocalIp                    = localIpBean.listaUnLocal();

        //
        String xBdNameContable            = fachadaLocalIp.getBdNameContable();

        //
        ContableMovimientoTx contableMovimientoTx = new ContableMovimientoTx();

        //
        DctoContableBean dctoContableBean = new DctoContableBean();

        //
        Vector vecDcto                    =    dctoContableBean.listaDctoPago();

        FachadaDctoBean fachadaDctoBean;

        // Iterador de objetos
        Iterator iterDcto                 = vecDcto.iterator();

        //
        while (iterDcto.hasNext()) {

            //
            fachadaDctoBean = (FachadaDctoBean)iterDcto.next();

            //
            contableMovimientoTx.setFechaDcto(fachadaDctoBean.getFechaDcto());
            contableMovimientoTx.setIdComprobanteContable(
                                    fachadaDctoBean.getIdComprobanteContable());
            contableMovimientoTx.setPrefijo(fachadaDctoBean.getPrefijo());
            contableMovimientoTx.setIdDcto(fachadaDctoBean.getIdDcto());
            contableMovimientoTx.setIdDctoNitCC(
                                              fachadaDctoBean.getIdDctoNitCC());
            contableMovimientoTx.setItemMovimiento(
                                           fachadaDctoBean.getItemMovimiento());
            contableMovimientoTx.setIdSubcuenta(
                    fachadaDctoBean.getIdSubcuenta());
            contableMovimientoTx.setIdCliente(fachadaDctoBean.getIdCliente());
            contableMovimientoTx.setVrBaseContable(
                                           fachadaDctoBean.getVrBaseContable());
            contableMovimientoTx.setVrBase(fachadaDctoBean.getVrBase());
            contableMovimientoTx.setVrTotal(fachadaDctoBean.getVrTotal());
            contableMovimientoTx.setNombreAsiento(
                                            fachadaDctoBean.getNombreAsiento());
            contableMovimientoTx.setComentarioMovimiento(
                                     fachadaDctoBean.getComentarioMovimiento());

            //
            contableMovimientoTx.ingresaPrefijo_GTERCEROS(xBdNameContable);

        }

    }

    //
    public void ingresaTerceroComprobante() {

        //----------------------------------------------------------------------
        FachadaLocalIp fachadaLocalIp     = new FachadaLocalIp();

        //
        LocalIpBean localIpBean           = new LocalIpBean();

        //
        localIpBean.setIdLocal(getIdLocal());

        //
        fachadaLocalIp                    = localIpBean.listaUnLocal();

        //
        String xBdNameContable            = fachadaLocalIp.getBdNameContable();

        //
        ContableTerceroTx contableTerceroTx
                                          = new ContableTerceroTx();

        //
        DctoContableBean dctoContableBean = new DctoContableBean();

        //
        Vector vecTercero           =    dctoContableBean.listaTercero();

        //
        FachadaTerceroBean fachadaTerceroBean;

        // Iterador de objetos
        Iterator iterTercero        = vecTercero.iterator();

        //
        while (iterTercero.hasNext()) {

            //
            fachadaTerceroBean = (FachadaTerceroBean)iterTercero.next();

             //
             contableTerceroTx.setIdCliente(fachadaTerceroBean.getIdCliente());
             contableTerceroTx.setDigitoVerificacion(
                                    fachadaTerceroBean.getDigitoVerificacion());
             contableTerceroTx.setNombreTercero(
                                         fachadaTerceroBean.getNombreTercero());
             contableTerceroTx.setDireccionTercero(
                                      fachadaTerceroBean.getDireccionTercero());
             contableTerceroTx.setCiudadTercero(
                                         fachadaTerceroBean.getCiudadTercero());
             contableTerceroTx.setTelefonoFijo(
                                          fachadaTerceroBean.getTelefonoFijo());
             contableTerceroTx.setTelefonoCelular(
                                       fachadaTerceroBean.getTelefonoCelular());
             contableTerceroTx.setTelefonoFax(
                                           fachadaTerceroBean.getTelefonoFax());
             contableTerceroTx.setEmail(fachadaTerceroBean.getEmail());
             contableTerceroTx.setContactoTercero(
                                       fachadaTerceroBean.getContactoTercero());
             contableTerceroTx.setIdPersona(fachadaTerceroBean.getIdPersona());
             contableTerceroTx.setIdAutoRetenedor(
                                       fachadaTerceroBean.getIdAutoRetenedor());
             contableTerceroTx.setIdRegimen(fachadaTerceroBean.getIdRegimen());
             contableTerceroTx.setIdDptoCiudad(
                                          fachadaTerceroBean.getIdDptoCiudad());
             contableTerceroTx.setDepartamentoTercero(
                                   fachadaTerceroBean.getDepartamentoTercero());
             contableTerceroTx.setTipoIdTercero(
                                         fachadaTerceroBean.getTipoIdTercero());

             //
             contableTerceroTx.ingresa_GTERCEROS(xBdNameContable);

             //
             contableTerceroTx.ingresa_CMDTERCEROS(xBdNameContable);


        }
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIndicadorInicial() {
        return indicadorInicial;
    }

    public void setIndicadorInicial(int indicadorInicial) {
        this.indicadorInicial = indicadorInicial;
    }

    public int getIndicadorFinal() {
        return indicadorFinal;
    }

    public void setIndicadorFinal(int indicadorFinal) {
        this.indicadorFinal = indicadorFinal;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getBdNameContable() {
        return bdNameContable;
    }

    public void setBdNameContable(String bdNameContable) {
        this.bdNameContable = bdNameContable;
    }
}
