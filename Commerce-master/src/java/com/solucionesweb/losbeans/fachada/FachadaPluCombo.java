package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaPluCombo {

    //
    private int idPluCombo;
    private int idPlu;
    private double cantidad;
    private int estado;
    private int idSeq;
    private String nombrePlu;
    private String nombreCategoria;

    //
    DecimalFormat sf4 = new DecimalFormat("#########.0000");

    //
    public void setIdPluCombo (int idPluCombo)
    {
        this.idPluCombo = idPluCombo ;
    }

    public int getIdPluCombo()
    {
        return idPluCombo;
    }

    public void setIdPluCombo (String idPluComboStr)
    {
        this.idPluCombo = new Integer(idPluComboStr).intValue();
    }

    public String getIdPluComboStr()
    {
        return new Integer(idPluCombo).toString();
    }

    public void setIdPlu (int idPlu)
    {
        this.idPlu = idPlu ;
    }

    public int getIdPlu()
    {
        return idPlu;
    }

    public void setIdPlu (String idPluStr)
    {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr()
    {
        return new Integer(idPlu).toString();
    }

    public void setEstado (int estado)
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado (String estadoStr)
    {
        this.estado = new Integer(estadoStr).intValue();
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

    public void setCantidad( double cantidad )
    {
        this.cantidad = cantidad;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public void setCantidad( String cantidadStr )
    {
        this.cantidad = new Double(cantidadStr).doubleValue();
    }

    public String getCantidadStr()
    {
        return new Double(cantidad).toString();
    }

    public String getCantidadSf4()
    {
        return sf4.format(getCantidad());
    }


    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public FachadaPluCombo() { }
}
