package com.solucionesweb.tags;

import javax.servlet.jsp.tagext.*;

public class ListaInventarioCostoMarcaTagExtraInfo extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[]{
                    //
                    new VariableInfo("idLocalVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("idPluVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("existenciaSf2Var", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreCategoriaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombreMarcaVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("nombrePluVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrCostoInventarioVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrTotalImpoconsumoVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vrTotalCostoINDVar", "java.lang.String", true,
                    VariableInfo.NESTED),
                    new VariableInfo("vetVrCostoIvaVar", "java.lang.String", true,
                    VariableInfo.NESTED),};
    }
}
