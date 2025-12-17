package com.solucionesweb.lasayudas;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.negocio.PluBean;

import com.solucionesweb.losbeans.negocio.PluInventarioBean;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

import com.solucionesweb.losbeans.fachada.FachadaPluInventarioBean;

public class ProcesoInventario  {

  public ProcesoInventario (){ }

  //
  public boolean actualiza (int xIdLocal,
                            String xReferencia,
                            double xExistencia,
                            int xTipoOrden,
                            int xIdOrden,
                            int xCantidadOrden){

    //
    int xCeroInt                      = 0;
    double xCeroDouble                = 0.0;
    int xEstadoActivo                 = 1;
    double xSignoEntrada              = +1;
    double xSignoSalida               = -1;
    double xSigno                     = +1;

    //
    FachadaPluBean fachadaPluBean     = new FachadaPluBean();

    //
    PluBean pluBean                   = new PluBean();

    //
    pluBean.setReferencia(xReferencia);

    //
    fachadaPluBean                    = pluBean.existePluFachada();

    //
    FachadaPluInventarioBean  fachadaPluInventarioBean
                                      = new FachadaPluInventarioBean();

    //
    PluInventarioBean pluInventarioBean
                                      = new PluInventarioBean();

    //
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdPlu(fachadaPluBean.getIdPlu());

    //
    fachadaPluInventarioBean          = pluInventarioBean.listaUnFCH();

    //
    if (fachadaPluInventarioBean.getIdPlu()==0) {

       //
       pluInventarioBean.setIdLocal(xIdLocal);
       pluInventarioBean.setIdPlu(fachadaPluBean.getIdPlu());
       pluInventarioBean.setExistencia(xCeroDouble);
       pluInventarioBean.setIdTipoOrden(xCeroInt);
       pluInventarioBean.setIdOrden(xCeroInt);
       pluInventarioBean.setCantidadOrden(xCeroDouble);
       pluInventarioBean.setEstado(xEstadoActivo);

       //
       pluInventarioBean.ingresa();;

    }

    //
    if (xTipoOrden == 9 ) xSigno = xSignoSalida;
    if (xTipoOrden == 29 ) xSigno = xSignoSalida;
    if (xTipoOrden == 1 ) xSigno = xSignoEntrada;
    if (xTipoOrden == 21 ) xSigno = xSignoSalida;

    //
    pluInventarioBean.setCantidadOrden(xCantidadOrden * xSigno);
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdPlu(fachadaPluInventarioBean.getIdPlu());

    //
    boolean xOkInventario = pluInventarioBean.actualiza();

    //
    return xOkInventario;
  
  }

  //
  public boolean actualizaInventarioEstado (int xIdLocal,
                                            int xTipoOrden,
                                            int xIdOrden) {

    //
    PluInventarioBean pluInventarioBean
                                      = new PluInventarioBean();

    //
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdTipoOrden(xTipoOrden);
    pluInventarioBean.setIdOrden(xIdOrden);

    //
    pluInventarioBean.actualizaInventarioEstado();

    return true;

  }
  
  //
  public boolean actualizaInventarioBodegas (int xIdLocal,
                                            int xTipoOrden,
                                            int xIdOrden,
                                            int xBodegaOrigen,
                                            int xBodegaDestino,
                                            int xSignoSale,
                                            int xSignoEntra) {

    //
    PluInventarioBean pluInventarioBean
                                      = new PluInventarioBean();

    //
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdTipoOrden(xTipoOrden);
    pluInventarioBean.setIdOrden(xIdOrden);
    
    if(xBodegaDestino == 999){
    pluInventarioBean.actualizaInventarioEstadoTerminado(xBodegaOrigen);
    pluInventarioBean.actualizaInventarioEstadoTer(xBodegaDestino); 
    }else{
    if(xBodegaOrigen == 2 ){
      pluInventarioBean.actualizaInventarioBodega(xBodegaDestino,xSignoSale);        
    }else{
    pluInventarioBean.actualizaInventarioBodegas(xBodegaOrigen,xSignoEntra);
    pluInventarioBean.actualizaInventarioBodega(xBodegaDestino,xSignoSale);
    }
    }
    return true;

  }
  
  //
  public boolean actualizaInventarioBodegaRetal (int xIdLocal,
                                            int xTipoOrden,
                                            int xIdOrden,
                                            int xBodegaOrigen,
                                            int xBodegaDestino,
                                            int xSignoSale,
                                            int xSignoEntra) {

    //
    PluInventarioBean pluInventarioBean
                                      = new PluInventarioBean();

    //
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdTipoOrden(xTipoOrden);
    pluInventarioBean.setIdOrden(xIdOrden);
    
   
    pluInventarioBean.actualizaInventarioBodegaRetal(xBodegaDestino,xSignoEntra); 
    
    

    return true;

  }
  
  //
  public boolean actualizaInventarioEstadoTermi (int xIdLocal,
                                            int xTipoOrden,
                                            int xIdOrden,
                                            int xIdBodegaActual,
                                            int xIdBodegaSiguinte) {

    //
    PluInventarioBean pluInventarioBean
                                      = new PluInventarioBean();

    //
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdTipoOrden(xTipoOrden);
    pluInventarioBean.setIdOrden(xIdOrden);

    //
    pluInventarioBean.actualizaInventarioEstadoTerminado(xIdBodegaActual);
    pluInventarioBean.actualizaInventarioEstadoTer(xIdBodegaSiguinte);

    return true;

  }

  //
  public boolean ajustaInventario (int xIdLocal,
                                   int xTipoOrden,
                                   int xIdOrden) {

    //
    PluInventarioBean pluInventarioBean
                                      = new PluInventarioBean();

    //
    pluInventarioBean.setIdLocal(xIdLocal);
    pluInventarioBean.setIdTipoOrden(xTipoOrden);
    pluInventarioBean.setIdOrden(xIdOrden);

    //
    pluInventarioBean.ajustaInventario();

    return true;

  }
}

