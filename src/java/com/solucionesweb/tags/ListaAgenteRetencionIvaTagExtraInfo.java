package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAgenteRetencionIvaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}