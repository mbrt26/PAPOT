package com.solucionesweb.losbeans.fachada;

import java.util.Date;

public class FachadaAgendaLogSoporteBean {

    // Propiedades
    private int idLog;
    private String idCliente;
    private String nombreCliente;
    private String telefono;
    private int estado;
    private String email;
    private String contacto;
    private int idEstadoTx;

    // Metodos
    public String getIdLogStr()
    {
        return new Integer(idLog).toString();
    }

    public void setIdLog( int idLog )
    {
        this.idLog = idLog ;
    }

    public void setIdLog( String idLogStr )
    {
        this.idLog = new Integer(idLogStr).intValue() ;
    }

    public int getIdLog()
    {
        return idLog;
    }

    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public String getNombreClienteOracle()
    {
        return "'" + nombreCliente + "'" ;
    }

    public void setNombreCliente( String nombreCliente )
    {
        this.nombreCliente = nombreCliente.trim().toUpperCase() ;
    }

    public void setTelefono( String telefono )
    {
        this.telefono = telefono ;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public String getTelefonoOracle()
    {
        return "'" + telefono + "'" ;
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

    public int getEstado()
    {
        return estado;
    }

    public String getEmail()
    {
        return email ;
    }

    public void setEmail( String email )
    {
        this.email = email.toLowerCase() ;
    }

    public String getContacto()
    {
        return contacto ;
    }

    public void setContacto( String contacto )
    {
        this.contacto = contacto.toUpperCase() ;
    }

    public void setIdEstadoTx( int idEstadoTx )
    {
        this.idEstadoTx = idEstadoTx ;
    }

    public int getIdEstadoTx()
    {
        return idEstadoTx;
    }

    public void setIdEstadoTx( String idEstadoTxStr )
    {
        this.idEstadoTx = new Integer(idEstadoTxStr).intValue();
    }

    public String getIdEstadoTxStr()
    {
        return new Integer(idEstadoTx).toString();
    }


    public FachadaAgendaLogSoporteBean() { }

}