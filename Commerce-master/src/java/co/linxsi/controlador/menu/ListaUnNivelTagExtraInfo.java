package co.linxsi.controlador.menu;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class ListaUnNivelTagExtraInfo extends TagExtraInfo
{
  public VariableInfo[] getVariableInfo(TagData data)
  {
    return new VariableInfo[] { new VariableInfo("idOpcionVar", "java.lang.String", true, 0), new VariableInfo("nombreOpcionVar", "java.lang.String", true, 0), new VariableInfo("rutaOpcionVar", "java.lang.String", true, 0), new VariableInfo("idCheckedVar", "java.lang.String", true, 0), new VariableInfo("isCheckedVar", "java.lang.String", true, 0), new VariableInfo("idSubOpcionPadreVar", "java.lang.String", true, 0), new VariableInfo("numeroHijoVar", "java.lang.String", true, 0) };
  }
}