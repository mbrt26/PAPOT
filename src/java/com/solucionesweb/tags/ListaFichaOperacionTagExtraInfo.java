package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaFichaOperacionTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    new VariableInfo("idClienteVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("referenciaClienteVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idOperacionVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idPluVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreReferenciaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("itemVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("textoEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("estadoVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idFichaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("referenciaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreOperacionVar", "java.lang.String", true,
                    VariableInfo.NESTED),};
    }
}
