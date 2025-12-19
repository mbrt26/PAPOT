package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAgenteRetencionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idAutoRetenedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreAutoRetenedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}