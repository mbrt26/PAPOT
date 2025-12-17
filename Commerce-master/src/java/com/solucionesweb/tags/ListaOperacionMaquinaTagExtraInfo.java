package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class ListaOperacionMaquinaTagExtraInfo extends TagExtraInfo {

    @Override
    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{new VariableInfo("idOperacionVar", "java.lang.String", true, 0), new VariableInfo("nombreEscalaVar", "java.lang.String", true, 0), new VariableInfo("idEscalaVar", "java.lang.String", true, 0), new VariableInfo("itemVar", "java.lang.String", true, 0), new VariableInfo("nombreMaquinaVar", "java.lang.String", true, 0)};
    }
}
