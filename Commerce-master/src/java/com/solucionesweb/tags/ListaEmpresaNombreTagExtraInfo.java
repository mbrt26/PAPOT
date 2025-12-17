package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaEmpresaNombreTagExtraInfo extends TagExtraInfo {
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
      new VariableInfo("idClienteSinFormatoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}