
package co.linxsi.modelo.retencion.retencion_contable;

import java.io.Serializable;

/**
 *
 * @author Desarrollador
 */


public class Retencion_Contable_DTO implements Serializable {
private int idConcepto;
private int idSubcuenta;
private int idPersona;
private String nombreConcepto;
private Double porcentajeRetencion;
private Double vrBaseRetencion;
private int  idTipoOrdenAlcance ;
private int idOrden;
private int numeroOrden;


    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

       public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public int getIdSubcuenta() {
        return idSubcuenta;
    }

    public void setIdSubcuenta(int idSubcuenta) {
        this.idSubcuenta = idSubcuenta;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreConcepto() {
        return nombreConcepto;
    }

    public void setNombreConcepto(String nombreConcepto) {
        this.nombreConcepto = nombreConcepto;
    }

    public Double getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    public void setPorcentajeRetencion(Double porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
    }

    public Double getVrBaseRetencion() {
        return vrBaseRetencion;
    }

    public void setVrBaseRetencion(Double vrBaseRetencion) {
        this.vrBaseRetencion = vrBaseRetencion;
    }

    public int getIdTipoOrdenAlcance() {
        return idTipoOrdenAlcance;
    }

    public void setIdTipoOrdenAlcance(int idTipoOrdenAlcance) {
        this.idTipoOrdenAlcance = idTipoOrdenAlcance;
    }





   


}
