package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaClienteNuevoCOTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}