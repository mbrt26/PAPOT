/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.inventario.carga_inventario;

/**
 *
 * @author Desarrollador
 */
public class Carga_Inventario_DTO {

    String nombrePLU;
    String nombreBodega;
    String material;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }
    Double vr_costo;
    Double existencia_teorica;
    int sk_bodega;
    int sk_plu;
    Double existencia;

    public int getSk_plu() {
        return sk_plu;
    }

    public void setSk_plu(int sk_plu) {
        this.sk_plu = sk_plu;
    }

    public int getSk_bodega() {
        return sk_bodega;
    }

    public void setSk_bodega(int sk_bodega) {
        this.sk_bodega = sk_bodega;
    }

    public Double getExistencia_teorica() {
        return existencia_teorica;
    }

    public void setExistencia_teorica(Double existencia_teorica) {
        this.existencia_teorica = existencia_teorica;
    }

    public Double getVr_costo() {
        return vr_costo;
    }

    public void setVr_costo(Double vr_costo) {
        this.vr_costo = vr_costo;
    }

    public String getNombrePLU() {
        return nombrePLU;
    }

    public void setNombrePLU(String nombrePLU) {
        this.nombrePLU = nombrePLU;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

}
