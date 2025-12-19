package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOperacionTouchTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

        // Variable que retornan al JSP
      new VariableInfo("idOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idColorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}