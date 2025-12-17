package com.solucionesweb.losbeans.fachada;

import java.util.Date;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaLocalBean implements IConstantes {

    //Propiedad
    private int idLocal;
    private String nombreLocal;
    private int idBodega;
    private int idActivo;
    private int estado;
    private String razonSocial;
    private String nit;
    private String direccion;
    private String telefono;
    private String regimen;
    private String resolucion;
    private Date fechaResolucion;
    private String ciudad;
    private String txtFactura;
    private String rango;
    private String prefijo;
    private String email;
    private String direccionIp;
    private String idRegional;
    private String idEmpresa;
    private String resolucionTransporte;
    private String rangoMinisterio;
    private String fax;
    private double margenDisponible;
    private double valorDisponible;
    private String fechaResolucionStr;
    private boolean inventarioVisible;
    private String ipLocal;
    private int idSeq;

    //Metodo
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

    public void setNombreLocal(String nombreLocal) {
        if (nombreLocal == null) {
            this.nombreLocal = STRINGVACIO;
        } else {
            this.nombreLocal = nombreLocal.trim();
        }
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(String idBodegaStr) {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }

    public String getIdBodegaStr() {
        return new Integer(idBodega).toString();
    }

    public void setIdActivo(int idActivo) {
        this.idActivo = idActivo;
    }

    public int getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(String idActivoStr) {
        this.idActivo = new Integer(idActivoStr).intValue();
    }

    public String getIdActivoStr() {
        return new Integer(idActivo).toString();
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr() {
        return new Integer(estado).toString();
    }

    public void setRazonSocial(String razonSocial) {
        if (razonSocial == null) {
            this.razonSocial = STRINGVACIO;
        } else {
            this.razonSocial = razonSocial.trim();
        }
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setDireccion(String direccion) {
        if (direccion == null) {
            this.direccion = STRINGVACIO;
        } else {
            this.direccion = direccion.trim();
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            this.telefono = STRINGVACIO;
        } else {
            this.telefono = telefono.trim();
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setRegimen(String regimen) {
        if (regimen == null) {
            this.regimen = STRINGVACIO;
        } else {
            this.regimen = regimen.trim();
        }
    }

    public String getRegimen() {
        return regimen;
    }

    public void setResolucion(String resolucion) {
        if (resolucion == null) {
            this.resolucion = STRINGVACIO;
        } else {
            this.resolucion = resolucion.trim();
        }
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setCiudad(String ciudad) {
        if (ciudad == null) {
            this.ciudad = STRINGVACIO;
        } else {
            this.ciudad = ciudad.trim();
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setTxtFactura(String txtFactura) {
        if (txtFactura == null) {
            this.txtFactura = STRINGVACIO;
        } else {
            this.txtFactura = txtFactura.trim();
        }
    }

    public String getTxtFactura() {
        return txtFactura;
    }

    public void setRango(String rango) {
        if (rango == null) {
            this.rango = STRINGVACIO;
        } else {
            this.rango = rango.trim();
        }
    }

    public String getRango() {
        return rango;
    }

    public void setPrefijo(String prefijo) {
        if (prefijo == null) {
            this.prefijo = STRINGVACIO;
        } else {
            this.prefijo = prefijo.trim();
        }
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setEmail(String email) {
        if (email == null) {
            this.email = STRINGVACIO;
        } else {
            this.email = email.trim();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setDireccionIp(String direccionIp) {
        if (direccionIp == null) {
            this.direccionIp = STRINGVACIO;
        } else {
            this.direccionIp = direccionIp.trim();
        }
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setIdRegional(String idRegional) {
        if (idRegional == null) {
            this.idRegional = STRINGVACIO;
        } else {
            this.idRegional = idRegional.trim();
        }
    }

    public String getIdRegional() {
        return idRegional;
    }

    public void setIdEmpresa(String idEmpresa) {
        if (idEmpresa == null) {
            this.idEmpresa = STRINGVACIO;
        } else {
            this.idEmpresa = idEmpresa.trim();
        }
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setResolucionTransporte(String resolucionTransporte) {
        if (resolucionTransporte == null) {
            this.resolucionTransporte = STRINGVACIO;
        } else {
            this.resolucionTransporte = resolucionTransporte.trim();
        }
    }

    public String getResolucionTransporte() {
        return resolucionTransporte;
    }

    public void setRangoMinisterio(String rangoMinisterio) {
        if (rangoMinisterio == null) {
            this.rangoMinisterio = STRINGVACIO;
        } else {
            this.rangoMinisterio = rangoMinisterio.trim();
        }
    }

    public String getRangoMinisterio() {
        return rangoMinisterio;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucionStr(String fechaResolucionStr) {
        if (fechaResolucionStr == null) {
            this.fechaResolucionStr = STRINGVACIO;
        } else {
            this.fechaResolucionStr = fechaResolucionStr.trim();
        }
    }

    public String getFechaResolucionStr() {
        return fechaResolucionStr;
    }

    public void setFax(String fax) {
        if (fax == null) {
            this.fax = STRINGVACIO;
        } else {
            this.fax = fax.trim();
        }
    }

    public String getFax() {
        return fax;
    }

    public double getMargenDisponible() {
        return margenDisponible;
    }

    public String getMargenDisponibleStr() {
        return new Double(margenDisponible).toString();
    }

    public void setMargenDisponible(double margenDisponible) {
        this.margenDisponible = margenDisponible;
    }

    public void setMargenDisponible(String margenDisponibleStr) {
        this.margenDisponible = new Double(margenDisponibleStr).doubleValue();
    }

    public double getValorDisponible() {
        return valorDisponible;
    }

    public String getValorDisponibleStr() {
        return new Double(valorDisponible).toString();
    }

    public void setValorDisponible(double valorDisponible) {
        this.valorDisponible = valorDisponible;
    }

    public void setValorDisponible(String valorDisponibleStr) {
        this.valorDisponible = new Double(valorDisponibleStr).doubleValue();
    }

    public FachadaLocalBean() {
    }

    public boolean isInventarioVisible() {
        return getInventarioVisible();
    }

    public boolean getInventarioVisible() {
        return inventarioVisible;
    }

    public void setInventarioVisible(boolean inventarioVisible) {
        this.inventarioVisible = inventarioVisible;
    }

    public String getIpLocal() {
        return ipLocal;
    }

    public void setIpLocal(String ipLocal) {
        this.ipLocal = ipLocal;
    }

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
}
