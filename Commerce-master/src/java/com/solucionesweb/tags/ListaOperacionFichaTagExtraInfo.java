package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaOperacionFichaTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    new VariableInfo("idClienteVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idReferenciaClienteVar", "java.lang.String", true,
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
                    new VariableInfo("nombreEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idTipoEscalaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idEscalaIndexVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idTextoEscalaIndexVar", "java.lang.String", true,
                    VariableInfo.NESTED),};
    }
}
