package com.solucionesweb.losbeans.fachada;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaTipoOrden implements IConstantes {

    //Propiedad
    private int idTipoOrden;
    private String nombreTipoOrden;
    private int estado;
    private double signo;
    private int idSeq;
    private int idAlcance;

    //
    public void setIdTipoOrden( int idTipoOrden )
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public void setIdTipoOrden( String idTipoOrdenStr )
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
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

    public void setSigno( double signo )
    {
        this.signo = signo ;
    }

    public double getSigno()
    {
        return signo;
    }

    public void setSigno( String signoStr )
    {
        this.signo = new Double(signoStr).doubleValue();
    }

    public String getSignoStr()
    {
        return new Double(signo).toString();
    }

    public String getNombreTipoOrden() {
        return nombreTipoOrden;
    }

    public String getNombreTipoOrdenMayuscula() {
        return getNombreTipoOrden().toUpperCase();
    }

    public void setNombreTipoOrden(String nombreTipoOrden) {
        this.nombreTipoOrden = nombreTipoOrden;
    }

    public void setIdAlcance( int idAlcance )
    {
        this.idAlcance = idAlcance ;
    }

    public int getIdAlcance()
    {
        return idAlcance;
    }

    public void setIdAlcance( String idAlcanceStr )
    {
        this.idAlcance = new Integer(idAlcanceStr).intValue();
    }
}
