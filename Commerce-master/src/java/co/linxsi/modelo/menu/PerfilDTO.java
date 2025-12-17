package co.linxsi.modelo.menu;

import java.io.Serializable;

public class PerfilDTO
  implements Serializable
{
  private int idPerfil;
  private int idOpcion;

  public String getIdOpcionStr()
  {
    return Integer.toString(getIdOpcion());
  }

  public int getIdPerfil() {
    return this.idPerfil;
  }

  public void setIdPerfil(int idPerfil) {
    this.idPerfil = idPerfil;
  }

  public String getIdPerfilStr() {
    return Integer.toString(getIdPerfil());
  }

  public int getIdOpcion() {
    return this.idOpcion;
  }

  public void setIdOpcion(int idOpcion) {
    this.idOpcion = idOpcion;
  }

  public void setIdOpcion(String idOpcionStr) {
    this.idOpcion = Integer.parseInt(idOpcionStr);
  }
}