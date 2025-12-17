package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FEDtlDTO implements Serializable {

    private int idplu;
    private double cantidad;
    private String nombrePlu;
    private double vrVenta;
    private double subtotal;
    private String nombreRefCliente;
    private String comentario;
    private double porcentajeIva;
    private double iva;
    private String orcenCompra;
    private String ot;
    private int item;
    private String unidadMedida;
    private int idFicha = 0;

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public DecimalFormat getSf2() {
        return Sf2;
    }

    public void setSf2(DecimalFormat Sf2) {
        this.Sf2 = Sf2;
    }
    DecimalFormat Sf2 = new DecimalFormat("############");
    final public static DecimalFormat df = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public int getIdplu() {
        return idplu;
    }

    public void setIdplu(int idplu) {
        this.idplu = idplu;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidadf2() {
        return Sf2.format(getCantidad());
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public double getVrVenta() {
        return vrVenta;
    }

    public void setVrVenta(double vrVenta) {
        this.vrVenta = vrVenta;
    }

    public String getVrVentaSf2() {
        return Sf2.format(getVrVenta());
    }
public String getVrVentaSf3() {
        return df.format(getVrVenta());
    }
    public double getSubtotal() {
        return subtotal;
    }

    public String getSubtotalSf2() {
        return Sf2.format(getSubtotal());
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getNombreRefCliente() {
        return nombreRefCliente;
    }

    public void setNombreRefCliente(String nombreRefCliente) {
        this.nombreRefCliente = nombreRefCliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public String getPorcentajeIvaSf2() {
        return Sf2.format(getPorcentajeIva());
    }

    public double getIva() {
        return iva;
    }

    public String getIvaSf2() {
        return Sf2.format(getIva());
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getOrcenCompra() {
        return orcenCompra;
    }

    public void setOrcenCompra(String orcenCompra) {
        this.orcenCompra = orcenCompra;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

}
