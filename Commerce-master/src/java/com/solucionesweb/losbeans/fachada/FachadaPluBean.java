package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

import com.solucionesweb.losbeans.utilidades.JhFormat;
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaPluBean  implements IConstantes {

    //
    private int idPlu;
    private String nombrePlu;
    private double vrGeneral;
    private double vrMayorista;
    private double vrSucursal;
    private double porcentajeIva;
    private int idTipo;
    private int idLinea;
    private int idCategoria;
    private int idMarca;
    private double vrCosto;
    private double vrCostoPrediccion;
    private int idUCompra;
    private int idUVenta;
    private double factorVenta;
    private double factorDespacho;
    private int estado;
    private int idSeq;
    private String strIdReferencia;
    private String strUnidadMedida;
    private String strIdLista;
    private double pesoTeorico;
    private String strIdLinea;
    private String referencia;
    private String nombreCategoria;
    private String nombreMarca;
    private double existencia;
    private int idLocal;
    private int idListaPrecio;
    private String ean;
    private int idBodega;
    private String nombreLinea;
    private double vrVentaSinIva;
    private double vrVentaConIva;
    private double cantidad;
    private double vrCostoSinIva;
    private double vrCostoConIva;
    private double vrIvaVenta;
    private double vrImpoconsumo;
    private double vrCostoIND;
    private double vrBase;
    private double vrIva;
    private double margenIND;
    private double factorDensidad;
    private int idFicha;
    private int idLog;
    private double cantidadPendiente;
    private double pesoPendiente;
    private double sk_proveedor;
    private double factor;//Factor de densidad para la ficha de una materia prima
    private int dolarizado;

    public double getSk_proveedor() {
        return sk_proveedor;
    }

    public void setSk_proveedor(double sk_proveedor) {
        this.sk_proveedor = sk_proveedor;
    }


    //
    JhFormat jhFormat = new JhFormat();

    //
    private static int xAproximacion = 50;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat sf0 = new DecimalFormat("##############");
    DecimalFormat sf2 = new DecimalFormat("############.00");

    //
    public void setIdSeq (int idSeq)
    {
        this.idSeq = idSeq ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public void setIdSeq (String idSeqStr)
    {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public void setIdPlu( int idPlu )
    {
        this.idPlu = idPlu;
    }

    public int getIdPlu()
    {
        return idPlu;
    }

    public String getIdPluEtiqueta()
    {
        return "*" + getIdPlu() + "*";
    }

    public void setIdPlu( String idPluStr )
    {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr()
    {
        return new Integer(getIdPlu()).toString();
    }
    
    public String getIdPluSf0()
    {
        return sf0.format(getIdPlu());
    }

    public String getNombrePlu()
    {
        return nombrePlu;
    }

    public void setNombrePlu( String nombrePlu )
    {
        this.nombrePlu = nombrePlu;
    }

    public void setVrGeneral( double vrGeneral )
    {
        this.vrGeneral = vrGeneral ;
    }

    public double getVrGeneral()
    {
        return vrGeneral;
    }

    public double getVrGeneralAproximado()
    {

        //
        long xVrAproximacion = Math.round((getVrGeneral()/xAproximacion));

        //
        return (double)( xVrAproximacion * xAproximacion );

    }

    public String getVrGeneralDf0()
    {
        return df0.format(getVrGeneral());
    }

    public String getVrGeneralSf0()
    {
        return sf0.format(getVrGeneral());
    }

    public void setVrGeneral( String vrGeneralStr )
    {
        this.vrGeneral = new Double(vrGeneralStr).doubleValue();
    }

    public String getVrGeneralStr()
    {
        return new Double (vrGeneral).toString();
    }

    public void setVrMayorista( double vrMayorista )
    {
        this.vrMayorista = vrMayorista ;
    }

    public double getVrMayorista()
    {
        return vrMayorista;
    }

    public String getVrMayoristaDf0()
    {
        return df0.format(getVrMayorista());
    }

    public void setVrMayorista( String vrMayoristaStr )
    {
        this.vrMayorista = new Double(vrMayoristaStr).doubleValue();
    }

    public String getVrMayoristaStr()
    {
        return new Double(vrMayorista).toString();
    }

    public String getVrMayoristaSf0()
    {
        return sf0.format(getVrMayorista());
    }

	public void setVrSucursal( double vrSucursal)
	{
		this.vrSucursal = vrSucursal;
	}

 	public double getVrSucursal()
	{
		return vrSucursal;
	}

    public String getVrSucursalDf0()
    {
        return df0.format(getVrSucursal());
    }


	public void setVrSucursal( String vrSucursalStr)
	{
		this.vrSucursal = new Double(vrSucursalStr).doubleValue();
	}

 	public String getVrSucursalStr()
	{
		return new Double(vrSucursal).toString();
	}

    public String getVrSucursalSf0()
    {
        return sf0.format(getVrSucursal());
    }

    public void setPorcentajeIva( double porcentajeIva )
    {
    	this.porcentajeIva = porcentajeIva;
    }

    public String getPorcentajeIvaDf2()
    {
        return df2.format(getPorcentajeIva());
    }

    public String getPorcentajeIvaDf0()
    {
        return df0.format(getPorcentajeIva());
    }

    public double getPorcentajeIva ()
    {
    	return porcentajeIva;
    }

    public void setPorcentajeIva ( String porcentajeIvaStr )
    {
    	this.porcentajeIva = new Double(porcentajeIvaStr).doubleValue();
    }

    public String getPorcentajeIvaStr ()
    {
    	return new Double(porcentajeIva).toString();
    }

    public void setIdTipo ( int idTipo )
    {
    	this.idTipo = idTipo;
    }

    public int getIdTipo()
    {
    	return idTipo;
    }

    public void setIdTipo ( String idTipoStr )
    {
    	this.idTipo = new Integer(idTipoStr).intValue();
    }

    public String getIdTipoStr()
    {
    	return new Integer(idTipo).toString();
    }

    public void setIdLinea ( int idLinea )
    {
    	this.idLinea = idLinea;
    }

    public int getIdLinea()
    {
    	return idLinea;
    }

    public void setIdLinea ( String idLineaStr )
    {
    	this.idLinea = new Integer(idLineaStr).intValue();
    }

    public String getIdLineaStr()
    {
    	return new Integer(idLinea).toString();
    }

    public void setIdCategoria( int idCategoria )
    {
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria()
    {
        return idCategoria;
    }

    public void setIdCategoria( String idCategoriaStr )
    {
        this.idCategoria = new Integer(idCategoriaStr).intValue();
    }

    public String getIdCategoriaStr()
    {
        return new Integer(idCategoria).toString();
    }

    public void setIdMarca( int idMarca )
    {
        this.idMarca = idMarca ;
    }

    public int getIdMarca()
    {
        return idMarca;
    }

    public void setIdMarca( String idMarcaStr )
    {
        this.idMarca = new Integer(idMarcaStr).intValue();
    }

    public String getIdMarcaStr()
    {
        return new Integer(idMarca).toString();
    }

    public void setVrCosto( double vrCosto )
    {
        this.vrCosto = vrCosto;
    }

    public double getVrCosto()
    {
        return vrCosto;
    }

    public double getVrCostoSf2()
    {
        return  (double)((int)(vrCosto * 100.0))/100 ;
    }

    public String getVrCostoDf0()
    {
        return df0.format(getVrCosto());
    }

    public String getVrCostoDf2()
    {
        return df2.format(getVrCosto());
    }

    public void setVrCosto( String vrCostoStr )
    {
        this.vrCosto = new Double(vrCostoStr).doubleValue();
    }

    public String getVrCostoStr()
    {
        return new Double(vrCosto).toString();
    }

    public void setIdUCompra( int idUCompra )
    {
        this.idUCompra = idUCompra;
    }

    public int getIdUCompra()
    {
        return idUCompra;
    }

    public void setIdUCompra( String idUCompraStr )
    {
        this.idUCompra = new Integer(idUCompraStr).intValue();
    }

    public String getIdUCompraStr()
    {
        return new Integer(idUCompra).toString();
    }

    public void setIdUVenta( int idUVenta )
    {
        this.idUVenta = idUVenta;
    }

    public int getIdUVenta()
    {
        return idUVenta;
    }

    public void setIdUVenta( String idUVentaStr )
    {
        this.idUVenta = new Integer(idUVentaStr).intValue();
    }

    public String getIdUVentaStr()
    {
        return new Integer(idUVenta).toString();
    }

    public void setPesoTeorico( double pesoTeorico )
    {
        this.pesoTeorico = pesoTeorico;
    }

    public double getPesoTeorico()
    {
        return pesoTeorico;
    }

    public void setPesoTeorico( String pesoTeoricoStr )
    {
        this.pesoTeorico = new Double(pesoTeoricoStr).doubleValue();
    }

    public String getPesoTeoricoStr()
    {
        return new Double(pesoTeorico).toString();
    }

    public void setFactorVenta( double factorVenta )
    {
        this.factorVenta = factorVenta;
    }

    public double getFactorVenta()
    {
        return factorVenta;
    }

    public void setFactorVenta( String factorVentaStr )
    {
        this.factorVenta = new Double(factorVentaStr).doubleValue();
    }

    public String getFactorVentaStr()
    {
        return new Double(factorVenta).toString();
    }

    public void setFactorDespacho( int factorDespacho )
    {
        this.factorDespacho = factorDespacho ;
    }

    public double getFactorDespacho()
    {
        return factorDespacho;
    }

    public void setFactorDespacho( String factorDespachoStr )
    {
        this.factorDespacho = new Double(factorDespachoStr).doubleValue();
    }

    public String getFactorDespachoStr()
    {
        return new Double(getFactorDespacho()).toString();
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

    public String getStrIdReferencia() {

        if (this.strIdReferencia!=null) {
           return strIdReferencia.toUpperCase().trim();
        } else {
           return strIdReferencia;
        }
    }

    public String getStrIdReferenciaMinuscula() {

        if (this.strIdReferencia!=null) {
           return strIdReferencia.toLowerCase().trim();
        } else {
           return strIdReferencia;
        }
    }

    public void setStrIdReferencia( String strIdReferencia )
    {
        this.strIdReferencia = strIdReferencia;
    }

    public void setStrIdLista( String strIdLista )
    {
        this.strIdLista = strIdLista ;
    }

    public String getStrIdLista()
    {
        return strIdLista;
    }

    public String getStrUnidadMedida()
    {
        return strUnidadMedida;
    }

    public void setStrUnidadMedida( String strUnidadMedida )
    {
        this.strUnidadMedida = strUnidadMedida;
    }

    public String getStrIdLinea()
    {
        return strIdLinea;
    }

    public void setStrIdLinea( String strIdLinea )
    {
        this.strIdLinea = strIdLinea;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public double getVrVentaUnitario(double xFactorBase) {

        return getVrGeneral() * ( 1 + xFactorBase ) ;

    }

    public String getVrVentaUnitarioStr(double xFactorBase)
    {
        return new Double (getVrGeneral() * ( 1 + xFactorBase )).toString();
    }

    public String getVrVentaUnitarioListaStr()
    {
        //
        double xVrPrecio  = 0.0;
        
        //
        int xIdListaPrecioGeneral   = 1;
        int xIdListaPrecioMayorista = 2;
        int xIdListaPrecioSucursal  = 3;

        //
        if (getIdListaPrecio()==xIdListaPrecioGeneral) {

           xVrPrecio = getVrGeneral();
        }
        if (getIdListaPrecio()==xIdListaPrecioMayorista) {

           xVrPrecio = getVrMayorista();
        }

        if (getIdListaPrecio()==xIdListaPrecioSucursal) {

           xVrPrecio = getVrSucursal();
        }

        return new Double (xVrPrecio).toString();

    }

    public double getVrVentaUnitarioLista()
    {
        //
        double xVrPrecio            = 0.0;

        //
        int xIdListaPrecioGeneral   = 1;
        int xIdListaPrecioMayorista = 2;
        int xIdListaPrecioSucursal  = 3;

        //
        if (getIdListaPrecio()==xIdListaPrecioGeneral) {

           xVrPrecio = getVrGeneral();
        }
        if (getIdListaPrecio()==xIdListaPrecioMayorista) {

           xVrPrecio = getVrMayorista();
        }

        if (getIdListaPrecio()==xIdListaPrecioSucursal) {

           xVrPrecio = getVrSucursal();
        }

        //
        return xVrPrecio;

    }

    public String getVrVentaUnitarioListaDf0()
    {
        return  sf0.format(getVrVentaUnitarioLista());
    }

    public String getVrVentaUnitarioDf0(double xFactorBase)
    {
        return  sf0.format(getVrGeneral() * ( 1 + xFactorBase ));
    }

    public void setExistencia( double existencia )
    {
        this.existencia = existencia;
    }

    public double getExistencia()
    {
        return existencia;
    }

    public void setExistencia( String existenciaStr )
    {
        this.existencia = new Double(existenciaStr).doubleValue();
    }

    public String getExistenciaStr()
    {
        return new Double(getExistencia()).toString();
    }

    public String getExistenciaDf2()
    {
        return df2.format(getExistencia());
    }

    public String getExistenciaSf2()
    {
        return sf2.format(getExistencia());
    }

    public void setCantidad( double cantidad )
    {
        this.cantidad = cantidad;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public void setCantidad( String cantidadStr )
    {
        this.cantidad = new Double(cantidadStr).doubleValue();
    }

    public String getCantidadStr()
    {
        return new Double(getCantidad()).toString();
    }

    public String getCantidadDf2()
    {
        return df2.format(getCantidad());
    }

    public String getCantidadDf0()
    {
        return df0.format(getCantidad());
    }

    public String getCantidadSf2()
    {
        return sf2.format(getCantidad());
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal;
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
        return new Integer(getIdLocal()).toString();
    }

    public String getIdLocalSf0()
    {
        return sf0.format(getIdLocal());
    }

    public int getIdListaPrecio() {
        return idListaPrecio;
    }

    public void setIdListaPrecio(int idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public double getMargenVrGeneral() {
        return ( ( getVrGeneral()   -
                   getVrCosto() ) /
                   getVrGeneral() ) * 100;
    }

    public double getMargenVrMayorista() {
        return ( ( getVrMayorista()   -
                   getVrCosto() ) /
                   getVrMayorista() ) * 100;
    }

    public double getMargenVrSucursal() {
        return ( ( getVrSucursal()   -
                   getVrCosto() ) /
                   getVrSucursal() ) * 100;
    }

    public String getMargenVrGeneralDf2()
    {
        return df2.format(getMargenVrGeneral());
    }

    public String getMargenVrMayoristaDf2()
    {
        return df2.format(getMargenVrMayorista());
    }

    public String getMargenVrSucursalDf2()
    {
        return df2.format(getMargenVrSucursal());
    }

    public String getVrExistenciaCostoStr()
    {
        return new Double(getVrCosto() *
                          getExistencia()).toString();
    }

    public String getVrExistenciaCostoDf2()
    {
        return df2.format(getVrCosto() *
                          getExistencia());
    }

    public String getVrExistenciaCostoSf2()
    {
        return sf2.format(getVrCosto() *
                          getExistencia());
    }

    public void setIdBodega( int idBodega )
    {
        this.idBodega = idBodega;
    }

    public int getIdBodega()
    {
        return idBodega;
    }

    public void setIdBodega( String idBodegaStr )
    {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }

    public String getIdBodegaStr()
    {
        return new Integer(getIdBodega()).toString();
    }

    public String getIdBodegaSf0()
    {
        return sf0.format(getIdBodega());
    }

    public void setVrVentaSinIva( double vrVentaSinIva )
    {
        this.vrVentaSinIva = vrVentaSinIva;
    }

    public double getVrVentaSinIva()
    {
        return vrVentaSinIva;
    }

    public String getVrVentaSinIvaDf0()
    {
        return df0.format(getVrVentaSinIva());
    }

    public String getVrVentaSinIvaDf2()
    {
        return df2.format(getVrVentaSinIva());
    }

    public void setVrVentaSinIva( String vrVentaSinIvaStr )
    {
        this.vrVentaSinIva = new Double(vrVentaSinIvaStr).doubleValue();
    }

    public String getVrVentaSinIvaStr()
    {
        return new Double(vrVentaSinIva).toString();
    }

    public void setVrVentaConIva( double vrVentaConIva )
    {
        this.vrVentaConIva = vrVentaConIva;
    }

    public double getVrVentaConIva()
    {
        return vrVentaConIva;
    }

    public String getVrVentaConIvaDf0()
    {
        return df0.format(getVrVentaConIva());
    }

    public String getVrVentaConIvaDf2()
    {
        return df2.format(getVrVentaConIva());
    }

    public void setVrVentaConIva( String vrVentaConIvaStr )
    {
        this.vrVentaConIva = new Double(vrVentaConIvaStr).doubleValue();
    }

    public String getVrVentaConIvaStr()
    {
        return new Double(vrVentaConIva).toString();
    }

    public void setVrCostoSinIva( double vrCostoSinIva )
    {
        this.vrCostoSinIva = vrCostoSinIva;
    }

    public double getVrCostoSinIva()
    {
        return vrCostoSinIva;
    }

    public String getVrCostoSinIvaDf0()
    {
        return df0.format(getVrCostoSinIva());
    }

    public String getVrCostoSinIvaDf2()
    {
        return df2.format(getVrCostoSinIva());
    }

    public void setVrCostoSinIva( String vrCostoSinIvaStr )
    {
        this.vrCostoSinIva = new Double(vrCostoSinIvaStr).doubleValue();
    }

    public String getVrCostoSinIvaStr()
    {
        return new Double(vrCostoSinIva).toString();
    }

    public void setVrCostoConIva( double vrCostoConIva )
    {
        this.vrCostoConIva = vrCostoConIva;
    }

    public double getVrCostoConIva()
    {
        return vrCostoConIva;
    }

    public String getVrCostoConIvaDf0()
    {
        return df0.format(getVrCostoConIva());
    }

    public String getVrCostoConIvaDf2()
    {
        return df2.format(getVrCostoConIva());
    }

    public void setVrCostoConIva( String vrCostoConIvaStr )
    {
        this.vrCostoConIva = new Double(vrCostoConIvaStr).doubleValue();
    }

    public String getVrCostoConIvaStr()
    {
        return new Double(vrCostoConIva).toString();
    }

    public void setVrIvaVenta( double vrIvaVenta )
    {
        this.vrIvaVenta = vrIvaVenta;
    }

    public double getVrIvaVenta()
    {
        return vrIvaVenta;
    }

    public String getVrIvaVentaDf0()
    {
        return df0.format(getVrIvaVenta());
    }

    public String getVrIvaVentaDf2()
    {
        return df2.format(getVrIvaVenta());
    }

    public void setVrIvaVenta( String vrIvaVentaStr )
    {
        this.vrIvaVenta = new Double(vrIvaVentaStr).doubleValue();
    }

    public String getVrIvaVentaStr()
    {
        return new Double(vrIvaVenta).toString();
    }

    public void setVrImpoconsumo( double vrImpoconsumo )
    {
        this.vrImpoconsumo = vrImpoconsumo;
    }

    public double getVrImpoconsumo()
    {
        return vrImpoconsumo;
    }

    public String getVrImpoconsumoDf0()
    {
        return df0.format(getVrImpoconsumo());
    }

    public String getVrImpoconsumoDf2()
    {
        return df2.format(getVrImpoconsumo());
    }

    public void setVrImpoconsumo( String vrImpoconsumoStr )
    {
        this.vrImpoconsumo = new Double(vrImpoconsumoStr).doubleValue();
    }

    public String getVrImpoconsumoStr()
    {
        return new Double(vrImpoconsumo).toString();
    }

    public void setVrCostoIND( double vrCostoIND )
    {
        this.vrCostoIND = vrCostoIND ;
    }

    public double getVrCostoIND()
    {
        return vrCostoIND;
    }

    public double getVrCostoINDSf2()
    {
        return  (double)((int)(vrCostoIND * 100.0))/100 ;
    }


    public void setVrCostoIND( String vrCostoINDStr )
    {
        this.vrCostoIND = new Double(vrCostoINDStr).doubleValue();
    }

    public String getVrCostoINDStr()
    {
        return new Double(vrCostoIND).toString();
    }

    public String getVrCostoINDDf2()
    {

        return df2.format(getVrCostoIND());

    }

    public void setVrBase( double vrBase )
    {
        this.vrBase = vrBase ;
    }

    public double getVrBase()
    {
        return vrBase;
    }

    public String getVrBaseDf0()
    {
        return df0.format(getVrBase());
    }

    public String getVrBaseSf0()
    {
        return sf0.format(getVrBase());
    }

    public void setVrBase( String vrBaseStr )
    {
        this.vrBase = new Double(vrBaseStr).doubleValue();
    }

    public String getVrBaseStr()
    {
        return new Double (vrBase).toString();
    }

    public void setVrIva( double vrIva )
    {
        this.vrIva = vrIva ;
    }

    public double getVrIva()
    {
        return vrIva;
    }

    public String getVrIvaDf0()
    {
        return df0.format(getVrIva());
    }

    public String getVrIvaSf0()
    {
        return sf0.format(getVrIva());
    }

    public void setVrIva( String vrIvaStr )
    {
        this.vrIva = new Double(vrIvaStr).doubleValue();
    }

    public String getVrIvaStr()
    {
        return new Double (vrIva).toString();
    }

    public void setMargenIND( double margenIND )
    {
        this.margenIND = margenIND ;
    }

    public double getMargenIND()
    {
        return margenIND;
    }

    public String getMargenINDDf0()
    {
        return df0.format(getMargenIND());
    }

    public String getMargenINDSf0()
    {
        return sf0.format(getMargenIND());
    }

    public void setMargenIND( String margenINDStr )
    {
        this.margenIND = new Double(margenINDStr).doubleValue();
    }

    public String getMargenINDStr()
    {
        return new Double (margenIND).toString();
    }



    public String getPorcentajeMargenINDDf2()
    {

      //
      return df2.format( (( getVrVentaSinIva() -
                            getVrCostoIND() )  /
                            getVrVentaSinIva()) * 100 ) ;
    }

    public String getNombreMaterial() {

      //
      return jhFormat.fill(getReferencia().trim(), '_', 20, 1) +
              getNombrePlu() ;
    }

    public void setFactorDensidad( double factorDensidad )
    {
        this.factorDensidad = factorDensidad ;
    }

    public double getFactorDensidad()
    {
        return factorDensidad;
    }

    public void setFactorDensidad( String factorDensidadStr )
    {
        this.factorDensidad = new Double(factorDensidadStr).doubleValue();
    }

    public String getFactorDensidadStr()
    {
        return new Double(getFactorDensidad()).toString();
    }

    public void setIdFicha( int idFicha )
    {
        this.idFicha = idFicha;
    }

    public int getIdFicha()
    {
        return idFicha;
    }

    public void setIdFicha( String idFichaStr )
    {
        this.idFicha = new Integer(idFichaStr).intValue();
    }

    public String getIdFichaStr()
    {
        return new Integer(getIdFicha()).toString();
    }

    public String getIdFichaSf0()
    {
        return sf0.format(getIdFicha());
    }

    public void setIdLog( int idLog )
    {
        this.idLog = idLog;
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
        return new Integer(getIdLog()).toString();
    }

    public String getIdLogSf0()
    {
        return sf0.format(getIdLog());
    }

    public void setCantidadPendiente( double cantidadPendiente )
    {
        this.cantidadPendiente = cantidadPendiente;
    }       

    public double getCantidadPendiente()
    {
        return cantidadPendiente;
    }

    public void setCantidadPendiente( String cantidadPendienteStr )
    {
        this.cantidadPendiente = new Double(cantidadPendienteStr).doubleValue();
    }

    public String getCantidadPendienteStr()
    {
        return new Double(getCantidadPendiente()).toString();
    }

    public String getCantidadPendienteDf2()
    {
        return df2.format(getCantidadPendiente());
    }

    public String getCantidadPendienteDf0()
    {
        return df0.format(getCantidadPendiente());
    }

    public String getCantidadPendienteSf2()
    {
        return sf2.format(getCantidadPendiente());
    }

    public void setPesoPendiente( double pesoPendiente )
    {
        this.pesoPendiente = pesoPendiente;
    }

    public double getPesoPendiente()
    {
        return pesoPendiente;
    }

    public void setPesoPendiente( String pesoPendienteStr )
    {
        this.pesoPendiente = new Double(pesoPendienteStr).doubleValue();
    }

    public String getPesoPendienteStr()
    {
        return new Double(getPesoPendiente()).toString();
    }

    public String getPesoPendienteDf2()
    {
        return df2.format(getPesoPendiente());
    }

    public String getPesoPendienteDf0()
    {
        return df0.format(getPesoPendiente());
    }

    public String getPesoPendienteSf2()
    {
        return sf2.format(getPesoPendiente());
    }
    //Dolarizado, metodo get
    public int getDolarizado() {
        return dolarizado;
    }

    public void setDolarizado(int dolarizado) {
        this.dolarizado = dolarizado;
    }

    public FachadaPluBean() { }

    public double getVrCostoPrediccion() {
        return vrCostoPrediccion;
    }

    public void setVrCostoPrediccion(double vrCostoPrediccion) {
        this.vrCostoPrediccion = vrCostoPrediccion;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
  public String getFactorStr()
    {
        return new Double(getFactor()).toString();
    }

}
