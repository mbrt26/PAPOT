package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaPrecioPluTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("strIdReferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("strUnidadMedidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}