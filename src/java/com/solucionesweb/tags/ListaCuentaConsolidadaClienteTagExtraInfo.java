package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCuentaConsolidadaClienteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

    // Propiedades
      new VariableInfo("tipoCarteraVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroDctosVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrSaldoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}