package com.solucionesweb.losbeans.utilidades;

public interface IFichaTecnica {

   // factorDendidad
   double factorDensidad(String xIdCliente,
                         int xIdFicha);

   // pesoMillar
   double pesoMillar(String xIdCliente,
                         int xIdFicha);

   // pesoPedido
   double pesoPedido(String xIdCliente,
                     int xIdFicha,
                     double xCantidadPedido);

   // pesoComplemento
   double pesoComplemento(String xIdCliente,
                     int xIdFicha);

   // metroPedido
   double metroPedido(String xIdCliente,
                     int xIdFicha,
                     double xCantidadPedido);

   // metroRollo
   double metroRollo(String xIdCliente,
                     int xIdFicha,
                     double xCantidadPedido);

   // pesoRollo
   double pesoRollo(String xIdCliente,
                     int xIdFicha,
                     double xCantidadPedido);


}