package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el CategoriaBean
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa la clase que contiene el FachadaCategoriaBean
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.LocalIpBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.tags.ListaPrecioListaTag;

/**
 * Esta ventana es para imprimir etiquetas para los productos /
 * vtaContenedorEanReferencia.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmEanReferenciaManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON--
     * ("Consultar")-Permite ver un producto/
     * ("Salir")-Retorna al menu principal /
     * ("Generar")-Genera un pdf del producto para imprimir /
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmEanReferenciaManejadorRequest() {
    }
/**
     * BUTTON PARAMETER--
     * "Nombre/Plu"-Ingrese nombre o producto/
     * "Fecha Final"-Fecha limite para ver un reporte /
     * "Destinacion"-selecciona forma de ver el reporte (Excel/pantalla) /
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

            //
            HttpSession sesion = request.getSession();
            UsuarioBean usuarioBean =
                    (UsuarioBean) sesion.getAttribute("usuarioBean");
            String idUsuario = usuarioBean.getIdUsuarioStr();
            int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // listaCategoria
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
                fachadaCategoriaBean.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaCategoriaBean", fachadaCategoriaBean);

                //
                return "/jsp/vtaFrmSelUtilPrecioVenta.jsp";

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                String idLinea = request.getParameter("idLinea");


                //
                String strCadena = idLinea.trim();
                int lonCadena = strCadena.length();
                int posCadena = strCadena.indexOf('+', 0);
                String xNombrePlu = "";

                //
                if (posCadena > 0) {

                    //
                    idLinea = strCadena.substring(0, posCadena).trim();
                    xNombrePlu =
                            strCadena.substring(posCadena + 1, lonCadena).trim();
                } else {

                    //
                    FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean =
                            new FachadaColaboraHistoriaBean();

                    //
                    fachadaColaboraHistoriaBean.setIdLinea(idLinea);
                    fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

                    fachadaColaboraHistoriaBean.setIdLocal(xIdLocalUsuario);

                   


                    //
                    request.setAttribute("fachadaColaboraHistoriaBean",
                            fachadaColaboraHistoriaBean);
                    return "/jsp/vtaFrmLstUtilEtiqueta.jsp";



                }

            }

            // Traer
            if (accionContenedor.compareTo("Traer") == 0) {

/*                //
               // String xImpresion = request.getParameter("impresion");
                String xReferencia = request.getParameter("chkIdReferencia");

                Validacion validacion = new Validacion();

                //
                GeneraPdfEtiqueta generaPdfEtiqueta = new GeneraPdfEtiqueta();

                //
                generaPdfEtiqueta.setTituloReporte("REPORTE ETIQUETAS");

                generaPdfEtiqueta.setIndicadorINI(1);
                generaPdfEtiqueta.setIndicadorFIN(2);
                generaPdfEtiqueta.setIdPlu(Integer.parseInt(xReferencia));
//                generaPdfEtiqueta.setImpresion(Integer.parseInt(xImpresion));

                //
                generaPdfEtiqueta.setNombreReporte("Barcode");

                //
                generaPdfEtiqueta.generaPdf(request, response);
*/
                  //
                String xIdPlu = request.getParameter("xIdPlu");

                //
                xIdPlu = xIdPlu.trim();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(xIdPlu);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                
                //
                return "/jsp/vtaFrmConUtilEtiquetaVenta.jsp";

            }

             // Traer
            if (accionContenedor.compareTo("Generar") == 0) {

                //
               String xColumna = request.getParameter("Columna");
               // String xReferencia = request.getParameter("chkIdReferencia");

                String xIdPlu = request.getParameter("xIdPlu");

                //
                xIdPlu = xIdPlu.trim();

                

                Validacion validacion = new Validacion();

                //
                GeneraPdfEtiqueta generaPdfEtiqueta = new GeneraPdfEtiqueta();

                //
                generaPdfEtiqueta.setTituloReporte("REPORTE ETIQUETAS");

                generaPdfEtiqueta.setIndicadorINI(1);
                generaPdfEtiqueta.setIndicadorFIN(2);
                generaPdfEtiqueta.setIdPlu(Integer.parseInt(xIdPlu));
                generaPdfEtiqueta.setColumna(Integer.parseInt(xColumna));

                //
                generaPdfEtiqueta.setNombreReporte("VtasRepEtiquetaColum");

                //
                generaPdfEtiqueta.generaPdf(request, response);
            }
        }
        return "/jsp/vtaContenedorUtilPrecioVenta.jsp";
    }
}
