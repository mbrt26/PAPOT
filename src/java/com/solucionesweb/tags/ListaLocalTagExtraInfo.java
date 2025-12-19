package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaLocalTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nitVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("razonSocialVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("direccionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("faxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}