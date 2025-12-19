package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;


import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.utilidades.Validacion;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmOrdenTrabajoProgresoManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmOrdenTrabajoProgresoManejadorRequest() {
    }

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoTerceroCliente = 1;
        int xIdTipoOrden = 9;
        int xIdTipoOrdenProceso = xIdTipoOrden + 50;

        //
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        int xEstadoSuspendido = 8;

        //
        String xIdOperacion = request.getParameter("xIdOperacion");

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {

                //
                return "/jsp/empty.htm";
            }


            //
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                int xIdTipoTercero = 1;

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setEstado(xEstadoSuspendido);
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTercero);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                return "/jsp/vtaContenedorOrdenTrabajoProgreso.jsp";
            }
        }

        //
        return "/jsp/empty.htm";
    }
}
