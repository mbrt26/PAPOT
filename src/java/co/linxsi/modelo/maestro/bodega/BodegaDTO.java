package co.linxsi.modelo.maestro.bodega;

import java.io.Serializable;

public class BodegaDTO
  implements Serializable
{
  private int sk_bodega;
  private String nombre;
  private String nombreTipoBodega;
  private int sk_local;
  private int sk_estado;
  private int actualiza;
  private int sk_inventario;
  private int sk_tipo_bodega;

    public String getNombreTipoBodega() {
        return nombreTipoBodega;
    }

    public void setNombreTipoBodega(String nombreTipoBodega) {
        this.nombreTipoBodega = nombreTipoBodega;
    }
  

    public int getSk_tipo_bodega() {
        return sk_tipo_bodega;
    }

    public void setSk_tipo_bodega(int sk_tipo_bodega) {
        this.sk_tipo_bodega = sk_tipo_bodega;
    }

  public int getSk_bodega()
  {
    return this.sk_bodega;
  }

  public void setSk_bodega(int sk_bodega) {
    this.sk_bodega = sk_bodega;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getSk_local() {
    return this.sk_local;
  }

  public void setSk_local(int sk_local) {
    this.sk_local = sk_local;
  }

  public int getSk_estado() {
    return this.sk_estado;
  }

  public void setSk_estado(int sk_estado) {
    this.sk_estado = sk_estado;
  }

  public int getActualiza() {
    return this.actualiza;
  }

  public void setActualiza(int actualiza) {
    this.actualiza = actualiza;
  }

  public int getSk_inventario() {
    return this.sk_inventario;
  }

  public void setSk_inventario(int sk_inventario) {
    this.sk_inventario = sk_inventario;
  }
}