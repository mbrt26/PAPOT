package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaEanTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("eanVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSeqVar", "java.lang.String", true,
                       VariableInfo.NESTED),

    };
  }
}