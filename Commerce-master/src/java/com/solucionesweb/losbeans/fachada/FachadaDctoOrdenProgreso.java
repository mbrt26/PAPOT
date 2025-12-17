package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

import com.solucionesweb.losbeans.utilidades.JhFormat;
// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FachadaDctoOrdenProgreso implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private int idOrden;
    private int item;
    private int idOperacion;
    private double idOperario;
    private double cantidadPerdida;
    private double cantidadTerminada;
    private double pesoPerdido;
    private String fechaInicio;
    private String fechaFin;
    private int estado;
    private int idCausa;
    private double pesoTerminado;
    private String nombreOperario;
    private int itemPadre;
    private String idCliente;
    private String nombreOperacion;
    private String fechaOrden;
    private int idLog;
    private int idFicha;
    private String ordenCompra;
    private String fechaEntrega;
    private double cantidad;
    private String nombreTercero;
    private int numeroOrden;
    private String referenciaCliente;
    private String referencia;
    private double cantidadEntregada;
    private double cantidadFacturada;
    private double cantidadPedida;
    private double pesoPedido;
    private int idControl;
    private int idPlu;
    private String nombrePlu;
    private int idControlTipo;
    private int idCopia;
    private String nombreCopia;
    private String observacion;
    private double idUsuario;
    private int idSigno;

    //--
    private double pesoTerminadoSaldo;
    private double cantidadTerminadaSaldo;
    private String nombreTerceroOperacion;
    private double cantidadEntrada;
    private double cantidadSalida;
    private double cantidadPendiente;
    private double vrCostoBaseMAT;
    private double vrCostoBaseCIF;
    private double vrCostoBaseMOD;
    private double pesoTara;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private double cantidadMinuto;
    private String nombreItem;
    private int idTipoCausa;
    private String nombreCausa;
    private String idDctoNitCC;
    private int idOrdenCruce;
    private int IdMaquina;
    private double tiempoPerdido;
    private int idCausaParo;
    private int idTurno = 0;
    private String fechaProduccion;


    public int getIdMaquina() {
        return IdMaquina;
    }

    public void setIdMaquina(int IdMaquina) {
        this.IdMaquina = IdMaquina;
    }

    //
    DecimalFormat df2 = new DecimalFormat("###,###,##0.00");
    DecimalFormat df1 = new DecimalFormat("###,###,##0.0");
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat sf0 = new DecimalFormat("###############");
    DecimalFormat sf1 = new DecimalFormat("########0.0");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    //
    JhFormat jhFormat = new JhFormat();

    // Metodos
    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr() {
        return new Integer(idLocal).toString();
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr() {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrdenStr) {
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public String getIdOrdenStr() {
        return new Integer(idOrden).toString();
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getItem() {
        return item;
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public String getItemStr() {
        return new Integer(item).toString();
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }

    public String getIdOperacionStr() {
        return new Integer(idOperacion).toString();
    }

    public void setIdOperario(double idOperario) {
        this.idOperario = idOperario;
    }

    public double getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(String idOperarioStr) {
        this.idOperario = new Double(idOperarioStr).doubleValue();
    }

    public String getIdOperarioStr() {
        return new Double(idOperario).toString();
    }

    public String getIdOperarioSf0() {
        return sf0.format(getIdOperario());
    }

    public void setCantidadPerdida(double cantidadPerdida) {
        this.cantidadPerdida = cantidadPerdida;
    }

    public double getCantidadPerdida() {
        return cantidadPerdida;
    }

    public String getCantidadPerdidaDf0() {
        return df0.format(getCantidadPerdida());
    }

    public String getCantidadPerdidaDf2() {
        return df2.format(getCantidadPerdida());
    }

    public int getCantidadPerdidaInt() {
        return (int) getCantidadPerdida();
    }

    public void setCantidadPerdida(String cantidadPerdidaStr) {
        this.cantidadPerdida = new Double(cantidadPerdidaStr).doubleValue();
    }

    public String getCantidadPerdidaStr() {
        return new Double(cantidadPerdida).toString();
    }

    public String getCantidadPerdidaSf1() {
        return sf1.format(getCantidadPerdida());
    }

    public void setCantidadTerminada(double cantidadTerminada) {
        this.cantidadTerminada = cantidadTerminada;
    }

    public double getCantidadTerminada() {
        return cantidadTerminada;
    }

    public String getCantidadTerminadaDf0() {
        return df0.format(getCantidadTerminada());
    }

    public String getCantidadTerminadaDf2() {
        return df2.format(getCantidadTerminada());
    }

    public int getCantidadTerminadaInt() {
        return (int) getCantidadTerminada();
    }

    public void setCantidadTerminada(String cantidadTerminadaStr) {
        this.cantidadTerminada = new Double(cantidadTerminadaStr).doubleValue();
    }

    public String getCantidadTerminadaStr() {
        return new Double(cantidadTerminada).toString();
    }

    public String getCantidadTerminadaSf1() {
        return sf1.format(getCantidadTerminada());
    }

    public String getCantidadPendienteFacturaSf1() {
        return sf1.format(getCantidadTerminada()
                - getCantidadFacturada());
    }

    public String getCantidadPendienteFacturaSf0() {
        return sf0.format(getCantidadTerminada()
                - getCantidadFacturada());
    }

    public void setPesoPerdido(double pesoPerdido) {
        this.pesoPerdido = pesoPerdido;
    }

    public double getPesoPerdido() {
        return pesoPerdido;
    }

    public String getPesoPerdidoDf0() {
        return df0.format(getPesoPerdido());
    }

    public String getPesoPerdidoDf1() {
        return df1.format(getPesoPerdido());
    }

    public String getPesoPerdidoDf2() {
        return df2.format(getPesoPerdido());
    }

    public int getPesoPerdidoInt() {
        return (int) getPesoPerdido();
    }

    public void setPesoPerdido(String pesoPerdidoStr) {
        this.pesoPerdido = new Double(pesoPerdidoStr).doubleValue();
    }

    public String getPesoPerdidoStr() {
        return new Double(pesoPerdido).toString();
    }

    public String getPesoPerdidoSf1() {
        return sf1.format(getPesoPerdido());
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaHoraInicioSqlServerJQuery() {

        return getFechaHoraInicio().substring(6, 10)
                + getFechaHoraInicio().substring(0, 2)
                + getFechaHoraInicio().substring(3, 5) + " "
                + getFechaHoraInicio().substring(11, 19);
    }

    public String getFechaHoraInicioJQuery() {

        return getFechaHoraInicio().substring(6, 10) + "/"
                + getFechaHoraInicio().substring(0, 2) + "/"
                + getFechaHoraInicio().substring(3, 5) + " "
                + getFechaHoraInicio().substring(11, 19);
    }

    public String getFechaHoraInicioSqlServer() {

        return getFechaInicioSqlServer() + " "
                + getFechaHoraInicio().substring(11, 19);
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicioSqlServer() {

        return getFechaInicio().substring(0, 4)
                + getFechaInicio().substring(5, 7)
                + getFechaInicio().substring(8, 10);

    }

    public String getFechaInicioCorta() {

        return getFechaInicio().substring(0, 4) + "/"
                + getFechaInicio().substring(5, 7) + "/"
                + getFechaInicio().substring(8, 10);

    }

    public String getechaInicioHHMM() {

        return getFechaInicio().substring(0, 16);

    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFinSqlServer() {

        return getFechaFin().substring(0, 4)
                + getFechaFin().substring(5, 7)
                + getFechaFin().substring(8, 10);

    }

    public String getFechaHoraFinSqlServerJQuery() {

        return getFechaHoraFin().substring(6, 10)
                + getFechaHoraFin().substring(0, 2)
                + getFechaHoraFin().substring(3, 5) + " "
                + getFechaHoraFin().substring(11, 19);
    }

    public String getFechaFinCorta() {

        return getFechaFin().substring(0, 4) + "/"
                + getFechaFin().substring(5, 7) + "/"
                + getFechaFin().substring(8, 10);

    }

    public String getFechaFinHHMM() {

        return getFechaFin().substring(0, 16);

    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr() {
        return new Integer(estado).toString();
    }

    public void setIdCausa(int idCausa) {
        this.idCausa = idCausa;
    }

    public int getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(String idCausaStr) {
        this.idCausa = new Integer(idCausaStr).intValue();
    }

    public String getIdCausaStr() {
        return new Integer(getIdCausa()).toString();
    }

    public void setPesoTerminado(double pesoTerminado) {
        this.pesoTerminado = pesoTerminado;
    }

    public double getPesoTerminado() {
        return pesoTerminado;
    }

    public String getPesoTerminadoDf0() {
        return df0.format(getPesoTerminado());
    }

    public String getPesoTerminadoDf1() {
        return df1.format(getPesoTerminado());
    }

    public String getPesoTerminadoDf2() {
        return df2.format(getPesoTerminado());
    }

    public int getPesoTerminadoInt() {
        return (int) getPesoTerminado();
    }

    public void setPesoTerminado(String pesoTerminadoStr) {
        this.pesoTerminado = new Double(pesoTerminadoStr).doubleValue();
    }

    public String getPesoTerminadoStr() {
        return new Double(pesoTerminado).toString();
    }

    public String getPesoTerminadoSf1() {
        return sf1.format(getPesoTerminado());
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    public int getItemPadre() {
        return itemPadre;
    }

    public void setItemPadre(String itemPadreStr) {
        this.itemPadre = new Integer(itemPadreStr).intValue();
    }

    public String getItemPadreStr() {
        return new Integer(itemPadre).toString();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaOrdenSqlServer() {

        return getFechaOrden().substring(0, 4)
                + getFechaOrden().substring(5, 7)
                + getFechaOrden().substring(8, 10);

    }

    public String getFechaOrdenCorta() {

        return getFechaOrden().substring(0, 4) + "/"
                + getFechaOrden().substring(5, 7) + "/"
                + getFechaOrden().substring(8, 10);

    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(String idLogStr) {
        this.idLog = new Integer(idLogStr).intValue();
    }

    public String getIdLogStr() {
        return new Integer(idLog).toString();
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(String idFichaStr) {
        this.idFicha = new Integer(idFichaStr).intValue();
    }

    public String getIdFichaStr() {
        return new Integer(idFicha).toString();
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaEntregaSqlServer() {

        return getFechaEntrega().substring(0, 4)
                + getFechaEntrega().substring(5, 7)
                + getFechaEntrega().substring(8, 10);

    }

    public String getFechaEntregaCorta() {

        return getFechaEntrega().substring(0, 4) + "/"
                + getFechaEntrega().substring(5, 7) + "/"
                + getFechaEntrega().substring(8, 10);

    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getCantidadDf0() {
        return df0.format(getCantidad());
    }

    public String getCantidadDf2() {
        return df2.format(getCantidad());
    }

    public int getCantidadInt() {
        return (int) getCantidad();
    }

    public void setCantidad(String cantidadStr) {
        this.cantidad = new Double(cantidadStr).doubleValue();
    }

    public String getCantidadStr() {
        return new Double(cantidad).toString();
    }

    public String getCantidadSf1() {
        return sf1.format(getCantidad());
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrdenStr) {
        this.numeroOrden = new Integer(numeroOrdenStr).intValue();
    }

    public String getNumeroOrdenStr() {
        return new Integer(numeroOrden).toString();
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getCantidadEntregadaFactura() {
        return getCantidadTerminada()
                - (getCantidadFacturada());
    }

    public void setCantidadEntregada(double cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public double getCantidadEntregada() {
        return cantidadEntregada;
    }

    public String getCantidadEntregadaDf0() {
        return df0.format(getCantidadEntregada());
    }

    public String getCantidadEntregadaDf2() {
        return df2.format(getCantidadEntregada());
    }

    public int getCantidadEntregadaInt() {
        return (int) getCantidadEntregada();
    }

    public void setCantidadEntregada(String cantidadEntregadaStr) {
        this.cantidadEntregada = new Double(cantidadEntregadaStr).doubleValue();
    }

    public String getCantidadEntregadaStr() {
        return new Double(cantidadEntregada).toString();
    }

    public String getCantidadEntregadaSf1() {
        return sf1.format(getCantidadEntregada());
    }

    public void setCantidadFacturada(double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public String getCantidadFacturadaDf0() {
        return df0.format(getCantidadFacturada());
    }

    public String getCantidadFacturadaDf2() {
        return df2.format(getCantidadFacturada());
    }

    public int getCantidadFacturadaInt() {
        return (int) getCantidadFacturada();
    }

    public void setCantidadFacturada(String cantidadFacturadaStr) {
        this.cantidadFacturada = new Double(cantidadFacturadaStr).doubleValue();
    }

    public String getCantidadFacturadaStr() {
        return new Double(cantidadFacturada).toString();
    }

    public String getCantidadFacturadaSf1() {
        return sf1.format(getCantidadFacturada());
    }

    public void setCantidadPedida(double cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public double getCantidadPedida() {
        return cantidadPedida;
    }

    public String getCantidadPedidaDf0() {
        return df0.format(getCantidadPedida());
    }

    public String getCantidadPedidaDf2() {
        return df2.format(getCantidadPedida());
    }

    public int getCantidadPedidaInt() {
        return (int) getCantidadPedida();
    }

    public void setCantidadPedida(String cantidadPedidaStr) {
        this.cantidadPedida = new Double(cantidadPedidaStr).doubleValue();
    }

    public String getCantidadPedidaStr() {
        return new Double(cantidadPedida).toString();
    }

    public String getCantidadPedidaSf1() {
        return sf1.format(getCantidadPedida());
    }

    public void setPesoPedido(double pesoPedido) {
        this.pesoPedido = pesoPedido;
    }

    public double getPesoPedido() {
        return pesoPedido;
    }

    public String getPesoPedidoDf0() {
        return df0.format(getPesoPedido());
    }

    public String getPesoPedidoDf2() {
        return df2.format(getPesoPedido());
    }

    public int getPesoPedidoInt() {
        return (int) getPesoPedido();
    }

    public void setPesoPedido(String pesoPedidoStr) {
        this.pesoPedido = new Double(pesoPedidoStr).doubleValue();
    }

    public String getPesoPedidoStr() {
        return new Double(pesoPedido).toString();
    }

    public String getPesoPedidoSf1() {
        return sf1.format(getPesoPedido());
    }

    public void setIdControl(int idControl) {
        this.idControl = idControl;
    }

    public int getIdControl() {
        return idControl;
    }

    public String getIdControlSf0() {
        return sf0.format(getIdControl());
    }

    public void setIdControl(String idControlStr) {
        this.idControl = new Integer(idControlStr).intValue();
    }

    public String getIdControlStr() {
        return new Integer(idControl).toString();
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(String idPluStr) {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr() {
        return new Integer(idPlu).toString();
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public void setIdControlTipo(int idControlTipo) {
        this.idControlTipo = idControlTipo;
    }

    public int getIdControlTipo() {
        return idControlTipo;
    }

    public void setIdControlTipo(String idControlTipoStr) {
        this.idControlTipo = new Integer(idControlTipoStr).intValue();
    }

    public String getIdControlTipoStr() {
        return new Integer(idControlTipo).toString();
    }

    public int getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(int idCopia) {
        this.idCopia = idCopia;
    }

    public String getNombreCopia() {
        return nombreCopia;
    }

    public void setNombreCopia(String nombreCopia) {
        this.nombreCopia = nombreCopia;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getObservacionMayuscula() {
        return getObservacion().toUpperCase();
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuarioStr) {
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuarioStr() {
        return new Double(idUsuario).toString();
    }

    public void setIdSigno(int idSigno) {
        this.idSigno = idSigno;
    }

    public int getIdSigno() {
        return idSigno;
    }

    public void setIdSigno(String idSignoStr) {
        this.idSigno = new Integer(idSignoStr).intValue();
    }

    public String getIdSignoStr() {
        return new Integer(idSigno).toString();
    }

    public void setPesoTerminadoSaldo(double pesoTerminadoSaldo) {
        this.pesoTerminadoSaldo = pesoTerminadoSaldo;
    }

    public double getPesoTerminadoSaldo() {
        return pesoTerminadoSaldo;
    }

    public String getPesoTerminadoSaldoDf0() {
        return df0.format(getPesoTerminadoSaldo());
    }

    public String getPesoTerminadoSaldoDf2() {
        return df2.format(getPesoTerminadoSaldo());
    }

    public int getPesoTerminadoSaldoInt() {
        return (int) getPesoTerminadoSaldo();
    }

    public void setPesoTerminadoSaldo(String pesoTerminadoSaldoStr) {
        this.pesoTerminadoSaldo = new Double(pesoTerminadoSaldoStr).doubleValue();
    }

    public String getPesoTerminadoSaldoStr() {
        return new Double(pesoTerminadoSaldo).toString();
    }

    public String getPesoTerminadoSaldoSf1() {
        return sf1.format(getPesoTerminadoSaldo());
    }

    public void setCantidadTerminadaSaldo(double cantidadTerminadaSaldo) {
        this.cantidadTerminadaSaldo = cantidadTerminadaSaldo;
    }

    public double getCantidadTerminadaSaldo() {
        return cantidadTerminadaSaldo;
    }

    public String getCantidadTerminadaSaldoDf0() {
        return df0.format(getCantidadTerminadaSaldo());
    }

    public String getCantidadTerminadaSaldoDf2() {
        return df2.format(getCantidadTerminadaSaldo());
    }

    public int getCantidadTerminadaSaldoInt() {
        return (int) getCantidadTerminadaSaldo();
    }

    public void setCantidadTerminadaSaldo(String cantidadTerminadaSaldoStr) {
        this.cantidadTerminadaSaldo = new Double(cantidadTerminadaSaldoStr).doubleValue();
    }

    public String getCantidadTerminadaSaldoStr() {
        return new Double(cantidadTerminadaSaldo).toString();
    }

    public String getCantidadTerminadaSaldoSf1() {
        return sf1.format(getCantidadTerminadaSaldo());
    }

    public String getNombreTerceroOperacion() {
        return nombreTerceroOperacion;
    }

    public void setNombreTerceroOperacion(String nombreTerceroOperacion) {
        this.nombreTerceroOperacion = nombreTerceroOperacion;
    }

    public void setCantidadSalida(double cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public double getCantidadSalida() {
        return cantidadSalida;
    }

    public String getCantidadSalidaDf0() {
        return df0.format(getCantidadSalida());
    }

    public String getCantidadSalidaDf2() {
        return df2.format(getCantidadSalida());
    }

    public int getCantidadSalidaInt() {
        return (int) getCantidadSalida();
    }

    public void setCantidadSalida(String cantidadSalidaStr) {
        this.cantidadSalida = new Double(cantidadSalidaStr).doubleValue();
    }

    public String getCantidadSalidaStr() {
        return new Double(cantidadSalida).toString();
    }

    public String getCantidadSalidaSf1() {
        return sf1.format(getCantidadSalida());
    }

    public void setCantidadEntrada(double cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public double getCantidadEntrada() {
        return cantidadEntrada;
    }

    public String getCantidadEntradaDf0() {
        return df0.format(getCantidadEntrada());
    }

    public String getCantidadEntradaDf2() {
        return df2.format(getCantidadEntrada());
    }

    public int getCantidadEntradaInt() {
        return (int) getCantidadEntrada();
    }

    public void setCantidadEntrada(String cantidadEntradaStr) {
        this.cantidadEntrada = new Double(cantidadEntradaStr).doubleValue();
    }

    public String getCantidadEntradaStr() {
        return new Double(cantidadEntrada).toString();
    }

    public String getCantidadEntradaSf1() {
        return sf1.format(getCantidadEntrada());
    }

    public void setCantidadPendiente(double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public String getCantidadPendienteDf0() {
        return df0.format(getCantidadPendiente());
    }

    public String getCantidadPendienteDf2() {
        return df2.format(getCantidadPendiente());
    }

    public int getCantidadPendienteInt() {
        return (int) getCantidadPendiente();
    }

    public void setCantidadPendiente(String cantidadPendienteStr) {
        this.cantidadPendiente = new Double(cantidadPendienteStr).doubleValue();
    }

    public String getCantidadPendienteStr() {
        return new Double(cantidadPendiente).toString();
    }

    public String getCantidadPendienteSf1() {
        return sf1.format(getCantidadPendiente());
    }

    public void setVrCostoBaseMAT(double vrCostoBaseMAT) {
        this.vrCostoBaseMAT = vrCostoBaseMAT;
    }

    public double getVrCostoBaseMAT() {
        return vrCostoBaseMAT;
    }

    public String getVrCostoBaseMATSf0() {
        return sf0.format(getVrCostoBaseMAT());
    }

    public void setVrCostoBaseMAT(String vrCostoBaseMATStr) {
        this.vrCostoBaseMAT = new Double(vrCostoBaseMATStr).doubleValue();
    }

    public String getVrCostoBaseMATStr() {
        return new Double(vrCostoBaseMAT).toString();
    }

    public void setVrCostoBaseCIF(double vrCostoBaseCIF) {
        this.vrCostoBaseCIF = vrCostoBaseCIF;
    }

    public double getVrCostoBaseCIF() {
        return vrCostoBaseCIF;
    }

    public String getVrCostoBaseCIFSf0() {
        return sf0.format(getVrCostoBaseCIF());
    }

    public void setVrCostoBaseCIF(String vrCostoBaseCIFStr) {
        this.vrCostoBaseCIF = new Double(vrCostoBaseCIFStr).doubleValue();
    }

    public String getVrCostoBaseCIFStr() {
        return new Double(vrCostoBaseCIF).toString();
    }

    public void setVrCostoBaseMOD(double vrCostoBaseMOD) {
        this.vrCostoBaseMOD = vrCostoBaseMOD;
    }

    public double getVrCostoBaseMOD() {
        return vrCostoBaseMOD;
    }

    public String getVrCostoBaseMODSf0() {
        return sf0.format(getVrCostoBaseMOD());
    }

    public void setVrCostoBaseMOD(String vrCostoBaseMODStr) {
        this.vrCostoBaseMOD = new Double(vrCostoBaseMODStr).doubleValue();
    }

    public String getVrCostoBaseMODStr() {
        return new Double(vrCostoBaseMOD).toString();
    }

    public void setPesoTara(double pesoTara) {
        this.pesoTara = pesoTara;
    }

    public double getPesoTara() {
        return pesoTara;
    }

    public String getPesoTaraDf0() {
        return df0.format(getPesoTara());
    }

    public String getPesoTaraDf1() {
        return df1.format(getPesoTara());
    }

    public String getPesoTaraDf2() {
        return df2.format(getPesoTara());
    }

    public int getPesoTaraInt() {
        return (int) getPesoTara();
    }

    public void setPesoTara(String pesoTaraStr) {
        this.pesoTara = new Double(pesoTaraStr).doubleValue();
    }

    public String getPesoTaraStr() {
        return new Double(pesoTara).toString();
    }

    public String getPesoTaraSf1() {
        return sf1.format(getPesoTara());
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public void setCantidadMinuto(double cantidadMinuto) {
        this.cantidadMinuto = cantidadMinuto;
    }

    public double getCantidadMinuto() {
        return cantidadMinuto;
    }

    public String getCantidadMinutoDf0() {
        return df0.format(getCantidadMinuto());
    }

    public String getCantidadMinutoDf2() {
        return df2.format(getCantidadMinuto());
    }

    public int getCantidadMinutoInt() {
        return (int) getCantidadMinuto();
    }

    public void setCantidadMinuto(String cantidadMinutoStr) {
        this.cantidadMinuto = new Double(cantidadMinutoStr).doubleValue();
    }

    public String getCantidadMinutoStr() {
        return new Double(cantidadMinuto).toString();
    }

    public String getCantidadMinutoSf1() {
        return sf1.format(getCantidadMinuto());
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public void setIdTipoCausa(int idTipoCausa) {
        this.idTipoCausa = idTipoCausa;
    }

    public int getIdTipoCausa() {
        return idTipoCausa;
    }

    public void setIdTipoCausa(String idTipoCausaStr) {
        this.idTipoCausa = new Integer(idTipoCausaStr).intValue();
    }

    public String getIdTipoCausaStr() {
        return new Integer(idTipoCausa).toString();
    }

    public String getNombreCausa() {
        return nombreCausa;
    }

    public void setNombreCausa(String nombreCausa) {

        if (nombreCausa == null) {

            this.nombreCausa = STRINGVACIO;
        } else {

            this.nombreCausa = nombreCausa;
        }
    }

    public String getItemEAN39() {

        return jhFormat.fill(getIdLocalStr(), '0', 3, 0)
                + jhFormat.fill(getIdTipoOrdenStr(), '0', 3, 0)
                + jhFormat.fill(getIdLogStr(), '0', 6, 0)
                + jhFormat.fill(getIdOperacionStr(), '0', 3, 0)
                + jhFormat.fill(getItemPadreStr(), '0', 3, 0)
                + jhFormat.fill(getItemStr(), '0', 3, 0);
    }

    public void setIdOrdenCruce(int idOrdenCruce) {
        this.idOrdenCruce = idOrdenCruce;
    }

    public int getIdOrdenCruce() {
        return idOrdenCruce;
    }

    public String getIdOrdenCruceSf0() {
        return sf0.format(getIdOrdenCruce());
    }

    public void setIdOrdenCruce(String idOrdenCruceStr) {
        this.idOrdenCruce = new Integer(idOrdenCruceStr).intValue();
    }

    public String getIdOrdenCruceStr() {
        return new Integer(idOrdenCruce).toString();
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public void setIdMaquina(String idMaquina) {
        this.IdMaquina = Integer.parseInt(idMaquina);
    }

    public double getTiempoPerdido() {
        return tiempoPerdido;
    }

    public void setTiempoPerdido(double tiempoPerdido) {
        this.tiempoPerdido = tiempoPerdido;
    }

    public int getIdCausaParo() {
        return idCausaParo;
    }

    public void setIdCausaParo(int idCausaParo) {
        this.idCausaParo = idCausaParo;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public String getOne() {

        StringBuilder sb = new StringBuilder();
        sb.append(getIdOrden());
        sb.append(",");
        sb.append(getItem());
        sb.append(",");
        sb.append(getNumeroOrden());
        sb.append(",");
        sb.append(getNombreTercero());
        sb.append(",");
        sb.append(getIdFicha());
        sb.append(",");
        sb.append(getReferenciaCliente());
        sb.append(",");
        sb.append(getFechaInicio());
        sb.append(",");
        sb.append(getFechaFin());
        sb.append(",");
        sb.append(getCantidadTerminada());
        sb.append(",");
        sb.append(getPesoTerminado());
        sb.append(",");
        sb.append(getPesoTara());
        sb.append(",");
        sb.append(getPesoPerdido());
        sb.append(",");
        sb.append((int) (getIdOperario()));
        sb.append(",");
        sb.append(getIdOperacion());
        sb.append(",");
        sb.append(getIdMaquina());
        sb.append(",");
        sb.append((getObservacion() == null ? "" : getObservacion()));
        sb.append(",");
        sb.append(getTiempoPerdido());
        sb.append(",");
        sb.append(getIdCausa());
        sb.append(",");
        sb.append(getIdTurno());
        sb.append(",");
        sb.append(getFechaProduccion());
        return sb.toString();
    }

}
