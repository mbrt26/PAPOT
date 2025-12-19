package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaInventarioOperacionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPerdidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPerdidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreReferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTerminadaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadEntregadaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadFacturadaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoEntregadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoFacturadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaPesoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}