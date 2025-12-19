package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaPluFicha extends FachadaPluBean {

    //
    private int idLocal;
    private int idTipoOrden;
    private int idPlu;
    private String referenciaCliente;
    private String idCliente;
    private int idOperacion;
    private String nombreReferencia;
    private int idEscala;
    private int idTipoEscala;
    private int item;
    private double vrEscala;
    private String textoEscala;
    private String nombreEscala;
    private String nombreItem;
    private int numeroOrden;
    private String fechaEntrega;
    private int itemPadre;
    private double cantidadPerdida;
    private double pesoPerdido;
    private double pesoTerminado;
    private double cantidad;
    private double cantidadTerminada;
    private double porcentajeTerminado;
    //
    private double factorDensidad;
    private double pesoMillar;
    private double pesoPedido;
    private double pesoComplemento;
    private double metroPedido;
    private double metroRollo;
    private double pesoRollo;
    private int idOrden;
    private String accionBoton;
    private String nombreOperacion;
    private double vrTotal;
    private double vrPromedio;
    private String nombreTercero;
    private String direccionDespacho;
    private String nombreCiudad;
    private String nombreDpto;
    private double vrVentaUnitarioSinIva;
    private String contacto;
    private String ordenCompra;
    private int idFormaPago;
    private String nombreVendedor;
    private String observacion;
    private double cantidadPedida;
    private double cantidadFacturada;
    private double cantidadEntregada;    
    private double pesoEntregado;        
    private double pesoFacturado;
    private String fechaPrograma;
    private int idOrdenPrograma;
    private String idColor;
    private double idOperario;
    private String fechaOrden;
    private double existenciaPeso;
    private double existenciaCantidad;
    private String nombreOperario;    

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,##0.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df5 = new DecimalFormat("###,###,##0.00000");
    DecimalFormat Sf0 = new DecimalFormat("################");
    DecimalFormat Sf2 = new DecimalFormat("############.00");
    DecimalFormat porcentaje = new DecimalFormat("%.00");

    //
    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public void setIdPlu(String idPluStr) {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr() {
        return new Integer(getIdPlu()).toString();
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente.trim().toUpperCase();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public String getIdOperacionStr() {
        return new Integer(getIdOperacion()).toString();
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }

    public String getNombreReferencia() {
        return nombreReferencia;
    }

    public void setNombreReferencia(String nombreReferencia) {
        this.nombreReferencia = nombreReferencia.trim().toUpperCase();
    }

    public String getVrEscalaFormato() {

        //
        if (getVrEscala() == 0.0) {
            return "";
        }

        //
        double xVrEscala = (double) ((int) getVrEscala()) - getVrEscala();

        //
        if (xVrEscala == 0) {

            return new Integer((int) getVrEscala()).toString();

        } else {

            return new Double(getVrEscala()).toString();
        }
    }

    public int getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public void setIdEscala(String idEscalaStr) {
        this.idEscala = new Integer(idEscalaStr).intValue();
    }

    public String getIdEscalaStr() {
        return new Integer(getIdEscala()).toString();
    }

    public int getIdTipoEscala() {
        return idTipoEscala;
    }

    public void setIdTipoEscala(int idTipoEscala) {
        this.idTipoEscala = idTipoEscala;
    }

    public void setIdTipoEscala(String idTipoEscalaStr) {
        this.idTipoEscala = new Integer(idTipoEscalaStr).intValue();
    }

    public String getIdTipoEscalaStr() {
        return new Integer(getIdTipoEscala()).toString();
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public String getItemStr() {
        return new Integer(getItem()).toString();
    }

    public double getVrEscala() {
        return vrEscala;
    }

    public String getVrEscalaStr() {

        //
        if (getVrEscala() > 999999999999.0) {

            //
            return getVrEscalaSf0();

        }

        //
        if (getVrEscala() != (double) (int) getVrEscala()) {

            //
            return new Double(getVrEscala()).toString();

        } else {

            //
            return getVrEscalaSf0();
            
        }
    }

    public String getVrEscalaSf0() {
        return Sf0.format(getVrEscala());
    }

    public void setVrEscala(double vrEscala) {
        this.vrEscala = vrEscala;
    }

    public void setVrEscala(String vrEscalaStr) {
        this.vrEscala = new Double(vrEscalaStr).doubleValue();
    }

    public String getTextoEscala() {
        return textoEscala;
    }

    public void setTextoEscala(String textoEscala) {
        this.textoEscala = textoEscala.trim().toUpperCase();
    }

    public String getNombreEscala() {
        return nombreEscala;
    }

    public void setNombreEscala(String nombreEscala) {
        this.nombreEscala = nombreEscala;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getReferenciaFormato() {

        //
        String xReferenciaFormato = getReferencia();
        String xVrAncho = "";
        String xVrLargo = "";
        String xVrFuelleLateral = "";
        String xVrFuelleFondo = "";
        String xVrCalibre = "";
        String xVrSolapa = "";

        /*-----------------
        if  (new Double(xVrAncho).doubleValue()>0) {

        xVrAncho = getVrEscalaFormato();

        }

        //-----------------
        if  (new Double(xVrLargo).doubleValue()>0) {

        xVrLargo =  "X" + getVrLargoFormato();

        }

        //-----------------
        if  (new Double(xVrFuelleLateral).doubleValue()>0) {

        xVrFuelleLateral = "+" +
        getVrFuelleLateralFormato();

        }

        //-----------------
        if  (new Double(xVrFuelleFondo).doubleValue()>0) {

        xVrFuelleFondo = "+" +
        getVrFuelleFondoFormato();

        }

        //-----------------
        if  (xVrSolapa>0) {

        xVrSolapa = "+" +
        getVrSolapaFormato();

        }

        //-----------------
        if  (getVrCalibre()>0) {

        xVrCalibre =   "X" + getVrCalibreFormato();

        }*/

        //
        return xReferenciaFormato
                + xVrAncho
                + xVrFuelleLateral
                + xVrFuelleFondo
                + xVrSolapa
                + xVrLargo
                + xVrCalibre;

    }

    public double getFactorDensidad() {
        return factorDensidad;
    }

    public String getFactorDensidadStr() {
        return new Double(getFactorDensidad()).toString();
    }

    public String getFactorDensidadDf5() {
        return df5.format(getFactorDensidad());
    }

    public void setFactorDensidad(double factorDensidad) {
        this.factorDensidad = factorDensidad;
    }

    public void setFactorDensidad(String factorDensidadStr) {
        this.factorDensidad = new Double(factorDensidadStr).doubleValue();
    }

    public double getPesoMillar() {
        return pesoMillar;
    }

    public String getPesoMillarDf5() {
        return df5.format(getPesoMillar());
    }

    public String getPesoMillarStr() {
        return new Double(getPesoMillar()).toString();
    }

    public String getPesoMillarDf2() {
        return df2.format(getPesoMillar());
    }

    public void setPesoMillar(double pesoMillar) {
        this.pesoMillar = pesoMillar;
    }

    public void setPesoMillar(String pesoMillarStr) {
        this.pesoMillar = new Double(pesoMillarStr).doubleValue();
    }

    public double getPesoPedido() {
        return pesoPedido;
    }

    public String getPesoPedidoStr() {
        return new Double(getPesoPedido()).toString();
    }

    public String getPesoPedidoDf0() {
        return df0.format(getPesoPedido()).toString();
    }

    public String getPesoPedidoDf1() {
        return df1.format(getPesoPedido()).toString();
    }

    public void setPesoPedido(double pesoPedido) {
        this.pesoPedido = pesoPedido;
    }

    public void setPesoPedido(String pesoPedidoStr) {
        this.pesoPedido = new Double(pesoPedidoStr).doubleValue();
    }

    public double getPesoComplemento() {
        return pesoComplemento;
    }

    public String getPesoComplementoStr() {
        return new Double(getPesoComplemento()).toString();
    }

    public String getPesoComplementoDf0() {
        return df0.format(getPesoComplemento());
    }

    public String getPesoComplementoDf2() {
        return df2.format(getPesoComplemento());
    }

    public void setPesoComplemento(double pesoComplemento) {
        this.pesoComplemento = pesoComplemento;
    }

    public void setPesoComplemento(String pesoComplementoStr) {
        this.pesoComplemento = new Double(pesoComplementoStr).doubleValue();
    }

    public double getMetroPedido() {
        return metroPedido;
    }

    public String getMetroPedidoStr() {
        return new Double(getMetroPedido()).toString();
    }

    public String getMetroPedidoDf0() {
        return df0.format(getMetroPedido());
    }

    public void setMetroPedido(double metroPedido) {
        this.metroPedido = metroPedido;
    }

    public void setMetroPedido(String metroPedidoStr) {
        this.metroPedido = new Double(metroPedidoStr).doubleValue();
    }

    public double getMetroRollo() {
        return metroRollo;
    }

    public String getMetroRolloStr() {
        return new Double(getMetroRollo()).toString();
    }

    public String getMetroRolloDf0() {
        return df0.format(getMetroRollo());
    }

    public void setMetroRollo(double metroRollo) {
        this.metroRollo = metroRollo;
    }

    public void setMetroRollo(String metroRolloStr) {
        this.metroRollo = new Double(metroRolloStr).doubleValue();
    }

    public double getPesoRollo() {
        return pesoRollo;
    }

    public String getPesoRolloStr() {
        return new Double(getPesoRollo()).toString();
    }

    public String getPesoRolloDf0() {
        return df0.format(getPesoRollo());
    }

    public String getPesoRolloDf2() {
        return df2.format(getPesoRollo());
    }

    public void setPesoRollo(double pesoRollo) {
        this.pesoRollo = pesoRollo;
    }

    public void setPesoRollo(String pesoRolloStr) {
        this.pesoRollo = new Double(pesoRolloStr).doubleValue();
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public String getNumeroOrdenStr() {
        return new Integer(getNumeroOrden()).toString();
    }

    public String getNumeroOrdenSf0() {
        return Sf0.format(getNumeroOrden());
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public void setNumeroOrden(String numeroOrdenStr) {
        this.numeroOrden = new Integer(numeroOrdenStr).intValue();
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setIdOrden(String idOrdenStr) {
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public String getIdOrdenStr() {
        return new Integer(getIdOrden()).toString();
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr() {
        return new Integer(getIdTipoOrden()).toString();
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

    public String getAccionBoton() {
        return accionBoton;
    }

    public void setAccionBoton(String accionBoton) {
        this.accionBoton = accionBoton;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public double getVrTotal() {
        return vrTotal;
    }

    public String getVrTotalStr() {
        return new Double(getVrTotal()).toString();
    }

    public void setVrTotal(double vrTotal) {
        this.vrTotal = vrTotal;
    }

    public void setVrTotal(String vrTotalStr) {
        this.vrTotal = new Double(vrTotalStr).doubleValue();
    }

    public double getVrPromedio() {
        return vrPromedio;
    }

    public String getVrPromedioStr() {
        return new Double(getVrPromedio()).toString();
    }

    public void setVrPromedio(double vrPromedio) {
        this.vrPromedio = vrPromedio;
    }

    public void setVrPromedio(String vrPromedioStr) {
        this.vrPromedio = new Double(vrPromedioStr).doubleValue();
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public String getDireccionDespacho() {
        return direccionDespacho;
    }

    public void setDireccionDespacho(String direccionDespacho) {
        this.direccionDespacho = direccionDespacho;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreDpto() {
        return nombreDpto;
    }

    public void setNombreDpto(String nombreDpto) {
        this.nombreDpto = nombreDpto;
    }

    public int getItemPadre() {
        return itemPadre;
    }

    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    public void setItemPadre(String itemPadreStr) {
        this.itemPadre = new Integer(itemPadreStr).intValue();
    }

    public String getItemPadreStr() {
        return new Integer(getItemPadre()).toString();
    }

    public double getCantidadPerdida() {
        return cantidadPerdida;
    }

    public String getCantidadPerdidaStr() {
        return new Double(getCantidadPerdida()).toString();
    }

    public void setCantidadPerdida(double cantidadPerdida) {
        this.cantidadPerdida = cantidadPerdida;
    }

    public void setCantidadPerdida(String cantidadPerdidaStr) {
        this.cantidadPerdida = new Double(cantidadPerdidaStr).doubleValue();
    }

    public double getPesoPerdido() {
        return pesoPerdido;
    }

    public String getPesoPerdidoStr() {
        return new Double(getPesoPerdido()).toString();
    }

    public String getPesoPerdidoDf1() {
        return  df1.format(getPesoPerdido());
    }

    public void setPesoPerdido(double pesoPerdido) {
        this.pesoPerdido = pesoPerdido;
    }

    public void setPesoPerdido(String pesoPerdidoStr) {
        this.pesoPerdido = new Double(pesoPerdidoStr).doubleValue();
    }

    public double getPesoTerminado() {
        return pesoTerminado;
    }

    public String getPesoTerminadoStr() {
        return new Double(getPesoTerminado()).toString();
    }

    public String getPesoTerminadoDf1() {
        return  df1.format(getPesoTerminado());
    }

    public void setPesoTerminado(double pesoTerminado) {
        this.pesoTerminado = pesoTerminado;
    }

    public void setPesoTerminado(String pesoTerminadoStr) {
        this.pesoTerminado = new Double(pesoTerminadoStr).doubleValue();
    }

    public double getPorcentajeTerminado() {
        return porcentajeTerminado;
    }

    public String getPorcentajeTerminadoStr() {
        return new Double(getPorcentajeTerminado()).toString();
    }

    public String getPorcentajeTerminadoDf0() {
        return df0.format(getPorcentajeTerminado());
    }

    public void setPorcentajeTerminado(double porcentajeTerminado) {
        this.porcentajeTerminado = porcentajeTerminado;
    }

    public void setPorcentajeTerminado(String porcentajeTerminadoStr) {
        this.porcentajeTerminado = new Double(porcentajeTerminadoStr).doubleValue();
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

    public double getCantidadTerminada() {
        return cantidadTerminada;
    }

    public String getCantidadTerminadaStr() {
        return new Double(getCantidadTerminada()).toString();
    }

    public String getCantidadTerminadaDf0() {
        return df0.format(getCantidadTerminada());
    }

    public String getCantidadTerminadaDf1() {
        return df1.format(getCantidadTerminada());
    }

    public void setCantidadTerminada(double cantidadTerminada) {
        this.cantidadTerminada = cantidadTerminada;
    }

    public void setCantidadTerminada(String cantidadTerminadaStr) {
        this.cantidadTerminada = new Double(cantidadTerminadaStr).doubleValue();
    }

    public double getVrVentaUnitarioSinIva() {
        return vrVentaUnitarioSinIva;
    }

    public String getVrVentaUnitarioSinIvaStr() {
        return new Double(getVrVentaUnitarioSinIva()).toString();
    }

    public void setVrVentaUnitarioSinIva(double vrVentaUnitarioSinIva) {
        this.vrVentaUnitarioSinIva = vrVentaUnitarioSinIva;
    }

    public void setVrVentaUnitarioSinIva(String vrVentaUnitarioSinIvaStr) {
        this.vrVentaUnitarioSinIva = new Double(vrVentaUnitarioSinIvaStr).doubleValue();
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
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
        return new Integer(getIdFormaPago()).toString();
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getCantidadPedida() {
        return cantidadPedida;
    }

    public String getCantidadPedidaDf0() {
        return df0.format(getCantidadPedida());
    }

    public String getCantidadPedidaStr() {
        return new Double(getCantidadPedida()).toString();
    }

    public void setCantidadPedida(double cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public void setCantidadPedida(String cantidadPedidaStr) {
        this.cantidadPedida = new Double(cantidadPedidaStr).doubleValue();
    }

    public double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public String getCantidadFacturadaStr() {
        return new Double(getCantidadFacturada()).toString();
    }

    public String getCantidadFacturadaDf0() {
        return df0.format(getCantidadFacturada());
    }

    public void setCantidadFacturada(double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public void setCantidadFacturada(String cantidadFacturadaStr) {
        this.cantidadFacturada = new Double(cantidadFacturadaStr).doubleValue();
    }
    
    public double getCantidadEntregada() {
        return cantidadEntregada;
    }

    public String getCantidadEntregadaStr() {
        return new Double(getCantidadEntregada()).toString();
    }

    public String getCantidadEntregadaDf0() {
        return df0.format(getCantidadEntregada());
    }

    public void setCantidadEntregada(double cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public void setCantidadEntregada(String cantidadEntregadaStr) {
        this.cantidadEntregada = new Double(cantidadEntregadaStr).doubleValue();
    }

    public double getPesoEntregado() {
        return pesoEntregado;
    }

    public String getPesoEntregadoStr() {
        return new Double(getPesoEntregado()).toString();
    }

    public void setPesoEntregado(double pesoEntregado) {
        this.pesoEntregado = pesoEntregado;
    }

    public void setPesoEntregado(String pesoEntregadoStr) {
        this.pesoEntregado = new Double(pesoEntregadoStr).doubleValue();
    }

    public double getPesoFacturado() {
        return pesoFacturado;
    }

    public String getPesoFacturadoStr() {
        return new Double(getPesoFacturado()).toString();
    }

    public void setPesoFacturado(double pesoFacturado) {
        this.pesoFacturado = pesoFacturado;
    }

    public void setPesoFacturado(String pesoFacturadoStr) {
        this.pesoFacturado = new Double(pesoFacturadoStr).doubleValue();
    }

    public double getExistenciaCantidad() {
        return existenciaCantidad;
    }

    public String getExistenciaCantidadDf0() {
        return df0.format(getExistenciaCantidad());
    }

    public String getExistenciaCantidadDf1() {
        return df1.format(getExistenciaCantidad());
    }

    public double getExistenciaPeso() {
        return existenciaPeso;
    }

    public String getExistenciaPesoDf0() {
        return df0.format(getExistenciaPeso());
    }

    public String getExistenciaPesoDf1() {
        return df1.format(getExistenciaPeso());
    }

    public String getExistenciaPesoSf0() {
        return sf0.format(getExistenciaPeso());
    }


    public String getExistenciaCantidadSf0() {
        return Sf0.format(getExistenciaCantidad());
    }

    public void setFechaPrograma(String fechaPrograma) {
        this.fechaPrograma = fechaPrograma;
    }

    public String getFechaPrograma() {
        return fechaPrograma;
    }

    public String getFechaProgramaCorta() {

        //
        return getFechaPrograma().substring(0, 4) + "/"
                + getFechaPrograma().substring(5, 7) + "/"
                + getFechaPrograma().substring(8, 10);
    }

    public String getFechaProgramaSqlServer() {

        return getFechaPrograma().substring(0, 4)
                + getFechaPrograma().substring(5, 7)
                + getFechaPrograma().substring(8, 10);
    }

    public int getIdOrdenPrograma() {
        return idOrdenPrograma;
    }

    public void setIdOrdenPrograma(int idOrdenPrograma) {
        this.idOrdenPrograma = idOrdenPrograma;
    }

    public void setIdOrdenPrograma(String idOrdenProgramaStr) {
        this.idOrdenPrograma = new Integer(idOrdenProgramaStr).intValue();
    }

    public String getIdOrdenProgramaStr() {
        return new Integer(getIdOrdenPrograma()).toString();
    }

    public String getIdColor() {
        return idColor;
    }

    public void setIdColor(String idColor) {
        this.idColor = idColor;
    }

    public void setIdOperario( double idOperario )
    {
        this.idOperario = idOperario ;
    }

    public double getIdOperario()
    {
        return idOperario;
    }

    public void setIdOperario( String idOperarioStr )
    {
        this.idOperario = new Double(idOperarioStr).doubleValue();
    }

    public String getIdOperarioStr()
    {
        return new Double(idOperario).toString();
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaOrdenCorta() {

        //
        return getFechaOrden().substring(0, 4) + "/"
                + getFechaOrden().substring(5, 7) + "/"
                + getFechaOrden().substring(8, 10);
    }

    public String getFechaOrdenSqlServer() {

        return getFechaOrden().substring(0, 4)
                + getFechaOrden().substring(5, 7)
                + getFechaOrden().substring(8, 10);
    }

    public void setExistenciaPeso(double existenciaPeso) {
        this.existenciaPeso = existenciaPeso;
    }

    public void setExistenciaCantidad(double existenciaCantidad) {
        this.existenciaCantidad = existenciaCantidad;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr() {
        return new Integer(getIdLocal()).toString();
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }
}
