package com.solucionesweb.losbeans.fachada;

public class FachadaAgenteRteFuenteVrBase {

    //Propiedad
    private int idRteFuenteVrBase;
    private String nombreRteFuenteVrBase;

    //
    public void setIdRteFuenteVrBase( int idRteFuenteVrBase )
    {
        this.idRteFuenteVrBase = idRteFuenteVrBase ;
    }

    public int getIdRteFuenteVrBase()
    {
        return idRteFuenteVrBase;
    }

    public void setIdRteFuenteVrBase( String idRteFuenteVrBaseStr )
    {
        this.idRteFuenteVrBase = new Integer(idRteFuenteVrBaseStr).intValue();
    }

    public String getIdRteFuenteVrBaseStr()
    {
        return new Integer(idRteFuenteVrBase).toString();
    }

    public String getNombreRteFuenteVrBase() {
        return nombreRteFuenteVrBase;
    }

    public void setNombreRteFuenteVrBase(String nombreRteFuenteVrBase) {
        this.nombreRteFuenteVrBase= nombreRteFuenteVrBase;
    }

}
