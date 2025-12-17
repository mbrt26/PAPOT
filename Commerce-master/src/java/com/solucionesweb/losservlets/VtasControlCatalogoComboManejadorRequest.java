package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class VtasControlCatalogoComboManejadorRequest
                                                 implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlCatalogoComboManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException,IOException   {

    //
    int xIdTipoCombo = 2;

    //
    FachadaPluBean fachadaPluBean         = new FachadaPluBean();

    //
    fachadaPluBean.setIdTipo(xIdTipoCombo);

    //
    request.setAttribute("fachadaPluBean",fachadaPluBean);
    return "/jsp/vtaContenedorCatalogoCombo.jsp";

  }
}
