package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOrdenSubcuentaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSubcuentaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idAsientoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreSubcuentaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}