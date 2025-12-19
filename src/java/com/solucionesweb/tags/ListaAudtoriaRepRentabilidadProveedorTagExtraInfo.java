package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAudtoriaRepRentabilidadProveedorTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoMvVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("margenIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),

    };
  }
}
