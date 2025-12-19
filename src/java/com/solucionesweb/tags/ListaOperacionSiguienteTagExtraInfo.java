package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOperacionSiguienteTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    new VariableInfo("idOperacionVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idOperacionSiguienteVar", "java.lang.String", true,
                    VariableInfo.NESTED),};
    }
}
