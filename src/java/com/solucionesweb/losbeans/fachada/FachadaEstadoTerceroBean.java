package com.solucionesweb.losbeans.fachada;

public class FachadaEstadoTerceroBean {

    //Propiedad
    private int estado;
    private String nombreEstado;

    //
    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado= nombreEstado;
    }

}
