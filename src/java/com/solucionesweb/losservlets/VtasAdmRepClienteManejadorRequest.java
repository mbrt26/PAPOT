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

//Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
/**
 *  Permite ver un reporte de detalle clientes por rango de fechas/
 *  vtaContenedorRepCliente.jsp /
 * 
 *  Este servlet implementa la interface GralManejadorRequest /
 */ 
public class VtasAdmRepClienteManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte de detalle de ventas por rango de fecha y lo muestra en pdf, xmls /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */ 
    public VtasAdmRepClienteManejadorRequest() {
    }
/**
   * BUTTON PARAMETER--
   * "FechaInicial"- Es la fecha inicial para ver un reporte /
   * "Fecha Final"-  ES la fecha limite para ver un reporte /
   * "Destinacion"- Tipo de formato en que se desea ver el reporte /
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp). /
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

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdVendedor = "0";
                String xIdDestinacion = request.getParameter("idDestinacion");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.validarRangoFecha(xFechaInicial, xFechaFinal);

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

                //
                fachadaColaboraReporteDctoBean.setFechaInicial(xFechaInicial);
                fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
                fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraReporteDctoBean.setIdTipoOrdenINI(9);
                fachadaColaboraReporteDctoBean.setIdTipoOrdenFIN(29);                
                fachadaColaboraReporteDctoBean.setIndicadorInicial(
                        xIndicadorInicial);
                fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);

                //
                request.setAttribute("fachadaColaboraReporteDctoBean",
                        fachadaColaboraReporteDctoBean);

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstRepClienteArchivo.jsp";

                }

                //---
                LocalIpBean localIpBean = new LocalIpBean();

                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                localIpBean.setIdLocal(xIdLocalUsuario);

                fachadaLocalIp = localIpBean.listaUnLocal();

                //
                GeneraPdfDctoOT generaPdfDctoOT
                                                  = new GeneraPdfDctoOT();

                //
                generaPdfDctoOT.setTituloReporte("REPORTE DETALLE CLIENTE");
                generaPdfDctoOT.setFechaInicial(xFechaInicial);
                generaPdfDctoOT.setFechaFinal(xFechaFinal);
                generaPdfDctoOT.setIdLocal(xIdLocalUsuario);
                generaPdfDctoOT.setIdVendedor(xIdVendedor);
                generaPdfDctoOT.setIdTipoOrdenINI(9);
                generaPdfDctoOT.setIdTipoOrdenFIN(29);
                generaPdfDctoOT.setIndicadorINI(xIndicadorInicial);
                generaPdfDctoOT.setIndicadorFIN(xIndicadorFinal);
                generaPdfDctoOT.setIpServidor(fachadaLocalIp.getIp());
                generaPdfDctoOT.setPuertoHttp(fachadaLocalIp.getPuertoHttp());
                generaPdfDctoOT.setNombreReporte("VtasRepDetalleCliente");

                //
                generaPdfDctoOT.generaPdf(request, response);

            }
        }

        //
        return "/jsp/empty.htm";

    }
}
