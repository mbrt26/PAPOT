package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;


public class FachadaColaboraLocalBean implements IConstantes {

    // Propiedades
    private int idLocal;
    private String nit;
    private String razonSocial;
    private String nombreLocal;
    private String direccion;
    private String telefono;
    private String fax;

    // Metodos
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return this.idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue() ;
    }

    public String getIdLocalStr()
    {
        return new Integer(this.idLocal).toString();
    }

    public void setNit( String nit )
    {
        this.nit = nit ;
    }

    public String getNit()
    {
        return this.nit;
    }

    public void setRazonSocial( String razonSocial )
    {
        this.razonSocial = razonSocial ;
    }

    public String getRazonSocial()
    {
        return this.razonSocial;
    }

    public void setNombreLocal( String nombreLocal )
    {
        this.nombreLocal = nombreLocal ;
    }

    public String getNombreLocal()
    {
        return this.nombreLocal;
    }

    public void setDireccion( String direccion )
    {
        this.direccion = direccion ;
    }

    public String getDireccion()
    {
        if (this.direccion == null) {
           return STRINGVACIO;
        }
        return this.direccion;
    }

    public void setTelefono( String telefono )
    {
        this.telefono = telefono ;
    }

    public String getTelefono()
    {
        if (this.telefono == null) {
           return STRINGVACIO;
        }
        return this.telefono;
    }

    public void setFax( String fax )
    {
        this.fax = fax ;
    }

    public String getFax()
    {
        if (this.fax == null) {
           return STRINGVACIO;
        }
        return this.fax;
    }

    public FachadaColaboraLocalBean() { }

}