package com.solucionesweb.losbeans.fachada;


public class FachadaLucroBonificacionTope {

    //
    private double idLucro;
    private String nombreLucro;
    private double topeInicial;
    private double topeFinal;
    private double porcentaje;
    private int estado;
    private int idSeq;

    //
    public void setIdLucro( double idLucro )
    {
        this.idLucro = idLucro ;
    }

    public double getIdLucro()
    {
        return idLucro;
    }

    public void setIdLucro( String idLucroStr )
    {
        this.idLucro = new Double(idLucroStr).doubleValue();
    }

    public String getIdLucroStr()
    {
        return new Double(idLucro).toString();
    }

    public void setNombreLucro( String nombreLucro )
    {
        this.nombreLucro = nombreLucro ;
    }

    public String getNombreLucro()
    {
        return nombreLucro;
    }

    public void setTopeInicial( double topeInicial )
    {
        this.topeInicial = topeInicial ;
    }

    public double getTopeInicial()
    {
        return topeInicial;
    }

    public void setTopeInicial( String topeInicialStr )
    {
        this.topeInicial = new Double(topeInicialStr).doubleValue() ;
    }

    public String getTopeInicialStr()
    {
        return new Double(topeInicial).toString();
    }

    public void setTopeFinal( double topeFinal )
    {
        this.topeFinal = topeFinal ;
    }

    public double getTopeFinal()
    {
        return topeFinal;
    }

    public void setTopeFinal( String topeFinalStr )
    {
        this.topeFinal = new Double(topeFinalStr).doubleValue() ;
    }

    public String getTopeFinalStr()
    {
        return new Double(topeFinal).toString();
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

    public void setPorcentaje( double porcentaje )
    {
        this.porcentaje = porcentaje ;
    }

    public double getPorcentaje()
    {
        return porcentaje;
    }

    public void setPorcentaje( String porcentajeStr )
    {
        this.porcentaje = new Double(porcentajeStr).doubleValue() ;
    }

    public String getPorcentajeStr()
    {
        return new Double(porcentaje).toString();
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
}
