package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class LiquidaOTTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

        // Variable que retornan al JSP
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTerminadaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPerdidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaInicioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaFinVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idCausaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTaraVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idControlVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
      new VariableInfo("idDctoNitCCVar", "java.lang.String", true,
                       VariableInfo.NESTED),      
      new VariableInfo("idOrdenCruceVar", "java.lang.String", true,
                       VariableInfo.NESTED),        
    };
  }
}