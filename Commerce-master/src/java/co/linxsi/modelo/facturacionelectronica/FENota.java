package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;


public class FENota implements Serializable{
    private String causal;
    private String nombreCausal;
    private String facturaInterna;
    private String facturaDian;
    private String fechaCruce;

    public String getCausal() {
        return causal;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }

    public String getNombreCausal() {
        return nombreCausal;
    }

    public void setNombreCausal(String nombreCausal) {
        this.nombreCausal = nombreCausal;
    }

    public String getFacturaInterna() {
        return facturaInterna;
    }

    public void setFacturaInterna(String facturaInterna) {
        this.facturaInterna = facturaInterna;
    }

    public String getFacturaDian() {
        return facturaDian;
    }

    public void setFacturaDian(String facturaDian) {
        this.facturaDian = facturaDian;
    }

    public String getFechaCruce() {
        return fechaCruce;
    }

    public void setFechaCruce(String fechaCruce) {
        this.fechaCruce = fechaCruce;
    }
    
    
    
    
}
