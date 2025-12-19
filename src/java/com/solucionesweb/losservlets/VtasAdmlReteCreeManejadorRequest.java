package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaContableCree;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.ContableCreeBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.JhDate;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasAdmlReteCreeManejadorRequest
  implements GralManejadorRequest
{
  public String generaPdf(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor);
    

    int xEstadoActivo = 9;
    

    JhDate jhDate = new JhDate();
    

    String fechaTxHM = null;
    try
    {
      fechaTxHM = jhDate.getDate(4, false);
    }
    catch (Exception ex) {}
    Day day = new Day();
    String strFechaVisita = day.getFechaFormateada();
    

    HttpSession sesion = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    


    String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
    Double xIdUsuario = Double.valueOf(usuarioBean.getIdUsuario());
    

    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    

    agendaLogVisitaBean.setEstado(xEstadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(xIdUsuario.doubleValue());
    

    FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
    

    fachadaAgendaLogVisitaBean = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();
    


    int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
    if (accionContenedor != null)
    {
      if (accionContenedor.compareTo("Salir") == 0) {
        return "/jsp/empty.htm";
      }
      if (accionContenedor.compareTo("Regresar") == 0) {
        return "/jsp/empty.htm";
      }
      if (accionContenedor.compareTo("Traer") == 0)
      {
        String xIdRteCree = request.getParameter("xIdRteCree");
        

        Validacion validacion = new Validacion();
        


        validacion.reasignar("CODIGO", xIdRteCree);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        FachadaContableCree fachadaContableCree = new FachadaContableCree();
        

        ContableCreeBean contableCreeBean = new ContableCreeBean();
        

        contableCreeBean.setIdRteCree(xIdRteCree);
        

        fachadaContableCree = contableCreeBean.listaUnFCH();
        if (fachadaContableCree.getIdRteCree() == 0)
        {
          validacion.reasignar("NO EXISTE LA ACTIVIDAD ECONOMICA " + xIdRteCree, "");
          

          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        request.setAttribute("fachadaContableCree", fachadaContableCree);
        

        return "/jsp/vtaFrmTraReteCree.jsp";
      }
      if (accionContenedor.compareTo("Modificar") == 0)
      {
        String xIdRteCree = request.getParameter("xIdRteCree");
        String xPorcentajeRteCree = request.getParameter("xPorcentajeRteCree");
        String xVrBaseRteCree = request.getParameter("xVrBaseRteCree");
        

        Validacion validacion = new Validacion();
        


        validacion.reasignar("PORCENTAJE", xPorcentajeRteCree);
        

        validacion.validarCampoDoublePositivo();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("VALOR BASE", xVrBaseRteCree);
        

        validacion.validarCampoDoublePositivo();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        ContableCreeBean contableCreeBean = new ContableCreeBean();
        

        contableCreeBean.setIdRteCree(xIdRteCree);
        contableCreeBean.setPorcentajeRteCree(xPorcentajeRteCree);
        contableCreeBean.setVrBaseRteCree(xVrBaseRteCree);
        
        contableCreeBean.actualiza();
        

        return "/jsp/vtaContenedorReteCree.jsp";
      }
    }
    return "/jsp/empty.htm";
  }
}
