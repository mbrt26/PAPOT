package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaVentaReferenciaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("codigoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreReferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrBaseVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoFacturadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadFacturadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTipoNegocioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("aliasUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
