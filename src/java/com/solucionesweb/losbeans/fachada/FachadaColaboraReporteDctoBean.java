package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;
import com.solucionesweb.losbeans.utilidades.JhFormat;
import java.math.BigDecimal;

public class FachadaColaboraReporteDctoBean implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private int idOrden;
    private String fechaOrden;
    private int estado;
    private String idCliente;
    private double idUsuario;
    private String fechaInicial;
    private String fechaFinal;
    private int totalOrdenes;
    private double totalPesoTeorico;
    private double totalVrVentaConIva;
    private String nombreUsuario;
    private int idDcto;
    private int indicador;
    private String fechaDcto;
    private double vrBase;
    private double vrIva;
    private int numeroArticulo;
    private String nombreTipoOrden;
    private String idTipoOrdenCadena;
    private String idDctoNitCC;
    private double vrRteFuente;
    private double vrRteIva;
    private double vrRteIca;
    private double vrDescuento;
    private int idTipo;
    private int idBodega;
    private double idVendedor;
    private int idMarca;
    private String indicadorCadena;
    private String nombreTercero;
    private String fechaDctoNitCC;
    private int idLocalCruce;
    private int idTipoOrdenCruce;
    private int idDctoCruce;
    private double vrFacturaVenta;
    private String nombreTipoNegocio;
    private String aliasUsuario;
    private int indicadorInicial;
    private int indicadorFinal;
    private String fechaCorte;
    private double vrImpoconsumo;
    private double vrCostoIND;
    private double vrCostoMV;
    private String horaTx;
    private String prefijo;
    private int idCaja;
    private String fechaInicialStr;
    private String fechaFinalStr;
    private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    private int idPlu;
    private String nombrePlu;
    private String nombreMarca;
    private double vrPago;
    private double cantidad;
    private String nombreCiudad;
    private double porcentajeIva;
    private double vrVentaSinIva;
    private double vrVentaIva;
    private int idCiudad;
    private String nombreCategoria;
    private int idCategoria;
    private int idListaPrecio;
    private int idEstado;
    private int idTipoTercero;
    private int idOrdenOrigen;
    private String referenciaCliente;
    private int numeroOrden;
    private int item;
    private int dctoDian;
    private String urlDian;
    
    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat Sf0 = new DecimalFormat("############");
    DecimalFormat Sf2 = new DecimalFormat("############.00");
    DecimalFormat porcentaje = new DecimalFormat("%.00");

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

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaDctoNitCC(String fechaDctoNitCC) {
        this.fechaDctoNitCC = fechaDctoNitCC;
    }

    public String getFechaDctoNitCC() {
        return fechaDctoNitCC;
    }

    public String getFechaOrdenSqlServer() {

        return getFechaOrden().substring(0, 4)
                + getFechaOrden().substring(5, 7)
                + getFechaOrden().substring(8, 10);
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicialStr(String fechaInicialStr) {
        this.fechaInicialStr = fechaInicialStr;
    }

    public String getFechaInicialSqlServer() {
        return getFechaInicial().substring(0, 4)
                + getFechaInicial().substring(5, 7)
                + getFechaInicial().substring(8, 10);
    }

    public String getFechaInicialSqlServerStr() {
        return getFechaInicialStr().substring(0, 4)
                + getFechaInicialStr().substring(5, 7)
                + getFechaInicialStr().substring(8, 10);
    }

    public String getFechaInicialStrOracle() {
        return "TO_DATE('" + getFechaInicial() + "','YYYY/MM/DD HH24:MI:SS')";
    }

    public String getFechaInicialStr() {
        return fechaInicialStr;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getFechaFinalSqlServer() {
        return getFechaFinal().substring(0, 4)
                + getFechaFinal().substring(5, 7)
                + getFechaFinal().substring(8, 10);
    }

    public String getFechaFinalSqlServerStr() {
        return getFechaFinalStr().substring(0, 4)
                + getFechaFinalStr().substring(5, 7)
                + getFechaFinalStr().substring(8, 10);
    }

    public String getFechaFinalStrOracle() {
        return "TO_DATE('" + getFechaFinal() + "','YYYY/MM/DD HH24:MI:SS')";
    }

    public void setFechaFinalStr(String fechaFinalStr) {
        this.fechaFinalStr = fechaFinalStr;
    }

    public String getFechaFinalStr() {
        return fechaFinalStr;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuarioStr) {
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public String getIdUsuarioStr() {
        return new Double(idUsuario).toString();
    }

    public String getIdUsuarioDf0() {
        return df0.format(getIdUsuario());
    }

    public void setTotalOrdenes(int totalOrdenes) {
        this.totalOrdenes = totalOrdenes;
    }

    public int getTotalOrdenes() {
        return totalOrdenes;
    }

    public void setTotalOrdenes(String totalOrdenesStr) {
        this.totalOrdenes = new Integer(totalOrdenesStr).intValue();
    }

    public String getTotalOrdenesStr() {
        return new Integer(totalOrdenes).toString();
    }

    public String getTotalOrdenesDf0() {
        return df0.format(getTotalOrdenes());
    }

    public void setTotalPesoTeorico(double totalPesoTeorico) {
        this.totalPesoTeorico = totalPesoTeorico;
    }

    public double getTotalPesoTeorico() {
        return totalPesoTeorico;
    }

    public void setTotalPesoTeorico(String totalPesoTeoricoStr) {
        this.totalPesoTeorico = new Integer(totalPesoTeoricoStr).intValue();
    }

    public String getTotalPesoTeoricoStr() {
        return new Double(totalPesoTeorico).toString();
    }

    public String getTotalPesoTeoricoDf2() {
        return df2.format(getTotalPesoTeorico());
    }

    public void setTotalVrVentaConIva(double totalVrVentaConIva) {
        this.totalVrVentaConIva = totalVrVentaConIva;
    }

    public double getTotalVrVentaConIva() {
        return totalVrVentaConIva;
    }

    public void setTotalVrVentaConIva(String totalVrVentaConIvaStr) {
        this.totalVrVentaConIva = new Double(totalVrVentaConIva).doubleValue();
    }

    public String getTotalVrVentaConIvaStr() {
        return new Double(totalVrVentaConIva).toString();
    }

    public String getTotalVrVentaConIvaDf2() {
        return df2.format(getTotalVrVentaConIva());
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario.trim();
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

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(String idDctoStr) {
        this.idDcto = new Integer(idDctoStr).intValue();
    }

    public String getIdDctoStr() {
        return new Integer(idDcto).toString();
    }

    public String getIdDctoDf0() {
        return df0.format(getIdDcto());
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicadorStr) {
        this.indicador = new Integer(indicadorStr).intValue();
    }

    public String getIndicadorStr() {
        return new Integer(getIndicador()).toString();
    }

    public String getIndicadorDf0() {
        return new Integer(getIndicador()).toString();
    }

    public void setFechaDcto(String fechaDcto) {
        this.fechaDcto = fechaDcto;
    }

    public String getFechaDcto() {
        return fechaDcto;
    }

    public String getFechaDctoFormato() {
        return getFechaDcto().substring(0, 4) + "/"
                + getFechaDcto().substring(5, 7) + "/"
                + getFechaDcto().substring(8, 10);
    }

    public String getFechaDctoSqlServer() {

        return getFechaDcto().substring(0, 4)
                + getFechaDcto().substring(5, 7)
                + getFechaDcto().substring(8, 10);
    }

    public void setVrBase(double vrBase) {
        this.vrBase = vrBase;
    }

    public double getVrBase() {
        return vrBase;
    }

    public void setVrBase(String vrBaseStr) {
        this.vrBase = new Double(vrBaseStr).doubleValue();
    }

    public String getVrBaseStr() {
        return new Double(vrBase).toString();
    }

    public String getVrBaseDf0() {
        return df0.format(getVrBase());
    }

    public void setVrIva(double vrIva) {
        this.vrIva = vrIva;
    }

    public double getVrIva() {
        return vrIva;
    }

    public void setVrIva(String vrIvaStr) {
        this.vrIva = new Double(vrIvaStr).doubleValue();
    }

    public String getVrIvaStr() {
        return new Double(vrIva).toString();
    }

    public String getVrIvaDf0() {
        return df0.format(getVrIva());
    }

    public double getVrVentaConIva() {
        return (getVrBase()
                + getVrIva()
                + getVrImpoconsumo()
                - getVrRteFuente());
    }

    public String getVrVentaConIvaDf0() {
        return df0.format(getVrVentaConIva());
    }

    public void setNumeroArticulo(int numeroArticulo) {
        this.numeroArticulo = numeroArticulo;
    }

    public int getNumeroArticulo() {
        return numeroArticulo;
    }

    public void setNumeroArticulo(String numeroArticuloStr) {
        this.numeroArticulo = new Integer(numeroArticuloStr).intValue();
    }

    public String getNumeroArticuloStr() {
        return new Integer(numeroArticulo).toString();
    }

    public String getNumeroArticuloDf0() {
        return df0.format(getNumeroArticulo());
    }

    public void setNombreTipoOrden(String nombreTipoOrden) {
        this.nombreTipoOrden = nombreTipoOrden;
    }

    public String getNombreTipoOrden() {
        return nombreTipoOrden;
    }

    public String getNombreTipoOrdenMayuscula() {
        return getNombreTipoOrden().toUpperCase();
    }

    public void setIdTipoOrdenCadena(String idTipoOrdenCadena) {
        this.idTipoOrdenCadena = idTipoOrdenCadena;
    }

    public String getIdTipoOrdenCadena() {
        return idTipoOrdenCadena;
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public void setVrRteFuente(double vrRteFuente) {
        this.vrRteFuente = vrRteFuente;
    }

    public double getVrRteFuente() {
        return vrRteFuente;
    }

    public void setVrRteFuente(String vrRteFuenteStr) {
        this.vrRteFuente = new Double(vrRteFuenteStr).doubleValue();
    }

    public String getVrRteFuenteStr() {
        return new Double(vrRteFuente).toString();
    }

    public String getVrRteFuenteDf0() {
        return df0.format(getVrRteFuente());
    }

    public void setVrRteIva(double vrRteIva) {
        this.vrRteIva = vrRteIva;
    }

    public double getVrRteIva() {
        return vrRteIva;
    }

    public void setVrRteIva(String vrRteIvaStr) {
        this.vrRteIva = new Double(vrRteIvaStr).doubleValue();
    }

    public String getVrRteIvaStr() {
        return new Double(vrRteIva).toString();
    }

    public String getVrRteIvaDf0() {
        return df0.format(getVrRteIva());
    }

    public void setVrRteIca(double vrRteIca) {
        this.vrRteIca = vrRteIca;
    }

    public double getVrRteIca() {
        return vrRteIca;
    }

    public void setVrRteIca(String vrRteIcaStr) {
        this.vrRteIca = new Double(vrRteIcaStr).doubleValue();
    }

    public String getVrRteIcaStr() {
        return new Double(vrRteIca).toString();
    }

    public String getVrRteIcaDf0() {
        return df0.format(getVrRteIca());
    }

    public void setVrDescuento(double vrDescuento) {
        this.vrDescuento = vrDescuento;
    }

    public double getVrDescuento() {
        return vrDescuento;
    }

    public void setVrDescuento(String vrDescuentoStr) {
        this.vrDescuento = new Double(vrDescuentoStr).doubleValue();
    }

    public String getVrDescuentoStr() {
        return new Double(vrDescuento).toString();
    }

    public String getVrDescuentoDf0() {
        return df0.format(getVrDescuento());
    }

    public double getVrFactura() {
        return (getVrBase()
                + getVrIva()
                + getVrImpoconsumo()
                - getVrRteFuente()
                - getVrRteIva()
                - getVrRteIca());
    }

    public String getVrFacturaDf0() {
        return df0.format(getVrBase()
                + getVrIva()
                + getVrImpoconsumo()
                - getVrRteFuente()
                - getVrRteIva()
                - getVrRteIca());
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipoStr) {
        this.idTipo = new Integer(idTipoStr).intValue();
    }

    public String getIdTipoStr() {
        return new Integer(idTipo).toString();
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(String idBodegaStr) {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }

    public String getIdBodegaStr() {
        return new Integer(idBodega).toString();
    }

    public void setIdVendedor(double idVendedor) {
        this.idVendedor = idVendedor;
    }

    public double getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedorStr) {
        this.idVendedor = new Double(idVendedorStr).doubleValue();
    }

    public String getIdVendedorStr() {
        return new Double(idVendedor).toString();
    }

    public String getIdVendedorDf0() {
        return df0.format(getIdVendedor());
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarcaStr) {
        this.idMarca = new Integer(idMarcaStr).intValue();
    }

    public String getIdMarcaStr() {
        return new Integer(idMarca).toString();
    }

    public void setIndicadorCadena(String indicadorCadena) {
        this.indicadorCadena = indicadorCadena;
    }

    public String getIndicadorCadena() {
        return indicadorCadena;
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero.trim();
    }

    public void setIdLocalCruce(int idLocalCruce) {
        this.idLocalCruce = idLocalCruce;
    }

    public int getIdLocalCruce() {
        return idLocalCruce;
    }

    public void setIdLocalCruce(String idLocalCruceStr) {
        this.idLocalCruce = new Integer(idLocalCruceStr).intValue();
    }

    public String getIdLocalCruceStr() {
        return new Integer(idLocalCruce).toString();
    }

    public void setIdTipoOrdenCruce(int idTipoOrdenCruce) {
        this.idTipoOrdenCruce = idTipoOrdenCruce;
    }

    public int getIdTipoOrdenCruce() {
        return idTipoOrdenCruce;
    }

    public void setIdTipoOrdenCruce(String idTipoOrdenCruceStr) {
        this.idTipoOrdenCruce = new Integer(idTipoOrdenCruceStr).intValue();
    }

    public String getIdTipoOrdenCruceStr() {
        return new Integer(idTipoOrdenCruce).toString();
    }

    public void setIdDctoCruce(int idDctoCruce) {
        this.idDctoCruce = idDctoCruce;
    }

    public int getIdDctoCruce() {
        return idDctoCruce;
    }

    public void setIdDctoCruce(String idDctoCruceStr) {
        this.idDctoCruce = new Integer(idDctoCruceStr).intValue();
    }

    public String getIdDctoCruceStr() {
        return new Integer(idDctoCruce).toString();
    }

    public String getIdDctoCruceDf0() {
        return df0.format(getIdDctoCruce());
    }

    public void setVrFacturaVenta(double vrFacturaVenta) {
        this.vrFacturaVenta = vrFacturaVenta;
    }

    public double getVrFacturaVenta() {
        return vrFacturaVenta;
    }

    public void setVrFacturaVenta(String vrFacturaVentaStr) {
        this.vrFacturaVenta = new Double(vrFacturaVentaStr).doubleValue();
    }

    public String getVrFacturaVentaStr() {
        return new Double(vrFacturaVenta).toString();
    }

    public String getVrFacturaVentaDf0() {
        return df0.format(getVrFacturaVenta());
    }

    public String getNombreTipoNegocio() {
        return nombreTipoNegocio;
    }

    public void setNombreTipoNegocio(String nombreTipoNegocio) {
        this.nombreTipoNegocio = nombreTipoNegocio.trim();
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        //
        this.aliasUsuario = aliasUsuario;
        if (aliasUsuario != null) {
            this.aliasUsuario = aliasUsuario.trim();
        }


    }

    public void setIndicadorInicial(int indicadorInicial) {
        this.indicadorInicial = indicadorInicial;
    }

    public int getIndicadorInicial() {
        return indicadorInicial;
    }

    public void setIndicadorInicial(String indicadorInicialStr) {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
    }

    public String getIndicadorInicialStr() {
        return new Integer(indicadorInicial).toString();
    }

    public String getIndicadorInicialDf0() {
        return new Integer(getIndicadorInicial()).toString();
    }

    public void setIndicadorFinal(int indicadorFinal) {
        this.indicadorFinal = indicadorFinal;
    }

    public int getIndicadorFinal() {
        return indicadorFinal;
    }

    public void setIndicadorFinal(String indicadorFinalStr) {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public String getIndicadorFinalStr() {
        return new Integer(indicadorFinal).toString();
    }

    public String getIndicadorFinalDf0() {
        return new Integer(getIndicadorFinal()).toString();
    }

    public String getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getFechaCorteSqlServer() {

        return getFechaCorte().substring(0, 4)
                + getFechaCorte().substring(5, 7)
                + getFechaCorte().substring(8, 10);
    }

    public void setVrImpoconsumo(double vrImpoconsumo) {
        this.vrImpoconsumo = vrImpoconsumo;
    }

    public double getVrImpoconsumo() {
        return vrImpoconsumo;
    }

    public void setVrImpoconsumo(String vrImpoconsumoStr) {
        this.vrImpoconsumo = new Double(vrImpoconsumoStr).doubleValue();
    }

    public String getVrImpoconsumoStr() {
        return new Double(vrImpoconsumo).toString();
    }

    public String getVrImpoconsumoDf0() {
        return df0.format(getVrImpoconsumo());
    }

    public void setMargenIND(double vrImpoconsumo) {
        this.vrImpoconsumo = vrImpoconsumo;
    }

    public double getMargenIND() {
        return vrImpoconsumo;
    }

    public void setMargenIND(String vrImpoconsumoStr) {
        this.vrImpoconsumo = new Double(vrImpoconsumoStr).doubleValue();
    }

    public String getMargenINDStr() {
        return new Double(vrImpoconsumo).toString();
    }

    public String getMargenINDDf0() {
        return df0.format(getMargenIND());
    }

    public String getMargenINDPorcentajeDf2() {
        return df2.format(getMargenIND() * 100);
    }

    public void setVrCostoIND(double vrCostoIND) {
        this.vrCostoIND = vrCostoIND;
    }

    public double getVrCostoIND() {
        return vrCostoIND;
    }

    public void setVrCostoIND(String vrCostoINDStr) {
        this.vrCostoIND = new Double(vrCostoINDStr).doubleValue();
    }

    public String getVrCostoINDStr() {
        return new Double(vrCostoIND).toString();
    }

    public String getVrCostoINDDf0() {
        return df0.format(getVrCostoIND());
    }

    public void setVrCostoMV(double vrCostoMV) {
        this.vrCostoMV = vrCostoMV;
    }

    public double getVrCostoMV() {
        return vrCostoMV;
    }

    public void setVrCostoMV(String vrCostoMVStr) {
        this.vrCostoMV = new Double(vrCostoMVStr).doubleValue();
    }

    public String getVrCostoMVStr() {
        return new Double(vrCostoMV).toString();
    }

    public String getVrCostoMVDf0() {
        return df0.format(getVrCostoMV());
    }

    public String getHoraTx() {
        return horaTx;
    }

    public void setHoraTx(String horaTx) {
        this.horaTx = horaTx;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(String idCajaStr) {
        this.idCaja = new Integer(idCajaStr).intValue();
    }

    public String getIdCajaStr() {
        return new Integer(idCaja).toString();
    }

    public int getIdTipoOrdenINI() {
        return idTipoOrdenINI;
    }

    public String getIdTipoOrdenINIStr() {
        return new Integer(idTipoOrdenINI).toString();
    }

    public void setIdTipoOrdenINI(int idTipoOrdenINI) {
        this.idTipoOrdenINI = idTipoOrdenINI;
    }

    public void setIdTipoOrdenINI(String idTipoOrdenINI) {
        this.idTipoOrdenINI = new Integer(idTipoOrdenINI).intValue();
    }

    public int getIdTipoOrdenFIN() {
        return idTipoOrdenFIN;
    }

    public String getIdTipoOrdenFINStr() {
        return new Integer(idTipoOrdenFIN).toString();
    }

    public void setIdTipoOrdenFIN(int idTipoOrdenFIN) {
        this.idTipoOrdenFIN = idTipoOrdenFIN;
    }

    public void setIdTipoOrdenFIN(String idTipoOrdenFIN) {
        this.idTipoOrdenFIN = new Integer(idTipoOrdenFIN).intValue();
    }

    public int getIdPlu() {
        return idPlu;
    }

    public String getIdPluStr() {
        return new Integer(getIdPlu()).toString();
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public double getVrPago() {
        return vrPago;
    }

    public String getVrPagoStr() {
        return new Double(getVrPago()).toString();
    }

    public void setVrPago(double vrPago) {
        this.vrPago = vrPago;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getCantidadStr() {
        return new Double(getCantidad()).toString();
    }

    public String getCantidadDf0() {
        return df0.format(getCantidad());
    }

    public String getCantidadDf1() {
        return df1.format(getCantidad());
    }

    public String getCantidadDf2() {
        return df1.format(getCantidad());
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getVrBaseDecimal() {

        BigDecimal vrBaseDecimal =
                new BigDecimal(getVrBase());


        return (vrBaseDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public String getVrCostoMVDecimal() {

        BigDecimal vrCostoMV = new BigDecimal(getVrCostoMV());

        return vrCostoMV.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    public String getVrCostoINDDecimal() {

        BigDecimal vrCostoIND = new BigDecimal(getVrCostoIND());

        return vrCostoIND.setScale(0, BigDecimal.ROUND_HALF_UP).toString();

    }

    public String getVrIvaDecimal() {

        BigDecimal vrIva = new BigDecimal(getVrIva());

        return vrIva.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getMargenClientePorcentaje() {
        return porcentaje.format(getMargenIND());
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public String getPorcentajeIvaPorcentaje() {
        return porcentaje.format(getPorcentajeIva());
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public void setVrVentaSinIva(double vrVentaSinIva) {
        this.vrVentaSinIva = vrVentaSinIva;
    }

    public double getVrVentaSinIva() {
        return vrVentaSinIva;
    }

    public void setVrVentaSinIva(String vrVentaSinIvaStr) {
        this.vrVentaSinIva = new Double(vrVentaSinIvaStr).doubleValue();
    }

    public String getVrVentaSinIvaStr() {
        return new Double(vrVentaSinIva).toString();
    }

    public String getVrVentaSinIvaDf0() {
        return df0.format(getVrVentaSinIva());
    }

    public String getVrVentaSinIvaDecimal() {

        BigDecimal vrVentaSinIva = new BigDecimal(getVrVentaSinIva());

        return vrVentaSinIva.setScale(0, BigDecimal.ROUND_HALF_UP).toString();

    }

    public void setVvrVentaIva(double vrVentaIva) {
        this.vrVentaIva = vrVentaIva;
    }

    public double getVrVentaIva() {
        return vrVentaIva;
    }

    public void setVvrVentaIva(String vvrVentaIvaStr) {
        this.vrVentaIva = new Double(vvrVentaIvaStr).doubleValue();
    }

    public String getVvrVentaIvaStr() {
        return new Double(vrVentaIva).toString();
    }

    public String getVvrVentaIvaDf0() {
        return df0.format(getVrVentaIva());
    }

    public String getVvrVentaIvaDecimal() {

        BigDecimal vvrVentaIva = new BigDecimal(getVrVentaIva());

        return vvrVentaIva.setScale(0, BigDecimal.ROUND_HALF_UP).toString();

    }

    public void setVrVentaIva(double vrVentaIva) {
        this.vrVentaIva = vrVentaIva;
    }

    public void setVrVentaIva(String vrVentaIvaStr) {
        this.vrVentaIva = new Double(vrVentaIvaStr).doubleValue();
    }

    public String getVrVentaIvaStr() {
        return new Double(vrVentaIva).toString();
    }

    public String getVrVentaIvaDf0() {
        return df0.format(getVrVentaIva());
    }

    public String getVrVentaIvaDecimal() {

        BigDecimal vrVentaIva = new BigDecimal(getVrVentaIva());

        return vrVentaIva.setScale(0, BigDecimal.ROUND_HALF_UP).toString();

    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getIdCiudadStr() {
        return new Integer(idCiudad).toString();
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoriaStr) {
        this.idCategoria = new Integer(idCategoriaStr).intValue();
    }

    public String getIdCategoriaStr() {
        return new Integer(idCategoria).toString();
    }

    public void setIdListaPrecio(int idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }

    public int getIdListaPrecio() {
        return idListaPrecio;
    }

    public void setIdListaPrecio(String idListaPrecioStr) {
        this.idListaPrecio = new Integer(idListaPrecioStr).intValue();
    }

    public String getIdListaPrecioStr() {
        return new Integer(idListaPrecio).toString();
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstadoStr) {
        this.idEstado = new Integer(idEstadoStr).intValue();
    }

    public String getIdEstadoStr() {
        return new Integer(idEstado).toString();
    }

    public void setIdTipoTercero(int idTipoTercero) {
        this.idTipoTercero = idTipoTercero;
    }

    public int getIdTipoTercero() {
        return idTipoTercero;
    }

    public void setIdTipoTercero(String idTipoTerceroStr) {
        this.idTipoTercero = new Integer(idTipoTerceroStr).intValue();
    }

    public String getIdTipoTerceroStr() {
        return new Integer(idTipoTercero).toString();
    }

    public void setIdOrdenOrigen(int idOrdenOrigen) {
        this.idOrdenOrigen = idOrdenOrigen;
    }

    public int getIdOrdenOrigen() {
        return idOrdenOrigen;
    }

    public void setIdOrdenOrigen(String idOrdenOrigenStr) {
        this.idOrdenOrigen = new Integer(idOrdenOrigenStr).intValue();
    }

    public String getIdOrdenOrigenStr() {
        return new Integer(idOrdenOrigen).toString();
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrdenStr) {
        this.setNumeroOrden(new Integer(numeroOrdenStr).intValue());
    }

    public String getNumeroOrdenStr() {
        return new Integer(getNumeroOrden()).toString();
    }

    public String getNumeroOrdenDf0() {
        return df0.format(getNumeroOrden());
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
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

    public int getDctoDian() {
        return dctoDian;
    }

    public void setDctoDian(int dctoDian) {
        this.dctoDian = dctoDian;
    }
    
     public String getDctoDianStr() {
        return new Integer(dctoDian).toString();
    }

    public String getUrlDian() {
        return urlDian;
    }

    public void setUrlDian(String urlDian) {
        this.urlDian = urlDian;
    }
    
    
}
