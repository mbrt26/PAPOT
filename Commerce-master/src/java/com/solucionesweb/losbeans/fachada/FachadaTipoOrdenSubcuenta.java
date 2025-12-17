package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaTipoOrdenSubcuenta {

    // Propiedades
    private int idTipoOrden;
    private String idSubcuenta;
    private int estado;
    private int idSeq;
    private int idAsiento;
    private String nombreTipoOrden;
    private String nombreSubcuenta;
    private double cantidad;
    private double vrUnitario;    
    private int idLocal;
    private int idOrden;
    private String nombreTercero;
    private int idDcto;
    private String idDctoNitCC;
    private String fechaDcto;
    private String observacion;
    private int idAlcance;
    private String fechaInicial;
    private String fechaFinal;
    private double vrDebito;
    private double vrCredito;
    private double vrMovimiento;
    private int idComprobante;
    private String idCliente;
    private int idRecibo;

    //
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat sf0 = new DecimalFormat("#########");
    DecimalFormat sf1 = new DecimalFormat("#########.0");
    DecimalFormat sf2 = new DecimalFormat("#########.00");

    // Metodos
    public void setIdTipoOrden( int idTipoOrden )
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public void setIdTipoOrden( String idTipoOrdenStr )
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue() ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdSubcuenta( String idSubcuenta )
    {
        this.idSubcuenta = idSubcuenta ;
    }

    public String getIdSubcuenta()
    {
        return idSubcuenta;
    }

    public int getEstado()
    {
        return estado;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public void setIdSeq( int idSeq )
    {
        this.idSeq = idSeq ;
    }

    public void setIdSeq( String idSeqStr )
    {
        this.idSeq = new Integer(idSeqStr).intValue() ;
    }

    public int getIdAsiento()
    {
        return idAsiento;
    }

    public String getIdAsientoStr()
    {
        return new Integer(idAsiento).toString();
    }

    public void setIdAsiento( int idAsiento )
    {
        this.idAsiento = idAsiento ;
    }

    public void setIdAsiento( String idAsientoStr )
    {
        this.idAsiento = new Integer(idAsientoStr).intValue() ;
    }

    public FachadaTipoOrdenSubcuenta() { }

    public String getNombreTipoOrden() {
        return nombreTipoOrden;
    }

    public void setNombreTipoOrden(String nombreTipoOrden) {
        this.nombreTipoOrden = nombreTipoOrden;
    }

    public String getNombreSubcuenta() {
        return nombreSubcuenta;
    }

    public void setNombreSubcuenta(String nombreSubcuenta) {
        this.nombreSubcuenta = nombreSubcuenta;
    }

    public void setCantidad( double cantidad )
    {
        this.cantidad = cantidad ;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public String getCantidadDf2()
    {
        return df2.format(getCantidad());
    }

    public int getCantidadInt()
    {
        return (int)getCantidad();
    }

    public void setCantidad( String cantidadStr )
    {
        this.cantidad = new Double(cantidadStr).doubleValue();
    }

    public String getCantidadStr()
    {
        return new Double(cantidad).toString();
    }

    public String getCantidadSf1()
    {
        return sf1.format(getCantidad());
    }

    public void setVrUnitario( double vrUnitario )
    {
        this.vrUnitario = vrUnitario ;
    }

    public double getVrUnitario()
    {
        return vrUnitario;
    }

    public String getVrUnitarioSf0()
    {
        return sf0.format(getVrUnitario());
    }

    public String getVrUnitarioDf2()
    {
        return df2.format(getVrUnitario());
    }

    public String getVrUnitarioDf0()
    {
        return df0.format(getVrUnitario());
    }

    public void setVrUnitario( String vrUnitarioStr )
    {
        this.vrUnitario = new Double(vrUnitarioStr).doubleValue();
    }

    public String getVrUnitarioStr()
    {
        return new Double(vrUnitario).toString();
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public void setIdLocal( String  idLocalStr )
    {
        this.idLocal =  new Integer(idLocalStr).intValue() ;
    }


    public int getIdLocal()
    {
        return idLocal;
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public void setIdOrden( int idOrden )
    {
        this.idOrden = idOrden ;
    }

    public int getIdOrden()
    {
        return idOrden;
    }

    public String getIdOrdenStr()
    {
        return new Integer(idOrden).toString();
    }

    public void setIdDcto( int idDcto )
    {
        this.idDcto = idDcto ;
    }

    public int getIdDcto()
    {
        return idDcto;
    }

    public String getIdDctoStr()
    {
        return new Integer(idDcto).toString();
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public void setIdRecibo( int idRecibo )
    {
        this.idRecibo = idRecibo ;
    }

    public int getIdRecibo()
    {
        return idRecibo;
    }

    public String getIdReciboStr()
    {
        return new Integer(idRecibo).toString();
    }

    public String getFechaDcto() {
        return fechaDcto;
    }

    public void setFechaDcto(String fechaDcto) {
        this.fechaDcto = fechaDcto;
    }

    public String getFechaDctoSqlServer() {

            return getFechaDcto().substring(0, 4) +
                   getFechaDcto().substring(5, 7) +
                   getFechaDcto().substring(8, 10);
    }

    public String getFechaDctoCorta() {

            return getFechaDcto().substring(0, 4) + "/" +
                   getFechaDcto().substring(5, 7) + "/" +
                   getFechaDcto().substring(8, 10);
    }

    public String getObservacion() {
        return observacion;
    }

    public String getObservacionLogitud50() {

        //
        if (getObservacion().length()>=50)

           //
           return getObservacion().substring(0,49);
        else {

           //
           return getObservacion();
        }
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public void setIdAlcance( int idAlcance )
    {
        this.idAlcance = idAlcance ;
    }

    public void setIdAlcance( String idAlcanceStr )
    {
        this.idAlcance = new Integer(idAlcanceStr).intValue() ;
    }

    public int getIdAlcance()
    {
        return idAlcance;
    }

    public String getIdAlcanceStr()
    {
        return new Integer(idAlcance).toString();
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

     public String getFechaInicialSqlServer() {

            return getFechaInicial().substring(0, 4) +
                   getFechaInicial().substring(5, 7) +
                   getFechaInicial().substring(8, 10);
    }

     public String getFechaFinalSqlServer() {

            return getFechaFinal().substring(0, 4) +
                   getFechaFinal().substring(5, 7) +
                   getFechaFinal().substring(8, 10);
    }

    public void setVrDebito( double vrDebito )
    {
        this.vrDebito = vrDebito ;
    }

    public double getVrDebito()
    {
        return vrDebito;
    }

    public String getVrDebitoSf0()
    {
        return sf0.format(getVrDebito());
    }

    public String getVrDebitoDf2()
    {
        return df2.format(getVrDebito());
    }

    public String getVrDebitoDf0()
    {
        return df0.format(getVrDebito());
    }

    public void setVrDebito( String vrDebitoStr )
    {
        this.vrDebito = new Double(vrDebitoStr).doubleValue();
    }

    public String getVrDebitoStr()
    {
        return new Double(vrDebito).toString();
    }

    public void setVrCredito( double vrCredito )
    {
        this.vrCredito = vrCredito ;
    }

    public double getVrCredito()
    {
        return vrCredito;
    }

    public String getVrCreditoSf0()
    {
        return sf0.format(getVrCredito());
    }

    public String getVrCreditoDf2()
    {
        return df2.format(getVrCredito());
    }

    public String getVrCreditoDf0()
    {
        return df0.format(getVrCredito());
    }

    public void setVrCredito( String vrCreditoStr )
    {
        this.vrCredito = new Double(vrCreditoStr).doubleValue();
    }

    public String getVrCreditoStr()
    {
        return new Double(vrCredito).toString();
    }

    public void setVrMovimiento( double vrMovimiento )
    {
        this.vrMovimiento = vrMovimiento ;
    }

    public double getVrMovimiento()
    {
        return vrMovimiento;
    }

    public String getVrMovimientoSf0()
    {
        return sf0.format(getVrMovimiento());
    }

    public String getVrMovimientoDf2()
    {
        return df2.format(getVrMovimiento());
    }

    public String getVrMovimientoDf0()
    {
        return df0.format(getVrMovimiento());
    }

    public void setVrMovimiento( String vrMovimientoStr )
    {
        this.vrMovimiento = new Double(vrMovimientoStr).doubleValue();
    }

    public String getVrMovimientoStr()
    {
        return new Double(vrMovimiento).toString();
    }

    public double getVrDiferencia()
    {
        return  ( getVrCredito() - getVrCredito() );
    }

    public String getVrDiferenciaStr()
    {
        return new Double( getVrDebito() - getVrCredito() ).toString();
    }
    public void setIdComprobante( int idComprobante )
    {
        this.idComprobante = idComprobante ;
    }

    public void setIdComprobante( String  idComprobanteStr )
    {
        this.idComprobante =  new Integer(idComprobanteStr).intValue() ;
    }


    public int getIdComprobante()
    {
        return idComprobante;
    }

    public String getIdComprobanteStr()
    {
        return new Integer(idComprobante).toString();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}