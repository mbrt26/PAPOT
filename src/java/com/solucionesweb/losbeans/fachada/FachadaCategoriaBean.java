package com.solucionesweb.losbeans.fachada;

public class FachadaCategoriaBean {

    // Propiedades
    private int idLinea;
    private int idCategoria;
    private String nombreCategoria;
    private int estado;
    private String nombreLinea;
    private int idSeq;
    private String IdLineaCategoria;
    private String nombreLocal;

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

    public void setIdLinea (int idLinea)
    {
        this.idLinea = idLinea ;
    }

    public int getIdLinea()
    {
        return idLinea;
    }

    public void setIdLinea (String idLineaStr)
    {
        this.idLinea = new Integer(idLineaStr).intValue();
    }

    public String getIdLineaStr()
    {
        return new Integer(idLinea).toString();
    }

    public void setIdCategoria( int idCategoria )
    {
        this.idCategoria = idCategoria ;
    }

    public int getIdCategoria()
    {
        return idCategoria;
    }

    public void setIdCategoria( String idCategoriaStr)
    {
    	this.idCategoria = new Integer (idCategoriaStr).intValue();
    }

    public String getIdCategoriaStr()
    {
        return new Integer(idCategoria).toString();
    }

    public String getNombreCategoria()
    {
        return nombreCategoria;
    }

    public void setNombreCategoria( String nombreCategoria )
    {
        this.nombreCategoria = nombreCategoria.toUpperCase() ;
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


    public FachadaCategoriaBean() { }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
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

    /**
     * @return the IdLineaCategoria
     */
    public String getIdLineaCategoria() {
        return IdLineaCategoria;
    }

    /**
     * @param IdLineaCategoria the IdLineaCategoria to set
     */
    public void setIdLineaCategoria(String IdLineaCategoria) {
        this.IdLineaCategoria = IdLineaCategoria;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

  
    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    
}