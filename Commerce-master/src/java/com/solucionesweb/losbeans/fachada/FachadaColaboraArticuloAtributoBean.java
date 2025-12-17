package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaColaboraArticuloAtributoBean implements IConstantes {

    //
    private String strIdReferencia;
    private String nombreLinea;
    private String nombreSublinea;
    private String nombrePlu;
    private double vrVentaUnitario;

    //
    public String getStrIdReferencia()
    {
        return this.strIdReferencia;
    }

    public void setStrIdReferencia( String strIdReferencia )
    {
        this.strIdReferencia = strIdReferencia;
    }

    public String getIdLinea()
    {
        return "'" + this.getStrIdReferencia().substring(1,3) + "'" ;
    }

    public String getIdSublinea()
    {
        return "'" + this.getStrIdReferencia().substring(3,5) + "'" ;
    }

    public String getIdMaterial()
    {
        return this.getStrIdReferencia().substring(5,5);
    }

    public String getIdTamano()
    {
        return this.getStrIdReferencia().substring(6,7);
    }

    public String getIdArte()
    {
        return this.getStrIdReferencia().substring(8,9);
    }

    public String getIdRelieve()
    {
        return this.getStrIdReferencia().substring(10,10);
    }

    public String getIdAcabado()
    {
        return this.getStrIdReferencia().substring(11,12);
    }

    public String getIdPintura()
    {
        return this.getStrIdReferencia().substring(13,14);
    }

    public String getNombreLinea()
    {
        return this.nombreLinea;
    }

    public void setNombreLinea( String nombreLinea )
    {

        if (nombreLinea == null) {
           this.nombreLinea = STRINGVACIO;
        } else {
        this.nombreLinea = nombreLinea ;
        }

    }

    public String getNombreSublinea()
    {
        return this.nombreSublinea;
    }

    public void setNombreSublinea( String nombreSublinea )
    {
        if (nombreSublinea == null) {
           this.nombreSublinea = STRINGVACIO;
        } else {
        this.nombreSublinea = nombreSublinea ;
        }
    }

    public String getNombrePlu()
    {
        return this.nombrePlu;
    }

    public void setNombrePlu( String nombrePlu )
    {
        this.nombrePlu = nombrePlu;
    }

    public void setVrVentaUnitario( double vrVentaUnitario )
    {
        this.vrVentaUnitario = vrVentaUnitario ;
    }

    public double getVrVentaUnitario()
    {
        return vrVentaUnitario;
    }

    public void setVrVentaUnitario( String vrVentaUnitarioStr )
    {
        this.vrVentaUnitario = new Double(vrVentaUnitarioStr).doubleValue() ;
    }

    public String getVrVentaUnitarioStr()
    {
        return new Double(vrVentaUnitario).toString();
    }

    public FachadaColaboraArticuloAtributoBean() { }

}