package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaInventarioOTExternoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      //
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
      new VariableInfo("idFichaVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
      new VariableInfo("referenciaClientePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
      new VariableInfo("nombreOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaPesoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadTotalVar", "java.lang.String", true,
                       VariableInfo.AT_END),
      new VariableInfo("existenciaPesoTotalVar", "java.lang.String", true,
                       VariableInfo.AT_END),
    };
  }
}