package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaResurtidoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCategoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMarcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaActualVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaActualSf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("letraEstiloVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidoStrVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoStrVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoNegociadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidoSf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("factorDespachoVar", "java.lang.String", true,
                       VariableInfo.NESTED)
    };
  }
}