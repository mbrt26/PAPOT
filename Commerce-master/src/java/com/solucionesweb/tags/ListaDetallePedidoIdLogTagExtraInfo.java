package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaDetallePedidoIdLogTagExtraInfo extends TagExtraInfo {
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {

      new VariableInfo("idPluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("strIdReferenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombrePluVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idLocalSugeridoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idBodegaSugeridoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaUnitarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaOriginalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("cantidadArticulosVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaSinIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("vrVentaConIvaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("pesoTeoricoTotalVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("marcaArteClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("nombreArchivoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("comentarioVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("idEstadoTxVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("itemVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("existenciaVar", "java.lang.String", true,
                       VariableInfo.NESTED),
      new VariableInfo("porcentajeDescuentoVar", "java.lang.String", true,
                       VariableInfo.NESTED),
    };
  }
}