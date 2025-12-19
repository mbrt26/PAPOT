package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOTCostoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
        // Variable que retornan al JSP
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idFichaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemPadreVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoBaseMATVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoBaseCIFVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoMODVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
