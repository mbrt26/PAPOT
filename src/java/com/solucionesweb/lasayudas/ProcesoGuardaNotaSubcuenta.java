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

public class ProcesoGuardaNotaSubcuenta {

  public int guarda(int    xIdLog,
                    int    xIdLogNew,
                    String xIdSubcuenta,
                    String xVrUnitario,
                    int    xIdTipoOrden,
                    String xIdUsuario,
                    String xIdLocalUsuario,
                    String xIdTercero,
                    String xFechaCorte,
                    int xSignoOperacionNota) {


      System.out.println(""+xIdLog+" "+xIdLogNew+" "+xIdSubcuenta +" "+xVrUnitario+" "+xIdTipoOrden+" "+xIdUsuario+" "+xFechaCorte
              +" "+xIdLocalUsuario);


          //
          String xIdPluSubcuenta        = "1";
          int   xCantidadPedida         = 1;
          int   xIdOrdenMax             = 0;
          int   xIdOrigenWeb            = 4;
          int   xEstadoDctoOrden        = 1;
          int   xEstadoNoMarcado        = 0;
          String xIdLista               = "1";
          String xNombrePluSubcuenta    = "SUBCUENTA CONTABLE";
          int   xIdBodega               = 1;
          int   xDiasHistoria           = 0;
          int   xDiasInventario         = 0;
          double xVrCostoNegociado      = 0.0;

          //
          String xComentario            = "";
          String xIdResponsable         = "0";
          String xIdClasificacion       = "0";

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
             String xEmail              = "";
             String xIdFormaPago        = "0";

             // NO existeOrden y se igual idLocal = idLocalInicial
             dctoOrdenBean.setIdLocal(xIdLocalUsuario);
             dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);

             //
             xIdOrdenMax                    =
                                       dctoOrdenBean.maximaIdOrdenIdLocal() + 1;


             System.out.println(""+xIdOrdenMax);
             //
             dctoOrdenBean.setIdLocal(xIdLocalUsuario);
             dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
             dctoOrdenBean.setIdOrden(xIdOrdenMax);
             dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
             dctoOrdenBean.setEstado(xEstadoDctoOrden);
             dctoOrdenBean.setIdCliente(xIdTercero);
             dctoOrdenBean.setIdUsuario(xIdUsuario);
             dctoOrdenBean.setIdOrigen(xIdOrigenWeb);
             dctoOrdenBean.setIdLog(xIdLogNew);
             dctoOrdenBean.setFechaEntrega(xFechaCorte);
             dctoOrdenBean.setTipoDcto(new Integer(0).toString());
             dctoOrdenBean.setEmail(xEmail);
             dctoOrdenBean.setFormaPago(xIdFormaPago);
             dctoOrdenBean.setDiasHistoria(xDiasHistoria);
             dctoOrdenBean.setDiasInventario(xDiasInventario);
             dctoOrdenBean.setObservacion("");

             //
             boolean okIngreso            = dctoOrdenBean.ingresaDctosOrden();

   

          // Igual Encabezado = Detalle en IdLocal
          DctoOrdenDetalleBean dctoOrdenDetalleBean
                                                   = new DctoOrdenDetalleBean();

          //
          dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
          dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);
          dctoOrdenDetalleBean.setCantidad(xCantidadPedida*xSignoOperacionNota);
          dctoOrdenDetalleBean.setCantidadPedida(xCantidadPedida*xSignoOperacionNota);
          dctoOrdenDetalleBean.setNombrePlu(xNombrePluSubcuenta);
          dctoOrdenDetalleBean.setNombreUnidadMedida("0");
          dctoOrdenDetalleBean.setIdPlu(xIdPluSubcuenta);
          dctoOrdenDetalleBean.setIdTipo(5);
          dctoOrdenDetalleBean.setEstado(xEstadoNoMarcado);
          dctoOrdenDetalleBean.setPorcentajeIva(0);
          dctoOrdenDetalleBean.setVrCosto(0);
          dctoOrdenDetalleBean.setVrCostoNegociado(xVrCostoNegociado);
          dctoOrdenDetalleBean.setStrIdLista(xIdLista);
          dctoOrdenDetalleBean.setStrIdReferencia(xIdPluSubcuenta);
          dctoOrdenDetalleBean.setPesoTeorico(0);
          dctoOrdenDetalleBean.setIdLocalSugerido(xIdLocalUsuario);
          dctoOrdenDetalleBean.setIdBodegaSugerido(
                                       new Integer(xIdLocalUsuario).toString());
          dctoOrdenDetalleBean.setIdReferenciaOriginal(xIdPluSubcuenta);
          dctoOrdenDetalleBean.setVrDsctoPie(
                                  fachadaDctoOrdenBean.getDescuentoComercial());

          //
          ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                                             new ColaboraDctoOrdenDetalleBean();

          // maximoItem
          colaboraDctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);

          //
          int maximoItem = colaboraDctoOrdenDetalleBean.maximoItem(xIdLog) + 1 ;

          // Ingresa Historia
          dctoOrdenDetalleBean.setVrVentaUnitario(xVrUnitario);
          dctoOrdenDetalleBean.setStrIdBodega(
                                       new Integer(xIdLocalUsuario).toString());
          dctoOrdenDetalleBean.setReferenciaCliente(xIdPluSubcuenta);
          dctoOrdenDetalleBean.setComentario(xComentario);
          dctoOrdenDetalleBean.setItem(maximoItem);
          dctoOrdenDetalleBean.setItemPadre(maximoItem);
          dctoOrdenDetalleBean.setIdResponsable(xIdResponsable);
          dctoOrdenDetalleBean.setIdClasificacion(xIdClasificacion);
          dctoOrdenDetalleBean.setIdBodega(xIdBodega);
          dctoOrdenDetalleBean.setVrImpoconsumo(0);
          dctoOrdenDetalleBean.setVrIvaResurtido(0);
          dctoOrdenDetalleBean.setIdSubcuenta(xIdSubcuenta);

          // ingresa OrdenDetalle
          boolean okIngresaDetalle   =
                                 dctoOrdenDetalleBean.ingresaDetalle();

          //
          return maximoItem;

  }
}

