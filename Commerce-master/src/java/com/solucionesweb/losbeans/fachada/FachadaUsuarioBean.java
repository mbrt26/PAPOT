package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de java
import java.io.*;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaUsuarioBean  implements Serializable, IConstantes {

    // Propiedades del Usuario
    private double idUsuario;
    private String nombreUsuario;
    private String clave;
    private int idNivel;
    private String direccion;
    private String telefono;
    private String fechaCambioClave;
    private int estado;
    private int idLocalUsuario;
    private String strIdLista;
    private String email;
    private String idRuta;
    private String aliasUsuario;
    private int idSeq;
    private int indicadorInicial;
    private int indicadorFinal;
    private String idNivelCadena;

    //
    DecimalFormat sf0 = new DecimalFormat("###############");

    //
    private boolean vigente;

    // Metodos Set para cada una de las propiedades
    public double getIdUsuario()
    {
        return idUsuario;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public String getIdUsuarioSf0()
    {
        return sf0.format(getIdUsuario());

    }

    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public String getNombreUsuario()
    {
        return nombreUsuario.toUpperCase();
    }

    public void setNombreUsuario( String nombreUsuario )
    {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave( String clave )
    {
        this.clave = clave.trim();
    }

    public int getIdNivel()
    {
        return idNivel;
    }

    public String getIdNivelStr()
    {
        return new Integer(idNivel).toString();
    }

    public void setIdNivel( int idNivel )
    {
        this.idNivel = idNivel ;
    }

    public void setIdNivel( String idNivelStr )
    {
        this.idNivel = new Integer(idNivelStr).intValue() ;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion( String direccion )
    {
        this.direccion = direccion.trim() ;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono( String telefono )
    {
        this.telefono = telefono.trim() ;
    }
                
    public void setFechaCambioClave( String fechaCambioClave )
    {
        this.fechaCambioClave = fechaCambioClave ;
    }

    public String getFechaCambioClave()
    {
        return fechaCambioClave;
    }

    public String getFechaCambioClaveSqlServer() {

            return getFechaCambioClave().substring(0, 4) +
                   getFechaCambioClave().substring(5, 7) +
                   getFechaCambioClave().substring(8, 10);
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

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isVigente() {
       return vigente;
    }

    public int getIdLocalUsuario()
    {
        return idLocalUsuario;
    }

    public void setIdLocalUsuario( int idLocalUsuario )
    {
        this.idLocalUsuario = idLocalUsuario;
    }

    public void setIdLocalUsuario( String idLocalUsuarioStr )
    {
        this.idLocalUsuario = new Integer(idLocalUsuarioStr).intValue();
    }

    public String getIdLocalUsuarioStr()
    {
        return new Integer(idLocalUsuario).toString();
    }

    public String getStrIdLista()
    {
        return strIdLista;
    }

    public void setStrIdLista( String strIdLista )
    {
        this.strIdLista = strIdLista;
    }


	public String getEmail() {
        if ((this.email == null) ||
            (this.email.trim().length()==0)) {
           return STRINGNN;
        }
        return this.email;
	}

    public void setEmail( String email ) {
         if ((this.email == null) ||
            (this.email.trim().length()==0)) {
           this.email =  STRINGNN;
        }
        this.email = email;
    }

    public String getIdRuta()
    {
        return idRuta;
    }

    public void setIdRuta( String idRuta )
    {
        this.idRuta = idRuta.trim() ;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario.toUpperCase();
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

    public int getIndicadorInicial()
    {
        return indicadorInicial;
    }

    public void setIndicadorInicial( int indicadorInicial )
    {
        this.indicadorInicial = indicadorInicial;
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

    public void setIndicadorFinal( String indicadorFinalStr )
    {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public String getIndicadorFinalStr()
    {
        return new Integer(indicadorFinal).toString();
    }

    public String getIdNivelCadena()
    {
        return idNivelCadena;
    }

    public void setIdNivelCadena( String idNivelCadena )
    {
        this.idNivelCadena = idNivelCadena.trim() ;
    }

    public FachadaUsuarioBean() { }

}