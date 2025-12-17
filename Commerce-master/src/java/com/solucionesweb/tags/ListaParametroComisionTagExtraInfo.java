package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaParametroComisionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLucroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLucroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("diaInicialVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("diaFinalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}