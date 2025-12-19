package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaPedidoUsuarioFechaTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idLocalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLogVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSucursalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("direccionDespachoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("emailVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("faxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("contactoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("observacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadArticulosVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTeoricoTotalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("strNumeroOrdenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idEstadoTx", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEstadoTxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEmpresaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("numeroCotizacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}