package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaFechaVisitaVendedorTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaVisitaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}