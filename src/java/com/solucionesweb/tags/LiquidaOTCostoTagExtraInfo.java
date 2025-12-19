package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class LiquidaOTCostoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
        // Variable que retornan al JSP
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idFichaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}