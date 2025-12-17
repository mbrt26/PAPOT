package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaClienteActivoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}