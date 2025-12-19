/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.local;

import java.io.Serializable;

/**
 *
 * @author Edgar García
 */
public class Local_DTO implements Serializable {

    private String razonSocial;
    private String nombreLocal;
    private String NIT;
    private String email;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String fax;
    private int idRegimen;
    private String nombreRegimen;
    private int idResponsabilidadFiscal;
    private String resolucion;
    private String resolucion2;
    private String resFiscal1;
    private String tipoOperacion;
    private String resFiscal2;
    private String resFiscal3;
    private String resFiscal4;
    private String departamento;
    private static final String CARACTER = "\\";
    private String idCiudad;
    private String idDpto;
    private String yearFiscal;
    private String pais = "COLOMBIA";

    public String getResolucion2() {
        return resolucion2;
    }

    public void setResolucion2(String resolucion2) {
        this.resolucion2 = resolucion2;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getYearFiscal() {
        return yearFiscal;
    }

    public void setYearFiscal(String yearFiscal) {
        this.yearFiscal = yearFiscal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(String idDpto) {
        this.idDpto = idDpto;
    }

    public int getIdRegimen() {
        return idRegimen;
    }

    public void setIdRegimen(int idRegimen) {
        this.idRegimen = idRegimen;
    }

    public String getResFiscal1() {
        return resFiscal1;
    }

    public void setResFiscal1(String resFiscal1) {
        this.resFiscal1 = resFiscal1;
    }

    public String getResFiscal2() {
        return resFiscal2;
    }

    public void setResFiscal2(String resFiscal2) {
        this.resFiscal2 = resFiscal2;
    }

    public String getResFiscal3() {
        return resFiscal3;
    }

    public void setResFiscal3(String resFiscal3) {
        this.resFiscal3 = resFiscal3;
    }

    public String getResFiscal4() {
        return resFiscal4;
    }

    public void setResFiscal4(String resFiscal4) {
        this.resFiscal4 = resFiscal4;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNombreRegimen() {
        return nombreRegimen;
    }

    public void setNombreRegimen(String nombreRegimen) {
        this.nombreRegimen = nombreRegimen;
    }

    public int getIdResponsabilidadFiscal() {
        return idResponsabilidadFiscal;
    }

    public void setIdResponsabilidadFiscal(int idResponsabilidadFiscal) {
        this.idResponsabilidadFiscal = idResponsabilidadFiscal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRazonSocial());
        sb.append(CARACTER);
        sb.append(getNombreLocal());
        sb.append(CARACTER);
        sb.append(getNIT());
        sb.append(CARACTER);
        sb.append(getEmail());
        sb.append(CARACTER);
        sb.append(getDireccion());
        sb.append(CARACTER);
        sb.append(getIdCiudad());
        sb.append(CARACTER);
        sb.append(getTelefono());
        sb.append(CARACTER);
        sb.append(getFax());
        sb.append(CARACTER);
        sb.append(getResolucion());
        sb.append(CARACTER);
        sb.append(getResolucion2());
        sb.append(CARACTER);
        sb.append(getResFiscal1());
        sb.append(CARACTER);
        sb.append(getResFiscal2());
        sb.append(CARACTER);
        sb.append(getResFiscal3());
        sb.append(CARACTER);
        sb.append(getResFiscal4());
        sb.append(CARACTER);
        sb.append(getIdRegimen());
        sb.append(CARACTER);
        sb.append(getTipoOperacion());
        return sb.toString();
    }

    public String ResponsabilidadesFiscales() {
        StringBuilder sb = new StringBuilder();
        String res1 = getResFiscal1().equals("0") ? "" : getResFiscal1();
        String res2 = getResFiscal2().equals("0") ? "" : ";" + getResFiscal2();
        String res3 = getResFiscal3().equals("0") ? "" : ";" + getResFiscal3();
        String res4 = getResFiscal4().equals("0") ? "" : ";" + getResFiscal4();
        sb.append(res1);
        sb.append(res2);
        sb.append(res3);
        sb.append(res4);

        return sb.toString().charAt(0) == ';' ? sb.toString().substring(1) : sb.toString();
    }

}
