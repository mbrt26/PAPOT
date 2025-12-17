package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAudtoriaRepRentabilidadMarcaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("porcentajeIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("margenIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      
    };
  }
}