package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOTExternaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPerdidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPerdidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTerminadaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaInicioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadPedidaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idControlTipoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idControlVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("observacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSignoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTipoControlVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadTerminadaSaldoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTerminadoSaldoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}