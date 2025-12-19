package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class SeleccionaTerceroxIdTipoTerceroTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

      new VariableInfo("idClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTerceroFormatVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("tipoIdTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("digitoVerificacionVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idTipoTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idPersonaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idAutoRetenedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idRegimenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTerceroVar", "java.lang.String", true,
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
      new VariableInfo("idFormaPagoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreRegimenVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreTipoTerceroVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreCiudadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreDptoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idVendedorVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cupoCreditoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}