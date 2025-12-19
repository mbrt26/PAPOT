package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaUnCategoriaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

      new VariableInfo("idLineaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
