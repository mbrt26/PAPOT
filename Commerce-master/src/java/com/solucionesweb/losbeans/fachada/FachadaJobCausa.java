package com.solucionesweb.losbeans.fachada;

import com.solucionesweb.losbeans.utilidades.JhFormat;

public class FachadaJobCausa {

    private int idTipoCausa;
    private int idCausa;
    private String nombreCausa;
    private int estado;

    //
    JhFormat jhFormat = new JhFormat();

    // Metodos
    public int getIdTipoCausa() {
        return idTipoCausa;
    }

    public void setIdTipoCausa(int idTipoCausa) {
        this.idTipoCausa = idTipoCausa;
    }

    public String getIdTipoCausaStr() {
        return new Integer(getIdTipoCausa()).toString();
    }

    public void setIdTipoCausa(String idTipoCausaStr) {
        this.idTipoCausa = new Integer(idTipoCausaStr).intValue();
    }


    public int getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(int idCausa) {
        this.idCausa = idCausa;
    }

    public String getIdCausaStr() {
        return new Integer(getIdCausa()).toString();
    }

    public void setIdCausa(String idCausaStr) {
        this.idCausa = new Integer(idCausaStr).intValue();
    }

    public String getIdCausaFormato() {
        return "" + jhFormat.fill(getIdCausaStr(), '0', 2, 0) + "";
    }


    public String getNombreCausa()
    {
        return nombreCausa;
    }

    public void setNombreCausa( String nombreCausa )
    {
        this.nombreCausa = nombreCausa.trim();
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

}
