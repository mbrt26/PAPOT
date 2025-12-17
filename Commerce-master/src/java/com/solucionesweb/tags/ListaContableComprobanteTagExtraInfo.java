package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaContableComprobanteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idComprobanteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}