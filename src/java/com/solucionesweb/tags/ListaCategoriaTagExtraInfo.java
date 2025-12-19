package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCategoriaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLineaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLineaCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}