package com.solucionesweb.lasayudas;

// Importa la clase que contiene el AgendaLogVisitaBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.negocio.PluBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el ColaboraArticuloBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraArticuloBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaUsuarioBean
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa la clase que contiene el FachadaAgendaProgramacionBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoGuardaPedidoReferencia {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoGuardaPedidoReferencia () { }

  public int guarda(int    xIdLog,
                    String xStrIdReferencia,
                    double xCantidad,
                    double xVrVentaUnitario,
                    int    xItemPadre,
                    String xStrIdBodega,
                    String xReferenciaCliente,
                    String xComentario,
                    int idResponsable,
                    int idClasificacion)  {

                  //
                  PluBean pluBean               = new PluBean();

                  //
                  String strIdReferencia        = null;
                  strIdReferencia               = xStrIdReferencia;
                  String strNombrePlu           = "** NO EXISTE ACABADO" ;
                  String strUnidadMedida        = "UN" ;
                  double pesoTeorico            = 1.0;
                  int idEstadoRefOriginal       = 1;
                  double porcentajeIva          = 0;
                  int   xIdBodega               = 1;
                  String xStrIdSucursal         = "";

                  //
                  pluBean.setStrIdReferencia( "'" + strIdReferencia + "'" );

                  //
                  FachadaPluBean fachadaPluBean = new FachadaPluBean();

                  //
                  fachadaPluBean                =  pluBean.existePluFachada();

                  //
                  int idPluMax                  = fachadaPluBean.getIdPlu();
                  int estadoPlu                 = 1;

                  //
                  if (fachadaPluBean.getIdPlu() == 0) {

                     //
                     idPluMax        =  pluBean.maximoIdPlu() + 1;
                     pluBean.setIdPlu(idPluMax);
                     pluBean.setEstado(estadoPlu);
                     pluBean.setStrIdReferencia(strIdReferencia);

                     // ingresa
                     pluBean.ingresa();

                  }

                  //
    	          String idLocalSugerido    = "1";
    	          String idBodegaSugerido   = "1";
    	          String vrVentaUnitario    = "1";

                  //
  	              int idTipoOrdenCO         = 10;   // Cotizaci�n
        	      int estadoDctoOrden       = 1;
  	              String tipoOrdenCO        = "CT"; // Cotizaci�n Movil
  	              int idOrigenBB            = 4;    // BlackBerry
  	              int idOrden               = 0;
  	              int idTipoPlu             = 1;    // TipoPlu inventario
  	              int estadoDesmarcado      = 0;    // Desmarcado retirar
                  int idBodega      	     = 0;

                  //
                  AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                  agendaLogVisitaBean.setIdLog(xIdLog);

                  //
                  FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean =
                                                  new FachadaAgendaLogVisitaBean();

                  //
                  fachadaAgendaLogVisitaBean = agendaLogVisitaBean.listaLogFachada();

                  //
                  double idUsuario           = fachadaAgendaLogVisitaBean.getIdUsuario();

                  //
                  UsuarioBean usuarioBean    = new UsuarioBean();
                  usuarioBean.setIdUsuario(idUsuario);

                  //
                  FachadaUsuarioBean fachadaUsuarioBean
                                             = new FachadaUsuarioBean();

                  //
                  fachadaUsuarioBean         = usuarioBean.listaUsuario();

                  //
                  String strIdLista          = "1";
                  int idLocal                =
                                         fachadaUsuarioBean.getIdLocalUsuario();

                  //
                  DctoOrdenBean dctoOrdenBean
                                             = new DctoOrdenBean();

                  //
                  FachadaDctoOrdenBean fachadaDctoOrdenBean
                                             = new FachadaDctoOrdenBean();

                  // Consulta si existeOrden
                  dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());

                  //
                  fachadaDctoOrdenBean       =
                                            dctoOrdenBean.listaDctoOrdenIdLog();

                  //
                  if (fachadaDctoOrdenBean.getIdOrden()>0) {

                     // SI existeOrden
                     idOrden                 = fachadaDctoOrdenBean.getIdOrden();

                  } else {

                     //
                     FachadaTerceroBean fachadaTerceroBean
                                             = new FachadaTerceroBean();

                     //
                     TerceroBean terceroBean = new TerceroBean();

                     //
                     terceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());

                     //
                     fachadaTerceroBean      =
                                            terceroBean.listaUnTerceroFachada();

                     //
                     String xEmail           = fachadaTerceroBean.getEmail();
                     String xIdFormaPago     =
                                         fachadaTerceroBean.getIdFormaPagoStr();


                     // NO existeOrden y se igual idLocal = idLocalInicial
                     dctoOrdenBean.setIdLocal(idLocal);
                     dctoOrdenBean.setIdTipoOrden(idTipoOrdenCO);

                     //
                     idOrden                 =
                                       dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

                     //
                     dctoOrdenBean.setIdLocal(idLocal);
                     dctoOrdenBean.setIdTipoOrden(idTipoOrdenCO);
                     dctoOrdenBean.setIdOrden(idOrden);
                     dctoOrdenBean.setFechaOrden(
                       fachadaAgendaLogVisitaBean.getFechaVisitaStr().substring(0,10));
                     dctoOrdenBean.setEstado(estadoDctoOrden);
                     dctoOrdenBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
                     dctoOrdenBean.setIdUsuario(
                                     fachadaAgendaLogVisitaBean.getIdUsuario());
                     dctoOrdenBean.setIdOrigen(idOrigenBB);
                     dctoOrdenBean.setIdLog(
                                         fachadaAgendaLogVisitaBean.getIdLog());
                     dctoOrdenBean.setFechaEntrega(
                      fachadaAgendaLogVisitaBean.getFechaVisitaStr().substring(0,10));
                     dctoOrdenBean.setTipoDcto(tipoOrdenCO);
                     dctoOrdenBean.setEmail(xEmail);
                     dctoOrdenBean.setFormaPago(xIdFormaPago);

                     //
                     boolean okIngreso = dctoOrdenBean.ingresaDctosOrden();

                  }

                  //
                  ColaboraArticuloBean colaboraArticuloBean = new ColaboraArticuloBean();

                  // listaArticuloXReferenciaCotizacion
                  colaboraArticuloBean.setStrIdReferencia("'" + strIdReferencia + "'" );

                  //
                  fachadaPluBean                    =
                      colaboraArticuloBean.listaArticuloxReferenciaCotizacion();

                  //
                  if (fachadaPluBean.getStrIdReferencia() != null)  {

                     //
                     strNombrePlu    =  fachadaPluBean.getNombrePlu();
                     strUnidadMedida =  fachadaPluBean.getStrUnidadMedida();
                     strNombrePlu    =  fachadaPluBean.getNombrePlu();
                     pesoTeorico     =  fachadaPluBean.getPesoTeorico();
                     strIdReferencia =  fachadaPluBean.getStrIdReferencia();
                     porcentajeIva   =  fachadaPluBean.getPorcentajeIva();

                  }


                  // Igual Encabezado = Detalle en IdLocal
                  DctoOrdenDetalleBean dctoOrdenDetalleBean
                                                   = new DctoOrdenDetalleBean();

                  //
                  dctoOrdenDetalleBean.setIdLocal(idLocal);
                  dctoOrdenDetalleBean.setIdTipoOrden(idTipoOrdenCO);
                  dctoOrdenDetalleBean.setIdOrden(idOrden);
                  dctoOrdenDetalleBean.setCantidad(xCantidad);
                  dctoOrdenDetalleBean.setNombrePlu(strNombrePlu);
                  dctoOrdenDetalleBean.setNombreUnidadMedida(strUnidadMedida);
                  dctoOrdenDetalleBean.setIdPlu(idPluMax);
                  dctoOrdenDetalleBean.setIdTipo(idTipoPlu);
                  dctoOrdenDetalleBean.setEstado(estadoDesmarcado);
                  dctoOrdenDetalleBean.setPorcentajeIva(porcentajeIva);
                  dctoOrdenDetalleBean.setVrVentaOriginal(xVrVentaUnitario);
                  //dctoOrdenDetalleBean.setIdBodega(idBodega);
                  dctoOrdenDetalleBean.setStrIdLista(strIdLista);
                  dctoOrdenDetalleBean.setStrIdReferencia(strIdReferencia);
                  dctoOrdenDetalleBean.setPesoTeorico(pesoTeorico);
                  dctoOrdenDetalleBean.setIdLocalSugerido(idLocalSugerido);
                  dctoOrdenDetalleBean.setIdBodegaSugerido(idBodegaSugerido);
                  dctoOrdenDetalleBean.setIdEstadoRefOriginal(idEstadoRefOriginal);
                  dctoOrdenDetalleBean.setIdReferenciaOriginal(strIdReferencia);

                  //
                  ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                                             new ColaboraDctoOrdenDetalleBean();

                  // maximoItem
                  colaboraDctoOrdenDetalleBean.setIdTipoOrden(idTipoOrdenCO);

                  //
                  int maximoItem = colaboraDctoOrdenDetalleBean.maximoItem(xIdLog) + 1 ;

                  // Valida  padre item = itemPadre o Complemento # diferentes
                  if (xItemPadre == 0) {

                      xItemPadre = maximoItem;

                  }

                  // Ingresa Historia
                  dctoOrdenDetalleBean.setVrVentaUnitario(xVrVentaUnitario);
                  dctoOrdenDetalleBean.setStrIdBodega(xStrIdBodega);
                  dctoOrdenDetalleBean.setReferenciaCliente(xReferenciaCliente);
                  dctoOrdenDetalleBean.setComentario(xComentario);
                  dctoOrdenDetalleBean.setItem(maximoItem);
                  dctoOrdenDetalleBean.setItemPadre(xItemPadre);
                  dctoOrdenDetalleBean.setIdResponsable(idResponsable);
                  dctoOrdenDetalleBean.setIdClasificacion(idClasificacion);
                  dctoOrdenDetalleBean.setIdBodega(xIdBodega);

                  // ingresa OrdenDetalle
                  boolean okIngresaDetalle   =
                                          dctoOrdenDetalleBean.ingresaDetalle();

                  return maximoItem;

  }
}