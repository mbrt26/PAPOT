package com.solucionesweb.losbeans.fachada;

public class FachadaAgenteRetencionIva {

    //Propiedad
    private int idRteIva;
    private String nombreRteIva;

    //
    public void setIdRteIva( int idRteIva )
    {
        this.idRteIva = idRteIva ;
    }

    public int getIdRteIva()
    {
        return idRteIva;
    }

    public void setIdRteIva( String idRteIvaStr )
    {
        this.idRteIva = new Integer(idRteIvaStr).intValue();
    }

    public String getIdRteIvaStr()
    {
        return new Integer(idRteIva).toString();
    }

    public String getNombreRteIva() {
        return nombreRteIva;
    }

    public void setNombreRteIva(String nombreRteIva) {
        this.nombreRteIva= nombreRteIva;
    }

}
