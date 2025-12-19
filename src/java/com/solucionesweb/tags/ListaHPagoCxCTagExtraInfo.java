package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaHPagoCxCTagExtraInfo extends TagExtraInfo {
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
      new VariableInfo("fechaPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteFuenteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDescuentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPeriodoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoDian", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPlanillaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}