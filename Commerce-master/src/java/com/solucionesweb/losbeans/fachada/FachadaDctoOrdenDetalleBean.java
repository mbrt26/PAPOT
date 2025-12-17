package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;

public class FachadaDctoOrdenDetalleBean implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private int idOrden;
    private double cantidad;
    private String nombrePlu;
    private int idPlu;
    private int idTipo;
    private int estado;
    private double porcentajeIva;
    private double vrVentaUnitario;
    private double ean;
    private double vrVentaOriginal;
    private double vrCosto;
    private double vrDsctoPie;
    private double porcentajeDscto;
    private double cantidadPedida;
    private double vrCostoNegociado;
    private double vrCostoResurtido;
    private String strIdBodega;
    private String strIdLista;
    private String strIdReferencia;
    private double pesoTeorico;
    private int idLocalSugerido;
    private String idBodegaSugerido;
    private int cantidadArticulos;
    private double vrVentaSinIva;
    private double vrVentaConIva;
    private double pesoTeoricoTotal;
    private String nombreUnidadMedida;
    private String marcaArteCliente;
    private String referenciaCliente;
    private String comentario;
    private int item;
    private int itemPadre;
    private int idEstadoTx;
    private int idTipoTx;
    private String idReferenciaOriginal;
    private int idEstadoRefOriginal;
    private int idClasificacion;
    private int idResponsable;
    private String fechaEntrega;
    private String nombreMarca;
    private String nombreCategoria;
    private double vrCostoActual;
    private double vrCostoConIva;
    private double vrCostoSinIva;
    private double existencia;
    private int idLog;
    private double vrIvaVenta;
    private double vrVentaSinDscto;
    private String nombreLinea;
    private String idCliente;
    private String fechaOrden;
    private String strIdSucursal;
    private double idUsuario;
    private double descuentoComercial;
    private double existenciaActual;
    private double acumuladoVenta;
    private double cantidadVentaPendiente;
    private double cantidadTrasladoPendiente;
    private double cantidadDespachoPendiente;
    private double cantidadPvd;
    private double cantidadInventarioMaximo;
    private double cantidadPedidoSugerido;
    private double cantidadCompraPendiente;
    private double cantidadPedido;
    private double vrCostoPedido;
    private int idBodega;
    private double cantidadAjuste;
    private double vrImpoconsumo;
    private double vrIvaCosto;
    private double vrCostoIND;
    private double vrIvaResurtido;
    private int factorDespacho;
    private String idSubcuenta;
    private double existenciaBodega;
    private double cantidadBonificada;
    private String fechaInicial;
    private int diasHistoria;
    private int rotacion;
    private String fechaInicialStr;
    private String fechaFinalStr;
    private String fechaFinal;
    private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    private int indicadorInicial;
    private int indicadorFinal;
    private double vrGeneral;
    private double vrMayorista;
    private int idOrdenOrigen;
    private int idLocalOrigen;
    private int idTipoOrdenOrigen;
    private double unidadVenta;
    private String referencia;
    private int idFicha;
    private int idOperacion;
    private int itemOrden;
    private int numeroOrden;
    private double vrVentaUnitarioSinIva;
    private double pesoTerminado;
    private double pesoPedido;
    private double pesoRetal;
    private double cantidadFacturada;
    private double cantidadPendiente;
    private String ordenCompra;
    private double pesoFacturado;
    private double pesoPendiente;
    private double cantidadTerminada;
    private double cantidadEntregada;
    private double pesoEntregado;
    private String observacion;
    private String nombreTercero;
    private double vrFactura;
    private double idDcto;
    private int indicador;
    private String fechaDcto;
    private String nombreBodega;
    private int unidadDian;

    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();

    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0", simbolos);
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat sf0 = new DecimalFormat("#########");
    DecimalFormat sf1 ;
    DecimalFormat sf2 = new DecimalFormat("#########.00");

    public int getUnidadDian() {
        return unidadDian;
    }

    public void setUnidadDian(int unidadDian) {
        this.unidadDian = unidadDian;
    }
    
  
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
        return new Integer(getIdLocal()).toString();
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

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getCantidadDf2() {
        return df2.format(getCantidad());
    }

    public String getCantidadDf0() {
        return df0.format(getCantidad());
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

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePluStr(String nombrePlu) {
        this.nombrePlu = nombrePlu;
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

    public String getPorcentajeIvaDf0() {
        return df0.format(getPorcentajeIva());
    }

    public void setVrVentaUnitario(double vrVentaUnitario) {
        this.vrVentaUnitario = vrVentaUnitario;
    }

    public double getVrVentaUnitario() {
        return vrVentaUnitario;
    }

    public String getVrVentaUnitarioSf0() {
        return sf0.format(getVrVentaUnitario());
    }

    public String getVrVentaUnitarioDf2() {
        return df2.format(getVrVentaUnitario());
    }

    public String getVrVentaUnitarioDf0() {
        return df0.format(getVrVentaUnitario());
    }

    public void setVrVentaUnitario(String vrVentaUnitarioStr) {
        this.vrVentaUnitario = new Double(vrVentaUnitarioStr).doubleValue();
    }

    public String getVrVentaUnitarioStr() {
        return new Double(vrVentaUnitario).toString();
    }

    public void setEan(double ean) {
        this.ean = ean;
    }

    public double getEan() {
        return ean;
    }

    public void setEan(String eanStr) {
        this.ean = new Double(eanStr).doubleValue();
    }

    public String getEanStr() {
        return new Double(ean).toString();
    }

    public void setVrVentaOriginal(double vrVentaOriginal) {

        //
        this.vrVentaOriginal = vrVentaOriginal;

        //
        if (vrVentaOriginal == 0.0) {
            this.vrVentaOriginal = 1.0;
        }

    }

    public double getVrVentaOriginal() {
        return vrVentaOriginal;
    }

    public void setVrVentaOriginal(String vrVentaOriginalStr) {
        this.vrVentaOriginal = new Double(vrVentaOriginalStr).doubleValue();
    }

    public String getVrVentaOriginalStr() {
        return new Double(vrVentaOriginal).toString();
    }

    public String getVrVentaOriginalDf2() {
        return df2.format(getVrVentaOriginal());
    }

    public void setVrCosto(double vrCosto) {
        this.vrCosto = vrCosto;
    }

    public double getVrCosto() {
        return vrCosto;
    }

    public void setVrCosto(String vrCostoStr) {
        this.vrCosto = new Double(vrCostoStr).doubleValue();
    }

    public String getVrCostoStr() {
        return new Double(vrCosto).toString();
    }

    public String getVrCostoDf2() {
        return df2.format(getVrCosto());
    }

    public String getVrCostoSf0() {
        return sf0.format(getVrCosto());
    }

    public String getVrCostoSf2() {
        return sf2.format(new Double((double) (int) (getVrCosto() * 100.0) / 100));

    }

    public String getVrCostoDf0() {
        return df0.format(getVrCosto());
    }

    public String getVrCostoDecimal() {

        BigDecimal vrCostoDecimal
                = new BigDecimal(getVrCosto());

        return (vrCostoDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());

    }

    public void setVrDsctoPie(double vrDsctoPie) {
        this.vrDsctoPie = vrDsctoPie;
    }

    public double getVrDsctoPie() {
        return vrDsctoPie;
    }

    public void setVrDsctoPie(String vrDsctoPieStr) {
        this.vrDsctoPie = new Double(vrDsctoPieStr).doubleValue();
    }

    public String getVrDsctoPieStr() {
        return new Double(vrDsctoPie).toString();
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

    public void setCantidadPedida(double cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public double getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(String cantidadPedidaStr) {
        this.cantidadPedida = new Double(cantidadPedidaStr).doubleValue();
    }

    public String getCantidadPedidaStr() {
        return new Double(cantidadPedida).toString();
    }

    public void setStrIdBodega(String strIdBodega) {
        this.strIdBodega = strIdBodega;
    }

    public String getStrIdBodega() {
        return strIdBodega;
    }

    public void setStrIdLista(String strIdLista) {
        this.strIdLista = strIdLista;
    }

    public String getStrIdLista() {
        return strIdLista;
    }

    public void setStrIdReferencia(String strIdReferencia) {
        this.strIdReferencia = strIdReferencia;
    }

    public String getStrIdReferencia() {
        return strIdReferencia;
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

    public String getVrVentaSinIvaDf0() {
        return df0.format(getVrVentaSinIva());
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
        return vrVentaConIva
                + vrIvaVenta
                + vrImpoconsumo;
    }

    public void setVrVentaConIva(String vrVentaConIvaStr) {
        this.vrVentaConIva = new Double(vrVentaConIvaStr).doubleValue();
    }

    public String getVrVentaConIvaStr() {
        return new Double(getVrVentaSinDscto()
                + getVrIvaVenta()
                + getVrImpoconsumo()).toString();
    }

    public String getVrVentaConIvaDf2() {
        return df2.format(getVrVentaSinDscto()
                + getVrIvaVenta()
                + getVrImpoconsumo());
    }

    public String getVrVentaConIvaDf0() {
        return df0.format(getVrVentaSinDscto()
                + getVrIvaVenta()
                + getVrImpoconsumo());
    }

    public String getVrVentaConIvaSf0() {
        return sf0.format(getVrVentaSinDscto()
                + getVrIvaVenta()
                + getVrImpoconsumo());
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

    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    public void setPesoTeorico(double pesoTeorico) {
        this.pesoTeorico = pesoTeorico;
    }

    public double getPesoTeorico() {
        return pesoTeorico;
    }

    public void setPesoTeorico(String pesoTeoricoStr) {
        this.pesoTeorico = new Double(pesoTeoricoStr).doubleValue();
    }

    public String getPesoTeoricoStr() {
        return new Double(pesoTeorico).toString();
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

    public void setIdBodegaSugerido(String idBodegaSugerido) {
        this.idBodegaSugerido = idBodegaSugerido;
    }

    public String getIdBodegaSugerido() {
        return idBodegaSugerido;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getItem() {
        return item;
    }

    public String getMarcaArteCliente() {
        return marcaArteCliente;
    }

    public void setMarcaArteCliente(String marcaArteCliente) {
        if (marcaArteCliente == null) {
            this.marcaArteCliente = STRINGVACIO;
        } else {
            this.marcaArteCliente = marcaArteCliente.trim();
        }
    }

    public void setReferenciaCliente(String referenciaCliente) {
        if (referenciaCliente == null) {
            this.referenciaCliente = STRINGVACIO;
        } else {
            this.referenciaCliente = referenciaCliente;
        }
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        if (comentario == null) {
            this.comentario = STRINGVACIO;
        } else {
            this.comentario = comentario.trim().toUpperCase();
        }
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public String getItemStr() {
        return new Integer(item).toString();
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

    public String getNombreArchivo() {

        int Longitud = getMarcaArteCliente().trim().length();
        int indiceSeparador1 = getMarcaArteCliente().indexOf("/", 0);

        //
        return getMarcaArteCliente().substring(indiceSeparador1 + 1, Longitud).trim();

    }

    public void setIdReferenciaOriginal(String idReferenciaOriginal) {
        this.idReferenciaOriginal = idReferenciaOriginal;
    }

    public String getIdReferenciaOriginal() {
        return idReferenciaOriginal;
    }

    public void setIdEstadoRefOriginal(int idEstadoRefOriginal) {
        this.idEstadoRefOriginal = idEstadoRefOriginal;
    }

    public int getIdEstadoRefOriginal() {
        return idEstadoRefOriginal;
    }

    public void setIdEstadoRefOriginal(String idEstadoRefOriginalStr) {
        this.idEstadoRefOriginal = new Integer(idEstadoRefOriginalStr).intValue();
    }

    public String getIdEstadoRefOriginalStr() {
        return new Integer(idEstadoRefOriginal).toString();
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(String idClasificacionStr) {
        this.idClasificacion = new Integer(idClasificacionStr).intValue();
    }

    public String getIdClasificacionStr() {
        return new Integer(idClasificacion).toString();
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(String idResponsableStr) {
        this.idResponsable = new Integer(idResponsableStr).intValue();
    }

    public String getIdResponsableStr() {
        return new Integer(idResponsable).toString();
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public String getFechaEntregaCorta() {

        //
        return getFechaEntrega().substring(0, 4) + "/"
                + getFechaEntrega().substring(5, 7) + "/"
                + getFechaEntrega().substring(8, 10);
    }

    public String getFechaEntregaSqlServer() {

        return getFechaEntrega().substring(0, 4)
                + getFechaEntrega().substring(5, 7)
                + getFechaEntrega().substring(8, 10);
    }

    public String getPorcentajeDescuentoDf2() {
        return df2.format(getPorcentajeDscto());
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public void setVrCostoActual(double vrCostoActual) {
        this.vrCostoActual = vrCostoActual;
    }

    public double getVrCostoActual() {
        return vrCostoActual;
    }

    public void setVrCostoActual(String vrCostoActualStr) {
        this.vrCostoActual = new Double(vrCostoActualStr).doubleValue();
    }

    public String getVrCostoActualStr() {
        return new Double(vrCostoActual).toString();
    }

    public String getVrCostoActualDf2() {
        return df2.format(getVrCostoActual());
    }

    public double getIncrementoCosto() {
        return ((getVrCosto() - getVrCostoActual())
                / getVrCostoActual()) * 100;
    }

    public String getIncrementoCostoDf2() {
        return df2.format(getIncrementoCosto());
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
        return new Double(vrCostoConIva).toString();
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

    public double getVrSubtotalCosto() {
        return (getCantidad() * getVrCosto());
    }

    public String getVrSubtotalCostoStr() {
        return new Double(getCantidad() * getVrCosto()).toString();
    }

    public String getVrSubtotalCostoDf2() {
        return df2.format(getCantidad() * getVrCosto());
    }

    public String getVrSubtotalCostoDf0() {
        return df0.format(getCantidad() * getVrCosto());
    }

    public String getVrSubtotalCostoSf0() {
        return sf0.format(getCantidad() * getVrCosto());
    }

    public String getVrSubtotalCostoSf2() {
        return sf2.format(getCantidad() * getVrCosto());
    }

    public double getVrSubtotalVenta() {
        return (getCantidad() * getVrVentaUnitario());
    }

    public String getVrSubtotalVentaStr() {
        return new Double(getCantidad() * getVrVentaUnitario()).toString();
    }

    public String getVrSubtotalVentaDf2() {
        return df2.format(getCantidad() * getVrVentaUnitario());
    }

    public String getVrSubtotalVentaDf0() {
        return df0.format(getCantidad() * getVrVentaUnitario());
    }

    public String getVrSubtotalVentaSf0() {
        return sf0.format(getCantidad() * getVrVentaUnitario());
    }

    public String getVrSubtotalVentaSf2() {
        return sf2.format(getCantidad() * getVrVentaUnitario());
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(String existenciaStr) {
        this.existencia = new Double(existenciaStr).doubleValue();
    }

    public String getExistenciaStr() {
        return new Double(existencia).toString();
    }

    public String getExistenciaDf2() {
        return df2.format(getExistencia());
    }

    public String getExistenciaDf0() {
        return df0.format(getExistencia());
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
        return new Integer(getIdLog()).toString();
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

    public String getVrVentaSinDsctoDf2() {
        return df2.format(getVrVentaSinDscto());
    }

    public String getVrVentaSinDsctoDf0() {
        return df0.format(getVrVentaSinDscto());
    }

    public String getVrVentaSinDsctoSf0() {
        return sf0.format(getVrVentaSinDscto());
    }

    public void setVrIvaVenta(double vrIvaVenta) {
        this.vrIvaVenta = vrIvaVenta;
    }

    public double getVrIvaVenta() {
        return vrIvaVenta;
    }

    public void setVrIvaVenta(String vrIvaVentaStr) {
        this.vrIvaVenta = new Double(vrIvaVentaStr).doubleValue();
    }

    public String getVrIvaVentaStr() {
        return new Double(vrIvaVenta).toString();
    }

    public String getVrIvaVentaDf2() {
        return df2.format(getVrIvaVenta());
    }

    public String getVrIvaVentaDf0() {
        return df0.format(getVrIvaVenta());
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public FachadaDctoOrdenDetalleBean() {
        simbolos.setDecimalSeparator('.');
        sf1= new DecimalFormat("#########.0",simbolos);
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getStrIdSucursal() {
        return strIdSucursal;
    }

    public void setStrIdSucursal(String strIdSucursal) {
        this.strIdSucursal = strIdSucursal;
    }

    public double getIdUsuario() {
        return idUsuario;
    }

    public String getIdUsuarioStr() {
        return new Double(getIdUsuario()).toString();
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setDescuentoComercial(double descuentoComercial) {
        this.descuentoComercial = descuentoComercial;
    }

    public double getDescuentoComercial() {
        return descuentoComercial;
    }

    public void setDescuentoComercial(String descuentoComercialStr) {
        this.descuentoComercial = new Double(descuentoComercialStr).doubleValue();
    }

    public String getDescuentoComercialStr() {
        return new Double(descuentoComercial).toString();
    }

    public String getDescuentoComercialDf0() {
        return df0.format(getDescuentoComercial());
    }

    public void setCantidadVentaPendiente(double cantidadVentaPendiente) {
        this.cantidadVentaPendiente = cantidadVentaPendiente;
    }

    public double getCantidadVentaPendiente() {
        return cantidadVentaPendiente;
    }

    public String getCantidadVentaPendienteDf0() {
        return df0.format(getCantidadVentaPendiente());
    }

    public void setCantidadVentaPendiente(String cantidadVentaPendienteStr) {
        this.cantidadVentaPendiente = new Double(cantidadVentaPendienteStr).doubleValue();
    }

    public String getCantidadVentaPendienteStr() {
        return new Double(cantidadVentaPendiente).toString();
    }

    public void setCantidadTrasladoPendiente(double cantidadTrasladoPendiente) {
        this.cantidadTrasladoPendiente = cantidadTrasladoPendiente;
    }

    public double getCantidadTrasladoPendiente() {
        return cantidadTrasladoPendiente;
    }

    public String getCantidadTrasladoPendienteDf0() {
        return df0.format(getCantidadTrasladoPendiente());
    }

    public void setCantidadTrasladoPendiente(String cantidadTrasladoPendienteStr) {
        this.cantidadTrasladoPendiente = new Double(cantidadTrasladoPendienteStr).doubleValue();
    }

    public String getCantidadTrasladoPendienteStr() {
        return new Double(cantidadTrasladoPendiente).toString();
    }

    public void setCantidadDespachoPendiente(double cantidadDespachoPendiente) {
        this.cantidadDespachoPendiente = cantidadDespachoPendiente;
    }

    public double getCantidadDespachoPendiente() {
        return cantidadDespachoPendiente;
    }

    public String getCantidadDespachoPendienteDf0() {
        return df0.format(getCantidadDespachoPendiente());
    }

    public void setCantidadDespachoPendiente(String cantidadDespachoPendienteStr) {
        this.cantidadDespachoPendiente = new Double(cantidadDespachoPendienteStr).doubleValue();
    }

    public String getCantidadDespachoPendienteStr() {
        return new Double(cantidadDespachoPendiente).toString();
    }

    public void setAcumuladoVenta(double acumuladoVenta) {
        this.acumuladoVenta = acumuladoVenta;
    }

    public double getAcumuladoVenta() {
        return acumuladoVenta;
    }

    public String getAcumuladoVentaDf0() {
        return df0.format(getAcumuladoVenta());
    }

    public void setAcumuladoVenta(String acumuladoVentaStr) {
        this.acumuladoVenta = new Double(acumuladoVentaStr).doubleValue();
    }

    public String getAcumuladoVentaStr() {
        return new Double(acumuladoVenta).toString();
    }

    public void setExistenciaActual(double existenciaActual) {
        this.existenciaActual = existenciaActual;
    }

    public double getExistenciaActual() {
        return existenciaActual;
    }

    public String getExistenciaActualDf0() {
        return df0.format(getExistenciaActual());
    }

    public void setExistenciaActual(String existenciaActualStr) {
        this.existenciaActual = new Double(existenciaActualStr).doubleValue();
    }

    public String getExistenciaActualStr() {
        return new Double(existenciaActual).toString();
    }

    public void setExistenciaBodega(double existenciaBodega) {
        this.existenciaBodega = existenciaBodega;
    }

    public double getExistenciaBodega() {
        return existenciaBodega;
    }

    public String getExistenciaBodegaDf0() {
        return df0.format(getExistenciaBodega());
    }

    public void setExistenciaBodega(String existenciaBodegaStr) {
        this.existenciaBodega = new Double(existenciaBodegaStr).doubleValue();
    }

    public String getExistenciaBodegaStr() {
        return new Double(existenciaBodega).toString();
    }

    public void setCantidadPvd(double cantidadPvd) {
        this.cantidadPvd = cantidadPvd;
    }

    public double getCantidadPvd() {
        return cantidadPvd;
    }

    public String getCantidadPvdDf0() {
        return df0.format(getCantidadPvd());
    }

    public String getCantidadPvdDf2() {
        return df2.format(getCantidadPvd());
    }

    public void setCantidadPvd(String cantidadPvdStr) {
        this.cantidadPvd = new Double(cantidadPvdStr).doubleValue();
    }

    public String getCantidadPvdStr() {
        return new Double(cantidadPvd).toString();
    }

    public void setCantidadInventarioMaximo(double cantidadInventarioMaximo) {
        this.cantidadInventarioMaximo = cantidadInventarioMaximo;
    }

    public double getCantidadInventarioMaximo() {
        return cantidadInventarioMaximo;
    }

    public String getCantidadInventarioMaximoDf0() {
        return df0.format(getCantidadInventarioMaximo());
    }

    public void setCantidadInventarioMaximo(String cantidadInventarioMaximoStr) {
        this.cantidadInventarioMaximo = new Double(cantidadInventarioMaximoStr).doubleValue();
    }

    public String getCantidadInventarioMaximoStr() {
        return new Double(cantidadInventarioMaximo).toString();
    }

    public void setCantidadPedidoSugerido(double cantidadPedidoSugerido) {
        this.cantidadPedidoSugerido = cantidadPedidoSugerido;
    }

    public double getCantidadPedidoSugerido() {
        return cantidadPedidoSugerido;
    }

    public String getCantidadPedidoSugeridoDf0() {
        return df0.format(getCantidadPedidoSugerido());
    }

    public void setCantidadPedidoSugerido(String cantidadPedidoSugeridoStr) {
        this.cantidadPedidoSugerido = new Double(cantidadPedidoSugeridoStr).doubleValue();
    }

    public String getCantidadPedidoSugeridoStr() {
        return new Double(cantidadPedidoSugerido).toString();
    }

    public void setCantidadCompraPendiente(double cantidadCompraPendiente) {
        this.cantidadCompraPendiente = cantidadCompraPendiente;
    }

    public double getCantidadCompraPendiente() {
        return cantidadCompraPendiente;
    }

    public String getCantidadCompraPendienteDf0() {
        return df0.format(getCantidadCompraPendiente());
    }

    public void setCantidadCompraPendiente(String cantidadCompraPendienteStr) {
        this.cantidadCompraPendiente = new Double(cantidadCompraPendienteStr).doubleValue();
    }

    public String getCantidadCompraPendienteStr() {
        return new Double(cantidadCompraPendiente).toString();
    }

    public void setCantidadPedido(double cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public double getCantidadPedido() {
        return cantidadPedido;
    }

    public String getCantidadPedidoStr() {
        return new Double(getCantidadPedido()).toString();
    }

    public String getCantidadPedidoDf0() {
        return df0.format(getCantidadPedido());
    }

    public String getCantidadPedidoSf0() {
        return sf0.format(getCantidadPedido());
    }

    public String getCantidadPedidoDf2() {
        return df2.format(getCantidadPedido());
    }

    public void setCantidadPedido(String cantidadPedidoStr) {
        this.cantidadPedido = new Double(cantidadPedidoStr).doubleValue();
    }

    public void setVrCostoPedido(double vrCostoPedido) {
        this.vrCostoPedido = vrCostoPedido;
    }

    public double getVrCostoPedido() {
        return vrCostoPedido;
    }

    public String getVrCostoPedidoDf0() {
        return df0.format(getVrCostoPedido());
    }

    public String getVrCostoPedidoStr() {
        return new Double(getVrCostoPedido()).toString();
    }

    public void setVrCostoPedido(String vrCostoPedidoStr) {
        this.vrCostoPedido = new Double(vrCostoPedidoStr).doubleValue();
    }

    public void setVrCostoResurtido(double vrCostoResurtido) {
        this.vrCostoResurtido = vrCostoResurtido;
    }

    public double getVrCostoResurtido() {
        return vrCostoResurtido;
    }

    public void setVrCostoResurtido(String vrCostoResurtidoStr) {
        this.vrCostoResurtido = new Double(vrCostoResurtidoStr).doubleValue();
    }

    public String getVrCostoResurtidoStr() {
        return new Double(vrCostoResurtido).toString();
    }

    public String getVrCostoResurtidoDf2() {
        return df2.format(getVrCostoResurtido());
    }

    public String getVrCostoResurtidoSf2() {
        return sf2.format(getVrCostoResurtido());
    }

    public String getVrCostoResurtidoSf0() {
        return sf0.format(getVrCostoResurtido());
    }

    public String getVrCostoResurtidoDf0() {
        return df0.format(getVrCostoResurtido());
    }

    public double getVrCostoNegociado() {
        return vrCostoNegociado;
    }

    public String getVrCostoNegociadoSf2() {
        return sf2.format(getVrCostoNegociado());
    }

    public void setVrCostoNegociado(double vrCostoNegociado) {
        this.vrCostoNegociado = vrCostoNegociado;
    }

    public void setVrCostoNegociado(String vrCostoNegociadoStr) {
        this.vrCostoNegociado = new Double(vrCostoNegociadoStr).doubleValue();
    }

    public String getVrCostoNegociadoStr() {
        return new Double((double) (int) (vrCostoNegociado * 100) / 100).toString();
    }

    public String getVrCostoNegociadoSf0() {
        return sf0.format(getVrCostoNegociado());
    }

    public String getVrCostoNegociadoDf2() {
        return df2.format(getVrCostoNegociado());
    }

    public String getVrCostoNegociadoDf0() {
        return df0.format(getVrCostoNegociado());
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
        return new Integer(getIdBodega()).toString();
    }

    public void setCantidadAjuste(double cantidadAjuste) {
        this.cantidadAjuste = cantidadAjuste;
    }

    public double getCantidadAjuste() {
        return cantidadAjuste;
    }

    public String getCantidadAjusteDf0() {
        return df0.format(getCantidadAjuste());
    }

    public String getCantidadAjusteDf2() {
        return df2.format(getCantidadAjuste());
    }

    public void setCantidadAjuste(String cantidadAjusteStr) {
        this.cantidadAjuste = new Double(cantidadAjusteStr).doubleValue();
    }

    public String getCantidadAjusteStr() {
        return new Double(cantidadAjuste).toString();
    }

    public void setVrImpoconsumo(double vrImpoconsumo) {
        this.vrImpoconsumo = vrImpoconsumo;
    }

    public double getVrImpoconsumo() {
        return vrImpoconsumo;
    }

    public String getVrImpoconsumoSf0() {
        return sf0.format(getVrImpoconsumo());
    }

    public String getVrImpoconsumoDf2() {
        return df2.format(getVrImpoconsumo());
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

    public String getVrImpoconsumoDecimal() {

        BigDecimal vrImpoconsumoDecimal
                = new BigDecimal(getVrImpoconsumo());

        return (vrImpoconsumoDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public void setVrIvaCosto(double vrIvaCosto) {
        this.vrIvaCosto = vrIvaCosto;
    }

    public double getVrIvaCosto() {
        return vrIvaCosto;
    }

    public void setVrIvaCosto(String vrIvaCostoStr) {
        this.vrIvaCosto = new Double(vrIvaCostoStr).doubleValue();
    }

    public String getVrIvaCostoStr() {
        return new Double(vrIvaCosto).toString();
    }

    public String getVrIvaCostoDf2() {
        return df2.format(getVrIvaCosto());
    }

    public String getVrIvaCostoDf0() {
        return df0.format(getVrIvaCosto());
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

    public String getVrCostoINDSf0() {
        return sf0.format(getVrCostoIND());
    }

    public String getVrCostoINDDecimal() {

        BigDecimal vrCostoINDDecimal
                = new BigDecimal(getVrCostoIND());

        return (vrCostoINDDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public String getVrCostoINDDf2() {

        return df2.format(getVrCostoIND());
    }

    public String getVrCostoINDDf0() {
        return df0.format(getVrCostoIND());
    }

    public String getVrCostoINDSf2() {
        return sf2.format(new Double((double) (int) (getVrCostoIND() * 100.0) / 100));

    }

    public void setVrIvaResurtido(double vrIvaResurtido) {
        this.vrIvaResurtido = vrIvaResurtido;
    }

    public double getVrIvaResurtido() {
        return vrIvaResurtido;
    }

    public void setVrIvaResurtido(String vrIvaResurtidoStr) {
        this.vrIvaResurtido = new Double(vrIvaResurtidoStr).doubleValue();
    }

    public String getVrIvaResurtidoStr() {
        return new Double(vrIvaResurtido).toString();
    }

    public String getVrIvaResurtidoSf0() {
        return sf0.format(getVrIvaResurtido());
    }

    public void setFactorDespacho(int factorDespacho) {
        this.factorDespacho = factorDespacho;
    }

    public int getFactorDespacho() {
        return factorDespacho;
    }

    public void setFactorDespacho(String factorDespachoStr) {
        this.factorDespacho = new Integer(factorDespachoStr).intValue();
    }

    public String getFactorDespachoStr() {
        return new Integer(getFactorDespacho()).toString();
    }

    public String getIdSubcuenta() {
        return idSubcuenta;
    }

    public void setIdSubcuenta(String idSubcuenta) {
        this.idSubcuenta = idSubcuenta;
    }

    public void setCantidadBonificada(double cantidadBonificada) {
        this.cantidadBonificada = cantidadBonificada;
    }

    public double getCantidadBonificada() {
        return cantidadBonificada;
    }

    public String getCantidadBonificadaDf2() {
        return df2.format(getCantidadBonificada());
    }

    public int getCantidadBonificadaInt() {
        return (int) getCantidadBonificada();
    }

    public void setCantidadBonificada(String cantidadBonificadaStr) {
        this.cantidadBonificada = new Double(cantidadBonificadaStr).doubleValue();
    }

    public String getCantidadBonificadaStr() {
        return new Double(cantidadBonificada).toString();
    }

    public String getCantidadBonificadaSf1() {
        return sf1.format(getCantidadBonificada());
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicialStr(String fechaInicial) {
        this.fechaInicial = fechaInicial;
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
        return new Integer(getDiasHistoria()).toString();
    }

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public String getRotacioStr() {
        return new Integer(getRotacion()).toString();
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

    public void setVrGeneral(double vrGeneral) {
        this.vrGeneral = vrGeneral;
    }

    public double getVrGeneral() {
        return vrGeneral;
    }

    public void setVrGeneral(String vrGeneralStr) {
        this.vrGeneral = new Double(vrGeneralStr).doubleValue();
    }

    public String getVrGeneralStr() {
        return new Double(vrGeneral).toString();
    }

    public String getVrGeneralDf0() {
        return df0.format(getVrGeneral());
    }

    public String getVrGeneralDecimal() {

        BigDecimal vrGeneralDecimal
                = new BigDecimal(getVrGeneral());

        return (vrGeneralDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public void setVrMayorista(double vrMayorista) {
        this.vrMayorista = vrMayorista;
    }

    public double getVrMayorista() {
        return vrMayorista;
    }

    public void setVrMayorista(String vrMayoristaStr) {
        this.vrMayorista = new Double(vrMayoristaStr).doubleValue();
    }

    public String getVrMayoristaStr() {
        return new Double(vrMayorista).toString();
    }

    public String getVrMayoristaDf0() {
        return df0.format(getVrMayorista());
    }

    public String getVrMayoristaDecimal() {

        BigDecimal vrMayoristaDecimal
                = new BigDecimal(getVrMayorista());

        return (vrMayoristaDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public int getIdOrdenOrigen() {
        return idOrdenOrigen;
    }

    public String getIdOrdenOrigenStr() {
        return new Integer(getIdOrdenOrigen()).toString();
    }

    public void setIdOrdenOrigen(int idOrdenOrigen) {
        this.idOrdenOrigen = idOrdenOrigen;
    }

    public void setIdOrdenOrigen(String idOrdenOrigenStr) {
        this.idOrdenOrigen = new Integer(idOrdenOrigenStr).intValue();
    }

    public int getIdLocalOrigen() {
        return idLocalOrigen;
    }

    public String getIdLocalOrigenStr() {
        return new Integer(getIdLocalOrigen()).toString();
    }

    public void setIdLocalOrigen(int idLocalOrigen) {
        this.idLocalOrigen = idLocalOrigen;
    }

    public void setIdLocalOrigen(String idLocalOrigenStr) {
        this.idLocalOrigen = new Integer(idLocalOrigenStr).intValue();
    }

    public int getIdTipoOrdenOrigen() {
        return idTipoOrdenOrigen;
    }

    public String getIdTipoOrdenOrigenStr() {
        return new Integer(getIdTipoOrdenOrigen()).toString();
    }

    public void setIdTipoOrdenOrigen(int idTipoOrdenOrigen) {
        this.idTipoOrdenOrigen = idTipoOrdenOrigen;
    }

    public void setIdTipoOrdenOrigen(String idTipoOrdenOrigenStr) {
        this.idTipoOrdenOrigen = new Integer(idTipoOrdenOrigenStr).intValue();
    }

    public void setUnidadVenta(double unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public double getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(String unidadVentaStr) {
        this.unidadVenta = new Double(unidadVentaStr).doubleValue();
    }

    public String getUnidadVentaStr() {
        return new Double(unidadVenta).toString();
    }

    public String getUnidadVentaDf0() {
        return df0.format(getUnidadVenta());
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
        return new Integer(getIdFicha()).toString();
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
        return new Integer(getIdOperacion()).toString();
    }

    public String getIdOperacionSf0() {
        return sf0.format(getIdOperacion());
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

    public String getNumeroOrdenSf0() {
        return sf0.format(getNumeroOrden());
    }

    public void setItemOrden(int itemOrden) {
        this.itemOrden = itemOrden;
    }

    public int getItemOrden() {
        return itemOrden;
    }

    public String getItemOrdenSf0() {
        return sf0.format(getItemOrden());
    }

    public void setItemOrden(String itemOrdenStr) {
        this.itemOrden = new Integer(itemOrdenStr).intValue();
    }

    public String getItemOrdenStr() {
        return new Integer(itemOrden).toString();
    }

    public void setVrVentaUnitarioSinIva(double vrVentaUnitarioSinIva) {
        this.vrVentaUnitarioSinIva = vrVentaUnitarioSinIva;
    }

    public double getVrVentaUnitarioSinIva() {
        return vrVentaUnitarioSinIva;
    }

    public void setVrVentaUnitarioSinIva(String vrVentaUnitarioSinIvaStr) {
        this.vrVentaUnitarioSinIva = new Double(vrVentaUnitarioSinIvaStr).doubleValue();
    }

    public String getVrVentaUnitarioSinIvaStr() {
        return new Double(vrVentaUnitarioSinIva).toString();
    }

    public String getVrVentaUnitarioSinIvaDf0() {
        return df0.format(getVrVentaUnitarioSinIva());
    }

    public String getVrVentaUnitarioSinIvaDecimal() {

        BigDecimal vrVentaUnitarioSinIvaDecimal
                = new BigDecimal(getVrVentaUnitarioSinIva());

        return (vrVentaUnitarioSinIvaDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    public void setPesoTerminado(double pesoTerminado) {
        this.pesoTerminado = pesoTerminado;
    }

    public double getPesoTerminado() {
        return pesoTerminado;
    }

    public String getPesoTerminadoDf2() {
        return df2.format(getPesoTerminado());
    }

    public String getPesoTerminadoDf0() {
        return df0.format(getPesoTerminado());
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

    public void setPesoPedido(double pesoPedido) {
        this.pesoPedido = pesoPedido;
    }

    public double getPesoPedido() {
        return pesoPedido;
    }

    public String getPesoPedidoDf2() {
        return df2.format(getPesoPedido());
    }

    public String getPesoPedidoDf0() {
        return df0.format(getPesoPedido());
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

    public void setPesoRetal(double pesoRetal) {
        this.pesoRetal = pesoRetal;
    }

    public double getPesoRetal() {
        return pesoRetal;
    }

    public String getPesoRetalDf2() {
        return df2.format(getPesoRetal());
    }

    public String getPesoRetalDf0() {
        return df0.format(getPesoRetal());
    }

    public int getPesoRetalInt() {
        return (int) getPesoRetal();
    }

    public void setPesoRetal(String pesoRetalStr) {
        this.pesoRetal = new Double(pesoRetalStr).doubleValue();
    }

    public String getPesoRetalStr() {
        return new Double(pesoRetal).toString();
    }

    public String getPesoRetalSf1() {
        return sf1.format(getPesoRetal());
    }

    public void setCantidadPendiente(double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public String getCantidadPendienteDf2() {
        return df2.format(getCantidadPendiente());
    }

    public String getCantidadPendienteDf0() {
        return df0.format(getCantidadPendiente());
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

    public void setCantidadFacturada(double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public String getCantidadFacturadaDf2() {
        return df2.format(getCantidadFacturada());
    }

    public String getCantidadFacturadaDf0() {
        return df0.format(getCantidadFacturada());
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

    public void setOrdenCompra(String ordenCompra) {

        this.ordenCompra = ordenCompra;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public String getOrdenCompraStr() {
        return new Double(ordenCompra).toString();
    }

    public String getOrdenCompraSf0() {
        return sf0.format(getOrdenCompra());
    }

    public void setPesoFacturado(double pesoFacturado) {
        this.pesoFacturado = pesoFacturado;
    }

    public double getPesoFacturado() {
        return pesoFacturado;
    }

    public String getPesoFacturadoDf2() {
        return df2.format(getPesoFacturado());
    }

    public String getPesoFacturadoDf0() {
        return df0.format(getPesoFacturado());
    }

    public int getPesoFacturadoInt() {
        return (int) getPesoFacturado();
    }

    public void setPesoFacturado(String pesoFacturadoStr) {
        this.pesoFacturado = new Double(pesoFacturadoStr).doubleValue();
    }

    public String getPesoFacturadoStr() {
        return new Double(pesoFacturado).toString();
    }

    public String getPesoFacturadoSf1() {
        return sf1.format(getPesoFacturado());
    }

    public void setPesoPendiente(double pesoPendiente) {
        this.pesoPendiente = pesoPendiente;
    }

    public double getPesoPendiente() {
        return pesoPendiente;
    }

    public String getPesoPendienteDf2() {
        return df2.format(getPesoPendiente());
    }

    public String getPesoPendienteDf0() {
        return df0.format(getPesoPendiente());
    }

    public int getPesoPendienteInt() {
        return (int) getPesoPendiente();
    }

    public void setPesoPendiente(String pesoPendienteStr) {
        this.pesoPendiente = new Double(pesoPendienteStr).doubleValue();
    }

    public String getPesoPendienteStr() {
        return new Double(pesoPendiente).toString();
    }

    public String getPesoPendienteSf1() {
        return sf1.format(getPesoPendiente());
    }

    public void setCantidadTerminada(double cantidadTerminada) {
        this.cantidadTerminada = cantidadTerminada;
    }

    public double getCantidadTerminada() {
        return cantidadTerminada;
    }

    public String getCantidadTerminadaDf2() {
        return df2.format(getCantidadTerminada());
    }

    public String getCantidadTerminadaDf0() {
        return df0.format(getCantidadTerminada());
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

    public void setCantidadEntregada(double cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public double getCantidadEntregada() {
        return cantidadEntregada;
    }

    public String getCantidadEntregadaDf2() {
        return df2.format(getCantidadEntregada());
    }

    public String getCantidadEntregadaDf0() {
        return df0.format(getCantidadEntregada());
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

    public void setPesoEntregado(double pesoEntregado) {
        this.pesoEntregado = pesoEntregado;
    }

    public double getPesoEntregado() {
        return pesoEntregado;
    }

    public String getPesoEntregadoDf2() {
        return df2.format(getPesoEntregado());
    }

    public String getPesoEntregadoDf0() {
        return df0.format(getPesoEntregado());
    }

    public int getPesoEntregadoInt() {
        return (int) getPesoEntregado();
    }

    public void setPesoEntregado(String pesoEntregadoStr) {
        this.pesoEntregado = new Double(pesoEntregadoStr).doubleValue();
    }

    public String getPesoEntregadoStr() {
        return new Double(pesoEntregado).toString();
    }

    public String getPesoEntregadoSf1() {
        return sf1.format(getPesoEntregado());
    }

    public String getObservacion() {
        return observacion;
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

    public void setVrFactura(double vrFactura) {
        this.vrFactura = vrFactura;
    }

    public double getVrFactura() {
        return vrFactura;
    }

    public void setVrFactura(String vrFacturaStr) {
        this.vrFactura = new Double(vrFacturaStr).doubleValue();
    }

    public String getVrFacturaStr() {
        return new Double(vrFactura).toString();
    }

    public String getVrFacturaDf0() {
        return df0.format(getVrFactura());
    }

    public void setIdDcto(double idDcto) {
        this.idDcto = idDcto;
    }

    public double getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(String idDctoStr) {
        this.idDcto = new Double(idDctoStr).doubleValue();
    }

    public String getIdDctoStr() {
        return new Double(getIdDcto()).toString();
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

    public String getFechaDctoSqlServer() {

        return getFechaDcto().substring(0, 4)
                + getFechaDcto().substring(5, 7)
                + getFechaDcto().substring(8, 10);
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

}
