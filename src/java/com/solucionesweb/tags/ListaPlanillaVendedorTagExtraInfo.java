package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaPlanillaVendedorTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idVendedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPlanillaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDescuentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteFuenteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIcaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("aliasUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}