package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.fachada.FachadaJobEscalaDetalle;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario;
import com.solucionesweb.losbeans.negocio.JobEscalaDetalleBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasAdmCatalogoPotMaquinaManejadorRequest implements GralManejadorRequest {

    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        int xIdPeriodo = 201203;
        int xEstadoActivo = 1;
        if (accionContenedor != null) {
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }
            HttpSession sesion = request.getSession();

            UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

            String idUsuario = usuarioBean.getIdUsuarioStr();
            int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
            if (accionContenedor.compareTo("Modificar") == 0) {
                String xEstado = request.getParameter("xEstado");
                String xIdEscala = request.getParameter("xIdEscala");
                String xNombreItem = request.getParameter("xNombreMaquina");
                String xItem = request.getParameter("xItem");

                Validacion campoAValidar = new Validacion();

                campoAValidar.reasignar("NOMBRE MAQUINA", xNombreItem);

                campoAValidar.validarCampoString();
                if (!campoAValidar.isValido()) {
                    request.setAttribute("validacion", campoAValidar);

                    return "/jsp/gralError.jsp";
                }
                campoAValidar.reasignar("ESTADO FUERA DEL RANGO ( 1 o 2 )", xEstado);

                campoAValidar.validarCampoRangoNumerico_1_2();
                if (!campoAValidar.isValido()) {
                    request.setAttribute("validacion", campoAValidar);

                    return "/jsp/gralError.jsp";
                }
                JobEscalaDetalleBean jobEscalaDetalleBean = new JobEscalaDetalleBean();

                jobEscalaDetalleBean.setIdEscala(xIdEscala);
                jobEscalaDetalleBean.setIdEscala(xIdEscala);

                jobEscalaDetalleBean.setIdEscala(xIdEscala);
                jobEscalaDetalleBean.setItem(xItem);
                jobEscalaDetalleBean.setNombreItem(xNombreItem.trim().toUpperCase());
                jobEscalaDetalleBean.setEstado(xEstado);

                jobEscalaDetalleBean.cambia();

                FachadaJobOperacionOperario fachadaJobOperacionOperario = new FachadaJobOperacionOperario();

                fachadaJobOperacionOperario.setIdLocal(xIdLocalUsuario);

                request.setAttribute("fachadaJobOperacionOperario", fachadaJobOperacionOperario);

                return "/jsp/vtaContenedorCatalogoPotMaquina.jsp";
            }
            if (accionContenedor.compareTo("cambia") == 0) {
                String xIdEscala = request.getParameter("xIdEscala");
                String xItem = request.getParameter("xItem");

                JobEscalaDetalleBean jobEscalaDetalleBean = new JobEscalaDetalleBean();

                FachadaJobEscalaDetalle fachadaJobEscalaDetalle = new FachadaJobEscalaDetalle();

                jobEscalaDetalleBean.setIdEscala(xIdEscala);
                jobEscalaDetalleBean.setItem(xItem);

                fachadaJobEscalaDetalle = jobEscalaDetalleBean.traeFCH();

                request.setAttribute("fachadaJobEscalaDetalle", fachadaJobEscalaDetalle);

                return "/jsp/vtaFrmCamCatalogoPotMaquina.jsp";
            }
            if (accionContenedor.compareTo("Ingresar") == 0) {
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdEscala = request.getParameter("xIdEscalaNew");
                String xNombreItem = request.getParameter("xNombreMaquina");

                Validacion campoAValidar = new Validacion();

                campoAValidar.reasignar("NOMBRE MAQUINA", xNombreItem);

                campoAValidar.validarCampoString();
                if (!campoAValidar.isValido()) {
                    request.setAttribute("validacion", campoAValidar);

                    return "/jsp/gralError.jsp";
                }
                JobEscalaDetalleBean jobEscalaDetalleBean = new JobEscalaDetalleBean();

                jobEscalaDetalleBean.setIdEscala(xIdEscala);

                int xMaximoItem = jobEscalaDetalleBean.maximoItem() + 1;

                jobEscalaDetalleBean.setIdEscala(xIdEscala);
                jobEscalaDetalleBean.setItem(xMaximoItem);
                jobEscalaDetalleBean.setNombreItem(xNombreItem.trim().toUpperCase());
                jobEscalaDetalleBean.setEstado(xEstadoActivo);

                jobEscalaDetalleBean.ingresa();

                FachadaJobOperacionOperario fachadaJobOperacionOperario = new FachadaJobOperacionOperario();

                fachadaJobOperacionOperario.setIdLocal(xIdLocalUsuario);

                request.setAttribute("fachadaJobOperacionOperario", fachadaJobOperacionOperario);

                return "/jsp/vtaContenedorCatalogoPotMaquina.jsp";
            }
        }
        return "/jsp/vtaContenedorCatalogoPotOperario.jsp";
    }

}
