package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOperacionCostoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPeriodoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}