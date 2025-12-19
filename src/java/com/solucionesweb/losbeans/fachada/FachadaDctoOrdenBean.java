package com.solucionesweb.losbeans.fachada;

// Importa el Bean de JhFormat
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaDctoOrdenBean  implements IConstantes {

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
    private String tipoDcto;
    private String email;
    private String direccionDespacho;
    private String fax;
    private String contacto;
    private String observacion;
    private String formaPago;
    private String ciudadDespacho;
    private String titulo;
    private String tituloConsulta;
    private String titulo1="CONTABLE COMPROBANTE INGRESO";
    private String titulo2="CONTABLE COMPROBANTE EGRESO";
    private String tituloConsulta1="CONSULTA COMPROBANTE INGRESO";
    private String tituloConsulta2="CONSULTA COMPROBANTE EGRESO";
    private String referenciaCliente;
    private int idFicha;
    private int idOperacion;
    private double idVendedor;
    //
    private int cantidadArticulos;
    private int vrVentaSinIva;
    private int vrVentaConIva;
    private int vrIva;
    private double pesoTeoricoTotal;
    //
    private String ordenCompra;
    private double descuentoComercial;
    private int impuestoVenta;
    private String idRazon;
    //
    private int idEstadoTx;
    private int idTipoTx;
    private int numeroOrden;
    private int idResponsable;
    private int idTipoTercero;

    //
    private int diasHistoria;
    private int diasInventario;
    private int idListaPrecio;
    private int idAlcance;
    
    //
    private int idPlu;
    private int idBodegaOrigen;
    private int idBodegaDestino;
    private int factorDespacho;
    private String idDctoNitCC;
    private String fechaInicial;
    private String fechaFinal;
    private int itemPadre;
    private String idRemisionNitCC;
    private double idOperario;
    private int pdfFE;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat sf0 = new DecimalFormat("##############");
    DecimalFormat sf2 = new DecimalFormat("############.00");

    //
    char    caracterRelleno = '0';
    JhFormat jhFormat = new JhFormat();

    // Metodos
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
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setIdOrigen( int idOrigen )
    {
        this.idOrigen = idOrigen ;
    }

    public int getIdOrigen()
    {
        return idOrigen;
    }

    public void setIdOrigen( String idOrigenStr )
    {
        this.idOrigen = new Integer(idOrigenStr).intValue();
    }

    public String getIdOrigenStr()
    {
        return new Integer(idOrigen).toString();
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

    public void setFechaOrden( String fechaOrden )
    {
        this.fechaOrden = fechaOrden ;
    }

    public String getFechaOrden()
    {
        return this.fechaOrden;
    }

    public String getFechaOrdenCorta()
    {
            return getFechaOrden().substring(0, 4)  + "/" +
                   getFechaOrden().substring(5, 7)  + "/" +
                   getFechaOrden().substring(8, 10) ;
    }

    public String getFechaOrdenSqlServer() {

            return getFechaOrden().substring(0, 4)  +
                   getFechaOrden().substring(5, 7)  +
                   getFechaOrden().substring(8, 10) ;

    }


    public String getFechaOrdenStrOracle()
    {
        return "TO_DATE('" + getFechaOrden() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setFechaEntrega( String fechaEntrega )
    {
        this.fechaEntrega = fechaEntrega ;
    }

    public String getFechaEntrega()
    {
        return fechaEntrega;
    }

    public String getFechaEntregaCorta()
    {
            return getFechaEntrega().substring(0, 4)  + "/" +
                   getFechaEntrega().substring(5, 7)  + "/" +
                   getFechaEntrega().substring(8, 10) ;
    }

    public String getFechaEntregaSqlServer() {

            return getFechaEntrega().substring(0, 4)  +
                   getFechaEntrega().substring(5, 7)  +
                   getFechaEntrega().substring(8, 10) ;

    }

    public String getFechaEntregaStrOracle()
    {
        return "TO_DATE('" + getFechaEntrega() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
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

    public void setTipoDcto( String tipoDcto )
    {
        this.tipoDcto = tipoDcto ;
    }

    public String getTipoDcto()
    {
        return tipoDcto;
    }

    public String getTipoDctoOracle()
    {
        return "'" + getTipoDcto() + "'" ;
    }

    public void setEmail( String email )
    {
        if (email == null) {
           this.email = STRINGVACIO;
        } else {
        this.email = email ;
        }
    }

    public String getEmail()
    {
        return email;
    }

    public void setDireccionDespacho( String direccionDespacho )
    {
        if (direccionDespacho == null) {
           this.direccionDespacho = STRINGVACIO;
        } else {
        this.direccionDespacho = direccionDespacho.toUpperCase();
        }
    }

    public String getDireccionDespacho()
    {
        return direccionDespacho;
    }

    public void setFax( String fax )
    {
        if (fax == null) {
           this.fax = STRINGVACIO;
        } else {
        this.fax = fax ;
        }
    }

    public String getFax()
    {
        return fax;
    }

    public void setContacto( String contacto )
    {
        if (contacto == null) {
           this.contacto = STRINGVACIO;
        } else {
        this.contacto = contacto.toUpperCase() ;
        }
    }

    public String getContacto()
    {
        return contacto;
    }

    public void setObservacion( String observacion )
    {
        if (observacion == null) {
           this.observacion = STRINGVACIO;
        } else {
        this.observacion = observacion.toUpperCase() ;
        }
    }

    public String getObservacion()
    {
        return observacion;
    }

    public String getObservacionMayuscula()
    {
        return getObservacion().toUpperCase().trim();
    }

    public void setCantidadArticulos( int cantidadArticulos )
    {
        this.cantidadArticulos = cantidadArticulos ;
    }

    public int getCantidadArticulos()
    {
        return cantidadArticulos;
    }

    public void setCantidadArticulos( String cantidadArticulosStr )
    {
        this.cantidadArticulos = new Integer(cantidadArticulosStr).intValue();
    }

    public String getCantidadArticulosStr()
    {
        return new Integer(cantidadArticulos).toString();
    }

    public void setVrVentaSinIva( int vrVentaSinIva )
    {
        this.vrVentaSinIva = vrVentaSinIva ;
    }

    public int getVrVentaSinIva()
    {
        return vrVentaSinIva;
    }

    public void setVrVentaSinIva( String vrVentaSinIvaStr )
    {
        this.vrVentaSinIva = new Integer(vrVentaSinIvaStr).intValue();
    }

    public String getVrVentaSinIvaStr()
    {
        return new Integer(vrVentaSinIva).toString();
    }

    public void setVrVentaConIva( int vrVentaConIva )
    {
        this.vrVentaConIva = vrVentaConIva ;
    }

    public int getVrVentaConIva()
    {
        return vrVentaConIva;
    }

    public void setVrVentaConIva( String vrVentaConIvaStr )
    {
        this.vrVentaConIva = new Integer(vrVentaConIvaStr).intValue();
    }

    public String getVrVentaConIvaStr()
    {
        return new Integer(vrVentaConIva).toString();
    }

    public void setVrIva( int vrIva )
    {
        this.vrIva = vrIva ;
    }

    public int getVrIva()
    {
        return vrIva;
    }

    public void setVrIva( String vrIvaStr )
    {
        this.vrIva = new Integer(vrIvaStr).intValue();
    }

    public String getVrIvaStr()
    {
        return new Integer(vrIva).toString();
    }

    public void setPesoTeoricoTotal( double pesoTeoricoTotal )
    {
        this.pesoTeoricoTotal = pesoTeoricoTotal ;
    }

    public double getPesoTeoricoTotal()
    {
        return pesoTeoricoTotal;
    }

    public void setPesoTeoricoTotal( String pesoTeoricoTotalStr )
    {
        this.pesoTeoricoTotal = new Double(pesoTeoricoTotalStr).doubleValue();
    }

    public String getPesoTeoricoTotalStr()
    {
        return new Double(pesoTeoricoTotal).toString();
    }

    public void setFormaPago( String formaPago )
    {
        if (formaPago == null) {
           this.formaPago = STRINGVACIO;
        } else {
        this.formaPago = formaPago ;
        }
    }

    public String getFormaPago()
    {
        return formaPago;
    }

    public void setCiudadDespacho( String ciudadDespacho )
    {
        if (ciudadDespacho == null) {
           this.ciudadDespacho = STRINGVACIO;
        } else {
        this.ciudadDespacho = ciudadDespacho ;
        }
    }

    public String getCiudadDespacho()
    {
        return ciudadDespacho;
    }


    public String getStrNumeroCotizacion()
    {

      return   jhFormat.fill(getIdLocalStr(),caracterRelleno,3,0) +
               jhFormat.fill(getIdOrdenStr(),caracterRelleno,7,0) ;
    }

    public void setOrdenCompra( String ordenCompra )
    {
        if (ordenCompra == null) {
           this.ordenCompra = STRINGVACIO;
        } else {
        this.ordenCompra = ordenCompra ;
        }
    }

    public String getOrdenCompra()
    {
        return ordenCompra;
    }

    public void setDescuentoComercial( double descuentoComercial )
    {
       this.descuentoComercial = descuentoComercial;
    }

    public void setDescuentoComercial(String descuentoComercialStr )
    {
       this.descuentoComercial =
                                new Double(descuentoComercialStr).doubleValue();
    }

    public double getDescuentoComercial()
    {
        return descuentoComercial;
    }

    public String getDescuentoComercialStr()
    {
        return  new Double(descuentoComercial).toString();
    }

    public void setIdRazon( String idRazon )
    {
        this.idRazon = idRazon ;
    }

    public String getIdRazon()
    {
        return idRazon;
    }

    public void setImpuestoVenta( int impuestoVenta )
    {
        this.impuestoVenta = impuestoVenta ;
    }

    public int getImpuestoVenta()
    {
        return impuestoVenta;
    }

    public void setImpuestoVenta( String impuestoVentaStr )
    {
        this.impuestoVenta = new Integer(impuestoVentaStr).intValue();
    }

    public String getImpuestoVentaStr()
    {
        return new Integer(impuestoVenta).toString();
    }

    public void setIdEstadoTx( int idEstadoTx )
    {
        this.idEstadoTx = idEstadoTx ;
    }

    public int getIdEstadoTx()
    {
        return idEstadoTx;
    }

    public void setIdEstadoTx( String idEstadoTxStr )
    {
        this.idEstadoTx = new Integer(idEstadoTxStr).intValue();
    }

    public String getIdEstadoTxStr()
    {
        return new Integer(idEstadoTx).toString();
    }

    public void setIdTipoTx( int idTipoTx )
    {
        this.idTipoTx = idTipoTx ;
    }

    public int getIdTipoTx()
    {
        return idTipoTx;
    }

    public void setIdTipoTx( String idTipoTxStr )
    {
        this.idTipoTx = new Integer(idTipoTxStr).intValue();
    }

    public String getIdTipoTxStr()
    {
        return new Integer(idTipoTx).toString();
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
        this.numeroOrden = new Integer(numeroOrdenStr).intValue();
    }

    public String getNumeroOrdenStr()
    {
        return new Integer(numeroOrden).toString();
    }

    public void setIdResponsable( int idResponsable )
    {
        this.idResponsable = idResponsable ;
    }

    public int getIdResponsable()
    {
        return idResponsable;
    }

    public void setIdResponsable( String idResponsableStr )
    {
        this.idResponsable = new Integer(idResponsableStr).intValue();
    }

    public String getIdResponsableStr()
    {
        return new Integer(idResponsable).toString();
    }

    public int getIdTipoTercero() {
        return idTipoTercero;
    }

    public String getIdTipoTerceroStr()
    {
        return new Integer(getIdTipoTercero()).toString();
    }

    public void setIdTipoTercero(int idTipoTercero) {
        this.idTipoTercero = idTipoTercero;
    }

    public void setIdTipoTercero( String idTipoTerceroStr )
    {
        this.idTipoTercero = new Integer(idTipoTerceroStr).intValue();
    }

    public void setDiasHistoria( int diasHistoria )
    {
        this.diasHistoria = diasHistoria ;
    }

    public int getDiasHistoria()
    {
        return diasHistoria;
    }

    public void setDiasHistoria( String diasHistoriaStr )
    {
        this.diasHistoria = new Integer(diasHistoriaStr).intValue();
    }

    public String getDiasHistoriaStr()
    {
        return new Integer(diasHistoria).toString();
    }

    public void setDiasInventario( int diasInventario )
    {
        this.diasInventario = diasInventario ;
    }

    public int getDiasInventario()
    {
        return diasInventario;
    }

    public void setDiasInventario( String diasInventarioStr )
    {
        this.diasInventario = new Integer(diasInventarioStr).intValue();
    }

    public String getDiasInventarioStr()
    {
        return new Integer(diasInventario).toString();
    }

    public void setIdListaPrecio( int idListaPrecio )
    {
        this.idListaPrecio = idListaPrecio ;
    }

    public int getIdListaPrecio()
    {
        return idListaPrecio;
    }

    public void setIdListaPrecio( String idListaPrecioStr )
    {
        this.idListaPrecio = new Integer(idListaPrecioStr).intValue();
    }

    public String getIdListaPrecioStr()
    {
        return new Integer(idListaPrecio).toString();
    }

    public void setIdPlu( int idPlu )
    {
        this.idPlu = idPlu ;
    }

    public int getIdPlu()
    {
        return idPlu;
    }

    public void setIdPlu( String idPluStr )
    {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr()
    {
        return new Integer(idPlu).toString();
    }

    public void setIdBodegaOrigen( int idBodegaOrigen )
    {
        this.idBodegaOrigen = idBodegaOrigen ;
    }

    public int getIdBodegaOrigen()
    {
        return idBodegaOrigen;
    }

    public void setIdBodegaOrigen( String idBodegaOrigenStr )
    {
        this.idBodegaOrigen = new Integer(idBodegaOrigenStr).intValue();
    }

    public String getIdBodegaOrigenStr()
    {
        return new Integer(idBodegaOrigen).toString();
    }

    public void setIdBodegaDestino( int idBodegaDestino )
    {
        this.idBodegaDestino = idBodegaDestino ;
    }

    public int getIdBodegaDestino()
    {
        return idBodegaDestino;
    }

    public void setIdBodegaDestino( String idBodegaDestinoStr )
    {
        this.idBodegaDestino = new Integer(idBodegaDestinoStr).intValue();
    }

    public String getIdBodegaDestinoStr()
    {
        return new Integer(idBodegaDestino).toString();
    }
    
  public void setIdAlcance( String idAlcanceStr )
    {
        this.idAlcance = new Integer(idAlcanceStr).intValue();
    }

    public void setIdAlcance( int idAlcance )
    {
        this.idAlcance = idAlcance ;
    }

    public int getIdAlcance()
    {
        return idAlcance;
    }

  
    public String getIdAlcanceStr()
    {
        return new Integer(idAlcance).toString();
    }

         public void setFactorDespacho( int factorDespacho )
    {
        this.factorDespacho = factorDespacho ;
    }

    public int getFactorDespacho()
    {
        return factorDespacho;
    }

    public void setFactorDespacho( String factorDespachoStr )
    {
        this.factorDespacho = new Integer(factorDespachoStr).intValue();
    }

    public String getFactorDespachoStr()
    {
        return new Integer(getFactorDespacho()).toString();
    }

    public FachadaDctoOrdenBean() { }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public void setFechaInicial( String fechaInicial )
    {
        this.fechaInicial = fechaInicial ;
    }

    public String getFechaInicial()
    {
        return this.fechaInicial;
    }

    public String getFechaInicialCorta()
    {
            return getFechaInicial().substring(0, 4)  + "/" +
                   getFechaInicial().substring(5, 7)  + "/" +
                   getFechaInicial().substring(8, 10) ;
    }

    public String getFechaInicialSqlServer() {

            return getFechaInicial().substring(0, 4)  +
                   getFechaInicial().substring(5, 7)  +
                   getFechaInicial().substring(8, 10) ;

    }

    public void setFechaFinal( String fechaFinal )
    {
        this.fechaFinal = fechaFinal ;
    }

    public String getFechaFinal()
    {
        return this.fechaFinal;
    }

    public String getFechaFinalCorta()
    {
            return getFechaFinal().substring(0, 4)  + "/" +
                   getFechaFinal().substring(5, 7)  + "/" +
                   getFechaFinal().substring(8, 10) ;
    }

    public String getFechaFinalSqlServer() {

            return getFechaFinal().substring(0, 4)  +
                   getFechaFinal().substring(5, 7)  +
                   getFechaFinal().substring(8, 10) ;

    }

    public String getTitulo() {

        if(Integer.parseInt(titulo)==4){
          return titulo1;
        }else if(Integer.parseInt(titulo)==5){
          return titulo2;
        }
       return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloConsulta() {
        if(Integer.parseInt(tituloConsulta)==4){
          return tituloConsulta1;
        }else if(Integer.parseInt(tituloConsulta)==5){
          return tituloConsulta2;
        }
       return tituloConsulta;
    }

    public void setTituloConsulta(String tituloConsulta) {
        this.tituloConsulta = tituloConsulta;
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
    }

    public void setIdFicha( int idFicha )
    {
        this.idFicha = idFicha ;
    }

    public int getIdFicha()
    {
        return idFicha;
    }

    public String getIdFichaSf0()
    {
        return sf0.format(getIdFicha());
    }

    public void setIdFicha( String idFichaStr )
    {
        this.idFicha = new Integer(idFichaStr).intValue();
    }

    public String getIdFichaStr()
    {
        return new Integer(idFicha).toString();
    }

    public void setIdOperacion( int idOperacion )
    {
        this.idOperacion = idOperacion ;
    }

    public int getIdOperacion()
    {
        return idOperacion;
    }

    public void setIdOperacion( String idOperacionStr )
    {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }

    public String getIdOperacionStr()
    {
        return new Integer(idOperacion).toString();
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

    public String getIdRemisionNitCC() {
        return idRemisionNitCC;
    }

    public void setIdRemisionNitCC(String idRemisionNitCC) {
        this.idRemisionNitCC = idRemisionNitCC;
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

    public int getPdfFE() {
        return pdfFE;
    }

    public void setPdfFE(int pdfFE) {
        this.pdfFE = pdfFE;
    }
    
}