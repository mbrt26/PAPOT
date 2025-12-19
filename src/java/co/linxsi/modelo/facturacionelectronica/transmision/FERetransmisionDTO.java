
package co.linxsi.modelo.facturacionelectronica.transmision;

import java.io.Serializable;


public class FERetransmisionDTO implements Serializable{
    private int iddctoDian;
    private int idorden;
    private String prefijo;
    private String nombreCliente;
    private String idCliente;
    private boolean estado;
    private String xml;
    private String url;
    private int idTipoOrden;
    private String xml_head;
    private String xml_dtl;
    private String xml_tax;
    private String xml_customer;
    private String xml_company;
    private String xml_payment;
    private String xml_trc;
    private String xml_netime;
    private String fechaDcto;
    private int iddcto;
    private String dctoDianOrigen;

    public int getIddctoDian() {
        return iddctoDian;
    }

    public void setIddctoDian(int iddctoDian) {
        this.iddctoDian = iddctoDian;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public String getXml_head() {
        return xml_head;
    }

    public void setXml_head(String xml_head) {
        this.xml_head = xml_head;
    }

    public String getXml_dtl() {
        return xml_dtl;
    }

    public void setXml_dtl(String xml_dtl) {
        this.xml_dtl = xml_dtl;
    }

    public String getXml_tax() {
        return xml_tax;
    }

    public void setXml_tax(String xml_tax) {
        this.xml_tax = xml_tax;
    }

    public String getXml_customer() {
        return xml_customer;
    }

    public void setXml_customer(String xml_customer) {
        this.xml_customer = xml_customer;
    }

    public String getXml_company() {
        return xml_company;
    }

    public void setXml_company(String xml_company) {
        this.xml_company = xml_company;
    }

    public String getXml_payment() {
        return xml_payment;
    }

    public void setXml_payment(String xml_payment) {
        this.xml_payment = xml_payment;
    }

    public String getXml_trc() {
        return xml_trc;
    }

    public void setXml_trc(String xml_trc) {
        this.xml_trc = xml_trc;
    }

    public String getXml_netime() {
        return xml_netime;
    }

    public void setXml_netime(String xml_netime) {
        this.xml_netime = xml_netime;
    }

    public String getFechaDcto() {
        return fechaDcto;
    }

    public void setFechaDcto(String fechaDcto) {
        this.fechaDcto = fechaDcto;
    }

    public int getIdorden() {
        return idorden;
    }

    public void setIdorden(int idorden) {
        this.idorden = idorden;
    }

    public int getIddcto() {
        return iddcto;
    }

    public void setIddcto(int iddcto) {
        this.iddcto = iddcto;
    }

    public String getDctoDianOrigen() {
        return dctoDianOrigen;
    }

    public void setDctoDianOrigen(String dctoDianOrigen) {
        this.dctoDianOrigen = dctoDianOrigen;
    }
    
       }
