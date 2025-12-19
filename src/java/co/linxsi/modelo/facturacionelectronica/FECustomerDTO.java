package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;
import java.text.DecimalFormat;

public class FECustomerDTO implements Serializable {

    private String idCompany;
    private double idCustomer;
    private String nombreCustomer;
    private String direccion;
    private String nombreCiudad;
    private String codigoCiudad;
    private String nombreDto;
    private String codigoDto;
    private String tipoOperacion;
    private String telefono;
    private String email;
    private String regimen;
    private String respoFiscal;
    private String identificationType;
    private String companyType;
    
    DecimalFormat Sf0 = new DecimalFormat("############");

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getIdCompanySf2() {
        return Sf0.format(getIdCompany());
    }

    public double getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(double idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdCustomerSf2() {
        return Sf0.format(getIdCustomer());
    }

    public String getNombreCustomer() {
        return nombreCustomer;
    }

    public void setNombreCustomer(String nombreCustomer) {
        this.nombreCustomer = nombreCustomer;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreDto() {
        return nombreDto;
    }

    public void setNombreDto(String nombreDto) {
        this.nombreDto = nombreDto;
    }

    public String getCodigoDto() {
        return codigoDto;
    }

    public void setCodigoDto(String codigoDto) {
        this.codigoDto = codigoDto;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getRespoFiscal() {
        return respoFiscal;
    }

    public void setRespoFiscal(String respoFiscal) {
        this.respoFiscal = respoFiscal;
    }

}
