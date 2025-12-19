package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaFechaTipoOrdenTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}