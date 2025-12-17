package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaDptoCiudadTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idCiudadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCiudadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreDptoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
