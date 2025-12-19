package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaRegimenTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idRegimenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreRegimenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
