package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaPagoLogTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idReciboVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("indicadorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoMedioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idMedioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrMedioDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaCobroCortaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idBancoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMedioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreBancoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}