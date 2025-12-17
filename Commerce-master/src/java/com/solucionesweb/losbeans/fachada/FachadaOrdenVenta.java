package com.solucionesweb.losbeans.fachada;

// Importa los paquetes de java
import java.io.*;

import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;


public class FachadaOrdenVenta extends FachadaDctoOrdenDetalleBean
                                          implements Serializable, IConstantes {

    // Propiedades Tercero
    private double idTercero;
    private String nombreTercero;
    private String direccionTercero;
    private String telefonoFijo;
    private String telefonoCelular;
    private String telefonoFax;
    private String email;
    private String formaPago;
    private String idCliente;
    private String idRuta;

    //Propiedad Local
    private int idLocal;
    private String nombreLocal;
    private String razonSocial;
    private String nit;
    private String direccion;
    private String telefono;
    private String regimen;
    private String resolucion;
    private String ciudad;
    private String txtFactura;
    private String rango;
    private String prefijo;
    private String emailLocal;
    private int idLog;
    private double vrIva;
    private String fechaOrden;
    private double idDcto;
    private String idDctoNitCC;
    private int idDctoCruce;
    private int diasPlazo;
    private String observacion;
    private double vrVentaSinDscto;
    private int digitoVerificacion;
    private double vrVentaConIvaSinDscto;
    private double vrCostoNegociado;
    private double vrCostoNegociadoConIva;
    private double vrCostoNegociadoSinIva;
    private int idCopia;
    private String nombreCopia;
    private double vrCostoUnitarioSinIva;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat sf0 = new DecimalFormat("##############");


    //
    JhFormat jhFormat  = new JhFormat();

    //
    public double getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(double idTercero) {
        this.idTercero = idTercero;
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public String getIdTerceroSf0() {
        return  sf0.format(getIdTercero());
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public String getDireccionTercero() {
        return direccionTercero;
    }

    public void setDireccionTercero(String direccionTercero) {
        this.direccionTercero = direccionTercero;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }


    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    /**
     * @return the telefonoFax
     */
    public String getTelefonoFax() {
        return telefonoFax;
    }

    /**
     * @param telefonoFax the telefonoFax to set
     */
    public void setTelefonoFax(String telefonoFax) {
        this.telefonoFax = telefonoFax;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the idCliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idRuta
     */
    public String getIdRuta() {
        return idRuta;
    }

    /**
     * @param idRuta the idRuta to set
     */
    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTxtFactura() {
        return txtFactura;
    }

    public void setTxtFactura(String txtFactura) {
        this.txtFactura = txtFactura;
    }

    public String getRango() {
        return rango;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getEmailLocal() {
        return emailLocal;
    }

    public void setEmailLocal(String emailLocal) {
        this.emailLocal = emailLocal;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public double getVrIva() {
        return vrIva;
    }

    public void setVrIva(double vrIva) {
        this.vrIva = vrIva;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaOrdenCorta() {

            return getFechaOrden().substring(0, 4) + "/" +
                   getFechaOrden().substring(5, 7) + "/" +
                   getFechaOrden().substring(8, 10);

    }

    public String getFechaVencimiento() {

            int xAno  = new Integer(getFechaOrden().substring(0, 4)).intValue();
            int xMes  = new Integer(getFechaOrden().substring(5, 7)).intValue();
            int xDia  = new Integer(getFechaOrden().substring(8, 10)).intValue();

            //
            Day fechaFactura       = new Day(xAno,xMes,xDia);

            fechaFactura.advance(diasPlazo);

            //
            return fechaFactura.getFechaFormateada();

    }

    public String getLocalOrdenVenta() {

         return  jhFormat.fill(getIdLocalStr(),'0', 3, 0) + "-" +
                 jhFormat.fill(getIdTipoOrdenStr(),'0', 1, 0)    + "-" +
                 jhFormat.fill(getIdOrdenStr(),'0', 6, 0);
    }

    public double getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(double idDcto) {
        this.idDcto = idDcto;
    }

    public void setIdDcto( String idDctoStr )
    {
        this.idDcto = new Double(idDctoStr).doubleValue();
    }

    public String getIdDctoStr()
    {
        return new Double(getIdDcto()).toString();
    }

    public String getIdDctoDf0()
    {
        return df0.format(getIdDcto());
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public int getIdDctoCruce() {
        return idDctoCruce;
    }

    public void setIdDctoCruce(int idDctoCruce) {
        this.idDctoCruce = idDctoCruce;
    }

    public void setIdDctoCruce( String idDctoCruceStr )
    {
        this.idDctoCruce = new Integer(idDctoCruceStr).intValue();
    }

    public String getIdDctoCruceStr()
    {
        return new Integer(getIdDctoCruce()).toString();
    }

    public String getIdDctoCruceDf0()
    {
        return df0.format(getIdDctoCruce());
    }

    public int getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(int diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public void setDiasPlazo( String diasPlazoStr )
    {
        this.diasPlazo = new Integer(diasPlazoStr).intValue();
    }

    public String getDiasPlazoStr()
    {
        return new Integer(getDiasPlazo()).toString();
    }

    public String getDiasPlazoDf0()
    {
        return df0.format(getDiasPlazo());
    }

    public String getFormaPagoTexto()
    {
        if (getDiasPlazo()== 0) {

            return "CONTADO " + getDiasPlazoDf0() + " DIAS";

        } else {
            return "CREDITO " + getDiasPlazoDf0() + " DIAS";
        }
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getVrVentaSinDscto() {
        return vrVentaSinDscto;
    }

    public void setVrVentaSinDscto(double vrVentaSinDscto) {
        this.vrVentaSinDscto = vrVentaSinDscto;
    }

    public int getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(int digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public void setDigitoVerificacion( String digitoVerificacionStr )
    {
        this.digitoVerificacion = new Integer(digitoVerificacionStr).intValue();
    }

    public String getDigitoVerificacionStr()
    {
        return new Integer(getDigitoVerificacion()).toString();
    }

    public String getDigitoVerificacionDf0()
    {
        return df0.format(getDigitoVerificacion());
    }

    public double getVrVentaConIvaSinDscto() {
        return vrVentaConIvaSinDscto;
    }

    public double getVrCostoNegociado() {
        return vrCostoNegociado;
    }

    public void setVrCostoNegociado(double vrCostoNegociado) {
        this.vrCostoNegociado = vrCostoNegociado;
    }

    public void setVrVentaConIvaSinDscto(double vrVentaConIvaSinDscto) {
        this.vrVentaConIvaSinDscto = vrVentaConIvaSinDscto;
    }

    public double getVrCostoNegociadoConIva() {
        return vrCostoNegociadoConIva;
    }

    public void setVrCostoNegociadoConIva(double vrCostoNegociadoConIva) {
        this.vrCostoNegociadoConIva = vrCostoNegociadoConIva;
    }

    public double getVrCostoNegociadoSinIva() {
        return vrCostoNegociadoSinIva;
    }

    public void setVrCostoNegociadoSinIva(double vrCostoNegociadoSinIva) {
        this.vrCostoNegociadoSinIva = vrCostoNegociadoSinIva;
    }

    public int getIdCopia() {
        return idCopia;
    }

    public String getIdCopiaStr() {
        return new Integer(getIdCopia()).toString();
    }


    public void setIdCopia(int idCopia) {
        this.idCopia = idCopia;
    }

    public void setIdCopia(String idCopiaStr) {
        this.idCopia = new Integer(idCopiaStr).intValue();
    }

    public String getNombreCopia() {
        return nombreCopia;
    }

    public void setNombreCopia(String nombreCopia) {
        this.nombreCopia = nombreCopia;
    }

    public double getVrCostoUnitarioSinIva() {
        return vrCostoUnitarioSinIva;
    }

    public void setVrCostoUnitarioSinIva(double vrCostoUnitarioSinIva) {
        this.vrCostoUnitarioSinIva = vrCostoUnitarioSinIva;
    }
}
