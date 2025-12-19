package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

import com.solucionesweb.losbeans.negocio.TerceroBean;

//Importa la clase para obtener la ip del almacen
import com.solucionesweb.lasayudas.ProcesoIp;

//Importa la clase para obtener la ip de la base de datos, que esta matriculada
//en la tabla tblLocales
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;

import com.solucionesweb.losbeans.negocio.LocalCajaBean;

//
public class VtasControlResurtidoRecepcionManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasControlResurtidoRecepcionManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


         //
        int xDiasHistoria              = 30;
        int xDiasInventario            = 15;
        int xEstadoActivo              = 9;
        String xIdClienteNulo          = "-1";

        //
        int xIdTipoTerceroProveedor = 2;
        int xIdTipoOrdenCompra      = 1;
        int xIdTipoOrdenPedidoProceso = xIdTipoOrdenCompra + 50;
        
        // ------------------------------- Valida conexion IP ------------------
        String ipTx = null;

        //Aca se obtiene la Ip de donde se esta ingresando
        ProcesoIp obtieneIp = new ProcesoIp();

        //
        ipTx = obtieneIp.getIpTx(request);

        //Aca obtendo la ip de la base de datos
        FachadaLocalCaja fachadaLocalCaja = new FachadaLocalCaja();

        LocalCajaBean localCajaBean = new LocalCajaBean();

        localCajaBean.setIpLocal(ipTx);

        ///
        fachadaLocalCaja = localCajaBean.listaFCH();

        //--
        if (fachadaLocalCaja.getIpLocal() == null) {
            // validacion
            Validacion validacion = new Validacion();

            validacion.setDescripcionError("OPERACION NO AUTORIZADA DESDE CONEXION EXTERNA");

            //
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";
        }        

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        double xIdUsuario    = usuarioBean.getIdUsuario();
        String xIdUsuarioStr = usuarioBean.getIdUsuarioSf0();
        int xIdLocalUsuario  = usuarioBean.getIdLocalUsuario();
        int xIdNivel         = usuarioBean.getIdNivel();


        // ----
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.listaLogActivo();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        //
        boolean xOkProceso =
                agendaLogVisitaBean.validaTipoOrdenProceso(
                xIdTipoOrdenPedidoProceso);
        //
        if (!xOkProceso) {

            boolean xOkOcupado = agendaLogVisitaBean.validaLogOcupado();

            //
            if (xOkOcupado) {

                // validacion
                Validacion validacion = new Validacion();

                validacion.setDescripcionError("ERROR, DEBE TERMINAR PROCESO ACTIVO");

                //
                request.setAttribute("validacion", validacion);
                return "/jsp/gralError.jsp";
            }

            //
            if (!xOkOcupado) {

                //
                int idPeriodo = 200611;
                int estadoAtendido = 1; // visitaActiva
                int estadoProgramada = 9; // visitaProgramada
                int idEstadoVisita = 1; // Programada

                //
                int idLog = agendaLogVisitaBean.maximoIdLog() + 1;

                //
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setIdCliente(xIdUsuarioStr);
                agendaLogVisitaBean.setIdPeriodo(idPeriodo);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);
                agendaLogVisitaBean.setIdLog(idLog);
                agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
                agendaLogVisitaBean.setEstado(estadoAtendido);
                agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                boolean okRetirar =
                        agendaLogVisitaBean.actualizaLogVisitaUsuario(
                        estadoProgramada);

                // estadoActivo = 9
                agendaLogVisitaBean.setEstado(estadoProgramada);

                //
                boolean okIngreso = agendaLogVisitaBean.ingresa();

                xIdLogActual = idLog;
            }
        }

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.listaLogActivo();

        //
        fachadaAgendaLogVisitaBean.setIdUsuario(xIdLocalUsuario);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        //
        TerceroBean terceroBean = new TerceroBean();

        //
        terceroBean.setIdCliente(xIdClienteNulo);
        terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

        //
        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
        fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

        //
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

        //
        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

        //
        dctoOrdenBean.setIdLog(xIdLogActual);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

        //
        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

        //
        fachadaDctoOrdenBean =
                dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

        //
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
        fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
        fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
        fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
        fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
        fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);

        //
        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

        //
        return "/jsp/vtaContenedorResurtidoRecepcion.jsp";

    }
}
