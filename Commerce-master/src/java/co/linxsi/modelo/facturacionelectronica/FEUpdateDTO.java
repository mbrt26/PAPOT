package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;


public class FEUpdateDTO implements Serializable{
    private String url;
    private String status;
    private String tracer;
    private int idorden;
    private String nit;
    private String documento;
    private String documentoDian;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTracer() {
        return tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    public int getIdorden() {
        return idorden;
    }

    public void setIdorden(int idorden) {
        this.idorden = idorden;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumentoDian() {
        return documentoDian;
    }

    public void setDocumentoDian(String documentoDian) {
        this.documentoDian = documentoDian;
    }
    
    
    
}
