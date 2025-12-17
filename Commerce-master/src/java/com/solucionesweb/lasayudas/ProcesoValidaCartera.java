package com.solucionesweb.lasayudas;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class ProcesoValidaCartera {

  public ProcesoValidaCartera (){ }

  //
  public boolean valida (int xIdLocal,
                         int xIdLog,
                         int xTipoOrdenCotizacion,
                         int xTipoOrdenPedido) {

        //
        double xSaldoFull        = 0.0;
        boolean xOkValidaSaldo   = false;
        int xIdTipoTerceroCliente= 1;

        //
        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                                           = new FachadaDctoOrdenDetalleBean();

      	// Parametros llegados de JSP
        ColaboraOrdenDetalleBean colaboraOrdenDetalleBean
                                           = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdTipoOrden(xTipoOrdenCotizacion);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);

        //
        fachadaDctoOrdenDetalleBean        =
                                     colaboraOrdenDetalleBean.liquidaOrdenFCH();
        
        //
        String xIdCliente                  = 
                                     fachadaDctoOrdenDetalleBean.getIdCliente();

      	// Parametros llegados de JSP
        ColaboraDctoBean colaboraDctoBean  =
                                              new ColaboraDctoBean(xIdLocal,
                                                               xTipoOrdenPedido,
                                                                   xIdCliente);
        //
        FachadaDctoBean fachadaDctoBean    = new FachadaDctoBean();

        //
        fachadaDctoBean                    =
                                            colaboraDctoBean.listaCxCTotalFCH();

        //
        xSaldoFull                         =
                              fachadaDctoOrdenDetalleBean.getVrVentaSinDscto() +
                              fachadaDctoOrdenDetalleBean.getVrIvaVenta()      +
                              fachadaDctoBean.getVrSaldo();
        
        //
        FachadaTerceroBean fachadaTerceroBean 
                                           = new FachadaTerceroBean();

        //
        ColaboraTercero colaboraTercero    = new ColaboraTercero();

        //
        colaboraTercero.setIdCliente(xIdCliente);
        colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

        //
        fachadaTerceroBean                 = colaboraTercero.listaTerceroFCH();

        if (fachadaTerceroBean.getCupoCredito()>= xSaldoFull) {

            xOkValidaSaldo                 = true;
        }

        //
        return xOkValidaSaldo;

  }
}
