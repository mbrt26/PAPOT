package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOTUnMaquinaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemPadreVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPerdidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPerdidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaEntregaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTerminadaDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeTerminadoDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreItemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPendienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPendienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),

    };
  }
}