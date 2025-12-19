package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaComprobanteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("observacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}