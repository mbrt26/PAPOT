package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;
import java.text.DecimalFormat;

public class FEHeadDTO implements Serializable {

    private String company;
    private String fecha;
    private String resolucion;
    private String prefijo;
    private String nombreUsuario;
    private double idUsuario;
    private String nit;
    private int dias;
    private String fechaVencimiento;
    private String telefono;
    private String nitCliente;
    private String nombreCliente;
    private double vrBase;
    private double vriva;
    private double vrRetefunte;
    private double vrRteIva;
    private double totalImpuesto;
    private double totalfactura;
    //Ambientales
//    private String textLegal = "Informacion Comercial: pa@plasticosambientales.com \n"
//            + "Informacion Facturacion: facturacion@plasticosambientales.com \n"
//            + "REGIMEN ORDINARIO. No somos grandes contribuyentes, No somos Retenedores de IVA. Esta factura se asimila en todos sus efectos legales a una letra de cambio (Artículos 621, 772, 773, 774 del Código de Comercio).  A partir de la fecha de vencimiento se causaran intereses de mora comerciales liquidados a la tasa máxima permitida por la ley y certificada por la Superintendencia. Articulo 884 del Código de Comercio. Autorizamos en caso de mora en el pago de la presente obligación a reportar a las Centrales de Riesgo o similares.\n"
//            + "Se presume que la persona que recibe la mercancia y firma la factura esta autorizada porel comprador o su respresentante legal, para comprometerlo sin que se requierareconocimiento de firma.";
    //Union
    /*private String textLegal = "Informacion Comercial: ventas@plasticosunion.com \n"
            + "Informacion Facturacion: pufactura@plasticosunion.com \n"
            + "REGIMEN ORDINARIO. No somos grandes contribuyentes, No somos Retenedores de IVA. Esta factura se asimila en todos sus efectos legales a una letra de cambio (Artículos 621, 772, 773, 774 del Código de Comercio).  A partir de la fecha de vencimiento se causaran intereses de mora comerciales liquidados a la tasa máxima permitida por la ley y certificada por la Superintendencia. Articulo 884 del Código de Comercio. Autorizamos en caso de mora en el pago de la presente obligación a reportar a las Centrales de Riesgo o similares.\n"
            + "Se presume que la persona que recibe la mercancia y firma la factura esta autorizada porel comprador o su respresentante legal, para comprometerlo sin que se requierareconocimiento de firma.";*/
    private String textLegal;
    private String observacion;
    private String ordenCompra;

    DecimalFormat sf0 = new DecimalFormat("##############");
    DecimalFormat sf2 = new DecimalFormat("##############");

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public double getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuarioSf0() {
        return sf0.format(getIdUsuario());
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getVrBase() {
        return vrBase;
    }

    public void setVrBase(double vrBase) {
        this.vrBase = vrBase;
    }

    public String getVrBaseSf0() {
        return sf2.format(getVrBase());
    }

    public double getVriva() {
        return vriva;
    }

    public void setVriva(double vriva) {
        this.vriva = vriva;
    }

    public String getVrivaSf0() {
        return sf2.format(getVriva());
    }

    public double getVrRetefunte() {
        return vrRetefunte;
    }

    public void setVrRetefunte(double vrRetefunte) {
        this.vrRetefunte = vrRetefunte;
    }

    public String getVrRetefunteSf0() {
        return sf2.format(getVrRetefunte());
    }

    public double getVrRteIva() {
        return vrRteIva;
    }

    public void setVrRteIva(double vrRteIva) {
        this.vrRteIva = vrRteIva;
    }

    public String getVrRteIvaSf0() {
        return sf2.format(getVrRteIva());
    }

    public double getTotalImpuesto() {
        return totalImpuesto;
    }

    public void setTotalImpuesto(double totalImpuesto) {
        this.totalImpuesto = totalImpuesto;
    }

    public String getTotalImpuestoSf0() {
        return sf2.format(getTotalImpuesto());
    }

    public double getTotalfactura() {
        return totalfactura;
    }

    public void setTotalfactura(double totalfactura) {
        this.totalfactura = totalfactura;
    }

    public String getTotalfacturaSf0() {
        return sf2.format(getTotalfactura());
    }

    public String getTextLegal() {
        return textLegal;
    }

    public void setTextLegal(String textLegal) {
        this.textLegal = textLegal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

}
