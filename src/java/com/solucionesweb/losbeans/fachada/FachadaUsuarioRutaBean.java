package com.solucionesweb.losbeans.fachada;

public class FachadaUsuarioRutaBean {

    // Propiedades
    private double idUsuario;
    private String idRuta;
    private int estado;
    private int indicadorInicial;
    private int indicadorFinal;

    // Metodos
    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario ;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public String getIdRuta()
    {
        return idRuta;
    }

    public void setIdRuta( String idRuta )
    {
        this.idRuta = idRuta.trim();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public int getIndicadorInicial()
    {
        return indicadorInicial;
    }

    public void setIndicadorInicial( int indicadorInicial )
    {
        this.indicadorInicial = indicadorInicial;
    }

    public void setIndicadorInicialStr( String indicadorInicialStr )
    {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
    }

    public void setIndicadorInicial( String indicadorInicialStr )
    {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
    }

    public String getIndicadorInicialStr()
    {
        return new Integer(indicadorInicial).toString();
    }

    public int getIndicadorFinal()
    {
        return indicadorFinal;
    }

    public void setIndicadorFinal( int indicadorFinal )
    {
        this.indicadorFinal = indicadorFinal;
    }

    public void setIndicadorFinalStr( String indicadorFinalStr )
    {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public void setIndicadorFinal( String indicadorFinalStr )
    {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public String getIndicadorFinalStr()
    {
        return new Integer(indicadorFinal).toString();
    }

    public FachadaUsuarioRutaBean() { }

}