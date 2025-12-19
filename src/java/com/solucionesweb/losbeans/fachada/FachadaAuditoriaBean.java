package com.solucionesweb.losbeans.fachada;

import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;

/**
 *
 * @author commerce
 */
public class FachadaAuditoriaBean  implements Serializable, IConstantes {

    private int idAuditoria;
    private int estado;
    private String nombreAuditoria;

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombreAuditoria() {
        return nombreAuditoria;
    }

    public void setNombreAuditoria(String nombreAuditoria) {
        this.nombreAuditoria = nombreAuditoria;
    }

    public String getNombreAuditoriaMayuscula() {
        return "*****" +
               getNombreAuditoria().toUpperCase() +
               "*****";
    }

}
