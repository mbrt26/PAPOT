package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaClienteControlrAgendaNitTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idClienteVar", "java.lang.String", true,
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
      new VariableInfo("contactoTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoFaxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoCelularVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("listaPrecioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("emailVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idFormaPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cupoCreditoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}