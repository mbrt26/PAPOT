package com.solucionesweb.losbeans.fachada;

public class FachadaLocalIp {

    //Propiedad
    private int idLocal;
    private String ip;
    private String hostName;
    private int estado;
    private int idSeq;  
    private int idLocalPadre;    
    private String bdNameContable;    
    private String puertoHttp;

    // Metodos
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public String getIp() {
        return ip.trim();
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setIdSeq( int idSeq )
    {
        this.idSeq = idSeq ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public void setIdSeq( String idSeqStr )
    {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public void setIdLocalPadre( int idLocalPadre )
    {
        this.idLocalPadre = idLocalPadre ;
    }

    public int getIdLocalPadre()
    {
        return idLocalPadre;
    }

    public void setIdLocalPadre( String idLocalPadreStr )
    {
        this.idLocalPadre = new Integer(idLocalPadreStr).intValue();
    }

    public String getIdLocalPadreStr()
    {
        return new Integer(idLocalPadre).toString();
    }

    public String getBdNameContable() {
        return bdNameContable;
    }

    public void setBdNameContable(String bdNameContable) {
        this.bdNameContable = bdNameContable;
    }

    public String getPuertoHttp() {
        return puertoHttp.substring(0,4);
    }

    public void setPuertoHttp(String puertoHttp) {
        this.puertoHttp = puertoHttp;
    }
}