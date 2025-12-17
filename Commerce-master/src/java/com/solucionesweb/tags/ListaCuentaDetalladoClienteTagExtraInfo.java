package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCuentaDetalladoClienteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

    // Propiedades
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaVencimientoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("diasMoraVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrSaldoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrSaldoSFVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("indicadorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}