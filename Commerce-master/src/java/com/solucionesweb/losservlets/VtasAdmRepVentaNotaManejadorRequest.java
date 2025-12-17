package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//validacion
import com.solucionesweb.losbeans.utilidades.Validacion;
/**
 * El reporte muestra las notas crédito hechas a las facturas realizadas en el 
 * rango de fechas seleccionadas y el responsable correspondiente. /
 * vtaContenedorRepVentaNota.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest
 */
// 
public class VtasAdmRepVentaNotaManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")- Permite ver un reporte en pdf del cliente seleccionado /
     * ("Salir")- Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros/
     */
 
    public VtasAdmRepVentaNotaManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "fecha inicial"-Fecha inicial del reporte /
     * "Fecha final"-Fecha limite del reporte /
     * "Alcance"-Selecciona clientes/
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
                String xIdVendedor = request.getParameter("xIdVendedor");

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
                LocalIpBean localIpBean = new LocalIpBean();

                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                localIpBean.setIdLocal(xIdLocalUsuario);

                fachadaLocalIp = localIpBean.listaUnLocal();


                //
                GeneraPdfAllResurtido generaPdfAllResurtido = new GeneraPdfAllResurtido();

                //
                generaPdfAllResurtido.setTituloReporte("REPORTE NOTAS");
                generaPdfAllResurtido.setFechaInicial(xFechaInicial);
                generaPdfAllResurtido.setFechaFinal(xFechaFinal);
                generaPdfAllResurtido.setIdLocal(xIdLocalUsuario);
                generaPdfAllResurtido.setIdVendedor(xIdVendedor);
                generaPdfAllResurtido.setIpServidor(fachadaLocalIp.getIp());
                generaPdfAllResurtido.setPuertoHttp(fachadaLocalIp.getPuertoHttp());



                //
                if (generaPdfAllResurtido.getIdVendedor() > 0) {
                    generaPdfAllResurtido.setNombreReporte("VtasRepAllResurtidoNotaRol");
                } else {
                    generaPdfAllResurtido.setNombreReporte("VtasRepAllResurtidoNota");
                }

                //
                generaPdfAllResurtido.setIdTipoOrdenINI(29);
                generaPdfAllResurtido.setIdTipoOrdenFIN(29);
                generaPdfAllResurtido.setIndicadorINI(xIndicadorInicial);
                generaPdfAllResurtido.setIndicadorFIN(xIndicadorFinal);

                //
                generaPdfAllResurtido.generaPdf(request, response);

            }

        }

        //
        return "/jsp/empty.htm";

    }
}
