package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el NombreRubroBean
import com.solucionesweb.losbeans.negocio.MarcaBean;

// Importa la clase que contiene el FachadaLineaBean
import com.solucionesweb.losbeans.fachada.FachadaMarcaBean;
/**
 * Esta opción permite consultar información sobre las Marcas creadas previamente, 
 * así como crear nuevas Marcas y modificarlas. /
 * vtaContenedorCatalogoMarca.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */


public class VtasAdmCatalogoMarcaManejadorRequest
                                               implements GralManejadorRequest {
     /**
     * BUTTON--
     * ("Ingresar")-Permite ingresa una nueva marca/
     * ("Listar")-Permite ver un listado de marcas registradas/vtaFrmSelCatalogoMarca.jsp/
     * ("Modificar")-Permite cambiar las marcas registradas/vtaFrmModCatalogoMarca.jsp/
     * ("Regresar")-("Salir")-Permite retornar al menu principal/
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmCatalogoMarcaManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "NombreMarca"-nombre de la marca que se desea ingresar como nueva o buscar/
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
            if (accionContenedor.compareTo("Listar") == 0) {

                String xNombreMarca = request.getParameter("xNombreMarca");


                FachadaMarcaBean fachadaMarcaBean = new FachadaMarcaBean();

                fachadaMarcaBean.setNombreMarca("'%"
                        + xNombreMarca.trim().toUpperCase() + "%'");

                request.setAttribute("fachadaMarcaBean", fachadaMarcaBean);
                return "/jsp/vtaFrmSelCatalogoMarca.jsp";
            }

            // Seleccionar
            if (accionContenedor.compareTo("Seleccionar") == 0) {


                String xIdMarca = request.getParameter("xIdMarca");

                // Valida idLinea
                if (xIdMarca != null) {

                    //
                    MarcaBean marcaBean = new MarcaBean();

                    FachadaMarcaBean fachadaMarcaBean = new FachadaMarcaBean();

                    //
                    marcaBean.setIdMarca(xIdMarca);

                    //
                    fachadaMarcaBean = marcaBean.listaMarcaFCH();

                    //
                    request.setAttribute("fachadaMarcaBean", fachadaMarcaBean);

                    //
                    return "/jsp/vtaFrmModCatalogoMarca.jsp";

                }
            }

            // Modificar
            if (accionContenedor.compareTo("Modificar") == 0) {

                //
                String xNombreMarca = request.getParameter("xNombreMarca");
                String xIdMarca = request.getParameter("xIdMarca");
                int estado = 1;

                // Valida idLinea
                if (xIdMarca != null) {

                    // validacion
                    Validacion validacion = new Validacion();
                    validacion.reasignar("nombreMarca", xNombreMarca.trim());

                    validacion.validarCampoString();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    MarcaBean marcaBean = new MarcaBean();

                    //
                    int xMaxIdSeq = marcaBean.listaIdSeqMaxima() + 1;

                    //
                    marcaBean.setIdMarca(xIdMarca);
                    marcaBean.setNombreMarca(xNombreMarca);
                    marcaBean.setEstado(estado);
                    marcaBean.setIdSeq(xMaxIdSeq);

                    //
                    boolean xOkActualizo = marcaBean.actualizaMarca();

                }
            }

            // Ingresar
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                String xNombreMarca = request.getParameter("xNombreMarca");
                int estado = 1;

                // validacion
                Validacion validacion = new Validacion();
                validacion.reasignar("nombreMarca", xNombreMarca);

                validacion.validarCampoString();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                MarcaBean marcaBean = new MarcaBean();

                // Maxima idLinea y idSeq
                int idMarcaMaxima = marcaBean.listaIdMarcaMaxima() + 1;

                //
                int xMaxIdSeq = marcaBean.listaIdSeqMaxima() + 1;

                //
                marcaBean.setIdMarca(idMarcaMaxima);
                marcaBean.setNombreMarca(xNombreMarca);
                marcaBean.setEstado(estado);
                marcaBean.setIdSeq(xMaxIdSeq);

                //
                boolean xOkIngreso = marcaBean.ingresa();

            }
        }

        return "/jsp/vtaContenedorCatalogoMarca.jsp";

    }
}
