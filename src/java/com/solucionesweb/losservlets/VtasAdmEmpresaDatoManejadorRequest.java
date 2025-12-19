package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;
/**
 * Este servlet permite consultar toda la informacion del cliente seleccionado
 * (direccion, telefono, ciudad, etc.) disponible en la base de datos /
 * vtaContenedorEmpresaDato.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest
 */


public class VtasAdmEmpresaDatoManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON--
     * ("Regresar")-Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros/
     */
    public VtasAdmEmpresaDatoManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        String idTipoPedido = "PB";
        int idTipoOrdenPedido = 9;
        int estadoActivo = 9;

        // strFechaVisita
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }

        }

        return "/jsp/empty.htm";

    }
}
