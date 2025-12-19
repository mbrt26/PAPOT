package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaInventarioReferenciaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("strIdBodegaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}