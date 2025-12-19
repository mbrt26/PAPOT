package co.linxsi.modelo.menu;

import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.text.DecimalFormat;

public class LoginDTO
  implements Serializable, IConstantes
{
  private int idLocal;
  private double idUsuario;
  private String nombreUsuario;
  private String clave;
  private int idNivel;
  private String direccion;
  private String telefono;
  private String fechaCambioClave;
  private int estado;
  private int idLocalUsuario;
  private String strIdLista;
  private String email;
  private String idRuta;
  private String aliasUsuario;
  private int idSeq;
  private int indicadorInicial;
  private int indicadorFinal;
  private String idNivelCadena;
  private int idMostrador;
  private double idOperario;
  private int skBodega;
  DecimalFormat sf0 = new DecimalFormat("###############");
  private boolean vigente;

  public double getIdUsuario()
  {
    return this.idUsuario;
  }

  public String getIdUsuarioStr()
  {
    return new Double(this.idUsuario).toString();
  }

  public String getIdUsuarioSf0()
  {
    return this.sf0.format(getIdUsuario());
  }

  public void setIdUsuario(double idUsuario)
  {
    this.idUsuario = idUsuario;
  }

  public void setIdUsuario(String idUsuarioStr)
  {
    this.idUsuario = new Double(idUsuarioStr).doubleValue();
  }

  public String getNombreUsuario()
  {
    return this.nombreUsuario.toUpperCase();
  }

  public void setNombreUsuario(String nombreUsuario)
  {
    this.nombreUsuario = nombreUsuario;
  }

  public String getClave()
  {
    return this.clave;
  }

  public void setClave(String clave)
  {
    this.clave = clave.trim();
  }

  public int getIdNivel()
  {
    return this.idNivel;
  }

  public String getIdNivelStr()
  {
    return new Integer(this.idNivel).toString();
  }

  public void setIdNivel(int idNivel)
  {
    this.idNivel = idNivel;
  }

  public void setIdNivel(String idNivelStr)
  {
    this.idNivel = new Integer(idNivelStr).intValue();
  }

  public String getDireccion()
  {
    return this.direccion;
  }

  public void setDireccion(String direccion)
  {
    this.direccion = direccion.trim();
  }

  public String getTelefono()
  {
    return this.telefono;
  }

  public void setTelefono(String telefono)
  {
    this.telefono = telefono.trim();
  }

  public void setFechaCambioClave(String fechaCambioClave)
  {
    this.fechaCambioClave = fechaCambioClave;
  }

  public String getFechaCambioClave()
  {
    return this.fechaCambioClave;
  }

  public String getFechaCambioClaveSqlServer()
  {
    return getFechaCambioClave().substring(0, 4) + 
      getFechaCambioClave().substring(5, 7) + 
      getFechaCambioClave().substring(8, 10);
  }

  public int getEstado()
  {
    return this.estado;
  }

  public String getEstadoStr()
  {
    return new Integer(this.estado).toString();
  }

  public void setEstado(int estado)
  {
    this.estado = estado;
  }

  public void setEstado(String estadoStr)
  {
    this.estado = new Integer(estadoStr).intValue();
  }

  public void setVigente(boolean vigente) {
    this.vigente = vigente;
  }

  public boolean isVigente() {
    return this.vigente;
  }

  public int getIdLocalUsuario()
  {
    return this.idLocalUsuario;
  }

  public void setIdLocalUsuario(int idLocalUsuario)
  {
    this.idLocalUsuario = idLocalUsuario;
  }

  public void setIdLocalUsuario(String idLocalUsuarioStr)
  {
    this.idLocalUsuario = new Integer(idLocalUsuarioStr).intValue();
  }

  public String getIdLocalUsuarioStr()
  {
    return new Integer(this.idLocalUsuario).toString();
  }

  public String getStrIdLista()
  {
    return this.strIdLista;
  }

  public void setStrIdLista(String strIdLista)
  {
    this.strIdLista = strIdLista;
  }

  public String getEmail()
  {
    if ((this.email == null) || 
      (this.email
      .trim().length() == 0)) {
      return "nn";
    }
    return this.email;
  }

  public void setEmail(String email) {
    if ((this.email == null) || 
      (this.email
      .trim().length() == 0)) {
      this.email = "nn";
    }
    this.email = email;
  }

  public String getIdRuta()
  {
    return this.idRuta;
  }

  public void setIdRuta(String idRuta)
  {
    this.idRuta = idRuta.trim();
  }

  public String getAliasUsuario() {
    return this.aliasUsuario;
  }

  public void setAliasUsuario(String aliasUsuario) {
    this.aliasUsuario = aliasUsuario.toUpperCase();
  }

  public void setIdSeq(int idSeq)
  {
    this.idSeq = idSeq;
  }

  public int getIdSeq()
  {
    return this.idSeq;
  }

  public void setIdSeq(String idSeqStr)
  {
    this.idSeq = new Integer(idSeqStr).intValue();
  }

  public String getIdSeqStr()
  {
    return new Integer(this.idSeq).toString();
  }

  public int getIndicadorInicial()
  {
    return this.indicadorInicial;
  }

  public void setIndicadorInicial(int indicadorInicial)
  {
    this.indicadorInicial = indicadorInicial;
  }

  public void setIndicadorInicial(String indicadorInicialStr)
  {
    this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
  }

  public String getIndicadorInicialStr()
  {
    return new Integer(this.indicadorInicial).toString();
  }

  public int getIndicadorFinal()
  {
    return this.indicadorFinal;
  }

  public void setIndicadorFinal(int indicadorFinal)
  {
    this.indicadorFinal = indicadorFinal;
  }

  public void setIndicadorFinal(String indicadorFinalStr)
  {
    this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
  }

  public String getIndicadorFinalStr()
  {
    return new Integer(this.indicadorFinal).toString();
  }

  public String getIdNivelCadena()
  {
    return this.idNivelCadena;
  }

  public void setIdNivelCadena(String idNivelCadena)
  {
    this.idNivelCadena = idNivelCadena.trim();
  }

  public int getIdMostrador() {
    return this.idMostrador;
  }

  public String getIdMostradorStr()
  {
    return new Integer(this.idMostrador).toString();
  }

  public void setIdMostrador(int idMostrador) {
    this.idMostrador = idMostrador;
  }

  public void setIdMostrador(String idMostradorStr)
  {
    this.idMostrador = new Integer(idMostradorStr).intValue();
  }

  public double getIdOperario() {
    return this.idOperario;
  }

  public String getIdOperarioStr() {
    return new Double(this.idOperario).toString();
  }

  public String getIdOperarioSf0() {
    return this.sf0.format(getIdOperario());
  }

  public void setIdOperario(double idOperario)
  {
    this.idOperario = idOperario;
  }

  public void setIdOperario(String idOperarioStr) {
    this.idOperario = new Double(idOperarioStr).doubleValue();
  }

  public int getIdLocal()
  {
    return this.idLocal;
  }

  public void setIdLocal(int idLocal) {
    this.idLocal = idLocal;
  }

  public int getSkBodega() {
    return this.skBodega;
  }

  public void setSkBodega(int skBodega) {
    this.skBodega = skBodega;
  }
}