package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAudtoriaRepRentabilidadClienteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoMvVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("margenClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}