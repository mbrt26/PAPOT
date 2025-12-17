package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.text.DecimalFormat;

public class FachadaPagoBean  implements IConstantes {

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat di0 = new DecimalFormat("##############");
    DecimalFormat di2 = new DecimalFormat("#########.00");

    //Propiedad Pago
    private int idLocal;
    private int idTipoOrden;
    private int idOrden;    
    private int idRecibo;
    private int indicador;
    private String fechaPago;
    private String nitCC;
    private int estado;
    private double idUsuario;
    private double vrPago;
    private double vrRteFuente;
    private double vrDescuento;
    private double vrRteIva;
    private int idPeriodo;
    private double idDcto;
    private String idDctoNitCC;
    private String strIdLocal;
    private String indicadorSesion;
    private String fechaInicialStr;
    private String fechaFinalStr;
    private String horaTx;

    //
    private String fechaDcto;
    private double vrTotal;
    private double vrSaldo;

    //Propiedad Ayuda Cliente
    private String nombreCliente;
    private String fechaVencimiento;
    private int diasMora;
    private int idPlanilla;
    private int numeroDcto;
    private String fechaInicial;
    private String fechaFinal;    
    private double vrTotalPago;        
    private double vrTotalDescuento;            
    private double vrTotalRteFuente;                
    private double vrTotalRteIva;
    private int idLog;
    private double vrDiferencia;
    private double idVendedor;
    private double vrRteIca;
    private int idReciboCruce;
    private double vrPagoCambio;
    private String nombreVendedor;
    private String nombreMedio;
    private double porcentajeComision;
    private double porcentajeSancion;
    private int diasPlazo;
    private int diasExcluidos;
    private int diasPago;
    private int indicadorInicial;
    private int indicadorFinal;
    private int idTipoOrdenInicial;
    private int idTipoOrdenFinal;
    private int idLucro;
    private double vrComision;
    private double porcentajeComisionEfectiva;
    private String aliasUsuario;
    private String observacion;
    private double vrRteCree;
    private String idDctoDian;

    //
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
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public void setIdTipoOrden( int idTipoOrden )
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public void setIdTipoOrden( String idTipoOrdenStr )
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdRecibo( int idRecibo )
    {
        this.idRecibo = idRecibo ;
    }

    public int getIdRecibo()
    {
        return idRecibo;
    }

    public void setIdRecibo( String idReciboStr )
    {
        this.idRecibo = new Integer(idReciboStr).intValue();
    }

    public String getIdReciboStr()
    {
        return new Integer(idRecibo).toString();
    }

    public void setIndicador( int indicador )
    {
        this.indicador = indicador ;
    }

    public int getIndicador()
    {
        return indicador;
    }

    public void setIndicador( String indicadorStr )
    {
        this.indicador = new Integer(indicadorStr).intValue();
    }

    public String getIndicadorStr()
    {
        return new Integer(indicador).toString();
    }

    public void setFechaPago( String fechaPago )
    {
        this.fechaPago = fechaPago ;
    }

    public String getFechaPago()
    {
        return fechaPago;
    }

    public String getFechaPagoCorta() {

            return getFechaPago().substring(0, 4) + "/" +
                   getFechaPago().substring(5, 7) + "/" +
                   getFechaPago().substring(8, 10);
    }

    public String getFechaPagoSqlServer() {

            return getFechaPago().substring(0, 4) +
                   getFechaPago().substring(5, 7) +
                   getFechaPago().substring(8, 10);
    }

    public void setStrIdLocal( String strIdLocal )
    {
        this.strIdLocal = strIdLocal;
    }

    public String getStrIdLocal()
    {
        return strIdLocal.trim();
    }

    public void setIndicadorSesion( String indicadorSesion )
    {
        this.indicadorSesion = indicadorSesion ;
    }

    public String getIndicadorSesion()
    {
        return indicadorSesion;
    }

