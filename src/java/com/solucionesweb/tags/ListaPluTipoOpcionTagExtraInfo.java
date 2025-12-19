package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaPluTipoOpcionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}