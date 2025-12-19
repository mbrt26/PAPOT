package com.solucionesweb.losbeans.fachada;


public class FachadaLucroBonificacionUsuario {

    //
    private double idUsuario;
    private int idAno;
    private int idMes;
    private int idSeq;
    private int estado;

    //
    private String fechaCorte;

    //
    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario ;
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public void setIdAno( int idAno )
    {
        this.idAno = idAno ;
    }

    public int getIdAno()
    {
        return idAno;
    }

    public void setIdAno( String idAnoStr )
    {
        this.idAno = new Integer(idAnoStr).intValue();
    }

    public String getIdAnoStr()
    {
        return new Integer(idAno).toString();
    }

    public void setIdMes( int idMes )
    {
        this.idMes = idMes ;
    }

    public int getIdMes()
    {
        return idMes;
    }

    public void setIdMes( String idMesStr )
    {
        this.idMes = new Integer(idMesStr).intValue();
    }

    public String getIdMesStr()
    {
        return new Integer(idMes).toString();
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

    public void setFechaCorte( String fechaCorte )
    {
        this.fechaCorte = fechaCorte;
    }

    public String getFechaCorte()
    {
        return fechaCorte;
    }

    public int getMesFechaCorte() {

            return new Integer(getFechaCorte().substring(5, 7)).intValue();
    }

    public int getAnoFechaCorte() {

            return new Integer(getFechaCorte().substring(0, 4)).intValue();

    }
}
