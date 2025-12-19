package com.solucionesweb.lasayudas;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el ContableRetencionBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoIngresoRetal{

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoIngresoRetal () { }

  public boolean ingresa(int xIdLocal,
                      int xIdTipoOrdenNew,
                      double xIdVendedor,
                      int xIdFicha,
                      int xIdOperacion,
                      double xPesoTerminada,
                      double xCantidadTerminada,
                      double xTotalMP,
                      String xIdCliente,
                      int xIdOrdenOrigen,
                      int xIdLocalOrigen,
                      int xIdTipoOrdenOrigen,
                      int xNumeroOrden,
                      int xItemPadre)  {

      //
      double xVrPago               = 0.0;
      int xEstadoVisita            = 1;
      int xEstadoCerrado           = 1;
      int xIdTipoNegocioVenta      = 1;
      double xVrRetencion          = 0.0;
      double xVrCero               = 0.0;
      int xIdEscala                = 610;
      int xIdBodegaSiguienteRetales= 666;

      //
      if (xTotalMP==0) {xTotalMP = xPesoTerminada;}

      //------------------------------------------------------------------------
      FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

      //
      JobOperacionBean jobOperacionBean = new JobOperacionBean();

      //
      jobOperacionBean.setIdOperacion(xIdOperacion);

      //
      fachadaJobOperacion = jobOperacionBean.listaOperacionActualSiguienteFCH(
                                                            xIdFicha,
                                                            xIdEscala);

      //
      //int xIdBodegaAnterior = fachadaJobOperacion.getIdOperacionAnterior();
      int xIdBodegaActual = fachadaJobOperacion.getIdOperacion();
      int xIdBodegaSiguiente = fachadaJobOperacion.getIdOperacionSiguiente();
      
      //
      xIdBodegaSiguiente = 666;

      //------------------------------------------------------------------------
      Day fechaHoy                 = new Day();

      String xFechaHoy             = fechaHoy.getFechaFormateada();

      //
      FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

      //
      TerceroBean  terceroBean     = new TerceroBean();

      //
      terceroBean.setIdCliente(xIdCliente);

      //
      fachadaTerceroBean = terceroBean.listaUnTerceroFachada();

      //
      AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

      //
      int xIdLogMax                   = agendaLogVisitaBean.maximoIdLog() + 1;

      //
      agendaLogVisitaBean.setIdLog(xIdLogMax);
      agendaLogVisitaBean.setIdCliente(xIdCliente);
      agendaLogVisitaBean.setIdUsuario(xIdVendedor);
      agendaLogVisitaBean.setIdLocal(xIdLocal);
      agendaLogVisitaBean.setFechaVisita(xFechaHoy);
      agendaLogVisitaBean.setIdEstadoVisita(xEstadoVisita);
      agendaLogVisitaBean.setEstado(xEstadoCerrado);
      agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenNew);

      //
      agendaLogVisitaBean.ingresa();

      //------------------------------------------------------------------------
      DctoOrdenBean dctoOrdenBean  = new DctoOrdenBean();

      //
      int xEstadoDcto              = 1;
      int  xIOrigenBB              = 1;
      String xIdRazonVacia         = "01";
      int xIdEstadoTx              = 2;  // autorizado
      int xIdTipoTx                = 1;
      String xIdResponsable        = "0";

      //
      int xDiasHistoria            = 0;
      int xDiasInventario          = 0;

      // Encabezado Dctos
      double  xIdUsuario       = xIdVendedor;
      String  email            = fachadaTerceroBean.getEmail();
      String  fax              = fachadaTerceroBean.getTelefonoFax();
      String  contacto         = fachadaTerceroBean.getContactoTercero();
      String  observacion      = "";
      String  direccionDespacho= fachadaTerceroBean.getDireccionTercero();
      String  ciudadDespacho   = fachadaTerceroBean.getCiudadTercero();
      String xNombreTercero    = fachadaTerceroBean.getNombreTercero();
      int xIdFormaPago              = fachadaTerceroBean.getIdFormaPago();

      //------------------
      String  ordenCompra          = "0";
      String  descuentoComercial   = "0";
      String  impuestoVenta        = "0";
      int xIndicador               = 1;
      int xIdTipoOrdenTraslado     = 4;

      //------------------------------------------------------------------------
      dctoOrdenBean.setIdLocal(xIdLocal);

      //
      int xIdOrdenMax                  =
                                      dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

      // ingresaPedido
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenBean.setIdOrden(xIdOrdenMax);
      dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
      dctoOrdenBean.setEstado(xEstadoDcto);
      dctoOrdenBean.setIdCliente(xIdCliente);
      dctoOrdenBean.setIdUsuario(xIdUsuario);
      dctoOrdenBean.setIdOrigen(xIOrigenBB);
      dctoOrdenBean.setIdLog(xIdLogMax);
      dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
      dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrdenNew).toString());
      dctoOrdenBean.setEmail(email);
      dctoOrdenBean.setFax(fax);
      dctoOrdenBean.setContacto(contacto);
      dctoOrdenBean.setObservacion(observacion);
      dctoOrdenBean.setDireccionDespacho(direccionDespacho);
      dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
      dctoOrdenBean.setFormaPago(new Integer(xIdFormaPago).toString());
      dctoOrdenBean.setOrdenCompra(ordenCompra);
      dctoOrdenBean.setDescuentoComercial(descuentoComercial);
      dctoOrdenBean.setImpuestoVenta(impuestoVenta);
      dctoOrdenBean.setIdRazon(xIdRazonVacia);
      dctoOrdenBean.setIdEstadoTx(xIdEstadoTx);
      dctoOrdenBean.setIdTipoTx(xIdTipoTx);
      dctoOrdenBean.setNumeroOrden(xNumeroOrden);
      dctoOrdenBean.setIdResponsable(xIdResponsable);
      dctoOrdenBean.setDiasHistoria(xDiasHistoria);
      dctoOrdenBean.setDiasInventario(xDiasInventario);

      //
      dctoOrdenBean.ingresaDctosOrden();

      //------------------------------------------------------------------------
      DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //------------------------------------------------------------------------
      dctoOrdenDetalleBean.ingresaProductoTerminado(xIdFicha,
                                             xIdOperacion,
                                             xPesoTerminada,
                                             xCantidadTerminada,
                                             xTotalMP,
                                             xIdOrdenOrigen,
                                             xIdLocalOrigen,
                                             xIdTipoOrdenOrigen,
                                             xIdBodegaSiguiente,
                                             xItemPadre);
      //
      int xIdSignoEntrada = -1;
      dctoOrdenDetalleBean.setIdBodega(xIdBodegaSiguiente);

      //
      //dctoOrdenDetalleBean.actualizaBodega(xIdSignoEntrada);

      //
      int xIdSignoSalida  = +1;
      dctoOrdenDetalleBean.setIdBodega(xIdBodegaActual);

      //
      //dctoOrdenDetalleBean.actualizaBodega(xIdSignoSalida);

      //------------------------------------------------------------------------
      dctoOrdenDetalleBean.ingresaProduccionMP(xIdFicha,
                                             xIdOperacion,
                                             xPesoTerminada,
                                             xTotalMP,
                                             xIdOrdenOrigen,
                                             xIdLocalOrigen,
                                             xIdTipoOrdenOrigen,
                                             xIdTipoOrdenTraslado,
                                             xIdBodegaActual,
                                             xItemPadre);

        //----------------------------------------------------------------------
        dctoOrdenDetalleBean.setIdBodega(xIdBodegaActual);
        dctoOrdenDetalleBean.setIdLocal(xIdLocalOrigen);
        dctoOrdenDetalleBean.setIdTipoOrdenOrigen(xIdTipoOrdenOrigen);
        dctoOrdenDetalleBean.setIdOrdenOrigen(xIdOrdenOrigen);
        dctoOrdenDetalleBean.setIdBodega(xIdOperacion);

        //
        boolean xExisteTrasladoMP = dctoOrdenDetalleBean.validaTrasladoMP(
                                                          xIdTipoOrdenTraslado);

        //
        if (xExisteTrasladoMP) {

            //---
            dctoOrdenDetalleBean.ingresaProduccionMP_Traslado(xIdFicha,
                    xIdOperacion,
                    xPesoTerminada,
                    xTotalMP,
                    xIdOrdenOrigen,
                    xIdLocalOrigen,
                    xIdTipoOrdenOrigen,
                    xIdTipoOrdenTraslado,
                    xIdBodegaActual,
                    xItemPadre);


        } else {

            //---
            dctoOrdenDetalleBean.ingresaProduccionMP(xIdFicha,
                    xIdOperacion,
                    xPesoTerminada,
                    xTotalMP,
                    xIdOrdenOrigen,
                    xIdLocalOrigen,
                    xIdTipoOrdenOrigen,
                    xIdTipoOrdenTraslado,
                    xIdBodegaActual,
                    xItemPadre);

        }

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //
      dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoSale(xIdBodegaActual);

      //
      dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoEntra(xIdBodegaSiguienteRetales);

      //------------------
      DctoBean dctoBean             = new DctoBean();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIndicador(xIndicador);

      // PASTICAUCA / NO POS
      int xIdDctoMax                = dctoBean.maximoDctoLocalIndicador() + 1;

      //
      FachadaColaboraDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaColaboraDctoOrdenBean();

      //
      ColaboraDctoOrdenBean colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

      //
      colaboraDctoOrdenBean.setIdLog(xIdLogMax);
      colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);

      //
      fachadaDctoOrdenBean    = colaboraDctoOrdenBean.liquidaOrdenFCH();

      // --
      double xVrVentaSinIva     = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrVentaSinDscto   = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrIva             = fachadaDctoOrdenBean.getVrIva();
      double xVrCosto           = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrImpoconsumo     = fachadaDctoOrdenBean.getVrImpoconsumo();
      double xVrCostoIND        = fachadaDctoOrdenBean.getVrCostoSinIva();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIdOrden(xIdOrdenMax);
      dctoBean.setIndicador(xIndicador);
      dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
      dctoBean.setIdDcto(xIdDctoMax);
      dctoBean.setVrBase(xVrVentaSinDscto);
      dctoBean.setVrPago(xVrPago);
      dctoBean.setEstado(1);
      dctoBean.setVrIva(xVrIva);
      dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
      dctoBean.setVrRteFuente(xVrRetencion);
      dctoBean.setVrDescuento(xVrVentaSinIva -
                              xVrVentaSinDscto );
      dctoBean.setVrRteIva(xVrCero);
      dctoBean.setVrRteIca(xVrCero);
      dctoBean.setIdUsuario(xIdUsuario);
      dctoBean.setIdCliente(xIdCliente);
      dctoBean.setDiasPlazo(xIdFormaPago);
      dctoBean.setPorcentajeDscto(xVrCero);
      dctoBean.setIdCausa((int)xVrCero);
      dctoBean.setIdDctoNitCC(new Integer(xIdDctoMax).toString());
      dctoBean.setFechaDctoNitCC(fechaHoy.getFechaFormateada());
      dctoBean.setVrPagarDctoNitCC(xVrCero);
      dctoBean.setVrDsctoFcro(xVrCero);
      dctoBean.setIdDctoCruce(xVrCero);
      dctoBean.setVrCostoMV(xVrCosto);
      dctoBean.setEstadoDcto((int)xVrCero);
      dctoBean.setIdLocalAdicional(xIdLocal);
      dctoBean.setIdPeriodo((int)xVrCero);
      dctoBean.setNombreTercero(xNombreTercero);
      dctoBean.setIdVendedor(xIdVendedor);
      dctoBean.setVrImpoconsumo(xVrImpoconsumo);
      dctoBean.setVrCostoIND(xVrCostoIND);

      //
      dctoBean.ingresaDcto();

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

      // actualizaInventarioEstado
      procesoIventario.actualizaInventarioEstado(xIdLocal,
                                                 xIdTipoOrdenNew,
                                                 xIdOrdenMax);

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      // actualizaEstadoInventario
      int xEstadoInventario = 4;

      //
      boolean xOkIngreso =
              dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      // actualizaEstadoInventario
      xEstadoInventario = 4;

      //
      xOkIngreso =
              dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      return xOkIngreso;

  }
  public boolean ingresaRetal(int xIdLocal,
                      int xIdTipoOrdenNew,
                      double xIdVendedor,
                      int xIdFicha,
                      int xIdOperacion,
                      double xPesoTerminada,
                      double xCantidadTerminada,
                      double xTotalMP,
                      String xIdCliente,
                      int xIdOrdenOrigen,
                      int xIdLocalOrigen,
                      int xIdTipoOrdenOrigen,
                      int xNumeroOrden,
                      int xItemPadre)  {

      //
      double xVrPago               = 0.0;
      int xEstadoVisita            = 1;
      int xEstadoCerrado           = 1;
      int xIdTipoNegocioVenta      = 1;
      double xVrRetencion          = 0.0;
      double xVrCero               = 0.0;
      int xIdEscala                = 610;
      int xIdBodegaSiguienteRetales= 666;

      //
      if (xTotalMP==0){ xTotalMP = xPesoTerminada;}

      //------------------------------------------------------------------------
      FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

      //
      JobOperacionBean jobOperacionBean = new JobOperacionBean();

      //
      jobOperacionBean.setIdOperacion(xIdOperacion);

      //
      fachadaJobOperacion = jobOperacionBean.listaOperacionActualSiguienteFCH(
                                                            xIdFicha,
                                                            xIdEscala);

      //
      //int xIdBodegaAnterior = fachadaJobOperacion.getIdOperacionAnterior();
      int xIdBodegaActual = fachadaJobOperacion.getIdOperacion();
      int xIdBodegaSiguiente = fachadaJobOperacion.getIdOperacionSiguiente();
      
      //
      xIdBodegaSiguiente = 666;

      //------------------------------------------------------------------------
      Day fechaHoy                 = new Day();

      String xFechaHoy             = fechaHoy.getFechaFormateada();

      //
      FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

      //
      TerceroBean  terceroBean     = new TerceroBean();

      //
      terceroBean.setIdCliente(xIdCliente);

      //
      fachadaTerceroBean = terceroBean.listaUnTerceroFachada();

      //
      AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

      //
      int xIdLogMax                   = agendaLogVisitaBean.maximoIdLog() + 1;

      //
      agendaLogVisitaBean.setIdLog(xIdLogMax);
      agendaLogVisitaBean.setIdCliente(xIdCliente);
      agendaLogVisitaBean.setIdUsuario(xIdVendedor);
      agendaLogVisitaBean.setIdLocal(xIdLocal);
      agendaLogVisitaBean.setFechaVisita(xFechaHoy);
      agendaLogVisitaBean.setIdEstadoVisita(xEstadoVisita);
      agendaLogVisitaBean.setEstado(xEstadoCerrado);
      agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenNew);

      //
      agendaLogVisitaBean.ingresa();

      //------------------------------------------------------------------------
      DctoOrdenBean dctoOrdenBean  = new DctoOrdenBean();

      //
      int xEstadoDcto              = 1;
      int  xIOrigenBB              = 1;
      String xIdRazonVacia         = "01";
      int xIdEstadoTx              = 2;  // autorizado
      int xIdTipoTx                = 1;
      String xIdResponsable        = "0";

      //
      int xDiasHistoria            = 0;
      int xDiasInventario          = 0;

      // Encabezado Dctos
      double  xIdUsuario       = xIdVendedor;
      String  email            = fachadaTerceroBean.getEmail();
      String  fax              = fachadaTerceroBean.getTelefonoFax();
      String  contacto         = fachadaTerceroBean.getContactoTercero();
      String  observacion      = "";
      String  direccionDespacho= fachadaTerceroBean.getDireccionTercero();
      String  ciudadDespacho   = fachadaTerceroBean.getCiudadTercero();
      String xNombreTercero    = fachadaTerceroBean.getNombreTercero();
      int xIdFormaPago              = fachadaTerceroBean.getIdFormaPago();

      //------------------
      String  ordenCompra          = "0";
      String  descuentoComercial   = "0";
      String  impuestoVenta        = "0";
      int xIndicador               = 1;
      int xIdTipoOrdenTraslado     = 4;

      //------------------------------------------------------------------------
      dctoOrdenBean.setIdLocal(xIdLocal);

      //
      int xIdOrdenMax                  =
                                      dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

      // ingresaPedido
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenBean.setIdOrden(xIdOrdenMax);
      dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
      dctoOrdenBean.setEstado(xEstadoDcto);
      dctoOrdenBean.setIdCliente(xIdCliente);
      dctoOrdenBean.setIdUsuario(xIdUsuario);
      dctoOrdenBean.setIdOrigen(xIOrigenBB);
      dctoOrdenBean.setIdLog(xIdLogMax);
      dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
      dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrdenNew).toString());
      dctoOrdenBean.setEmail(email);
      dctoOrdenBean.setFax(fax);
      dctoOrdenBean.setContacto(contacto);
      dctoOrdenBean.setObservacion(observacion);
      dctoOrdenBean.setDireccionDespacho(direccionDespacho);
      dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
      dctoOrdenBean.setFormaPago(new Integer(xIdFormaPago).toString());
      dctoOrdenBean.setOrdenCompra(ordenCompra);
      dctoOrdenBean.setDescuentoComercial(descuentoComercial);
      dctoOrdenBean.setImpuestoVenta(impuestoVenta);
      dctoOrdenBean.setIdRazon(xIdRazonVacia);
      dctoOrdenBean.setIdEstadoTx(xIdEstadoTx);
      dctoOrdenBean.setIdTipoTx(xIdTipoTx);
      dctoOrdenBean.setNumeroOrden(xNumeroOrden);
      dctoOrdenBean.setIdResponsable(xIdResponsable);
      dctoOrdenBean.setDiasHistoria(xDiasHistoria);
      dctoOrdenBean.setDiasInventario(xDiasInventario);

      //
      dctoOrdenBean.ingresaDctosOrden();

      //------------------------------------------------------------------------
      DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //------------------------------------------------------------------------
      dctoOrdenDetalleBean.ingresaProductoTerminado(xIdFicha,
                                             xIdOperacion,
                                             xPesoTerminada,
                                             xCantidadTerminada,
                                             xTotalMP,
                                             xIdOrdenOrigen,
                                             xIdLocalOrigen,
                                             xIdTipoOrdenOrigen,
                                             xIdBodegaSiguiente,
                                             xItemPadre);
      //
      int xIdSignoEntrada = +1;
      dctoOrdenDetalleBean.setIdBodega(xIdBodegaSiguiente);

      //
      dctoOrdenDetalleBean.actualizaBodega(xIdSignoEntrada);

      //
      int xIdSignoSalida  = -1;
      dctoOrdenDetalleBean.setIdBodega(xIdBodegaSiguiente);

      //
      dctoOrdenDetalleBean.actualizaBodega(xIdSignoSalida);

      //------------------------------------------------------------------------
      dctoOrdenDetalleBean.ingresaProduccionMP(xIdFicha,
                                             xIdOperacion,
                                             xPesoTerminada,
                                             xTotalMP,
                                             xIdOrdenOrigen,
                                             xIdLocalOrigen,
                                             xIdTipoOrdenOrigen,
                                             xIdTipoOrdenTraslado,
                                             xIdBodegaSiguiente,
                                             xItemPadre);

        //----------------------------------------------------------------------
        dctoOrdenDetalleBean.setIdBodega(xIdBodegaSiguiente);
        dctoOrdenDetalleBean.setIdLocal(xIdLocalOrigen);
        dctoOrdenDetalleBean.setIdTipoOrdenOrigen(xIdTipoOrdenOrigen);
        dctoOrdenDetalleBean.setIdOrdenOrigen(xIdOrdenOrigen);
        dctoOrdenDetalleBean.setIdBodega(xIdOperacion);

        //
        boolean xExisteTrasladoMP = dctoOrdenDetalleBean.validaTrasladoMP(
                                                          xIdTipoOrdenTraslado);

        //
        if (xExisteTrasladoMP) {

            //---
            dctoOrdenDetalleBean.ingresaProduccionMP_Traslado(xIdFicha,
                    xIdOperacion,
                    xPesoTerminada,
                    xTotalMP,
                    xIdOrdenOrigen,
                    xIdLocalOrigen,
                    xIdTipoOrdenOrigen,
                    xIdTipoOrdenTraslado,
                    xIdBodegaSiguiente,
                    xItemPadre);


        } else {

            //---
            dctoOrdenDetalleBean.ingresaProduccionMP(xIdFicha,
                    xIdOperacion,
                    xPesoTerminada,
                    xTotalMP,
                    xIdOrdenOrigen,
                    xIdLocalOrigen,
                    xIdTipoOrdenOrigen,
                    xIdTipoOrdenTraslado,
                    xIdBodegaSiguiente,
                    xItemPadre);

        }

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //
      dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoSale(xIdBodegaActual);

      //
      dctoOrdenDetalleBean.actualizaBodegaMP_TrasladoEntra(xIdBodegaSiguienteRetales);

      //------------------
      DctoBean dctoBean             = new DctoBean();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIndicador(xIndicador);

      // PASTICAUCA / NO POS
      int xIdDctoMax                = dctoBean.maximoDctoLocalIndicador() + 1;

      //
      FachadaColaboraDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaColaboraDctoOrdenBean();

      //
      ColaboraDctoOrdenBean colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

      //
      colaboraDctoOrdenBean.setIdLog(xIdLogMax);
      colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);

      //
      fachadaDctoOrdenBean    = colaboraDctoOrdenBean.liquidaOrdenFCH();

      // --
      double xVrVentaSinIva     = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrVentaSinDscto   = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrIva             = fachadaDctoOrdenBean.getVrIva();
      double xVrCosto           = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrImpoconsumo     = fachadaDctoOrdenBean.getVrImpoconsumo();
      double xVrCostoIND        = fachadaDctoOrdenBean.getVrCostoSinIva();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIdOrden(xIdOrdenMax);
      dctoBean.setIndicador(xIndicador);
      dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
      dctoBean.setIdDcto(xIdDctoMax);
      dctoBean.setVrBase(xVrVentaSinDscto);
      dctoBean.setVrPago(xVrPago);
      dctoBean.setEstado(1);
      dctoBean.setVrIva(xVrIva);
      dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
      dctoBean.setVrRteFuente(xVrRetencion);
      dctoBean.setVrDescuento(xVrVentaSinIva -
                              xVrVentaSinDscto );
      dctoBean.setVrRteIva(xVrCero);
      dctoBean.setVrRteIca(xVrCero);
      dctoBean.setIdUsuario(xIdUsuario);
      dctoBean.setIdCliente(xIdCliente);
      dctoBean.setDiasPlazo(xIdFormaPago);
      dctoBean.setPorcentajeDscto(xVrCero);
      dctoBean.setIdCausa((int)xVrCero);
      dctoBean.setIdDctoNitCC(new Integer(xIdDctoMax).toString());
      dctoBean.setFechaDctoNitCC(fechaHoy.getFechaFormateada());
      dctoBean.setVrPagarDctoNitCC(xVrCero);
      dctoBean.setVrDsctoFcro(xVrCero);
      dctoBean.setIdDctoCruce(xVrCero);
      dctoBean.setVrCostoMV(xVrCosto);
      dctoBean.setEstadoDcto((int)xVrCero);
      dctoBean.setIdLocalAdicional(xIdLocal);
      dctoBean.setIdPeriodo((int)xVrCero);
      dctoBean.setNombreTercero(xNombreTercero);
      dctoBean.setIdVendedor(xIdVendedor);
      dctoBean.setVrImpoconsumo(xVrImpoconsumo);
      dctoBean.setVrCostoIND(xVrCostoIND);

      //
      dctoBean.ingresaDcto();

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

      // actualizaInventarioEstado
      procesoIventario.actualizaInventarioBodegaRetal(xIdLocal,
                                                 xIdTipoOrdenNew,
                                                 xIdOrdenMax,
                                                 xIdBodegaActual,
                                                 xIdBodegaSiguiente,
                                                 xIdSignoEntrada,
                                                 xIdSignoSalida);

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      // actualizaEstadoInventario
      int xEstadoInventario = 4;

      //
      boolean xOkIngreso =
              dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

