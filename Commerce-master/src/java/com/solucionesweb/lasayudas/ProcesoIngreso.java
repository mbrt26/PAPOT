package com.solucionesweb.lasayudas;

// Importa la clase que contiene el Day
import co.linxsi.modelo.retencion.retencion_contable.Retencion_Contable_DAO;
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa la clase que contiene el FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.ContableCreeBean;

// Importa la clase que contiene el ContableRetencionBean
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;
import javax.servlet.jsp.PageContext;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoIngreso {

    // Metodo contructor por defecto, es decir, sin parametros
    public ProcesoIngreso() {
    }
private int idTipoAlcance = 0;

    public int getIdTipoAlcance() {
        return idTipoAlcance;
    }

    public void setIdTipoAlcance(int idTipoAlcance) {
        this.idTipoAlcance = idTipoAlcance;
    }

    public DctoBean ingresa(int xIdLocal,
            int xIdTipoOrdenNew,
            int xIdLog,
            int xIdTipoOrdenOld,
            String xNombreTercero,
            int xIdTipoNegocioVenta,
            String xObservacion,
            String xIdVendedor,
            String xIpTx) {

        //
        int xIdTipoNegocioContado = 1;
        int xIdTipoNegocioCredito = 2;
        double xVrPago = 0.0;

        //------------------
        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

        // Bean Dcto
        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

        //
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdLog(xIdLog);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);

        //
        fachadaDctoOrdenBean =
                dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

        int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
        int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
        xIdVendedor = fachadaDctoOrdenBean.getIdVendedorStr();

        // Day
        Day fechaHoy = new Day();

        //
        int xEstadoDcto = 1;
        int xIOrigenBB = 1;
        String xIdRazonVacia = "01";
        int xIdEstadoTx = 2;  // autorizado
        int xIdTipoTx = 1;
        String xNumeroOrden = "0";
        String xIdResponsable = "0";
        int xIdConceptoRFCompra = 1;

        //
        int xDiasHistoria = 0;
        int xDiasInventario = 0;

        // Encabezado Dctos
        int xIdOrdenOld = fachadaDctoOrdenBean.getIdOrden();
        String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
        double xIdUsuario = fachadaDctoOrdenBean.getIdUsuario();
        String email = fachadaDctoOrdenBean.getEmail();
        String fax = fachadaDctoOrdenBean.getFax();
        String contacto = fachadaDctoOrdenBean.getContacto();
        String observacion = xObservacion;
        String direccionDespacho =
                fachadaDctoOrdenBean.getDireccionDespacho();
        String ciudadDespacho = fachadaDctoOrdenBean.getCiudadDespacho();
        String formaPago = fachadaDctoOrdenBean.getFormaPago();

        //------------------
        String ordenCompra = fachadaDctoOrdenBean.getOrdenCompra();
        String descuentoComercial =
                fachadaDctoOrdenBean.getDescuentoComercialStr();
        String impuestoVenta =
                fachadaDctoOrdenBean.getImpuestoVentaStr();
        String idRazon = fachadaDctoOrdenBean.getIdRazon();

        //------------------
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        TerceroBean terceroBean = new TerceroBean();

        //
        terceroBean.setIdCliente(xIdCliente);

        //
        fachadaTerceroBean = terceroBean.listaUnTerceroFachadaCliente();

        //
        int xIndicador = fachadaTerceroBean.getIndicador();
        int xIdFormaPago = fachadaTerceroBean.getIdFormaPago();
        int xIdRteIva = fachadaTerceroBean.getIdRteIva();
        int xIdRteIvaVrBase = fachadaTerceroBean.getIdRteIvaVrBase();
        xNombreTercero = fachadaTerceroBean.getNombreTercero();
        int xIdRteFuenteVrBase = fachadaTerceroBean.getIdRteFuenteVrBase();

        //
        if (xIdTipoNegocioVenta == xIdTipoNegocioContado) {

            //
            xIdFormaPago = 0;
            xIdTipoNegocioVenta = xIdTipoNegocioContado;

        } else {

            //
            xIdTipoNegocioVenta = xIdTipoNegocioCredito;

        }

        //------------------
        FachadaColaboraDctoOrdenBean fachadaBeanDcto
                                           = new FachadaColaboraDctoOrdenBean();

        //------------------
        ColaboraOrdenDetalleBean colaboraOrdenDetalleBean
                                               = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);

        //
        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                                            = new FachadaDctoOrdenDetalleBean();

        //
        fachadaDctoOrdenDetalleBean =
                colaboraOrdenDetalleBean.liquidaOrdenFCH();

        //
        double xVrVentaSinIva =
                fachadaDctoOrdenDetalleBean.getVrVentaSinIva();
        double xVrVentaSinDscto =
                fachadaDctoOrdenDetalleBean.getVrVentaSinDscto();
        double xVrIva =
                fachadaDctoOrdenDetalleBean.getVrIvaVenta();
        double xVrCosto =
                fachadaDctoOrdenDetalleBean.getVrCostoSinIva();
        double xVrImpoconsumo =
                fachadaDctoOrdenDetalleBean.getVrImpoconsumo();
        double xVrCostoIND =
                fachadaDctoOrdenDetalleBean.getVrCostoIND();

        //------------------------------------------------------------------------
        ContableRetencionBean contableRetencionBean = new ContableRetencionBean();

        //
        double xVrRetencion = 0;
        
        if(xIdTipoOrdenNew == 9){
            Retencion_Contable_DAO retContDAO = new Retencion_Contable_DAO();
           
            xIdConceptoRFCompra = this.getIdTipoAlcance();
            Retencion_Contable_DAO contableRetencionDao = new Retencion_Contable_DAO();
            xVrRetencion =
                contableRetencionDao.calculaRetencionFactura(
                fachadaTerceroBean.getIdAutoRetenedor(),
                xIdConceptoRFCompra,
                xVrVentaSinDscto,
                xIdRteFuenteVrBase);
        }else{
        xVrRetencion =
                contableRetencionBean.calculaRetencion(
                fachadaTerceroBean.getIdAutoRetenedor(),
                xIdConceptoRFCompra,
                xVrVentaSinDscto,
                xIdRteFuenteVrBase);
        }

        //
        double xVrRteIva =
                contableRetencionBean.calculaRteIva(
                fachadaTerceroBean.getIdAutoRetenedor(),
                xIdConceptoRFCompra,
                xIdRteIva,
                xIdRteIvaVrBase,
                xVrIva,
                xVrVentaSinDscto);
        
            ContableCreeBean contableCreeBean = new ContableCreeBean();
    int xIdTipoTercero = 1;
    

    double xVrRetencionCree = 
            contableCreeBean.calculaRetencionCreeLocal(xIdLocal, xVrVentaSinDscto);

        //------------------------------------------------------------------------
        int xIdOrdenMax =
                dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

        // ingresaPedido
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenBean.setIdOrden(xIdOrdenMax);
        dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
        dctoOrdenBean.setEstado(xEstadoDcto);
        dctoOrdenBean.setIdCliente(xIdCliente);
        dctoOrdenBean.setIdUsuario(xIdUsuario);
        dctoOrdenBean.setIdOrigen(xIOrigenBB);
        dctoOrdenBean.setIdLog(xIdLog);
        dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
        dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrdenNew).toString());
        dctoOrdenBean.setEmail(email);
        dctoOrdenBean.setFax(fax);
        dctoOrdenBean.setContacto(contacto);
        dctoOrdenBean.setObservacion(observacion);
        dctoOrdenBean.setDireccionDespacho(direccionDespacho);
        dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
        dctoOrdenBean.setFormaPago(new Integer(xIdFormaPago).toString());
        dctoOrdenBean.setOrdenCompra(ordenCompra);
        dctoOrdenBean.setDescuentoComercial(descuentoComercial);
        dctoOrdenBean.setImpuestoVenta(impuestoVenta);
        dctoOrdenBean.setIdRazon(xIdRazonVacia);
        dctoOrdenBean.setIdEstadoTx(xIdEstadoTx);
        dctoOrdenBean.setIdTipoTx(xIdTipoTx);
        dctoOrdenBean.setNumeroOrden(xNumeroOrden);
        dctoOrdenBean.setIdResponsable(xIdResponsable);
        dctoOrdenBean.setDiasHistoria(xDiasHistoria);
        dctoOrdenBean.setDiasInventario(xDiasInventario);
        dctoOrdenBean.setIdFicha(xIdFicha);

        // ingresaPedido
        boolean okIngresoDcto = dctoOrdenBean.ingresaPedido();

        //------------------------------------------------------------------------
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

        /*----------------------------------------------------------------------
        int xIdBodegaActual = 999;
        int xIdBodegaSiguiente = 9;

        //---
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

        //
        dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoSale(xIdBodegaActual);

        //
        dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoEntra(xIdBodegaSiguiente); */

        //----------------------------------------------------------------------
        if (xIdTipoNegocioVenta == xIdTipoNegocioContado) {

            //
            xVrPago = xVrVentaSinDscto
                    + xVrIva
                    + xVrImpoconsumo
                    - xVrRetencion
                    - xVrRteIva
                    - xVrRetencionCree;
        }

        //------------------
        DctoBean dctoBean = new DctoBean();

        //
        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoBean.setIndicador(xIndicador);

        // CEDIPLAST / CAJA POS
        //int xIdDctoMax                = dctoBean.maximoDctoIpIndicador(xIpTx) + 1;

        // PASTICAUCA / NO POS
        int xIdDctoMax = dctoBean.maximoDctoLocalIndicador() + 1;

        //
        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoBean.setIdOrden(xIdOrdenMax);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
        dctoBean.setIdDcto(xIdDctoMax);
        dctoBean.setVrBase(xVrVentaSinDscto);
        dctoBean.setVrPago(xVrPago);
        dctoBean.setEstado(1);
        dctoBean.setVrIva(xVrIva);
        dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
        dctoBean.setVrRteFuente(xVrRetencion);
        dctoBean.setVrDescuento(xVrVentaSinIva
                - xVrVentaSinDscto);
        dctoBean.setVrRteIva(xVrRteIva);
        dctoBean.setVrRteIca(0);
        dctoBean.setIdUsuario(xIdUsuario);
        dctoBean.setIdCliente(xIdCliente);
        dctoBean.setDiasPlazo(xIdFormaPago);
        dctoBean.setPorcentajeDscto(0);
        dctoBean.setIdCausa(0);
        dctoBean.setIdDctoNitCC(new Integer(xIdDctoMax).toString());
        dctoBean.setFechaDctoNitCC(fechaHoy.getFechaFormateada());
        dctoBean.setVrPagarDctoNitCC(0);
        dctoBean.setVrDsctoFcro(0);
        dctoBean.setIdDctoCruce(0);
        dctoBean.setVrCostoMV(xVrCosto);
        dctoBean.setEstadoDcto(0);
        dctoBean.setIdLocalAdicional(xIdLocal);
        dctoBean.setIdPeriodo(0);
        dctoBean.setNombreTercero(xNombreTercero);
        dctoBean.setIdVendedor(xIdVendedor);
        dctoBean.setVrImpoconsumo(xVrImpoconsumo);
        dctoBean.setVrCostoIND(xVrCostoIND);
        dctoBean.setVrRteCree(xVrRetencionCree);

        //
        dctoBean.ingresaDcto();

        //------------------------------------------------------------------------
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

        //
        dctoOrdenDetalleBean.actualiza(xIdTipoOrdenNew, xIdOrdenMax);

        //
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);
        dctoOrdenBean.setIdOrden(xIdOrdenOld);

        //
        dctoOrdenBean.retira();

        //
        ProcesoInventario procesoIventario = new ProcesoInventario();

        // actualizaInventarioEstado
        procesoIventario.actualizaInventarioEstado(xIdLocal,
                xIdTipoOrdenNew,
                xIdOrdenMax);

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

        // actualizaEstadoInventario
        int xEstadoInventario = 4;

        //
        dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

        return dctoBean;

    }
    
    public DctoBean ingresaFactura(int xIdLocal,
            int xIdTipoOrdenNew,
            int xIdLog,
            int xIdTipoOrdenOld,
            String xNombreTercero,
            int xIdTipoNegocioVenta,
            String xObservacion,
            String xIdVendedor,
            String xIpTx) {

        //
        int xIdTipoNegocioContado = 1;
        int xIdTipoNegocioCredito = 2;
        double xVrPago = 0.0;

        //------------------
        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

        // Bean Dcto
        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

        //
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdLog(xIdLog);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);

        //
        fachadaDctoOrdenBean =
                dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

        int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
        int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
        xIdVendedor = fachadaDctoOrdenBean.getIdVendedorStr();

        // Day
        Day fechaHoy = new Day();

        //
        int xEstadoDcto = 1;
        int xIOrigenBB = 1;
        String xIdRazonVacia = "01";
        int xIdEstadoTx = 2;  // autorizado
        int xIdTipoTx = 1;
        String xNumeroOrden = "0";
        String xIdResponsable = "0";
        int xIdConceptoRFCompra = 1;

        //
        int xDiasHistoria = 0;
        int xDiasInventario = 0;

        // Encabezado Dctos
        int xIdOrdenOld = fachadaDctoOrdenBean.getIdOrden();
        String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
        double xIdUsuario = fachadaDctoOrdenBean.getIdUsuario();
        String email = fachadaDctoOrdenBean.getEmail();
        String fax = fachadaDctoOrdenBean.getFax();
        String contacto = fachadaDctoOrdenBean.getContacto();
        String observacion = xObservacion;
        String direccionDespacho =
                fachadaDctoOrdenBean.getDireccionDespacho();
        String ciudadDespacho = fachadaDctoOrdenBean.getCiudadDespacho();
        String formaPago = fachadaDctoOrdenBean.getFormaPago();

        //------------------
        String ordenCompra = fachadaDctoOrdenBean.getOrdenCompra();
        String descuentoComercial =
                fachadaDctoOrdenBean.getDescuentoComercialStr();
        String impuestoVenta =
                fachadaDctoOrdenBean.getImpuestoVentaStr();
        String idRazon = fachadaDctoOrdenBean.getIdRazon();

        //------------------
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        TerceroBean terceroBean = new TerceroBean();

        //
        terceroBean.setIdCliente(xIdCliente);

        //
        fachadaTerceroBean = terceroBean.listaUnTerceroFachada();

        //
        int xIndicador = fachadaTerceroBean.getIndicador();
        int xIdFormaPago = fachadaTerceroBean.getIdFormaPago();
        int xIdRteIva = fachadaTerceroBean.getIdRteIva();
        int xIdRteIvaVrBase = fachadaTerceroBean.getIdRteIvaVrBase();
        xNombreTercero = fachadaTerceroBean.getNombreTercero();
        int xIdRteFuenteVrBase = fachadaTerceroBean.getIdRteFuenteVrBase();

        //
        if (xIdTipoNegocioVenta == xIdTipoNegocioContado) {

            //
            xIdFormaPago = 0;
            xIdTipoNegocioVenta = xIdTipoNegocioContado;

        } else {

            //
            xIdTipoNegocioVenta = xIdTipoNegocioCredito;

        }

        //------------------
        FachadaColaboraDctoOrdenBean fachadaBeanDcto
                                           = new FachadaColaboraDctoOrdenBean();

        //------------------
        ColaboraOrdenDetalleBean colaboraOrdenDetalleBean
                                               = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);

        //
        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                                            = new FachadaDctoOrdenDetalleBean();

        //
        fachadaDctoOrdenDetalleBean =
                colaboraOrdenDetalleBean.liquidaOrdenFCH();

        //
        double xVrVentaSinIva =
                fachadaDctoOrdenDetalleBean.getVrVentaSinIva();
        double xVrVentaSinDscto =
                fachadaDctoOrdenDetalleBean.getVrVentaSinDscto();
        double xVrIva =
                fachadaDctoOrdenDetalleBean.getVrIvaVenta();
        double xVrCosto =
                fachadaDctoOrdenDetalleBean.getVrCostoSinIva();
        double xVrImpoconsumo =
                fachadaDctoOrdenDetalleBean.getVrImpoconsumo();
        double xVrCostoIND =
                fachadaDctoOrdenDetalleBean.getVrCostoIND();

        //------------------------------------------------------------------------
        ContableRetencionBean contableRetencionBean = new ContableRetencionBean();

        //
        double xVrRetencion =
                contableRetencionBean.calculaRetencion(
                fachadaTerceroBean.getIdAutoRetenedor(),
                xIdConceptoRFCompra,
                xVrVentaSinDscto,
                xIdRteFuenteVrBase);

        //
        double xVrRteIva =
                contableRetencionBean.calculaRteIva(
                fachadaTerceroBean.getIdAutoRetenedor(),
                xIdConceptoRFCompra,
                xIdRteIva,
                xIdRteIvaVrBase,
                xVrIva,
                xVrVentaSinDscto);

        //------------------------------------------------------------------------
        int xIdOrdenMax =
                dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

        // ingresaPedido
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenBean.setIdOrden(xIdOrdenMax);
        dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
        dctoOrdenBean.setEstado(xEstadoDcto);
        dctoOrdenBean.setIdCliente(xIdCliente);
        dctoOrdenBean.setIdUsuario(xIdUsuario);
        dctoOrdenBean.setIdOrigen(xIOrigenBB);
        dctoOrdenBean.setIdLog(xIdLog);
        dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
        dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrdenNew).toString());
        dctoOrdenBean.setEmail(email);
        dctoOrdenBean.setFax(fax);
        dctoOrdenBean.setContacto(contacto);
        dctoOrdenBean.setObservacion(observacion);
        dctoOrdenBean.setDireccionDespacho(direccionDespacho);
        dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
        dctoOrdenBean.setFormaPago(new Integer(xIdFormaPago).toString());
        dctoOrdenBean.setOrdenCompra(ordenCompra);
        dctoOrdenBean.setDescuentoComercial(descuentoComercial);
        dctoOrdenBean.setImpuestoVenta(impuestoVenta);
        dctoOrdenBean.setIdRazon(xIdRazonVacia);
        dctoOrdenBean.setIdEstadoTx(xIdEstadoTx);
        dctoOrdenBean.setIdTipoTx(xIdTipoTx);
        dctoOrdenBean.setNumeroOrden(xNumeroOrden);
        dctoOrdenBean.setIdResponsable(xIdResponsable);
        dctoOrdenBean.setDiasHistoria(xDiasHistoria);
        dctoOrdenBean.setDiasInventario(xDiasInventario);
        dctoOrdenBean.setIdFicha(xIdFicha);

        // ingresaPedido
        boolean okIngresoDcto = dctoOrdenBean.ingresaPedido();

        //------------------------------------------------------------------------
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

        
        int xIdBodegaActual = 999;
        
       

            /*----------------------------------------------------------------------
                   int xIdBodegaSiguiente = 9;
        //---
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);
        dctoOrdenDetalleBean.setIdBodega(1);
        
        //
        dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoSale(xIdBodegaActual);*/

        

        //----------------------------------------------------------------------
        if (xIdTipoNegocioVenta == xIdTipoNegocioContado) {

            //
            xVrPago = xVrVentaSinDscto
                    + xVrIva
                    + xVrImpoconsumo
                    - xVrRetencion
                    - xVrRteIva;
        }

        //------------------
        DctoBean dctoBean = new DctoBean();

        //
        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoBean.setIndicador(xIndicador);

        // CEDIPLAST / CAJA POS
        //int xIdDctoMax                = dctoBean.maximoDctoIpIndicador(xIpTx) + 1;

        // PASTICAUCA / NO POS
        int xIdDctoMax = dctoBean.maximoDctoLocalIndicador() + 1;

        //
        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoBean.setIdOrden(xIdOrdenMax);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
        dctoBean.setIdDcto(xIdDctoMax);
        dctoBean.setVrBase(xVrVentaSinDscto);
        dctoBean.setVrPago(xVrPago);
        dctoBean.setEstado(1);
        dctoBean.setVrIva(xVrIva);
        dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
        dctoBean.setVrRteFuente(xVrRetencion);
        dctoBean.setVrDescuento(xVrVentaSinIva
                - xVrVentaSinDscto);
        dctoBean.setVrRteIva(xVrRteIva);
        dctoBean.setVrRteIca(0);
        dctoBean.setIdUsuario(xIdUsuario);
        dctoBean.setIdCliente(xIdCliente);
        dctoBean.setDiasPlazo(xIdFormaPago);
        dctoBean.setPorcentajeDscto(0);
        dctoBean.setIdCausa(0);
        dctoBean.setIdDctoNitCC(new Integer(xIdDctoMax).toString());
        dctoBean.setFechaDctoNitCC(fechaHoy.getFechaFormateada());
        dctoBean.setVrPagarDctoNitCC(0);
        dctoBean.setVrDsctoFcro(0);
        dctoBean.setIdDctoCruce(0);
        dctoBean.setVrCostoMV(xVrCosto);
        dctoBean.setEstadoDcto(0);
        dctoBean.setIdLocalAdicional(xIdLocal);
        dctoBean.setIdPeriodo(0);
        dctoBean.setNombreTercero(xNombreTercero);
        dctoBean.setIdVendedor(xIdVendedor);
        dctoBean.setVrImpoconsumo(xVrImpoconsumo);
        dctoBean.setVrCostoIND(xVrCostoIND);

        //
        dctoBean.ingresaDcto();

        //------------------------------------------------------------------------
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

        //
        dctoOrdenDetalleBean.actualiza(xIdTipoOrdenNew, xIdOrdenMax);
        
        //
        dctoOrdenDetalleBean.actualizaBodegaMPFactura(xIdBodegaActual,xIdOrdenMax); 

        //
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);
        dctoOrdenBean.setIdOrden(xIdOrdenOld);

        //
        dctoOrdenBean.retira();

        //
        ProcesoInventario procesoIventario = new ProcesoInventario();

        // actualizaInventarioEstado
        procesoIventario.actualizaInventarioEstado(xIdLocal,
                xIdTipoOrdenNew,
                xIdOrdenMax);

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

        // actualizaEstadoInventario
        int xEstadoInventario = 4;

        //
        dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

        return dctoBean;

    }
}