    public void setFechaInicialStr( String fechaInicialStr )
    {
        this.fechaInicialStr = fechaInicialStr;
    }

    public String getFechaInicialStr()
    {
        return fechaInicialStr;
    }

    public void setFechaFinalStr( String fechaFinalStr )
    {
        this.fechaFinalStr = fechaFinalStr;
    }

    public String getFechaFinalStr()
    {
        return fechaFinalStr;
    }

    public void setVrPago( double vrPago )
    {
        this.vrPago = vrPago ;
    }

    public double getVrPago()
    {
        return vrPago;
    }

    public String getVrPagoStrFormatDf2Str()  {
        return df2.format(getVrPago());
    }

    public String getVrPagoStrFormatDi2Str()  {
        return di2.format(getVrPago());
    }

    public void setVrPago( String vrPagoStr )
    {
        this.vrPago = new Double(vrPagoStr).doubleValue();
    }

    public String getVrPagoStr()
    {
        return new Double(vrPago).toString();
    }

    public String getVrPagoDf0()  {
        return df0.format(getVrPago());
    }

    public String getVrPagoStrSF()
    {
        return di0.format(getVrPago());
    }

    public void setNitCC( String nitCC )
    {
        this.nitCC = nitCC ;
    }

    public String getNitCC()
    {
        return nitCC;
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

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
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public void setVrRteFuente( double vrRteFuente )
    {
        this.vrRteFuente = vrRteFuente ;
    }

    public double getVrRteFuente()
    {
        return vrRteFuente;
    }

    public void setVrRteFuente( String vrRteFuenteStr )
    {
        this.vrRteFuente = new Double(vrRteFuenteStr).doubleValue();
    }

    public String getVrRteFuenteStr()
    {
        return new Double(vrRteFuente).toString();
    }

    public String getVrRteFuenteStrSF()
    {
        return di0.format(getVrRteFuente());
    }

    public String getVrRteFuenteStrFormatDf2Str()  {
        return df2.format(getVrRteFuente());
    }

    public String getVrRteFuenteDf0()  {
        return df0.format(getVrRteFuente());
    }

    public String getVrRteFuenteStrFormatDi2Str()  {
        return di2.format(getVrRteFuente());
    }

    public void setVrDescuento( double vrDescuento )
    {
        this.vrDescuento = vrDescuento ;
    }

    public double getVrDescuento()
    {
        return vrDescuento;
    }

    public String getVrDescuentoStrSF()
    {
        return di0.format(getVrDescuento());
    }

    public String getVrDescuentoDf0()  {
        return df0.format(getVrDescuento());
    }

    public String getVrDescuentoStrFormatDf2Str()  {
        return df2.format(getVrDescuento());
    }

    public String getVrDescuentoStrFormatDi2Str()  {
        return di2.format(getVrDescuento());
    }

    public void setVrDescuento( String vrDescuentoStr )
    {
        this.vrDescuento = new Double(vrDescuentoStr).doubleValue();
    }

    public String getVrDescuentoStr()
    {
        return new Double(vrDescuento).toString();
    }

    public void setVrRteIva( double vrRteIva )
    {
        this.vrRteIva = vrRteIva ;
    }

    public double getVrRteIva()
    {
        return vrRteIva;
    }

    public String getVrRteIvaStrSF()
    {
        return di0.format(getVrRteIva());
    }

    public void setVrRteIva( String vrRteIvaStr )
    {
        this.vrRteIva = new Double(vrRteIvaStr).doubleValue();
    }

    public String getVrRteIvaStr()
    {
        return new Double(vrRteIva).toString();
    }

    public String getVrRteIvaStrFormatDf2Str()  {
        return df2.format(getVrRteIva());
    }

    public String getVrRteIvaDf0()  {
        return df0.format(getVrRteIva());
    }

    public String getVrRteIvaStrFormatDi2Str()  {
        return di2.format(getVrRteIva());
    }

    public void setIdPeriodo( int idPeriodo )
    {
        this.idPeriodo = idPeriodo ;
    }

    public int getIdPeriodo()
    {
        return idPeriodo;
    }

    public void setIdPeriodo( String idPeriodoStr )
    {
        this.idPeriodo = new Integer(idPeriodoStr).intValue();
    }

    public String getIdPeriodoStr()
    {
        return new Integer(idPeriodo).toString();
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
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public String getIdOrdenStr()
    {
        return new Integer(idOrden).toString();
    }


    public void setIdDcto( double idDcto )
    {
        this.idDcto = idDcto ;
    }

    public double getIdDcto()
    {
        return idDcto;
    }

    public void setIdDcto( String idDctoStr )
    {
        this.idDcto = new Double(idDctoStr).doubleValue();
    }

    public String getIdDctoStr()
    {
        return new Double(idDcto).toString();
    }

    public void setIdDctoNitCC( String idDctoNitCC )
    {
        this.idDctoNitCC = idDctoNitCC;
    }

    public String getIdDctoNitCC()
    {
        return idDctoNitCC;
    }

    public void setNombreCliente( String nombreCliente )
    {
         this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setFechaDcto( String fechaDcto )
    {
        this.fechaDcto = fechaDcto;
    }

    public String getFechaDcto()
    {
        return fechaDcto;
    }

    public void setVrTotal( double vrTotal )
    {
        this.vrTotal = vrTotal ;
    }

    public double getVrTotal()
    {
        return vrTotal;
    }

    public void setVrTotal( String vrTotalStr )
    {
        this.vrTotal = new Double(vrTotalStr).doubleValue();
    }

    public String getVrTotalStr()
    {
        return new Double(vrTotal).toString();
    }

    public String getVrTotalStrFormatDi2Str()  {
        return di2.format(getVrTotal());
    }

    public String getVrTotalStrFormatDf2Str()  {
        return df2.format(getVrTotal());
    }

    public void setVrSaldo( double vrSaldo )
    {
        this.vrSaldo = vrSaldo ;
    }

    public double getVrSaldo()
    {
        return vrSaldo;
    }

    public void setVrSaldo( String vrSaldoStr )
    {
        this.vrSaldo = new Double(vrSaldoStr).doubleValue();
    }

    public String getVrSaldoStr()
    {
        return new Double(vrSaldo).toString();
    }

    public String getVrSaldoStrFormatDf2Str()  {
        return df2.format(getVrSaldo());
    }

    public String getVrSaldoStrFormatDi2Str()  {
        return di2.format(getVrSaldo());
    }

    public String getVrSaldoStrFormatDf0()  {
        return df0.format(getVrSaldo());
    }

    public String getVrSaldoStrDf0()  {
        return df0.format(getVrSaldo());
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(int diasMora) {
        this.diasMora = diasMora;
    }

    public void setDiasMora(String diasMoraStr) {
        this.diasMora = new Integer(diasMoraStr).intValue();
    }

    public String getDiasMoraStr()
    {
        return new Integer(getDiasMora()).toString();
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public String getIdPlanillaStr() {
        
        return new Integer(getIdPlanilla()).toString();
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public void setIdPlanilla(String idPlanillaStr) {
        this.idPlanilla = new Integer(idPlanillaStr).intValue();
    }

    public int getNumeroDcto() {
        return numeroDcto;
    }

    public String getNumeroDctoStr() {
        return new Integer(getNumeroDcto()).toString();
    }

    public void setNumeroDcto(int numeroDcto) {
        this.numeroDcto = numeroDcto;
    }

    public void setNumeroDcto(String numeroDctoStr) {
        this.numeroDcto = new Integer(numeroDctoStr).intValue();
    }

    public FachadaPagoBean() { }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaInicialSqlServer() {

            return getFechaInicial().substring(0, 4) +
                   getFechaInicial().substring(5, 7) +
                   getFechaInicial().substring(8, 10);
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getFechaFinalSqlServer() {

            return getFechaFinal().substring(0, 4) +
                   getFechaFinal().substring(5, 7) +  
                   getFechaFinal().substring(8, 10);
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setVrTotalPago( double vrTotalPago )
    {
        this.vrTotalPago = vrTotalPago ;
    }

    public double getVrTotalPago()
    {
        return vrTotalPago;
    }

    public String getVrTotalPagoStrFormatDf2Str()  {
        return df2.format(getVrTotalPago());
    }

    public String getVrTotalPagoStrFormatDi2Str()  {
        return di2.format(getVrTotalPago());
    }

    public void setVrTotalPago( String vrTotalPagoStr )
    {
        this.vrTotalPago = new Double(vrTotalPagoStr).doubleValue();
    }

    public String getVrTotalPagoStr()
    {
        return new Double(vrTotalPago).toString();
    }

    public String getVrTotalPagoDf0()  {
        return df0.format(getVrTotalPago());
    }

    public String getVrTotalPagoStrSF()
    {
        return di0.format(getVrTotalPago());
    }

    public void setVrTotalDescuento( double vrTotalDescuento )
    {
        this.vrTotalDescuento = vrTotalDescuento ;
    }

    public double getVrTotalDescuento()
    {
        return vrTotalDescuento;
    }

    public String getVrTotalDescuentoStrFormatDf2Str()  {
        return df2.format(getVrTotalDescuento());
    }

    public String getVrTotalDescuentoStrFormatDi2Str()  {
        return di2.format(getVrTotalDescuento());
    }

    public void setVrTotalDescuento( String vrTotalDescuentoStr )
    {
        this.vrTotalDescuento = new Double(vrTotalDescuentoStr).doubleValue();
    }

    public String getVrTotalDescuentoStr()
    {
        return new Double(vrTotalDescuento).toString();
    }

    public String getVrTotalDescuentoDf0()  {
        return df0.format(getVrTotalDescuento());
    }

    public String getVrTotalDescuentoStrSF()
    {
        return di0.format(getVrTotalDescuento());
    }

    public void setVrTotalRteFuente( double vrTotalRteFuente )
    {
        this.vrTotalRteFuente = vrTotalRteFuente ;
    }

    public double getVrTotalRteFuente()
    {
        return vrTotalRteFuente;
    }

    public String getVrTotalRteFuenteStrFormatDf2Str()  {
        return df2.format(getVrTotalRteFuente());
    }

    public String getVrTotalRteFuenteStrFormatDi2Str()  {
        return di2.format(getVrTotalRteFuente());
    }

    public void setVrTotalRteFuente( String vrTotalRteFuenteStr )
    {
        this.vrTotalRteFuente = new Double(vrTotalRteFuenteStr).doubleValue();
    }

    public String getVrTotalRteFuenteStr()
    {
        return new Double(vrTotalRteFuente).toString();
    }

    public String getVrTotalRteFuenteDf0()  {
        return df0.format(getVrTotalRteFuente());
    }

    public String getVrTotalRteFuenteStrSF()
    {
        return di0.format(getVrTotalRteFuente());
    }

    public void setVrTotalRteIva( double vrTotalRteIva )
    {
        this.vrTotalRteIva = vrTotalRteIva ;
    }

    public double getVrTotalRteIva()
    {
        return vrTotalRteIva;
    }

    public String getVrTotalRteIvaStrFormatDf2Str()  {
        return df2.format(getVrTotalRteIva());
    }

    public String getVrTotalRteIvaStrFormatDi2Str()  {
        return di2.format(getVrTotalRteIva());
    }

    public void setVrTotalRteIva( String vrTotalRteIvaStr )
    {
        this.vrTotalRteIva = new Double(vrTotalRteIvaStr).doubleValue();
    }

    public String getVrTotalRteIvaStr()
    {
        return new Double(vrTotalRteIva).toString();
    }

    public String getVrTotalRteIvaDf0()  {
        return df0.format(getVrTotalRteIva());
    }

    public String getVrTotalRteIvaStrSF()
    {
        return di0.format(getVrTotalRteIva());
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
        this.idLog = new Integer(idLogStr).intValue();
    }

    public String getIdLogStr()
    {
        return new Integer(idLog).toString();
    }

    public void setVrDiferencia( double vrDiferencia )
    {
        this.vrDiferencia = vrDiferencia ;
    }

    public double getVrDiferencia()
    {
        return vrDiferencia;
    }

    public void setVrDiferencia( String vrDiferenciaStr )
    {
        this.vrDiferencia = new Double(vrDiferenciaStr).doubleValue();
    }

    public String getVrDiferenciaStr()
    {
        return new Double(vrDiferencia).toString();
    }

    public String getVrDiferenciaStrFormatDi2Str()  {
        return di2.format(getVrDiferencia());
    }

    public String getVrDiferenciaDf0()  {
        return df0.format(getVrDiferencia());
    }

    public String getVrDiferenciaStrFormatDf2Str()  {
        return df2.format(getVrDiferencia());
    }

    public void setIdVendedor( double idVendedor )
    {
        this.idVendedor = idVendedor ;
    }

    public double getIdVendedor()
    {
        return idVendedor;
    }

    public void setIdVendedor( String idVendedorStr )
    {
        this.idVendedor = new Double(idVendedorStr).doubleValue();
    }

    public String getIdVendedorStr()
    {
        return new Double(idVendedor).toString();
    }

    public String getIdVendedorDi0()
    {
        return di0.format(getIdVendedor());
    }

    public void setVrRteIca( double vrRteIca )
    {
        this.vrRteIca = vrRteIca ;
    }

    public double getVrRteIca()
    {
        return vrRteIca;
    }

    public String getVrRteIcaDf0()
    {
        return df0.format(getVrRteIca());
    }

    public void setVrRteIca( String vrRteIcaStr )
    {
        this.vrRteIca = new Double(vrRteIcaStr).doubleValue();
    }

    public String getVrRteIcaStr()
    {
        return new Double(vrRteIca).toString();
    }

    public void setIdReciboCruce( int idReciboCruce )
    {
        this.idReciboCruce = idReciboCruce ;
    }

    public int getIdReciboCruce()
    {
        return idReciboCruce;
    }

    public void setIdReciboCruce( String idReciboCruceStr )
    {
        this.idReciboCruce = new Integer(idReciboCruceStr).intValue();
    }

    public String getIdReciboCruceStr()
    {
        return new Integer(idReciboCruce).toString();
    }

    public void setVrPagoCambio( double vrPagoCambio )
    {
        this.vrPagoCambio = vrPagoCambio ;
    }

    public double getVrPagoCambio()
    {
        return vrPagoCambio;
    }

    public String getVrPagoCambioStrFormatDf2Str()  {
        return df2.format(getVrPagoCambio());
    }

    public String getVrPagoCambioStrFormatDi2Str()  {
        return di2.format(getVrPagoCambio());
    }

    public void setVrPagoCambio( String vrPagoCambioStr )
    {
        this.vrPagoCambio = new Double(vrPagoCambioStr).doubleValue();
    }

    public String getVrPagoCambioStr()
    {
        return new Double(vrPagoCambio).toString();
    }

    public String getVrPagoCambioDf0()  {
        return df0.format(getVrPagoCambio());
    }

    public String getVrPagoCambioStrSF()
    {
        return di0.format(getVrPagoCambio());
    }

    public void setNombreVendedor( String nombreVendedor )
    {
         this.nombreVendedor = nombreVendedor;
    }

    public String getNombreVendedor()
    {
        return nombreVendedor;
    }

    public String getNombreMedio() {
        return nombreMedio;
    }

    public void setNombreMedio(String nombreMedio) {
        this.nombreMedio = nombreMedio;
    }

    public void setDiasPlazo( int diasPlazo )
    {
        this.diasPlazo = diasPlazo ;
    }

    public int getDiasPlazo()
    {
        return diasPlazo;
    }

    public void setDiasPlazo( String diasPlazoStr )
    {
        this.diasPlazo = new Integer(diasPlazoStr).intValue();
    }

    public String getDiasPlazoStr()
    {
        return new Integer(diasPlazo).toString();
    }

    public void setDiasExcluidos( int diasExcluidos )
    {
        this.diasExcluidos = diasExcluidos ;
    }

    public int getDiasExcluidos()
    {
        return diasExcluidos;
    }

    public void setDiasExcluidos( String diasExcluidosStr )
    {
        this.diasExcluidos = new Integer(diasExcluidosStr).intValue();
    }

    public String getDiasExcluidosStr()
    {
        return new Integer(diasExcluidos).toString();
    }

    public void setPorcentajeComision( double porcentajeComision )
    {
        this.porcentajeComision = porcentajeComision ;
    }

    public double getPorcentajeComision()
    {
        return porcentajeComision;
    }

    public String getPorcentajeComisionDf0()
    {
        return df0.format(getPorcentajeComision());
    }

    public void setPorcentajeComision( String porcentajeComisionStr )
    {
        this.porcentajeComision = new Double(porcentajeComisionStr).doubleValue();
    }

    public String getPorcentajeComisionStr()
    {
        return new Double(porcentajeComision).toString();
    }

    public void setPorcentajeSancion( double porcentajeSancion )
    {
        this.porcentajeSancion = porcentajeSancion ;
    }

    public double getPorcentajeSancion()
    {
        return porcentajeSancion;
    }

    public String getPorcentajeSancionDf0()
    {
        return df0.format(getPorcentajeSancion());
    }

    public void setPorcentajeSancion( String porcentajeSancionStr )
    {
        this.porcentajeSancion = new Double(porcentajeSancionStr).doubleValue();
    }

    public String getPorcentajeSancionStr()
    {
        return new Double(porcentajeSancion).toString();
    }

    public void setDiasPago( int diasPago )
    {
        this.diasPago = diasPago ;
    }

    public int getDiasPago()
    {
        return diasPago;
    }

    public void setDiasPago( String diasPagoStr )
    {
        this.diasPago = new Integer(diasPagoStr).intValue();
    }

    public String getDiasPagoStr()
    {
        return new Integer(diasPago).toString();
    }

    public void setIndicadorInicial( int indicadorInicial )
    {
        this.indicadorInicial = indicadorInicial ;
    }

    public int getIndicadorInicial()
    {
        return indicadorInicial;
    }

    public void setIndicadorInicial( String indicadorInicialStr )
    {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
    }

    public String getIndicadorInicialStr()
    {
        return new Integer(indicadorInicial).toString();
    }

    public void setIndicadorFinal( int indicadorFinal )
    {
        this.indicadorFinal = indicadorFinal ;
    }

    public int getIndicadorFinal()
    {
        return indicadorFinal;
    }

    public void setIndicadorFinal( String indicadorFinalStr )
    {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public String getIndicadorFinalStr()
    {
        return new Integer(indicadorFinal).toString();
    }

    public void setIdTipoOrdenInicial( int idTipoOrdenInicial )
    {
        this.idTipoOrdenInicial = idTipoOrdenInicial ;
    }

    public int getIdTipoOrdenInicial()
    {
        return idTipoOrdenInicial;
    }

    public void setIdTipoOrdenInicial( String idTipoOrdenInicialStr )
    {
        this.idTipoOrdenInicial = new Integer(idTipoOrdenInicialStr).intValue();
    }

    public String getIdTipoOrdenInicialStr()
    {
        return new Integer(idTipoOrdenInicial).toString();
    }

    public void setIdTipoOrdenFinal( int idTipoOrdenFinal )
    {
        this.idTipoOrdenFinal = idTipoOrdenFinal ;
    }

    public int getIdTipoOrdenFinal()
    {
        return idTipoOrdenFinal;
    }

    public void setIdTipoOrdenFinal( String idTipoOrdenFinalStr )
    {
        this.idTipoOrdenFinal = new Integer(idTipoOrdenFinalStr).intValue();
    }

    public String getIdTipoOrdenFinalStr()
    {
        return new Integer(idTipoOrdenFinal).toString();
    }

    public void setIdLucro( int idLucro )
    {
        this.idLucro = idLucro ;
    }

    public int getIdLucro()
    {
        return idLucro;
    }

    public void setIdLucro( String idLucroStr )
    {
        this.idLucro = new Integer(idLucroStr).intValue();
    }

    public String getIdLucroStr()
    {
        return new Integer(idLucro).toString();
    }

    public void setVrComision( double vrComision )
    {
        this.vrComision = vrComision ;
    }

    public double getVrComision()
    {
        return vrComision;
    }

    public String getVrComisionStrFormatDf2Str()  {
        return df2.format(getVrComision());
    }

    public String getVrComisionStrFormatDi2Str()  {
        return di2.format(getVrComision());
    }

    public void setVrComision( String vrComisionStr )
    {
        this.vrComision = new Double(vrComisionStr).doubleValue();
    }

    public String getVrComisionStr()
    {
        return new Double(vrComision).toString();
    }

    public String getVrComisionDf0()  {
        return df0.format(getVrComision());
    }

    public String getVrComisionStrSF()
    {
        return di0.format(getVrComision());
    }

    public void setPorcentajeComisionEfectiva( double porcentajeComisionEfectiva )
    {
        this.porcentajeComisionEfectiva = porcentajeComisionEfectiva ;
    }

    public double getPorcentajeComisionEfectiva()
    {
        return porcentajeComisionEfectiva;
    }

    public String getPorcentajeComisionEfectivaStrFormatDf2Str()  {
        return df2.format(getPorcentajeComisionEfectiva());
    }

    public String getPorcentajeComisionEfectivaStrFormatDi2Str()  {
        return di2.format(getPorcentajeComisionEfectiva());
    }

    public void setPorcentajeComisionEfectiva( String porcentajeComisionEfectivaStr )
    {
        this.porcentajeComisionEfectiva = new Double(porcentajeComisionEfectivaStr).doubleValue();
    }

    public String getPorcentajeComisionEfectivaStr()
    {
        return new Double(porcentajeComisionEfectiva).toString();
    }

    public String getPorcentajeComisionEfectivaDf0()  {
        return df0.format(getPorcentajeComisionEfectiva());
    }

    public String getPorcentajeComisionEfectivaStrSF()
    {
        return di0.format(getPorcentajeComisionEfectiva());
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

   

    public String getHoraTx() {
        return horaTx;
    }

    public void setHoraTx(String horaTx) {
        this.horaTx = horaTx;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getObservacionMayuscula() {
        return getObservacion().toUpperCase();
    }

    public void setObservacion(String observacion) {
        
        //
        this.observacion = observacion;

        //
        if ( observacion == null)  {

            //
            this.observacion = STRINGVACIO;

        }
    }
    
    public void setVrRteCree(double vrRteCree) {
        this.vrRteCree = vrRteCree;
    }

    public double getVrRteCree() {
        return vrRteCree;
    }

    public String getVrRteCreeDf0() {
        return df0.format(getVrRteCree());
    }

    public void setVrRteCree(String vrRteCreeStr) {
        this.vrRteCree = new Double(vrRteCreeStr).doubleValue();
    }

    public String getVrRteCreeStr() {
        return new Double(vrRteCree).toString();
    }

    public String getIdDctoDian() {
        return idDctoDian;
    }

    public void setIdDctoDian(String idDctoDian) {
        this.idDctoDian = idDctoDian;
    }

}