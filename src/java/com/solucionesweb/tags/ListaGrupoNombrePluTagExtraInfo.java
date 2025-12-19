package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaGrupoNombrePluTagExtraInfo extends TagExtraInfo {
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
      new VariableInfo("vrGeneralVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrMayoristaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrSucursalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}