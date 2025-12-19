package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.LocalCajaBean;
import com.solucionesweb.losbeans.utilidades.Day;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Validacion;

//
public class VtasControlOTExternaManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasControlOTExternaManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoTerceroCliente = 1;
        int xIdTipoOrden = 9;
        int xIdTipoOrdenProceso = xIdTipoOrden + 50;

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
        
        //Aca obtengo la ip de la base de datos
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
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();

        //
        int xIdTipoTercero = 1;
        int xEstadoSuspendido = 8;
        
        //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);
        
        //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                    = new FachadaAgendaLogVisitaBean();

    //
    fachadaAgendaLogVisitaBean      =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    //
    int xIdLogActual                = fachadaAgendaLogVisitaBean.getIdLog();

    //
    if (xIdLogActual==0) {

       //
       int idPeriodo            = 200611;
       int estadoAtendido       = 1; // visitaActiva
       int estadoProgramada     = 9; // visitaProgramada
       int idEstadoVisita       = 1; // Programada
       String xIdSucursal       = "";

       //
       int idLog                = agendaLogVisitaBean.maximoIdLog() + 1;

       //
       agendaLogVisitaBean.setIdCliente(xIdLocalUsuario);
       agendaLogVisitaBean.setIdUsuario(idUsuario);
       agendaLogVisitaBean.setIdPeriodo(idPeriodo);
       agendaLogVisitaBean.setFechaVisita(strFechaVisita);
       agendaLogVisitaBean.setIdLog(idLog);
       agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
       agendaLogVisitaBean.setEstado(estadoAtendido);

       //
       boolean okRetirar          =
       agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoProgramada);


       // estadoActivo = 9
       agendaLogVisitaBean.setEstado(estadoProgramada);

       //
       boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

    } else {

       //
       agendaLogVisitaBean.setIdCliente(xIdLocalUsuario);
       agendaLogVisitaBean.setIdUsuario(idUsuario);
       agendaLogVisitaBean.setFechaVisita(strFechaVisita);
       agendaLogVisitaBean.setEstado(estadoActivo);

    }

        //
        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

        //
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso);
        fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
        fachadaDctoOrdenBean.setEstado(xEstadoSuspendido);
        fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTercero);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        return "/jsp/vtaContenedorOTExterna.jsp";

    }
}