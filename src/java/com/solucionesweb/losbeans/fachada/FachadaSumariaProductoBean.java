package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaSumariaProductoBean implements IConstantes {

    //
    private String strIdReferencia;
    private String nombrePlu;

    //
    public String getStrIdReferencia()
    {
        return this.strIdReferencia;
    }

    public void setStrIdReferencia( String strIdReferencia )
    {
        this.strIdReferencia = strIdReferencia;
    }

    public String getNombrePlu()
    {
        return this.nombrePlu;
    }

    public void setNombrePlu( String nombrePlu )
    {
        this.nombrePlu = nombrePlu;
    }

    public FachadaSumariaProductoBean() { }

}