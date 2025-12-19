package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el ValidacionColaboraReporteDctoBean
import com.solucionesweb.losbeans.utilidades.ValidacionColaboraReporteDctoBean;

// Importa la clase que contiene el FachadaColaboraLogisticaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

/**
 * Este servlet permite ver los historicos de compras para los proveedores. /
 * vtaContenedorEmpresaHPedidoProveedor.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmEmpresaHPedidoProveedorManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite consultar un listado de compras /
     * Al seleccionar #Dscto genera un pdf de la compra elegida /vtaFrmLstEmpresaHPedidoProveedor.jsp /
     * ("Listar")-Genera un pdf de un listado de compras /taFrmLstEmpresaHPedidoProveedor.jsp /
     * ("Regresar")-Regresa al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmEmpresaHPedidoProveedorManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Fecha Inicial"-Fecha inicial para ver un historico de compras /
     * "Fecha Final"-Fecha limite para ver un historico de compras /
     * 
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp)./
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        int xIdTipoTerceroCliente = 2;
        String idTipoPedido = "PB";
        int idTipoOrdenPedido = 1;
        int estadoActivo = 9;
        String xIdTipoOrdenCadena = "1,21"; // compra + nota compra


        // strFechaVisita
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        int xIndicadorInicial = usuarioBean.getIndicadorInicial();
        int xIndicadorFinal = usuarioBean.getIndicadorFinal();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            // tomar
            if (accionContenedor.compareTo("detallarCotizacion") == 0) {

                //
                String xIdLocal = request.getParameter("idLocal");
                String xIdOrden = request.getParameter("idOrden");
                String xIdTipoOrden = request.getParameter("idTipoOrden");

                //
                String numeroDeOrden = Integer.toString(21);


                //
                if (xIdTipoOrden.equals(numeroDeOrden)) {

                    GeneraPDFNota generaPDFNota = new GeneraPDFNota();

                    //
                    generaPDFNota.setIdLocal(request.getParameter("idLocal"));
                    generaPDFNota.setIdOrden(request.getParameter("idOrden"));
                    generaPDFNota.setIdTipoOrden(request.getParameter("idTipoOrden"));

                    //
                    generaPDFNota.generaPdf(request, response);

                } else {


                    //
                    GeneraPDFResurtidoCompraLegalizado generaPDF
                                     = new GeneraPDFResurtidoCompraLegalizado();

                    //
                    generaPDF.setIdLocal(xIdLocal);
                    generaPDF.setIdOrden(xIdOrden);
                    generaPDF.setIdTipoOrden(xIdTipoOrden);
                    generaPDF.setTituloReporte("COMPRA CUMPLIDA");
                    generaPDF.setReporteName("RepEmpresaResurtidoCompraLegalizado");

                    //
                    generaPDF.generaPdf(request, response);
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
                String idTipoOrden = request.getParameter("xIdTipoOrden");
                String fechaInicial = request.getParameter("xFechaInicial");
                String fechaFinal = request.getParameter("xFechaFinal");
                String idCliente = request.getParameter("xIdCliente");
                String idLocal = request.getParameter("xIdLocal");

                //
                GeneraPDFHistoriaCxP generaPDF = new GeneraPDFHistoriaCxP();

                //
                generaPDF.setIdCliente(idCliente);
                generaPDF.setIdTipoOrdenCadena(xIdTipoOrdenCadena);
                generaPDF.setIdLocal(idLocal);
                generaPDF.setFechaInicial(fechaInicial);
                generaPDF.setFechaFinal(fechaFinal);
                generaPDF.setTerceroReporte("PROVEEDOR");
                generaPDF.setTituloReporte("HISTORICO COMPRAS");
                generaPDF.setIndicadorINI(xIndicadorInicial);
                generaPDF.setIndicadorFIN(xIndicadorFinal);

                //
                generaPDF.generaPdf(request, response);

            }

            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdTipoTercero = request.getParameter("xIdTipoTercero");

                // Bean de ValidacionLogPcBean
                ValidacionColaboraReporteDctoBean campoAValidar
                         = new ValidacionColaboraReporteDctoBean("fechaInicial", fechaInicial);

                // Valida el fechaInicial
                campoAValidar.validarCampoFecha();

                if (campoAValidar.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacionColaboraReporteDctoBean",
                            campoAValidar);

                    return "/jsp/gralErrReporteCliente.jsp";
                }

                // Valida el fechaFinal
                campoAValidar.reasignar("fechaFinal", fechaFinal);

                // Valida el fechaFinal
                campoAValidar.validarCampoFecha();

                if (campoAValidar.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacionColaboraReporteDctoBean",
                            campoAValidar);

                    return "/jsp/gralErrReporteCliente.jsp";
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
                    return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";
                }

                // fachadaColaboraLogisticaBean
                FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean
                                           = new FachadaColaboraLogisticaBean();
                //
                fachadaColaboraLogisticaBean.setIdUsuario(idUsuario);
                fachadaColaboraLogisticaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaColaboraLogisticaBean.setFechaInicial(fechaInicial);
                fachadaColaboraLogisticaBean.setFechaFinal(fechaFinal);
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
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaColaboraLogisticaBean",
                        fachadaColaboraLogisticaBean);
                return "/jsp/vtaFrmLstEmpresaHPedidoProveedor.jsp";

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
