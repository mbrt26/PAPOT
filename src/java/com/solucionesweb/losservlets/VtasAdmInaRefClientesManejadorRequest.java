package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraClientesBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasAdmInaRefClientesManejadorRequest implements GralManejadorRequest {

    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();

        if (accionContenedor.compareTo("Consultar") == 0) {
            String xIdCliente = request.getParameter("xIdTercero");

            ColaboraOrdenTrabajo cot = new ColaboraOrdenTrabajo();

            cot.setIdCliente(xIdCliente);

            Vector vector = new Vector();

            vector = cot.listaUnaFichaAll();

            //
            request.setAttribute("vector", vector);

            FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
                        
            ColaboraClientesBean ccb = new ColaboraClientesBean();

            ccb.setIdCliente(xIdCliente);
            ccb.setIdTipoTercero(1);
            
            fachadaTerceroBean = ccb.listaUnTercero();

            request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
            //
            return "/jsp/vtaAdmInaRefClient.jsp";

        }

        return "/jsp/empty.htm";

    }

}
