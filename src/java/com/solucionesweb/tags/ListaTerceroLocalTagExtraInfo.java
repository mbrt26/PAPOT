package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaTerceroLocalTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEmpresaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("direccionTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idDptoCiudadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoFijoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoCelularVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoFaxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("emailVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("contactoTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idVendedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSeqVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}

