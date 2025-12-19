package com.solucionesweb.losbeans.fachada;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaLocalCaja   implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idCaja;
    private int idTipoCaja;
    private String textoRango;
    private String rango;
    private String prefijo;
    private int estado;
    private int idSeq;
    private String ipLocal;
    private String resolucion;

    //Metodo
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public void setIdCaja( int idCaja )
    {
        this.idCaja = idCaja ;
    }

    public int getIdCaja()
    {
        return idCaja;
    }

    public void setIdCaja( String idCajaStr )
    {
        this.idCaja = new Integer(idCajaStr).intValue();
    }

    public String getIdCajaStr()
    {
        return new Integer(idCaja).toString();
    }

        public void setIdTipoCaja( int idTipoCaja )
    {
        this.idTipoCaja = idTipoCaja ;
    }

    public int getIdTipoCaja()
    {
        return idTipoCaja;
    }

    public void setIdTipoCaja( String idTipoCajaStr )
    {
        this.idTipoCaja = new Integer(idTipoCajaStr).intValue();
    }

    public String getIdTipoCajaStr()
    {
        return new Integer(idTipoCaja).toString();
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

    public void setRango( String rango )
    {
        if (rango == null) {
           this.rango = STRINGVACIO;
        } else  {
          this.rango  = rango.trim();
        }
    }

    public String getRango()
    {
        return rango;
    }

    public void setPrefijo( String prefijo )
    {
        if (prefijo == null) {
           this.prefijo = STRINGVACIO;
        } else  {
          this.prefijo  = prefijo.trim();
        }
    }

    public String getPrefijo()
    {
        return prefijo;
    }

    public void setTextoRango( String textoRango )
    {
        if (textoRango == null) {
           this.textoRango = STRINGVACIO;
        } else  {
          this.textoRango  = textoRango.trim();
        }
    }

    public String getTextoRango()
    {
        return textoRango;
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

    public String getIpLocal() {
        return ipLocal;
    }

    public void setIpLocal(String ipLocal) {
        this.ipLocal = ipLocal;
    }

    public FachadaLocalCaja() { }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

}

