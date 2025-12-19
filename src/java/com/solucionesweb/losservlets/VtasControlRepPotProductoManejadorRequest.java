package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

public class VtasControlRepPotProductoManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasControlRepPotProductoManejadorRequest() { }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //
        int xIdTipoTerceroCliente = 1;
        int xIdTipoOrdenProceso = 59;

        //
        int diasAtras = 0;

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

        //
        String xIddUsuario = usuarioBean.getIdUsuarioStr();
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();

        //
        FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

        //
        fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
        fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);
        fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
        fachadaColaboraReporteDctoBean.setIdTipoOrden(xIdTipoOrdenProceso);


        //
        request.setAttribute("fachadaColaboraReporteDctoBean",
                fachadaColaboraReporteDctoBean);

        //
        return "/jsp/vtaContenedorRepPotProducto.jsp";

    }
}
