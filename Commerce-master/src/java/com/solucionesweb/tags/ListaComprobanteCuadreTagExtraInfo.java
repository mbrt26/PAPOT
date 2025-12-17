package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaComprobanteCuadreTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("fechaCuadreVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEstadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("saldoInicialVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrIngresoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrEgresoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("saldoFinalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
     
    };
  }
}

