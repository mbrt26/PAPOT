package com.solucionesweb.lasayudas;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoReciboCompra {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoReciboCompra () { }


  public void ingresa(int xIdLocal,
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
                                 dctoOrdenBean.listaDctoOrdenLog();


      // Encabezado Dctos
      int     xIdOrdenOld         = fachadaDctoOrdenBean.getIdOrden();

      //------------------
      DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

      //
      dctoOrdenDetalleBean.actualizaBodegaMP();

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

      // actualizaInventarioEstado
      procesoIventario.actualizaInventarioEstado(xIdLocal,
                                                 xIdTipoOrdenOld,
                                                 xIdOrdenOld);

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenOld);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

      // actualizaEstadoInventario
      int xEstadoInventario = 4;

      //
      dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);


  }

}