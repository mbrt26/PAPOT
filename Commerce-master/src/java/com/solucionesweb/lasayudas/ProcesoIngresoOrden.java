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

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoIngresoOrden {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoIngresoOrden () { }


  public DctoOrdenBean ingresa(int xIdLocal,
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
      int xIdEstadoTx             = 1;  // autorizado
      int xIdTipoTx               = 1;
      String xNumeroOrden         = "0";
      String xIdResponsable       = "0";
      int xIdConceptoRFCompra     = 1;
      int xIdTipoTerceroProveedor = 2;

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
      int xDiasHistoria           = fachadaDctoOrdenBean.getDiasHistoria();
      int xDiasInventario         = fachadaDctoOrdenBean.getDiasInventario();
      String xFechaEntrega        = fachadaDctoOrdenBean.getFechaEntrega();

      //------------------
      FachadaTerceroBean fachadaTerceroBean
                                    = new FachadaTerceroBean();

      //
      TerceroBean terceroBean       = new TerceroBean();

      //
      terceroBean.setIdCliente(xIdCliente);
      terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);

      //
      fachadaTerceroBean            = terceroBean.listaUnTerceroFCH();

      //
      String xNombreTercero         = fachadaTerceroBean.getNombreTercero();

      //
      int xIndicador                = 1;
      int xIdFormaPago              = 0;
      int xIdTipoNegocioVenta       = 1;

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
      dctoOrdenBean.setFechaEntrega(xFechaEntrega);
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

      // ingresaPedido
      boolean okIngresoDcto         = dctoOrdenBean.ingresaPedido();

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
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      // actualizaEstadoInventario
      int xEstadoInventario = 0;

      //
      dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      //
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdOrden(xIdOrdenOld);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOld);

      // retira
      okIngresoDcto         = dctoOrdenBean.retiraLog();

      return dctoOrdenBean;

  }

}