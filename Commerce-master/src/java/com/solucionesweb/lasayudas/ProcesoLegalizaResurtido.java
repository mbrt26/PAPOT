package com.solucionesweb.lasayudas;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

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

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoLegalizaResurtido {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoLegalizaResurtido () { }


  public DctoBean ingresa(FachadaDctoBean fachadaDctoBean,
                          int xIdLog)  {

      //
      int xIdTipoTercero         = 2;

      // DctoOrdenBean
      DctoOrdenBean dctoOrdenBean  = new DctoOrdenBean();

      //
      dctoOrdenBean.setIdLocal(fachadaDctoBean.getIdLocal());
      dctoOrdenBean.setIdLog(xIdLog);
      dctoOrdenBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());

      //
      FachadaDctoOrdenBean fachadaDctoOrdenBean
                             = new FachadaDctoOrdenBean();

      //
      fachadaDctoOrdenBean        =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

      //
      int xIdOrdenActual          = fachadaDctoOrdenBean.getIdOrden();

      // Day
      Day fechaHoy                = new Day();

      // Encabezado Dctos
  	  String  xIdCliente          = fachadaDctoOrdenBean.getIdCliente();
  	  double  xIdUsuario          = fachadaDctoOrdenBean.getIdUsuario();

      //------------------
      FachadaTerceroBean fachadaTerceroBean
                                  = new FachadaTerceroBean();

      //
      TerceroBean terceroBean     = new TerceroBean();

      //
      terceroBean.setIdCliente(xIdCliente);
      terceroBean.setIdTipoTercero(xIdTipoTercero);

      //
      fachadaTerceroBean            = terceroBean.listaUnTerceroFCH();

      //
      String xNombreTercero         = fachadaTerceroBean.getNombreTercero();

      //
      int xIndicador                = 1;
      int xIdFormaPago              = 0;
      int xIdTipoNegocioVenta       = 1;

      //
      FachadaColaboraDctoOrdenBean fachadaBeanTotales
                                       = new FachadaColaboraDctoOrdenBean();

      FachadaColaboraDctoOrdenBean fachadaBeanDcto
                                       =    new FachadaColaboraDctoOrdenBean();

      //------------------
      ColaboraOrdenDetalleBean colaboraOrdenDetalleBean
                                       = new ColaboraOrdenDetalleBean();

      //
      colaboraOrdenDetalleBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
      colaboraOrdenDetalleBean.setIdLog(xIdLog);
      colaboraOrdenDetalleBean.setIdTipoOrden(
                                         fachadaDctoOrdenBean.getIdTipoOrden());

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
      double xVrCosto                  = fachadaBeanDcto.getVrCostoSinIva();

      //
      double xVrRetencion              = 0;


      //------------------
      DctoBean dctoBean             = new DctoBean();

      //
      dctoBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
      dctoBean.setIdTipoOrden(fachadaDctoOrdenBean.getIdTipoOrden());
      dctoBean.setIndicador(xIndicador);

      //
      int xIdDctoMax                = dctoBean.maximoDctoLocalIndicador() + 1;

      //
      dctoBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
      dctoBean.setIdTipoOrden(fachadaDctoOrdenBean.getIdTipoOrden());
      dctoBean.setIdOrden(xIdOrdenActual);
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
      dctoBean.setIdDctoNitCC(fachadaDctoBean.getIdDctoNitCC());
      dctoBean.setFechaDctoNitCC(fachadaDctoBean.getFechaDctoNitCC());
      dctoBean.setVrPagarDctoNitCC(0);
      dctoBean.setVrDsctoFcro(0);
      dctoBean.setIdDctoCruce(0);
      dctoBean.setVrCostoMV(xVrCosto);
      dctoBean.setEstadoDcto(0);
      dctoBean.setIdLocalAdicional(fachadaDctoBean.getIdLocal());
      dctoBean.setIdPeriodo(0);
      dctoBean.setNombreTercero(xNombreTercero);
      dctoBean.setIdVendedor(xIdUsuario);

      //
      dctoBean.ingresaDcto();

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

      // actualizaInventarioEstado
      procesoIventario.actualizaInventarioEstado(
                                         fachadaDctoOrdenBean.getIdLocal(),
                                         fachadaDctoOrdenBean.getIdTipoOrden(),
                                         xIdOrdenActual);


      //------------------
      DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

      //
      dctoOrdenDetalleBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
      dctoOrdenDetalleBean.setIdTipoOrden(fachadaDctoOrdenBean.getIdTipoOrden());
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenActual);

      // actualizaEstadoInventario
      int xEstadoInventario = 4;

      //
      dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      //
      return dctoBean;

  }

}