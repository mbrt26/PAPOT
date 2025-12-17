package com.solucionesweb.losservlets;

import com.solucionesweb.lasayudas.ProcesoGuardaPlu;
import com.solucionesweb.lasayudas.ProcesoIngresoParcialCompra;
import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.ContableCreeBean;
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;
import com.solucionesweb.losbeans.negocio.DctoBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;
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

public class VtasAdmResurtidoCompraManejadorRequest
  implements GralManejadorRequest
{
  public String generaPdf(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String accionContenedor = request.getParameter("accionContenedor");
    

    System.out.println("accionContenedor :" + accionContenedor);
    

    int xIdTipoOrdenCompra = 1;
    int xIdTipoTerceroProveedor = 2;
    int xIdTipoOrdenCompraProceso = xIdTipoOrdenCompra + 50;
    


    HttpSession sesion = request.getSession();
    

    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    


    String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
    String xIdUsuario = usuarioBean.getIdUsuarioSf0();
    int xEstadoActivo = 9;
    int xEstadoSuspendido = 8;
    

    int estadoProgramada = 9;
    

    Day day = new Day();
    

    String strFechaVisita = day.getFechaFormateada();
    

    JhDate jhDate = new JhDate();
    

    String fechaTxHM = null;
    try
    {
      fechaTxHM = jhDate.getDate(4, false);
    }
    catch (Exception ex)
    {
      Logger.getLogger(VtasAdmResurtidoTrasladoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
    }
    FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
    


    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    


    agendaLogVisitaBean.setEstado(xEstadoActivo);
    agendaLogVisitaBean.setFechaVisita(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(xIdUsuario);
    

    fachadaAgendaLogVisitaBean = agendaLogVisitaBean.listaLogActivo();
    


    int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
    

    boolean xOrdenDiferente = agendaLogVisitaBean.validaDiferenteOrdenProceso(xIdTipoOrdenCompraProceso);
    if (xOrdenDiferente)
    {
      Validacion validacion = new Validacion();
      

      validacion.setDescripcionError("ERROR, DEBE TERMINAR PROCESO ACTIVO");
      

      request.setAttribute("validacion", validacion);
      return "/jsp/gralError.jsp";
    }
    if (accionContenedor.compareTo("Crear") == 0)
    {
      xIdLogActual = xIdLogActual;
    }
    else
    {
      agendaLogVisitaBean.setEstado(xEstadoSuspendido);
      agendaLogVisitaBean.setIdLog(xIdLogActual);
      

      fachadaAgendaLogVisitaBean = agendaLogVisitaBean.listaLogSuspendidoFCH(xIdTipoOrdenCompraProceso);
      if (fachadaAgendaLogVisitaBean.getIdLog() > 0) {
        xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
      }
    }
    if (accionContenedor != null)
    {
      if (accionContenedor.compareTo("Salir") == 0) {
        return "/jsp/empty.htm";
      }
      if (accionContenedor.compareTo("Regresar") == 0) {
        return "/jsp/empty.htm";
      }
      if (accionContenedor.compareTo("Confirmar") == 0)
      {
        String xDiasHistoria = request.getParameter("xDiasHistoria");
        String xDiasInventario = request.getParameter("xDiasInventario");
        
        String xIdTercero = request.getParameter("xIdTercero");
        String xFechaCorte = request.getParameter("xFechaCorte");
        String[] xIdPluArr = request.getParameterValues("xIdPlu");
        String[] xCantidadPedidaArr = request.getParameterValues("xCantidadPedido");
        
        String[] xCostoPedidoArr = request.getParameterValues("xCostoPedido");
        
        String xIdLog = request.getParameter("xIdLog");
        


        Validacion valida = new Validacion();
        for (int indice = 0; indice < xIdPluArr.length; indice++) {
          if (xCantidadPedidaArr[indice].length() != 0)
          {
            valida.reasignar("CANTIDAD", xCantidadPedidaArr[indice]);
            

            valida.validarCampoDoublePositivo();
            if (!valida.isValido())
            {
              request.setAttribute("validacion", valida);
              return "/jsp/gralError.jsp";
            }
            if (xCostoPedidoArr[indice].length() != 0)
            {
              valida.reasignar("VR.COSTO", xCostoPedidoArr[indice]);
              

              valida.validarCampoDoublePositivo();
              if (!valida.isValido())
              {
                request.setAttribute("validacion", valida);
                return "/jsp/gralError.jsp";
              }
            }
          }
        }
        Validacion validacion = new Validacion();
        for (int indice = 0; indice < xCantidadPedidaArr.length; indice++)
        {
          validacion.reasignar("CANTIDAD PEDIDA", xCantidadPedidaArr[indice]);
          


          validacion.validarCampoDoublePositivo();
          if (!validacion.isValido())
          {
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";
          }
          validacion.reasignar("COSTO NEGOCIADO", xCostoPedidoArr[indice]);
          


          validacion.validarCampoDoublePositivo();
          if (!validacion.isValido())
          {
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";
          }
        }
        xIdLogActual = new Integer(xIdLog).intValue();
        

        ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();
        for (int indice = 0; indice < xCantidadPedidaArr.length; indice++) {
          if (new Double(xCantidadPedidaArr[indice]).doubleValue() > 0.0D) {
            procesoGuardaPlu.guarda(xIdLogActual, xIdPluArr[indice], xCantidadPedidaArr[indice], xCostoPedidoArr[indice], xIdTipoOrdenCompraProceso, xIdUsuario, xIdLocalUsuario, xIdTercero, xFechaCorte, xDiasHistoria, xDiasInventario);
          }
        }
        ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();
        


        colaboraResurtidoOrden.setIdLocal(xIdLocalUsuario);
        colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        colaboraResurtidoOrden.setIdLog(xIdLog);
        

        FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();
        


        fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
        fachadaColaboraProveedorBean.setIdDctoNitCC("");
        


        request.setAttribute("fachadaColaboraProveedorBean", fachadaColaboraProveedorBean);
        


        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();
        


        fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();
        


        fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraDctoOrdenBean.setFechaOrden(strFechaVisita);
        fachadaColaboraDctoOrdenBean.setIdDctoNitCC("");
        

        request.setAttribute("fachadaColaboraDctoOrdenBean", fachadaColaboraDctoOrdenBean);
        


        return "/jsp/vtaFrmLegResurtidoCompra.jsp";
      }
      if (accionContenedor.compareTo("Iniciar") == 0)
      {
        String xDiasHistoria = request.getParameter("xDiasHistoria");
        String xDiasInventario = request.getParameter("xDiasInventario");
        
        String xIdTercero = request.getParameter("xIdTercero");
        String xFechaCorte = request.getParameter("xFechaCorte");
        String xIdLog = request.getParameter("xIdLog");
        

        xIdLogActual = new Integer(xIdLog).intValue();
        

        ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();
        


        procesoGuardaPlu.guarda(xIdLogActual, "208", "1", "1", xIdTipoOrdenCompraProceso, xIdUsuario, xIdLocalUsuario, xIdTercero, xFechaCorte, xDiasHistoria, xDiasInventario);
        











        int estadoSuspendido = 8;
        

        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setEstado(estadoSuspendido);
        

        boolean okActualiza = agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoProgramada);
        




        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
        


        fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
        fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
        fachadaDctoOrdenBean.setIdCliente(xIdTercero);
        fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
        fachadaDctoOrdenBean.setIdLog(xIdLogActual);
        fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
        

        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
        


        return "/jsp/vtaContenedorResurtidoCompra.jsp";
      }
      if (accionContenedor.compareTo("retira") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        String xIdPlu = request.getParameter("xIdPlu");
        

        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
        

        dctoOrdenBean.setIdLog(xIdLog);
        

        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
        

        fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLog();
        


        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();
        


        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenDetalleBean.setIdLog(xIdLog);
        dctoOrdenDetalleBean.setIdPlu(xIdPlu);
        


        boolean okRetiro = dctoOrdenDetalleBean.retiraPlu();
        

        dctoOrdenBean.setIdLocal(xIdLocalUsuario);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenBean.setIdOrden(xIdLog);
        

        boolean okDetalle = dctoOrdenDetalleBean.validaOrden();
        if (!okDetalle)
        {
          dctoOrdenBean.setIdLocal(xIdLocalUsuario);
          dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenBean.setIdOrden(xIdLog);
          

          dctoOrdenBean.retiraOrden();
          

          fachadaDctoOrdenBean.setDiasHistoria(30);
          fachadaDctoOrdenBean.setDiasInventario(15);
          fachadaDctoOrdenBean.setIdCliente("0");
          fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
          fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
          fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
          
          fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
          fachadaDctoOrdenBean.setIdLog(xIdLogActual);
          fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
          


          request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
          


          return "/jsp/vtaContenedorResurtidoCompra.jsp";
        }
        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();
        


        ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();
        


        colaboraResurtidoOrden.setIdLocal(xIdLocal);
        colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
        colaboraResurtidoOrden.setIdLog(xIdLog);
        

        fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();
        


        fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraDctoOrdenBean.setVrCostoSinIva(0.0D);
        fachadaColaboraDctoOrdenBean.setVrImpoconsumo(0.0D);
        fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(0.0D);
        fachadaColaboraDctoOrdenBean.setVrCostoIva(0.0D);
        

        request.setAttribute("fachadaColaboraDctoOrdenBean", fachadaColaboraDctoOrdenBean);
        


        FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();
        


        fachadaColaboraProveedorBean.setVrCostoSinIva(0.0D);
        fachadaColaboraProveedorBean.setVrImpoconsumo(0.0D);
        fachadaColaboraProveedorBean.setVrCostoRteFuente(0.0D);
        fachadaColaboraProveedorBean.setVrCostoIva(0.0D);
        fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
        fachadaColaboraProveedorBean.setIdDctoNitCC("");
        

        request.setAttribute("fachadaColaboraProveedorBean", fachadaColaboraProveedorBean);
        


        return "/jsp/vtaFrmLegResurtidoCompra.jsp";
      }
      if (accionContenedor.compareTo("+Productos") == 0)
      {
        String xIdLinea = request.getParameter("xIdLinea");
        

        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        

        String strCadena = xIdLinea.trim();
        int lonCadena = strCadena.length();
        int posCadena = strCadena.indexOf('+', 0);
        String xNombrePlu = "";
        if (posCadena > 0)
        {
          xIdLinea = strCadena.substring(0, posCadena).trim();
          xNombrePlu = strCadena.substring(posCadena + 1, lonCadena).trim();
        }
        else
        {
          double xIdPlu = 0.0D;
          String strIdPlu = strCadena;
          try
          {
            xIdPlu = new Double(strIdPlu).doubleValue();
          }
          catch (NumberFormatException nfe)
          {
            xNombrePlu = xIdLinea;
            xIdLinea = "";
          }
        }
        ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();
        


        colaboraResurtidoOrden.setIdLocal(xIdLocal);
        colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
        colaboraResurtidoOrden.setIdLog(xIdLog);
        

        FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();
        



        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();
        


        fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();
        


        fachadaColaboraDctoOrdenBean.setIdLinea(xIdLinea);
        fachadaColaboraDctoOrdenBean.setNombrePlu(xNombrePlu);
        

        request.setAttribute("fachadaColaboraDctoOrdenBean", fachadaColaboraDctoOrdenBean);
        


        return "/jsp/vtaFrmAdiResurtidoCompra.jsp";
      }
      boolean okLog;
      if (accionContenedor.compareTo("cumplir") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        

        int xIdEstadoTxSinTx = 1;
        int tareaVisitaCumplida = 7;
        int estadoTerminado = 1;
        

        agendaLogVisitaBean.setIdLog(xIdLog);
        agendaLogVisitaBean.setIdCliente(xIdUsuario);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrden);
        agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
        agendaLogVisitaBean.setIdLocal(xIdLocal);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdEstadoVisita(tareaVisitaCumplida);
        agendaLogVisitaBean.setEstado(estadoTerminado);
        

        ProcesoIp procesoIp = new ProcesoIp();
        

        String ipTx = procesoIp.getIpTx(request);
        

        agendaLogVisitaBean.setIpTx(ipTx);
        agendaLogVisitaBean.setFechaTx(fechaTxHM);
        

        okLog = agendaLogVisitaBean.finalizaVisita();
      }
//      boolean okLog;
      if (accionContenedor.compareTo("Eliminar") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdOrden = request.getParameter("xIdOrden");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        

        int xIdEstadoTxSinTx = 1;
        int tareaVisita = 6;
        int estadoTerminado = 1;
        

        agendaLogVisitaBean.setIdLog(xIdLog);
        agendaLogVisitaBean.setIdCliente(xIdUsuario);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrden);
        agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
        agendaLogVisitaBean.setIdLocal(xIdLocal);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
        agendaLogVisitaBean.setEstado(estadoTerminado);
        

        ProcesoIp procesoIp = new ProcesoIp();
        

        String ipTx = procesoIp.getIpTx(request);
        

        agendaLogVisitaBean.setIpTx(ipTx);
        agendaLogVisitaBean.setFechaTx(fechaTxHM);
        

        okLog = agendaLogVisitaBean.finalizaVisita();
      }
      if (accionContenedor.compareTo("Imprimir") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdOrden = request.getParameter("xIdOrden");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        


        GeneraPDFResurtidoCompraSolicitado generaPDFResurtidoSolicitado = new GeneraPDFResurtidoCompraSolicitado();
        


        generaPDFResurtidoSolicitado.setIdLocal(xIdLocal);
        generaPDFResurtidoSolicitado.setIdOrden(xIdOrden);
        generaPDFResurtidoSolicitado.setIdTipoOrden(xIdTipoOrden);
        generaPDFResurtidoSolicitado.setIdLog(xIdLog);
        generaPDFResurtidoSolicitado.setReporteName("RepEmpresaResurtidoCompraSolicitada");
        


        generaPDFResurtidoSolicitado.setTituloReporte("COMPRA SOLICITADA");
        


        generaPDFResurtidoSolicitado.generaPdf(request, response);
      }
      if (accionContenedor.compareTo("tomar") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdOrden = request.getParameter("xIdOrden");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        

        GeneraPDFResurtidoCompraLegalizado generaPDF = new GeneraPDFResurtidoCompraLegalizado();
        


        generaPDF.setIdLocal(xIdLocal);
        generaPDF.setIdOrden(xIdOrden);
        generaPDF.setIdTipoOrden(xIdTipoOrden);
        generaPDF.setIdLog(xIdLog);
        generaPDF.setTituloReporte("COMPRA CUMPLIDA");
        generaPDF.setReporteName("RepEmpresaResurtidoCompraLegalizado");
        

        generaPDF.generaPdf(request, response);
      }
      if (accionContenedor.compareTo("Listar") == 0)
      {
        String xIdTercero = request.getParameter("xIdTercero");
        String xFechaCorte = request.getParameter("xFechaCorte");
        

        Validacion validacion = new Validacion();
        

        validacion.reasignar("PROVEEDOR", xIdTercero);
        

        validacion.validarCampoDouble();
        if (!validacion.isValido())
        {
          validacion.setDescripcionError("ERROR, FALTA SELECCIONAR PROVEEDOR");
          

          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("FECHA CORTE", xFechaCorte);
        
        validacion.validarCampoFecha();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        int xDiasHistoria = 0;
        int xDiasInventario = 0;
        


        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
        


        fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
        fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
        fachadaDctoOrdenBean.setIdCliente(xIdTercero);
        fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompra);
        fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
        fachadaDctoOrdenBean.setIdLog(xIdLogActual);
        

        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
        


        return "/jsp/vtaFrmConResurtidoCompra.jsp";
      }
      if ((accionContenedor.compareTo("Liquidar") == 0) || (accionContenedor.compareTo("Legalizar") == 0))
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        

        String[] xIdPluArr = request.getParameterValues("xIdPlu");
        String[] xCantidadArr = request.getParameterValues("xCantidad");
        
        String[] xVrSubtotalArr = request.getParameterValues("xVrSubtotalArr");
        
        String[] xPorcentajeIvaArr = request.getParameterValues("xPorcentajeIvaArr");
        
        String[] xVrIvaResurtidoArr = request.getParameterValues("xVrIvaResurtidoArr");
        
        String[] xVrImpoconsumoArr = request.getParameterValues("xVrImpoconsumoArr");
        


        String xVrBase = request.getParameter("xVrBase");
        String xVrImpoconsumo = request.getParameter("xVrImpoconsumo");
        
        String xVrDescuento = request.getParameter("xVrDescuento");
        
        String xVrIva = request.getParameter("xVrIva");
        String xVrRteFuente = request.getParameter("xVrRteFuente");
        String xVrRteCree = request.getParameter("xVrRteCree");
        String xVrPagarDctoNitCC = request.getParameter("xVrPagarDctoNitCC");
        
        String xFechaDctoNitCC = request.getParameter("xFechaDctoNitCC");
        
        String xFechaDcto = request.getParameter("xFechaDcto");
        
        String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");
        

        int xPresionaLiquidar = 1;
        

        Validacion valida = new Validacion();
        

        valida.reasignar("FECHA RECIBO ", xFechaDcto);
        

        valida.validarCampoFecha();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("FECHA FACTURA", xFechaDctoNitCC);
        

        valida.validarCampoFecha();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("NUMERO FACTURA", xIdDctoNitCC);
        

        valida.validarCampoString();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VR.BASE", xVrBase);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("V.IMPOCO", xVrImpoconsumo);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VR.DESCUENTO", xVrDescuento);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VR.IVA", xVrIva);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VR.RFTE", xVrRteFuente);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VR.RCREE", xVrRteCree);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        for (int indice = 0; indice < xCantidadArr.length; indice++)
        {
          valida.reasignar("CAN.REC", xCantidadArr[indice]);
          

          valida.validarCampoDoublePositivo();
          if (!valida.isValido())
          {
            request.setAttribute("validacion", valida);
            return "/jsp/gralError.jsp";
          }
          valida.reasignar("VR.BASE", xVrSubtotalArr[indice]);
          

          valida.validarCampoDoublePositivo();
          if (!valida.isValido())
          {
            request.setAttribute("validacion", valida);
            return "/jsp/gralError.jsp";
          }
          valida.reasignar("%IVA", xPorcentajeIvaArr[indice]);
          

          valida.validarCampoDoublePositivo();
          if (!valida.isValido())
          {
            request.setAttribute("validacion", valida);
            return "/jsp/gralError.jsp";
          }
          valida.reasignar("V.IMPOCO", xVrImpoconsumoArr[indice]);
          

          valida.validarCampoDoublePositivo();
          if (!valida.isValido())
          {
            request.setAttribute("validacion", valida);
            return "/jsp/gralError.jsp";
          }
          valida.reasignar("VR.IVA", xVrIvaResurtidoArr[indice]);
          

          valida.validarCampoDoublePositivo();
          if (!valida.isValido())
          {
            request.setAttribute("validacion", valida);
            return "/jsp/gralError.jsp";
          }
        }
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();
        

        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenDetalleBean.setIdLog(xIdLog);
        

        dctoOrdenDetalleBean.borraResurtidoCompra();
        for (int indice = 0; indice < xCantidadArr.length; indice++)
        {
          dctoOrdenDetalleBean.setIdLocal(xIdLocal);
          dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
          dctoOrdenDetalleBean.setIdLog(xIdLog);
          dctoOrdenDetalleBean.setCantidad(xCantidadArr[indice]);
          dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
          dctoOrdenDetalleBean.setVrCostoResurtido(xVrSubtotalArr[indice]);
          
          dctoOrdenDetalleBean.setVrIvaResurtido(xVrIvaResurtidoArr[indice]);
          
          dctoOrdenDetalleBean.setVrImpoconsumo(xVrImpoconsumoArr[indice]);
          


          dctoOrdenDetalleBean.setPorcentajeIva(xPorcentajeIvaArr[indice]);
          


          double xVrIvaResurtido = new Double(xVrSubtotalArr[indice]).doubleValue() * (new Double(xPorcentajeIvaArr[indice]).doubleValue() / 100.0D);
          


          dctoOrdenDetalleBean.setVrIvaResurtido(xVrIvaResurtido);
          if (dctoOrdenDetalleBean.getCantidad() != 0.0D) {
            dctoOrdenDetalleBean.actualizaResurtidoCompra();
          }
        }
        ColaboraOrdenDetalleBean colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();
        


        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();
        


        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        

        fachadaDctoOrdenDetalleBean = colaboraOrdenDetalleBean.liquidaOrdenFCH();
        


        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();
        

        DctoBean dctoBean = new DctoBean();
        

        dctoBean.setIdLocal(xIdLocal);
        dctoBean.setIdTipoOrden(xIdTipoOrdenCompra);
        dctoBean.setIdDctoNitCC(xIdDctoNitCC.trim());
        dctoBean.setIdCliente(fachadaDctoOrdenDetalleBean.getIdCliente());
        


        fachadaDctoBean = dctoBean.listaUnIdDctoNitCC();
        if (fachadaDctoBean.getIdDcto() > 0.0D)
        {
          valida.reasignar("ERROR, FACTURA YA EXISTE PARA EL PROVEEDOR", "");
          


          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        int xIdTipoTerceroCliente = 2;
        

        ColaboraTercero colaboraTercero = new ColaboraTercero();
        

        colaboraTercero.setIdCliente(fachadaDctoOrdenDetalleBean.getIdCliente());
        
        colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        
        fachadaTerceroBean = colaboraTercero.listaTerceroFCH();
        


        String xIdClienteProveedor = fachadaTerceroBean.getIdCliente();
        

        FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();
        


        FachadaDctoOrdenDetalleBean fachadaDctoOrdenProveedorBean = new FachadaDctoOrdenDetalleBean();
        


        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        

        fachadaDctoOrdenProveedorBean = colaboraOrdenDetalleBean.liquidaOrdenProveedorFCH();
        


        int xIdConceptoRFCompra = 1;
        int xVrTopeSI = 0;
        int xVrTopeNO100 = 1;
        

        ContableRetencionBean contableRetencionBean = new ContableRetencionBean();
        
        double xVrRetencion = contableRetencionBean.calculaRetencion(fachadaTerceroBean.getIdAutoRetenedor(), xIdConceptoRFCompra, fachadaDctoOrdenProveedorBean.getVrCostoResurtido(), xVrTopeSI);
   

        ContableCreeBean contableCreeBean = new ContableCreeBean();
        

        double xVrRetencioCree = contableCreeBean.calculaRetencionCree(xIdTipoTerceroProveedor, fachadaDctoOrdenProveedorBean.getVrCostoResurtido(), xIdClienteProveedor);
        




        fachadaColaboraProveedorBean.setVrCostoSinIva(fachadaDctoOrdenProveedorBean.getVrCostoResurtido());
        
        fachadaColaboraProveedorBean.setVrImpoconsumo(fachadaDctoOrdenProveedorBean.getVrImpoconsumo());
        
        fachadaColaboraProveedorBean.setVrCostoRteFuente(xVrRetencion);
        fachadaColaboraProveedorBean.setVrCostoRteCree(xVrRetencioCree);
        fachadaColaboraProveedorBean.setVrCostoIva(fachadaDctoOrdenProveedorBean.getVrIvaResurtido());
        
        fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
        fachadaColaboraProveedorBean.setIdDctoNitCC(xIdDctoNitCC);
        

        request.setAttribute("fachadaColaboraProveedorBean", fachadaColaboraProveedorBean);
        


        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();
        
        ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();
        

        colaboraResurtidoOrden.setIdLocal(xIdLocal);
        colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
        colaboraResurtidoOrden.setIdLog(xIdLog);
        

        fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();
        

        fachadaColaboraDctoOrdenBean.setIdLocal(xIdLocal);
        fachadaColaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
        fachadaColaboraDctoOrdenBean.setIdLog(xIdLog);
        fachadaColaboraDctoOrdenBean.setFechaEntrega(xFechaDctoNitCC);
        fachadaColaboraDctoOrdenBean.setFechaOrden(xFechaDcto);
        fachadaColaboraDctoOrdenBean.setVrCostoSinIva(xVrBase);
        fachadaColaboraDctoOrdenBean.setVrImpoconsumo(xVrImpoconsumo);
        fachadaColaboraDctoOrdenBean.setVrDescuento(0.0D);
        fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(xVrRetencion);
        fachadaColaboraDctoOrdenBean.setVrCostoRteCree(xVrRteCree);
        fachadaColaboraDctoOrdenBean.setVrCostoIva(xVrIva);
        fachadaColaboraDctoOrdenBean.setIdDctoNitCC(xIdDctoNitCC);
        

        fachadaColaboraDctoOrdenBean.setVrDiferencia(
                fachadaColaboraDctoOrdenBean.getVrPagarFactura() - 
                fachadaColaboraProveedorBean.getVrPagarFactura());
        

        fachadaColaboraDctoOrdenBean.setVrDiferenciaVrCostoSinIva(
                fachadaColaboraDctoOrdenBean.getVrCostoSinIva() - 
                fachadaColaboraProveedorBean.getVrCostoSinIva());
        


        fachadaColaboraDctoOrdenBean.setVrDiferenciaVrImpoconsumo(
                fachadaColaboraDctoOrdenBean.getVrImpoconsumo() - 
                fachadaColaboraProveedorBean.getVrImpoconsumo());
        


        fachadaColaboraDctoOrdenBean.setVrDiferenciaVrCostoIva(
                fachadaColaboraDctoOrdenBean.getVrCostoIva() - 
                        fachadaColaboraProveedorBean.getVrCostoIva());
        


        fachadaColaboraDctoOrdenBean.setVrDiferenciaVrRteFuente(
                fachadaColaboraDctoOrdenBean.getVrCostoRteFuente() - 
                        fachadaColaboraProveedorBean.getVrCostoRteFuente());
        

        fachadaColaboraDctoOrdenBean.setVrDiferenciaVrRteCree(
                fachadaColaboraDctoOrdenBean.getVrCostoRteCree() - 
                        fachadaColaboraProveedorBean.getVrCostoRteCree());
        



        fachadaColaboraProveedorBean.setVrCostoSinIva(
                fachadaDctoOrdenProveedorBean.getVrCostoResurtido());
        

        fachadaColaboraProveedorBean.setVrImpoconsumo(
                fachadaDctoOrdenProveedorBean.getVrImpoconsumo());
        

        fachadaColaboraProveedorBean.setVrCostoRteFuente(xVrRteFuente);
        fachadaColaboraProveedorBean.setVrCostoIva(fachadaDctoOrdenProveedorBean.getVrIvaResurtido());
        

        fachadaColaboraProveedorBean.setVrCostoRteCree(xVrRteCree);
        
        fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
        fachadaColaboraProveedorBean.setIdDctoNitCC(xIdDctoNitCC);
        fachadaColaboraProveedorBean.setPresionaLiquidar(xPresionaLiquidar);
        
        fachadaColaboraDctoOrdenBean.setIndicador(fachadaTerceroBean.getIndicador());
        if (accionContenedor.compareTo("Legalizar") != 0)
        {
          request.setAttribute("fachadaColaboraDctoOrdenBean", fachadaColaboraDctoOrdenBean);
          


          return "/jsp/vtaFrmLegResurtidoCompra.jsp";
        }
        if (((int)fachadaColaboraDctoOrdenBean.getVrPagarFactura() - 
                (int)fachadaColaboraProveedorBean.getVrPagarFactura() > 1) || 
                ((int)fachadaColaboraDctoOrdenBean.getVrPagarFactura() - 
                (int)fachadaColaboraProveedorBean.getVrPagarFactura() < -1))
        {
          valida.reasignar("ERROR, LIQUIDACION INCORRECTA DE FACTURA", "");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        ProcesoIngresoParcialCompra proceso = new ProcesoIngresoParcialCompra();
        


        fachadaDctoBean = proceso.ingresaCompras(new Integer(xIdLocal).intValue(), 
                                          xIdTipoOrdenCompra, 
                                          new Integer(xIdLog).intValue(), 
                                          xIdTipoOrdenCompraProceso, 
                                          fachadaColaboraProveedorBean);

        GeneraPDFResurtidoCompraLegalizado generaPDF = new GeneraPDFResurtidoCompraLegalizado();
        


        generaPDF.setIdLocal(xIdLocalUsuario);
        generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
        generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
        generaPDF.setTituloReporte("COMPRA CUMPLIDA");
        generaPDF.setReporteName("RepEmpresaResurtidoCompraLegalizado");
        

        generaPDF.generaPdf(request, response);
      }
      if (accionContenedor.compareTo("coger") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdLog = request.getParameter("xIdLog");
        

        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();
        


        ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();
        


        colaboraResurtidoOrden.setIdLocal(xIdLocal);
        colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
        colaboraResurtidoOrden.setIdLog(xIdLog);
        

        fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();
        


        fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
        

        request.setAttribute("fachadaColaboraDctoOrdenBean", fachadaColaboraDctoOrdenBean);
        


        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();
        


        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenDetalleBean.setIdLog(xIdLog);
        

        dctoOrdenDetalleBean.actualizaPendiente();
        

        ColaboraOrdenDetalleBean colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();
        


        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();
        


        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        

        fachadaDctoOrdenDetalleBean = colaboraOrdenDetalleBean.liquidaOrdenFCH();
        

        int xIdTipoTerceroCliente = 2;
        

        ColaboraTercero colaboraTercero = new ColaboraTercero();
        

        colaboraTercero.setIdCliente(fachadaDctoOrdenDetalleBean.getIdCliente());
        
        colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean = colaboraTercero.listaTerceroFCH();
        


        int xIdConceptoRFCompra = 1;
        int xIdRteFuenteVrBase = 0;
        

        ContableRetencionBean contableRetencionBean = new ContableRetencionBean();
        


        double xVrRetencion = contableRetencionBean.calculaRetencion(fachadaTerceroBean.getIdAutoRetenedor(), xIdConceptoRFCompra, fachadaDctoOrdenDetalleBean.getVrCostoSinIva(), xIdRteFuenteVrBase);
        






        colaboraResurtidoOrden.setIdLocal(xIdLocal);
        colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
        colaboraResurtidoOrden.setIdLog(xIdLog);
        

        fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();
        


        fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraDctoOrdenBean.setVrCostoSinIva(0.0D);
        fachadaColaboraDctoOrdenBean.setVrImpoconsumo(0.0D);
        fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(0.0D);
        fachadaColaboraDctoOrdenBean.setVrCostoIva(0.0D);
        fachadaColaboraDctoOrdenBean.setIdDctoNitCC("");
        

        fachadaColaboraDctoOrdenBean.setNombreTercero(fachadaColaboraDctoOrdenBean.getNombreTercero());
        
        fachadaColaboraDctoOrdenBean.setIdOrden(fachadaColaboraDctoOrdenBean.getIdOrden());
        

        fachadaColaboraDctoOrdenBean.setFechaOrden(fachadaColaboraDctoOrdenBean.getFechaOrden());
        
        fachadaColaboraDctoOrdenBean.setCantidadArticulos(fachadaColaboraDctoOrdenBean.getCantidadArticulos());
        


        request.setAttribute("fachadaColaboraDctoOrdenBean", fachadaColaboraDctoOrdenBean);
        


        FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();
        


        FachadaDctoOrdenDetalleBean fachadaDctoOrdenProveedorBean = new FachadaDctoOrdenDetalleBean();
        


        colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
        colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        colaboraOrdenDetalleBean.setIdLog(xIdLog);
        

        fachadaDctoOrdenProveedorBean = colaboraOrdenDetalleBean.liquidaOrdenProveedorFCH();
        


        fachadaColaboraProveedorBean.setVrCostoSinIva(fachadaDctoOrdenProveedorBean.getVrCostoResurtido());
        
        fachadaColaboraProveedorBean.setVrImpoconsumo(fachadaDctoOrdenProveedorBean.getVrImpoconsumo());
        
        fachadaColaboraProveedorBean.setVrCostoRteFuente(0.0D);
        fachadaColaboraProveedorBean.setVrCostoIva(fachadaDctoOrdenProveedorBean.getVrIvaResurtido());
        
        fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
        fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
        fachadaColaboraProveedorBean.setIdDctoNitCC("");
        

        request.setAttribute("fachadaColaboraProveedorBean", fachadaColaboraProveedorBean);
        


        return "/jsp/vtaFrmLegResurtidoCompra.jsp";
      }
      if (accionContenedor.compareTo("Traer") == 0)
      {
        String xDiasHistoria = request.getParameter("xDiasHistoria");
        String xDiasInventario = request.getParameter("xDiasInventario");
        
        String xIdTercero = request.getParameter("xIdTercero");
        String xFechaCorte = request.getParameter("xFechaCorte");
        



        Validacion validacion = new Validacion();
        

        validacion.reasignar("#DIAS HISTORIA", xDiasHistoria);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("#DIAS INVENTARIO", xDiasInventario);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("PROVEEDOR", xIdTercero);
        

        validacion.validarCampoDouble();
        if (!validacion.isValido())
        {
          validacion.setDescripcionError("ERROR, FALTA SELECCIONAR PROVEEDOR");
          

          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("FECHA CORTE", xFechaCorte);
        
        validacion.validarCampoFecha();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        TerceroBean terceroBean = new TerceroBean();
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        terceroBean.setIdCliente(xIdTercero);
        terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);
        

        fachadaTerceroBean = terceroBean.listaUnTerceroFCH();
        
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        


        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
        

        fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
        fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
        fachadaDctoOrdenBean.setIdCliente(xIdTercero);
        fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
        fachadaDctoOrdenBean.setIdLog(xIdLogActual);
        

        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
        


        return "/jsp/vtaFrmLstResurtidoCompra.jsp";
      }
      if (accionContenedor.compareTo("Guardar") == 0)
      {
        String xDiasHistoria = request.getParameter("xDiasHistoria");
        String xDiasInventario = request.getParameter("xDiasInventario");
        
        String xIdTercero = request.getParameter("xIdTercero");
        String xFechaCorte = request.getParameter("xFechaCorte");
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String[] xIdPluArr = request.getParameterValues("xIdPlu");
        String[] xCantidadPedidaArr = request.getParameterValues("xCantidadPedido");
        
        String[] xCostoPedidoArr = request.getParameterValues("xCostoPedido");
        
        String xIdLog = request.getParameter("xIdLog");
        
        System.out.println(xIdTercero);
        


        Validacion validacion = new Validacion();
        for (int indice = 0; indice < xCantidadPedidaArr.length; indice++)
        {
          validacion.reasignar("CANTIDAD PEDIDA", xCantidadPedidaArr[indice]);
          


          validacion.validarCampoDoublePositivo();
          if (!validacion.isValido())
          {
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";
          }
          validacion.reasignar("COSTO NEGOCIADO", xCostoPedidoArr[indice]);
          


          validacion.validarCampoDoublePositivo();
          if (!validacion.isValido())
          {
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";
          }
        }
        xIdLogActual = new Integer(xIdLog).intValue();
        

        ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();
        for (int indice = 0; indice < xCantidadPedidaArr.length; indice++) {
          if (new Double(xCantidadPedidaArr[indice]).doubleValue() > 0.0D) {
            procesoGuardaPlu.guarda(xIdLogActual, xIdPluArr[indice], xCantidadPedidaArr[indice], xCostoPedidoArr[indice], xIdTipoOrdenCompraProceso, xIdUsuario, xIdLocalUsuario, xIdTercero, xFechaCorte, xDiasHistoria, xDiasInventario);
          }
        }
        int estadoSuspendido = 8;
        

        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setEstado(estadoSuspendido);
        

        boolean okActualiza = agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoProgramada);
        




        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
        


        fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
        fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
        fachadaDctoOrdenBean.setIdCliente(xIdTercero);
        fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
        fachadaDctoOrdenBean.setIdLog(xIdLogActual);
        fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
        

        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
        


        return "/jsp/vtaContenedorResurtidoCompra.jsp";
      }
      if ((accionContenedor.compareTo("Elaborar") == 0) || (accionContenedor.compareTo("Crear") == 0))
      {
        String xDiasHistoria = request.getParameter("xDiasHistoria");
        String xDiasInventario = request.getParameter("xDiasInventario");
        
        String xIdTercero = request.getParameter("xIdTercero");
        String xFechaCorte = request.getParameter("xFechaCorte");
        String xIdLog = request.getParameter("xIdLog");
        if (accionContenedor.compareTo("Elaborar") == 0) {
          xIdLogActual = new Integer(xIdLog).intValue();
        }
        Validacion validacion = new Validacion();
        

        validacion.reasignar("#DIAS HISTORIA", xDiasHistoria);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("#DIAS INVENTARIO", xDiasInventario);
        
        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("BODEGA ORIGEN", xIdTercero);
        

        validacion.validarCampoDouble();
        if (!validacion.isValido())
        {
          validacion.setDescripcionError("ERROR, FALTA SELECCIONAR BODEGA ORIGEN");
          

          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("FECHA CORTE", xFechaCorte);
        
        validacion.validarCampoFecha();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          return "/jsp/gralError.jsp";
        }
        TerceroBean terceroBean = new TerceroBean();
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        terceroBean.setIdCliente(xIdTercero);
        terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);
        

        fachadaTerceroBean = terceroBean.listaUnTerceroFCH();
        

        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        


        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
        

        fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
        fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
        fachadaDctoOrdenBean.setIdCliente(xIdTercero);
        fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
        fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
        fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
        fachadaDctoOrdenBean.setIdLog(xIdLogActual);
        

        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
        


        return "/jsp/vtaFrmIngResurtidoCompra.jsp";
      }
    }
    return "/jsp/empty.htm";
  }
}
