package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasControlRetencionManejadorRequest
  implements GralManejadorRequest
{
  public String generaPdf(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    int xEstadoActivo = 9;
    

    Day day = new Day();
    String strFechaVisita = day.getFechaFormateada();
    

    HttpSession sesion = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    


    String xIdUsuario = usuarioBean.getIdUsuarioStr();
    String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
    

    return "/jsp/vtaContenedorRetencion.jsp";
  }
}
