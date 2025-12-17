package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAgenteRteIvaVrBaseTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idRteIvaVrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreRteIvaVrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}