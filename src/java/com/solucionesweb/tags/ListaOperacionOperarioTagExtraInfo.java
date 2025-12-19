package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOperacionOperarioTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}