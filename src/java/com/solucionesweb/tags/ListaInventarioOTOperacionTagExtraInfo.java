package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaInventarioOTOperacionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      //
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaPesoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreItemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadTotalVar", "java.lang.String", true,
                       VariableInfo.AT_END),
      new VariableInfo("existenciaPesoTotalVar", "java.lang.String", true,
                       VariableInfo.AT_END),
      new VariableInfo("nombreOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
    };
  }
}