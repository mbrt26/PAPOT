package com.solucionesweb.lasayudas;

// Importa la clase que contiene el Day
import co.linxsi.modelo.retencion.retencion_contable.Retencion_Contable_DAO;
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaDctoBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

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

import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa la clase que contiene el ContableRetencionBean
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoIngresoNota {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoIngresoNota () { }

  public DctoBean ingresa(int xIdLocal,
                          int xIdTipoOrdenNew,
                          int xIdOrden,
                          int xIdLog,
                          int xIdTipoOrdenOld,
                          int xSignoOperacion,
                          int xIdDcto,
                          int xIndicador,
                          int xIdCausa,
                          double xVrRteFuenteTotal)  {


      //
      int xIdTipoOrdenCruce = xIdTipoOrdenNew - 20;

      //------------------
      FachadaDctoOrdenBean fachadaDctoOrdenBean
                                   = new FachadaDctoOrdenBean();

      // Bean Dcto
      DctoOrdenBean dctoOrdenBean  = new DctoOrdenBean();

      //
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdLog(xIdLog);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);

      //
      fachadaDctoOrdenBean         =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

      // Day
      Day fechaHoy                = new Day();
      Retencion_Contable_DAO daoRetencion = new Retencion_Contable_DAO();
      //
      int xEstadoDcto             = 1;
      int  xIOrigenBB             = 1;
      String xIdRazonVacia        = "01";
      int xIdEstadoTx             = 2;  // autorizado
      int xIdTipoTx               = 1;
      String xNumeroOrden         = "0";
      String xIdResponsable       = "0";
      int xIdConceptoRFCompra      = daoRetencion.getIdConceptoRetNota(xIdOrden) ;

      // Encabezado Dctos
      int     xIdOrdenOld         = fachadaDctoOrdenBean.getIdOrden();
  	  String  xIdCliente          = fachadaDctoOrdenBean.getIdCliente();
  	  double  xIdUsuario          = fachadaDctoOrdenBean.getIdUsuario();
  	  String  email               = fachadaDctoOrdenBean.getEmail();
  	  String  fax                 = fachadaDctoOrdenBean.getFax();
  	  String  contacto            = fachadaDctoOrdenBean.getContacto();
  	  String  observacion         = fachadaDctoOrdenBean.getObservacion();
  	  String  direccionDespacho   =
                                    fachadaDctoOrdenBean.getDireccionDespacho();
  	  String  ciudadDespacho      = fachadaDctoOrdenBean.getCiudadDespacho();
  	  String  formaPago           = fachadaDctoOrdenBean.getFormaPago();

      //------------------
      String  ordenCompra         = fachadaDctoOrdenBean.getOrdenCompra();
      String  descuentoComercial  =
                                fachadaDctoOrdenBean.getDescuentoComercialStr();
      String  impuestoVenta       = fachadaDctoOrdenBean.getImpuestoVentaStr();
      String  idRazon             = fachadaDctoOrdenBean.getIdRazon();


      //------------------
      FachadaTerceroBean fachadaTerceroBean
                                   = new FachadaTerceroBean();

      //
      TerceroBean terceroBean       = new TerceroBean();

      //
      terceroBean.setIdCliente(xIdCliente);

      //
      fachadaTerceroBean            = terceroBean.listaUnTerceroFachada();

      //
      fachadaTerceroBean.setIndicador(xIndicador);

      //
      int xIdFormaPago              = fachadaTerceroBean.getIdFormaPago();
      int xIdRteIva                 = fachadaTerceroBean.getIdRteIva();
      int xIdRteIvaVrBase           = fachadaTerceroBean.getIdRteIvaVrBase();

      //------------------
      FachadaDctoOrdenDetalleBean fachadaBeanDetalle;

      //
      FachadaColaboraDctoOrdenBean fachadaBeanTotales
                                       = new FachadaColaboraDctoOrdenBean();

      FachadaColaboraDctoOrdenBean fachadaBeanDcto
                                       =    new FachadaColaboraDctoOrdenBean();

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
      fachadaDctoOrdenDetalleBean      =
                                     colaboraOrdenDetalleBean.liquidaOrdenFCH();

      //
      double xVrVentaSinIva            =
                                 fachadaDctoOrdenDetalleBean.getVrVentaSinIva();
      double xVrVentaSinDscto          =
                              fachadaDctoOrdenDetalleBean.getVrVentaSinDscto();
      double xVrIva                    =
                                    fachadaDctoOrdenDetalleBean.getVrIvaVenta();
      double xVrCosto                  =
                                 fachadaDctoOrdenDetalleBean.getVrCostoSinIva();
      double xVrImpoconsumo            =
                                 fachadaDctoOrdenDetalleBean.getVrImpoconsumo();
      double xVrCostoIND               =
                                 fachadaDctoOrdenDetalleBean.getVrCostoIND();

      //
      ContableRetencionBean contableRetencionBean
                                       = new ContableRetencionBean();

      //
      double xVrRetencion              =
                     contableRetencionBean.calculaRetencionNota(
                                        fachadaTerceroBean.getIdAutoRetenedor(),
                                                            xIdConceptoRFCompra,
                                                              xVrVentaSinIva);

      //
      double xVrRteIva                 =
                     contableRetencionBean.calculaRteIva(
                                        fachadaTerceroBean.getIdAutoRetenedor(),
                                                            xIdConceptoRFCompra,
                                                              xIdRteIva,
                                                              xIdRteIvaVrBase,
                                                              xVrIva,
                                                              xVrVentaSinDscto);

      //--- Sin vrRteFuente en Dcto origen
      if (xVrRteFuenteTotal==0) xVrRetencion = 0 ;

      //
      int xIdOrdenMax                  =
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
      dctoOrdenBean.setFormaPago(formaPago);
      dctoOrdenBean.setOrdenCompra(ordenCompra);
      dctoOrdenBean.setDescuentoComercial(descuentoComercial);
      dctoOrdenBean.setImpuestoVenta(impuestoVenta);
      dctoOrdenBean.setIdRazon(xIdRazonVacia);
      dctoOrdenBean.setIdEstadoTx(xIdEstadoTx);
      dctoOrdenBean.setIdTipoTx(xIdTipoTx);
      dctoOrdenBean.setNumeroOrden(xNumeroOrden);
      dctoOrdenBean.setIdResponsable(xIdResponsable);

      // ingresaPedido
      boolean okIngresoDcto         = dctoOrdenBean.ingresaPedido();

      //------------------
      terceroBean.setIdCliente(xIdCliente);

      fachadaTerceroBean            = terceroBean.listaUnTerceroFachada();


      //------------------
      DctoBean dctoBean             = new DctoBean();

      FachadaDctoBean fachadaDctoBean
                                    = new FachadaDctoBean();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenCruce);
      dctoBean.setIdOrden(xIdOrden);
      dctoBean.setIndicador(xIndicador);

      //
      fachadaDctoBean               = dctoBean.listaUnDctoOrden();

      //
      int xIdLocalCruce             = fachadaDctoBean.getIdLocal();
      double xIdDctoCruce           = fachadaDctoBean.getIdDcto();
      Double xIdVendedor            = fachadaDctoBean.getIdVendedor();
      int xIdTipoNegocio            = fachadaDctoBean.getIdTipoNegocio();
      String xNombreTercero         = fachadaDctoBean.getNombreTercero();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIndicador(xIndicador);

      //
      int xIdDctoMax               = dctoBean.maximoDctoLocalIndicador() + 1;

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIdOrden(xIdOrdenMax);
      dctoBean.setIndicador(xIndicador);
      dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
      dctoBean.setIdDcto(xIdDctoMax);
      dctoBean.setVrBase(xVrVentaSinIva * xSignoOperacion );
      dctoBean.setVrPago(0);
      dctoBean.setEstado(1);
      dctoBean.setVrIva(xVrIva * xSignoOperacion );
      dctoBean.setIdTipoNegocio(xIdTipoNegocio);
      dctoBean.setVrRteFuente(xVrRetencion * xSignoOperacion);
      dctoBean.setVrDescuento(0);
      dctoBean.setVrRteIva(xVrRteIva * xSignoOperacion);
      dctoBean.setNombreTercero(xNombreTercero);
      dctoBean.setIdUsuario(xIdUsuario);
      dctoBean.setIdCliente(xIdCliente);
      dctoBean.setDiasPlazo(xIdFormaPago);
      dctoBean.setPorcentajeDscto(0);
      dctoBean.setIdCausa(xIdCausa);
      dctoBean.setIdDctoNitCC(new Integer(xIdDctoMax).toString());
      dctoBean.setFechaDctoNitCC(fechaHoy.getFechaFormateada());
      dctoBean.setVrPagarDctoNitCC(0);
      dctoBean.setVrDsctoFcro(0);
      dctoBean.setIdDctoCruce(0);
      dctoBean.setVrCostoMV(xVrCosto * xSignoOperacion );
      dctoBean.setEstadoDcto(0);
      dctoBean.setIdLocalAdicional(xIdLocal);
      dctoBean.setIdPeriodo(0);
      dctoBean.setIdVendedor(xIdVendedor);

      //
      dctoBean.setIdLocalCruce(xIdLocalCruce);
      dctoBean.setIdTipoOrdenCruce(xIdTipoOrdenCruce);
      dctoBean.setIdDctoCruce(xIdDctoCruce);
      dctoBean.setIndicador(xIndicador);
      dctoBean.setVrImpoconsumo(xVrImpoconsumo * xSignoOperacion );
      dctoBean.setVrCostoIND(xVrCosto * xSignoOperacion );
      dctoBean.setIdOrdenCruce(xIdOrden);

      //
      dctoBean.ingresaDcto();

      //------------------
      DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

      //
      dctoOrdenDetalleBean.actualizaNota(xIdTipoOrdenNew,
                                         xIdOrdenMax,
                                         xSignoOperacion);

      //
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);
      dctoOrdenBean.setIdOrden(xIdOrdenOld);

      //
      dctoOrdenBean.retira();

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

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