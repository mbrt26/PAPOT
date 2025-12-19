package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCausaNotaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}