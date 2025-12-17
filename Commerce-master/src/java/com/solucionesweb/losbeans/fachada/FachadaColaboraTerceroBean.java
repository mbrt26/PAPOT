package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaColaboraTerceroBean implements IConstantes {

    // Propiedades
    private String idCliente;
    private double idUsuario;
    private String idSucursal;
    private int idPeriodo;
    private int estado;
    private String fechaVisitaStr;
    private String nombreCliente;
    private String direccion;
    private String telefono;
    private String telefonoFax;
    private String telefonoCelular;
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
    private String nitCC;
    private String listaPrecio;
    private double cupoCredito;
    private String idVendedor;
    private String idRuta;

    //
    private String idClienteCadena;
    private String nombreEstadoCliente;
    private String strEstadoCliente;
    private String fechaVisita;

    //
    int estadoClienteActivo    = 1;
    int estadoClienteRetenido  = 0;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");

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

    public String getNombreClienteMinuscula()
    {
        return nombreCliente.trim().toLowerCase();
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
        return contacto.trim();
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
           return "Retenido" ;
        }
    }

    public String getFechaVisita()
    {
        return fechaVisita ;
    }

    public void setFechaVisita( String fechaVisita )
    {
        this.fechaVisita = fechaVisita;
    }

    public String getStrEstadoCliente()
    {
        return strEstadoCliente;
    }

    public void setStrEstadoCliente( String strEstadoCliente )
    {

        this.strEstadoCliente = strEstadoCliente;

        if (getStrEstadoCliente().compareTo("R") == 0) {

           this.setEstado(estadoClienteActivo);

        } else {
           this.setEstado(estadoClienteRetenido);
        }
    }

    public void setNitCC( String nitCC )
    {
        this.nitCC = nitCC ;
    }

    public String getNitCC()
    {
        return nitCC;
    }

    public void setListaPrecio( String listaPrecio )
    {
        this.listaPrecio = listaPrecio ;
    }

    public String getListaPrecio()
    {
        return listaPrecio;
    }

    public void setTelefonoFax( String telefonoFax )
    {
        if (telefonoFax == null) {
           this.telefonoFax = STRINGVACIO;
        } else {
          this.telefonoFax = telefonoFax ;
        }
    }

    public String getTelefonoFax()
    {
        return telefonoFax;
    }

    public void setTelefonoCelular( String telefonoCelular )
    {
        if (telefonoCelular == null) {
           this.telefonoCelular = STRINGVACIO;
        } else {
          this.telefonoCelular = telefonoCelular ;
        }
    }

    public String getTelefonoCelular()
    {
        return telefonoCelular;
    }

    public void setCupoCredito( double cupoCredito )
    {
        this.cupoCredito = cupoCredito ;
    }

    public double getCupoCredito()
    {
        return cupoCredito;
    }

    public void setCupoCredito( String cupoCreditoStr )
    {
        this.cupoCredito = new Double(cupoCreditoStr).doubleValue() ;
    }

    public String getCupoCreditoStr()
    {
        return new Double(cupoCredito).toString();
    }

    public String getCupoCreditoFormateado()
    {
        return df0.format(getCupoCredito());
    }

    public void setIdVendedor( String idVendedor )
    {
        if (idVendedor == null) {
           this.idVendedor = STRINGVACIO;
        } else {
        this.idVendedor = idVendedor ;
        }
    }

    public String getIdVendedor()
    {
        return idVendedor;
    }

    public void setIdRuta( String idRuta )
    {
        if (idRuta == null) {
           this.idRuta = STRINGVACIO;
        } else {
        this.idRuta = idRuta ;
        }
    }

    public String getIdRuta()
    {
        return idRuta;
    }

    public FachadaColaboraTerceroBean() { }

}