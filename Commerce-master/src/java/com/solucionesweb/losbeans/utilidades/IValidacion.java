package com.solucionesweb.losbeans.utilidades;

public interface IValidacion {

   // isValido
   boolean isValido();

   // validarCampoEntero
   void validarCampoEntero();

   // validarCampoRangoNumerico
   void validarCampoRangoNumerico();

   // validarCampoEnteroPositivo
   void validarCampoEnteroPositivo();

   //validarCampoEnteroIndicador
   void validarCampoEnteroIndicador();

   //validarCampoEnteroEstado
   void validarCampoEnteroEstado();

   // validarCampoDouble
   void validarCampoDouble();

   // validarCampoDoublePositivo
   void validarCampoDoublePositivo();

   // validarCampoString
   void validarCampoString();

   // validarCampoFecha
   void validarCampoFecha();

   // validarCampoFecha
   void validarRangoFecha(String fechaInicio, String fechaFin);

   // reasignar
   void reasignar(String nombreCampo, String valorCampo);

   // asignarError
   void asignarError(String nombreCampo, String valorCampo,
                            String codigoError, String descripcionError,
                            String solucion);
}