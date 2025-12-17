package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBodega;

// Importa la clase que contiene el LocalBodegaBean
import com.solucionesweb.losbeans.negocio.LocalBodegaBean;
/**
 * En el que se muestra las O.T.(ordenes de trabajo) por operación externo,
 * referencia cliente, operario a quien se le remitió el pedido,Inventario Peso (Kg) e inventario Cantidad (UN). /
 * vtaContenedorRepInventarioOTExterno.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepInventarioOTExternoManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte de inventario /
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmRepInventarioOTExternoManejadorRequest() {
    }
     /**
     * BUTTON PARAMETER--
     * "Operacion"- Selecciona una operacion/
     * "Destinacion"-selecciona forma de ver el reporte excel-pantalla /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoOrdenPedido = 59;

        //
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
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xIdDestinacion = request.getParameter("idDestinacion");
                String xIdBodega = request.getParameter("xIdOperacion");

                //
                int xIdTipo = 1;

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
                request.setAttribute("fachadaLocalBodega", fachadaLocalBodega);

                //
                String xTituloReporte = "INVENTARIO "
                        + " - BODEGA "
                        + fachadaLocalBodega.getNombreBodega()
                        + "("
                        + fachadaLocalBodega.getIdBodegaStr()
                        + ")";

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenPedido);
                fachadaDctoOrdenDetalleBean.setIdOperacion(xIdBodega);

                //
                request.setAttribute("fachadaDctoOrdenDetalleBean", fachadaDctoOrdenDetalleBean);

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstInventarioOTExternoArchivo.jsp";

                }

                //
                return "/jsp/vtaFrmLstInventarioOTExterno.jsp";

                /*
                String xSubtitulo = "";

                //
                if (fachadaLocalBodega.getIdBodega() > 0) {

                //
                xSubtitulo = fachadaLocalBodega.getNombreBodega();


                } else {

                xSubtitulo = " GENERAL ";

                }*/

                /*
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
                generaPDFInventarioCategoria.generaPdf(request, response);*/

            }
        }

        return "/jsp/empty.htm";

    }
}
