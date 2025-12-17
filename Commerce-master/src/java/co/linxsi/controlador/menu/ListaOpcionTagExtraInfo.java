package co.linxsi.controlador.menu;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class ListaOpcionTagExtraInfo extends TagExtraInfo
{
  public VariableInfo[] getVariableInfo(TagData data)
  {
    return new VariableInfo[] { new VariableInfo("idOpcionPadreVar", "java.lang.String", true, 0), new VariableInfo("nombreOpcionPadreVar", "java.lang.String", true, 0), new VariableInfo("rutaOpcionPadreVar", "java.lang.String", true, 0), new VariableInfo("iconoVar", "java.lang.String", true, 0), new VariableInfo("descripcionPadreVar", "java.lang.String", true, 0) };
  }
}