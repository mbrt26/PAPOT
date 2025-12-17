package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import com.solucionesweb.lasayudas.ProcesoIp;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.negocio.LocalCajaBean;
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Validacion;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
 */
public class VtasControlEmpresaNotaManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasControlEmpresaNotaManejadorRequest() {
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
        int diasAtras = 0;
        int estadoActivo = 9;

        //
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        Day fechaFinal = new Day();
        String fechaFinalStr = fechaFinal.getFechaFormateada();

        // FechaInicial
        fechaFinal.advance(diasAtras);
        String fechaInicialStr = fechaFinal.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        // -------------------------------Valida conexion IP -------------------
        String ipTx = null;

        //Aca se obtiene la Ip de donde se esta ingresando
        ProcesoIp obtieneIp = new ProcesoIp();
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

        //----------------------------------------------------------------------
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
        agendaLogVisitaBean.setEstado(estadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(idUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        TerceroBean terceroBean = new TerceroBean();

        //
        terceroBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
        terceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

        //
        fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

        //
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        // Retorna a seleccionar cliente
        if (fachadaTerceroBean.getIdCliente() == null) {

            return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

        }

        //
        FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();

        //
        fachadaColaboraReporteDctoBean.setIdUsuario(idUsuario);
        fachadaColaboraReporteDctoBean.setIdCliente(
                fachadaAgendaLogVisitaBean.getIdCliente());
        fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
        fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);

        //
        request.setAttribute("fachadaColaboraReporteDctoBean",
                fachadaColaboraReporteDctoBean);

        //
        return "/jsp/vtaContenedorEmpresaNota.jsp";

    }
}
