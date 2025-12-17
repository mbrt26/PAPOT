package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaColaboraAgendaLogVisitaBean implements IConstantes {

    // Propiedades
    private int idLog;
    private double idUsuario;
    private String idCliente;
    private String nombreCliente;
    private String fechaVisita;
    private int idEstadoVisita;
    private String nombreEstado;

    //
    private String fechaInicial;
    private String fechaFinal;
    private int idLocal;
    private String telefono;
    private String nombreUsuario;
    private int totalNuevo;
    private String horaVisita;
    private int estado;

    private String nombreTercero;
    private String direccionTercero;
    private String telefonoFijo;
    private String nombreEmpresa;
    private String ciudadTercero;
    private int idOrden;
    private int item;
    private double cantidad;
    private String referenciaCliente;
    private String fechaEntrega;
    private int numeroOrden;    
    private int idFicha;

    // Metodos
    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario ;
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue() ;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setNombreCliente( String nombreCliente )
    {
        this.nombreCliente = nombreCliente ;
    }

    public String getFechaVisita()
    {
        return fechaVisita;
    }

    public String getFechaVisitaCorta()
    {
            return getFechaVisita().substring(0, 4) + "/" +
                   getFechaVisita().substring(5, 7) + "/" +
                   getFechaVisita().substring(8, 10);
    }

    public String getFechaVisitaSqlServer()
    {
            return getFechaVisita().substring(0, 4) +
                   getFechaVisita().substring(5, 7) +
                   getFechaVisita().substring(8, 10);
    }

    public void setFechaVisita( String fechaVisita )
    {
        this.fechaVisita = fechaVisita ;
    }

    public String getFechaVisitaStrOracle()
    {
        return "TO_DATE('" + getFechaVisita() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setIdEstadoVisita( int idEstadoVisita )
    {
        this.idEstadoVisita = idEstadoVisita ;
    }

    public int getIdEstadoVisita()
    {
        return idEstadoVisita;
    }

    public void setIdEstadoVisita( String idEstadoVisitaStr )
    {
        this.idEstadoVisita = new Integer(idEstadoVisitaStr).intValue() ;
    }

    public String getIdEstadoVisitaStr()
    {
        return new Integer(idEstadoVisita).toString();
    }

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado ;
    }

    public String getFechaInicial()
    {
        return fechaInicial;
    }

    public void setFechaInicial( String fechaInicial )
    {
        this.fechaInicial = fechaInicial ;
    }

    public String getFechaInicialStrOracle()
    {
        return "TO_DATE('" + getFechaInicial() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public String getFechaFinal()
    {
        return fechaFinal;
    }

    public void setFechaFinal( String fechaFinal )
    {
        this.fechaFinal = fechaFinal ;
    }

    public String getFechaFinalStrOracle()
    {
        return "TO_DATE('" + getFechaFinal() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue() ;
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono( String telefono )
    {
        this.telefono = telefono ;
    }

    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public void setNombreUsuario( String nombreUsuario )
    {
        this.nombreUsuario = nombreUsuario ;
    }

    public void setIdLog( int idLog )
    {
        this.idLog = idLog ;
    }

    public int getIdLog()
    {
        return idLog;
    }

    public void setIdLog( String idLogStr )
    {
        this.idLog = new Integer(idLogStr).intValue() ;
    }

    public String getIdLogStr()
    {
        return new Integer(idLog).toString();
    }

    public void setTotalNuevo( int totalNuevo )
    {
        this.totalNuevo = totalNuevo ;
    }

    public int getTotalNuevo()
    {
        return totalNuevo;
    }

    public void setTotalNuevo( String totalNuevoStr )
    {
        this.totalNuevo = new Integer(totalNuevoStr).intValue() ;
    }

    public String getTotalNuevoStr()
    {
        return new Integer(totalNuevo).toString();
    }

    public String getHoraVisita()
    {
        return horaVisita;
    }

    public void setHoraVisita( String horaVisita )
    {
        this.horaVisita = horaVisita ;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public String getEstadoStr() {
        return new Integer(getEstado()).toString();
    }

    public FachadaColaboraAgendaLogVisitaBean() { }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public String getDireccionTercero() {
        return direccionTercero;
    }

    public void setDireccionTercero(String direccionTercero) {
        this.direccionTercero = direccionTercero;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCiudadTercero() {
        return ciudadTercero;
    }

    public void setCiudadTercero(String ciudadTercero) {
        this.ciudadTercero = ciudadTercero;
    }

    public void setIdOrden( int idOrden )
    {
        this.idOrden = idOrden ;
    }

    public int getIdOrden()
    {
        return idOrden;
    }

    public void setIdOrden( String idOrdenStr )
    {
        this.idOrden = new Integer(idOrdenStr).intValue() ;
    }

    public String getIdOrdenStr()
    {
        return new Integer(idOrden).toString();
    }

    public int getItem() {
        return item;
    }

    public String getItemStr() {
        return  new Integer(getItem()).toString();
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getCantidadStr() {
        return new Double(getCantidad()).toString();
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setCantidad(String cantidadStr) {
        this.cantidad = new Double(cantidadStr).doubleValue();
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaEntregaCorta()
    {
            return getFechaEntrega().substring(0, 4) + "/" +
                   getFechaEntrega().substring(5, 7) + "/" +
                   getFechaEntrega().substring(8, 10);
    }

    public void setNumeroOrden( int numeroOrden )
    {
        this.numeroOrden = numeroOrden ;
    }

    public int getNumeroOrden()
    {
        return numeroOrden;
    }

    public void setNumeroOrden( String numeroOrdenStr )
    {
        this.numeroOrden = new Integer(numeroOrdenStr).intValue() ;
    }

    public String getNumeroOrdenStr()
    {
        return new Integer(numeroOrden).toString();
    }

    public void setIdFicha( int idFicha )
    {
        this.idFicha = idFicha ;
    }

    public int getIdFicha()
    {
        return idFicha;
    }

    public void setIdFicha( String idFichaStr )
    {
        this.idFicha = new Integer(idFichaStr).intValue() ;
    }

    public String getIdFichaStr()
    {
        return new Integer(idFicha).toString();
    }
}