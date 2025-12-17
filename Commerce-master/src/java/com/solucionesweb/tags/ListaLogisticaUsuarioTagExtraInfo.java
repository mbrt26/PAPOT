package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaLogisticaUsuarioTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("placaVehiculoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaLogisticaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}