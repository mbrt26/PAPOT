package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import com.solucionesweb.lasayudas.ProcesoIp;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaColaboraLogisticaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

//
import com.solucionesweb.losbeans.negocio.DctoAuditoriaBean;
import com.solucionesweb.losbeans.negocio.DctoBean;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
import com.solucionesweb.losbeans.utilidades.JhDate;

/**
 * Esta opción tiene el mismo funcionamiento que la opción de histórico de pago,
 * pero se diferencia en que en esta se muestra la historia de las facturas realizadas al cliente seleccionado /
 * vtaContenedorEmpresaHPedido.jsp /
 * 
 *Este servlet implementa la interface GralManejadorRequest
 */

public class VtasAdmEmpresaHPedidoManejadorRequest
        implements GralManejadorRequest {
     /**
     * BUTTON--
     * ("Consultar")-Permite consultar un listado de pedidos / vtaFrmLstEmpresaHPedido.jsp /
     * ("Listar")- Genera un pdf de un listado de pedidos /
     * ("Regresar")/("Salir")- Regresa al menu principal /
     * 
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmEmpresaHPedidoManejadorRequest() { }
     /**
     * PARAMETROS BUTTONS--
     * "FechaInicial"- Fecha inicial para ver un historico de factura /
     * "FechaFinal"- Fecha final para ver un historico de factura /
     * 
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        String idTipoPedido = "PB";
        int estadoActivo = 9;
        String xIdTipoOrdenCadena = "9,29"; // venta + nota venta


        // strFechaVisita
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();

        //
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        int xIndicadorInicial = usuarioBean.getIndicadorInicial();
        int xIndicadorFinal = usuarioBean.getIndicadorFinal();

        //---
        JhDate jhDate = new JhDate();

        //
        String fechaTxHM = null;

        try {
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {}

        // Valida conexion IP -------------------
        String ipTx   = null;

        //Aca se obtiene la Ip de donde se esta ingresando
        ProcesoIp obtieneIp = new ProcesoIp();
        ipTx = obtieneIp.getIpTx(request);



        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("detallarCotizacion") == 0) {

                String idLocal = request.getParameter("idLocal");
                String idTipoOrden = request.getParameter("idTipoOrden");
                String idOrden = request.getParameter("idOrden");

                //
                int xAuditoria = 1;

                DctoBean dctoBean = new DctoBean();

                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                DctoAuditoriaBean dctoAuditoriaBean = new DctoAuditoriaBean();

                dctoBean.setIdOrden(idOrden);
                dctoBean.setIdTipoOrden(idTipoOrden);
                dctoBean.setIdLocal(idLocal);

                fachadaDctoBean = dctoBean.listaUnPagoOrden();

                //
                dctoAuditoriaBean.setIdDcto(fachadaDctoBean.getIdDcto());
                dctoAuditoriaBean.setEstado(fachadaDctoBean.getEstado());
                dctoAuditoriaBean.setIdLocal(fachadaDctoBean.getIdLocal());
                dctoAuditoriaBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrden());
                dctoAuditoriaBean.setIdOrden(fachadaDctoBean.getIdOrden());
                dctoAuditoriaBean.setIdUsuarioResponsable((int) usuarioBean.getIdUsuario());
                dctoAuditoriaBean.setIpTx(ipTx);
                dctoAuditoriaBean.setFechaAuditoria(fechaTxHM);
                dctoAuditoriaBean.setIdAuditoria(xAuditoria);
              
                //
                dctoAuditoriaBean.ingresaDctoAuditoria();

                //
                String numeroDeOrden = Integer.toString(29);

                //
                LocalIpBean localIpBean = new LocalIpBean();

                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                localIpBean.setIdLocal(xIdLocalUsuario);

                fachadaLocalIp = localIpBean.listaUnLocal();


                //
                if (idTipoOrden.equals(numeroDeOrden)) {

                    GeneraPDFNota generaPDFNota = new GeneraPDFNota();

                    //
                    generaPDFNota.setIdLocal(request.getParameter("idLocal"));
                    generaPDFNota.setIdOrden(request.getParameter("idOrden"));
                    generaPDFNota.setIdTipoOrden(request.getParameter("idTipoOrden"));

                    //
                    generaPDFNota.generaPdf(request, response);

                } else {

                    //
                    GeneraPDFFactura generaPDFServlet = new GeneraPDFFactura();

                    //
                    generaPDFServlet.setIdLocal(xIdLocalUsuario);
                    generaPDFServlet.setIdOrden(idOrden);
                    generaPDFServlet.setIdTipoOrden(idTipoOrden);

                    //
                    generaPDFServlet.generaPdf(request, response);
                }
            }

            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoTercero = request.getParameter("xIdTipoTercero");

                //
                GeneraPDFHistoriaCxC generaPDF = new GeneraPDFHistoriaCxC();

                //
                generaPDF.setIdCliente(xIdCliente);
                generaPDF.setIdTipoOrdenCadena(xIdTipoOrdenCadena);
                generaPDF.setIdLocal(xIdLocal);
                generaPDF.setFechaInicial(xFechaInicial);
                generaPDF.setFechaFinal(xFechaFinal);
                generaPDF.setTerceroReporte("CLIENTE");
                generaPDF.setTituloReporte("HISTORICO PEDIDOS DEL " + xFechaInicial
                        + " AL " + xFechaFinal);
                generaPDF.setIndicadorINI(xIndicadorInicial);
                generaPDF.setIndicadorFIN(xIndicadorFinal);

                //
                generaPDF.generaPdf(request, response);

            }

            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoTercero = request.getParameter("xIdTipoTercero");


                // Bean de ValidacionLogPcBean
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                // Valida el fechaInicial
                validacion.validarCampoFecha();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacion", validacion);

                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                // Valida el fechaInicial
                validacion.validarCampoFecha();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacion", validacion);

                    return "/jsp/gralError.jsp";
                }


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
                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";
                }

                // fachadaColaboraLogisticaBean
                FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean = new FachadaColaboraLogisticaBean();
                //
                fachadaColaboraLogisticaBean.setIdUsuario(idUsuario);
                fachadaColaboraLogisticaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaColaboraLogisticaBean.setFechaInicial(xFechaInicial);
                fachadaColaboraLogisticaBean.setFechaFinal(xFechaFinal);
                fachadaColaboraLogisticaBean.setIdTipoPedido(idTipoPedido);
                fachadaColaboraLogisticaBean.setIdTipoOrdenCadena(
                        xIdTipoOrdenCadena);
                fachadaColaboraLogisticaBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraLogisticaBean.setIndicadorInicial(xIndicadorInicial);
                fachadaColaboraLogisticaBean.setIndicadorFinal(xIndicadorFinal);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                fachadaTerceroBean.setIdCliente(xIdCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTercero);
                fachadaTerceroBean.setIdLocal(xIdLocal);

                // Aqui escribe el Bean de Validacion en el Request
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaColaboraLogisticaBean",
                        fachadaColaboraLogisticaBean);
                return "/jsp/vtaFrmLstEmpresaHPedido.jsp";

            }

            // Seleccionar
            if (accionContenedor.compareTo("Seleccionar") == 0) {

                //
                String idLocal = request.getParameter("idLocal");
                String idTipoOrden = request.getParameter("idTipoOrden");
                String idOrden = request.getParameter("idOrden");

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
                dctoOrdenBean.setIdLocal(idLocal);
                dctoOrdenBean.setIdTipoOrden(idTipoOrden);
                dctoOrdenBean.setIdOrden(idOrden);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrden();

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                fachadaAgendaLogVisitaBean.setIdCliente(
                        fachadaDctoOrdenBean.getIdCliente());
                fachadaAgendaLogVisitaBean.setIdLog(fachadaDctoOrdenBean.getIdLog());
                fachadaAgendaLogVisitaBean.setIdUsuario(
                        fachadaDctoOrdenBean.getIdUsuario());


                // idTipoOrdenCotizacion
                int idTipoOrdenCotizacion = 10;
                dctoOrdenBean.setIdLog(fachadaDctoOrdenBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(idTipoOrdenCotizacion);
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaFrmConHPedido.jsp";
            }


        }

        return "/jsp/empty.htm";

    }
}
