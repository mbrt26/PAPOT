package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaResurtidoAjusteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaEntregaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("diasHistoriaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("diasInventarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadArticulosVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoMVVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}