package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaFormaPagoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idCodeVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCodeVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}