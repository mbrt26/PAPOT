package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class LiquidaOTExternaTagExtraInfo extends TagExtraInfo {
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
      new VariableInfo("cantidadPedidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idControlVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),                       
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}