package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaClienteCotizacionArticulosRetirarTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("strIdReferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLineaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreSublineaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}