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
import com.solucionesweb.losbeans.negocio.LocalBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.negocio.PluBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoIngresoResurtido {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoIngresoResurtido () { }


  public DctoBean ingresa(int xIdLocal,
                          int xIdTipoOrdenNew,
                          int xIdLog,
                          int xIdTipoOrdenOld)  {


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
      fachadaDctoOrdenBean        =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

      // Day
      Day fechaHoy                = new Day();

      //
      int xEstadoDcto             = 1;
      int  xIOrigenBB             = 1;
      String xIdRazonVacia        = "01";
      int xIdEstadoTx             = 2;  // autorizado
      int xIdTipoTx               = 1;
      String xNumeroOrden         = "0";
      String xIdResponsable       = "0";
      int xIdConceptoRFCompra     = 1;

      // Encabezado Dctos
          int     xIdOrdenOld         = fachadaDctoOrdenBean.getIdOrden();
  	  String  xIdCliente          = fachadaDctoOrdenBean.getIdCliente();
  	  double  xIdUsuario          = fachadaDctoOrdenBean.getIdUsuario();
  	  String  email               = fachadaDctoOrdenBean.getEmail();
  	  String  fax                 = fachadaDctoOrdenBean.getFax();
  	  String  contacto            = fachadaDctoOrdenBean.getContacto();
  	  String  observacion         = "";
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
      FachadaLocalBean fachadaLocalBean
                                    = new FachadaLocalBean();

      //
      LocalBean localBean           = new LocalBean();

      //
      localBean.setIdLocal(xIdCliente);

      //
      fachadaLocalBean              = localBean.listaUnLocal();

      //
      String xNombreTercero         = fachadaLocalBean.getNombreLocal();

      //
      int xIndicador                = 1;
      int xIdFormaPago              = 0;
      int xIdTipoNegocioVenta       = 1;

      //------------------
      FachadaDctoOrdenDetalleBean fachadaBeanDetalle;

      //
      FachadaColaboraDctoOrdenBean fachadaBeanTotales
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
                                fachadaDctoOrdenDetalleBean.getVrCostoConIva();

      //
      double xVrRetencion              = 0;

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
      boolean okIngresoDcto         = dctoOrdenBean.ingresaPedido();

      //------------------
      DctoBean dctoBean             = new DctoBean();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIndicador(xIndicador);

      //
      int xIdDctoMax                = dctoBean.maximoDctoLocalIndicador() + 1;

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIdOrden(xIdOrdenMax);
      dctoBean.setIndicador(xIndicador);
      dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
      dctoBean.setIdDcto(xIdDctoMax);
      dctoBean.setVrBase(xVrVentaSinDscto);
      dctoBean.setVrPago(0);
      dctoBean.setEstado(1);
      dctoBean.setVrIva(xVrIva);
      dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
      dctoBean.setVrRteFuente(xVrRetencion);
      dctoBean.setVrDescuento(xVrVentaSinIva -
                              xVrVentaSinDscto );
      dctoBean.setVrRteIva(0);
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
      dctoBean.setIdVendedor(xIdUsuario);

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
      dctoOrdenDetalleBean.actualiza(xIdTipoOrdenNew, xIdOrdenMax);

      //
      PluBean pluBean                  = new PluBean();

      // actualizaCosto --------> siempre antes actualizaInventarioEstado
      pluBean.actualizaCosto(xIdLocal,
                             xIdTipoOrdenNew,
                             xIdOrdenMax);

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

      // actualizaInventarioEstado
      procesoIventario.actualizaInventarioEstado(xIdLocal,
                                                 xIdTipoOrdenNew,
                                                 xIdOrdenMax);

      // actualizaEstadoInventario
      int xEstadoInventario = 4;

      //
      dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      //
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdOrden(xIdOrdenOld);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);

      // retira
      okIngresoDcto         = dctoOrdenBean.retiraLog();

      return dctoBean;

  }

}