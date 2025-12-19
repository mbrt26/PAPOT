
package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;


// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;


// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;

/**
 * Donde este muestra un reporte en Excel con los ingresos hechos en el rango de fechas. /
 * vtaContenedorRepVentaIngresos.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepCuadreCajaIngresoManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte en pdf/
     * ("Regresar")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmRepCuadreCajaIngresoManejadorRequest() {
    }
/**
     * BUTTON PARAMETER--
     * "Fecha inicial"-Fecha inicial para ver un reporte /
     * "Fecha Final"-Fecha limite para ver un reporte /
     * 
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        String accionContenedor = request.getParameter("accionContenedor");


        
        //---
        


        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
      
 
        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {


            // Regresar
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

          

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
               
                String xFechaCorte = request.getParameter("xFechaFinal");
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdAlcance  = request.getParameter("xIdAlcance");
                

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA CORTE", xFechaCorte);
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaTipoOrdenSubcuenta fachadaTipoOrdenSubcuenta
                                              = new FachadaTipoOrdenSubcuenta();

                //
                fachadaTipoOrdenSubcuenta.setIdLocal(xIdLocalUsuario);
                fachadaTipoOrdenSubcuenta.setFechaDcto(xFechaCorte);
                fachadaTipoOrdenSubcuenta.setIdAlcance(xIdAlcance);
                fachadaTipoOrdenSubcuenta.setFechaInicial(xFechaInicial);
                fachadaTipoOrdenSubcuenta.setFechaFinal(xFechaFinal);


                //
                request.setAttribute("fachadaTipoOrdenSubcuenta",
                        fachadaTipoOrdenSubcuenta);

               response.setContentType("application/vnd.ms-excel");
               response.setHeader("Content-Disposition",
                                             "attachment;filename=archivo.xls");

                //
                return "/jsp/vtaFrmLstContableComprobantePeriodo.jsp";

            }

        }

        return "/jsp/empty.htm";

    }
}
