package com.solucionesweb.losbeans.fachada;

public class FachadaListaPrecioBean {

    // Propiedades
    private String strIdLista;
    private int idLocal;
    private String nombreLista;

    //
    public String getStrIdLista()
    {
        return strIdLista;
    }

    public void setStrIdLista( String strIdLista )
    {
        this.strIdLista = strIdLista.trim() ;
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr)
    {
    	this.idLocal = new Integer (idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public String getNombreLista()
    {
        return nombreLista;
    }

    public void setNombreLista( String nombreLista )
    {
        this.nombreLista = nombreLista ;
    }

    public FachadaListaPrecioBean() { }

}