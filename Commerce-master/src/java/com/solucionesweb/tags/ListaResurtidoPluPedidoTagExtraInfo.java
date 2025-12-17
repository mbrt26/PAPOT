package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaResurtidoPluPedidoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("cantidadPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrCostoPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}

