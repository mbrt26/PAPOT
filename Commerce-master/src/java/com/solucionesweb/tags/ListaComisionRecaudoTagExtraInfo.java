package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaComisionRecaudoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idFraVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idReciboVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrPagadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDiasVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrComisionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("aliasUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
     
    };
  }
}
