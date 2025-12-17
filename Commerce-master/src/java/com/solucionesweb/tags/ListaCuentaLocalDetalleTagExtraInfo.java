package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaCuentaLocalDetalleTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

    // Propiedades
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
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
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDescuentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteFuenteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDiferenciaDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteCreeVar", "java.lang.String", true, 
                       VariableInfo.NESTED),
      new VariableInfo("fe", "java.lang.String", true, 
                       VariableInfo.NESTED),
      new VariableInfo("letraEstiloVar", "java.lang.String", true,
                       VariableInfo.NESTED)
    };
  }
}