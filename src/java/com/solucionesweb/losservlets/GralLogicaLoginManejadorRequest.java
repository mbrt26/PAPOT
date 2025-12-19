package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Importa la clase que contiene el Bean de Validacion de Campos ValidacionUsuarioBean
import com.solucionesweb.losbeans.utilidades.ValidacionUsuarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.LocalBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

// Este servlet implementa la interface GralManejadorRequest
public class GralLogicaLoginManejadorRequest implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public GralLogicaLoginManejadorRequest() {
    }

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Captura los parametros suministrados por el usuario
        String idUsuario = request.getParameter("idUsuario");
        String clave = request.getParameter("clave");

        //
        if (idUsuario == null) {

            return "/jsp/gralFrmLogin.jsp";

        }

        //
        if (clave == null) {

            return "/jsp/gralFrmLogin.jsp";

        }

        // Instancia el Bean de Validacion para validar los campos
        ValidacionUsuarioBean campoAValidar
                = new ValidacionUsuarioBean("idUsuario", idUsuario);

        // Valida el idUsuario
        campoAValidar.validarCampoEntero();

        if (campoAValidar.isValido() == false) {

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("validacionUsuarioBean", campoAValidar);

            return "/jsp/gralErrUsuario.jsp";
        }

        // Reusa el bean reseteando los valores de sus propiedades
        campoAValidar.reasignar("clave", clave);

        //
        campoAValidar.validarCampoString();

        if (campoAValidar.isValido() == false) {
            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("validacionUsuarioBean", campoAValidar);

            return "/jsp/gralErrUsuario.jsp";
        }

        // Valida el UsuarioBean con parametros.
        int estadoVigente = 1;  // Usuario vigente

        //
        UsuarioBean usuarioBean = new UsuarioBean();

        //
        usuarioBean.setIdUsuario(idUsuario);
        usuarioBean.setClave(clave);
        usuarioBean.setEstado(estadoVigente);

        // Efectua la busqueda del usuario en la base de datos
        usuarioBean.vigenciaUsuario();

        //
        FachadaUsuarioBean fachadaUsuarioBean
                = new FachadaUsuarioBean();

        //
        UsuarioRutaBean usuarioRutaBean = new UsuarioRutaBean();

        //
        usuarioRutaBean.setIdUsuario(idUsuario);

        //
        boolean okRutaVendedor = usuarioRutaBean.validaUsuarioRuta();

        // Perfiles que requieren ruta (vendedores)
        int idNivelVendedorAlmacen = 1;
        int idNivelVendedorMovil = 10;

        // Obtener el nivel del usuario para determinar si requiere ruta
        FachadaUsuarioBean fachadaTemp = usuarioBean.listaUsuario();
        int nivelUsuario = fachadaTemp.getIdNivel();

        // La ruta solo es requerida para vendedores (niveles 1 y 10)
        boolean requiereRuta = (nivelUsuario == idNivelVendedorAlmacen) ||
                               (nivelUsuario == idNivelVendedorMovil) ||
                               (nivelUsuario == idNivelVendedorAlmacen + 50) ||
                               (nivelUsuario == idNivelVendedorMovil + 50);

        // Usuario vigente + (Ruta definida si es vendedor, o no requiere ruta)
        if (usuarioBean.isVigente() && (!requiereRuta || okRutaVendedor)) {

            //
            fachadaUsuarioBean = usuarioRutaBean.listaFCH();

            //
            String idRuta = usuarioRutaBean.listaRutaUsuario();

            //
            String strIdListaNula = "001";

            //
            usuarioBean.setStrIdLista(strIdListaNula);
            usuarioBean.setIdRuta(idRuta);
            usuarioBean.setIndicadorInicial(
                    fachadaUsuarioBean.getIndicadorInicial());
            usuarioBean.setIndicadorFinal(
                    fachadaUsuarioBean.getIndicadorFinal());

            //
            fachadaUsuarioBean = usuarioBean.listaUsuario();

            //
            int xIdLocal = fachadaUsuarioBean.getIdLocalUsuario();
            int xIdNivel = fachadaUsuarioBean.getIdNivel();

            //
            usuarioBean.setIdLocalUsuario(xIdLocal);
            usuarioBean.setIdNivel(xIdNivel);

            //
            LocalBean localBean = new LocalBean();

            //
            localBean.setIdLocal(xIdLocal);

            //
            FachadaLocalBean fachadaLocalBean
                    = new FachadaLocalBean();

            fachadaLocalBean = localBean.listaUnLocal();

            // Obtiene la session actual
            HttpSession session = request.getSession(true);

            session.setAttribute("inventarioVisible",
                    fachadaLocalBean.getInventarioVisible());
            session.setAttribute("usuarioBean", usuarioBean);
            session.setAttribute("idLocal", xIdLocal);
           
          
//             Perfil Usuario
            int idNivelVendedor = 1;    // vend almcn
            int idNivelAdministradorAlmacen = 2;    // adm almac
            int idNivelAdministradorGeneral = 3;    // adm general
            int idNivelFinanciero = 4;    // adm financiero
            int idNivelEmpresa = 5;    // gerente
            int idNivelCordinador = 6;    // coordinador
            int idNivelTesorero = 7;    // tesorero
            // idNivelVendedorMovil ya definido arriba
            int idNivelAdministradorConsulta = 12;  // Consulta General
            int idNivelContador = 13;   // contador
            int idNivelAdministradorAlmacenConsulta = 14;   //Consulta administrador
            int idNivelAuditor = 11;   // auditor
            int idNivelOperario = 20;    // operario

        // Perfil Vendedor
        if ((fachadaUsuarioBean.getIdNivel() == idNivelVendedor) ||
           (fachadaUsuarioBean.getIdNivel()  == idNivelVendedor + 50)) {
            return "/jsp/mnuContenedorVendedor.jsp";
        }

        // Perfil Administrador Comercial UnCO
        if ((fachadaUsuarioBean.getIdNivel() == idNivelAdministradorAlmacen) ||
           (fachadaUsuarioBean.getIdNivel()  == idNivelAdministradorAlmacen + 50)) {

    	   return "/jsp/mnuContenedorAdministradorAlmacen.jsp";
           
        }

        // Perfil Administrador Comercial
        if (fachadaUsuarioBean.getIdNivel() == idNivelAdministradorGeneral ) {
    	   return "/jsp/mnuContenedorAdministradorGeneral.jsp";
        }

        // Perfil Administrador Comercial
        if (fachadaUsuarioBean.getIdNivel() == idNivelCordinador ) {
    	   return "/jsp/mnuContenedorCordinador.jsp";
        }

        // Perfil Administrador Financiero
        if (fachadaUsuarioBean.getIdNivel() == idNivelFinanciero ) {
    	   return "/jsp/mnuControlPedidoFinanciero.jsp";
        }

        // Perfil Administrador Empresa
        if (fachadaUsuarioBean.getIdNivel() == idNivelEmpresa ) {
    	   return "/jsp/mnuContenedor.jsp";
        }

        // Perfil Tesorero
        if (fachadaUsuarioBean.getIdNivel() == idNivelTesorero ) {
    	   return "/jsp/mnuContenedorTesorero.jsp";
        }

         // Perfil Contador
        if (fachadaUsuarioBean.getIdNivel() == idNivelContador ) {
    	   return "/jsp/mnuContenedorContador.jsp";
        }

         // Perfil Auditor
        if (fachadaUsuarioBean.getIdNivel() == idNivelAuditor ) {
    	   return "/jsp/mnuContenedorAuditor.jsp";
        }

        // Perfil Administrador Vendedor Movil
        if (fachadaUsuarioBean.getIdNivel() == idNivelVendedorMovil ) {
    	   return "/jsp/mnuContenedorVendedorMovil.jsp";
        }

         // Perfil Administrador Consulta
        if (fachadaUsuarioBean.getIdNivel() == idNivelAdministradorConsulta ) {
    	   return "/jsp/mnuContenedorAdministradorConsulta.jsp";
        }

          // Perfil Administrador Consulta
        if (fachadaUsuarioBean.getIdNivel() == idNivelAdministradorAlmacenConsulta ) {
    	   return "/jsp/mnuContenedorAdministradorAlmacenConsulta.jsp";
        }

          // Perfil Administrador Consulta
        if (fachadaUsuarioBean.getIdNivel() == idNivelOperario ) {
    	   return "GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTProductoTouch.ctr";
        }

//             Temporalmente devuelve el welcome
	return "/jsp/gralFrmLogin.jsp";
        } else {

            // Aqui reusa el Bean de Validacion para manejar el error
            campoAValidar.asignarError("idUsuario", usuarioBean.getIdUsuarioStr(),
                    "UsuarioNoVigente",
                    "El Usuario no esta vigente o "
                    + "no se encuentra registrado",
                    "Revise su IdUsuario y password o "
                    + "comuniquese con el proveedor del Sistema.");

            // Escribe el Bean de Validacion en el Request
            request.setAttribute("validacionUsuarioBean", campoAValidar);

            return "/jsp/gralErrUsuario.jsp";

        }

    }

}
