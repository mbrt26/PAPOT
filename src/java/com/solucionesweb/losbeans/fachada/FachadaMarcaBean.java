package com.solucionesweb.losbeans.fachada;

public class FachadaMarcaBean
{

    //
    private int idMarca;
    private String nombreMarca;
    private int estado;
    private int idSeq;

    //
    private int idLocal;
    private int idBodega;

    //
    public void setIdSeq (int idSeq)
    {
        this.idSeq = idSeq ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public void setIdSeq (String idSeqStr)
    {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public void setIdMarca( int idMarca )
    {
        this.idMarca = idMarca ;
    }

    public int getIdMarca()
    {
        return idMarca;
    }

    public void setIdMarca( String idMarcaStr )
    {
        this.idMarca = new Integer(idMarcaStr).intValue();
    }

    public String getIdMarcaStr()
    {
        return new Integer(idMarca).toString();
    }

    public String getNombreMarca()
    {
        return nombreMarca;
    }

    public void setNombreMarca( String nombreMarca )
    {
        this.nombreMarca = nombreMarca;
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
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setIdBodega (int idBodega)
    {
        this.idBodega = idBodega ;
    }

    public int getIdBodega()
    {
        return idBodega;
    }

    public void setIdBodega (String idBodegaStr)
    {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }

    public String getIdBodegaStr()
    {
        return new Integer(idBodega).toString();
    }

    public void setIdLocal (int idLocal)
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal (String idLocalStr)
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public FachadaMarcaBean() { }

}