package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOTProductivoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
        // Variable que retornan al JSP
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemPadreVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTerminadaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPerdidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadMinutoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreItemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