//      // actualizaEstadoInventario
//      xEstadoInventario = 4;
//
//      //
//      xOkIngreso =
//              dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      return xOkIngreso;

  }

  public boolean retira(int xIdLocal,
                      int xIdTipoOrdenNew,
                      double xIdVendedor,
                      int xIdFicha,
                      int xIdOperacion,
                      double xPesoTerminada,
                      double xCantidadTerminada,
                      double xTotalMP,
                      String xIdCliente,
                      int xIdOrdenOrigen,
                      int xIdLocalOrigen,
                      int xIdTipoOrdenOrigen,
                      int xNumeroOrden,
                      int xItemPadre,
                      String xObservacion)  {

      //
      double xVrPago               = 0.0;
      int xEstadoVisita            = 1;
      int xEstadoCerrado           = 1;
      int xIdTipoNegocioVenta      = 1;
      double xVrRetencion          = 0.0;
      double xVrCero               = 0.0;
      int xIdEscala                = 610;

      //
      if (xTotalMP==0) xTotalMP = xPesoTerminada;

      //------------------------------------------------------------------------
      FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

      //
      JobOperacionBean jobOperacionBean = new JobOperacionBean();

      //
      jobOperacionBean.setIdOperacion(xIdOperacion);

      //
      fachadaJobOperacion = jobOperacionBean.listaOperacionAnteriorActualFCH(
                                                            xIdFicha,
                                                            xIdEscala);

      //
      int xIdBodegaAnterior = fachadaJobOperacion.getIdOperacionAnterior();
      int xIdBodegaActual = fachadaJobOperacion.getIdOperacion();

      //------------------------------------------------------------------------
      Day fechaHoy                 = new Day();

      String xFechaHoy             = fechaHoy.getFechaFormateada();

      //
      FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

      //
      TerceroBean  terceroBean     = new TerceroBean();

      //
      terceroBean.setIdCliente(xIdCliente);

      //
      fachadaTerceroBean = terceroBean.listaUnTerceroFachada();

      //
      AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

      //
      int xIdLogMax                   = agendaLogVisitaBean.maximoIdLog() + 1;

      //
      agendaLogVisitaBean.setIdLog(xIdLogMax);
      agendaLogVisitaBean.setIdCliente(xIdCliente);
      agendaLogVisitaBean.setIdUsuario(xIdVendedor);
      agendaLogVisitaBean.setIdLocal(xIdLocal);
      agendaLogVisitaBean.setFechaVisita(xFechaHoy);
      agendaLogVisitaBean.setIdEstadoVisita(xEstadoVisita);
      agendaLogVisitaBean.setEstado(xEstadoCerrado);
      agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenNew);

      //
      agendaLogVisitaBean.ingresa();

      //------------------------------------------------------------------------
      DctoOrdenBean dctoOrdenBean  = new DctoOrdenBean();

      //
      int xEstadoDcto              = 1;
      int  xIOrigenBB              = 1;
      String xIdRazonVacia         = "01";
      int xIdEstadoTx              = 2;  // autorizado
      int xIdTipoTx                = 1;
      String xIdResponsable        = "0";

      //
      int xDiasHistoria            = 0;
      int xDiasInventario          = 0;

      // Encabezado Dctos
      double  xIdUsuario       = xIdVendedor;
      String  email            = fachadaTerceroBean.getEmail();
      String  fax              = fachadaTerceroBean.getTelefonoFax();
      String  contacto         = fachadaTerceroBean.getContactoTercero();
      String  observacion      = xObservacion;
      String  direccionDespacho= fachadaTerceroBean.getDireccionTercero();
      String  ciudadDespacho   = fachadaTerceroBean.getCiudadTercero();
      String  formaPago        = fachadaTerceroBean.getIdFormaPagoStr();
      String xNombreTercero    = fachadaTerceroBean.getNombreTercero();
      int xIdFormaPago              = fachadaTerceroBean.getIdFormaPago();

      //------------------
      String  ordenCompra          = "0";
      String  descuentoComercial   = "0";
      String  impuestoVenta        = "0";
      int xIndicador               = 1;
      int xIdTipoOrdenTraslado     = 4;

      //------------------------------------------------------------------------
      dctoOrdenBean.setIdLocal(xIdLocal);

      //
      int xIdOrdenMax                  =
                                      dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

      // ingresaPedido
      dctoOrdenBean.setIdLocal(xIdLocal);
      dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenBean.setIdOrden(xIdOrdenMax);
      dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
      dctoOrdenBean.setEstado(xEstadoDcto);
      dctoOrdenBean.setIdCliente(xIdCliente);
      dctoOrdenBean.setIdUsuario(xIdUsuario);
      dctoOrdenBean.setIdOrigen(xIOrigenBB);
      dctoOrdenBean.setIdLog(xIdLogMax);
      dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
      dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrdenNew).toString());
      dctoOrdenBean.setEmail(email);
      dctoOrdenBean.setFax(fax);
      dctoOrdenBean.setContacto(contacto);
      dctoOrdenBean.setObservacion(observacion);
      dctoOrdenBean.setDireccionDespacho(direccionDespacho);
      dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
      dctoOrdenBean.setFormaPago(new Integer(xIdFormaPago).toString());
      dctoOrdenBean.setOrdenCompra(ordenCompra);
      dctoOrdenBean.setDescuentoComercial(descuentoComercial);
      dctoOrdenBean.setImpuestoVenta(impuestoVenta);
      dctoOrdenBean.setIdRazon(xIdRazonVacia);
      dctoOrdenBean.setIdEstadoTx(xIdEstadoTx);
      dctoOrdenBean.setIdTipoTx(xIdTipoTx);
      dctoOrdenBean.setNumeroOrden(xNumeroOrden);
      dctoOrdenBean.setIdResponsable(xIdResponsable);
      dctoOrdenBean.setDiasHistoria(xDiasHistoria);
      dctoOrdenBean.setDiasInventario(xDiasInventario);

      //
      dctoOrdenBean.ingresaDctosOrden();

      //------------------------------------------------------------------------
      DctoOrdenDetalleBean dctoOrdenDetalleBean
                                       = new DctoOrdenDetalleBean();

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //------------------------------------------------------------------------
      dctoOrdenDetalleBean.ingresaProductoTerminado(xIdFicha,
                                             xIdOperacion,
                                             xPesoTerminada,
                                             xCantidadTerminada,
                                             xTotalMP,
                                             xIdOrdenOrigen,
                                             xIdLocalOrigen,
                                             xIdTipoOrdenOrigen,
                                             xIdBodegaAnterior,
                                             xItemPadre);


      //
      int xIdSignoEntrada = -1;
      dctoOrdenDetalleBean.setIdBodega(xIdBodegaActual);
      dctoOrdenDetalleBean.actualizaBodega(xIdSignoEntrada);

      //
      int xIdSignoSalida  = +1;
      dctoOrdenDetalleBean.setIdBodega(xIdBodegaAnterior);
      dctoOrdenDetalleBean.actualizaBodega(xIdSignoSalida);


      //------------------------------------------------------------------------
      dctoOrdenDetalleBean.ingresaProduccionMP(xIdFicha,
                                             xIdOperacion,
                                             xPesoTerminada,
                                             xTotalMP,
                                             xIdOrdenOrigen,
                                             xIdLocalOrigen,
                                             xIdTipoOrdenOrigen,
                                             xIdTipoOrdenTraslado,
                                             xIdBodegaAnterior,
                                             xItemPadre);

      //
      dctoOrdenDetalleBean.actualizaBodegaRetiro();

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      //
      dctoOrdenDetalleBean.actualizaBodegaMP();

      //------------------
      DctoBean dctoBean             = new DctoBean();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIndicador(xIndicador);

      // PASTICAUCA / NO POS
      int xIdDctoMax                = dctoBean.maximoDctoLocalIndicador() + 1;

      //
      FachadaColaboraDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaColaboraDctoOrdenBean();

      //
      ColaboraDctoOrdenBean colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

      //
      colaboraDctoOrdenBean.setIdLog(xIdLogMax);
      colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNew);

      //
      fachadaDctoOrdenBean    = colaboraDctoOrdenBean.liquidaOrdenFCH();

      // --
      double xVrVentaSinIva     = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrVentaSinDscto   = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrIva             = fachadaDctoOrdenBean.getVrIva();
      double xVrCosto           = fachadaDctoOrdenBean.getVrCostoSinIva();
      double xVrImpoconsumo     = fachadaDctoOrdenBean.getVrImpoconsumo();
      double xVrCostoIND        = fachadaDctoOrdenBean.getVrCostoSinIva();

      //
      dctoBean.setIdLocal(xIdLocal);
      dctoBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoBean.setIdOrden(xIdOrdenMax);
      dctoBean.setIndicador(xIndicador);
      dctoBean.setFechaDcto(fechaHoy.getFechaFormateada());
      dctoBean.setIdDcto(xIdDctoMax);
      dctoBean.setVrBase(xVrVentaSinDscto);
      dctoBean.setVrPago(xVrPago);
      dctoBean.setEstado(1);
      dctoBean.setVrIva(xVrIva);
      dctoBean.setIdTipoNegocio(xIdTipoNegocioVenta);
      dctoBean.setVrRteFuente(xVrRetencion);
      dctoBean.setVrDescuento(xVrVentaSinIva -
                              xVrVentaSinDscto );
      dctoBean.setVrRteIva(xVrCero);
      dctoBean.setVrRteIca(xVrCero);
      dctoBean.setIdUsuario(xIdUsuario);
      dctoBean.setIdCliente(xIdCliente);
      dctoBean.setDiasPlazo(xIdFormaPago);
      dctoBean.setPorcentajeDscto(xVrCero);
      dctoBean.setIdCausa((int)xVrCero);
      dctoBean.setIdDctoNitCC(new Integer(xIdDctoMax).toString());
      dctoBean.setFechaDctoNitCC(fechaHoy.getFechaFormateada());
      dctoBean.setVrPagarDctoNitCC(xVrCero);
      dctoBean.setVrDsctoFcro(xVrCero);
      dctoBean.setIdDctoCruce(xVrCero);
      dctoBean.setVrCostoMV(xVrCosto);
      dctoBean.setEstadoDcto((int)xVrCero);
      dctoBean.setIdLocalAdicional(xIdLocal);
      dctoBean.setIdPeriodo((int)xVrCero);
      dctoBean.setNombreTercero(xNombreTercero);
      dctoBean.setIdVendedor(xIdVendedor);
      dctoBean.setVrImpoconsumo(xVrImpoconsumo);
      dctoBean.setVrCostoIND(xVrCostoIND);

      //
      dctoBean.ingresaDcto();

      //
      ProcesoInventario procesoIventario
                                       = new ProcesoInventario();

      // actualizaInventarioEstado
      procesoIventario.actualizaInventarioEstado(xIdLocal,
                                                 xIdTipoOrdenNew,
                                                 xIdOrdenMax);

      //
      dctoOrdenDetalleBean.setIdLocal(xIdLocal);
      dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenNew);
      dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

      // actualizaEstadoInventario
      int xEstadoInventario = 4;

      //
      boolean xOkIngreso =
              dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      // actualizaEstadoInventario
      xEstadoInventario = 4;

      //
      xOkIngreso =
              dctoOrdenDetalleBean.actualizaEstadoInventario(xEstadoInventario);

      return xOkIngreso;

  }

}