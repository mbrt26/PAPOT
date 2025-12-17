package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el PagoBean
import com.solucionesweb.losbeans.negocio.PagoBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;


// Importa la clase que contiene el FachadaUsuarioBean
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Importa la clase que contiene el FachadaPagoBean
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//Importa la clase para obtener la ip del almacen
import com.solucionesweb.lasayudas.ProcesoIp;

//Importa la clase para obtener la ip de la base de datos, que esta matriculada
//en la tabla tblLocales
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;

import com.solucionesweb.losbeans.negocio.LocalCajaBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.utilidades.Validacion;

//
public class VtasControlEmpresaPagoCxCPlanillaManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasControlEmpresaPagoCxCPlanillaManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoTerceroCliente = 1;
        int xIdTipoOrden = 9;
        int xIdTipoOrdenTemporal = 59;

        //
        String strIdSucursal = "--";
        String idCliente = "-1";
        int estadoActivo = 9;
        
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
        
        //---
        Day day = new Day();
        
        //
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        
        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        
        //
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setEstado(estadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(idUsuario);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        fachadaAgendaLogVisitaBean.setFechaVisita(strFechaVisita);
        fachadaAgendaLogVisitaBean.setIdLocal(xIdLocalUsuario);
        fachadaAgendaLogVisitaBean.setIdTipoOrden(xIdTipoOrden);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        //
        if (fachadaAgendaLogVisitaBean.getIdUsuario() == 0) {

            //
            fachadaPagoBean.setFechaPago(strFechaVisita);
            fachadaPagoBean.setIdLocal(xIdLocalUsuario);
            fachadaPagoBean.setIdTipoOrden(xIdTipoOrden + 50);

            //
            request.setAttribute("fachadaPagoBean", fachadaPagoBean);

            //
            return "/jsp/vtaContenedorPagoCxCPlanilla.jsp";


        } else {

            //
            PagoBean pagoBean = new PagoBean();

            //
            pagoBean.setIdLocal(xIdLocalUsuario);
            pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
            pagoBean.setIdTipoOrden(xIdTipoOrdenTemporal);

            //
            fachadaPagoBean = pagoBean.listaUnPagoFCH();

            //
            if (fachadaPagoBean.getIdLog() == 0) {

                //
                fachadaPagoBean.setFechaPago(strFechaVisita);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenTemporal);

                //
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);

                //
                return "/jsp/vtaContenedorPagoCxCPlanilla.jsp";



            }

            //
            FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

            //
            usuarioBean.setIdUsuario(fachadaPagoBean.getIdUsuario());

            //
            fachadaUsuarioBean = usuarioBean.listaUsuario();


            // xIdTipoOrdenTemporal
            fachadaPagoBean.setIdTipoOrden(xIdTipoOrden);

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);
            request.setAttribute("fachadaPagoBean", fachadaPagoBean);

            //
            return "/jsp/vtaFrmLiqPagoCxCPlanilla.jsp";

        }
    }
}
