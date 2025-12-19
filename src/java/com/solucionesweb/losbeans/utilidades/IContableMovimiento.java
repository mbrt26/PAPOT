package com.solucionesweb.losbeans.utilidades;

public interface IContableMovimiento {

   // limpiaLocalMovimiento
   boolean limpiaLocalMovimiento(int xIdLocal,
                                int xIdTipoOrden);

   // traeRemotoMovimiento
   boolean traeRemotoMovimiento(int xIdLocal,
                                int xIdTipoOrden,
                                int xIdLog);

   // traeMovimiento
   boolean traeMovimiento(int xIdLocal,
                          int xIdLog);

   // generaLocalMovimiento
   boolean generaLocalMovimiento();

   //guardaRemotoMovimiento
   boolean guardaRemotoMovimiento();

}