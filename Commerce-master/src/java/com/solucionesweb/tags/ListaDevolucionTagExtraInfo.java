package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaDevolucionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrSubtotalVentaDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoConIvaDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("unidadVentaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED)
    };
  }
}