package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class DestinacionTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("instrucciondDestinacion", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDestinacion", "java.lang.String", true,
                       VariableInfo.NESTED),
    };

  }
}