package com.solucionesweb.losbeans.fachada;

// Importa los paquetes de java
import java.io.*;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaTerceroBean implements Serializable, IConstantes {

    // Metodo constructor por defecto sin parametros
    public FachadaTerceroBean() {
    }
    // Propiedades
    private String idCliente;
    private double idTercero;
    private String tipoIdTercero;
    private int digitoVerificacion;
    private int idTipoTercero;
    private int idPersona;
    private int idAutoRetenedor;
    private String idRegimen;
    private String nombreTercero;
    private String direccionTercero;
    private int idDptoCiudad;
    //private int idDpto;
    private String telefonoFijo;
    private String telefonoCelular;
    private String telefonoFax;
    private String email;
    private int idFormaPago;
    private int estado;
    private int idSeq;
    private String idRuta;
    private String nombreEmpresa;
    private double cupoCredito;
    private int indicador;
    private String ciudadTercero;
    private String contactoTercero;
    private String departamentoTercero;
    //
    private String idClienteCadena;
    private int idListaPrecio;
    private double idVendedor;
    private int idTipoOrden;
    //
    private String nombreRegimen;
    private String nombreTipoTercero;
    private String nombreCiudad;
    private String nombreDpto;
    private double factorBase;
    private int idLocal;
    private int idLocalTercero;
    private String idSucursal = "000";
    private String paisTercero = "COLOMBIA";
    private String textoVacio = "";
    private int idLog;
    private int idOrigenLog;

    private int idRteFuenteVrBase;
    private int idRteIva;
    private int idRteIvaVrBase;
    private int idEstado;
    private String nombreClase;
    private int idClase;
    private int idRteCree;
    private String CC_Nit;
    private String fechaCreacion;
    private int diaMaxFacturacion;
    private int  idOperacionFactura;
    private String nombreOperacionFactura;
    private String idResponsabilidad;
    private String nombreResponsabilidad;
     private String[] idResponsabilidades  = new String[10];

    public String[] getIdResponsabilidades() {
        return idResponsabilidades;
    }

    public void setIdResponsabilidades(String[] idResponsabilidades) {
        this.idResponsabilidades = idResponsabilidades;
    }

    public String getIdResponsabilidad() {
        return idResponsabilidad;
    }

    public void setIdResponsabilidad(String idResponsabilidad) {
        this.idResponsabilidad = idResponsabilidad;
    }

    public String getNombreResponsabilidad() {
        return nombreResponsabilidad;
    }

    public void setNombreResponsabilidad(String nombreResponsabilidad) {
        this.nombreResponsabilidad = nombreResponsabilidad;
    }

    public int getIdOperacionFactura() {
        return idOperacionFactura;
    }

    public void setIdOperacionFactura(int idOperacionFactura) {
        this.idOperacionFactura = idOperacionFactura;
    }

    public String getNombreOperacionFactura() {
        return nombreOperacionFactura;
    }

    public void setNombreOperacionFactura(String nombreOperacionFactura) {
        this.nombreOperacionFactura = nombreOperacionFactura;
    }


    public int getDiaMaxFacturacion() {
        return diaMaxFacturacion;
    }

    public void setDiaMaxFacturacion(int diaMaxFacturacion) {
        this.diaMaxFacturacion = diaMaxFacturacion;
    }
    //
    String xIdDptoCiudadNN = "5154";

    //
    NumberFormat nf = NumberFormat.getNumberInstance();
    //
    DecimalFormat df0 = new DecimalFormat("#####,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat di0 = new DecimalFormat("###############");
    DecimalFormat sf0 = new DecimalFormat("###############");

    //
    public void setIdSeq(int idSeq) {
        this.idSeq = idSeq;
    }

    public int getIdSeq() {
        return idSeq;
    }

    public void setIdSeq(String idSeqStr) {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public String getIdSeqStr() {
        return new Integer(idSeq).toString();
    }

    public double getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(double idTercero) {
        this.idTercero = idTercero;
    }

    public void setIdTercero(String idTerceroStr) {
        this.idTercero = new Double(idTerceroStr).doubleValue();
    }

    public String getIdTerceroStr() {
        return new Double(idTercero).toString();
    }

    public String getIdTerceroFormatStr() {
        return nf.format(getIdTercero());
    }

    public String getIdTerceroFormatSf0() {
        return sf0.format(getIdTercero());
    }

    public String getIdTerceroSf0() {
        return sf0.format(getIdTercero());
    }

    public String getTipoIdTercero() {
        return tipoIdTercero;
    }

    public String getTipoIdTerceroSoftLand() {

        //
        return "'" + getTipoIdTercero() + "'";

    }

    public void setTipoIdTercero(String tipoIdTercero) {

        if (tipoIdTercero == null) {
            this.tipoIdTercero = "C";
        } else {
            this.tipoIdTercero = tipoIdTercero;
        }
    }

    public int getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public String getDigitoVerificacionDi0() {

        //
        if (getDigitoVerificacionStr() == null) {
            return "0";
        } else {
            return di0.format(getDigitoVerificacion());
        }
    }

    public void setDigitoVerificacion(int digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacionStr) {
        if (digitoVerificacionStr == null) {
            this.digitoVerificacion = 0;
        } else {
            this.digitoVerificacion = new Integer(digitoVerificacionStr).intValue();
        }
    }

    public String getDigitoVerificacionStr() {
        return new Integer(digitoVerificacion).toString();
    }

    public int getIdTipoTercero() {
        return idTipoTercero;
    }

    public void setIdTipoTercero(int idTipoTercero) {
        this.idTipoTercero = idTipoTercero;
    }

    public void setIdTipoTercero(String idTipoTerceroStr) {
        this.idTipoTercero = new Integer(idTipoTerceroStr).intValue();
    }

    public String getIdTipoTerceroStr() {
        return new Integer(getIdTipoTercero()).toString();
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setIdPersona(String idPersonaStr) {
        this.idPersona = new Integer(idPersonaStr).intValue();
    }

    public String getIdPersonaStr() {
        return new Integer(idPersona).toString();
    }

    public String getIdPersonaSoftLand() {
        return "'" + getIdPersonaStr() + "'";
    }

    public int getIdAutoRetenedor() {
        return idAutoRetenedor;
    }

    public void setIdAutoRetenedor(int idAutoRetenedor) {

        this.idAutoRetenedor = idAutoRetenedor;
    }

    public void setIdAutoRetenedor(String idAutoRetenedorStr) {
        this.idAutoRetenedor = new Integer(idAutoRetenedorStr).intValue();
    }

    public String getIdAutoRetenedorStr() {
        return new Integer(idAutoRetenedor).toString();
    }

    public String getIdAutoRetenedorSoftLand() {
        return "'" + getIdAutoRetenedorStr() + "'";
    }

    public String getIdRegimen() {
        return idRegimen;
    }

    public void setIdRegimen(String idRegimen) {

        if ((idRegimen == null)
                || (idRegimen.length() == 0)) {
            this.idRegimen = "NI";
        } else {
            this.idRegimen = idRegimen.trim().toUpperCase();
        }
    }

    public String getIdRegimenSoftLand() {
        return "'" + getIdRegimen() + "'";
    }

    public String getNombreTercero() {
        if (this.nombreTercero == null) {
            return STRINGVACIO;
        }
        return this.nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        if (nombreTercero == null) {
            this.nombreTercero = STRINGVACIO;
        } else {
            this.nombreTercero = nombreTercero.trim().toUpperCase();
        }
    }

    public String getNombreTerceroSoftLand() {

        //
        int xLongitud = getNombreTercero().length();

        //
        if (xLongitud > 80) {

            //
            return "'" + getNombreTercero().substring(0, 79) + "'";

        } else {

            //
            return "'" + getNombreTercero() + "'";
        }

    }

    //
    public String getDireccionTercero() {

        if ((direccionTercero == null)
                || (direccionTercero.length() == 0)) {
            return STRINGNN;
        } else {
            return direccionTercero;
        }
    }

    public void setDireccionTercero(String direccionTercero) {

        if ((direccionTercero == null)
                || (direccionTercero.length() == 0)) {
            this.direccionTercero = STRINGNN;
        } else {
            this.direccionTercero = direccionTercero.trim().toUpperCase();
        }

    }

    public String getDireccionTerceroSoftLand() {

        //
        int xLongitud = getDireccionTercero().length();

        //
        if (xLongitud > 50) {

            //
            return "'" + getDireccionTercero().substring(0, 49) + "'";

        } else {

            //
            return "'" + getDireccionTercero() + "'";
        }

    }

    public int getIdDptoCiudad() {
        return idDptoCiudad;
    }

    public void setIdDptoCiudad(int idDptoCiudad) {

        //
        this.idDptoCiudad = idDptoCiudad;

    }

    public void setIdDptoCiudad(String idDptoCiudadStr) {
        //
        if ((idDptoCiudadStr == null) || (idDptoCiudadStr.length() < 1)) {

            //
            this.idDptoCiudad = new Integer(xIdDptoCiudadNN).intValue();

        } else {

            //
            this.idDptoCiudad = new Integer(idDptoCiudadStr).intValue();
        }

    }

    public String getIdDptoCiudadStr() {
        return new Integer(idDptoCiudad).toString();
    }

    public String getIdDptoCiudadSoftLand() {
        return "'" + getIdDptoCiudadStr() + "'";
    }

    public String getIdCiudadSoftLand() {

        //
        if (getIdDptoCiudad() > 9999) {

            //
            return "'" + getIdDptoCiudadStr().substring(2, 5) + "'";

        } else {

            //
            return "'" + getIdDptoCiudadStr().substring(1, 4) + "'";

        }
    }

    public String getIdDptoSoftLand() {

        //
        if (getIdDptoCiudad() > 9999) {

            //
            return "'" + getIdDptoCiudadStr().substring(0, 2) + "'";
        } else {

            //
            return "'" + "0" + getIdDptoCiudadStr().substring(0, 1) + "'";

        }
    }

    public String getTelefonoFijo() {

        if ((telefonoFijo == null)
                || (telefonoFijo.length() == 0)) {
            return STRINGNN;
        } else {
            return telefonoFijo;
        }
    }

    public void setTelefonoFijo(String telefonoFijo) {
        if ((telefonoFijo == null)
                || (telefonoFijo.length() == 0)) {
            this.telefonoFijo = STRINGNN;
        } else {
            this.telefonoFijo = telefonoFijo.trim().toUpperCase();
        }
    }

    public String getTelefonoFijoSoftLand() {

        //
        int xLongitud = getTelefonoFijo().length();

        //
        if (xLongitud > 15) {

            //
            return "'" + getTelefonoFijo().substring(0, 14) + "'";

        } else {

            //
            return "'" + getTelefonoFijo() + "'";
        }

    }

    public String getTelefonoCelular() {

        if ((telefonoCelular == null)
                || (telefonoCelular.length() == 0)) {
            return STRINGNN;
        } else {
            return telefonoCelular;
        }
    }

    public void setTelefonoCelular(String telefonoCelular) {

        if ((telefonoCelular == null)
                || (telefonoCelular.length() == 0)) {
            this.telefonoCelular = STRINGNN;
        } else {
            this.telefonoCelular = telefonoCelular.trim().toUpperCase();
        }
    }

    public String getTelefonoCelularSoftLand() {

        //
        int xLongitud = getTelefonoCelular().length();

        //
        if (xLongitud > 15) {

            //
            return "'" + getTelefonoCelular().substring(0, 14) + "'";

        } else {

            //
            return "'" + getTelefonoCelular() + "'";
        }

    }

    public String getTelefonoFax() {
        if ((telefonoFax == null)
                || (telefonoFax.length() == 0)) {
            return STRINGNN;
        } else {
            return telefonoFax;
        }
    }

    public void setTelefonoFax(String telefonoFax) {
        if (telefonoFax == null) {
            this.telefonoFax = STRINGNN;
        } else {
            this.telefonoFax = telefonoFax.trim().toUpperCase();
        }
    }

    public String getTelefonoFaxSoftLand() {

        //
        int xLongitud = getTelefonoFax().length();

        //
        if (xLongitud > 15) {

            //
            return "'" + getTelefonoFax().substring(0, 14) + "'";

        } else {

            //
            return "'" + getTelefonoFax() + "'";
        }

    }

    public String getEmail() {
        if ((email == null)
                || (email.length() == 0)) {
            return STRINGNN;
        } else {
            return email;
        }
    }

    public void setEmail(String email) {

        if ((email == null)
                || (email.length() == 0)) {
            this.email = STRINGNN;
        } else {
            this.email = email.trim().toLowerCase();
        }
    }

    public String getEmailSoftLand() {

        //
        int xLongitud = getEmail().length();

        //
        if (xLongitud > 50) {

            //
            return "'" + getEmail().substring(0, 49) + "'";

        } else {

            //
            return "'" + getEmail() + "'";
        }

    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public void setIdFormaPago(String idFormaPagoStr) {
        this.idFormaPago = new Integer(idFormaPagoStr).intValue();
    }

    public String getIdFormaPagoStr() {
        return new Integer(idFormaPago).toString();
    }

    public int getEstado() {
        return estado;
    }

    public String getEstadoStr() {
        return new Integer(estado).toString();
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdClienteDf0() {
        return df0.format(new Double(getIdCliente()).doubleValue());
    }

    public String getIdRuta() {
        return idRuta;
    }

    public String getIdClientePSL() {
        int intLongitud = getIdCliente().length();

        //
        return getIdCliente().substring(0, 1)
                + getIdCliente().substring(2, intLongitud);
    }

    public String getIdClienteSoftLand() {

        //
        int intLongitud = getIdCliente().length();

        //
        if (intLongitud > 12) {

            return "'" + getIdCliente().substring(0, 11) + "'";

        } else {

            return "'" + getIdCliente() + "'";

        }
    }

    public String getIdSucursalSoftLand() {

        //
        return "'" + idSucursal + "'";

    }

    public void setIdRuta(String idRuta) {
        if (idRuta == null) {
            this.idRuta = STRINGVACIO;
        } else {
            this.idRuta = idRuta.trim().toUpperCase();
        }
    }

    public String getNombreEmpresa() {

        if ((nombreEmpresa == null)
                || (nombreEmpresa.length() == 0)) {
            return STRINGNN;
        } else {
            return nombreEmpresa;
        }
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        if (nombreEmpresa == null) {
            this.nombreEmpresa = nombreEmpresa;
        } else {
            this.nombreEmpresa = nombreEmpresa.trim().toUpperCase();
        }
    }

    public double getCupoCredito() {
        return this.cupoCredito;
    }

    public void setCupoCredito(double cupoCredito) {
        this.cupoCredito = cupoCredito;
    }

    public void setCupoCredito(String cupoCreditoStr) {
        this.cupoCredito = new Double(cupoCreditoStr).doubleValue();
    }

    public String getCupoCreditoDf0() {
        return df0.format(new Double(getCupoCredito()).doubleValue());
    }

    public String getCupoCreditoSf0() {
        return sf0.format(new Double(getCupoCredito()).doubleValue());
    }

    public int getIndicador() {
        return this.indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public void setIndicador(String indicadorStr) {
        this.indicador = new Integer(indicadorStr).intValue();
    }

    public String getIndicadorStr() {
        return new Integer(getIndicador()).toString();
    }

    public String getCiudadTercero() {

        if (this.ciudadTercero == null) {
            return this.ciudadTercero = STRINGNN;
        } else {
            return this.ciudadTercero;
        }
    }

    public void setCiudadTercero(String ciudadTercero) {

        if ((ciudadTercero == null)
                || (ciudadTercero.length() == 0)) {
            this.ciudadTercero = STRINGNN;
        } else {
            this.ciudadTercero = ciudadTercero.trim().toUpperCase();
        }
    }

    public String getCiudadTerceroSoftLand() {

        //
        int xLongitud = getCiudadTercero().length();

        //
        if (xLongitud > 20) {

            //
            return "'" + getCiudadTercero().substring(0, 19) + "'";

        } else {

            //
            return "'" + getCiudadTercero() + "'";
        }

    }

    public String getDepartamentoTercero() {
        return departamentoTercero;
    }

    public void setDepartamentoTercero(String departamentoTercero) {
        if (departamentoTercero == null) {
            this.departamentoTercero = STRINGVACIO;
        } else {
            this.departamentoTercero = departamentoTercero.trim().toUpperCase();
        }
    }

    public String getDepartamentoTerceroSoftLand() {

        //
        int xLongitud = getDepartamentoTercero().length();

        //
        if (xLongitud > 20) {

            //
            return "'" + getDepartamentoTercero().substring(0, 19) + "'";

        } else {

            //
            return "'" + getDepartamentoTercero() + "'";
        }

    }

    public String getPaisTerceroSoftLand() {

        //
        return "'" + paisTercero + "'";

    }

    public String getTextoVacioSoftLand() {

        //
        return "'" + textoVacio + "'";

    }

    public String getContactoTercero() {
        return contactoTercero;
    }

    public void setContactoTercero(String contactoTercero) {

        if ((contactoTercero == null)
                || (contactoTercero.length() == 0)) {
            this.contactoTercero = STRINGNN;
        } else {
            this.contactoTercero = contactoTercero.trim().toUpperCase();
        }
    }

    public String getContactoTerceroSoftLand() {

        //
        int xLongitud = getContactoTercero().length();

        //
        if (xLongitud > 50) {

            //
            return "'" + getContactoTercero().substring(0, 49) + "'";

        } else {

            //
            return "'" + getContactoTercero() + "'";
        }

    }

    public String getIdClienteCadena() {
        return idClienteCadena;
    }

    public void setIdClienteCadena(String idClienteCadena) {
        this.idClienteCadena = idClienteCadena;
    }

    public int getIdListaPrecio() {
        return idListaPrecio;
    }

    public String getIdListaPrecioStr() {
        return new Integer(getIdListaPrecio()).toString();
    }

    public void setIdListaPrecio(int idListaPrecio) {

        this.idListaPrecio = idListaPrecio;

    }

    public void setIdListaPrecio(String idListaPrecioStr) {

        this.idListaPrecio = new Integer(idListaPrecioStr).intValue();

    }

    public double getIdVendedor() {
        return idVendedor;
    }

    public String getIdVendedorStr() {

        return new Double(getIdVendedor()).toString();
    }

    public String getIdVendedorDi0() {

        return di0.format(getIdVendedor());
    }

    public void setIdVendedor(double idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdVendedor(String idVendedorStr) {
        this.idVendedor = new Double(idVendedorStr).doubleValue();
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public String getIdTipoOrdenStr() {
        return new Integer(getIdTipoOrden()).toString();
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getNombreRegimen() {
        return nombreRegimen;
    }

    public void setNombreRegimen(String nombreRegimen) {
        this.nombreRegimen = nombreRegimen;
    }

    public String getNombreTipoTercero() {
        return nombreTipoTercero;
    }

    public void setNombreTipoTercero(String nombreTipoTercero) {
        this.nombreTipoTercero = nombreTipoTercero;
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

    public double getFactorBase() {
        return factorBase;
    }

    public String getFactorBaseStr() {
        return new Double(getFactorBase()).toString();
    }

    public void setFactorBase(double factorBase) {
        this.factorBase = factorBase;
    }

    public void setFactorBase(String factorBaseStr) {
        this.factorBase = new Double(factorBaseStr).doubleValue();
    }

    //
    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr() {
        return new Integer(idLocal).toString();
    }

    //
    public void setIdLocalTercero(int idLocalTercero) {
        this.idLocalTercero = idLocalTercero;
    }

    public int getIdLocalTercero() {
        return idLocalTercero;
    }

    public void setIdLocalTercero(String idLocalTerceroStr) {
        this.idLocalTercero = new Integer(idLocalTerceroStr).intValue();
    }

    public String getIdLocalTerceroStr() {
        return new Integer(idLocalTercero).toString();
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(String idLogStr) {
        this.idLog = new Integer(idLogStr).intValue();
    }

    public String getIdLogStr() {
        return new Integer(idLog).toString();
    }

    //
    public void setIdOrigenLog(int idOrigenLog) {
        this.idOrigenLog = idOrigenLog;
    }

    public int getIdOrigenLog() {
        return idOrigenLog;
    }

    public void setIdOrigenLog(String idOrigenLogStr) {
        this.idOrigenLog = new Integer(idOrigenLogStr).intValue();
    }

    public String getIdOrigenLogStr() {
        return new Integer(idOrigenLog).toString();
    }

    public int getIdRteFuenteVrBase() {
        return idRteFuenteVrBase;
    }

    public void setIdRteFuenteVrBase(int idRteFuenteVrBase) {
        this.idRteFuenteVrBase = idRteFuenteVrBase;
    }

    public void setIdRteFuenteVrBase(String idRteFuenteVrBaseStr) {
        this.idRteFuenteVrBase = new Integer(idRteFuenteVrBaseStr).intValue();
    }

    public String getIdRteFuenteVrBaseStr() {
        return new Integer(getIdRteFuenteVrBase()).toString();
    }

    public int getIdRteIva() {
        return idRteIva;
    }

    public void setIdRteIva(int idRteIva) {
        this.idRteIva = idRteIva;
    }

    public void setIdRteIva(String idRteIvaStr) {
        this.idRteIva = new Integer(idRteIvaStr).intValue();
    }

    public String getIdRteIvaStr() {
        return new Integer(getIdRteIva()).toString();
    }

    public int getIdRteIvaVrBase() {
        return idRteIvaVrBase;
    }

    public void setIdRteIvaVrBase(int idRteIvaVrBase) {
        this.idRteIvaVrBase = idRteIvaVrBase;
    }

    public void setIdRteIvaVrBase(String idRteIvaVrBaseStr) {
        this.idRteIvaVrBase = new Integer(idRteIvaVrBaseStr).intValue();
    }

    public String getIdRteIvaVrBaseStr() {
        return new Integer(getIdRteIvaVrBase()).toString();
    }

    public int getIdRteCree() {
        return idRteCree;
    }

    public void setIdRteCree(int idRteCree) {
        this.idRteCree = idRteCree;
    }

    public void setIdRteCree(String idRteCreeStr) {
        this.idRteCree = new Integer(idRteCreeStr).intValue();
    }

    public String getIdRteCreeStr() {
        return new Integer(idRteCree).toString();
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public void setIdClase(String idClaseStr) {
        this.idClase = new Integer(idClaseStr).intValue();
    }

    public String getIdClaseStr() {
        return new Integer(idClase).toString();
    }

    public String getCC_Nit() {
        return CC_Nit;
    }

    public void setCC_Nit(String CC_Nit) {
        this.CC_Nit = CC_Nit;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
