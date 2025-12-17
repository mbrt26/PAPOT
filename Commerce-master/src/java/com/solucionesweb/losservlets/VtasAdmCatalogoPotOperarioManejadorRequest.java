package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;

// Importa la clase que contiene el PluInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;
import com.solucionesweb.losbeans.negocio.JobOperacionOperarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
/**
 * Esta ventana permite ver una lista de operarios  y una operación para cada uno./
 * vtaContenedorCatalogoPotOperario.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest/
 */
public class VtasAdmCatalogoPotOperarioManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-Retorna al menu principal /
     * ("Ingresar")-Permite relacionar una operacion con un operario /
     * ("Guardar")-Permite guardar relacion  /
     * ("Retirar")permite eliminar relacion /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmCatalogoPotOperarioManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Operacion"-seleccione operacion /
     * "Operarios"-seleccione operario /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp).
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
                String xIdOperario = request.getParameter("xIdVendedor");

                //
                JobOperacionOperarioBean jobOperacionOperarioBean = new JobOperacionOperarioBean();

                jobOperacionOperarioBean.setIdLocal(xIdLocal);
                jobOperacionOperarioBean.setIdOperacion(xIdOperacion);
                jobOperacionOperarioBean.setIdOperario(xIdOperario);

                //
                jobOperacionOperarioBean.retira();

                //---
                FachadaJobOperacionOperario fachadaJobOperacionOperario
                                            = new FachadaJobOperacionOperario();

                //
                fachadaJobOperacionOperario.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaJobOperacionOperario",
                        fachadaJobOperacionOperario);

                return "/jsp/vtaContenedorCatalogoPotOperario.jsp";


            }


            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdOperario = request.getParameter("xIdVendedor");

                //
                JobOperacionOperarioBean jobOperacionOperarioBean = new JobOperacionOperarioBean();

                jobOperacionOperarioBean.setIdLocal(xIdLocalUsuario);
                jobOperacionOperarioBean.setIdOperacion(xIdOperacion);
                jobOperacionOperarioBean.setIdPeriodo(xIdPeriodo);
                jobOperacionOperarioBean.setIdOperario(xIdOperario);
                jobOperacionOperarioBean.setEstado(xEstadoActivo);

                //
                jobOperacionOperarioBean.ingresa();

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
                return "/jsp/vtaFrmIngCatalogoPotOperario.jsp";

            }

            // Ingresar
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");

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
                return "/jsp/vtaFrmIngCatalogoPotOperario.jsp";

            }

        }

        return "/jsp/vtaContenedorCatalogoPotOperario.jsp";
    }
}
