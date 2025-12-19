package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class LiquidaPagoMedioTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("vrFacturaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCambioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}