package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el Bean de JhFormat
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa el Bean de Day
import com.solucionesweb.losbeans.utilidades.Day;

public class FachadaColaboraDctoOrdenBean implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private int idOrden;
    private String fechaOrden;
    private int estado;
    private String idCliente;
    private double idUsuario;
    private int idOrigen;
    private int idLog;
    private String fechaEntrega;
    private String strIdSucursal;
    private String strIdUsuario;
    private String tipoDcto;
    private String email;
    private String direccionDespacho;
    private String fax;
    private String contacto;
    private String observacion;
    private int cantidadArticulos;
    private double vrVentaSinIva;
    private double vrVentaConIva;
    private double vrIva;
    private double pesoTeoricoTotal;
    private String strNumeroOrden;
    private String nombreEstado;
    private int idLocalSugerido;
    private String ordenCompra;
    private String descuentoComercial;
    private int impuestoVenta;
    private String idRazon;
    private String ciudadDespacho;
    private int idEstadoTx;
    private int idTipoTx;
    private int numeroOrden;
    private int idDcto;
    private int diasPlazo;
    private String idDctoNitCC;
    private double vrCostoBase;
    private double vrCosto;
    private double vrCostoConIva;
    private double vrCostoSinIva;
    private double vrCostoRteFuente;
    private double vrCostoDescuento;
    private double vrCostoIva;
    private int indicador;
    private String fechaInicial;
    private String fechaFinal;
    private int numeroDctos;
    private double vrVentaSinDscto;
    private int diasHistoria;
    private int diasInventario;
    private String nombreTercero;
    private String nombreUsuario;
    private double vrCostoMV;
    private String nombreLocal;
    private double vrImpoconsumo;
    private double vrDescuento;
    private double vrDiferencia;
    private String nombrePlu;
    private String idLinea;
    private int idFormaPago;
    private String idTipoOrdenCadena;
    private double vrDiferenciaVrCostoSinIva;
    private double vrDiferenciaVrImpoconsumo;
    private double vrDiferenciaVrCostoIva;
    private double vrDiferenciaVrRteFuente;
    private int idOrdenOrigen;
    private String telefonoFijo;
    private String nombreEmpresa;
    private String ciudadTercero;
    private int item;
    private double cantidad;
    private String referenciaCliente;
    private int idFicha;
    private String idRemisionNitCC;
    private int idEstado;
    private double pesoTerminado;
    private double pesoPedido;
    private double pesoRetal;
    private double cantidadFacturada;
    private double cantidadTerminada;
    private double idVendedor;
    private String fechaDcto;
    private double vrFactura;
    private int indicadorInicial;
    private int indicadorFinal;
    private int idEstadoVisita;
    private int idLocalOrigen;
    private int idTipoOrdenOrigen;
    private int idPlu;
    private double vrCostoRteCree;
    private double vrDiferenciaVrRteCree;
    private double vrRteCree;
    private int presionaLiquidar;
    private double vrCostoRteIva;

    //
    char caracterRelleno = '0';
    JhFormat jhFormat = new JhFormat();

    //
    DecimalFormat df2 = new DecimalFormat("###,###,###,###.00");
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat sf0 = new DecimalFormat("############");
    DecimalFormat sf1 = new DecimalFormat("#########.0");

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

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(String idOrigenStr) {
        this.idOrigen = new Integer(idOrigenStr).intValue();
    }

    public String getIdOrigenStr() {
        return new Integer(idOrigen).toString();
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

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaOrden() {
        return fechaOrden.substring(0, 10);
    }

    public String getFechaOrdenCorta() {
        return getFechaOrden().substring(0, 4) + "/" +
                getFechaOrden().substring(5, 7) + "/" +
                getFechaOrden().substring(8, 10);
    }

    public String getFechaOrdenSqlServer() {
        return getFechaOrden().substring(0, 4) +
                getFechaOrden().substring(5, 7) +
                getFechaOrden().substring(8, 10);
    }

    public String getFechaOrdenStrOracle() {
        return "TO_DATE('" + getFechaOrden() + "','YYYY/MM/DD HH24:MI:SS')";
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaEntregaCorta() {
        return getFechaEntrega().substring(0, 4) + "/" +
                getFechaEntrega().substring(5, 7) + "/" +
                getFechaEntrega().substring(8, 10);
    }

    public String getFechaEntrega() {
        return fechaEntrega.substring(0, 10);
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

    public void setStrIdSucursal(String strIdSucursal) {
        this.strIdSucursal = strIdSucursal;
    }

    public String getStrIdSucursal() {
        return strIdSucursal;
    }

    public void setStrIdUsuario(String strIdUsuario) {
        this.strIdUsuario = strIdUsuario;
    }

    public String getStrIdUsuario() {
        return strIdUsuario;
    }

    public void setTipoDcto(String tipoDcto) {
        this.tipoDcto = tipoDcto;
    }

    public String getTipoDcto() {
        return tipoDcto;
    }

    public void setEmail(String email) {
        if (email == null) {
            this.email = STRINGVACIO;
        } else {
            this.email = email;
        }
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public void setDireccionDespacho(String direccionDespacho) {
        if (direccionDespacho == null) {
            this.direccionDespacho = STRINGVACIO;
        } else {
            this.direccionDespacho = direccionDespacho;
        }
    }

    public String getDireccionDespacho() {
        return direccionDespacho;
    }

    public void setFax(String fax) {
        if (fax == null) {
            this.fax = STRINGVACIO;
        } else {
            this.fax = fax;
        }
    }

    public String getFax() {
        return fax;
    }

    public void setContacto(String contacto) {
        if (contacto == null) {
            this.contacto = STRINGVACIO;
        } else {
            this.contacto = contacto;
        }
    }

    public String getContacto() {
        return contacto;
    }

    public void setObservacion(String observacion) {
        if (observacion == null) {
            this.observacion = STRINGVACIO;
        } else {
            this.observacion = observacion;
        }
    }

    public String getObservacion() {
        return observacion;
    }

    public String getObservacionMayuscula() {

        if (getObservacion()!= null ) return getObservacion().toUpperCase();

        return getObservacion();
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(String cantidadArticulosStr) {
        this.cantidadArticulos = new Integer(cantidadArticulosStr).intValue();
    }

    public String getCantidadArticulosStr() {
        return new Integer(cantidadArticulos).toString();
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

    public String getVrVentaSinIvaDf2() {
        return df2.format(getVrVentaSinIva());
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

    public String getVrVentaConIvaDf2() {
        return df2.format(getVrVentaConIva());
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

    public String getVrIvaDf2() {
        return df2.format(getVrIva());
    }

    public String getVrIvaSf0() {
        return sf0.format(getVrIva());
    }

    public String getVrIvaDf0() {
        return df0.format(getVrIva());
    }

    public void setPesoTeoricoTotal(double pesoTeoricoTotal) {
        this.pesoTeoricoTotal = pesoTeoricoTotal;
    }

    public double getPesoTeoricoTotal() {
        return pesoTeoricoTotal;
    }

    public void setPesoTeoricoTotal(String pesoTeoricoTotalStr) {
        this.pesoTeoricoTotal = new Double(pesoTeoricoTotalStr).doubleValue();
    }

    public String getPesoTeoricoTotalStr() {
        return new Double(pesoTeoricoTotal).toString();
    }

    public String getPesoTeoricoTotalDf0() {
        return df0.format(getPesoTeoricoTotal());
    }

    public void setStrNumeroOrden(String strNumeroOrden) {
        this.strNumeroOrden = strNumeroOrden;
    }

    public String getStrNumeroOrden() {

        return jhFormat.fill(getIdLocalStr(), caracterRelleno, 3, 0) + "-" +
                getTipoDcto() + "-" +
                jhFormat.fill(getIdOrdenStr(), caracterRelleno, 6, 0);
    }

    public void setNombreEstado(String nombreEstado) {
        if (nombreEstado == null) {
            this.nombreEstado = STRINGVACIO;
        } else {
            this.nombreEstado = nombreEstado;
        }
    }

    public String getNombreEstado() {
        return nombreEstado.toLowerCase();
    }

    public void setIdLocalSugerido(int idLocalSugerido) {
        this.idLocalSugerido = idLocalSugerido;
    }

    public int getIdLocalSugerido() {
        return idLocalSugerido;
    }

    public void setIdLocalSugerido(String idLocalSugeridoStr) {
        this.idLocalSugerido = new Integer(idLocalSugeridoStr).intValue();
    }

    public String getIdLocalSugeridoStr() {
        return new Integer(idLocalSugerido).toString();
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setImpuestoVenta(int impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public int getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(String impuestoVentaStr) {
        this.impuestoVenta = new Integer(impuestoVentaStr).intValue();
    }

    public String getImpuestoVentaStr() {
        return new Integer(impuestoVenta).toString();
    }

    public void setDescuentoComercial(String descuentoComercial) {
        this.descuentoComercial = descuentoComercial;
    }

    public String getDescuentoComercial() {
        return descuentoComercial;
    }

    public void setIdRazon(String idRazon) {
        this.idRazon = idRazon;
    }

    public String getIdRazon() {
        return idRazon;
    }

    public void setIdEstadoTx(int idEstadoTx) {
        this.idEstadoTx = idEstadoTx;
    }

    public int getIdEstadoTx() {
        return idEstadoTx;
    }

    public void setIdEstadoTx(String idEstadoTxStr) {
        this.idEstadoTx = new Integer(idEstadoTxStr).intValue();
    }

    public String getIdEstadoTxStr() {
        return new Integer(idEstadoTx).toString();
    }

    public void setIdTipoTx(int idTipoTx) {
        this.idTipoTx = idTipoTx;
    }

    public int getIdTipoTx() {
        return idTipoTx;
    }

    public void setIdTipoTx(String idTipoTxStr) {
        this.idTipoTx = new Integer(idTipoTxStr).intValue();
    }

    public String getIdTipoTxStr() {
        return new Integer(idTipoTx).toString();
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

    public void setCiudadDespacho(String ciudadDespacho) {
        if (ciudadDespacho == null) {
            this.ciudadDespacho = STRINGVACIO;
        } else {
            this.ciudadDespacho = ciudadDespacho;
        }
    }

    public String getCiudadDespacho() {
        return ciudadDespacho;
    }

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public void setIdDcto(String idDctoStr) {
        this.idTipoOrden = new Integer(idDctoStr).intValue();
    }

    public String getIdDctoStr() {
        return new Integer(getIdDcto()).toString();
    }

    public String getIdDctoDf0() {
        return df0.format(getIdDcto());
    }

    public int getDiasPlazo() {
        return diasPlazo;
    }

    public String getDiasPlazoStr() {
        return new Integer(getDiasPlazo()).toString();
    }

    public void setDiasPlazo(int diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public void setDiasPlazo(String diasPlazoStr) {
        this.idTipoOrden = new Integer(diasPlazoStr).intValue();
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public double getVrCosto() {
        return vrCosto;
    }

    public String getVrCostoStr() {
        return new Double(getVrCosto()).toString();
    }

    public void setVrCosto(double vrCosto) {
        this.vrCosto = vrCosto;
    }

    public void setVrCosto(String vrCostoStr) {
        this.vrCosto = new Double(getVrCosto()).doubleValue();
    }

    public void setVrCostoConIva(double vrCostoConIva) {
        this.vrCostoConIva = vrCostoConIva;
    }

    public double getVrCostoConIva() {
        return vrCostoConIva;
    }

    public void setVrCostoConIva(String vrCostoConIvaStr) {
        this.vrCostoConIva = new Double(vrCostoConIvaStr).doubleValue();
    }

    public String getVrCostoConIvaStr() {
        return new Double(getVrCostoConIva()).toString();
    }

    public String getVrCostoConIvaSf0() {
        return sf0.format(getVrCostoConIva());
    }

    public String getVrCostoConIvaDf2() {
        return df2.format(getVrCostoConIva());
    }

    public String getVrCostoConIvaDf0() {
        return df0.format(getVrCostoConIva());
    }

    public void setVrCostoSinIva(double vrCostoSinIva) {
        this.vrCostoSinIva = vrCostoSinIva;
    }

    public double getVrCostoSinIva() {
        return vrCostoSinIva;
    }

    public void setVrCostoSinIva(String vrCostoSinIvaStr) {
        this.vrCostoSinIva = new Double(vrCostoSinIvaStr).doubleValue();
    }

    public String getVrCostoSinIvaStr() {
        return new Double(vrCostoSinIva).toString();
    }

    public String getVrCostoSinIvaDf2() {
        return df2.format(getVrCostoSinIva());
    }

    public String getVrCostoSinIvaDf0() {
        return df0.format(getVrCostoSinIva());
    }

    public String getVrCostoSinIvaSf0() {
        return sf0.format(getVrCostoSinIva());
    }

    public double getVrCostoRteFuente() {
        return vrCostoRteFuente;
    }

    public String getVrCostoRteFuenteDf0() {
        return df0.format(getVrCostoRteFuente());
    }

    public String getVrCostoRteFuenteSf0() {
        return sf0.format(getVrCostoRteFuente());
    }

    public void setVrCostoRteFuente(double vrCostoRteFuente) {
        this.vrCostoRteFuente = vrCostoRteFuente;
    }

    public void setVrCostoRteFuente(String vrCostoRteFuenteStr) {
        this.vrCostoRteFuente = new Double(vrCostoRteFuenteStr).doubleValue();
    }

    public double getVrCostoMV() {
        return vrCostoMV;
    }

    public String getVrCostoMVDf0() {
        return df0.format(getVrCostoMV());
    }

    public String getVrCostoMVDf2() {
        return df2.format(getVrCostoMV());
    }

    public String getVrCostoMVSf0() {
        return sf0.format(getVrCostoMV());
    }

    public void setVrCostoMV(double vrCostoMV) {
        this.vrCostoMV = vrCostoMV;
    }

    public void setVrCostoDescuento(double vrCostoDescuento) {
        this.vrCostoDescuento = vrCostoDescuento;
    }

    public double getVrCostoDescuento() {
        return vrCostoDescuento;
    }

    public void setVrCostoDescuento(String vrCostoDescuentoStr) {
        this.vrCostoDescuento = new Double(vrCostoDescuentoStr).doubleValue();
    }

    public String getVrCostoDescuentoStr() {
        return new Double(vrCostoDescuento).toString();
    }

    public String getVrCostoDescuentoDf2() {
        return df2.format(getVrCostoDescuento());
    }

    public String getVrCostoDescuentoSf0() {
        return sf0.format(getVrCostoDescuento());
    }

    public double getVrCostoPago() {

        return (getVrCostoBase() -
                getVrCostoDescuento() -
                getVrCostoRteFuente() +
                getVrCostoIva());
    }

    public String getVrCostoPagoStr() {
        return new Double(getVrCostoPago()).toString();
    }

    public String getVrCostoPagoDf2() {
        return df2.format(getVrCostoPago());
    }

    public String getVrCostoPagoDf0() {
        return df0.format(getVrCostoPago());
    }

    public String getVrCostoPagoSf0() {
        return sf0.format(getVrCostoPago());
    }

    public double getVrPagarFactura() {

        return (getVrCostoSinIva() +
                getVrCostoIva()    +
                getVrImpoconsumo() -
                getVrDescuento() -
                getVrCostoRteFuente()-
                getVrCostoRteCree());
    }

    public String getVrPagarFacturaDf0() {
        return df0.format(getVrPagarFactura());
    }

    public double getVrCostoIva() {
        return vrCostoIva;
    }

    public String getVrCostoIvaDf0() {
        return df0.format(getVrCostoIva());
    }

    public String getVrCostoIvaSf0() {
        return sf0.format(getVrCostoIva());
    }

    public void setVrCostoIva(double vrCostoIva) {
        this.vrCostoIva = vrCostoIva;
    }

    public void setVrCostoIva(String vrCostoIvaStr) {
        this.vrCostoIva = new Double(vrCostoIvaStr).doubleValue();
    }

    public double getVrCostoBase() {
        return vrCostoBase;
    }

    public String getVrCostoBaseDf0() {
        return df0.format(getVrCostoBase());
    }

    public String getVrCostoBaseSf0() {
        return sf0.format(getVrCostoBase());
    }

    public void setVrCostoBase(double vrCostoBase) {
        this.vrCostoBase = vrCostoBase;
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

    public String getFechaInicialSqlServer() {
        return getFechaInicial().substring(0, 4) +
                getFechaInicial().substring(5, 7) +
                getFechaInicial().substring(8, 10);
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaFinalSqlServer() {
        return getFechaFinal().substring(0, 4) +
                getFechaFinal().substring(5, 7) +
                getFechaFinal().substring(8, 10);
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setNumeroDctos(int numeroDctos) {
        this.numeroDctos = numeroDctos;
    }

    public int getNumeroDctos() {
        return numeroDctos;
    }

    public void setNumeroDctos(String numeroDctosStr) {
        this.numeroDctos = new Integer(numeroDctosStr).intValue();
    }

    public String getNumeroDctosStr() {
        return new Integer(numeroDctos).toString();
    }

    public double getVrVentaSinDscto() {
        return vrVentaSinDscto;
    }

    public String getVrVentaSinDsctoDf0() {
        return df0.format(getVrVentaSinDscto());
    }

    public String getVrVentaSinDsctoSf0() {
        return sf0.format(getVrVentaSinDscto());
    }

    public void setVrVentaSinDscto(double vrVentaSinDscto) {
        this.vrVentaSinDscto = vrVentaSinDscto;
    }

    public void setDiasHistoria(int diasHistoria) {
        this.diasHistoria = diasHistoria;
    }

    public int getDiasHistoria() {
        return diasHistoria;
    }

    public void setDiasHistoria(String diasHistoriaStr) {
        this.diasHistoria = new Integer(diasHistoriaStr).intValue();
    }

    public String getDiasHistoriaStr() {
        return new Integer(diasHistoria).toString();
    }

    public void setDiasInventario(int diasInventario) {
        this.diasInventario = diasInventario;
    }

    public int getDiasInventario() {
        return diasInventario;
    }

    public void setDiasInventario(String diasInventarioStr) {
        this.diasInventario = new Integer(diasInventarioStr).intValue();
    }

    public String getDiasInventarioStr() {
        return new Integer(diasInventario).toString();
    }

    public void setNombreTercero(String nombreTercero) {
        if (nombreTercero == null) {
            this.nombreTercero = STRINGVACIO;
        } else {
            this.nombreTercero = nombreTercero;
        }
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreLocal(String nombreLocal) {
        if (nombreLocal == null) {
            this.nombreLocal = STRINGVACIO;
        } else {
            this.nombreLocal = nombreLocal;
        }
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null) {
            this.nombreUsuario = STRINGVACIO;
        } else {
            this.nombreUsuario = nombreUsuario;
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setVrImpoconsumo(double vrImpoconsumo) {
        this.vrImpoconsumo = vrImpoconsumo;
    }

    public double getVrImpoconsumo() {
        return vrImpoconsumo;
    }

    public String getVrImpoconsumoSf0() {
        return sf0.format(vrImpoconsumo);
    }


    public void setVrImpoconsumo(String vrImpoconsumoStr) {
        this.vrImpoconsumo = new Double(vrImpoconsumoStr).doubleValue();
    }

    public String getVrImpoconsumoStr() {
        return new Double(vrImpoconsumo).toString();
    }

    public String getVrImpoconsumoDf2() {
        return df2.format(getVrImpoconsumo());
    }

    public String getVrImpoconsumoDf0() {
        return df0.format(getVrImpoconsumo());
    }

    public void setVrDescuento(double vrDescuento) {
        this.vrDescuento = vrDescuento;
    }

    public double getVrDescuento() {
        return vrDescuento;
    }

     public String getVrDescuentoSf0() {
        return sf0.format(vrDescuento);
    }

    public void setVrDescuento(String vrDescuentoStr) {
        this.vrDescuento = new Double(vrDescuentoStr).doubleValue();
    }

    public String getVrDescuentoStr() {
        return new Double(vrDescuento).toString();
    }

    public String getVrDescuentoDf2() {
        return df2.format(getVrDescuento());
    }

    public String getVrDescuentoDf0() {
        return df0.format(getVrDescuento());
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }

    public void setVrDiferencia(double vrDiferencia) {
        this.vrDiferencia = vrDiferencia;
    }

    public double getVrDiferencia() {
        return vrDiferencia;
    }

    public void setVrDiferencia(String vrDiferenciaStr) {
        this.vrDiferencia = new Double(vrDiferenciaStr).doubleValue();
    }

    public String getVrDiferenciaStr() {
        return new Double(vrDiferencia).toString();
    }

    public String getVrDiferenciaDf2() {
        return df2.format(getVrDiferencia());
    }

    public String getVrDiferenciaDf0() {
        return df0.format(getVrDiferencia());
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public void setIdFormaPago(String idFormaPagoStr) {
        this.idFormaPago = new Integer(idFormaPagoStr).intValue();
    }

    public String getIdFormaPagoStr() {
        return new Integer(idFormaPago).toString();
    }

    public String getIdTipoOrdenCadena() {
        return idTipoOrdenCadena;
    }

    public void setIdTipoOrdenCadena(String idTipoOrdenCadena) {
        this.idTipoOrdenCadena = idTipoOrdenCadena;
    }

    public void setVrDiferenciaVrCostoSinIva(double vrDiferenciaVrCostoSinIva) {
        this.vrDiferenciaVrCostoSinIva = vrDiferenciaVrCostoSinIva;
    }

    public double getVrDiferenciaVrCostoSinIva() {
        return vrDiferenciaVrCostoSinIva;
    }

    public void setVrDiferenciaVrCostoSinIva(String vrDiferenciaVrCostoSinIvaStr) {
        this.vrDiferenciaVrCostoSinIva = new Double(vrDiferenciaVrCostoSinIvaStr).doubleValue();
    }

    public String getVrDiferenciaVrCostoSinIvaStr() {
        return new Double(vrDiferenciaVrCostoSinIva).toString();
    }

    public String getVrDiferenciaVrCostoSinIvaDf2() {
        return df2.format(getVrDiferenciaVrCostoSinIva());
    }

    public String getVrDiferenciaVrCostoSinIvaDf0() {
        return df0.format(getVrDiferenciaVrCostoSinIva());
    }

    public void setVrDiferenciaVrImpoconsumo(double vrDiferenciaVrImpoconsumo) {
        this.vrDiferenciaVrImpoconsumo = vrDiferenciaVrImpoconsumo;
    }

    public double getVrDiferenciaVrImpoconsumo() {
        return vrDiferenciaVrImpoconsumo;
    }

    public void setVrDiferenciaVrImpoconsumo(String vrDiferenciaVrImpoconsumoStr) {
        this.vrDiferenciaVrImpoconsumo = new Double(vrDiferenciaVrImpoconsumoStr).doubleValue();
    }

    public String getVrDiferenciaVrImpoconsumoStr() {
        return new Double(vrDiferenciaVrImpoconsumo).toString();
    }

    public String getVrDiferenciaVrImpoconsumoDf2() {
        return df2.format(getVrDiferenciaVrImpoconsumo());
    }

    public String getVrDiferenciaVrImpoconsumoDf0() {
        return df0.format(getVrDiferenciaVrImpoconsumo());
    }

    public void setVrDiferenciaVrCostoIva(double vrDiferenciaVrCostoIva) {
        this.vrDiferenciaVrCostoIva = vrDiferenciaVrCostoIva;
    }

    public double getVrDiferenciaVrCostoIva() {
        return vrDiferenciaVrCostoIva;
    }

    public void setVrDiferenciaVrCostoIva(String vrDiferenciaVrCostoIvaStr) {
        this.vrDiferenciaVrCostoIva = new Double(vrDiferenciaVrCostoIvaStr).doubleValue();
    }

    public String getVrDiferenciaVrCostoIvaStr() {
        return new Double(vrDiferenciaVrCostoIva).toString();
    }

    public String getVrDiferenciaVrCostoIvaDf2() {
        return df2.format(getVrDiferenciaVrCostoIva());
    }

    public String getVrDiferenciaVrCostoIvaDf0() {
        return df0.format(getVrDiferenciaVrCostoIva());
    }

    public void setVrDiferenciaVrRteFuente(double vrDiferenciaVrRteFuente) {
        this.vrDiferenciaVrRteFuente = vrDiferenciaVrRteFuente;
    }

    public double getVrDiferenciaVrRteFuente() {
        return vrDiferenciaVrRteFuente;
    }

    public void setVrDiferenciaVrRteFuente(String vrDiferenciaVrRteFuenteStr) {
        this.vrDiferenciaVrRteFuente = new Double(vrDiferenciaVrRteFuenteStr).doubleValue();
    }

    public String getVrDiferenciaVrRteFuenteStr() {
        return new Double(vrDiferenciaVrRteFuente).toString();
    }

    public String getVrDiferenciaVrRteFuenteDf2() {
        return df2.format(getVrDiferenciaVrRteFuente());
    }

    public String getVrDiferenciaVrRteFuenteDf0() {
        return df0.format(getVrDiferenciaVrRteFuente());
    }

    public int getIdOrdenOrigen() {
        return idOrdenOrigen;
    }

    public void setIdOrdenOrigen(int idOrdenOrigen) {
        this.idOrdenOrigen = idOrdenOrigen;
    }

    public String getIdOrdenOrigenStr() {
        return new Integer(idOrdenOrigen).toString();
    }

     public void setIdOrdenOrigen(String idOrdenOrigenStr) {
        this.idOrdenOrigen = new Integer(idOrdenOrigenStr).intValue();
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

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidadStr) {
        this.cantidad = new Double(cantidadStr).doubleValue();
    }

    public String getCantidadStr() {
        return new Double(cantidad).toString();
    }
    
    public String getCantidadDf0() {
        return df0.format(getCantidad());
    }

    public String getCantidadDf2() {
        return df2.format(getCantidad());
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

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public void setIdFicha(String idFichaStr) {
        this.idFicha = new Integer(idFichaStr).intValue();
    }

    public String getIdFichaStr() {
        return new Integer(idFicha).toString();
    }

    
    public int getPlazoEntrega() {
        
	   Day xFechaHoy = new Day();
           
           // Extrae el A�o, Mes y Dia
	   String anoStr = getFechaEntrega().substring(0,4);
	   String mesStr = getFechaEntrega().substring(5,7);
	   String diaStr = getFechaEntrega().substring(8,10);

           //
	   int anoInt = Integer.parseInt(anoStr);
	   int mesInt = Integer.parseInt(mesStr);
	   int diaInt = Integer.parseInt(diaStr);

           //
	   Day xFechaEntrega = new Day(anoInt,mesInt,diaInt);  

           //
           int xPlazoEntrega = xFechaEntrega.daysBetween(xFechaHoy);
      
           //
           return xPlazoEntrega;
           
    }

    public FachadaColaboraDctoOrdenBean() { }

    public String getIdRemisionNitCC() {
        return idRemisionNitCC;
    }

    public void setIdRemisionNitCC(String idRemisionNitCC) {
        this.idRemisionNitCC = idRemisionNitCC;
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

    public void setPesoTerminado( double pesoTerminado )
    {
        this.pesoTerminado = pesoTerminado ;
    }

    public double getPesoTerminado()
    {
        return pesoTerminado;
    }

    public String getPesoTerminadoDf2()
    {
        return df2.format(getPesoTerminado());
    }

    public String getPesoTerminadoDf0()
    {
        return df0.format(getPesoTerminado());
    }

    public int getPesoTerminadoInt()
    {
        return (int)getPesoTerminado();
    }

    public void setPesoTerminado( String pesoTerminadoStr )
    {
        this.pesoTerminado = new Double(pesoTerminadoStr).doubleValue();
    }

    public String getPesoTerminadoStr()
    {
        return new Double(pesoTerminado).toString();
    }

    public String getPesoTerminadoSf1()
    {
        return sf1.format(getPesoTerminado());
    }

    public void setPesoPedido( double pesoPedido )
    {
        this.pesoPedido = pesoPedido ;
    }

    public double getPesoPedido()
    {
        return pesoPedido;
    }

    public String getPesoPedidoDf2()
    {
        return df2.format(getPesoPedido());
    }

    public String getPesoPedidoDf0()
    {
        return df0.format(getPesoPedido());
    }

    public int getPesoPedidoInt()
    {
        return (int)getPesoPedido();
    }

    public void setPesoPedido( String pesoPedidoStr )
    {
        this.pesoPedido = new Double(pesoPedidoStr).doubleValue();
    }

    public String getPesoPedidoStr()
    {
        return new Double(pesoPedido).toString();
    }

    public String getPesoPedidoSf1()
    {
        return sf1.format(getPesoPedido());
    }

    public void setPesoRetal( double pesoRetal )
    {
        this.pesoRetal = pesoRetal ;
    }

    public double getPesoRetal()
    {
        return pesoRetal;
    }

    public String getPesoRetalDf2()
    {
        return df2.format(getPesoRetal());
    }

    public String getPesoRetalDf0()
    {
        return df0.format(getPesoRetal());
    }

    public int getPesoRetalInt()
    {
        return (int)getPesoRetal();
    }

    public void setPesoRetal( String pesoRetalStr )
    {
        this.pesoRetal = new Double(pesoRetalStr).doubleValue();
    }

    public String getPesoRetalStr()
    {
        return new Double(pesoRetal).toString();
    }

    public String getPesoRetalSf1()
    {
        return sf1.format(getPesoRetal());
    }

    public double getPesoPendiente()
    {
        return ( getPesoPedido() -
                 getPesoTerminado() ) ;
    }

    public String getPesoPendienteDf2()
    {
        return df2.format(getPesoPendiente());
    }

    public String getPesoPendienteDf0()
    {
        return df0.format(getPesoPendiente());
    }

    public void setCantidadFacturada(double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public void setCantidadFacturada(String cantidadFacturadaStr) {
        this.cantidadFacturada = new Double(cantidadFacturadaStr).doubleValue();
    }

    public String getCantidadFacturadaStr() {
        return new Double(cantidadFacturada).toString();
    }

    public String getCantidadFacturadaDf0() {
        return df0.format(getCantidadFacturada());
    }

    public double getCantidadPendiente() {
        return (getCantidad() - getCantidadFacturada());
    }


    public String getCantidadPendienteStr() {
        return new Double(getCantidadPendiente()).toString();
    }

    public String getCantidadPendienteDf0() {
        return df0.format(getCantidadPendiente());
    }

    public void setCantidadTerminada(double cantidadTerminada) {
        this.cantidadTerminada = cantidadTerminada;
    }

    public double getCantidadTerminada() {
        return cantidadTerminada;
    }

    public void setCantidadTerminada(String cantidadTerminadaStr) {
        this.cantidadTerminada = new Double(cantidadTerminadaStr).doubleValue();
    }

    public String getCantidadTerminadaStr() {
        return new Double(cantidadTerminada).toString();
    }

    public String getCantidadTerminadaDf0() {
        return df0.format(getCantidadTerminada());
    }

    public double getCantidadFacturadaPendiente() {
        return (getCantidadTerminada() - getCantidadFacturada());
    }

    public String getCantidadFacturadaPendienteStr() {
        return new Double(getCantidadFacturadaPendiente()).toString();
    }

    public String getCantidadFacturadaPendienteDf0() {
        return df0.format(getCantidadFacturadaPendiente());
    }

    public double getCantidadProduccionPendiente() {
        return (getCantidad() - getCantidadTerminada());
    }

    public String getCantidadProduccionPendienteStr() {
        return new Double(getCantidadProduccionPendiente()).toString();
    }

    public String getCantidadProduccionPendienteDf0() {
        return df0.format(getCantidadProduccionPendiente());
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

    public String getFechaDcto() {
        return fechaDcto;
    }

    public void setFechaDcto(String fechaDcto) {
        this.fechaDcto = fechaDcto;
    }

    public double getVrFactura() {
        return vrFactura;
    }

    public String getVrFacturaDf0() {
        return df0.format(getVrFactura());
    }

    public String getVrFacturaDf2() {
        return df2.format(getVrFactura());
    }

    public String getVrFacturaSf0() {
        return sf0.format(getVrFactura());
    }

    public void setVrFactura(double vrFactura) {
        this.vrFactura = vrFactura;
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

    public void setIdEstadoVisita(int idEstadoVisita) {
        this.idEstadoVisita = idEstadoVisita;
    }

    public int getIdEstadoVisita() {
        return idEstadoVisita;
    }

    public void setIdEstadoVisita(String idEstadoVisitaStr) {
        this.idEstadoVisita = new Integer(idEstadoVisitaStr).intValue();
    }

    public String getIdEstadoVisitaStr() {
        return new Integer(idEstadoVisita).toString();
    }
    
    public void setIdLocalOrigen(int idLocalOrigen) {
        this.idLocalOrigen = idLocalOrigen;
    }

    public int getIdLocalOrigen() {
        return idLocalOrigen;
    }

    public void setIdLocalOrigen(String idLocalOrigenStr) {
        this.idLocalOrigen = new Integer(idLocalOrigenStr).intValue();
    }

    public String getIdLocalOrigenStr() {
        return new Integer(idLocalOrigen).toString();
    }

    public void setIdTipoOrdenOrigen(int idTipoOrdenOrigen) {
        this.idTipoOrdenOrigen = idTipoOrdenOrigen;
    }

    public int getIdTipoOrdenOrigen() {
        return idTipoOrdenOrigen;
    }

    public void setIdTipoOrdenOrigen(String idTipoOrdenOrigenStr) {
        this.idTipoOrdenOrigen = new Integer(idTipoOrdenOrigenStr).intValue();
    }

    public String getIdTipoOrdenOrigenStr() {
        return new Integer(idTipoOrdenOrigen).toString();
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
    
    
    public double getVrCostoRteCree() {
        return vrCostoRteCree;
    }

    public String getVrCostoRteCreeDf0() {
        return df0.format(getVrCostoRteCree());
    }

    public String getVrCostoRteCreeSf0() {
        return sf0.format(getVrCostoRteCree());
    }

    public void setVrCostoRteCree(double vrCostoRteCree) {
        this.vrCostoRteCree = vrCostoRteCree;
    }

    public void setVrCostoRteCree(String vrCostoRteCreeStr) {
        this.vrCostoRteCree = new Double(vrCostoRteCreeStr).doubleValue();
    }

    public void setVrDiferenciaVrRteCree(double vrDiferenciaVrRteCree) {
        this.vrDiferenciaVrRteCree = vrDiferenciaVrRteCree;
    }

    public double getVrDiferenciaVrRteCree() {
        return vrDiferenciaVrRteCree;
    }

    public void setVrDiferenciaVrRteCree(String vrDiferenciaVrRteCreeStr) {
        this.vrDiferenciaVrRteCree = new Double(vrDiferenciaVrRteCreeStr).doubleValue();
    }

    public String getVrDiferenciaVrRteCreeStr() {
        return new Double(vrDiferenciaVrRteCree).toString();
    }

    public String getVrDiferenciaVrRteCreeDf2() {
        return df2.format(getVrDiferenciaVrRteCree());
    }

    public String getVrDiferenciaVrRteCreeDf0() {
        return df0.format(getVrDiferenciaVrRteCree());
    }

    public double getVrRteCree() {
        return vrRteCree;
    }

    public String getVrRteCreeDf0() {
        return df0.format(getVrRteCree());
    }

    public String getVrRteCreeSf0() {
        return sf0.format(getVrRteCree());
    }

    public void setVrRteCree(double vrRteCree) {
        this.vrRteCree = vrRteCree;
    }

    public void setVrRteCree(String vrRteCreeStr) {
        this.vrRteCree = new Double(vrRteCreeStr).doubleValue();
    }

    public void setPresionaLiquidar(int presionaLiquidar) {
        this.presionaLiquidar = presionaLiquidar;
    }

    public int getPresionaLiquidar() {
        return presionaLiquidar;
    }

    public void setPresionaLiquidar(String presionaLiquidarStr) {
        this.presionaLiquidar = new Integer(presionaLiquidarStr).intValue();
    }

    public String getPresionaLiquidarStr() {
        return new Integer(presionaLiquidar).toString();
    }

    public double getVrCostoRteIva() {
        return vrCostoRteIva;
    }

    public String getVrCostoRteIvaDf0() {
        return df0.format(getVrCostoRteIva());
    }

    public String getVrCostoRteIvaSf0() {
        return sf0.format(getVrCostoRteIva());
    }

    public void setVrCostoRteIva(double vrCostoRteIva) {
        this.vrCostoRteIva = vrCostoRteIva;
    }

    public void setVrCostoRteIva(String vrCostoRteIvaStr) {
        this.vrCostoRteIva = new Double(vrCostoRteIvaStr).doubleValue();
    }

}