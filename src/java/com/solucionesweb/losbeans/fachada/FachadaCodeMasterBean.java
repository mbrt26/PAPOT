package com.solucionesweb.losbeans.fachada;

public class FachadaCodeMasterBean {

    // Propiedades
    private String idCode;
    private String nombreCode;
    private int tipoCode;
    private String motivo;

    // Metodos
    public void setIdCode( String idCode )
    {
        this.idCode = idCode ;
    }

    public String getIdCode()
    {
        return idCode;
    }

    public String getNombreCode()
    {
        return nombreCode;
    }

    public void setNombreCode( String nombreCode )
    {
        this.nombreCode = nombreCode.trim();
    }

    public int getTipoCode()
    {
        return tipoCode;
    }

    public String getTipoCodeStr()
    {
        return new Integer(tipoCode).toString();
    }

    public void setTipoCode( int tipoCode )
    {
        this.tipoCode = tipoCode ;
    }

    public void setTipoCode( String tipoCodeStr )
    {
        this.tipoCode = new Integer(tipoCodeStr).intValue() ;
    }

    public void setMotivo( String motivo )
    {
        this.motivo = motivo ;
    }

    public String getMotivo()
    {
        return motivo;
    }

    public FachadaCodeMasterBean() { }

}