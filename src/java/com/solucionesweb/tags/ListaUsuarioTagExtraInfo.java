package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaUsuarioTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("claveVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idNivelVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("direccionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaCambioClaveVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("emailVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("aliasUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSeqVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}