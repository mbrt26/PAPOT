package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;


public class ListaAudtoriaSinVentasTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrGeneralVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrMayoristaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("factorDespachoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrImpoconsumoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
	  new VariableInfo("vrCostoIndVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}

