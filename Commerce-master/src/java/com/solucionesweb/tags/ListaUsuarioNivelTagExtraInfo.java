package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaUsuarioNivelTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idNivelVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreNivelVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}