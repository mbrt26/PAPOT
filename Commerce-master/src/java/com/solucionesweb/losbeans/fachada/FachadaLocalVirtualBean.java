package com.solucionesweb.losbeans.fachada;

public class FachadaLocalVirtualBean {

    // Propiedades
    private int idLocal;
    private int idLocalVirtual;
    private int vrVentaSinIva;
    private int estado;

    // Metodos
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal;
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

    public int getIdLocalVirtual()
    {
        return idLocalVirtual;
    }

    public void setIdLocalVirtual( String idLocalVirtualStr )
    {
        this.idLocalVirtual = new Integer(idLocalVirtualStr).intValue();
    }

    public void setVrVentaSinIva( int vrVentaSinIva )
    {
        this.vrVentaSinIva = vrVentaSinIva;
    }

    public int getVrVentaSinIva()
    {
        return vrVentaSinIva;
    }

    public void setVrVentaSinIva( String vrVentaSinIvaStr )
    {
        this.vrVentaSinIva = new Integer(vrVentaSinIvaStr).intValue();
    }

    public String getVrVentaSinIvaStr()
    {
        return new Integer(vrVentaSinIva).toString();
    }

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

    public FachadaLocalVirtualBean() { }

}