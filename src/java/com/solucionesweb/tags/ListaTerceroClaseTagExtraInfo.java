package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class ListaTerceroClaseTagExtraInfo
  extends TagExtraInfo
{
  public VariableInfo[] getVariableInfo(TagData data)
  {
      return new VariableInfo[]{
          new VariableInfo("idClaseVar", "java.lang.String", true,
          VariableInfo.NESTED),
          new VariableInfo("nombreClaseVar", "java.lang.String", true,
          VariableInfo.NESTED),};
  }
}
