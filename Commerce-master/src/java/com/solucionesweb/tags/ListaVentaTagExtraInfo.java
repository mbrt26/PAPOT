package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaVentaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrImpoconsumoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrFacturaVentaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrDescuentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrRteFuenteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTipoNegocioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("aliasUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("horaTxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
     new VariableInfo("prefijoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
     new VariableInfo("idCajaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
