package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaResurtidoPluTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("acumuladoVentaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVentaPendienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTrasladoPendienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadDespachoPendienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadCompraPendienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPvdVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadInventarioMaximoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidoSugeridoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("letraEstiloVar", "java.lang.String", true,
                       VariableInfo.NESTED)
    };
  }
}

