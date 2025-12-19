package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCentroTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}