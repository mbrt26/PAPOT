package com.solucionesweb.lasayudas;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import java.util.Iterator;
import java.util.Vector;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoPesoPedido {

    //
    int xIdLocal = 1;
    int xIdTipoOrden = 59;
    double xPesoPedido = 0.0;
    int xNumeroOrden = 0;
    int xItemPadre = 0;

    // Metodo contructor por defecto, es decir, sin parametros
    public void listaPedido() {

        //--------------------------------------------------------------
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

        //
        FichaTecnica fichaTecnica = new FichaTecnica();

        //
        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;

        //
        ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                                           = new ColaboraDctoOrdenDetalleBean();

        Vector objetoBean = colaboraDctoOrdenDetalleBean.listaPesoPedido();

        //
        Iterator iteratorBean = objetoBean.iterator();

        //
        while (iteratorBean.hasNext()) {

            //
            fachadaDctoOrdenDetalleBean =
                    (FachadaDctoOrdenDetalleBean) iteratorBean.next();

            //
            double xPesoPedido = fichaTecnica.pesoPedido(
                    fachadaDctoOrdenDetalleBean.getIdCliente(),
                    fachadaDctoOrdenDetalleBean.getIdFicha(),
                    fachadaDctoOrdenDetalleBean.getCantidad());

            //
            dctoOrdenDetalleBean.setPesoPedido(xPesoPedido);
            dctoOrdenDetalleBean.setIdLocal(xIdLocal);
            dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
            dctoOrdenDetalleBean.setNumeroOrden(
                                  fachadaDctoOrdenDetalleBean.getNumeroOrden());
            dctoOrdenDetalleBean.setItem(fachadaDctoOrdenDetalleBean.getItem());

            //---
            dctoOrdenDetalleBean.actualizaPesoPedido();

        }
    }
}
