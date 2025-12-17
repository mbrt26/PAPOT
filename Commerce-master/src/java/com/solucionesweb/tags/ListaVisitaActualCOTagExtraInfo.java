package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaVisitaActualCOTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idEstadoVisitaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEstadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("horaVisitaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}