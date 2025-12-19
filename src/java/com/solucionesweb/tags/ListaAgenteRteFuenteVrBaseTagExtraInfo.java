package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAgenteRteFuenteVrBaseTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idRteFuenteVrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreRteFuenteVrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}