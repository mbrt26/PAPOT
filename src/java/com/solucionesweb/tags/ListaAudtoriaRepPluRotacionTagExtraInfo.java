package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAudtoriaRepPluRotacionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("factorDespachoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("rotacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
   
    };
  }
}
