package com.solucionesweb.losbeans.fachada;

import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.math.BigDecimal;

public class FachadaDctoBean implements IConstantes {

    //
    NumberFormat nf = NumberFormat.getNumberInstance();
    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private double idDcto;
    private int idOrden;
    private int indicador;
    private String fechaDcto;
    private double vrBase;
    private double vrPago;
    private int estado;
    private double vrIva;
    private double vrIvaVenta;
    private int idTipoNegocio;
    private double vrRteFuente;
    private double vrDescuento;
    private double vrRteIva;
    private String nombreTercero;
    private double idUsuario;
    private double nitCC;
    private int diasPlazo;
    private double porcentajeDscto;
    private int idCausa;
    private String idDctoNitCC;
    private String fechaDctoNitCC;
    private double vrPagarDctoNitCC;
    private double vrDsctoFcro;
    private double idDctoCruce;
    private int idOrdenCruce;
    private double vrCostoMV;
    private int estadoDcto;
    private int idLocalAdicional;
    private int idPeriodo;
    private String strIdLocal;
    private String strIdDcto;
    private String indicadorSesion;
    private String fechaDctoNitCCStr;
    private String strNitCC;
    //Propiedad Ayuda
    private int edad;
    private String direccion;
    private String telefono;
    private String fechaCorteStr;
    private int diasVencimiento;
    private String fechaInicialStr;
    private String fechaFinalStr;
    private double vrTotal;
    private double vrFactura;
    private String idCliente;
    private String tipoCartera;
    private int numeroDctos;
    private double vrSaldo;
    private String fechaVcto;
    private int diasMora;
    private int idLocalCruce;
    private int idTipoOrdenCruce;
    private double porcentajeIva;
    private double vrVentaConIva;
    private double vrVentaSinDscto;
    private double vrVentaSinIva;
    private int idEstadoTx;
    private String idTipoOrdenCadena;
    private String nombreTipoOrden;
    private int idRecibo;
    private String fechaPago;
    private int idPlanilla;
    private double idVendedor;
    private String nombreVendedor;
    private String aliasUsuario;
    private String nombreTipoNegocio;
    private double vrRteIca;
    private String observacion;
    private int edadFra;
    private double vrImpoconsumo;
    private String tipoNota;
    private int idComprobanteContable;
    private int itemMovimiento;
    private String codigoMonedaMovimiento = "000";
    private String textoVacio = "";
    private String idSucursal = "000";
    private String comentarioMovimiento;
    private String origenMovimiento = "FAC";
    private int idLog;
    private double vrCxC;
    private double vrCostoIva;
    private double vrCostoIND;
    private String fechaTx;
    private String nombreSubcuenta;
    private String idSubcuenta;
    private String nombreAsiento;
    private String prefijo;
    private String nombreCausa;
    private double vrBaseContable;
    private double margenCliente;
    private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    private int indicadorInicial;
    private int indicadorFinal;
    private int idAsiento;
    private String fechaInicial;
    private String fechaFinal;
    private int idTipoAjuste;
    private String nombreUsuario;
    private int idOrdenOrigen;
    private double vrRteCree;
    
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

    public void setStrIdLocal(String strIdLocal) {
        this.strIdLocal = strIdLocal;
    }

    public String getStrIdLocal() {
        return strIdLocal.trim();
    }

    public String getStrIdLocalSoftLand() {
        return "'" + jhFormat.fill(getIdLocalStr(), '0', 3, 0) + "'";
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
        return new Integer(getIdTipoOrden()).toString();
    }

    public void setIdDcto(double idDcto) {
        this.idDcto = idDcto;
    }

    public double getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(String idDctoStr) {
        this.setIdDcto(new Double(idDctoStr).doubleValue());
    }

    public void setIdDctoStr(String idDctoStr) {
        this.setIdDcto(new Double(idDctoStr).doubleValue());
    }

    public String getIdDctoStr() {
        return new Double(getIdDcto()).toString();
    }

    public String getIdDctoEnteroSoftLand() {
        return "'" + jhFormat.fill(new Integer((int) getIdDcto()).toString(), '0', 15, 0) + "'";
    }

    public String getIdDctoSoftLand() {
        return "'" + jhFormat.fill(getIdDctoStr(), '0', 15, 0) + "'";
    }

    public String getIdDctoDf0() {
        return df0.format(getIdDcto());
    }

    public String getIdDctoSf0() {
        return Sf0.format(getIdDcto());
    }

    public void setStrIdDcto(String strIdDcto) {
        this.strIdDcto = strIdDcto;
    }

    public String getStrIdDcto() {
        return strIdDcto;
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
        return new Integer(indicador).toString();
    }

    public void setIndicadorSesion(String indicadorSesion) {
        this.indicadorSesion = indicadorSesion;
    }

    public String getIndicadorSesion() {
        return indicadorSesion;
    }

    public void setFechaDcto(String fechaDcto) {
        this.fechaDcto = fechaDcto;
    }

    public String getFechaDcto() {
        return fechaDcto;
    }

    public String getFechaDctoCorta() {

        //
        return getFechaDcto().substring(0, 4) + "/"
                + getFechaDcto().substring(5, 7) + "/"
                + getFechaDcto().substring(8, 10);
    }
    
    public String getFechaDctoFactAmbi() {

        int xAno = new Integer(getFechaDcto().substring(0, 4)).intValue();
        int xMes = new Integer(getFechaDcto().substring(5, 7)).intValue();
        int xDia = new Integer(getFechaDcto().substring(8, 10)).intValue();

        Day fechaFactura = new Day(xAno, xMes, xDia);

        return fechaFactura.getFechaFormateadaFactAmbi();
    }

