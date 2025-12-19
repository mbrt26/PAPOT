package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class listaControlAgendaEstadoTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idUsuarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idSucursalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPeriodoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("fechaVisitaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("estadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("direccionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("telefonoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEmpresaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("ciudadDireccionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("departamentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreEstadoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}