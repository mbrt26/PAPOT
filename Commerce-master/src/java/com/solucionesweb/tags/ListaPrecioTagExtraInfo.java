package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaPrecioTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idListaPrecioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreListaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idListaBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("factorBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),

    };
  }
} 