package co.linxsi.modelo.facturacionelectronica;

import java.io.Serializable;


public class FacturacionElectronicaDTO implements Serializable{
    private String xml_Head;
    private String xml_Dtl;
    private String xml_Tax;
    private String xml_Company;
    private String xml_SalesTRC;
    private String xml_Customer;
    private String xml_COOneTime;
    private int idOrden;
    private int idTipoOrden;
    private int idDcto;
    private int idDctoDian;
    private int tipo_xml;
    private boolean status;
    private String tracer;
    private String xml;
    private String url;
    private String prefijo;

    public String getXml_Head() {
        return xml_Head;
    }

    public void setXml_Head(String xml_Head) {
        this.xml_Head = xml_Head;
    }

    public String getXml_Dtl() {
        return xml_Dtl;
    }

    public void setXml_Dtl(String xml_Dtl) {
        this.xml_Dtl = xml_Dtl;
    }

    public String getXml_Tax() {
        return xml_Tax;
    }

    public void setXml_Tax(String xml_Tax) {
        this.xml_Tax = xml_Tax;
    }

    public String getXml_Company() {
        return xml_Company;
    }

    public void setXml_Company(String xml_Company) {
        this.xml_Company = xml_Company;
    }

    public String getXml_SalesTRC() {
        return xml_SalesTRC;
    }

    public void setXml_SalesTRC(String xml_SalesTRC) {
        this.xml_SalesTRC = xml_SalesTRC;
    }

    public String getXml_Customer() {
        return xml_Customer;
    }

    public void setXml_Customer(String xml_Customer) {
        this.xml_Customer = xml_Customer;
    }

    public String getXml_COOneTime() {
        return xml_COOneTime;
    }

    public void setXml_COOneTime(String xml_COOneTime) {
        this.xml_COOneTime = xml_COOneTime;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public int getIdDctoDian() {
        return idDctoDian;
    }

    public void setIdDctoDian(int idDctoDian) {
        this.idDctoDian = idDctoDian;
    }

    public int getTipo_xml() {
        return tipo_xml;
    }

    public void setTipo_xml(int tipo_xml) {
        this.tipo_xml = tipo_xml;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTracer() {
        return tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
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

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
    
    
    
}
