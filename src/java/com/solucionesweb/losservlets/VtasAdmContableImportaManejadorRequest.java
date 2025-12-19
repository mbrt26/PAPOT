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

import com.solucionesweb.losbeans.negocio.DctoContableBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;

import com.solucionesweb.lasayudas.ProcesoContableComprobante;
/**
 * Permite ver reportes de comprobantes de ingreso y egreso /
 * vtaContenedorContableImporta.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmContableImportaManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-retorna al menu principal /
     * (Importar)-Permite traer un contable de otroo sistema /
     * ("Confirmar")-permite confirmar el contable importado /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmContableImportaManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Fecha Inicial"-ingreso fecha inicial  para ver el reporte /
     * "Fecha final"-ingreso fecha limite para ver un reporte /
     * "Centro Operativo"-Seleccione centro /
     * "Comprobante"-Seleccione numero de comprobanntes que se necesite ver /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        int xIndicadorInicial = usuarioBean.getIndicadorInicial();
        int xIndicadorFinal = usuarioBean.getIndicadorFinal();
        
        //
        int xIdComprobante_4  = 4;
        int xIdComprobante_2  = 2;
        int xIdComprobante_12 = 12;
        int xIdComprobante_13 = 13;
        int xIdComprobante_6  = 6;
        int xIdComprobante_5  = 5;

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

               //
               String xFechaInicial  = request.getParameter("xFechaInicial");
               String xFechaFinal    = request.getParameter("xFechaFinal");
               String xIdLocal       = request.getParameter("xIdLocal");
               String xIdComprobante = request.getParameter("xIdComprobante");

               //
               ProcesoContableComprobante procesoContableComprobante
                                             = new ProcesoContableComprobante();

               //
               procesoContableComprobante.setIdLocal(
                                              new Integer(xIdLocal).intValue());
               procesoContableComprobante.setIndicadorInicial(xIndicadorInicial);
               procesoContableComprobante.setIndicadorFinal(xIndicadorFinal);
               procesoContableComprobante.setFechaInicial(xFechaInicial);
               procesoContableComprobante.setFechaFinal(xFechaFinal);
               procesoContableComprobante.setIdComprobante(
                                       new Integer(xIdComprobante).intValue() );

               //
               procesoContableComprobante.validaComprobante();

               //
               procesoContableComprobante.ingresaTerceroComprobante();

               //
               procesoContableComprobante.ingresaDctoComprobante();


               //
               return "./GralControladorServlet?nombrePaginaRequest=/potPermisoAdmContableImporta.ctr";

            }

            // Importar
            if (accionContenedor.compareTo("Importar") == 0) {

               //
               String xFechaInicial  = request.getParameter("xFechaInicial");
               String xFechaFinal    = request.getParameter("xFechaFinal");
               String xIdLocal       = request.getParameter("xIdLocal");
               String xIdComprobante = request.getParameter("xIdComprobante");

               //
               ProcesoContableComprobante procesoContableComprobante
                                             = new ProcesoContableComprobante();

               //
               procesoContableComprobante.setIdLocal(
                                              new Integer(xIdLocal).intValue());
               procesoContableComprobante.setIndicadorInicial(xIndicadorInicial);
               procesoContableComprobante.setIndicadorFinal(xIndicadorFinal);
               procesoContableComprobante.setFechaInicial(xFechaInicial);
               procesoContableComprobante.setFechaFinal(xFechaFinal);
               procesoContableComprobante.setIdComprobante(
                                       new Integer(xIdComprobante).intValue() );

               //
               procesoContableComprobante.validaComprobante();

               //
               FachadaTipoOrdenSubcuenta fachadaTipoOrdenSubcuenta
                                              = new FachadaTipoOrdenSubcuenta();

               //
               fachadaTipoOrdenSubcuenta.setIdLocal(xIdLocal);
               fachadaTipoOrdenSubcuenta.setIdComprobante(xIdComprobante);
               fachadaTipoOrdenSubcuenta.setFechaInicial(xFechaInicial);
               fachadaTipoOrdenSubcuenta.setFechaFinal(xFechaFinal);

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaTipoOrdenSubcuenta",
                                                     fachadaTipoOrdenSubcuenta);


               //
               if ((fachadaTipoOrdenSubcuenta.getIdComprobante() == xIdComprobante_4) ||
                   (fachadaTipoOrdenSubcuenta.getIdComprobante() == xIdComprobante_6) ||
                   (fachadaTipoOrdenSubcuenta.getIdComprobante() == xIdComprobante_5) ||
                   (fachadaTipoOrdenSubcuenta.getIdComprobante() == xIdComprobante_2))  {

                   //
                   return "/jsp/vtaFrmLstContableImporta.jsp";

               }

               //
               if ((fachadaTipoOrdenSubcuenta.getIdComprobante() == xIdComprobante_12) ||
                   (fachadaTipoOrdenSubcuenta.getIdComprobante() == xIdComprobante_13)) {

                   //
                   return "/jsp/vtaFrmPagContableImporta.jsp";

               }
            }
        }

        //
        return "/jsp/empty.htm";

    }
}
