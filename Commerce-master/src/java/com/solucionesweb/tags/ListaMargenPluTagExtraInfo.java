package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaMargenPluTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLineaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLineaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrIvaVentaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoINDVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeMargenINDVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}