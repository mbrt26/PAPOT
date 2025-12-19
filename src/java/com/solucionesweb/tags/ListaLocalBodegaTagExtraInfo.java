package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaLocalBodegaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idBodegaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreBodegaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}