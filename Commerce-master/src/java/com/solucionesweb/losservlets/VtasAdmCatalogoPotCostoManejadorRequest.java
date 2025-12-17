package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import com.solucionesweb.losbeans.fachada.FachadaJobCosto;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;

// Importa la clase que contiene el PluInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario;
import com.solucionesweb.losbeans.negocio.JobCostoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;
import com.solucionesweb.losbeans.negocio.JobOperacionCostoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionOperarioBean;

import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
/**
 * Esta interfaz permite ver un catálogo de costos por operación, también permite 
 * retirar costos  e ingresar nuevos./
 * vtaContenedorCatalogoPotCosto.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest/
 */
public class VtasAdmCatalogoPotCostoManejadorRequest
        implements GralManejadorRequest {
/**
     * BUTTON--
     * ("Regresar")-Retorna al menu principal /
     * ("Ingresar")-Permite relacionar costo con operacion /
     * ("Guardar")-Permite guardar costos de la relacion /
     * ("Retirar")permite eliminar relacion /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmCatalogoPotCostoManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Tipo Costo"-seleccione Tipo de costo de la operacion /
     * "Operacion"-seleccione operacion /
     * "Cantidad base"-ingreso de valor basico /
     * "Vr. costo unitario"-ingreso valor costo unitario /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //--
        int xIdPeriodo = 201203;
        int xEstadoActivo = 1;

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }
            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            //
            HttpSession sesion = request.getSession();

            //
            UsuarioBean usuarioBean =
                    (UsuarioBean) sesion.getAttribute("usuarioBean");
            String idUsuario = usuarioBean.getIdUsuarioStr();
            int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

            // Guardar
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdCosto = request.getParameter("xIdCosto");

                //
                JobOperacionCostoBean jobOperacionCostoBean = new JobOperacionCostoBean();

                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);
                jobOperacionCostoBean.setIdCosto(xIdCosto);

                //
                jobOperacionCostoBean.retira();

                //---
                FachadaJobOperacionOperario fachadaJobOperacionOperario
                                           = new FachadaJobOperacionOperario();

                //
                fachadaJobOperacionOperario.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaJobOperacionOperario",
                        fachadaJobOperacionOperario);

                //
                return "/jsp/vtaContenedorCatalogoPotCosto.jsp";


            }


            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdCosto = request.getParameter("xIdCosto");
                String xCantidadBase = request.getParameter("xCantidadBase");
                String xVrCostoBase = request.getParameter("xVrCostoBase");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("CANTIDAD BASE", xCantidadBase);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {


                    // Aqui escribe el Bean de Validacio
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("VR.COSTO UNITARIO", xVrCostoBase);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {


                    // Aqui escribe el Bean de Validacio
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                JobOperacionCostoBean jobOperacionCostoBean
                                                  = new JobOperacionCostoBean();

                //
                jobOperacionCostoBean.setIdLocal(xIdLocalUsuario);
                jobOperacionCostoBean.setIdCosto(xIdCosto);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);
                jobOperacionCostoBean.setCantidadBase(xCantidadBase);
                jobOperacionCostoBean.setVrCostoBase(xVrCostoBase);
                jobOperacionCostoBean.setEstado(xEstadoActivo);
                jobOperacionCostoBean.setIdPeriodo(xIdPeriodo);

                //
                jobOperacionCostoBean.ingresa();

                //---
                FachadaJobOperacionOperario fachadaJobOperacionOperario
                                           = new FachadaJobOperacionOperario();

                //
                fachadaJobOperacionOperario.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaJobOperacionOperario",
                        fachadaJobOperacionOperario);

                //
                return "/jsp/vtaContenedorCatalogoPotCosto.jsp";

            }

            // Ingresar
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdCosto = request.getParameter("xIdCosto");

                //
                FachadaJobCosto fachadaJobCosto = new FachadaJobCosto();

                //
                JobCostoBean jobCostoBean = new JobCostoBean();

                //
                jobCostoBean.setIdCosto(xIdCosto);

                //
                fachadaJobCosto = jobCostoBean.listaUnFCH();

                //
                request.setAttribute("fachadaJobCosto", fachadaJobCosto);

                //
                FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

                //
                JobOperacionBean jobOperacionBean = new JobOperacionBean();

                //
                jobOperacionBean.setIdOperacion(xIdOperacion);

                //
                fachadaJobOperacion = jobOperacionBean.listaUnaOperacionFCH();

                //
                request.setAttribute("fachadaJobOperacion", fachadaJobOperacion);

                //
                return "/jsp/vtaFrmIngCatalogoPotCosto.jsp";

            }
        }

        return "/jsp/vtaContenedorCatalogoPotCosto.jsp";
    }
}
