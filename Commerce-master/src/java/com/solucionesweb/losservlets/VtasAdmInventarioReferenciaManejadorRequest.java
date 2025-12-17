package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el CategoriaBean
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaCategoriaBean
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
/**
 * El sistema muestra la siguiente interfaz, donde se puede consultar el inventario actual por referencia /
 * vtaContenedorInventarioReferencia.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest
 */

public class VtasAdmInventarioReferenciaManejadorRequest
        implements GralManejadorRequest {
/**
     * BUTTON--
     * ("Traer")-Permite traer un detalle de un producto /
     * ("Salir")-Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmInventarioReferenciaManejadorRequest() {
    }

    /**
   * BUTTON PARAMETER--
   * "Referencia"-Ingresa numero de referencia de un producto /
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // Listar
            if (accionContenedor.compareTo("listaCategoria") == 0) {

                //
                String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");

                //
                int indiceSeparador = xIdLineaCategoria.indexOf("~");
                int Longitud = xIdLineaCategoria.length();
                String xIdLinea = xIdLineaCategoria.substring(0,
                        indiceSeparador);
                String xIdCategoria = xIdLineaCategoria.substring(
                        indiceSeparador + 1, Longitud);

                //
                CategoriaBean categoriaBean = new CategoriaBean();

                //
                FachadaCategoriaBean fachadaCategoriaBean = new FachadaCategoriaBean();

                //
                categoriaBean.setIdLinea(xIdLinea);
                categoriaBean.setIdCategoria(xIdCategoria);

                //
                fachadaCategoriaBean = categoriaBean.listaUnaCategoriaFCH();

                //
                request.setAttribute("fachadaCategoriaBean", fachadaCategoriaBean);

                //
                return "/jsp/vtaFrmSelInventarioReferencia.jsp";

            }

            // Seleccionar
            if (accionContenedor.compareTo("Traer") == 0) {

                String xIdPlu = request.getParameter("xIdPlu");
                String xIdOperacion = request.getParameter("xIdOperacion");

                // Valida idLinea
                if (xIdPlu != null) {

                    //
                    xIdPlu = xIdPlu.trim();

                    //
                    int xIdTipoInventario = 1;

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


                    // isValido
                    if (fachadaPluBean.getIdTipo() != xIdTipoInventario) {

                        //
                        validacion.reasignar("EL ARTICULO NO CONTROLA INVENTARIO", xIdPlu);

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    fachadaPluBean.setIdLocal(xIdLocalUsuario);
                    fachadaPluBean.setIdBodega(xIdOperacion);

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
                    return "/jsp/vtaFrmConInventarioReferencia.jsp";

                }
            }
        }

        return "/jsp/vtaContenedorInventarioReferencia.jsp";

    }
}
