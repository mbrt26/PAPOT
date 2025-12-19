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
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa la clase que contiene el PluInventarioBean
import com.solucionesweb.losbeans.negocio.LocalBean;
import com.solucionesweb.losbeans.negocio.PluInventarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.util.Vector;

/**
 * Esta opción permite consultar información sobre las referencias creadas
 * previamente, así como crear nuevas referencias y modificarlas. /
 * vtaContenedorCatalogoReferencia.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmCatalogoReferenciaManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON-- ("Traer")-Permite ver una referencia especifica /
     * ("Ingresar")-Permite ingresar referencias nuevas/ ("Guardar")-Permite
     * guardar una referencia nueva / ("Consultar")-Permite ver el listado de
     * todas las referencias / ("Regresar")-("Salir")-Permite retornar al menu
     * principal / ("Modificar")-Permite actualizar o cambiar referencias /
     *
     * Metodo contructor por defecto, es decir, sin parametros
     */
    public VtasAdmCatalogoReferenciaManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER-- "Categoria"-selecciona categoria / "Marca"-Selecciona
     * marca / "Referencia"-Ingrese referencia / "Destinacion"-Seleccione si se
     * desea ver en excel o pantalla / "Estado"- estado de
     * referencia(activo/inactivo)/ "Descripcion"-Ingreso descripcion de
     * referencia / "Lista#1"-Ingreso de percios de lista 1 / "Lista#2"-Ingreso
     * de percios de lista 2 / "Lista#3"-Ingreso de percios de lista 3 /
     * "%Iva"-Ingreso porcentaje de iva / "Tipo"- ingrese tipo/
     * "Vr.ImpoConsumo"-ingrese valor impoconsumo / "Und.Despacho"-ingreso
     * unidades de despacho / "Factor densidad"-ingreso densidad producto /
     * "Marca"-Seleccioen marca/
     *
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        cargarProveedores(request);

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
            UsuarioBean usuarioBean
                    = (UsuarioBean) sesion.getAttribute("usuarioBean");
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
                return "/jsp/vtaFrmSelCatalogoReferencia.jsp";
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
                    return "/jsp/vtaFrmModCatalogoReferencia.jsp";

                }
            }

            // Modificar
            if (accionContenedor.compareTo("Modificar") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");
                String xNombrePlu = request.getParameter("xNombrePlu");
                String xVrGeneral = request.getParameter("xVrGeneral");
                String xVrMayorista = request.getParameter("xVrMayorista");
                String xVrSucursal = request.getParameter("xVrSucursal");
                String xPorcentajeIva = request.getParameter("xPorcentajeIva");
                String xIdTipo = request.getParameter("xIdTipo");
                String xIdMarca = request.getParameter("xIdMarca");
                String xVrCosto = request.getParameter("xVrCosto");
                String xVrCostoIND = request.getParameter("xVrCostoIND");
                String xVrImpoconsumo = request.getParameter("xVrImpoconsumo");
                String xFactorDespacho = request.getParameter("xFactorDespacho");
                String xFactorDensidad = request.getParameter("xFactorDensidad");
                String xFactor = request.getParameter("xFactor");
                String xReferenciaMaterial = request.getParameter("xReferencia");
                String xSkProveedorPpal = request.getParameter("listaProveedores");
                String xDolarizado = request.getParameter("xDolarizado");
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
                    validacion.reasignar("nombrePlu", xNombrePlu.trim());

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
                    validacion.reasignar("TIPO", xIdTipo);

                    validacion.validarCampoRangoTipo();

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
                    pluBean.setReferencia(xReferenciaMaterial);
                    pluBean.setSk_proveedor(Double.parseDouble(xSkProveedorPpal));
                    pluBean.setFactor(Double.parseDouble(xFactor));
                    pluBean.setDolarizado(Integer.parseInt(xDolarizado));
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
                    return "/jsp/vtaFrmSelCatalogoReferencia.jsp";

                }
            }

            // Ingresar
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setNombrePlu("");
                fachadaPluBean.setReferencia("");
                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmIngCatalogoReferencia.jsp";

            }

            // Modificar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");
                String xNombrePlu = request.getParameter("xNombrePlu");
                String xVrGeneral = request.getParameter("xVrGeneral");
                String xVrMayorista = request.getParameter("xVrMayorista");
                String xVrSucursal = request.getParameter("xVrSucursal");
                String xPorcentajeIva = request.getParameter("xPorcentajeIva");
                String xIdTipo = request.getParameter("xIdTipo");
                String xIdMarca = request.getParameter("xIdMarca");
                int estado = 1;
                String xVrImpoconsumo = request.getParameter("xVrImpoconsumo");
                String xFactorDespacho = request.getParameter("xFactorDespacho");
                String xFactorDensidad = request.getParameter("xFactorDensidad");
                String xReferencia = request.getParameter("xReferencia");
                String xSkProveedor = request.getParameter("listaProveedores");
                String xFactor = request.getParameter("xFactor");
                String xDolarizado = request.getParameter("xDolarizado");

                //
                int indiceSeparador = xIdLineaCategoria.indexOf("~");
                int Longitud = xIdLineaCategoria.length();

                //
                String xIdLinea = xIdLineaCategoria.substring(0,
                        indiceSeparador);
                String xIdCategoria = xIdLineaCategoria.substring(
                        indiceSeparador + 1, Longitud);

                // Valida idLinea
                if (xNombrePlu != null) {

                    // validacion
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("nombrePlu", xNombrePlu.trim());

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
                    validacion.reasignar("Tipo", xIdTipo);

                    validacion.validarCampoRangoTipo();

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
                    validacion.reasignar("FACTOR ", xFactor);

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
                    pluBean.setSk_proveedor(Float.parseFloat(xSkProveedor));
                    pluBean.setFactor(Double.parseDouble(xFactor));
                    pluBean.setDolarizado(Integer.parseInt(xDolarizado));

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
                    return "/jsp/vtaFrmIngCatalogoReferencia.jsp";
                }
            }
        }

        return "/jsp/vtaContenedorCatalogoReferencia.jsp";

    }

    private void cargarProveedores(HttpServletRequest request) {
        ColaboraTercero ct = new ColaboraTercero();
        ct.setIdTipoTercero(2);
        ct.setNombreTercero("%%");
        Vector listaProveedores = ct.listaAllNombre();
        request.setAttribute("listaProveedores", listaProveedores);
    }
}
