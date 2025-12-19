package com.solucionesweb.losbeans.fachada;

public class FachadaAgenteRetencionIvaVrBase {

    //Propiedad
    private int idRteIvaVrBase;
    private String nombreRteIvaVrBase;

    //
    public void setIdRteIvaVrBase( int idRteIvaVrBase )
    {
        this.idRteIvaVrBase = idRteIvaVrBase ;
    }

    public int getIdRteIvaVrBase()
    {
        return idRteIvaVrBase;
    }

    public void setIdRteIvaVrBase( String idRteIvaVrBaseStr )
    {
        this.idRteIvaVrBase = new Integer(idRteIvaVrBaseStr).intValue();
    }

    public String getIdRteIvaVrBaseStr()
    {
        return new Integer(idRteIvaVrBase).toString();
    }

    public String getNombreRteIvaVrBase() {
        return nombreRteIvaVrBase;
    }

    public void setNombreRteIvaVrBase(String nombreRteIvaVrBase) {
        this.nombreRteIvaVrBase= nombreRteIvaVrBase;
    }

}
