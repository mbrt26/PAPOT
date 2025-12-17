package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;


public class FECompanyDTO implements Serializable{
    private String nit;
    private String company;
    private String direccion;
    private String idciudad;
    private String codigoDto;
    private String nombreCiudad;
    private String nombreDto;
    private String respFiscal;
    private String pais = "COLOMBIA";
    private String operacion;
    private String telefono;
    private String tipoOperacion;
    private String regimen;
    private String yearFiscal;
    private String email;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(String idciudad) {
        this.idciudad = idciudad;
    }

    public String getCodigoDto() {
        return codigoDto;
    }

    public void setCodigoDto(String codigoDto) {
        this.codigoDto = codigoDto;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreDto() {
        return nombreDto;
    }

    public void setNombreDto(String nombreDto) {
        this.nombreDto = nombreDto;
    }

    public String getRespFiscal() {
        return respFiscal;
    }

    public void setRespFiscal(String respFiscal) {
        this.respFiscal = respFiscal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getYearFiscal() {
        return yearFiscal;
    }

    public void setYearFiscal(String yearFiscal) {
        this.yearFiscal = yearFiscal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
}
