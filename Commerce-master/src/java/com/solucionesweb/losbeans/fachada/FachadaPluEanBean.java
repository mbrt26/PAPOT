package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaPluEanBean  {

    //
    private String ean;
    private int idPlu;
    private int estado;
    private int idSeq;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat sf0 = new DecimalFormat("##############");

    //
    public String getEan()
    {
        return ean;
    }

    public void setEan( String ean )
    {
        this.ean = ean;
    }

    public void setIdPlu( int idPlu )
    {
        this.idPlu = idPlu;
    }

    public int getIdPlu()
    {
        return idPlu;
    }

    public void setIdPlu( String idPluStr )
    {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr()
    {
        return new Integer(getIdPlu()).toString();
    }

    public String getIdPluSf0()
    {
        return sf0.format(getIdPlu());
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

    public FachadaPluEanBean() { }

}