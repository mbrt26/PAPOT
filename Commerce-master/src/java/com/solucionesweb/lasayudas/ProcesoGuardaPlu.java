package com.solucionesweb.lasayudas;

// Importa la clase que contiene el utilidades
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ProcesoGuardaPlu {

  public int guarda(int    xIdLog,
                    String xIdPlu,
                    String xCantidadPedida,
                    String xVrCostoNegociado,
                    int    xIdTipoOrden,
                    String xIdUsuario,
                    String xIdLocalUsuario,
                    String xIdTercero,
                    String xFechaCorte,
                    String xDiasHistoria,
                    String xDiasInventario)  {

          //---
          int   xIdOrdenMax             = 0;
          int   xIdOrigenWeb            = 4;
          int   xEstadoDctoOrden        = 1;
          int   xEstadoNoMarcado        = 0;
          String xIdLista               = "1";
          int   xIdBodega               = 1;
          int xUnidadVenta              = 1;
          String xComentario            = "";
          String xIdResponsable         = "0";
          String xIdClasificacion       = "0";
          double xVrCero                = 0.0;

          //
          Day fechaHoy                  = new Day();

          //
          DctoOrdenBean dctoOrdenBean   = new DctoOrdenBean();

          //
          FachadaDctoOrdenBean fachadaDctoOrdenBean
                                        = new FachadaDctoOrdenBean();

          // Consulta si existeOrden
          dctoOrdenBean.setIdLog(xIdLog);

          //
          fachadaDctoOrdenBean          = dctoOrdenBean.listaDctoOrdenIdLog();

          //
          int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

          //
          if (fachadaDctoOrdenBean.getIdOrden()>0) {

             // SI existeOrden
             xIdOrdenMax                 = fachadaDctoOrdenBean.getIdOrden();


          } else {


             //
             String xEmail              = "";
             String xIdFormaPago        = "0";

             // NO existeOrden y se igual idLocal = idLocalInicial
             dctoOrdenBean.setIdLocal(xIdLocalUsuario);
             dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);

             //
             xIdOrdenMax                    =
                                       dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

             //
             dctoOrdenBean.setIdLocal(xIdLocalUsuario);
             dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
             dctoOrdenBean.setIdOrden(xIdOrdenMax);
             dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
             dctoOrdenBean.setEstado(xEstadoDctoOrden);
             dctoOrdenBean.setIdCliente(xIdTercero);
             dctoOrdenBean.setIdUsuario(xIdUsuario);
             dctoOrdenBean.setIdOrigen(xIdOrigenWeb);
             dctoOrdenBean.setIdLog(xIdLog);
             dctoOrdenBean.setFechaEntrega(xFechaCorte);
             dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrden).toString());
             dctoOrdenBean.setEmail(xEmail);
             dctoOrdenBean.setFormaPago(xIdFormaPago);
             dctoOrdenBean.setDiasHistoria(xDiasHistoria);             
             dctoOrdenBean.setDiasInventario(xDiasInventario);
             dctoOrdenBean.setNumeroOrden(xNumeroOrden);

             //
             boolean okIngreso            = dctoOrdenBean.ingresaDctosOrden();

          }

          //
          ColaboraPlu colaboraPlu         = new ColaboraPlu();

          //
          colaboraPlu.setIdPlu(xIdPlu);

          //
          FachadaPluBean fachadaPluBean   = new FachadaPluBean();

          //
          fachadaPluBean                  = colaboraPlu.listaUnPluFCH();

          // Igual Encabezado = Detalle en IdLocal
          DctoOrdenDetalleBean dctoOrdenDetalleBean
                                                   = new DctoOrdenDetalleBean();

          //
          dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
          dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenDetalleBean.setIdPlu(xIdPlu);          
          dctoOrdenDetalleBean.setIdLog(xIdLog);

          //
          dctoOrdenDetalleBean.retiraPlu();

          //
          dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
          dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);
          dctoOrdenDetalleBean.setCantidad(xCantidadPedida);
          dctoOrdenDetalleBean.setCantidadPedida(xCantidadPedida);
          dctoOrdenDetalleBean.setNombrePlu(fachadaPluBean.getNombreCategoria()
                                            + " " +
                                            fachadaPluBean.getNombrePlu());
          dctoOrdenDetalleBean.setNombreUnidadMedida(
                                               fachadaPluBean.getIdUVentaStr());
          dctoOrdenDetalleBean.setIdPlu(xIdPlu);
          dctoOrdenDetalleBean.setIdTipo(fachadaPluBean.getIdTipo());
          dctoOrdenDetalleBean.setEstado(xEstadoNoMarcado);
          dctoOrdenDetalleBean.setPorcentajeIva(
                                             fachadaPluBean.getPorcentajeIva());
          dctoOrdenDetalleBean.setVrVentaOriginal(fachadaPluBean.getVrGeneral());
          dctoOrdenDetalleBean.setVrCosto(fachadaPluBean.getVrCosto());
          dctoOrdenDetalleBean.setVrCostoNegociado(xVrCostoNegociado);
          dctoOrdenDetalleBean.setStrIdLista(xIdLista);
          dctoOrdenDetalleBean.setStrIdReferencia(fachadaPluBean.getIdPluStr());
          dctoOrdenDetalleBean.setPesoTeorico(0);
          dctoOrdenDetalleBean.setIdLocalSugerido(xIdLocalUsuario);
          dctoOrdenDetalleBean.setIdBodegaSugerido(
                                       new Integer(xIdLocalUsuario).toString());
         // dctoOrdenDetalleBean.setIdEstadoRefOriginal(idEstadoRefOriginal);
          dctoOrdenDetalleBean.setIdReferenciaOriginal(
                                                  fachadaPluBean.getIdPluStr());
          dctoOrdenDetalleBean.setVrDsctoPie(
                                  fachadaDctoOrdenBean.getDescuentoComercial());
          dctoOrdenDetalleBean.setFechaEntrega(xFechaCorte);

          //---
          ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                                             new ColaboraDctoOrdenDetalleBean();

          // maximoItem
          colaboraDctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);

          //
          int maximoItem = colaboraDctoOrdenDetalleBean.maximoItem(xIdLog) + 1 ;

          // Ingresa Historia
          dctoOrdenDetalleBean.setVrVentaUnitario(fachadaPluBean.getVrGeneral());
          dctoOrdenDetalleBean.setStrIdBodega(
                                       new Integer(xIdLocalUsuario).toString());
          dctoOrdenDetalleBean.setReferenciaCliente(
                                                  fachadaPluBean.getIdPluStr());
          dctoOrdenDetalleBean.setComentario(xComentario);
          dctoOrdenDetalleBean.setItem(maximoItem);
          dctoOrdenDetalleBean.setItemPadre(maximoItem);
          dctoOrdenDetalleBean.setIdResponsable(xIdResponsable);
          dctoOrdenDetalleBean.setIdClasificacion(xIdClasificacion);
          dctoOrdenDetalleBean.setIdBodega(xIdBodega);
          dctoOrdenDetalleBean.setVrImpoconsumo(
                                             fachadaPluBean.getVrImpoconsumo());
          dctoOrdenDetalleBean.setUnidadVenta(xUnidadVenta);
          dctoOrdenDetalleBean.setCantidadEntregada(xVrCero);
          dctoOrdenDetalleBean.setPesoEntregado(xVrCero);
          dctoOrdenDetalleBean.setNumeroOrden(xNumeroOrden);          
          dctoOrdenDetalleBean.setIdOperacion(xNumeroOrden);

          // ingresa OrdenDetalle
          boolean okIngresaDetalle   =
                                 dctoOrdenDetalleBean.ingresaDetalle();

          //
          return maximoItem;

  }
}

