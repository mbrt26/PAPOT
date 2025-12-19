package com.solucionesweb.losservlets;

import co.linxsi.controlador.cliente.cotizacion.Control_Cliente_Cotizacion;
import co.linxsi.controlador.cliente.cotizacion2.Control_Cliente_Cotizacion2;
import co.linxsi.controlador.touch.edicion.Controller_Produccion_Edicion;
import java.io.*;
import java.util.*;
import java.util.Enumeration;

// Import Servlets Packages
import javax.servlet.*;
import javax.servlet.http.*;
// Importa la clase usuarioBean para validar la existencia de este dentro de la session
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase PermisoUsuarioBean para validar los permisos del ususario a la pagina solicitada
import com.solucionesweb.losbeans.utilidades.PermisoUsuarioBean;

// Importa el bean de control de errores de logica de la aplicacion
import com.solucionesweb.losbeans.utilidades.ErrorAplicacionBean;

// El Controlador de Request de la aplicacion busca cual es la clase que manejara el request
public class GralControladorServlet extends HttpServlet {

    // Tabla hash para relacionar las paginas que hacen el request
    private Map manejadorHash = new HashMap();

    public void init() throws ServletException {

        manejadorHash.put("/jsp/gralFrmLogin.jsp", new GralLogicaLoginManejadorRequest());
        manejadorHash.put("/vtaPermisosRegresar.ctr", new GralLogicaRegresarManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteSeleccionarNombre.ctr", new VtasControlClienteSeleccionarNombreManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteSeleccionarNombre.jsp", new VtasAdmClienteSeleccionarNombreManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteConsultarDatos.ctr", new VtasControlClienteConsultarDatosManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConClienteConsultarDatos.jsp", new VtasAdmClienteConsultarDatosManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteConsultarCxCConsolidada.ctr", new VtasControlClienteConsultarCxCConsolidadaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConClienteConsultarCxCConsolidada.jsp", new VtasAdmConsultarCxCConsolidadaManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteConsultarHPedido.ctr", new VtasControlClienteConsultarHPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteConsultarHPedido.jsp", new VtasAdmClienteConsultarHPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstClienteConsultarHPedido.jsp", new VtasAdmClienteConsultarHPedidoManejadorRequest());

        manejadorHash.put("/potPermisoClienteConsultarHPago.ctr", new VtasControlClienteConsultarHPagoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteConsultarHPago.jsp", new VtasAdmClienteConsultarHPagoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstClienteConsultarHPago.jsp", new VtasAdmClienteConsultarHPagoManejadorRequest());

        manejadorHash.put("/vtaPermisoClienteElaboraPedido.ctr", new VtasControlClienteElaboraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteElaboraPedido.jsp", new VtasAdmClienteElaboraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModClienteElaboraPedido.jsp", new VtasAdmClienteElaboraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelClienteElaboraPedido.jsp", new VtasAdmClienteElaboraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmDesClienteElaboraPedido.jsp", new VtasAdmClienteElaboraPedidoManejadorRequest());

        manejadorHash.put("/vtaPermisoClienteCotiza.ctr", new VtasControlClienteCotizaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteCotiza.jsp", new VtasAdmClienteClienteCotizaManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteConsultarHistoricoInventario.ctr", new VtasControlClienteConsultarHistoricoInventarioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConClienteConsultarHistoricoInventario.jsp", new VtasAdmConsultarHistoricoInventarioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstClienteConsultarHistoricoInventario.jsp", new VtasAdmConsultarHistoricoInventarioManejadorRequest());

        manejadorHash.put("/vtaPermisosClienteReportarVisita.ctr", new VtasControlClienteReportarVisitaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConClienteReportarVisita.jsp", new VtasAdmReportarVisitaManejadorRequest());

        manejadorHash.put("/jsp/vtaFrmSelClienteFinalizaPedido.jsp", new VtasAdmClienteFinalizaPedidoManejadorRequest());

        manejadorHash.put("/potPermisoAdmUtilInactivo.ctr", new VtasControlAdmUtilInactivoManejadorRequest());

        manejadorHash.put("/potPermisoCopiaDeSeguridad.ctr", new VtasControlAdmCopiaDeSeguridadManejadorRequest());

        manejadorHash.put("/gralPermisosTerminar.ctr", new GralPermisosTerminarManejadorRequest());

        manejadorHash.put("/vtaPermisosClienteAdministraPedido.ctr", new VtasControlClienteAdministraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteAdministraPedido.jsp", new VtasAdmClienteAdministraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstClienteAdministraPedido.jsp", new VtasAdmClienteAdministraPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConClientePedido.jsp", new VtasAdmClienteAdministraPedidoManejadorRequest());

        manejadorHash.put("/vtaPermisosMenuCliente.ctr", new GralLogicaMenuClienteManejadorRequest());
        manejadorHash.put("/vtaPermisosMenuClienteNuevo.ctr", new GralLogicaMenuClienteNuevoManejadorRequest());
        manejadorHash.put("/vtaPermisosMenuAgenda.ctr", new GralLogicaMenuAgendaManejadorRequest());
        manejadorHash.put("/vtaPermisosMenuCuentas.ctr", new GralLogicaMenuCuentasManejadorRequest());
        manejadorHash.put("/vtaPermisosMenuCotizacion.ctr", new GralLogicaMenuCotizacionManejadorRequest());
        manejadorHash.put("/vtaPermisosMenuLogistica.ctr", new GralLogicaMenuLogisticaManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteNuevoCodificar.ctr", new VtasControlClienteNuevoCodificarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteNuevoCodificar.jsp", new VtasAdmClienteNuevoCodificarManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteNuevoActualizar.ctr", new VtasControlClienteNuevoActualizarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteNuevoActualizar.jsp", new VtasAdmClienteNuevoActualizarManejadorRequest());
        manejadorHash.put("/jsp/vtaLstClienteNuevoActualizar.jsp", new VtasAdmClienteNuevoActualizarManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteNuevoDesactivar.ctr", new VtasControlClienteNuevoDesactivarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteNuevoDesactivar.jsp", new VtasAdmClienteNuevoDesactivarManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteNuevoActivar.ctr", new VtasControlClienteNuevoActivarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteNuevoActivar.jsp", new VtasAdmClienteNuevoActivarManejadorRequest());

        manejadorHash.put("/vtaContenedorAgendaConsultar.ctr", new VtasControlAgendaConsultarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorAgendaConsultar.jsp", new VtasAdmCargarAgendaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstControlCargarAgenda.jsp", new VtasAdmCargarAgendaManejadorRequest());

        manejadorHash.put("/vtaContenedorAgendaCargar.ctr", new VtasControlAgendaCargarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorAgendaCargar.jsp", new VtasAdmCargarAgendaManejadorRequest());

        manejadorHash.put("/vtaContenedorAgendaProgramar.ctr", new VtasControlAgendaProgramarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorAgendaProgramar.jsp", new VtasAdmAgendaProgramarManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelAgendaProgramar.jsp", new VtasAdmAgendaProgramarManejadorRequest());

        manejadorHash.put("/vtaContenedorAgendaReprogramar.ctr", new VtasControlAgendaReprogramarManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorAgendaReprogramar.jsp", new VtasAdmAgendaReprogramarManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelAgendaReprogramar.jsp", new VtasAdmAgendaReprogramarManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConAgendaReprogramar.jsp", new VtasAdmAgendaReprogramarManejadorRequest());

        manejadorHash.put("/vtaContenedorAgendaIngresarCliente.ctr", new VtasControlAgendaIngresarClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorAgendaIngresarCliente.jsp", new VtasAdmAgendaIngresarClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelAgendaIngresarCliente.jsp", new VtasAdmAgendaIngresarClienteManejadorRequest());

        manejadorHash.put("/vtaPermisosMenuAllListaPedido.ctr", new VtasControlClienteAllListaPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorLstClienteAllPedido.jsp", new VtasAdmClienteAllListaPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstClienteAllPedido.jsp", new VtasAdmClienteAllListaPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConClienteAllPedido.jsp", new VtasAdmClienteAllListaPedidoManejadorRequest());

        manejadorHash.put("/vtaContenedorCotizacionInventario.ctr", new VtasControlCotizacionInventarioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCotizacionInventario.jsp", new VtasAdmCotizacionInventarioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCotizacionInventario.jsp", new VtasAdmCotizacionInventarioManejadorRequest());

        manejadorHash.put("/vtaContenedorClienteAutorizaProspectoUnCO.ctr", new VtasControlClienteAutorizaProspectoUnCOUnCOManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorClienteAutorizaProspectoUnCO.jsp", new VtasAdmClienteAutorizaPedidoProspectoUnCOManejadorRequest());

        manejadorHash.put("/vtaContenedorReportePedidoCOVendedor.ctr", new VtasControlReportePedidoCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReportePedidoCOVendedor.jsp", new VtasAdmReportePedidoCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReportePedidoCOVendedor.jsp", new VtasAdmReportePedidoCOVendedorManejadorRequest());

        manejadorHash.put("/vtaContenedorReportePedidoCorporativo.ctr", new VtasControlReportePedidoCorporativoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReportePedidoCorporativo.jsp", new VtasAdmReportePedidoCorporativoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReportePedidoCorporativo.jsp", new VtasAdmReportePedidoCorporativoManejadorRequest());

        manejadorHash.put("/vtaContenedorReporteVisitaCOVendedor.ctr", new VtasControlReporteVisitaCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReporteVisitaCOVendedor.jsp", new VtasAdmReporteVisitaCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReporteVisitaCOVendedor.jsp", new VtasAdmReporteVisitaCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReporteVisitaCOVendedorContacto.jsp", new VtasAdmReporteVisitaCOVendedorManejadorRequest());

        manejadorHash.put("/vtaContenedorReporteVisitaActualCOVendedor.ctr", new VtasControlReporteVisitaActualCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReporteVisitaActualCOVendedor.jsp", new VtasAdmReporteVisitaActualCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReporteVisitaActualCOVendedor.jsp", new VtasAdmReporteVisitaActualCOVendedorManejadorRequest());

        manejadorHash.put("/vtaContenedorReporteVisitaUnVendedor.ctr", new VtasControlReporteVisitaUnVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReporteVisitaUnVendedor.jsp", new VtasAdmReporteVisitaUnVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReporteVisitaUnVendedor.jsp", new VtasAdmReporteVisitaUnVendedorManejadorRequest());

        manejadorHash.put("/vtaContenedorReporteClienteNuevoCOVendedor.ctr", new VtasControlReporteClienteNuevoCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReporteClienteNuevoCOVendedor.jsp", new VtasAdmReporteClienteNuevoCOVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReporteClienteNuevoCOVendedor.jsp", new VtasAdmReporteClienteNuevoCOVendedorManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaSelecciona.ctr", new VtasControlEmpresaSeleccionaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaSelecciona.jsp", new VtasAdmEmpresaSeleccionaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaDato.ctr", new VtasControlEmpresaDatoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaDato.jsp", new VtasAdmEmpresaDatoManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaCxC.ctr", new VtasControlEmpresaCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaCxC.jsp", new VtasAdmEmpresaCxCManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaNota.ctr", new VtasControlEmpresaNotaManejadorRequest());
        //manejadorHash.put("/jsp/vtaContenedorEmpresaNota.jsp", new VtasAdmEmpresaNotaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmLstEmpresaNota.jsp", new VtasAdmEmpresaNotaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmSelEmpresaNotaAdicionaPlu.jsp", new VtasAdmEmpresaNotaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmSelEmpresaFinalizaNota.jsp", new VtasAdmEmpresaNotaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaHPendiente.ctr", new VtasControlEmpresaHPendienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaHPendiente.jsp", new VtasAdmEmpresaHPendienteManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaHPedido.ctr", new VtasControlEmpresaHPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaHPedido.jsp", new VtasAdmEmpresaHPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstEmpresaHPedido.jsp", new VtasAdmEmpresaHPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConHPedido.jsp", new VtasAdmEmpresaHPedidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmDetEmpresaHPedido.jsp", new VtasAdmEmpresaHPedidoManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaVenta.ctr", new VtasControlEmpresaVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaVenta.jsp", new VtasAdmEmpresaVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelEmpresaVenta.jsp", new VtasAdmEmpresaVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModEmpresaVenta.jsp", new VtasAdmEmpresaVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaVentaIndicador.jsp", new VtasAdmEmpresaVentaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaElabora.ctr", new VtasControlEmpresaElaboraManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaElaboraPedido.jsp", new VtasAdmEmpresaElaboraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelEmpresaElaboraAdicionaPlu.jsp", new VtasAdmEmpresaElaboraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModEmpresaElaboraRetiraPlu.jsp", new VtasAdmEmpresaElaboraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConEmpresaConfirmaRetiraPlu.jsp", new VtasAdmEmpresaElaboraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModEmpresaElaboraDsctoComercial.jsp", new VtasAdmEmpresaElaboraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelEmpresaFinalizaPedido.jsp", new VtasAdmEmpresaFinalizaPedidoManejadorRequest());

        manejadorHash.put("/potPermisoAdmOrdenTrabajo.ctr", new VtasControlOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmCreOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmMatOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmCamOrdenTrabajo.jsp", new VtasAdmOrdenTrabajoManejadorRequest());

        manejadorHash.put("/potPermisoAdmOTEstado.ctr", new VtasControlAdmOTEstadoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorAdmOTEstado.jsp", new VtasAdmOTEstadoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOTEstado.jsp", new VtasAdmOTEstadoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConOTEstado.jsp", new VtasAdmOTEstadoManejadorRequest());

        manejadorHash.put("/potPermisoAdmFichaTecnica.ctr", new VtasControlFichaTecnicaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorFichaTecnica.jsp", new VtasAdmFichaTecnicaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstFichaTecnica.jsp", new VtasAdmFichaTecnicaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModFichaTecnica.jsp", new VtasAdmFichaTecnicaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmCamFichaTecnica.jsp", new VtasAdmFichaTecnicaManejadorRequest());

        manejadorHash.put("/potPermisoAdmRemisionFactura.ctr", new VtasControlRemisionFacturaProgresoManejadorRequest());
        //manejadorHash.put("/jsp/vtaContenedorRemisionFactura.jsp", new VtasAdmRemisionFacturaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmIngRemisionFacturaRemision.jsp", new VtasAdmRemisionFacturaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmLiqRemisionFacturaRemision.jsp", new VtasAdmRemisionFacturaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmIngFacturaFacturaRemision.jsp", new VtasAdmRemisionFacturaManejadorRequest());
        //manejadorHash.put("/jsp/vtaFrmLiqFacturaFacturaRemision.jsp", new VtasAdmRemisionFacturaManejadorRequest());

        manejadorHash.put("/potPermisoAdmOrdenTrabajoProgreso.ctr", new VtasControlOrdenTrabajoProgresoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOrdenTrabajoProgreso.jsp", new VtasAdmOrdenTrabajoProgresoManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaSuspendido.ctr", new VtasControlEmpresaSuspendidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaSuspendido.jsp", new VtasAdmEmpresaSuspendidoManejadorRequest());

        manejadorHash.put("/potPermisoAdmOrdenTrabajoActivo.ctr", new VtasControlOrdenTrabajoActivoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOrdenTrabajoActivo.jsp", new VtasAdmOrdenTrabajoActivoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngOrdenTrabajoActivo.jsp", new VtasAdmOrdenTrabajoActivoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOrdenTrabajoActivo.jsp", new VtasAdmOrdenTrabajoActivoManejadorRequest());

        manejadorHash.put("/potPermisoAdmExterno.ctr", new VtasControlOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmMaqOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmRetOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmTieOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmPerOTExterna.jsp", new VtasAdmOTExternaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmRetOTExternaSalida.jsp", new VtasAdmOTExternaManejadorRequest());

        manejadorHash.put("/potPermisoAdmOTProducto.ctr", new VtasControlOTProductoManejadorRequest());
        manejadorHash.put("/potPermisoAdmOTProducto2.ctr", new Controller_Produccion_Edicion());
        manejadorHash.put("/jsp/vtaContenedorOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmMaqOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmRetOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmTieOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmPerOTProducto.jsp", new VtasAdmOTProductoManejadorRequest());

        manejadorHash.put("/potPermisoAdmOTProductoTouch.ctr", new VtasControlOTProductoTouchManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOTProductoTouch.jsp", new VtasAdmOTProductoTouchManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngOTProductoTouch.jsp", new VtasAdmOTProductoTouchManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmManOTProductoTouch.jsp", new VtasAdmOTProductoTouchManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSumOTProductoTouch.jsp", new VtasAdmOTProductoTouchManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmRetOTProductoTouch.jsp", new VtasAdmOTProductoTouchManejadorRequest());

        manejadorHash.put("/potPermisoAdmOTPrograma.ctr", new VtasControlOTProgramaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOTPrograma.jsp", new VtasAdmOTProgramaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOTPrograma.jsp", new VtasAdmOTProgramaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConOTPrograma.jsp", new VtasAdmOTProgramaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModOTPrograma.jsp", new VtasAdmOTProgramaManejadorRequest());

        manejadorHash.put("/potPermisoAdmOTCosto.ctr", new VtasControlOTCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOTCosto.jsp", new VtasAdmOTCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOTCosto.jsp", new VtasAdmOTCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngOTCosto.jsp", new VtasAdmOTCostoManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaCotiza.ctr", new VtasControlEmpresaCotizaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaCotiza.jsp", new VtasAdmEmpresaCotizaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaRemesa.ctr", new VtasControlEmpresaRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaRemesa.jsp", new VtasAdmEmpresaRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelEmpresaRemesa.jsp", new VtasAdmEmpresaRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModEmpresaRemesa.jsp", new VtasAdmEmpresaRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModDsctoEmpresaRemesa.jsp", new VtasAdmEmpresaRemesaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaHRemesa.ctr", new VtasControlEmpresaHRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaHRemesa.jsp", new VtasAdmEmpresaHRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstEmpresaHRemesa.jsp", new VtasAdmEmpresaHRemesaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConHRemesa.jsp", new VtasAdmEmpresaHRemesaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaPagoCxC.ctr", new VtasControlEmpresaPagoCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorPagoCxC.jsp", new VtasAdmPagoCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLiqPagoCxC.jsp", new VtasAdmPagoCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLiqPagoCxCParcial.jsp", new VtasAdmPagoCxCManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaPagoCxCPlanilla.ctr", new VtasControlEmpresaPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorPagoCxCPlanilla.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLiqPagoCxCPlanilla.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModPagoCxCPlanilla.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelPagoCxCPlanilla.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngPagoCxCPlanilla.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngPagoCxCPlanillaParcial.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstPagoCxCPlanilla.jsp", new VtasAdmPagoCxCPlanillaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaHPagoCxC.ctr", new VtasControlEmpresaHPagoCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaHPagoCxC.jsp", new VtasAdmEmpresaHPagoCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstEmpresaHPagoCxC.jsp", new VtasAdmEmpresaHPagoCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmRetEmpresaHPagoCxC.jsp", new VtasAdmEmpresaHPagoCxCManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaSeleccionaProveedor.ctr", new VtasControlEmpresaSeleccionaProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp", new VtasAdmEmpresaSeleccionaProveedorManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaProveedorDato.ctr", new VtasControlEmpresaProveedorDatoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaDatoProveedor.jsp", new VtasAdmEmpresaProveedorDatoManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaProveedorCxC.ctr", new VtasControlEmpresaProveedorCxCManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaProveedorCxC.jsp", new VtasAdmEmpresaProveedorCxCManejadorRequest());

        manejadorHash.put("/potPermisoAdmProveedorPagoCxPPlanilla.ctr", new VtasControlProveedorPagoCxPManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorPagoCxPPlanilla.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLiqPagoCxPPlanilla.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelPagoCxPPlanilla.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngPagoCxPPlanilla.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngPagoCxPPlanillaParcial.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModPagoCxPPlanilla.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstPagoCxPPlanilla.jsp", new VtasAdmPagoCxPPlanillaManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaHPagoCxP.ctr", new VtasControlEmpresaHPagoCxPManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaHPagoCxP.jsp", new VtasAdmEmpresaHPagoCxPManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstEmpresaHPagoCxP.jsp", new VtasAdmEmpresaHPagoCxPManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmRetEmpresaHPagoCxP.jsp", new VtasAdmEmpresaHPagoCxPManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaHPedidoProveedor.ctr", new VtasControlEmpresaHPedidoProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaHPedidoProveedor.jsp", new VtasAdmEmpresaHPedidoProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstEmpresaHPedidoProveedor.jsp", new VtasAdmEmpresaHPedidoProveedorManejadorRequest());

        manejadorHash.put("/potPermisoAdmEmpresaElaboraProveedor.ctr", new VtasControlEmpresaElaboraProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp", new VtasAdmEmpresaElaboraProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelEmpresaElaboraProveedorAdicionaPlu.jsp", new VtasAdmEmpresaElaboraProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConEmpresaProveedorConfirmaRetiraPlu.jsp", new VtasAdmEmpresaElaboraProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModEmpresaElaboraProveedorRetiraPlu.jsp", new VtasAdmEmpresaElaboraProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelEmpresaFinalizaPedidoProveedor.jsp", new VtasAdmEmpresaFinalizaPedidoProveedorManejadorRequest());

        manejadorHash.put("/potPermisoAdmResurtidoNota.ctr", new VtasControlResurtidoNotaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorResurtidoNota.jsp", new VtasAdmResurtidoNotaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstResurtidoNota.jsp", new VtasAdmResurtidoNotaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelResurtidoNota.jsp", new VtasAdmResurtidoNotaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmFinResurtidoNota.jsp", new VtasAdmResurtidoNotaManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoCliente.ctr", new VtasControlCatalogoClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoCliente.jsp", new VtasAdmCatalogoClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoCliente.jsp", new VtasAdmCatalogoClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoCliente.jsp", new VtasAdmCatalogoClienteManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoClienteLocal.ctr", new VtasControlCatalogoClienteLocalManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoClienteLocal.jsp", new VtasAdmCatalogoClienteLocalManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoClienteLocal.jsp", new VtasAdmCatalogoClienteLocalManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoClienteLocal.jsp", new VtasAdmCatalogoClienteLocalManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmCamCatalogoClienteLocal.jsp", new VtasAdmCatalogoClienteLocalManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoProveedor.ctr", new VtasControlCatalogoProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoProveedor.jsp", new VtasAdmCatalogoProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoProveedor.jsp", new VtasAdmCatalogoProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoProveedor.jsp", new VtasAdmCatalogoProveedorManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoLinea.ctr", new VtasControlCatalogoLineaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoLinea.jsp", new VtasAdmCatalogoLineaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoLinea.jsp", new VtasAdmCatalogoLineaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoLinea.jsp", new VtasAdmCatalogoLineaManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoCategoria.ctr", new VtasControlCatalogoCategoriaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoCategoria.jsp", new VtasAdmCatalogoCategoriaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoCategoria.jsp", new VtasAdmCatalogoCategoriaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoCategoria.jsp", new VtasAdmCatalogoCategoriaManejadorRequest());

        manejadorHash.put("/potPermisoAdmAdmCatalogoMarca.ctr", new VtasControlCatalogoMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoMarca.jsp", new VtasAdmCatalogoMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoMarca.jsp", new VtasAdmCatalogoMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoMarca.jsp", new VtasAdmCatalogoMarcaManejadorRequest());

        manejadorHash.put("/potPermisoAdmAdmCatalogoReferencia.ctr", new VtasControlCatalogoReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoReferencia.jsp", new VtasAdmCatalogoReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoReferencia.jsp", new VtasAdmCatalogoReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoReferencia.jsp", new VtasAdmCatalogoReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngCatalogoReferencia.jsp", new VtasAdmCatalogoReferenciaManejadorRequest());

        manejadorHash.put("/potPermisoAdmAdmCatalogoMaterial.ctr", new VtasControlCatalogoMaterialManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoMaterial.jsp", new VtasAdmCatalogoMaterialManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngCatalogoMaterial.jsp", new VtasAdmCatalogoMaterialManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoMaterial.jsp", new VtasAdmCatalogoMaterialManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoMaterial.jsp", new VtasAdmCatalogoMaterialManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoCombo.ctr", new VtasControlCatalogoComboManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoCombo.jsp", new VtasAdmCatalogoComboManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoCombo.jsp", new VtasAdmCatalogoComboManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoCombo.jsp", new VtasAdmCatalogoComboManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstCatalogoCombo.jsp", new VtasAdmCatalogoComboManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoEan.ctr", new VtasControlCatalogoEanManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoEan.jsp", new VtasAdmCatalogoEanManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoEan.jsp", new VtasAdmCatalogoEanManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoUsuario.ctr", new VtasControlCatalogoUsuarioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoUsuario.jsp", new VtasAdmCatalogoUsuarioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelCatalogoUsuario.jsp", new VtasAdmCatalogoUsuarioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModCatalogoUsuario.jsp", new VtasAdmCatalogoUsuarioManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoPotMaquina.ctr", new VtasControlCatalogoPotMaquinaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoPotMaquina.jsp", new VtasAdmCatalogoPotMaquinaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmCamCatalogoPotMaquina.jsp", new VtasAdmCatalogoPotMaquinaManejadorRequest());

        //potPermisoRepVentaReferencias
        manejadorHash.put("/potPermisoRepVentaReferencias.ctr", new com.solucionesweb.losservlets.VtasControlRepVentaRefeManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaReferencia.jsp", new com.solucionesweb.losservlets.VtasAdmRepVentaReferenciaManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoPotOperario.ctr", new VtasControlCatalogoPotOperarioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoPotOperario.jsp", new VtasAdmCatalogoPotOperarioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngCatalogoPotOperario.jsp", new VtasAdmCatalogoPotOperarioManejadorRequest());

        manejadorHash.put("/potPermisoAdmCatalogoPotCosto.ctr", new VtasControlCatalogoPotCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCatalogoPotCosto.jsp", new VtasAdmCatalogoPotCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngCatalogoPotCosto.jsp", new VtasAdmCatalogoPotCostoManejadorRequest());

        manejadorHash.put("/potPermisoAdmRetencion.ctr", new VtasControlRetencionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRetencion.jsp", new VtasAdmRetencionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelRetencion.jsp", new VtasAdmRetencionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmTraRetencion.jsp", new VtasAdmRetencionManejadorRequest());

        manejadorHash.put("/potPermisoAdmReteCree.ctr", new VtasControlReteCreeManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReteCree.jsp", new VtasAdmlReteCreeManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmTraReteCree.jsp", new VtasAdmlReteCreeManejadorRequest());

        manejadorHash.put("/potPermisoAdmUtilPrecioVenta.ctr", new VtasControlUtilPrecioVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorUtilPrecioVenta.jsp", new VtasAdmUtilPrecioVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelUtilPrecioVenta.jsp", new VtasAdmUtilPrecioVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConUtilPrecioVenta.jsp", new VtasAdmUtilPrecioVentaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstUtilPrecioVenta.jsp", new VtasAdmUtilPrecioVentaManejadorRequest());

        manejadorHash.put("/potPermisoAdmUtilClave.ctr", new VtasControlUtilClaveManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorUtilClave.jsp", new VtasAdmUtilUtilClaveManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaIva.ctr", new VtasControlRepVentaIvaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaIva.jsp", new VtasAdmRepVentaIvaManejadorRequest());

        manejadorHash.put("/potPermisoRepCliente.ctr", new VtasControlRepClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepCliente.jsp", new VtasAdmRepClienteManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaCtm.ctr", new VtasControlRepVentaCtmManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaCtm.jsp", new VtasAdmRepVentaCtmManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaNota.ctr", new VtasControlRepVentaNotaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaNota.jsp", new VtasAdmRepVentaNotaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepNotaAll.jsp", new VtasAdmRepVentaNotaManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaRemision.ctr", new VtasControlRepVentaRemisionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaRemision.jsp", new VtasAdmRepVentaRemisionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepVentaRemision.jsp", new VtasAdmRepVentaRemisionManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaCxCDetalle.ctr", new VtasControlRepVentaCxCDetalleManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaCxCDetalle.jsp", new VtasAdmRepVentaCxCDetalleManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepVentaCxCDetalleArchivo.jsp", new VtasAdmRepVentaCxCDetalleManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaCxCCliente.ctr", new VtasControlRepVentaCxCClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaCxCCliente.jsp", new VtasAdmRepVentaCxCClienteManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaIngreso.ctr", new VtasControlRepVentaIngresoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaIngreso.jsp", new VtasAdmRepVentaIngresoManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaMarca.ctr", new VtasControlRepVepVentaMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaMarca.jsp", new VtasAdmRepVentaMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepVentaMarca.jsp", new VtasAdmRepVentaMarcaManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaCosto.ctr", new VtasControlRepVepVentaCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaCosto.jsp", new VtasAdmRepVentaCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepVentaCosto.jsp", new VtasAdmRepVentaCostoManejadorRequest());

        manejadorHash.put("/potPermisoRepCompraIva.ctr", new VtasControlRepResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepResurtidoCompra.jsp", new VtasAdmRepResurtidoCompraManejadorRequest());

        manejadorHash.put("/potPermisoRepCompraIvaTotal.ctr", new VtasControlRepCompraIvaTotalManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepCompraIvaTotal.jsp", new VtasAdmRepCompraIvaTotalManejadorRequest());

        manejadorHash.put("/potPermisoRepCompraCxPDetalle.ctr", new VtasControlRepCompraCxPDetalleManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepCompraCxPDetalle.jsp", new VtasAdmRepCompraCxPDetalleManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepCompraCxPDetalleArchivo.jsp", new VtasAdmRepCompraCxPDetalleManejadorRequest());

        manejadorHash.put("/potPermisoRepCompraEgreso.ctr", new VtasControlRepCompraEgresoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepCompraEgreso.jsp", new VtasAdmRepCompraEgresoManejadorRequest());

        manejadorHash.put("/potPermisoRefciasProveedor.ctr", new VtasControlRefciasProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRefciasProveedorPlu.jsp", new VtasAdmRefciasProveedorPluManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRefciasProveedorPlu.jsp", new VtasAdmRefciasProveedorPluManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaIngresoCuadreCaja.ctr", new VtasControlRepCuadreCajaIngresoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaIngresos.jsp", new VtasAdmRepCuadreCajaIngresoManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioReferencia.ctr", new VtasControlInventarioReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioReferencia.jsp", new VtasAdmInventarioReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelInventarioReferencia.jsp", new VtasAdmInventarioReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConInventarioReferencia.jsp", new VtasAdmInventarioReferenciaManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioCategoria.ctr", new VtasControlInventarioCategoriaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioCategoria.jsp", new VtasAdmInventarioCategoriaManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioMarca.ctr", new VtasControlInventarioMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioMarca.jsp", new VtasAdmInventarioMarcaManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioOperacion.ctr", new VtasControlInventarioOperacionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioOperacion.jsp", new VtasAdmInventarioOperacionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioOperacion.jsp", new VtasAdmInventarioOperacionManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioOTOperacion.ctr", new VtasControlRepInventarioOTOperacionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepInventarioOTOperacion.jsp", new VtasAdmRepInventarioOTOperacionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioOTOperacion.jsp", new VtasAdmRepInventarioOTOperacionManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioMPOperacion.ctr", new VtasControlRepInventariomMPOperacionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepInventarioMPOperacion.jsp", new VtasAdmRepInventarioMPOperacionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioMPOperacion.jsp", new VtasAdmRepInventarioMPOperacionManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioMPMaquina.ctr", new VtasControlRepInventarioMPMaquinaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepInventarioMPMaquina.jsp", new VtasAdmRepInventarioMPMaquinaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioMPMaquina.jsp", new VtasAdmRepInventarioMPMaquinaManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioOTExterno.ctr", new VtasControlRepInventarioOTExternoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepInventarioOTExterno.jsp", new VtasAdmRepInventarioOTExternoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioOTExterno.jsp", new VtasAdmRepInventarioOTExternoManejadorRequest());

        manejadorHash.put("/potPermisoRepInventarioKardex.ctr", new VtasControlInventarioKardexManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioKardex.jsp", new VtasAdmInventarioKardexManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelInventarioKardexReferencia.jsp", new VtasAdmInventarioKardexManejadorRequest());

        manejadorHash.put("/potPermisoRepPotCosto.ctr", new VtasControlRepPotCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPotCosto.jsp", new VtasAdmRepPotCostoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepPotCosto.jsp", new VtasAdmRepPotCostoManejadorRequest());

        manejadorHash.put("/potPermisoRepPotProducto.ctr", new VtasControlRepPotProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPotProducto.jsp", new VtasAdmRepPotProductoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepPotProducto.jsp", new VtasAdmRepPotProductoManejadorRequest());

        manejadorHash.put("/potPermisoRepPotTiempoPerdido.ctr", new VtasControlRepPotTiempoPerdidoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPotTiempoPerdido.jsp", new VtasAdmRepPotTiempoPerdidoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepPotTiempoPerdido.jsp", new VtasAdmRepPotTiempoPerdidoManejadorRequest());

        manejadorHash.put("/potPermisoAdmResurtidoCompra.ctr", new VtasControlResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmModResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmFinResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmAdiResurtidoCompra.jsp", new VtasAdmResurtidoCompraManejadorRequest());

        manejadorHash.put("/potPermisoAdmResurtidoRecepcion.ctr", new VtasControlResurtidoRecepcionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorResurtidoRecepcion.jsp", new VtasAdmResurtidoRecepcionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstResurtidoRecepcion.jsp", new VtasAdmResurtidoRecepcionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegResurtidoRecepcion.jsp", new VtasAdmResurtidoRecepcionManejadorRequest());

        manejadorHash.put("/potPermisoAdmSuministro.ctr", new VtasControlSuministroManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorSuministro.jsp", new VtasAdmSuministroManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstSuministro.jsp", new VtasAdmSuministroManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegSuministro.jsp", new VtasAdmSuministroManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmAdiSuministro.jsp", new VtasAdmSuministroManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngSuministro.jsp", new VtasAdmSuministroManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConSuministro.jsp", new VtasAdmSuministroManejadorRequest());

        manejadorHash.put("/potPermisoAdmReciboCompra.ctr", new VtasControlReciboCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorReciboCompra.jsp", new VtasAdmReciboCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstReciboCompra.jsp", new VtasAdmReciboCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegReciboCompra.jsp", new VtasAdmReciboCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmAdiReciboCompra.jsp", new VtasAdmReciboCompraManejadorRequest());

        manejadorHash.put("/potPermisoAdmOrdenCompra.ctr", new VtasControlOrdenCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorOrdenCompra.jsp", new VtasAdmOrdenCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngOrdenCompra.jsp", new VtasAdmOrdenCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstOrdenCompra.jsp", new VtasAdmOrdenCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConOrdenCompra.jsp", new VtasAdmOrdenCompraManejadorRequest());

        manejadorHash.put("/potPermisoAdmOrdenServicio.ctr", new VtasControlServicioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorServicio.jsp", new VtasAdmServicioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstServicio.jsp", new VtasAdmServicioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegServicio.jsp", new VtasAdmServicioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConServicio.jsp", new VtasAdmServicioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmCamServicio.jsp", new VtasAdmServicioManejadorRequest());

        manejadorHash.put("/potPermisoAdmResurtidoTraslado.ctr", new VtasControlResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmFinResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmAjuResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmTraeResurtidoTraslado.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegResurtidoTrasladoTx.jsp", new VtasAdmResurtidoTrasladoManejadorRequest());

        manejadorHash.put("/potPermisoAdmResurtidoDespacho.ctr", new VtasControlResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSelResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmAjuResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmTraeResurtidoDespacho.jsp", new VtasAdmResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegResurtidoDespachoTx.jsp", new VtasAdmResurtidoDespachoManejadorRequest());

        manejadorHash.put("/potPermisoAdmResurtidoAutoconsumo.ctr", new VtasControlResurtidoAutoconsumoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorResurtidoAutoconsumo.jsp", new VtasAdmResurtidoAutoconsumoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngResurtidoAutoconsumo.jsp", new VtasAdmResurtidoAutoconsumoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstResurtidoAutoconsumo.jsp", new VtasAdmResurtidoAutoconsumoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConResurtidoAutoconsumo.jsp", new VtasAdmResurtidoAutoconsumoManejadorRequest());

        manejadorHash.put("/potPermisoRepResurtidoCompra.ctr", new VtasControlRepResurtidoCompraManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepResurtidoCompra.jsp", new VtasAdmRepResurtidoCompraManejadorRequest());

        manejadorHash.put("/potPermisoRepResurtidoTraslado.ctr", new VtasControlRepResurtidoTrasladoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepResurtidoTraslado.jsp", new VtasAdmRepResurtidoTrasladoManejadorRequest());

        manejadorHash.put("/potPermisoRepResurtidoDespacho.ctr", new VtasControlRepResurtidoDespachoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepResurtidoDespacho.jsp", new VtasAdmRepResurtidoDespachoManejadorRequest());

        manejadorHash.put("/potPermisoRepResurtidoAutoconsumo.ctr", new VtasControlRepResurtidoAutoconsumoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepResurtidoAutoconsumo.jsp", new VtasAdmRepResurtidoAutoconsumoManejadorRequest());

        manejadorHash.put("/potPermisoAdmInventarioConteo.ctr", new VtasControlAdmInventarioConteoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioConteo.jsp", new VtasAdmInventarioConteoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngInventarioConteo.jsp", new VtasAdmInventarioConteoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioConteo.jsp", new VtasAdmInventarioConteoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLegInventarioConteo.jsp", new VtasAdmInventarioConteoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmSubInventarioConteo.jsp", new VtasAdmInventarioConteoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConInventarioConteo.jsp", new VtasAdmInventarioConteoManejadorRequest());

        manejadorHash.put("/potPermisoAdmInventarioMovimiento.ctr", new VtasControlInventarioMovimientoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioMovimiento.jsp", new VtasAdmInventarioMovimientoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngInventarioMovimiento.jsp", new VtasAdmInventarioMovimientoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioMovimiento.jsp", new VtasAdmInventarioMovimientoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConInventarioMovimiento.jsp", new VtasAdmInventarioMovimientoManejadorRequest());

        manejadorHash.put("/potPermisoAdmInventarioAjuste.ctr", new VtasControlInventarioAjusteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioAjuste.jsp", new VtasAdmInventarioAjusteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConInventarioAjuste.jsp", new VtasAdmInventarioAjusteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioAjuste.jsp", new VtasAdmInventarioAjusteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngInventarioAjuste.jsp", new VtasAdmInventarioAjusteManejadorRequest());

        manejadorHash.put("/potPermisoAdmInventarioAjusteOT.ctr", new VtasControlInventarioAjusteOTManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInventarioAjusteOT.jsp", new VtasAdmInventarioAjusteOTManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConInventarioAjusteOT.jsp", new VtasAdmInventarioAjusteOTManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstInventarioAjusteOT.jsp", new VtasAdmInventarioAjusteOTManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngInventarioAjusteOT.jsp", new VtasAdmInventarioAjusteOTManejadorRequest());

        manejadorHash.put("/potPermisoRepMargen.ctr", new VtasControlRepMargenManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepMargen.jsp", new VtasAdmRepMargenManejadorRequest());

        manejadorHash.put("/potPermisoRepMargenPlu.ctr", new VtasControlRepMargenPluManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepMargenPlu.jsp", new VtasAdmRepMargenPluManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepMargenPlu.jsp", new VtasAdmRepMargenPluManejadorRequest());

        manejadorHash.put("/potPermisoAdmComisionSenior.ctr", new VtasControlRepComisionSeniorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepComisionSenior.jsp", new VtasAdmRepComisionSeniorManejadorRequest());

        manejadorHash.put("/potPermisoAdmComisionEfectiva.ctr", new VtasControlRepComisionEfectivaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepComisionEfectiva.jsp", new VtasAdmRepComisionEfectivaManejadorRequest());

        //parametros para modificar los porcentajes de comision cartera
        manejadorHash.put("/potPermisoAdmParametroComision.ctr", new VtasControlRepParametroComisionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorParametroComision.jsp", new VtasAdmRepParametroComisionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLsModificarParaComision.jsp", new VtasAdmRepParametroComisionManejadorRequest());

        manejadorHash.put("/potPermisoAdmBonificacionMes.ctr", new VtasControlRepBonificacionMesManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepBonificacionMes.jsp", new VtasAdmRepBonificacionMesManejadorRequest());

        manejadorHash.put("/potPermisoAdmBonificacionTrimeste.ctr", new VtasControlRepBonificacionTrimestreManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepBonificacionTrimestre.jsp", new VtasAdmRepBonificacionTrimestreManejadorRequest());

        manejadorHash.put("/potPermisoAdmContableComprobante.ctr", new VtasControlContableComprobanteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorContableComprobante.jsp", new VtasAdmContableComprobanteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngContableComprobante.jsp", new VtasAdmContableComprobanteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstContableComprobante.jsp", new VtasAdmContableComprobanteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngContableComprobanteIndicador.jsp", new VtasAdmContableComprobanteManejadorRequest());

        manejadorHash.put("/potPermisoRepVentaCuadreCaja.ctr", new VtasControlRepVentaCuadreCajaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaCuadreCaja.jsp", new VtasAdmRepVentaCuadreCajaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepVentaCuadreCajaIndicador.jsp", new VtasAdmRepVentaCuadreCajaManejadorRequest());

        manejadorHash.put("/potPermisoCierreCuadreCaja.ctr", new VtasControlRepCierreCuadreCajaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCierreCuadreCaja.jsp", new VtasAdmCierreCuadreCajaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstCierreCaja.jsp", new VtasAdmCierreCuadreCajaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorCierreCuadreCajaIndicador.jsp", new VtasAdmCierreCuadreCajaManejadorRequest());

        manejadorHash.put("/potPermisoAdmCuadreCajaRevIngreso.ctr", new VtasControlAdmCuadreCajaRevIngresoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEmpresaRevIngreso.jsp", new VtasAdmCuadreCajaRevIngresoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstHistoricoComprobante.jsp", new VtasAdmCuadreCajaRevIngresoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmIngRevComprobante.jsp", new VtasAdmCuadreCajaRevIngresoManejadorRequest());

        manejadorHash.put("/potPermisoAdmContableImporta.ctr", new VtasControlContableImportaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorContableImporta.jsp", new VtasAdmContableImportaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstContableImporta.jsp", new VtasAdmContableImportaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmPagContableImporta.jsp", new VtasAdmContableImportaManejadorRequest());

        manejadorHash.put("/potPermisoRepCambioPrecio.ctr", new VtasControlRepCambioPrecioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepCambioPrecio.jsp", new VtasAdmRepCambioPrecioManejadorRequest());

        manejadorHash.put("/potPermisoRepPluSinVentas.ctr", new VtasControlRepPluSinVentasManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPluSinVentas.jsp", new VtasAdmRepPluSinVentasManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepPlusSinVentas", new VtasAdmRepPluSinVentasManejadorRequest());

        manejadorHash.put("/potPermisoRepPluVendosSinInv.ctr", new VtasControlRepPluVendosSinInvtrioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPluSinInvrioVentas.jsp", new VtasAdmRepPluVendosSinInvManejadorRequest());

        manejadorHash.put("/potPermisoRepPluMasVendidos.ctr", new VtasControlRepPlusMasVendidosManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPluMasVendidos.jsp", new VtasAdmRepPlusMasVendidosManejadorRequest());

        manejadorHash.put("/potPermisoRepPluRotacion.ctr", new VtasControlRepPluRotacionManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepPluRotacion.jsp", new VtasAdmRepPlusRotacionManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepPlusRotacion.jsp", new VtasAdmRepPlusRotacionManejadorRequest());

        manejadorHash.put("/potPermisoRepPluDcto.ctr", new VtasControlRepDctoPluManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepDsctoPlu.jsp", new VtasAdmRepDctoPluManejadorRequest());

        manejadorHash.put("/potPermisoRepDesctoFac.ctr", new VtasControlRepDesctoFactManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepDEsctoFact.jsp", new VtasAdmRepDesctoFactManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadCliente.ctr", new VtasControlRepRentbilidadClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadCliente.jsp", new VtasAdmRepRentbilidadClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadCliente.jsp", new VtasAdmRepRentbilidadClienteManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadMarca.ctr", new VtasControlRepRentbilidadMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadMarca.jsp", new VtasAdmRepRentbilidadMarcaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadMarca.jsp", new VtasAdmRepRentbilidadMarcaManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadVendedor.ctr", new VtasControlRepRentbilidadVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadVendedor.jsp", new VtasAdmRepRentbilidadVendedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadVendedor.jsp", new VtasAdmRepRentbilidadVendedorManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadVendedorPlu.ctr", new VtasControlRepRentbilidadVendedorPluManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadVendedorPlu.jsp", new VtasAdmRepRentbilidadVendedorPluManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadVendPlu.jsp", new VtasAdmRepRentbilidadVendedorPluManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadVendedorCliente.ctr", new VtasControlRepRentbilidadVendedorClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadVendedorCliente.jsp", new VtasAdmRepRentbilidadVendedorClienteManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadVendCliente.jsp", new VtasAdmRepRentbilidadVendedorClienteManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadProveedor.ctr", new VtasControlRepRentbilidadProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadProveedor.jsp", new VtasAdmRepRentbilidadProveedorManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadProveedor.jsp", new VtasAdmRepRentbilidadProveedorManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadZonaGeografica.ctr", new VtasControlRepRentbilidadZonaGeograficaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadZonaGeografica.jsp", new VtasAdmRepRentbilidadZonaGeograficaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadZona.jsp", new VtasAdmRepRentbilidadZonaGeograficaManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadCategoria.ctr", new VtasControlRepRentbilidadCategoriaManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadCategoria.jsp", new VtasAdmRepRentbilidadCategoriaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadCategoria.jsp", new VtasAdmRepRentbilidadCategoriaManejadorRequest());

        manejadorHash.put("/potPermisoRepRentbilidadListaPrecio.ctr", new VtasControlRepRentbilidadListaPrecioManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepRentabilidadListaPrecio.jsp", new VtasAdmRepRentbilidadListaPrecioManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepRendadListaPrecio.jsp", new VtasAdmRepRentbilidadListaPrecioManejadorRequest());

        manejadorHash.put("/potPermisoEan.ctr", new VtasControlEanManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorEanReferencia.jsp", new VtasAdmEanReferenciaManejadorRequest());

        manejadorHash.put("/jsp/vtaFrmLstUtilEtiqueta.jsp", new VtasAdmEanReferenciaManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmConUtilEtiquetaVenta.jsp", new VtasAdmEanReferenciaManejadorRequest());

        manejadorHash.put("/potPermisoRepAjustePositivo.ctr", new VtasControlRepAjustePositivoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepAjustePositivo.jsp", new VtasAdmRepAjustePositivoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepAjuste.jsp", new VtasAdmRepAjustePositivoManejadorRequest());

        manejadorHash.put("/potPermisoRepAjusteNegativo.ctr", new VtasControlRepAjusteNegativoManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorRepNegativo.jsp", new VtasAdmRepAjusteNegativoManejadorRequest());
        manejadorHash.put("/jsp/vtaFrmLstRepAjuste.jsp", new VtasAdmRepAjusteNegativoManejadorRequest());

        //Activa o Inactiva referencias de clientes
        manejadorHash.put("/potPermisoIncativaReferenciasClientes.ctr", new VtasControlInaRefClientesManejadorRequest());
        manejadorHash.put("/jsp/vtaContenedorInaRefClient.jsp", new VtasAdmInaRefClientesManejadorRequest());
        manejadorHash.put("/jsp/vtaAdmInaRefClient.jsp", new VtasAdmInaRefClientesManejadorRequest());
        manejadorHash.put("/potPermisoCotizacion.ctr", new Control_Cliente_Cotizacion());
        manejadorHash.put("/potPermisoCotizacion2.ctr", new Control_Cliente_Cotizacion2());

        //Programacion OT Produccion
        manejadorHash.put("/potPermisoProgramacionOTProduccion.ctr", new com.solucionesweb.controller.programacion.ControlProgramacionOTProduccion());
        //manejadorHash.put("/potPermisoIncativaReferenciasClientes.ctr", new com.solucionesweb.controller.programacion.ProgramacionOTProgramacionController());

        //Facturacion Electronica
        manejadorHash.put("/ControlFERetransmision.ctr", new co.linxsi.controlador.facturacionelectronica.transmision.ControlFERetransmision());
        //manejadorHash.put("/potPermisoIncativaReferenciasClientes.ctr", new com.solucionesweb.controller.programacion.ProgramacionOTProgramacionController());

        //Maestro Bodegas
        //ControlMaestroBodegas.ctr
        manejadorHash.put("/ControlMaestroBodegas.ctr", new co.linxsi.controlador.maestro.bodegas.Control_Maestro_Bodegas());
        //Maestro Procesos
        //ControlMaestroOperaciones.ctr
        manejadorHash.put("/ControlMaestroOperaciones.ctr", new co.linxsi.controlador.maestro.operaciones.Control_Maestro_Operaciones());
        //Maestro Maquinas
        //ControlMaestroMaquinas.ctr
        manejadorHash.put("/ControlMaestroMaquinas.ctr", new co.linxsi.controlador.maestro.maquinas.Control_Maestro_Maquinas());
        //Maestro Paro Maquina
        //ControlMaestroParoMaquinas.ctr
        manejadorHash.put("/ControlMaestroParoMaquina.ctr", new co.linxsi.controlador.maestro.paro_maquina.Control_Maestro_Paro_Maquina());
        //ControlMaestroRetales.ctr
        manejadorHash.put("/ControlMaestroRetales.ctr", new co.linxsi.controlador.maestro.retales.Control_Maestro_Retales());

        //ControlMaestroUnidades.ctr
        manejadorHash.put("/ControlMaestroUnidades.ctr", new co.linxsi.controlador.maestro.unidades.Control_Maestro_Unidades());

        //ControlMaestroCalificadores
        manejadorHash.put("/ControlMaestroCalificadores.ctr", new co.linxsi.controlador.maestro.calificadores.Control_Maestro_Calificadores());

        //ControlMaestroLocales
        manejadorHash.put("/ControlMaestroLocales.ctr", new co.linxsi.controlador.maestro.local.Control_Maestro_Locales());
        manejadorHash.put("/vista/maestros/VistaMaestroLocal.jsp", new co.linxsi.controlador.maestro.local.Control_Maestro_Locales());

        //ControlInventario
        manejadorHash.put("/ControlInventarioConsulta.ctr", new co.linxsi.controlador.inventario.consulta.Control_Inventario_Consulta());
        manejadorHash.put("/ControlInventarioAjuste.ctr", new co.linxsi.controlador.inventario.ajuste.Control_Inventario_Ajuste());
        manejadorHash.put("/ControlInventarioTraslado.ctr", new co.linxsi.controlador.inventario.traslado.Control_Inventario_Traslado());
        manejadorHash.put("/vista/inventario/traslado/ControlInventarioTraslado.jsp", new co.linxsi.controlador.inventario.traslado.Controller_Inventario_Traslado());

        manejadorHash.put("/ControlMaestroImpuesto.ctr", new co.linxsi.controlador.maestro.impuestos.Control_Maestro_Impuestos());

        //ControlCompra
        manejadorHash.put("/ControlOrdenCompra.ctr", new co.linxsi.controlador.compras.orden_compra.Control_Orden_Compra());

        //Produccion
        manejadorHash.put("vista/Touch/VistaProduccionTouchRetal.jsp", new co.linxsi.controlador.touch.retal.Controller_Produccion_Touch_Retal());
        manejadorHash.put("vista/Touch/VistaProduccionTouchParo.jsp", new co.linxsi.controlador.touch.paro.Controller_Produccion_Touch_Paro());
        //Reporte Retales
        //manejadorHash.put("vista/Touch/VistaReporteRetal.jsp", new co.linxsi.controlador.touch.retal.Controller_Produccion_Reporte_Retal());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Requerimiento de [" + request.getParameter("nombrePaginaRequest") + "]");

        String nombrePaginaSolicitada = request.getParameter("nombrePaginaRequest");

        if (nombrePaginaSolicitada.compareTo("/jsp/gralFrmLogin.jsp") != 0) {

            // Obtiene la session actual
            HttpSession session = request.getSession();

            if (session.isNew()) {
                request.getRequestDispatcher("/jsp/gralFrmLogin.jsp").forward(request, response);
                return;
            } else {

                //
                Enumeration listaAtributos = session.getAttributeNames();

                boolean encontroAtributoUsuarioBean = false;

                while (listaAtributos.hasMoreElements()) {
                    String nombreAtributo = (String) listaAtributos.nextElement();

                    if (nombreAtributo == "usuarioBean") {
                        encontroAtributoUsuarioBean = true;
                    }
                }

                if (encontroAtributoUsuarioBean == true) {

                    UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
                    String idUsuario = usuarioBean.getIdUsuarioStr();

                    if (idUsuario == null || idUsuario.length() == 0) {

                        // Significa que el usuario no se ha logeado entonces lo redirecciona
                        // a la forma de captura de login y password
                        request.getRequestDispatcher("/jsp/gralFrmLogin.jsp").forward(request, response);
                        return;
                    } else {

                        //
                        if (nombrePaginaSolicitada == null) {

                            //
                            System.out.println("La session ha caducado");
                            request.getRequestDispatcher("/jsp/gralFrmLogin.jsp").forward(request, response);

                        }
                        // Aqui debe validar si el usuario tiene permiso para acceder a la pagina solicitada

                        PermisoUsuarioBean permisoUsuarioBean = new PermisoUsuarioBean(new Double(idUsuario).doubleValue(),
                                nombrePaginaSolicitada);

                        // llama el metodo validador
                        permisoUsuarioBean.verificarPermiso();

                        if (permisoUsuarioBean.getTieneAcceso()) {
                        } else {
                            // Redirecciona hacia una pagina que informa que el usuario no tiene permiso
                            request.setAttribute("permisoUsuarioBean", permisoUsuarioBean);
                            request.getRequestDispatcher("/jsp/gralPermisoDenegado.jsp").forward(request, response);
                        }

                    }
                } else {
                    System.out.println("El usuarioBean no es valido.");
                    request.getRequestDispatcher("/jsp/gralFrmLogin.jsp").forward(request, response);
                }
            }
        }

        GralManejadorRequest rh = (GralManejadorRequest) manejadorHash.get(request.getParameter("nombrePaginaRequest"));

        if (rh == null) {
            // Aqui no encontrÃ² quien maneje la pagina que hace el request
            ErrorAplicacionBean errorAplicacionBean
                    = new ErrorAplicacionBean("GralControladorServlet.java",
                            "RequestHandlerNulo",
                            "No Existe ningun Request Handler para manejar la Opcion solicitada por el usuario",
                            "rh",
                            null,
                            "Por favor envÃ­e esta pagina al Proveedor de la aplicacion.");
            request.setAttribute("errorAplicacionBean", errorAplicacionBean);

            String vistaURL = response.encodeURL("/jsp/gralErrorAplicacion.jsp");
            request.getRequestDispatcher(vistaURL).forward(request, response);

            System.out.println("Aqui no encontro quien maneje la pagina que hace el request");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {

            // Aqui tenemos un manejador de request para el request actual
            String vistaURL = rh.generaPdf(request, response);

            if (vistaURL == null) {
                System.out.println("El ManejadorRequest no retornÃ³ ninguna vista entonces no hace forward");
                // El ManejadorRequest no retornÃ³ ninguna vista entonces no hace forward
                ErrorAplicacionBean errorAplicacionBean
                        = new ErrorAplicacionBean("GralControladorServlet.java",
                                "VistaURLNula",
                                "La Pagina a retornar es nula",
                                "vistaURL",
                                null,
                                "Por favor envÃ­e esta pagina al Proveedor de la aplicacion.");
                request.setAttribute("errorAplicacionBean", errorAplicacionBean);

                vistaURL = response.encodeURL("/jsp/gralErrorAplicacion.jsp");
                request.getRequestDispatcher(vistaURL).forward(request, response);

            } else {

                Enumeration listaAtributos = request.getAttributeNames();

                String tipoRecurso = (String) request.getAttribute("tipoRecurso");

                if (tipoRecurso == null && vistaURL.equals("/vista/menu/menuPrincipal.jsp") || !vistaURL.isEmpty()) {
                    // Encodifica la URL para posibilitar URL Rewriting en caso
                    // que el cliente rechace cookies.
                    vistaURL = response.encodeURL(vistaURL);
                    request.getRequestDispatcher(vistaURL).forward(request, response);
                } else {

                    // Debe ser un servlet
                    if (tipoRecurso.equals("servlet")) {

                        System.out.println("DespacharÃ¡ hacia el servlet: " + vistaURL);

                        RequestDispatcher rd
                                = getServletContext().getNamedDispatcher(vistaURL);

                        rd.forward(request, response);

                    } else {

                        ErrorAplicacionBean errorAplicacionBean
                                = new ErrorAplicacionBean("GralControladorServlet.java",
                                        "tipoRecursoNoValido",
                                        "El tipo de Recurso deberia ser un servlet",
                                        "tipoRecurso",
                                        tipoRecurso,
                                        "Por favor envÃ­e esta pagina al Proveedor de la aplicacion.");
                        request.setAttribute("errorAplicacionBean", errorAplicacionBean);

                        vistaURL = response.encodeURL("/jsp/gralErrorAplicacion.jsp");
                        request.getRequestDispatcher(vistaURL).forward(request, response);

                    }
                }
            }
        }
    }
}
