package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaContableDctoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDebitoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCreditoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDiferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idReciboVar", "java.lang.String", true,
                       VariableInfo.NESTED),

    };
  }
}