    public String getAnoMovimientoSoftLand() {

        //
        return "'" + getFechaDcto().substring(0, 4) + "'";
    }

    public String getPeriodoMovimientoSoftLand() {

        //
        return "'" + getFechaDcto().substring(5, 7) + "'";

    }

    public String getFechaDctoSoftLand() {

        //
        return "'" + getFechaDcto().substring(5, 7) + "/"
                + getFechaDcto().substring(8, 10) + "/"
                + getFechaDcto().substring(0, 4) + "'";
    }

    public Day getFechaDctoDay() {
        String anoStr = getFechaDcto().substring(0, 4);
        String mesStr = getFechaDcto().substring(5, 7);
        String diaStr = getFechaDcto().substring(8, 10);
        int anoInt = Integer.parseInt(anoStr);
        int mesInt = Integer.parseInt(mesStr);
        int diaInt = Integer.parseInt(diaStr);
        Day fechaDay = new Day(anoInt, mesInt, diaInt);

        return fechaDay;
    }

    public String getFechaDctoSqlServer() {

        return getFechaDcto().substring(0, 4)
                + getFechaDcto().substring(5, 7)
                + getFechaDcto().substring(8, 10);
    }

    public String getFechaDctoFormato() {

        return getFechaDcto().substring(0, 4) + "/"
                + getFechaDcto().substring(5, 7) + "/"
                + getFechaDcto().substring(8, 10);
    }

    public void setFechaCorteStr(String fechaCorteStr) {
        this.fechaCorteStr = fechaCorteStr;
    }

    public String getFechaCorteStr() {
        return fechaCorteStr;
    }

    public String getFechaCorteMsAccessStr() {
        return "#" + fechaCorteStr + "#";
    }

    public String fechaCorteSqlServer() {

        return getFechaCorteStr().substring(0, 4)
                + getFechaCorteStr().substring(5, 7)
                + getFechaCorteStr().substring(8, 10);
    }

    public void setFechaInicialStr(String fechaInicialStr) {
        this.fechaInicialStr = fechaInicialStr;
    }

    public String getFechaInicialStr() {
        return fechaInicialStr;
    }

    public String getFechaInicialSqlServer() {

        return getFechaInicialStr().substring(0, 4)
                + getFechaInicialStr().substring(5, 7)
                + getFechaInicialStr().substring(8, 10);

    }

    public String getFechaInicialSqlServerStr() {
        return getFechaInicialStr().substring(0, 4)
                + getFechaInicialStr().substring(5, 7)
                + getFechaInicialStr().substring(8, 10);
    }

    public void setFechaFinalStr(String fechaFinalStr) {
        this.fechaFinalStr = fechaFinalStr;
    }

    public String getFechaFinalStr() {
        return fechaFinalStr;
    }

    public String getFechaFinalSqlServer() {

        return getFechaFinalStr().substring(0, 4)
                + getFechaFinalStr().substring(5, 7)
                + getFechaFinalStr().substring(8, 10);
    }

    public String getFechaFinalSqlServerStr() {
        return getFechaFinalStr().substring(0, 4)
                + getFechaFinalStr().substring(5, 7)
                + getFechaFinalStr().substring(8, 10);
    }

    public Day getFechaCorteDay() {
        String anoStr = getFechaCorteStr().substring(0, 4);
        String mesStr = getFechaCorteStr().substring(5, 7);
        String diaStr = getFechaCorteStr().substring(8, 10);
        int anoInt = Integer.parseInt(anoStr);
        int mesInt = Integer.parseInt(mesStr);
        int diaInt = Integer.parseInt(diaStr);
        Day fechaDay = new Day(anoInt, mesInt, diaInt);

        return fechaDay;
    }

    public void setVrBase(double vrBase) {
        this.vrBase = vrBase;
    }

    public double getVrBase() {
        return vrBase;
    }

    public String getVrBaseSf0() {
        return Sf0.format(getVrBase());
    }

