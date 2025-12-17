package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaContactoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreContactoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoContactoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}