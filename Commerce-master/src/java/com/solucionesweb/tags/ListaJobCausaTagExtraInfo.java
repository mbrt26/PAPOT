package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaJobCausaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idTipoCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCausaFormatoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}