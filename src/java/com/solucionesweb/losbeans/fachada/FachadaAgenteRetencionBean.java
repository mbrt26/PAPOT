package com.solucionesweb.losbeans.fachada;

public class FachadaAgenteRetencionBean {

    //Propiedad
    private int idAutoRetenedor;
    private String nombreAutoRetenedor;

    //
    public void setIdAutoRetenedor( int idAutoRetenedor )
    {
        this.idAutoRetenedor = idAutoRetenedor ;
    }

    public int getIdAutoRetenedor()
    {
        return idAutoRetenedor;
    }

    public void setIdAutoRetenedor( String idAutoRetenedorStr )
    {
        this.idAutoRetenedor = new Integer(idAutoRetenedorStr).intValue();
    }

    public String getIdAutoRetenedorStr()
    {
        return new Integer(idAutoRetenedor).toString();
    }

    public String getNombreAutoRetenedor() {
        return nombreAutoRetenedor;
    }

    public void setNombreAutoRetenedor(String nombreAutoRetenedor) {
        this.nombreAutoRetenedor= nombreAutoRetenedor;
    }

}
