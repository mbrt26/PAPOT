package com.solucionesweb.lasayudas;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.util.*;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el ColaboraDctoOrdenBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa la clase que contiene el FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoConfirmaPedido {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoConfirmaPedido () { }

  public void confirma(String idCliente,
                       String idSucursal,
                       String idUsuario,
                       String idLog,
                       String clave,
                       int idResponsable)  {

           // Day
           Day day = new Day();

           // Formateo
           JhFormat formato    = new JhFormat();
           char caracterBlanco = ' ';
           char caracterCero   = '0';
           int  ladoDerecho    = 1;
           int  ladoIzquierdo  = 0;

           // Constantes requeridas Reglas de Negocio
           String  strIdLista090         = "090";
           int     intIdLog              = new Integer(idLog).intValue();
           int     idTipoOrdenCotizacion = 10;
           int     idTipoOrdenPedido     = 9;
  	       int     estadoDctoOrden       = 1;
  	       String  tipoOrdenPedido       = "BB"; // Cotizaci�n Movil
  	       int     idOrigenBB            = 4;    // BlackBerry
           int     idEstadoTx            = 1;    // Pendiente x autorizar
           int     idTipoTx              = 1;    // Novedad ingreso
           int     numeroOrden           = 0;    // Sin numero orden MAX
           int     xIdLocalSugerido      = 1;

           //
           FachadaTerceroBean fachadaTerceroBean
                                         = new FachadaTerceroBean();

           //
           TerceroBean terceroBean       = new TerceroBean();

           terceroBean.setIdCliente(idCliente);

           fachadaTerceroBean            = terceroBean.listaUnTerceroFachada();

           //
           int xIndicador                = fachadaTerceroBean.getIndicador();

           // Bean Dcto
    	   DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

    	   //
    	   dctoOrdenBean.setIdLog(idLog);
    	   dctoOrdenBean.setIdTipoOrden(idTipoOrdenCotizacion);

           // listaDctoOrdenIdLogIdTipoOrden
           FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean       =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           // Valida envio correo + cotizacion
           String pathBDCotizacion    = "C:\\Comercial\\BDCotizacionSqlServer\\";
                                        
           String strNumeroCotizacion = padStringLeft(
                      fachadaDctoOrdenBean.getIdLocalStr(),3,"0").trim() + "-" +
                                      fachadaDctoOrdenBean.getTipoDcto() + "-" +
              padStringLeft(fachadaDctoOrdenBean.getIdOrdenStr(),6,"0").trim() ;

           // Encabezado Dctos
  	       String  email               = fachadaDctoOrdenBean.getEmail();
  	       String  fax                 = fachadaDctoOrdenBean.getFax();
  	       String  contacto            = fachadaDctoOrdenBean.getContacto();
  	       String  observacion         = fachadaDctoOrdenBean.getObservacion();
  	       String  direccionDespacho   =
                                    fachadaDctoOrdenBean.getDireccionDespacho();
  	       String  ciudadDespacho      =
                                       fachadaDctoOrdenBean.getCiudadDespacho();
  	       String  formaPago           = fachadaDctoOrdenBean.getFormaPago();
           String  strFechaVisita      = day.getFechaFormateada();
           String  strFechaEntrega     =
              fachadaDctoOrdenBean.getFechaEntrega().toString().substring(0,10);
           String  strFechaDespacho    =
              fachadaDctoOrdenBean.getFechaEntrega().toString().substring(0,10);
                   strFechaDespacho    = formato.RetirarGuion(strFechaDespacho);

           //
           String  ordenCompra         = fachadaDctoOrdenBean.getOrdenCompra();
           String  descuentoComercial  =
                                fachadaDctoOrdenBean.getDescuentoComercialStr();
           String  impuestoVenta       =
                                     fachadaDctoOrdenBean.getImpuestoVentaStr();
           String  idRazon             = fachadaDctoOrdenBean.getIdRazon();

           // Bean Fachada
           FachadaDctoOrdenDetalleBean fachadaBeanDetalle;

           //
           FachadaColaboraDctoOrdenBean fachadaBeanTotales
                                       = new FachadaColaboraDctoOrdenBean();

           FachadaColaboraDctoOrdenBean fachadaBeanDcto
                                       =    new FachadaColaboraDctoOrdenBean();

           // Bean DctoOrdenDetalleBean
           DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

           // Bean ColaboraDctoOrdenDetalleBean
           ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                                       = new ColaboraDctoOrdenDetalleBean();

           // Bean Colaboracion
           ColaboraDctoOrdenBean colaboraDctoOrdenBean
                                       = new ColaboraDctoOrdenBean();

           //
           colaboraDctoOrdenBean.setIdLog(idLog);
           colaboraDctoOrdenBean.setIdTipoOrden(idTipoOrdenCotizacion);

           // CREA PEDIDO
	       Vector vectorTotales        =
                      colaboraDctoOrdenBean.totalizaCotizacionIdLocalSugerido();

           //
           Iterator iteratorTotales    = vectorTotales.iterator();

           // idOrden
           String idRazonVacia = "";
           int idOrden         = 0 ;

           // Bloque control de idlog ( varios idLocalSugerido )
           while (iteratorTotales.hasNext()) {

                 //
                 fachadaBeanTotales    =
                           (FachadaColaboraDctoOrdenBean)iteratorTotales.next();

                 // CREA PEDIDO
                 dctoOrdenBean.setIdLocal(xIdLocalSugerido);
                 dctoOrdenBean.setIdTipoOrden(idTipoOrdenPedido);

                 //
                 idOrden = dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

                 // ingresaPedido
                 dctoOrdenBean.setIdLocal(xIdLocalSugerido);
                 dctoOrdenBean.setIdTipoOrden(idTipoOrdenPedido);
                 dctoOrdenBean.setIdOrden(idOrden);
                 dctoOrdenBean.setFechaOrden(strFechaVisita);
                 dctoOrdenBean.setEstado(estadoDctoOrden);
                 dctoOrdenBean.setIdCliente(idCliente);
                 dctoOrdenBean.setIdUsuario(idUsuario);
                 dctoOrdenBean.setIdOrigen(idOrigenBB);
                 dctoOrdenBean.setIdLog(idLog);
                 dctoOrdenBean.setFechaEntrega(strFechaEntrega);
                 dctoOrdenBean.setTipoDcto(tipoOrdenPedido);
                 dctoOrdenBean.setEmail(email);
                 dctoOrdenBean.setFax(fax);
                 dctoOrdenBean.setContacto(contacto);
                 dctoOrdenBean.setObservacion(observacion);
                 dctoOrdenBean.setDireccionDespacho(direccionDespacho);
                 dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
                 dctoOrdenBean.setFormaPago(formaPago);
                 dctoOrdenBean.setOrdenCompra(ordenCompra);
                 dctoOrdenBean.setDescuentoComercial(descuentoComercial);
                 dctoOrdenBean.setImpuestoVenta(impuestoVenta);
                 dctoOrdenBean.setIdRazon(idRazonVacia);
                 dctoOrdenBean.setIdEstadoTx(idEstadoTx);
                 dctoOrdenBean.setIdTipoTx(idTipoTx);
                 dctoOrdenBean.setNumeroOrden(numeroOrden);
                 dctoOrdenBean.setIdResponsable(idResponsable);

                 // ingresaPedido
                 boolean okIngresoDcto   = dctoOrdenBean.ingresaPedido();

                 if (!okIngresoDcto) {
                       break;
                 }

                 //
                 DctoBean dctoBean       = new DctoBean();

                 //
                 dctoBean.setIdLocal(xIdLocalSugerido);
                 dctoBean.setIdTipoOrden(idTipoOrdenPedido);
                 dctoBean.setIndicador(xIndicador);
                 dctoBean.setFechaDcto(strFechaVisita);

                 dctoOrdenBean.setFechaOrden(strFechaVisita);

                 //
                 int xIdDctoMax           =
                         dctoBean.maximoDctoLocalIndicador() + 1;

                 //
                 dctoBean.setIdLocal(xIdLocalSugerido);
                 dctoBean.setIdTipoOrden(idTipoOrdenPedido);
                 dctoBean.setIdDcto(xIdDctoMax);
                 dctoBean.setIndicador(xIndicador);

                 dctoBean.setFechaDcto(contacto);
                 dctoBean.setVrBase(clave);
                 dctoBean.setVrPago(idRazon);
                 dctoBean.setEstado(idEstadoTx);

                 dctoBean.ingresaDcto();



                 // listaArticulosIdLocalSugeridoIdTipoOrden
                 colaboraDctoOrdenDetalleBean.setIdResponsable(idResponsable);
                 colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                                                         idTipoOrdenCotizacion);

                 //
                 Vector vectorDetalle
                    = colaboraDctoOrdenDetalleBean.listaArticulos(intIdLog);

                 //
                 Iterator iteratorDetalle = vectorDetalle.iterator();

                 // Bloque control de idOrden x idLocalSugerido
                 while (iteratorDetalle.hasNext()) {

                       //
                       fachadaBeanDetalle    =
                            (FachadaDctoOrdenDetalleBean)iteratorDetalle.next();

                       // Valida cambio de precio
                       String strIdListaPrecio
                                             =
                                             fachadaBeanDetalle.getStrIdLista();

                       //
                       if ( fachadaBeanDetalle.getVrVentaOriginal() !=
                          fachadaBeanDetalle.getVrVentaUnitario() ) {

                          //
                          strIdListaPrecio = strIdLista090;
                       }

                       //
                       double xVrDescuento     =
                                (fachadaBeanDetalle.getVrVentaOriginal() -
                                 fachadaBeanDetalle.getVrVentaUnitario());

                       // Valor ABS

                       // ingresaDctoOrdenDetalle
                       dctoOrdenDetalleBean.setIdLocal(xIdLocalSugerido);
                       dctoOrdenDetalleBean.setIdTipoOrden(idTipoOrdenPedido);
                       dctoOrdenDetalleBean.setIdOrden(idOrden);
                       dctoOrdenDetalleBean.setCantidad(
                                              fachadaBeanDetalle.getCantidad());
                       dctoOrdenDetalleBean.setNombrePlu(
                                             fachadaBeanDetalle.getNombrePlu());
                       dctoOrdenDetalleBean.setIdPlu(
                                                 fachadaBeanDetalle.getIdPlu());
                       dctoOrdenDetalleBean.setIdTipo(
                                                fachadaBeanDetalle.getIdTipo());
                       dctoOrdenDetalleBean.setEstado(
                                                fachadaBeanDetalle.getEstado());
                       dctoOrdenDetalleBean.setPorcentajeIva(
                                         fachadaBeanDetalle.getPorcentajeIva());
                       dctoOrdenDetalleBean.setVrVentaUnitario(
                                       fachadaBeanDetalle.getVrVentaUnitario());
                       dctoOrdenDetalleBean.setEan(fachadaBeanDetalle.getEan());
                       dctoOrdenDetalleBean.setVrVentaOriginal(
                                       fachadaBeanDetalle.getVrVentaOriginal());
                       dctoOrdenDetalleBean.setVrCosto(
                                               fachadaBeanDetalle.getVrCosto());
                       dctoOrdenDetalleBean.setVrDsctoPie(xVrDescuento);
                       dctoOrdenDetalleBean.setPorcentajeDscto(
                                       fachadaBeanDetalle.getPorcentajeDscto());
                       dctoOrdenDetalleBean.setCantidadPedida(
                                        fachadaBeanDetalle.getCantidadPedida());
                       dctoOrdenDetalleBean.setVrCostoNegociado(
                                      fachadaBeanDetalle.getVrCostoNegociado());
                       dctoOrdenDetalleBean.setStrIdBodega(
                                           fachadaBeanDetalle.getStrIdBodega());
                       dctoOrdenDetalleBean.setIdBodegaSugerido(formato.fill(
                                       fachadaBeanDetalle.getIdBodegaSugerido(),
                                       caracterCero,2,ladoIzquierdo));
                       dctoOrdenDetalleBean.setStrIdLista(strIdListaPrecio);
                       dctoOrdenDetalleBean.setStrIdReferencia(
                                       fachadaBeanDetalle.getStrIdReferencia());
                       dctoOrdenDetalleBean.setPesoTeorico(
                                           fachadaBeanDetalle.getPesoTeorico());
                       dctoOrdenDetalleBean.setNombreUnidadMedida(
                                    fachadaBeanDetalle.getNombreUnidadMedida());
                       dctoOrdenDetalleBean.setIdLocalSugerido(
                                                              xIdLocalSugerido);
                       dctoOrdenDetalleBean.setIdBodegaSugerido(
                                      fachadaBeanDetalle.getIdBodegaSugerido());
                       dctoOrdenDetalleBean.setMarcaArteCliente(
                                      fachadaBeanDetalle.getMarcaArteCliente());
                       dctoOrdenDetalleBean.setReferenciaCliente(
                                     fachadaBeanDetalle.getReferenciaCliente());
                       dctoOrdenDetalleBean.setComentario(
                                            fachadaBeanDetalle.getComentario());
                       dctoOrdenDetalleBean.setItem(
                                                  fachadaBeanDetalle.getItem());
                       dctoOrdenDetalleBean.setItemPadre(
                                             fachadaBeanDetalle.getItemPadre());
                       dctoOrdenDetalleBean.setIdEstadoTx(idEstadoTx);
                       dctoOrdenDetalleBean.setIdTipoTx(idTipoTx);
                       dctoOrdenDetalleBean.setIdResponsable(idResponsable);
                       dctoOrdenDetalleBean.setIdReferenciaOriginal(
                                  fachadaBeanDetalle.getIdReferenciaOriginal());
                       dctoOrdenDetalleBean.setIdEstadoRefOriginal(
                                   fachadaBeanDetalle.getIdEstadoRefOriginal());
                       dctoOrdenDetalleBean.setIdClasificacion(
                                       fachadaBeanDetalle.getIdClasificacion());
                       dctoOrdenDetalleBean.setIdResponsable(idResponsable);
                       dctoOrdenDetalleBean.setFechaEntrega(strFechaEntrega);

                       // ingresaDctoOrdenDetalle
                       boolean okIngresoDctoDetalle =
                                 dctoOrdenDetalleBean.ingresa(xIdLocalSugerido);

                 }
           }

  }

       private static String padStringLeft (String orig,
                                            int size,
                                            String padChar) {
               if ( orig == null ) {
                    orig = "<null>";
               }

               StringBuffer buffer = new StringBuffer(" ");
               int extraChars = size - orig.length();

               for(int i = 0; i < extraChars; i++) {
                   buffer.append(padChar);
               }

               buffer.append(orig);
               return( buffer.toString() );
       }

       private static String padStringRight (String orig, int size,String padChar) {
               if ( orig == null ) {
                    orig = "<null>";
               }

               StringBuffer buffer = new StringBuffer(" ");
               int extraChars = size - orig.length();
               buffer.append(orig);

               for(int i = 0; i < extraChars; i++) {
                   buffer.append(padChar);
               }

               return( buffer.toString() );
       }

}