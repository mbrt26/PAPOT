package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaFechaVisitaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}