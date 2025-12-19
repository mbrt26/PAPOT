package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaColaboraHistoriaComplementoBean implements IConstantes {

    // Propiedades
    private String idCliente;
    private String strIdReferencia;
    private String idReferenciaComplemento;
    private String nombrePlu;

    //
    private int item;

    // metodos
    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public String getStrIdReferencia()
    {
        return strIdReferencia;
    }

    public void setStrIdReferencia( String strIdReferencia )
    {
        this.strIdReferencia = strIdReferencia.trim() ;
    }

    public String getIdReferenciaComplemento()
    {
        return idReferenciaComplemento;
    }

    public void setIdReferenciaComplemento( String idReferenciaComplemento )
    {
        this.idReferenciaComplemento = idReferenciaComplemento.trim() ;
    }

    public void setItem( int item )
    {
        this.item = item ;
    }

    public int getItem()
    {
        return item;
    }

    public void setItem( String itemStr )
    {
        this.item = new Integer(itemStr).intValue();
    }

    public String getItemStr()
    {
        return new Integer(item).toString();
    }

    public String getNombrePlu()
    {
        return nombrePlu;
    }

    public void setNombrePlu( String nombrePlu )
    {
        this.nombrePlu = nombrePlu.trim() ;
    }

    public FachadaColaboraHistoriaComplementoBean() { }

}