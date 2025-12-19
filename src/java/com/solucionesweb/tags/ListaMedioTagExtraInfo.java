package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaMedioTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idMedioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreMedioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}
