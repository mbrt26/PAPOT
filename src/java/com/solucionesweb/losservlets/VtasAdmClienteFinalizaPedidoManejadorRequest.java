package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase ProcesoGeneraPDF
import com.solucionesweb.lasayudas.ProcesoEnviaEmail;

//
import com.solucionesweb.lasayudas.GeneraPDFJasper;

// Importa la clase ProcesoConfirmaPedido
import com.solucionesweb.lasayudas.ProcesoConfirmaPedido;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el AgendaLogVisitaBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el ColaboraTerceroBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteFinalizaPedidoManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmClienteFinalizaPedidoManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber� ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        int xIdNivelVendedor = 1;
        int xIdNivelDirector = 3;
        int xIdTipoOrdenPedido = 9;

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        JhDate jhDate = new JhDate();

        //
        String fechaTxHM = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmEmpresaFinalizaPedidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            System.out.println(" accionContenedor " + accionContenedor);

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/mnuControlClienteBB.jsp";
            }

            // Regresar
            if (accionContenedor.compareTo("REGRESAR") == 0) {

                //
                int estadoActivo = 9;

                //
                HttpSession sesion = request.getSession();
                UsuarioBean usuarioBean =
                        (UsuarioBean) sesion.getAttribute("usuarioBean");
                String idUsuario = usuarioBean.getIdUsuarioStr();

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorClienteSeleccionarNombre.jsp";

                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaContenedorClienteElaboraPedido.jsp";

            }

            //
            if (accionContenedor.compareTo("CONFIRMAR") == 0) {

                // Datos recibidos cotizaci�n
                String xIdLogActual = request.getParameter("idLog");
                String fechaEntrega = request.getParameter("fechaEntrega");
                String observacion = request.getParameter("observacion");
                String contacto = request.getParameter("contacto");
                String ordenCompra = request.getParameter("ordenCompra");
                String impuestoVenta = "1";
                String clave = request.getParameter("clave");

                // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();

                // Valida fechaEntrega
                validacion.reasignar("fechaEntrega", fechaEntrega);
                validacion.validarCampoFecha();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida IdLogActual
                validacion.reasignar("IdLogActual", xIdLogActual);
                validacion.validarCampoEntero();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida impuestoVenta
                validacion.reasignar("impuestoVenta", impuestoVenta);
                validacion.validarCampoEntero();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida fechaEntrega
                validacion.reasignar("clave", clave);
                validacion.validarCampoString();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                //
                int estadoActivo = 9;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion
                int idResponsableBodega = 1;
                int idResponsableProduccion = 2;
                int idTipoOrdenCotizacion = 10;

                //
                int idTipoOrdenFactura = 9;

                //
                String idSucursalInicial = "00";
                int idPeriodo = 200611;

                //
                HttpSession sesion = request.getSession();
                UsuarioBean usuarioBean =
                        (UsuarioBean) sesion.getAttribute("usuarioBean");

                //
                String idUsuario = usuarioBean.getIdUsuarioStr();

                // Valida el UsuarioBean con parametros.
                int estadoVigente = 1;         // Usuario vigente
                usuarioBean.setIdUsuario(idUsuario);
                usuarioBean.setClave(clave);
                usuarioBean.setEstado(estadoVigente);

                // Efectua la busqueda del usuario en la base de datos
                usuarioBean.vigenciaUsuario();
                if (!usuarioBean.isVigente()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();


                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());

                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //
                String strIdSucursal = "01";
                String direccionDespacho =
                        fachadaTerceroBean.getDireccionTercero();
                String ciudadDespacho =
                        fachadaTerceroBean.getCiudadTercero();
                String email = fachadaTerceroBean.getEmail();
                String fax = fachadaTerceroBean.getTelefonoFax();
                String formaPago =
                        fachadaTerceroBean.getIdFormaPagoStr();
                String descuentoComercial = "";
                String idRazon =
                        fachadaTerceroBean.getIdFormaPagoStr();
                String xIdRuta = fachadaTerceroBean.getIdRuta();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(idTipoOrdenFactura);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                if (existePedido) {

                    //
                    validacion.reasignar("PEDIDO", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("Pedido YA CONFIRMADO");
                    validacion.setSolucion("Iniciar NUEVO PEDIDO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }

                //
                String xStrIdSucursal         = "";

                // confirmaPedido
                ProcesoConfirmaPedido proceso = new ProcesoConfirmaPedido();

                // confirma
                proceso.confirma(fachadaAgendaLogVisitaBean.getIdCliente(),
                        xStrIdSucursal,
                        fachadaAgendaLogVisitaBean.getIdUsuarioStr(),
                        xIdLogActual,
                        clave,
                        idResponsableBodega);

                // finalizaCotizacion
                dctoOrdenBean.setEstado(tareaVisita);
                dctoOrdenBean.setDireccionDespacho(direccionDespacho);
                dctoOrdenBean.setEmail(email);
                dctoOrdenBean.setFax(fax);
                dctoOrdenBean.setContacto(contacto);
                dctoOrdenBean.setObservacion(observacion);
                dctoOrdenBean.setFechaEntrega(fechaEntrega);
                dctoOrdenBean.setFormaPago(formaPago);
                dctoOrdenBean.setCiudadDespacho(ciudadDespacho);
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setOrdenCompra(ordenCompra);
                dctoOrdenBean.setDescuentoComercial(descuentoComercial);
                dctoOrdenBean.setImpuestoVenta(impuestoVenta);
                dctoOrdenBean.setIdRazon(idRazon);

                // finalizaCotizacion
                boolean okFinalizar = dctoOrdenBean.finalizaCotizacion();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(idTipoOrdenFactura);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                String strIdLocal = fachadaDctoOrdenBean.getIdLocalStr();
                String strIdTipoOrdenPedido =
                        fachadaDctoOrdenBean.getIdTipoOrdenStr();
                String strIdOrden = fachadaDctoOrdenBean.getIdOrdenStr();
                String strFechaOrden = fachadaDctoOrdenBean.getFechaOrden();
                String strEmailCliente = fachadaDctoOrdenBean.getEmail();
                String strIdCliente = fachadaDctoOrdenBean.getIdCliente();

                //
                GeneraPDFJasper generaPDFJasper = new GeneraPDFJasper();
                generaPDFJasper.setIdOrden(idPeriodo);
                generaPDFJasper.setIdTipoOrden(xIdTipoOrdenPedido);

                try {
                    //
                    generaPDFJasper.generaPDF();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VtasAdmClienteFinalizaPedidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VtasAdmClienteFinalizaPedidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
                }

                //
                String pathBDCotizacion = "C:\\Comercial\\BDCotizacionSqlServer\\";

                //
                String strNumeroOrden = padStringLeft(strIdLocal, 3, "0").trim() +
                        "-" + strIdTipoOrdenPedido +
                        "-" + padStringLeft(strIdOrden, 6, "0").trim();

                //
                String fileNumeroOrden = pathBDCotizacion +
                        strNumeroOrden + ".pdf";

                //
                usuarioBean.setIdNivel(xIdNivelVendedor);

                //
                String strEmailVendedor = usuarioBean.emailRutaNivel(xIdRuta);

                //
                usuarioBean.setIdNivel(xIdNivelDirector);

                //
                String strEmailDirector = usuarioBean.emailRutaNivel(xIdRuta);

                //
                validacion.reasignar("EMAIL", strEmailCliente);

                //
                validacion.validaCampoEmail();

                if (!validacion.isValido()) {

                    //
                    strEmailCliente = strEmailDirector;

                }

                //
                ProcesoEnviaEmail envia = new ProcesoEnviaEmail();

                //
                envia.enviarEmail(fileNumeroOrden,
                        strEmailCliente,
                        strEmailVendedor,
                        strEmailDirector,
                        strNumeroOrden,
                        strFechaOrden);

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdPeriodo(idPeriodo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
                agendaLogVisitaBean.setEstado(estadoProgramado);

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String ipTx = procesoIp.getIpTx(request);

                agendaLogVisitaBean.setIpTx(ipTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finalizaVisita();

                // ingresaLogVisita Nuevo
                agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                int idLogProximo = agendaLogVisitaBean.maximoIdLog() + 1;

                //
                agendaLogVisitaBean.setIdLog(idLogProximo);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdPeriodo(idPeriodo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(estadoProgramado);
                agendaLogVisitaBean.setEstado(estadoActivo);

                // ingresaLogVisita = 9
                boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

                return "/jsp/mnuControlClienteBB.jsp";

            }
        }

        return "/jsp/mnuControlClienteBB.jsp";

    }

    private static String padStringLeft(String orig, int size, String padChar) {
        if (orig == null) {
            orig = "<null>";
        }
        StringBuffer buffer = new StringBuffer(" ");
        int extraChars = size - orig.length();

        for (int i = 0; i < extraChars; i++) {
            buffer.append(padChar);
        }

        buffer.append(orig);
        return (buffer.toString());
    }

    private static String padStringRight(String orig, int size, String padChar) {
        if (orig == null) {
            orig = "<null>";
        }

        StringBuffer buffer = new StringBuffer(" ");
        int extraChars = size - orig.length();
        buffer.append(orig);

        for (int i = 0; i < extraChars; i++) {
            buffer.append(padChar);
        }
        return (buffer.toString());
    }
}