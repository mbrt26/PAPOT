package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaInventarioMPMaquinaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      //
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreItemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaPesoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaCantidadTotalVar", "java.lang.String", true,
                       VariableInfo.AT_END),
      new VariableInfo("existenciaPesoTotalVar", "java.lang.String", true,
                       VariableInfo.AT_END),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}