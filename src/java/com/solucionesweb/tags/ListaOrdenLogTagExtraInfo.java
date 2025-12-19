package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOrdenLogTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSucursalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idEstadoTxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadArticulosVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTeoricoTotalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}