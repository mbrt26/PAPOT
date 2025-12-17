package com.solucionesweb.losbeans.utilidades;

public interface IConstantes {
   String StringDesconocido = "Desconocido";

   /** Numero maximo que puede tener una factura
    *
   */
   int nroMaximoDetallesPorFactura = 8;

   String StringEspacio = "&nbsp;";

   // String para evitar que salga el aviso null al retornar datos a jsp
   String StringNulo = "&nbsp;";

   // String para guardar el null, pero en forma de string vacio
   //   String STRINGVACIO = "&nbsp;";
   String STRINGVACIO = "";

   //
   String STRINGNN = "nn";

   // Entero para inicializar variables numericas
   int VALORINICIALIZACIONNUMEROS = 0;

}