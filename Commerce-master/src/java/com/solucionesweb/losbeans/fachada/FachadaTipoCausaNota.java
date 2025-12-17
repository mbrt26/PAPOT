package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de java
import java.io.*;

public class FachadaTipoCausaNota implements Serializable, IConstantes {


    // Propiedades del Usuario
    private String nombreCausa;
    private int idCausa;
    private int estado;
    private int idSeq;


    public String getNombreCausa()
    {
        return nombreCausa.toUpperCase();
    }

    public void setNombreCausa( String nombreCausa )
    {
        this.nombreCausa = nombreCausa;
    }

   public int getIdCausa()
    {
        return idCausa;
    }

    public String getIdCausaStr()
    {
        return new Integer(idCausa).toString();
    }

    public void setIdCausa( int idCausa )
    {
        this.idCausa = idCausa ;
    }

    public void setIdCausa( String idCausaStr )
    {
        this.idCausa = new Integer(idCausaStr).intValue() ;
    }


    public int getEstado()
    {
        return estado;
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

   public FachadaTipoCausaNota() { }

}