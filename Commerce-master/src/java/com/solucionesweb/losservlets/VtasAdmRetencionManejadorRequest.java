package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaContableRetencionBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.JhDate;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasAdmRetencionManejadorRequest
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
    catch (Exception ex)
    {
      Logger.getLogger(VtasAdmEmpresaFinalizaPedidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
    }
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
        String xIdConcepto = request.getParameter("xIdConcepto");
        String xIdSubcuenta = request.getParameter("xIdSubcuenta");
        String xIdPersona = request.getParameter("xIdPersona");
        

        ContableRetencionBean contableRetencionBean = new ContableRetencionBean();
        


        FachadaContableRetencionBean fachadaContableRetencionBean = new FachadaContableRetencionBean();
        


        contableRetencionBean.setIdConcepto(xIdConcepto);
        contableRetencionBean.setIdSubcuenta(xIdSubcuenta);
        contableRetencionBean.setIdPersona(xIdPersona);
        

        fachadaContableRetencionBean = contableRetencionBean.listaFCH();
        

        request.setAttribute("fachadaContableRetencionBean", fachadaContableRetencionBean);
        


        return "/jsp/vtaFrmTraRetencion.jsp";
      }
      if (accionContenedor.compareTo("Modificar") == 0)
      {
        String xIdConcepto = request.getParameter("xIdConcepto");
        String xIdSubcuenta = request.getParameter("xIdSubcuenta");
        String xIdPersona = request.getParameter("xIdPersona");
        String xNombreConcepto = request.getParameter("xNombreConcepto");
        String xPorcentajeRetencion = request.getParameter("xPorcentajeRetencion");
        String xVrBaseRetencion = request.getParameter("xVrBaseRetencion");
        String xIdTipoOrdenAlcance = request.getParameter("xIdTipoOrdenAlcance");
        

        Validacion validacion = new Validacion();
        


        validacion.reasignar("CONCEPTO", xIdConcepto);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("SUBCUENTA", xIdSubcuenta);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("IDPERSONA", xIdPersona);
        
        validacion.validarCampoEnteroPositivo();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("NOMBRE CONCEPTO", xNombreConcepto);
        
        validacion.validarCampoString();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("PORCENTAJE RETENCION", xPorcentajeRetencion);
        
        validacion.validarCampoDoublePositivo();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("VR.BASE", xVrBaseRetencion);
        

        validacion.validarCampoDoublePositivo();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("IDALCANCE", xIdTipoOrdenAlcance);
        

        validacion.validarCampoEnteroPositivo();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        ContableRetencionBean contableRetencionBean = new ContableRetencionBean();
        

        contableRetencionBean.setIdConcepto(xIdConcepto);
        contableRetencionBean.setIdSubcuenta(xIdSubcuenta);
        contableRetencionBean.setIdPersona(xIdPersona);
        contableRetencionBean.setNombreConcepto(xNombreConcepto);
        contableRetencionBean.setPorcentajeRetencion(xPorcentajeRetencion);
        contableRetencionBean.setVrBaseRetencion(xVrBaseRetencion);
        contableRetencionBean.setIdTipoOrdenAlcance(xIdTipoOrdenAlcance);
        

        contableRetencionBean.actualiza();
        

        return "/jsp/vtaContenedorRetencion.jsp";
      }
    }
    return "/jsp/empty.htm";
  }
}
