package com.solucionesweb.lasayudas;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;


// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;


public class ProcesoGuardaResurtidoPlu {

  public boolean guarda(int xIdLocal,
                    int xIdTipoOrden,
                    int xIdLog,
                    String xIdPlu,
                    String xCantidadPedida,
                    String xCantidadBonificada,
                    String xVrCostoNegociado,
                    String xIdTercero,
                    String xObservacion,
                    int xDiasHistoria,
                    int xDiasInventario,
                    String xFechaEntrega)  {

          // dctoOrdenBean
          DctoOrdenBean dctoOrdenBean              = new DctoOrdenBean();

          //
          dctoOrdenBean.setIdLocal(xIdLocal);
          dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenBean.setIdLog(xIdLog);
          dctoOrdenBean.setIdCliente(xIdTercero);
          dctoOrdenBean.setObservacion(xObservacion);
          dctoOrdenBean.setDiasHistoria(xDiasHistoria);
          dctoOrdenBean.setDiasInventario(xDiasInventario);
          dctoOrdenBean.setFechaEntrega(xFechaEntrega);

          // ingresa OrdenDetalle
          boolean okIngresaOrden =
                                 dctoOrdenBean.guardaResurtido();

          //
          ColaboraPlu colaboraPlu         = new ColaboraPlu();

          //
          colaboraPlu.setIdPlu(xIdPlu);

          //
          FachadaPluBean fachadaPluBean   = new FachadaPluBean();

          //
          fachadaPluBean                  = colaboraPlu.listaUnPluFCH();

          // Igual Encabezado = Detalle en IdLocal
          DctoOrdenDetalleBean dctoOrdenDetalleBean
                                                   = new DctoOrdenDetalleBean();

          //
          dctoOrdenDetalleBean.setIdLocal(xIdLocal);
          dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenDetalleBean.setIdLog(xIdLog);
          dctoOrdenDetalleBean.setIdPlu(xIdPlu);
          dctoOrdenDetalleBean.setCantidad(xCantidadPedida);
          dctoOrdenDetalleBean.setCantidadBonificada(xCantidadBonificada);
          dctoOrdenDetalleBean.setCantidadPedida(xCantidadPedida);
          dctoOrdenDetalleBean.setVrCostoNegociado(xVrCostoNegociado);
          dctoOrdenDetalleBean.setVrIvaResurtido(
                                   new Double(xCantidadPedida).doubleValue()   *
                                   new Double(xVrCostoNegociado).doubleValue() *
                                  ( fachadaPluBean.getPorcentajeIva() / 100 )) ;


          // ingresa OrdenDetalle
          boolean okIngresaDetalle   =
                                 dctoOrdenDetalleBean.guardaResurtido();

          //
          return okIngresaDetalle;

  }
}

