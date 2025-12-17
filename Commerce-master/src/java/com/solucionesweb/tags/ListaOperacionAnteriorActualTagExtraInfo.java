package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOperacionAnteriorActualTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

        // Variable que retornan al JSP
      new VariableInfo("idOperacionAnteriorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionAnteriorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperacionActualVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionActualVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}