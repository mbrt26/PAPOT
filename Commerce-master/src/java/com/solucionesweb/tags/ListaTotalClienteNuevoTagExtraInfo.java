package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaTotalClienteNuevoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("nombreUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("totalNuevoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}