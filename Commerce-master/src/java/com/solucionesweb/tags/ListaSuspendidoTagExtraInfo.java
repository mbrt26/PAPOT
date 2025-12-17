package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaSuspendidoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idEstadoVisitaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("direccionTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoFijoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEmpresaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("ciudadTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaEntregaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idFichaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("ordenCompraVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadDf0Var", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("xColorPedidoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}