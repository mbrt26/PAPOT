package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa la clase que contiene el PluComboBean
import com.solucionesweb.losbeans.negocio.PluComboBean;

// Importa la clase que contiene el ColaboraPluCombo
import com.solucionesweb.losbeans.colaboraciones.ColaboraPluCombo;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluCombo;
import com.solucionesweb.losbeans.negocio.PluBean;
/**
 * Al presionar esta opción, el usuario debe seleccionar el producto deseado y 
 * luego ingresar el código y la cantidad que necesita. /
 * vtaContenedorCatalogoCombo.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 * 
 */
//
public class VtasAdmCatalogoComboManejadorRequest
                                               implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-("Salir")-Retorna al menu principal /
     * ("Ingresar")-Ingresa cambios para un articulo /vtaFrmSelCatalogoCombo.jsp /
     * ("Modificar")-Permite confirmar cambios de cantidades de un producto /vtaFrmModCatalogoCombo.jsp /
     * ("Retirar")-Permite retirar un producto un combo / 
     * 
     * Metodo contructor por defecto, es decir, sin parametros/
     */

    public VtasAdmCatalogoComboManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Codigo"-ingresa codigo de articulo /
     * "Cantidad"-Ingresa cantidad del articulo/
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp)./
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }
            //
             if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            // Listar
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xCantidad = request.getParameter("xCantidad");
                String xIdPluCombo = request.getParameter("xIdPluCombo");

                //
                int xEstadoOk = 1;
                int xIdSeqOk = 0;
                int xIdTipoInventario = 1;

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("REFERENCIA", xIdPlu);

                //
                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("CANTIDAD", xCantidad);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(xIdPlu);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                // isValido
                if (fachadaPluBean.getIdTipo() != xIdTipoInventario) {

                    //
                    validacion.reasignar("REFERENCIA " + xIdPlu
                            + " NO CONTROLA INVENTARIO", xIdPlu);

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }


                //
                PluComboBean pluComboBean = new PluComboBean();

                //
                int xMaxIdSeq = pluComboBean.listaMaximaIdSeq() + 1;

                //
                pluComboBean.setIdPluCombo(xIdPluCombo);
                pluComboBean.setIdPlu(xIdPlu);
                pluComboBean.setCantidad(xCantidad);
                pluComboBean.setEstado(xEstadoOk);
                pluComboBean.setIdSeq(xMaxIdSeq);

                //
                pluComboBean.retirar();

                //
                pluComboBean.ingresa();

                //
                colaboraPlu.setIdPlu(xIdPluCombo);

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                if (fachadaPluBean.getIdLinea() == 0) {

                    //
                    validacion.reasignar("NO EXISTE REFERENCIA", xIdPlu);

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);
                return "/jsp/vtaFrmModCatalogoCombo.jsp";

            }

            // Traer
            if (accionContenedor.compareTo("Traer") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");

                // Valida idLinea
                if (xIdPlu != null) {

                    //
                    xIdPlu = xIdPlu.trim();

                    // validacion
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("CODIGO", xIdPlu);

                    validacion.validarCampoEntero();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    ColaboraPlu colaboraPlu = new ColaboraPlu();

                    //
                    colaboraPlu.setIdPlu(xIdPlu);

                    //
                    FachadaPluBean fachadaPluBean = new FachadaPluBean();

                    //
                    fachadaPluBean = colaboraPlu.listaUnPluFCH();

                    //
                    if (fachadaPluBean.getIdLinea() == 0) {

                        //
                        validacion.reasignar("NO EXISTE REFERENCIA", xIdPlu);

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    request.setAttribute("fachadaPluBean", fachadaPluBean);

                    //
                    return "/jsp/vtaFrmModCatalogoCombo.jsp";

                }
            }

            // Traer
            if (accionContenedor.compareTo("TraerCambio") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdPluCombo = request.getParameter("xIdPluCombo");

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(xIdPluCombo);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                ColaboraPluCombo colaboraPluCombo = new ColaboraPluCombo();

                colaboraPluCombo.setIdPlu(xIdPlu);
                colaboraPluCombo.setIdPluCombo(xIdPluCombo);

                //
                FachadaPluCombo fachadaPluCombo = new FachadaPluCombo();

                //
                fachadaPluCombo = colaboraPluCombo.listaUnComboPluFCH();

                //
                request.setAttribute("fachadaPluCombo", fachadaPluCombo);
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmLstCatalogoCombo.jsp";

            }

            // Modificar
            if (accionContenedor.compareTo("Modificar") == 0) {

                //
                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xCantidad = request.getParameter("xCantidad");
                String xIdPluCombo = request.getParameter("xIdPluCombo");

                // Valida Validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("CANTIDAD", xCantidad);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                int xEstadoOk = 1;

                //
                PluComboBean pluComboBean = new PluComboBean();

                //
                int xMaxIdSeq = pluComboBean.listaMaximaIdSeq() + 1;

                //
                pluComboBean.setIdPluCombo(xIdPluCombo);
                pluComboBean.setIdPlu(xIdPlu);
                pluComboBean.setCantidad(xCantidad);
                pluComboBean.setIdSeq(xMaxIdSeq);
                pluComboBean.setEstado(xEstadoOk);

                //
                pluComboBean.modifica();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(xIdPluCombo);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmModCatalogoCombo.jsp";

            }

            // Modificar
            if (accionContenedor.compareTo("Retirar") == 0) {

                //
                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdPluCombo = request.getParameter("xIdPluCombo");

                //
                int xEstadoRetiro = 0;

                //
                PluComboBean pluComboBean = new PluComboBean();

                //
                int xMaxIdSeq = pluComboBean.listaMaximaIdSeq() + 1;

                //
                pluComboBean.setIdPluCombo(xIdPluCombo);
                pluComboBean.setIdPlu(xIdPlu);
                pluComboBean.setEstado(xEstadoRetiro);
                pluComboBean.setIdSeq(xMaxIdSeq);

                //
                pluComboBean.modifica();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(xIdPluCombo);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmModCatalogoCombo.jsp";

            }

        }

        return "/jsp/vtaContenedorCatalogoCombo.jsp";

    }
}
