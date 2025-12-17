package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenEstado;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenEstadoBean;
/**
 * En esta ventana se puede observar el estado de produccion de los pedidos 
 * ingresando el numero de pedido o el cliente /
 * vtaContenedorAdmOTEstado.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmOTEstadoManejadorRequest implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("UnPedido")-permite ver un pedido /
     * ("Consultar")-Pemite ver un listado de articulos en produccion de un cliente  /
     * ("Salir")-("Regresar")-Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmOTEstadoManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Pedido"-ingreso numero de pedido /
     * "Cliente"-Selecciona cliente /
     * "Estado"-Selecciona estado de produccion /
     * "Destinacion"-Forma de ver el reporte excel-pantalla /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        int xIdTipoTercero = 1;

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            // Cumplir
            if (accionContenedor.compareTo("Cumplir") == 0) {

                //
                String xIdLocalTipoOrdenOrdenItemArr[] =
                        request.getParameterValues("xIdLocalTipoOrdenOrdenItem");

                //
                int xIdEstadoCumplido = 9;
                int xLongitud = 0;
                int xUno = 0;
                int xDos = 0;
                int xTres = 0;
                String xIdLocal;
                String xIdTipoOrden;
                String xIdOrden;
                String xItem;

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                for (int i = 0; i < xIdLocalTipoOrdenOrdenItemArr.length; i++) {

                    //
                    xLongitud = xIdLocalTipoOrdenOrdenItemArr[i].length();
                    xUno = xIdLocalTipoOrdenOrdenItemArr[i].indexOf('~');
                    xDos = xIdLocalTipoOrdenOrdenItemArr[i].indexOf('~', xUno + 1);
                    xTres = xIdLocalTipoOrdenOrdenItemArr[i].indexOf('~', xDos + 1);

                    //
                    xIdLocal = xIdLocalTipoOrdenOrdenItemArr[i].substring(0, xUno);
                    xIdTipoOrden = xIdLocalTipoOrdenOrdenItemArr[i].substring(xUno + 1, xDos);
                    xIdOrden = xIdLocalTipoOrdenOrdenItemArr[i].substring(xDos + 1, xTres);
                    xItem = xIdLocalTipoOrdenOrdenItemArr[i].substring(xTres + 1, xLongitud);

                    //
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdOrden(xIdOrden);
                    dctoOrdenDetalleBean.setItem(xItem);
                    dctoOrdenDetalleBean.setIdEstadoRefOriginal(xIdEstadoCumplido);

                    //
                    dctoOrdenDetalleBean.actualizaPedidoOrden();

                }

            }

            // UnPedido
            if (accionContenedor.compareTo("UnPedido") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOrden = request.getParameter("xIdOrden");
                String xItem = request.getParameter("xItem");
                String xNumeroOrden = request.getParameter("xNumeroOrden");

                //--------------------------------------------------------------
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);

                //
                fachadaPluFicha =
                        colaboraOrdenTrabajo.listaUnOTFCH(
                        new Integer(xItem).intValue());

                //
                xIdOrden = fachadaPluFicha.getIdOrdenStr();

                //
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setIdOrden(xIdOrden);
                fachadaPluFicha.setItemPadre(xItem);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //--------------------------------------------------------------
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLocal(xIdLocal);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenBean.setNumeroOrden(xNumeroOrden);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoNumeroOrden();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(fachadaDctoOrdenBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTercero);

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);

                //
                return "/jsp/vtaFrmConOTEstado.jsp";

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdEstado = request.getParameter("xIdEstado");
                String xIdDestinacion = request.getParameter("idDestinacion");
                String xIdTercero = request.getParameter("xIdTercero");

                //
                String xIdTipoOrdenCadena = "59";

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.validarRangoFecha(xFechaInicial, xFechaFinal);

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaColaboraReporteDctoBean.setFechaInicial(xFechaInicial);
                fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
                fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraReporteDctoBean.setIdTipoOrden(xIdTipoOrdenCadena);
                fachadaColaboraReporteDctoBean.setIdTipoTercero(xIdTipoTercero);
                fachadaColaboraReporteDctoBean.setIdEstado(xIdEstado);
                fachadaColaboraReporteDctoBean.setIdCliente(xIdTercero);

                //
                request.setAttribute("fachadaColaboraReporteDctoBean",
                        fachadaColaboraReporteDctoBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(xIdTercero);
                colaboraTercero.setIdTipoTercero(xIdTipoTercero);

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);

                //
                FachadaDctoOrdenEstado fachadaDctoOrdenEstado = new FachadaDctoOrdenEstado();

                //
                DctoOrdenEstadoBean dctoOrdenEstadoBean = new DctoOrdenEstadoBean();

                //
                dctoOrdenEstadoBean.setIdEstado(xIdEstado);

                //
                fachadaDctoOrdenEstado = dctoOrdenEstadoBean.listaUnFCH();

                //
                request.setAttribute("fachadaDctoOrdenEstado",
                        fachadaDctoOrdenEstado);

                //
                if (xIdDestinacion.compareTo("Pantalla") == 0) {

                    //
                    return "/jsp/vtaFrmLstOTEstado.jsp";


                }

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstOTEstadoArchivo.jsp";

                }
            }
        }

        //
        return "/jsp/empty.htm";

    }
}
