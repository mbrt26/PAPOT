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

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.negocio.PluBean;

// Importa la clase que contiene el CategoriaBean
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa la clase que contiene el FachadaCategoriaBean
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa la clase que contiene el PluInventarioBean
import com.solucionesweb.losbeans.negocio.LocalBean;
import com.solucionesweb.losbeans.negocio.PluInventarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
/**
 *Esta opción permite consultar información sobre los materiales creados previamente,
 * así como crear nuevos y modificarlas. /
 * vtaContenedorCatalogoMaterial.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmCatalogoMaterialManejadorRequest
        implements GralManejadorRequest {
    /**
    * BUTTON--
    * ("Traer")-Permite ver un material especifico /
    * ("Ingresar")-Permite ingresar materiales nuevos/
    * ("Guardar")-Permite guardar una referencia nueva /
    * ("Consultar")-Permite ver el listado de todas las materiales /
    * ("Regresar")-("Salir")-Permite retornar al menu principal /
    * ("Modificar")-Permite actualizar o cambiar materiales /
    * 
    * Metodo contructor por defecto, es decir, sin parametros /
    */
    
    public VtasAdmCatalogoMaterialManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Categoria"-selecciona categoria /
     * "Marca"-Selecciona marca /
     * "Referencia"-Ingrese referencia /
     * "Destinacion"-Seleccione si se desea ver en excel o pantalla /
     * "Descripcion"-Ingreso descripcion del material /
     * "Descripcion refrencia"-Ingreso descripcion de referencia /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp). /
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

            //
            HttpSession sesion = request.getSession();
            UsuarioBean usuarioBean =
                    (UsuarioBean) sesion.getAttribute("usuarioBean");
            String idUsuario = usuarioBean.getIdUsuarioStr();
            int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

            // Listar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");
                String xIdDestinacion = request.getParameter("idDestinacion");
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

                FachadaLocalBean fachadaLocalBean = new FachadaLocalBean();

                LocalBean localBean = new LocalBean();

                localBean.setIdLocal(xIdLocalUsuario);

                fachadaLocalBean = localBean.listaUnLocal();

                fachadaCategoriaBean.setNombreLocal(fachadaLocalBean.getNombreLocal());


                //
                if ((fachadaCategoriaBean.getIdLinea() == 0)
                        && (fachadaCategoriaBean.getIdCategoria() == 0)) {

                    //
                    fachadaCategoriaBean.setNombreCategoria("CATEGORIAS GENERAL");

                }

                //
                request.setAttribute("fachadaCategoriaBean", fachadaCategoriaBean);

                //if (detinacion=HTM)

                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmSelCatalogoReferenciaArchivo.jsp";

                }

                //
                return "/jsp/vtaFrmSelCatalogoMaterial.jsp";
                //ELSE EXCEL



            }

            // Traer
            if (accionContenedor.compareTo("Traer") == 0) {

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
                    return "/jsp/vtaFrmModCatalogoMaterial.jsp";

                }
            }

            // Modificar
            if (accionContenedor.compareTo("Modificar") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");
                String xNombrePlu = request.getParameter("xNombrePlu");
                String xReferencia = request.getParameter("xReferencia");


                String xVrGeneral = "1";
                String xVrMayorista = "1";
                String xVrSucursal = "1";
                String xPorcentajeIva = "16";
                String xIdTipo = "4";
                String xIdMarca = "1";
                int estado = 1;
                String xVrImpoconsumo = "0";
                String xFactorDespacho = "1";
                String xFactorDensidad = "0";
                String xVrCosto = "1";
                String xVrCostoIND= "1";

                //
                int indiceSeparador = xIdLineaCategoria.indexOf("~");
                int Longitud = xIdLineaCategoria.length();
                String xIdLinea = xIdLineaCategoria.substring(0,
                        indiceSeparador);
                String xIdCategoria = xIdLineaCategoria.substring(
                        indiceSeparador + 1, Longitud);
                //
                int xEstadoOk = 1;

                // Valida idLinea
                if (xNombrePlu != null) {

                    // validacion
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("DESCRIPCION REFERENCIA", xNombrePlu.trim());

                    validacion.validarCampoString();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }
                    
                    //
                    validacion.reasignar("REFERENCIA", xReferencia.trim());

                    validacion.validarCampoString();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("Lista#1", xVrGeneral);

                    //
                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("Lista#2", xVrMayorista);

                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("Lista#3", xVrSucursal);

                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("PorcentajeIva", xPorcentajeIva);

                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("VR.COSTO", xVrCosto);

                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("VR.COSTO IND.", xVrCostoIND);

                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("VR.IMPOCONSUMO", xVrImpoconsumo);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("UNIDAD DESPACHO", xFactorDespacho);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("FACTOR DENSIDAD", xFactorDensidad);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    PluBean pluBean = new PluBean();

                    //
                    int xMaxIdSeq = pluBean.listaIdSeqMaxima() + 1;

                    //
                    pluBean.setIdPlu(xIdPlu);
                    pluBean.setNombrePlu(xNombrePlu);
                    pluBean.setVrGeneral(xVrGeneral);
                    pluBean.setVrMayorista(xVrMayorista);
                    pluBean.setVrSucursal(xVrSucursal);
                    pluBean.setPorcentajeIva(xPorcentajeIva);
                    pluBean.setIdTipo(xIdTipo);
                    pluBean.setIdMarca(xIdMarca);
                    pluBean.setIdLinea(xIdLinea);
                    pluBean.setIdCategoria(xIdCategoria);
                    pluBean.setVrCosto(xVrCosto);
                    pluBean.setVrCostoIND(xVrCostoIND);
                    pluBean.setIdSeq(xMaxIdSeq);
                    pluBean.setVrImpoconsumo(xVrImpoconsumo);
                    pluBean.setFactorDespacho(xFactorDespacho);
                    pluBean.setFactorDensidad(xFactorDensidad);
                    pluBean.setReferencia(xReferencia.trim());

                    //
                    boolean xOkActualizo = pluBean.actualizaAll();

                    //
                    FachadaCategoriaBean fachadaCategoriaBean = new FachadaCategoriaBean();

                    //
                    CategoriaBean categoriaBean = new CategoriaBean();

                    //
                    categoriaBean.setIdLinea(xIdLinea);
                    categoriaBean.setIdCategoria(xIdCategoria);

                    //
                    fachadaCategoriaBean = categoriaBean.listaUnaCategoriaFCH();

                    //
                    request.setAttribute("fachadaCategoriaBean", fachadaCategoriaBean);

                    //
                    return "/jsp/vtaFrmSelCatalogoMaterial.jsp";

                }
            }

            // Ingresar
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setNombrePlu("");

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmIngCatalogoMaterial.jsp";

            }

            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");
                String xNombrePlu = request.getParameter("xNombrePlu");
                String xVrGeneral = "1";
                String xVrMayorista = "1";
                String xVrSucursal = "1";
                String xPorcentajeIva = "16";
                String xIdTipo = "4";
                String xIdMarca = "1";
                int estado = 1;
                String xVrImpoconsumo = "0";
                String xFactorDespacho = "1";
                String xFactorDensidad = "0";
                String xReferencia = request.getParameter("xReferencia");

                //
                int indiceSeparador = xIdLineaCategoria.indexOf("~");
                int Longitud = xIdLineaCategoria.length();
                String xIdLinea = xIdLineaCategoria.substring(0,
                        indiceSeparador);
                String xIdCategoria = xIdLineaCategoria.substring(
                        indiceSeparador + 1, Longitud);

                // Valida idLinea
                if (xNombrePlu != null) {

                    // validacion
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("DESCRIPCION REFERENCIA", xNombrePlu.trim());

                    validacion.validarCampoString();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("REFERENCIA", xReferencia.trim());

                    validacion.validarCampoString();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("Lista#1", xVrGeneral);

                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("Lista#2", xVrMayorista);

                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("Lista#3", xVrSucursal);

                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("PorcentajeIva", xPorcentajeIva);

                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("VR.IMPOCONSUMO", xVrImpoconsumo);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("UNIDAD DESPACHO", xFactorDespacho);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("FACTOR DENSIDAD", xFactorDensidad);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    PluBean pluBean = new PluBean();

                    //
                    int xIdMaxIdPlu = pluBean.maximoIdPlu() + 1;

                    //
                    int xMaxIdSeq = pluBean.listaIdSeqMaxima() + 1;

                    //
                    pluBean.setIdPlu(xIdMaxIdPlu);
                    pluBean.setNombrePlu(xNombrePlu);
                    pluBean.setVrGeneral(xVrGeneral);
                    pluBean.setVrMayorista(xVrMayorista);
                    pluBean.setVrSucursal(xVrSucursal);
                    pluBean.setPorcentajeIva(xPorcentajeIva);
                    pluBean.setIdTipo(xIdTipo);
                    pluBean.setIdMarca(xIdMarca);
                    pluBean.setIdLinea(xIdLinea);
                    pluBean.setIdCategoria(xIdCategoria);
                    pluBean.setIdSeq(xMaxIdSeq);
                    pluBean.setFactorDespacho(xFactorDespacho);
                    pluBean.setVrImpoconsumo(xVrImpoconsumo);
                    pluBean.setFactorDensidad(xFactorDensidad);
                    pluBean.setReferencia(xReferencia);

                    //
                    boolean xOkIngresa = pluBean.ingresa();

                    //
                    PluInventarioBean pluInventarioBean = new PluInventarioBean();

                    //
                    pluInventarioBean.ingresa();

                    //
                    FachadaPluBean fachadaPluBean = new FachadaPluBean();

                    //
                    request.setAttribute("fachadaPluBean", fachadaPluBean);

                    //
                    return "/jsp/vtaFrmIngCatalogoMaterial.jsp";
                }
            }
        }

        return "/jsp/vtaContenedorCatalogoMaterial.jsp";

    }
}
