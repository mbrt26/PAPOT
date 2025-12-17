package com.solucionesweb.losbeans.fachada;

public class FachadaCiudadBean {

    // Propiedades
	private int idCiudad;
    private String nombreCiudad;
    private String nombreDpto;
    private int estado;
    private int idSeq;

    //
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

	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getIdCiudadStr() {
        return new Integer(idCiudad).toString();
	}

	public void setIdCiudad(String idCiudadStr) {
		this.idCiudad = new Integer(idCiudadStr).intValue();
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	public String getNombreDpto() {
		return nombreDpto;
	}
	public void setNombreDpto(String nombreDpto) {
		this.nombreDpto = nombreDpto;
	}

    //---estado
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


    public FachadaCiudadBean() { }

}