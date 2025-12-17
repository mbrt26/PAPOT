package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCostoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}