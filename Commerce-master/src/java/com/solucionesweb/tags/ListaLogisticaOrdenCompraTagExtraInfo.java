package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaLogisticaOrdenCompraTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("tipoDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDctoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}