package com.solucionesweb.lasayudas;

import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class ProcesoValidaPlazo {

  public ProcesoValidaPlazo () { }

  //
  public String valida (int xIdLocal,
                        int xIdLog,
                        int xTipoOrdenCotizacion) {

        //
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

        //
        FachadaTerceroBean fachadaTerceroBean
                                           = new FachadaTerceroBean();

        //
        ColaboraTercero colaboraTercero    = new ColaboraTercero();

        //
        colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
        colaboraTercero.setIdCliente(xIdCliente);

        //
        fachadaTerceroBean                 = colaboraTercero.listaTerceroFCH();

        //
        int xIdFormaPago                   = fachadaTerceroBean.getIdFormaPago();

        //
        ColaboraDctoBean colaboraDctoBean  =  new ColaboraDctoBean(xIdLocal,
                                                 xTipoOrdenCotizacion-50,
                                                 xIdCliente);

        //
        boolean xOkExcedePlazo             =
                                     colaboraDctoBean.excedePlazo(xIdFormaPago);
        //
        String xExcedePlazo                = "";

        //
        if (xOkExcedePlazo) {

           //
           xExcedePlazo                    = "EXCEDE PLAZO ";

        }
        
        return xExcedePlazo;

  }
}
