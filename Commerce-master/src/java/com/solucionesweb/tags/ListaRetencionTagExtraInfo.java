package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class ListaRetencionTagExtraInfo
  extends TagExtraInfo
{
  public VariableInfo[] getVariableInfo(TagData data)
  {
    return new VariableInfo[] { 
        new VariableInfo("idConceptoVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
        new VariableInfo("idSubcuentaVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
        new VariableInfo("idPersonaVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
        new VariableInfo("nombreConceptoVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
        new VariableInfo("porcentajeRetencionVar", "java.lang.String", true,  
                VariableInfo.NESTED),
        new VariableInfo("vrBaseRetencionVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
        new VariableInfo("estadoVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
        new VariableInfo("idSeqVar", "java.lang.String", true,
                 VariableInfo.NESTED),
        new VariableInfo("idTipoOrdenAlcanceVar", "java.lang.String", true, 
                 VariableInfo.NESTED),
    };
  }
}
