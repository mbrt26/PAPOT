package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaResurtidoLocalTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadArticulosVar", "java.lang.String", true,
                       VariableInfo.NESTED),

    };
  }
}