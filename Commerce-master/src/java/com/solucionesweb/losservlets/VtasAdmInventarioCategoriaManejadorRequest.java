package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el CategoriaBean
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaCategoriaBean
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBodega;

// Importa la clase que contiene el LocalBodegaBean
import com.solucionesweb.losbeans.negocio.LocalBodegaBean;
/**
 * El sistema muestra la siguiente interfaz, en donde selecciona las categorías que desea consultar/
 * vtaContenedorInventarioCategoria.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmInventarioCategoriaManejadorRequest
                                               implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Salir")-Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmInventarioCategoriaManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Destinacion"-Seleccione como ver el reporte(excel/pantalla) /
     * "Bodega"-Selecciona la bodega /
     * "Categoria"-Selecciona la categoria /
     * "Estado"- Selecciona el estado de la categoria(Todas-activo-inactivo) /
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
                String xIdDestinacion = request.getParameter("xIdDestinacion");
                String xIdBodega = request.getParameter("xIdBodega");

                //
                int xIdTipo = 1;

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
                categoriaBean.setIdCategoria(xIdCategoria);
                categoriaBean.setIdLinea(xIdLinea);


                //
                FachadaCategoriaBean fachadaCategoriaBean = new FachadaCategoriaBean();

                fachadaCategoriaBean = categoriaBean.listaUnaCategoriaFCH();

                //
                fachadaCategoriaBean.setIdLocal(xIdLocalUsuario);
                fachadaCategoriaBean.setIdBodega(xIdBodega);

                //
                FachadaLocalBodega fachadaLocalBodega = new FachadaLocalBodega();

                //
                LocalBodegaBean localBodegaBean = new LocalBodegaBean();

                //
                localBodegaBean.setIdLocal(xIdLocalUsuario);
                localBodegaBean.setIdBodega(xIdBodega);

                //
                fachadaLocalBodega = localBodegaBean.listaUnFCH();

                //
                String xTituloReporte = "INVENTARIO "
                        + " - BODEGA "
                        + fachadaLocalBodega.getNombreBodega()
                        + "("
                        + fachadaLocalBodega.getIdBodegaStr()
                        + ")";

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    request.setAttribute("fachadaCategoriaBean", fachadaCategoriaBean);

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstInventarioCategoria.jsp";

                }

                //
                String xSubtitulo = "";

                //
                if (fachadaCategoriaBean.getIdCategoria() > 0) {

                    //
                    xSubtitulo = fachadaCategoriaBean.getNombreCategoria();


                } else {

                    xSubtitulo = " GENERAL ";

                }

                //
                GeneraPDFInventarioCategoria generaPDFInventarioCategoria
                                           = new GeneraPDFInventarioCategoria();

                //
                generaPDFInventarioCategoria.setIdLocal(xIdLocalUsuario);
                generaPDFInventarioCategoria.setIdLinea(xIdLinea);
                generaPDFInventarioCategoria.setIdCategoria(xIdCategoria);
                generaPDFInventarioCategoria.setIdTipo(xIdTipo);
                generaPDFInventarioCategoria.setTituloReporte(xTituloReporte + " "
                        + xSubtitulo);
                generaPDFInventarioCategoria.setIdBodega(xIdBodega);

                //
                generaPDFInventarioCategoria.generaPdf(request, response);

            }
        }

        return "/jsp/empty.htm";

    }
}
