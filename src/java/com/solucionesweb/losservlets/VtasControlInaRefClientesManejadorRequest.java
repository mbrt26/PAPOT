package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraClientesBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VtasControlInaRefClientesManejadorRequest implements GralManejadorRequest{

    public String generaPdf(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
         int xIdTipoTerceroCliente = 1;
         
          ColaboraClientesBean ccb = new ColaboraClientesBean();
          
          Vector vector = new Vector();
          
          vector = ccb.listaTerceroOpcion();

        //
        request.setAttribute("vector",
                vector);

        //
        return "/jsp/vtaContenedorInaRefClient.jsp";
        
    }
    
}
