package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasControlCatalogoPotMaquinaManejadorRequest implements GralManejadorRequest {

    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession();

        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        FachadaJobOperacionOperario fachadaJobOperacionOperario = new FachadaJobOperacionOperario();

        fachadaJobOperacionOperario.setIdLocal(xIdLocalUsuario);

        request.setAttribute("fachadaJobOperacionOperario", fachadaJobOperacionOperario);

        return "/jsp/vtaContenedorCatalogoPotMaquina.jsp";
    }

}
