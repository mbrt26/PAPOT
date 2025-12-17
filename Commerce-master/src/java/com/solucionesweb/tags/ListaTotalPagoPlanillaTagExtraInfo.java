package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaTotalPagoPlanillaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("vrPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDescuentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteFuenteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteCreeVar", "java.lang.String", true, 
                       VariableInfo.NESTED)
    };
  }
}