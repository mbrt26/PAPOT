package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaEscalaTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    new VariableInfo("idEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("itemVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreItemVar", "java.lang.String", true,
                    VariableInfo.NESTED),};
    }
}
