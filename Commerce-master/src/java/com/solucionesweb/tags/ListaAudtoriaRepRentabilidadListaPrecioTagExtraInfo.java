package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaAudtoriaRepRentabilidadListaPrecioTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    new VariableInfo("idListaPrecioVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idLocalVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrCostoMvVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrIvaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrPagoVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrBaseVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrCostoIndVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("margenIndVar", "java.lang.String", true,
                    VariableInfo.NESTED),
        };
    }
}
