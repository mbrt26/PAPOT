package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaDctoEstadoActualTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("estadoRegistroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEstadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}