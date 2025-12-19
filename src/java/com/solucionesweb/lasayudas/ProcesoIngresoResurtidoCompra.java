package com.solucionesweb.lasayudas;

// Importa la clase que contiene el Day
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

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.negocio.PluBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoIngresoResurtidoCompra {

    // Metodo contructor por defecto, es decir, sin parametros
    public ProcesoIngresoResurtidoCompra() {
    }

    public DctoBean ingresa(int xIdLocal,
            int xIdTipoOrdenNew,
            int xIdLog,
            int xIdTipoOrdenOld,
            FachadaColaboraDctoOrdenBean fachadaProveedorBean) {


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
                dctoOrdenBean.listaDctoOrdenLog();

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
        int xIdTipoTercero = 2;

        // Encabezado Dctos
        int xIdOrdenOld = fachadaDctoOrdenBean.getIdOrden();
        String xIdTercero = fachadaDctoOrdenBean.getIdCliente();
        double xIdUsuario = fachadaDctoOrdenBean.getIdUsuario();
        String email = fachadaDctoOrdenBean.getEmail();
        String fax = fachadaDctoOrdenBean.getFax();
        String contacto = fachadaDctoOrdenBean.getContacto();
        String observacion = fachadaDctoOrdenBean.getObservacionMayuscula();
        String direccionDespacho = fachadaDctoOrdenBean.getDireccionDespacho();
        String ciudadDespacho = fachadaDctoOrdenBean.getCiudadDespacho();
        String formaPago = fachadaDctoOrdenBean.getFormaPago();

        //------------------
        String ordenCompra = fachadaDctoOrdenBean.getOrdenCompra();
        String descuentoComercial =
                fachadaDctoOrdenBean.getDescuentoComercialStr();
        String impuestoVenta = fachadaDctoOrdenBean.getImpuestoVentaStr();
        String idRazon = fachadaDctoOrdenBean.getIdRazon();

        //------------------
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        TerceroBean terceroBean = new TerceroBean();

        //
        terceroBean.setIdCliente(xIdTercero);
        terceroBean.setIdTipoTercero(xIdTipoTercero);

        //
        fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

        //
        String xNombreTercero = fachadaTerceroBean.getNombreTercero();

        //
        int xIndicador = fachadaTerceroBean.getIndicador();
        int xIdFormaPago = fachadaTerceroBean.getIdFormaPago();
        int xIdTipoNegocioVenta = 2;

        //------------------
        ColaboraOrdenDetalleBean colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);

        //
        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

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
                fachadaDctoOrdenDetalleBean.getVrCostoConIva();
        double xVrImpoconsumo =
                fachadaDctoOrdenDetalleBean.getVrImpoconsumo();
        double xVrCostoIND =
                fachadaDctoOrdenDetalleBean.getVrCostoIND();

        //
        int xIdOrdenMax =
                dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

        // ingresaPedido
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenBean.setIdOrden(xIdOrdenMax);
        dctoOrdenBean.setFechaOrden(fachadaDctoOrdenDetalleBean.getFechaOrden());
        dctoOrdenBean.setEstado(xEstadoDcto);
        dctoOrdenBean.setIdCliente(xIdTercero);
        dctoOrdenBean.setIdUsuario(xIdUsuario);
        dctoOrdenBean.setIdOrigen(xIOrigenBB);
        dctoOrdenBean.setIdLog(xIdLog);
        dctoOrdenBean.setFechaEntrega(fachadaDctoOrdenDetalleBean.getFechaOrden());
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

        // ingresaPedido
        boolean okIngresoDcto = dctoOrdenBean.ingresaPedido();

        //------------------
        DctoBean dctoBean = new DctoBean();

        //
        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoBean.setIndicador(xIndicador);

        //
        int xIdDctoMax = dctoBean.maximoDctoLocalIndicador() + 1;

        //
        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoBean.setIdOrden(xIdOrdenMax);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setFechaDcto(fachadaProveedorBean.getFechaOrdenCorta());
        dctoBean.setIdDcto(xIdDctoMax);
        dctoBean.setVrBase(fachadaProveedorBean.getVrCostoSinIva()
                - fachadaProveedorBean.getVrImpoconsumo()
                - fachadaProveedorBean.getVrCostoIva());
        dctoBean.setVrPago(0);
        dctoBean.setEstado(1);
        dctoBean.setVrIva(fachadaProveedorBean.getVrCostoIva());
        dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
        dctoBean.setVrRteFuente(fachadaProveedorBean.getVrCostoRteFuente());
        dctoBean.setVrDescuento(fachadaProveedorBean.getVrDescuento());
        dctoBean.setVrRteIva(0);
        dctoBean.setVrRteIca(0);
        dctoBean.setIdUsuario(xIdUsuario);
        dctoBean.setIdCliente(xIdTercero);
        dctoBean.setDiasPlazo(xIdFormaPago);
        dctoBean.setPorcentajeDscto(0);
        dctoBean.setIdCausa(0);
        dctoBean.setIdDctoNitCC(fachadaProveedorBean.getIdDctoNitCC());
        dctoBean.setFechaDctoNitCC(fachadaProveedorBean.getFechaEntregaCorta());
        dctoBean.setVrPagarDctoNitCC(fachadaProveedorBean.getVrCostoSinIva()
                - fachadaProveedorBean.getVrImpoconsumo()
                - fachadaProveedorBean.getVrCostoRteFuente());
        dctoBean.setVrDsctoFcro(0);
        dctoBean.setIdDctoCruce(0);
        dctoBean.setVrCostoMV(xVrCosto);
        dctoBean.setEstadoDcto(0);
        dctoBean.setIdLocalAdicional(xIdLocal);
        dctoBean.setIdPeriodo(0);
        dctoBean.setNombreTercero(xNombreTercero);
        dctoBean.setIdVendedor(xIdUsuario);
        dctoBean.setVrImpoconsumo(fachadaProveedorBean.getVrImpoconsumo());
        dctoBean.setVrCostoIND(xVrCostoIND);

        //
        dctoBean.ingresaDcto();

        //------------------
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

        // actualizaIvaResurtido (compras porcentajeIva = 0, vrIvaResurtido = 0)
        dctoOrdenDetalleBean.actualizaIvaResurtido();

        // actualizaOrdenOrigen
        dctoOrdenDetalleBean.actualizaOrdenOrigen();

        // actualiza
        dctoOrdenDetalleBean.actualiza(xIdTipoOrdenNew, xIdOrdenMax);

        //
        PluBean pluBean = new PluBean();

        // actualizaCosto
        pluBean.actualizaCosto(xIdLocal,
                xIdTipoOrdenNew,
                xIdOrdenMax);

        // actualizaCostoPluCombo
        pluBean.actualizaCostoPluCombo();

        //------------------------------------------------------------------------
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

        //
        dctoOrdenDetalleBean.actualizaBodegaMP();

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

        //
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdOrden(xIdOrdenOld);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);

        // retira
        okIngresoDcto = dctoOrdenBean.retiraLog();

        return dctoBean;

    }
}
