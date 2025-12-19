package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOrdenClienteTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {

        // Variable que retornan al JSP
    return new VariableInfo[] {
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idEstadoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreReferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idFichaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}