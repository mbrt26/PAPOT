package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaEscalaBaseTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    new VariableInfo("idEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idTipoEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idEscalaIndexVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idTextoEscalaIndexVar", "java.lang.String", true,
                    VariableInfo.NESTED),};
    }
}
