 package com.solucionesweb.tags;

 import javax.servlet.jsp.tagext.*;

 public class ListaResurtidoEmpresaTagExtraInfo extends TagExtraInfo {
   public VariableInfo[] getVariableInfo(TagData data) {
     return new VariableInfo[] {
       new VariableInfo("idPluVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("idLocalVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("nombrePluVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("vrCostoVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("vrCostoNegociadoVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("existenciaVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("factorDespachoVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("cantidadPedidaVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("existenciaBodegaVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("cantidadPvdVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("cantidadInventarioMaximoVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("cantidadPedidoUVVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("cantidadPedidoUDVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("letraEstiloVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("cantidadBonificadaVar", "java.lang.String", true,
                        VariableInfo.NESTED),
       new VariableInfo("porcentajeIvaVar", "java.lang.String", true,
                        VariableInfo.NESTED),
     };
   }
 }