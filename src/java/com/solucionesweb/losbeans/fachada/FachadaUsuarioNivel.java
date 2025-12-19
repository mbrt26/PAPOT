package com.solucionesweb.losbeans.fachada;


public class FachadaUsuarioNivel {

    //Propiedad
    private int idNivel;
    private String nombreNivel;

    //
    public void setIdNivel( int idNivel )
    {
        this.idNivel = idNivel ;
    }

    public int getIdNivel()
    {
        return idNivel;
    }

    public void setIdNivel( String idNivelStr )
    {
        this.idNivel = new Integer(idNivelStr).intValue();
    }

    public String getIdNivelStr()
    {
        return new Integer(idNivel).toString();
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }
}
