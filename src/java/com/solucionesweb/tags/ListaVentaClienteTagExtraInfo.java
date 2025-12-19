package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaVentaClienteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      // Variable que retornan al JSP
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("indicadorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrFacturaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("ordenCompraVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("kgVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
