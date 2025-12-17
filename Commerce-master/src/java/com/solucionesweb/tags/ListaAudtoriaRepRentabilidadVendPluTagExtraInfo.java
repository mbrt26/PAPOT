package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAudtoriaRepRentabilidadVendPluTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("aliasUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idVendedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoMvVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("margenIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}