    public String getVrBaseDecimal() {

        BigDecimal vrBaseDecimal =
                new BigDecimal(getVrBase());


        return (vrBaseDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public double getVrBaseRedondeo() {

        //
        return (double) (int) getVrBase();

    }

    public void setVrBase(String vrBaseStr) {
        this.vrBase = new Double(vrBaseStr).doubleValue();
    }

    public String getVrBaseFormatStr() {
        return nf.format(getVrBase());
    }

    public String getVrBaseStr() {
        return new Double(vrBase).toString();
    }

    public String getVrBaseDf0() {
        return df0.format(getVrBase());
    }

    public void setVrPago(double vrPago) {
        this.vrPago = vrPago;
    }

    public double getVrPago() {
        return vrPago;
    }

    public String getVrPagoSf0() {
        return Sf0.format(getVrPago());
    }

    public String getVrPagoDf0() {
        return df0.format(getVrPago());
    }

    public void setVrPago(String vrPagoStr) {
        this.vrPago = new Double(vrPagoStr).doubleValue();
    }

    public String getVrPagoFormatStr() {
        return nf.format(getVrPago());
    }

    public String getVrPagoStr() {
        return new Double(vrPago).toString();
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

    public void setVrIva(double vrIva) {
        this.vrIva = vrIva;
    }

    public double getVrIva() {
        return vrIva;
    }

    public String getVrIvaSf0() {
        return Sf0.format(getVrIva());
    }

    public void setVrIva(String vrIvaStr) {
        this.vrIva = new Double(vrIvaStr).doubleValue();
    }

    public String getVrIvaFormatStr() {
        return nf.format(getVrIva());
    }

    public String getVrIvaStr() {
        return new Double(vrIva).toString();
    }

    public String getVrIvaDecimal() {

        BigDecimal vrIva = new BigDecimal(getVrIva());

        return vrIva.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public void setIdTipoNegocio(int idTipoNegocio) {
        this.idTipoNegocio = idTipoNegocio;
    }

    public int getIdTipoNegocio() {
        return idTipoNegocio;
    }

    public void setIdTipoNegocio(String idTipoNegocioStr) {
        this.idTipoNegocio = new Integer(idTipoNegocioStr).intValue();
    }

    public String getIdTipoNegocioStr() {
        return new Integer(idTipoNegocio).toString();
    }

    public void setVrRteFuente(double vrRteFuente) {
        this.vrRteFuente = vrRteFuente;
    }

    public double getVrRteFuente() {
        return vrRteFuente;
    }

    public String getVrRteFuenteDecimal() {

        BigDecimal retefuente = new BigDecimal(getVrRteFuente());
        return retefuente.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getVrRteFuenteSf0() {
        return Sf0.format(getVrRteFuente());
    }

    public String getVrRteFuenteDf0() {
        return df0.format(getVrRteFuente());
    }

    public void setVrRteFuente(String vrRteFuenteStr) {
        this.vrRteFuente = new Double(vrRteFuenteStr).doubleValue();
    }

    public String getVrRteFuenteStr() {
        return new Double(vrRteFuente).toString();
    }

    public void setVrDescuento(double vrDescuento) {
        this.vrDescuento = vrDescuento;
    }

    public double getVrDescuento() {
        return vrDescuento;
    }

    public String getVrDescuentoDecimal() {

        BigDecimal descuento = new BigDecimal(getVrDescuento());

        return descuento.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getVrDescuentoDf0() {
        return df0.format(getVrDescuento());
    }

    public void setVrDescuento(String vrDescuentoStr) {
        this.vrDescuento = new Double(vrDescuentoStr).doubleValue();
    }

    public String getVrDescuentoStr() {
        return new Double(vrDescuento).toString();
    }

    public void setVrRteIva(double vrRteIva) {
        this.vrRteIva = vrRteIva;
    }

    public double getVrRteIva() {
        return vrRteIva;
    }

    public String getVrRteIvaSf0() {
        return Sf0.format(getVrRteIva());
    }

    public String getVrRteIvaDf0() {
        return df0.format(getVrRteIva());
    }

    public void setVrRteIva(String vrRteIvaStr) {
        this.vrRteIva = new Double(vrRteIvaStr).doubleValue();
    }

    public String getVrRteIvaStr() {
        return new Double(vrRteIva).toString();
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public String getNombreTercero() {

        return nombreTercero;
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

    public String getIdUsuarioFormatStr() {
        return nf.format(getIdUsuario());
    }

    public String getIdUsuarioStr() {
        return new Double(idUsuario).toString();
    }

    public void setNitCC(double nitCC) {
        this.nitCC = nitCC;
    }

    public double getNitCC() {
        return nitCC;
    }

    public void setNitCC(String nitCCStr) {
        this.nitCC = new Double(nitCCStr).doubleValue();
    }

    public String getNitCCStr() {
        return new Double(nitCC).toString();
    }

    public void setDiasPlazo(int diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public int getDiasPlazo() {
        return diasPlazo;
    }

    public String getDiasPlazoDf0() {
        return df0.format(getDiasPlazo());
    }

    public void setDiasPlazo(String diasPlazoStr) {
        this.diasPlazo = new Integer(diasPlazoStr).intValue();
    }

    public String getDiasPlazoStr() {
        return new Integer(diasPlazo).toString();
    }

    public void setPorcentajeDscto(double porcentajeDscto) {
        this.porcentajeDscto = porcentajeDscto;
    }

    public double getPorcentajeDscto() {
        return porcentajeDscto;
    }

    public void setPorcentajeDscto(String porcentajeDsctoStr) {
        this.porcentajeDscto = new Double(porcentajeDsctoStr).doubleValue();
    }

    public String getPorcentajeDsctoStr() {
        return new Double(porcentajeDscto).toString();
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
        return new Integer(idCausa).toString();
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public String getIdDctoNitCC() {

        //
        double valor = 0.0;

        try {

            valor = Double.parseDouble(idDctoNitCC);

        } catch (NumberFormatException nfe) {

            return idDctoNitCC.trim();
        }


        return new Integer((int) valor).toString();
    }

    public void setFechaDctoNitCC(String fechaDctoNitCC) {
        this.fechaDctoNitCC = fechaDctoNitCC;
    }

    public String getFechaDctoNitCC() {
        return fechaDctoNitCC;
    }

    public String getIdDctoNitCCSoftLand() {
        return "'" + jhFormat.fill(getIdDctoNitCC(), '0', 15, 0) + "'";
    }

    public String getFechaDctoNitCCCorta() {
        return getFechaDctoNitCC().substring(0, 4) + "/"
                + getFechaDctoNitCC().substring(5, 7) + "/"
                + getFechaDctoNitCC().substring(8, 10);
    }

    public Day getFechaDctoNitCCDay() {
        String anoStr = getFechaDctoNitCCStr().substring(0, 4);
        String mesStr = getFechaDctoNitCCStr().substring(5, 7);
        String diaStr = getFechaDctoNitCCStr().substring(8, 10);
        int anoInt = Integer.parseInt(anoStr);
        int mesInt = Integer.parseInt(mesStr);
        int diaInt = Integer.parseInt(diaStr);
        Day fechaDay = new Day(anoInt, mesInt, diaInt);

        return fechaDay;
    }

    public void setFechaDctoNitCCStr(String fechaDctoNitCCStr) {
        if (fechaDctoNitCCStr == null) {
            this.fechaDctoNitCCStr = STRINGVACIO;
        } else {
            this.fechaDctoNitCCStr = fechaDctoNitCCStr;
        }
    }

    public String getFechaDctoNitCCStr() {
        return fechaDctoNitCCStr;
    }

    public String getFechaDctoNitCCSqlServer() {

        if (getFechaDctoNitCC() == null) {
            return getFechaDctoSqlServer();
        } else {

            return getFechaDctoNitCC().substring(0, 4)
                    + getFechaDctoNitCC().substring(5, 7)
                    + getFechaDctoNitCC().substring(8, 10);
        }
    }

    public void setVrPagarDctoNitCC(double vrPagarDctoNitCC) {
        this.vrPagarDctoNitCC = vrPagarDctoNitCC;
    }

    public double getVrPagarDctoNitCC() {
        return vrPagarDctoNitCC;
    }

    public void setVrPagarDctoNitCC(String vrPagarDctoNitCCStr) {
        this.vrPagarDctoNitCC = new Integer(vrPagarDctoNitCCStr).intValue();
    }

    public String getVrPagarDctoNitCCStr() {
        return new Double(vrPagarDctoNitCC).toString();
    }

    public void setVrDsctoFcro(double vrDsctoFcro) {
        this.vrDsctoFcro = vrDsctoFcro;
    }

    public double getVrDsctoFcro() {
        return vrDsctoFcro;
    }

    public String getVrDsctoFcroSf0() {
        return Sf0.format(getVrDsctoFcro());
    }

    public void setVrDsctoFcro(String vrDsctoFcroStr) {
        this.vrDsctoFcro = new Double(vrDsctoFcroStr).doubleValue();
    }

    public String getVrDsctoFcroStr() {
        return new Double(vrDsctoFcro).toString();
    }

    public String getVrDsctoFcroDf0() {
        return df0.format(getVrDsctoFcro());
    }

    public void setIdDctoCruce(double idDctoCruce) {
        this.idDctoCruce = idDctoCruce;
    }

    public double getIdDctoCruce() {
        return idDctoCruce;
    }

    public void setIdDctoCruce(String idDctoCruceStr) {
        this.idDctoCruce = new Double(idDctoCruceStr).doubleValue();
    }

    public String getIdDctoCruceStr() {
        return new Double(idDctoCruce).toString();
    }

    public void setVrCostoMV(double vrCostoMV) {
        this.vrCostoMV = vrCostoMV;
    }

    public double getVrCostoMV() {
        return vrCostoMV;
    }

    public String getVrCostoMVSf0() {
        return Sf0.format(getVrCostoMV());
    }

    public void setVrCostoMV(String vrCostoMVStr) {
        this.vrCostoMV = new Double(vrCostoMVStr).doubleValue();
    }

    public String getVrCostoMVStr() {
        return new Double(vrCostoMV).toString();
    }

    public String getVrCostoMVDecimal() {

        BigDecimal vrCostoMV = new BigDecimal(getVrCostoMV());

        return vrCostoMV.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    public void setEstadoDcto(int estadoDcto) {
        this.estadoDcto = estadoDcto;
    }

    public int getEstadoDcto() {
        return estadoDcto;
    }

    public void setEstadoDcto(String estadoDctoStr) {
        this.estadoDcto = new Integer(estadoDctoStr).intValue();
    }

    public String getEstadoDctoStr() {
        return new Integer(estadoDcto).toString();
    }

    public void setIdLocalAdicional(int idLocalAdicional) {
        this.idLocalAdicional = idLocalAdicional;
    }

    public int getIdLocalAdicional() {
        return idLocalAdicional;
    }

    public void setIdLocalAdicional(String idLocalAdicionalStr) {
        this.idLocalAdicional = new Integer(idLocalAdicionalStr).intValue();
    }

    public String getIdLocalAdicionalStr() {
        return new Integer(idLocalAdicional).toString();
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodoStr) {
        this.idPeriodo = new Integer(idPeriodoStr).intValue();
    }

    public String getIdPeriodoStr() {
        return new Integer(idPeriodo).toString();
    }

    // Metodo Ayuda
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(String edadStr) {
        this.edad = new Integer(edadStr).intValue();
    }

    public String getEdadStr() {
        return new Integer(edad).toString();
    }

    public void setDireccion(String direccion) {
        if (direccion == null) {
            this.direccion = STRINGVACIO;
        } else {
            this.direccion = direccion;
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setTelefono(String telefono) {

        if (telefono == null) {
            this.telefono = STRINGVACIO;
        } else {
            this.telefono = telefono;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDiasVencimiento(int diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }

    public int getDiasVencimiento() {
        return diasVencimiento;
    }

    public void setDiasVencimiento(String diasVencimientoStr) {
        this.diasVencimiento = new Integer(diasVencimientoStr).intValue();
    }

    public String getDiasVencimientoStr() {
        return new Integer(diasVencimiento).toString();
    }

    public void setVrTotal(double vrTotal) {
        this.vrTotal = vrTotal;
    }

    public double getVrTotal() {
        return vrTotal;
    }

    public void setVrTotal(String vrTotalStr) {
        this.vrTotal = new Double(vrTotalStr).doubleValue();
    }

    public String getVrTotalStr() {
        return new Double(vrTotal).toString();
    }

    public String getVrTotalStrFormatDf0Str() {
        return df0.format(vrTotal);
    }

    public String getVrTotalStrFormatDf1Str() {
        return df1.format(vrTotal);
    }

    public void setVrFactura(double vrFactura) {
        this.vrFactura = vrFactura;
    }

    public double getVrFactura() {
        return (getVrBase()
                + getVrIva()
                + getVrImpoconsumo()
                - getVrRteFuente()
                - getVrRteIva()
                - getVrRteIca()
                - getVrRteCree());
    }

    public String getVrFacturaStr() {
        return new Double(getVrFactura()).toString();
    }

    public String getVrFacturaDf0() {
        return df0.format(getVrFactura());
    }

    public String getVrFacturaSf0() {
        return Sf0.format(getVrFactura());
    }

    public String getVrFacturaNotaSf0() {
        return Sf0.format(getVrFactura() * (-1));
    }

    public double getVrFacturaNota() {
        return (getVrFactura() * (-1));
    }

    public String getVrFacturaDecimal() {

        BigDecimal vrFactura = new BigDecimal(getVrFactura());

        return vrFactura.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public void setStrNitCC(String strNitCC) {
        this.strNitCC = strNitCC;
    }

    public String getStrNitCC() {
        return strNitCC.trim();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdClienteSoftLand() {
        return "'" + jhFormat.fill(getIdCliente(), '0', 12, 0) + "'";
    }

    public String getTipoCartera() {
        return tipoCartera;
    }

    public void setTipoCartera(String tipoCartera) {
        this.tipoCartera = tipoCartera;
    }

    public int getNumeroDctos() {
        return numeroDctos;
    }

    public String getNumeroDctoDf0() {
        return df0.format(getNumeroDctos());
    }

    public void setNumeroDctos(int numeroDctos) {
        this.numeroDctos = numeroDctos;
    }

    public double getVrSaldo() {
        return vrSaldo;
    }

    public String getVrSaldoDf0() {
        return df0.format(getVrSaldo());
    }

    public void setVrSaldo(double vrSaldo) {
        this.vrSaldo = vrSaldo;
    }

    public String getVrSaldoStr() {
        return new Double(getVrSaldo()).toString();
    }

    public BigDecimal getVrSaldoDecimal() {

        BigDecimal VrSaldoDecimal = new BigDecimal(getVrSaldoStr());

        return VrSaldoDecimal;


    }

    public String getFechaVcto() {
        return fechaVcto;
    }

    public String getFechaVencimiento() {

        int xAno = new Integer(getFechaDcto().substring(0, 4)).intValue();
        int xMes = new Integer(getFechaDcto().substring(5, 7)).intValue();
        int xDia = new Integer(getFechaDcto().substring(8, 10)).intValue();

        //
        Day fechaFactura = new Day(xAno, xMes, xDia);

        fechaFactura.advance(getDiasPlazo());

        //
        return fechaFactura.getFechaFormateada();

    }
    
     public String getFechaVencimientoFacAmbi() {

        int xAno = new Integer(getFechaDcto().substring(0, 4)).intValue();
        int xMes = new Integer(getFechaDcto().substring(5, 7)).intValue();
        int xDia = new Integer(getFechaDcto().substring(8, 10)).intValue();

        //
        Day fechaFactura = new Day(xAno, xMes, xDia);

        fechaFactura.advance(getDiasPlazo());

        //
        return fechaFactura.getFechaFormateadaFactAmbi();

    }

    public void setFechaVcto(String fechaVcto) {
        this.fechaVcto = fechaVcto;
    }

    public int getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(int diasMora) {
        this.diasMora = diasMora;
    }

    public String getDiasMoraStr() {
        return new Integer(getDiasMora()).toString();
    }

    public int getIdLocalCruce() {
        return idLocalCruce;
    }

    public void setIdLocalCruce(int idLocalCruce) {
        this.idLocalCruce = idLocalCruce;
    }

    public void setIdLocalCruce(String idLocalCruceStr) {
        this.idLocalCruce = new Integer(idLocalCruceStr).intValue();
    }

    public String getIdLocalCruceStr() {
        return new Integer(getIdLocalCruce()).toString();
    }

    public int getIdTipoOrdenCruce() {
        return idTipoOrdenCruce;
    }

    public void setIdTipoOrdenCruce(int idTipoOrdenCruce) {
        this.idTipoOrdenCruce = idTipoOrdenCruce;
    }

    public void setIdTipoOrdenCruce(String idTipoOrdenCruceStr) {
        this.idTipoOrdenCruce = new Integer(idTipoOrdenCruceStr).intValue();
    }

    public String getIdTipoOrdenCruceStr() {
        return new Integer(getIdTipoOrdenCruce()).toString();
    }

    public double calculaSaldo() {
        return getVrBase()
                + getVrIva()
                + getVrImpoconsumo()
                - (getVrPago()
                + getVrRteFuente()
                + getVrRteIva()
                + getVrDsctoFcro()
                + getVrRteCree());
    }

    public double calculaVrPedidoConIva() {
        return (getVrBase()
                + getVrIva()
                + getVrImpoconsumo());
    }

    public String calculaVrPedidoConIvaDf0() {
        return df0.format(calculaVrPedidoConIva());
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(String porcentajeIvaStr) {
        this.porcentajeIva = new Double(porcentajeIvaStr).doubleValue();
    }

    public String getPorcentajeIvaStr() {
        return new Double(porcentajeIva).toString();
    }

    public void setVrVentaConIva(double vrVentaConIva) {
        this.vrVentaConIva = vrVentaConIva;
    }

    public double getVrVentaConIva() {
        return vrVentaConIva;
    }

    public void setVrVentaConIva(String vrVentaConIvaStr) {
        this.vrVentaConIva = new Double(vrVentaConIvaStr).doubleValue();
    }

    public String getVrVentaConIvaStr() {
        return new Double(vrVentaConIva).toString();
    }

    public String getVrVentaConIvaDf0() {
        return df0.format(getVrVentaConIva());
    }

    public int getVrVentaConIvaSoftLand() {
//        return Sf0.format(getVrVentaConIva());
        return (int) getVrVentaConIva();
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

    public String getVrVentaSinIvaSf0() {
        return Sf0.format(getVrVentaSinIva());
    }

    public String getVrVentaSinIvaStr() {
        return new Double(vrVentaSinIva).toString();
    }

    public String getVrVentaSinIvaDf0() {
        return df0.format(getVrVentaSinIva());
    }

    public int getVrVentaSinIvaSoftLand() {
//        return Sf0.format(getVrVentaSinIva());
        return (int) getVrVentaSinIva();
    }

    public void setVrVentaSinDscto(double vrVentaSinDscto) {
        this.vrVentaSinDscto = vrVentaSinDscto;
    }

    public double getVrVentaSinDscto() {
        return vrVentaSinDscto;
    }

    public void setVrVentaSinDscto(String vrVentaSinDsctoStr) {
        this.vrVentaSinDscto = new Double(vrVentaSinDsctoStr).doubleValue();
    }

    public String getVrVentaSinDsctoStr() {
        return new Double(vrVentaSinDscto).toString();
    }

    public String getVrVentaSinDsctoDf0() {
        return df0.format(getVrVentaSinDscto());
    }

    public int getVrVentaSinDsctoSoftLand() {
//        return Sf0.format(getVrVentaSinDscto());
        return (int) getVrVentaSinDscto();
    }

    public int getIdEstadoTx() {
        return idEstadoTx;
    }

    public void setIdEstadoTx(int idEstadoTx) {
        this.idEstadoTx = idEstadoTx;
    }

    public void setIdEstadoTx(String idEstadoTxStr) {
        this.idEstadoTx = new Integer(idEstadoTxStr).intValue();
    }

    public String getIdTipoOrdenCadena() {
        return idTipoOrdenCadena;
    }

    public void setIdTipoOrdenCadena(String idTipoOrdenCadena) {
        this.idTipoOrdenCadena = idTipoOrdenCadena;
    }

    public String getNombreTipoOrden() {
        return nombreTipoOrden;
    }

    public void setNombreTipoOrden(String nombreTipoOrden) {
        this.nombreTipoOrden = nombreTipoOrden;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public String getIdReciboSf0() {
        return Sf0.format(getIdRecibo());

    }

    public void setIdRecibo(String idReciboStr) {
        this.idRecibo = new Integer(idReciboStr).intValue();
    }

    public String getIdReciboStr() {
        return new Integer(idRecibo).toString();
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Day getFechaPagoDay() {
        String anoStr = getFechaPago().substring(0, 4);
        String mesStr = getFechaPago().substring(5, 7);
        String diaStr = getFechaPago().substring(8, 10);
        int anoInt = Integer.parseInt(anoStr);
        int mesInt = Integer.parseInt(mesStr);
        int diaInt = Integer.parseInt(diaStr);
        Day fechaDay = new Day(anoInt, mesInt, diaInt);

        return fechaDay;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public String getFechaPagoSqlServer() {

        return getFechaPago().substring(0, 4)
                + getFechaPago().substring(5, 7)
                + getFechaPago().substring(8, 10);
    }

    public String getFechaPagoFormato() {

        return getFechaPago().substring(0, 4) + "/"
                + getFechaPago().substring(5, 7) + "/"
                + getFechaPago().substring(8, 10);
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(String idPlanillaStr) {
        this.idPlanilla = new Integer(idPlanillaStr).intValue();
    }

    public String getIdPlanillaStr() {
        return new Integer(idPlanilla).toString();
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

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor.toUpperCase();
    }

    public void setNombreTipoNegocio(String nombreTipoNegocio) {
        this.nombreTipoNegocio = nombreTipoNegocio;
    }

    public String getNombreTipoNegocio() {

        return nombreTipoNegocio;

    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public void setVrRteIca(double vrRteIca) {
        this.vrRteIca = vrRteIca;
    }

    public double getVrRteIca() {
        return vrRteIca;
    }

    public String getVrRteIcaSf0() {
        return Sf0.format(getVrRteIca());
    }

    public String getVrRteIcaDf0() {
        return df0.format(getVrRteIca());
    }

    public void setVrRteIca(String vrRteIcaStr) {
        this.vrRteIca = new Double(vrRteIcaStr).doubleValue();
    }

    public String getVrRteIcaStr() {
        return new Double(vrRteIca).toString();
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    public String getFormaPagoTexto() {
        if (getDiasPlazo() == 0) {

            return "CONTADO " + getDiasPlazoDf0() + " DIAS";

        } else {
            return "CREDITO " + getDiasPlazoDf0() + " DIAS";
        }
    }

    public void setEdadFra(int edadFra) {
        this.edadFra = edadFra;
    }

    public int getEdadFra() {
        return edadFra;
    }

    public void setEdadFra(String edadFraStr) {
        this.edadFra = new Integer(edadFraStr).intValue();
    }

    public String getEdadFraStr() {
        return new Integer(edadFra).toString();
    }

    public void setVrImpoconsumo(double vrImpoconsumo) {
        this.vrImpoconsumo = vrImpoconsumo;
    }

    public double getVrImpoconsumo() {
        return vrImpoconsumo;
    }

    public String getVrImpoconsumoSf0() {
        return Sf0.format(getVrImpoconsumo());
    }

    public String getVrImpoconsumoDf0() {
        return df0.format(getVrImpoconsumo());
    }

    public void setVrImpoconsumo(String vrImpoconsumoStr) {
        this.vrImpoconsumo = new Double(vrImpoconsumoStr).doubleValue();
    }

    public String getVrImpoconsumoStr() {
        return new Double(vrImpoconsumo).toString();
    }

    public int getIdComprobanteContable() {
        return idComprobanteContable;
    }

    public String getIdComprobanteContableStr() {
        return new Integer(idComprobanteContable).toString();
    }

    public void setIdComprobanteContable(int idComprobanteContable) {
        this.idComprobanteContable = idComprobanteContable;
    }

    public void setIdComprobanteContable(String idComprobanteContableStr) {
        this.idComprobanteContable = new Integer(idComprobanteContableStr).intValue();
    }

    public String getIdComprobanteContableSoftLand() {
        return "'" + jhFormat.fill(getIdComprobanteContableStr(), '0', 5, 0) + "'";
    }

    public String getIdPrefijoMovimientoSoftLand() {
        return "'" + jhFormat.fill(getIdLocalStr(), '0', 2, 0)
                + jhFormat.fill(getIdTipoOrdenStr(), '0', 3, 0) + "'";
    }

    public void setItemMovimiento(String itemMovimientoStr) {
        this.itemMovimiento = new Integer(itemMovimientoStr).intValue();
    }

    public void setItemMovimiento(int itemMovimiento) {
        this.itemMovimiento = itemMovimiento;
    }

    public int getItemMovimiento() {
        return this.itemMovimiento;
    }

    public String getItemMovimientoStr() {
        return new Integer(getItemMovimiento()).toString();
    }

    public String getItemMovimientoSoftLand() {
        return "'" + jhFormat.fill(getItemMovimientoStr(), '0', 5, 0) + "'";
    }

    public String getCodigoMonedaMovimientoSoftLand() {
        return "'" + codigoMonedaMovimiento + "'";
    }

    public String getTextoVacioSoftLand() {

        //
        return "'" + textoVacio + "'";

    }

    public String getIdSucursalSoftLand() {

        //
        return "'" + idSucursal + "'";

    }

    public String getComentarioMovimientoSoftLand() {

        int xLongitud = comentarioMovimiento.length();

        if (xLongitud >= 100) {

            //
            return "'" + comentarioMovimiento.substring(0, 99) + "'";

        } else {

            //
            return "'" + comentarioMovimiento + "'";
        }
    }

    public String getComentarioMovimiento() {

        int xLongitud = comentarioMovimiento.length();

        if (xLongitud >= 100) {

            //
            return comentarioMovimiento.substring(0, 99);

        } else {

            //
            return comentarioMovimiento;
        }
    }

    public void setComentarioMovimiento(String comentarioMovimiento) {
        this.comentarioMovimiento = comentarioMovimiento;
    }

    public String getOrigenMovimientoSoftland() {

        //
        return "'" + origenMovimiento + "'";
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

    public String getStrIdLogSoftLand() {
        return "'" + jhFormat.fill(getIdLogStr(), '0', 3, 0) + "'";
    }

    public void setVrCxC(double vrCxC) {
        this.vrCxC = vrCxC;
    }

    public double getVrCxC() {
        return vrCxC;
    }

    public String getVrCxCDf0() {
        return df0.format(getVrCxC());
    }

    public void setVrCxC(String vrCxCStr) {
        this.vrCxC = new Double(vrCxCStr).doubleValue();
    }

    public String getVrCxCStr() {
        return new Double(vrCxC).toString();
    }

    public void setVrCostoIva(double vrCostoIva) {
        this.vrCostoIva = vrCostoIva;
    }

    public double getVrCostoIva() {
        return vrCostoIva;
    }

    public String getVrCostoIvaDf0() {
        return df0.format(getVrCostoIva());
    }

    public void setVrCostoIva(String vrCostoIvaStr) {
        this.vrCostoIva = new Double(vrCostoIvaStr).doubleValue();
    }

    public String getVrCostoIvaStr() {
        return new Double(vrCostoIva).toString();
    }

    public void setVrIvaVenta(double vrIvaVenta) {
        this.vrIvaVenta = vrIvaVenta;
    }

    public double getVrIvaVenta() {
        return vrIvaVenta;
    }

    public String getVrIvaVentaSf0() {
        return Sf0.format(getVrIvaVenta());
    }

    public String getVrIvaVentaDf0() {
        return df0.format(getVrIvaVenta());
    }

    public void setVrIvaVenta(String vrIvaVentaStr) {
        this.vrIvaVenta = new Double(vrIvaVentaStr).doubleValue();
    }

    public String getVrIvaVentaStr() {
        return new Double(vrIvaVenta).toString();
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

    public String getVrCostoINDDecimal() {

        BigDecimal vrCostoIND = new BigDecimal(getVrCostoIND());

        return vrCostoIND.setScale(0, BigDecimal.ROUND_HALF_UP).toString();

    }

    public void setIdOrdenCruce(int idOrdenCruce) {
        this.idOrdenCruce = idOrdenCruce;
    }

    public int getIdOrdenCruce() {
        return idOrdenCruce;
    }

    public void setIdOrdenCruce(String idOrdenCruceStr) {
        this.idOrdenCruce = new Integer(idOrdenCruceStr).intValue();
    }

    public String getIdOrdenCruceStr() {
        return new Integer(idOrdenCruce).toString();
    }

    public String getFechaTx() {
        return fechaTx;
    }

    public String getFechaTxCorta() {
        return getFechaTx().substring(0, 19);
    }

    public void setFechaTx(String fechaTx) {
        this.fechaTx = fechaTx;
    }

    public String getNombreSubcuenta() {
        return nombreSubcuenta;
    }

    public void setNombreSubcuenta(String nombreSubcuenta) {
        this.nombreSubcuenta = nombreSubcuenta;
    }

    public String getIdSubcuenta() {
        return idSubcuenta;
    }

    public void setIdSubcuenta(String idSubcuenta) {
        this.idSubcuenta = idSubcuenta;
    }

    public String getNombreAsiento() {
        return nombreAsiento;
    }

    public String getNombreAsientoSoftland() {
        return "'" + nombreAsiento + "'";
    }

    public void setNombreAsiento(String nombreAsiento) {
        this.nombreAsiento = nombreAsiento;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getIdDctoPrefijoSoftLand() {
        return "'"
                + jhFormat.fill(getPrefijo().trim(), '0', 5, 0)
                + jhFormat.fill(new Integer((int) getIdDcto()).toString(), '0', 10, 0)
                + "'";
    }

    public String getIdDctoNitCCPrefijoSoftLand() {
        return "'"
                + jhFormat.fill(getPrefijo().trim(), '0', 5, 0)
                + jhFormat.fill(getIdDctoNitCC(), '0', 10, 0)
                + "'";
    }

    public String getNombreCausa() {
        return nombreCausa;
    }

    public void setNombreCausa(String nombreCausa) {
        this.nombreCausa = nombreCausa;
    }

    public void setVrBaseContable(double vrBaseContable) {
        this.vrBaseContable = vrBaseContable;
    }

    public double getVrBaseContable() {
        return vrBaseContable;
    }

    public String getVrBaseContableSf0() {
        return Sf0.format(getVrBaseContable());
    }

    public double getVrBaseContableRedondeo() {

        //
        return (double) (int) getVrBaseContable();

    }

    public void setVrBaseContable(String vrBaseContableStr) {
        this.vrBaseContable = new Double(vrBaseContableStr).doubleValue();
    }

    public String getVrBaseContableFormatStr() {
        return nf.format(getVrBaseContable());
    }

    public String getVrBaseContableStr() {
        return new Double(vrBaseContable).toString();

    }

    public void setMargenCliente(double margenCliente) {
        this.margenCliente = margenCliente;
    }

    public double getMargenCliente() {
        return margenCliente;
    }

    public String getMargenClienteSf0() {
        return Sf0.format(getMargenCliente());
    }

    public String getMargenClienteDecimal() {

        BigDecimal margenCliente = new BigDecimal(getMargenCliente());

        return margenCliente.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

    }

    public String getMargenClientePorcentaje() {
        return porcentaje.format(getMargenCliente());
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

    public void setIdTipoOrdenINIStr(String idTipoOrdenINI) {
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

    public void setIdTipoOrdenFINStr(String idTipoOrdenFIN) {
        this.idTipoOrdenFIN = new Integer(idTipoOrdenFIN).intValue();
    }

    public int getIndicadorInicial() {
        return indicadorInicial;
    }

    public String getIndicadorInicialStr() {
        return new Integer(indicadorInicial).toString();
    }

    public void setIndicadorInicial(int indicadorInicial) {
        this.indicadorInicial = indicadorInicial;
    }

    public int getIndicadorFinal() {
        return indicadorFinal;
    }

    public String getIndicadorFinalStr() {
        return new Integer(indicadorFinal).toString();
    }

    public void setIndicadorFinal(int indicadorFinal) {
        this.indicadorFinal = indicadorFinal;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(String idAsientoStr) {
        this.idAsiento = new Integer(idAsientoStr).intValue();
    }

    public String getIdAsientoStr() {
        return new Integer(idAsiento).toString();
    }

    public String getStrIdAsientoSoftLand() {
        return "'" + jhFormat.fill(getIdAsientoStr(), '0', 3, 0) + "'";
    }

    public FachadaDctoBean() {
    }

    /**
     * @return the fechaInicial
     */
    public String getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getIdTipoAjuste() {
        return idTipoAjuste;
    }

    public String getIdTipoAjusteStr() {
        return new Integer(idTipoAjuste).toString();
    }

    public void setIdTipoAjuste(int idTipoAjuste) {
        this.idTipoAjuste = idTipoAjuste;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setIdOrdenOrigen( int idOrdenOrigen )
    {
        this.idOrdenOrigen = idOrdenOrigen ;
    }

    public int getIdOrdenOrigen()
    {
        return idOrdenOrigen;
    }

    public void setIdOrdenOrigen( String idOrdenOrigenStr )
    {
        this.idOrdenOrigen = new Integer(idOrdenOrigenStr).intValue();
    }

    public String getIdOrdenOrigenStr()
    {
        return new Integer(idOrdenOrigen).toString();
    }
    
    public void setVrRteCree(double vrRteCree) {
        this.vrRteCree = vrRteCree;
    }

    public double getVrRteCree() {
        return vrRteCree;
    }

    public String getVrRteCreeSf0() {
        return Sf0.format(getVrRteCree());
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
}
