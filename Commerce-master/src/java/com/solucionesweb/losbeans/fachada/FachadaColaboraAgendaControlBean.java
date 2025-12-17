package com.solucionesweb.losbeans.fachada;

import java.util.Date;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaColaboraAgendaControlBean implements IConstantes {

    // Propiedades
    private String idCliente;
    private double idUsuario;
    private String idSucursal;
    private int idPeriodo;
    private Date fechaVisita;
    private int estado;
    private String fechaVisitaStr;
    private String nombreCliente;
    private String direccion;
    private String telefono;
    private String nombreEmpresa;
    private String ciudadDireccion;
    private String departamento;
    private String nombreEstado;
    private String formaPago;
    private String email;
    private String fax;
    private String contacto;
    private String observacion;
    private String ciudadDespacho;

    //
    private String idClienteCadena;

    // Metodos
    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario ;
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue() ;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public String getIdSucursal()
    {
        return idSucursal;
    }

    public String getIdSucursalOracle()
    {
        return "'" + idSucursal + "'" ;
    }

    public void setIdSucursal( String idSucursal )
    {
        this.idSucursal = idSucursal.trim();
    }

    public void setIdPeriodo( int idPeriodo )
    {
        this.idPeriodo = idPeriodo ;
    }

    public int getIdPeriodo()
    {
        return idPeriodo;
    }

    public void setIdPeriodo( String idPeriodoStr )
    {
        this.idPeriodo = new Integer(idPeriodoStr).intValue();
    }

    public String getIdPeriodoStr()
    {
        return new Integer(idPeriodo).toString();
    }

    public void setFechaVisita( Date fechaVisita )
    {
        this.fechaVisita = fechaVisita ;
    }

    public Date getFechaVisita()
    {
        return fechaVisita;
    }

    public void setFechaVisita( String fechaVisitaStr )
    {
        this.fechaVisita = new Date(fechaVisitaStr) ;
    }

    public String getFechaVisitaSqlServer() {

            return getFechaVisitaStr().substring(0, 4) +
                   getFechaVisitaStr().substring(5, 7) +
                   getFechaVisitaStr().substring(8, 10) ;

    }

    public void setFechaVisitaStr( String fechaVisitaStr )
    {
        this.fechaVisitaStr = fechaVisitaStr ;
    }

    public String getFechaVisitaStr()
    {
        return  fechaVisitaStr ;
    }

    public String getFechaVisitaStrMsAccess()
    {
        return  "#" + getFechaVisitaStr() + "#" ;
    }

    public String getFechaVisitaStrOracle()
    {
        return "TO_DATE('" + getFechaVisitaStr() + "','YYYY/MM/DD HH24:MI:SS')" ;
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

    public void setNombreCliente( String nombreCliente )
    {
        if (nombreCliente == null) {
           this.nombreCliente = STRINGVACIO;
        } else {
        this.nombreCliente = nombreCliente ;
        }
    }

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setDireccion( String direccion )
    {
        if (direccion == null) {
           this.direccion = STRINGVACIO;
        } else {
        this.direccion = direccion ;
        }
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setTelefono( String telefono )
    {
        if (telefono == null) {
           this.telefono = STRINGVACIO;
        } else {
          this.telefono = telefono ;
        }
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setNombreEmpresa( String nombreEmpresa )
    {
        if (nombreEmpresa == null) {
           this.nombreEmpresa = STRINGVACIO;
        } else {
          this.nombreEmpresa = nombreEmpresa ;
        }
    }

    public String getNombreEmpresa()
    {
        return nombreEmpresa;
    }

    public void setCiudadDireccion( String ciudadDireccion )
    {
        if (ciudadDireccion == null) {
           this.ciudadDireccion = STRINGVACIO;
        } else {
          this.ciudadDireccion = ciudadDireccion ;
        }
    }

    public String getCiudadDireccion()
    {
        return ciudadDireccion;
    }

    public void setDepartamento( String departamento )
    {
        if (departamento == null) {
           this.departamento = STRINGVACIO;
        } else {
          this.departamento = departamento ;
        }
    }

    public String getDepartamento()
    {
        return departamento;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado ;
    }

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setFormaPago( String formaPago )
    {
        this.formaPago = formaPago ;
    }

    public String getFormaPago()
    {
        return formaPago;
    }

    public void setEmail( String email )
    {
        if (email == null) {
           this.email = STRINGVACIO;
        } else {
        this.email = email ;
        }
    }

    public String getEmail()
    {
        return email;
    }

    public void setFax( String fax )
    {
        if (fax == null) {
           this.fax = STRINGVACIO;
        } else {
        this.fax = fax ;
        }
    }

    public String getFax()
    {
        return fax;
    }

    public void setContacto( String contacto )
    {
        if (contacto == null) {
           this.contacto = STRINGVACIO;
        } else {
        this.contacto = contacto ;
        }
    }

    public String getContacto()
    {
        return contacto;
    }

    public void setObservacion( String observacion )
    {
        this.observacion = observacion ;
    }

    public String getObservacion()
    {
        return observacion;
    }

    public void setCiudadDespacho( String ciudadDespacho )
    {
        this.ciudadDespacho = ciudadDespacho ;
    }

    public String getCiudadDespacho()
    {
        return ciudadDespacho;
    }

    public String getIdClienteCadena()
    {
        return idClienteCadena;
    }

    public void setIdClienteCadena( String idClienteCadena )
    {
        this.idClienteCadena = idClienteCadena;
    }

    public String getNombreEstadoCliente()
    {
        if ( getEstado() == 1 ) {
           return "Activo" ;
        } else {
           return "Suspendido" ;
        }
    }

    public FachadaColaboraAgendaControlBean() { }

}