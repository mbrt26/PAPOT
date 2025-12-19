package com.solucionesweb.losbeans.fachada;


// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaDctoAuditoria implements IConstantes {

    private int idLocal;
    private int idTipoOrden;
    private double idDcto;
    private int idOrden;
    private int idUsuarioResponsable;
    private int idAuditoria;
    private String fechaAuditoria;
    private int estado;
    private String ipTx;
    private int reimpresion;

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public double getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(double idDcto) {
        this.idDcto = idDcto;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(int idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(String fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIpTx() {
        return ipTx;
    }

    public void setIpTx(String ipTx) {
        this.ipTx = ipTx;
    }

    /**
     * @return the reimpresion
     */
    public int getReimpresion() {
        return reimpresion;
    }

    /**
     * @param reimpresion the reimpresion to set
     */
    public void setReimpresion(int reimpresion) {
        this.reimpresion = reimpresion;
    }

     public String getFechaAuditoriaSqlServer() {
            return getFechaAuditoria().substring(0, 4) + "-" +
                   getFechaAuditoria().substring(5, 7) + "-" +
                   getFechaAuditoria().substring(8, 10);
    }

    

    
}
