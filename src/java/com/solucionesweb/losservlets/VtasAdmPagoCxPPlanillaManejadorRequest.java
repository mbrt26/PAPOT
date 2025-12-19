package com.solucionesweb.losservlets;

import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.DctoBean;
import com.solucionesweb.losbeans.negocio.PagoBean;
import com.solucionesweb.losbeans.negocio.PagoMedioBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.JhDate;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasAdmPagoCxPPlanillaManejadorRequest
  implements GralManejadorRequest
{
  public String generaPdf(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String accionContenedor = request.getParameter("accionContenedor");
    

    int xIdTipoTerceroCliente = 2;
    int xIdTipoOrdenVenta = 1;
    int tareaVisita = 6;
    int estadoProgramado = 1;
    int xIdEstadoTxSinTx = 1;
    

    String xIdSucursal = "00";
    String xIdCliente = "-1";
    

    int xIdTipoOrdenPagoProceso = xIdTipoOrdenVenta + 50;
    

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
    int estadoActivo = 9;
    Day day = new Day();
    String strFechaVisita = day.getFechaFormateada();
    

    HttpSession sesion = request.getSession();
    

    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    


    String xIdUsuario = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
    

    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(xIdUsuario);
    

    FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
    

    fachadaAgendaLogVisitaBean = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();
    


    int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
    

    fachadaAgendaLogVisitaBean.setIdUsuario(xIdUsuario);
    

    request.setAttribute("fachadaAgendaLogVisitaBean", fachadaAgendaLogVisitaBean);
    if (accionContenedor != null)
    {
      if (accionContenedor.compareTo("Salir") == 0) {
        return "/jsp/empty.htm";
      }
      if (accionContenedor.compareTo("Regresar") == 0) {
        return "/jsp/empty.htm";
      }
      if (accionContenedor.compareTo("listaPlanilla") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIdPlanilla = request.getParameter("xIdPlanilla");
        

        GeneraPDFPagoPlanillaVendedor generaPDF = new GeneraPDFPagoPlanillaVendedor();
        


        generaPDF.setIdLocal(xIdLocal);
        generaPDF.setIdTipoOrden(xIdTipoOrden);
        generaPDF.setIdPlanilla(xIdPlanilla);
        generaPDF.setTerceroReporte("VENDEDOR  ");
        generaPDF.setTituloReporte("PLANILLA DE PAGO # ");
        

        generaPDF.generaPdf(request, response);
      }
      if (accionContenedor.compareTo("Listar") == 0)
      {
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        Validacion valida = new Validacion();
        

        valida.reasignar("VENDEDOR", xIdVendedor);
        

        valida.validarCampoDouble();
        if (!valida.isValido())
        {
          valida.setDescripcionError("ERROR, EL VENDEDOR NO EXISTE");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("FECHA PAGO", xFechaPago);
        

        valida.validarCampoFecha();
        if (!valida.isValido())
        {
          valida.setDescripcionError("ERROR, FECHA PAGO CON FORMATO INCORRECTO");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        int idPeriodo = 200611;
        int estadoAtendido = 1;
        int estadoProgramada = 9;
        int idEstadoVisita = 1;
        


        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setEstado(estadoProgramada);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        

        boolean okLogOcupado = agendaLogVisitaBean.validaLogOcupado();
        if (okLogOcupado)
        {
          valida.reasignar("EXISTE UN PEDIDO O PAGO EN PROCESO", "");
          

          valida.setDescripcionError("DEBE TERMINAR O CANCELAR LO ACTIVO");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        

        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();
        

        usuarioBean.setIdUsuario(xIdVendedor);
        

        fachadaUsuarioBean = usuarioBean.listaUsuario();
        

        usuarioBean.setIdUsuario(xIdUsuario);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);
        


        return "/jsp/vtaFrmLstPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("Guardar Medio") == 0)
      {
        String xVrPago = request.getParameter("xVrPago");
        String xIdMedio = request.getParameter("xIdMedio");
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIndicador = request.getParameter("xIndicador");
        String xIdDcto = request.getParameter("xIdDcto");
        String xIdBanco = request.getParameter("xIdBanco");
        String xIdDctoMedio = request.getParameter("xIdDctoMedio");
        String xFechaCobro = request.getParameter("xFechaCobro");
        

        Validacion validacion = new Validacion();
        

        validacion.reasignar("CODIGO BANCO", xIdBanco);
        

        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          

          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("NUMERO CHEQUE", xIdDctoMedio);
        

        validacion.validarCampoString();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          

          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("FECHA COBRO CHEQUE", xFechaCobro);
        

        validacion.validarCampoFecha();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          

          return "/jsp/gralError.jsp";
        }
        validacion.reasignar("MEDIO DE PAGO", xIdMedio);
        

        validacion.validarCampoEntero();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          

          return "/jsp/gralError.jsp";
        }
        PagoBean pagoBean = new PagoBean();
        

        pagoBean.setIdLocal(xIdLocalUsuario);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        

        PagoMedioBean pagoMedioBean = new PagoMedioBean();
        

        int xEstadoOk = 1;
        

        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoMedioBean.setIndicador(xIndicador);
        pagoMedioBean.setVrMedio(xVrPago);
        pagoMedioBean.setEstado(xEstadoOk);
        pagoMedioBean.setIdBanco(xIdBanco);
        pagoMedioBean.setIdDctoMedio(xIdDctoMedio);
        pagoMedioBean.setFechaCobro(xFechaCobro);
        pagoMedioBean.setIdMedio(xIdMedio);
        

        pagoMedioBean.actualizarMedioTemporal(xIdTipoOrdenPagoProceso, new Double(xIdDcto).doubleValue());
        


        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        pagoBean.setIdDcto(xIdDcto);
        pagoBean.setIndicador(xIndicador);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdLocal(xIdLocal);
        

        fachadaPagoBean = pagoBean.listaUnPagoDctoFCH();
        

        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        

        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();
        

        usuarioBean.setIdUsuario(fachadaPagoBean.getIdVendedor());
        

        fachadaUsuarioBean = usuarioBean.listaUsuario();
        

        usuarioBean.setIdUsuario(xIdUsuario);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);
        

        return "/jsp/vtaFrmLiqPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("traeMedio") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIndicador = request.getParameter("xIndicador");
        String xIdDcto = request.getParameter("xIdDcto");
        



        PagoBean pagoBean = new PagoBean();
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        pagoBean.setIdDcto(xIdDcto);
        pagoBean.setIndicador(xIndicador);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdLocal(xIdLocal);
        

        fachadaPagoBean = pagoBean.listaUnPagoDctoFCH();
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
        fachadaTerceroBean.setIdCliente(fachadaPagoBean.getNitCC());
        fachadaTerceroBean.setIdLocal(xIdLocal);
        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        

        request.setAttribute("fachadaAgendaLogVisitaBean", fachadaAgendaLogVisitaBean);
        
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        

        return "/jsp/vtaFrmModPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("retiraDcto") == 0)
      {
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIndicador = request.getParameter("xIndicador");
        String xIdDcto = request.getParameter("xIdDcto");
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        PagoBean pagoBean = new PagoBean();
        

        PagoMedioBean pagoMedioBean = new PagoMedioBean();
        

        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoMedioBean.setIndicador(xIndicador);
        pagoMedioBean.setIdLog(xIdLogActual);
        

        pagoMedioBean.retiraPagoDctoTemporal(new Integer(xIdDcto).intValue());
        


        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIndicador(xIndicador);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdDcto(xIdDcto);
        pagoBean.setIdLog(xIdLogActual);
        

        pagoBean.retiraPagoDctoTemporal();
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        fachadaPagoBean.setIdLog(xIdLogActual);
        

        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();
        

        usuarioBean.setIdUsuario(xIdVendedor);
        

        fachadaUsuarioBean = usuarioBean.listaUsuario();
        

        usuarioBean.setIdUsuario(xIdUsuario);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);
        

        return "/jsp/vtaFrmLiqPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("Cambiar Proveedor") == 0)
      {
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        fachadaPagoBean.setIdLog(xIdLogActual);
        

        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();
        

        usuarioBean.setIdUsuario(xIdVendedor);
        

        fachadaUsuarioBean = usuarioBean.listaUsuario();
        

        usuarioBean.setIdUsuario(xIdUsuario);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);
        

        return "/jsp/vtaFrmLiqPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("Pagar") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        String xObservacion = request.getParameter("xObservacion");
        

        Validacion validacion = new Validacion();
        

        validacion.reasignar("NOTA ", xIdUsuario);
        

        validacion.validarCampoString();
        if (!validacion.isValido())
        {
          request.setAttribute("validacion", validacion);
          

          return "/jsp/gralError.jsp";
        }
        DctoBean dctoBean = new DctoBean();
        

        PagoBean pagoBean = new PagoBean();
        

        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        

        int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;
        

        pagoBean.setIdLocal(xIdLocal);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdLog(xIdLogActual);
        

        Vector vecPagoTmp = pagoBean.listaPagoProceso();
        

        Iterator iteratorBean = vecPagoTmp.iterator();
        



        boolean okPago = false;
        while (iteratorBean.hasNext())
        {
          FachadaPagoBean fachadaPagoBean = (FachadaPagoBean)iteratorBean.next();
          

          int xIdReciboOld = fachadaPagoBean.getIdRecibo();
          int xIndicador = fachadaPagoBean.getIndicador();
          

          dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
          dctoBean.setIndicador(xIndicador);
          dctoBean.setIdTipoOrden(xIdTipoOrdenVenta);
          dctoBean.setIdLocal(xIdLocal);
          dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
          dctoBean.setIdDctoNitCC(fachadaPagoBean.getIdDctoNitCC());
          dctoBean.setVrPago(fachadaPagoBean.getVrPago());
          dctoBean.setVrRteFuente(fachadaPagoBean.getVrRteFuente());
          dctoBean.setVrDsctoFcro(fachadaPagoBean.getVrDescuento());
          dctoBean.setVrRteIva(fachadaPagoBean.getVrRteIva());
          dctoBean.setVrRteIca(fachadaPagoBean.getVrRteIca());
          dctoBean.setVrRteCree(fachadaPagoBean.getVrRteCree());
          

          okPago = dctoBean.actualizaPago();
          

          pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
          pagoBean.setIdLocal(xIdLocal);
          pagoBean.setIndicador(fachadaPagoBean.getIndicador());
          

          int xIdReciboMAX = pagoBean.maximoReciboIdLocalxIndicador() + 1;
          


          pagoBean.setIdPlanilla(idMaximaPlanilla);
          pagoBean.setIdLog(xIdLogActual);
          pagoBean.setIdDcto(fachadaPagoBean.getIdDcto());
          pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
          pagoBean.setIdLocal(xIdLocal);
          pagoBean.setIndicador(fachadaPagoBean.getIndicador());
          pagoBean.setIdRecibo(xIdReciboMAX);
          pagoBean.setIdVendedor(xIdVendedor);
          

          pagoBean.setIdLocal(xIdLocal);
          pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
          pagoBean.setIdLog(xIdLogActual);
          pagoBean.setIdRecibo(xIdReciboOld);
          

          pagoBean.ingresaPago(xIdTipoOrdenVenta, xIdReciboMAX, xIndicador, fachadaPagoBean.getIdDcto(), xIdVendedor, fachadaPagoBean.getVrPago(), idMaximaPlanilla, fachadaPagoBean.getIdDctoNitCC());
          








          pagoBean.setIdLocal(xIdLocal);
          pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
          pagoBean.setIdRecibo(xIdReciboMAX);
          pagoBean.setIndicador(xIndicador);
          pagoBean.setObservacion(xObservacion);
          

          pagoBean.actualizaObservacion();
          

          FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();
          
          ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();
          

          colaboraDctoBean.setIdLocal(fachadaPagoBean.getIdLocal());
          colaboraDctoBean.setIdCliente(fachadaPagoBean.getNitCC());
          colaboraDctoBean.setIdTipoOrden(xIdTipoOrdenVenta);
          colaboraDctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
          





          fachadaDctoPagoBean = colaboraDctoBean.listaCuentaDetalladoClienteFCH();
          


          double xVSaldoDctoActualizado = fachadaDctoPagoBean.getVrSaldo();
          


          pagoBean.setVrSaldo(xVSaldoDctoActualizado);
          

          pagoBean.setIdLocal(xIdLocal);
          pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
          pagoBean.setIdLog(xIdLogActual);
          pagoBean.setIdRecibo(xIdReciboMAX);
          pagoBean.setIndicador(xIndicador);
          

          pagoBean.actualizaSaldo();
          


          PagoMedioBean pagoMedioBean = new PagoMedioBean();
          

          pagoMedioBean.setIdLocal(xIdLocal);
          pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
          pagoMedioBean.setIdLog(xIdLogActual);
          pagoMedioBean.setIdRecibo(xIdReciboOld);
          

          pagoMedioBean.ingresaPago(xIdTipoOrdenVenta, xIdReciboMAX, xIndicador, fachadaPagoBean.getIdDcto());
          




          pagoMedioBean.setIdRecibo(xIdReciboOld);
          pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
          pagoMedioBean.setIndicador(xIndicador);
          pagoMedioBean.setIdLocal(xIdLocal);
          

          pagoMedioBean.retiraReciboTemporal();
          

          pagoBean.setIdRecibo(xIdReciboOld);
          pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
          pagoMedioBean.setIndicador(xIndicador);
          pagoMedioBean.setIdLocal(xIdLocal);
          

          pagoBean.retiraReciboTemporal();
        }
        if (okPago)
        {
          agendaLogVisitaBean.setIdLog(xIdLogActual);
          agendaLogVisitaBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
          
          agendaLogVisitaBean.setIdUsuario(fachadaAgendaLogVisitaBean.getIdUsuario());
          
          agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenVenta);
          agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
          agendaLogVisitaBean.setIdLocal(xIdLocal);
          
          agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
          agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
          agendaLogVisitaBean.setEstado(estadoProgramado);
          

          ProcesoIp procesoIp = new ProcesoIp();
          

          String ipTx = procesoIp.getIpTx(request);
          
          agendaLogVisitaBean.setIpTx(ipTx);
          agendaLogVisitaBean.setFechaTx(fechaTxHM);
          

          boolean okLog = agendaLogVisitaBean.finalizaVisita();
          

          GeneraPDFPagoPlanillaVendedor generaPDF = new GeneraPDFPagoPlanillaVendedor();
          


          generaPDF.setIdLocal(xIdLocalUsuario);
          generaPDF.setIdTipoOrden(xIdTipoOrdenVenta);
          generaPDF.setIdPlanilla(idMaximaPlanilla);
          generaPDF.setTerceroReporte("PROVEEDOR  ");
          generaPDF.setTituloReporte("COMPROBANTE DE EGRESO # ");
          generaPDF.setNombreReporte("VtasRepCxPPagoPlanillaVendedor");
          

          generaPDF.generaPdf(request, response);
        }
      }
      if (accionContenedor.compareTo("PagoTotal") == 0)
      {
        double xCero = 0.0D;
        

        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIndicador = request.getParameter("xIndicador");
        String xIdDcto = request.getParameter("xIdDcto");
        String xIdLocal = request.getParameter("xIdLocal");
        xIdCliente = request.getParameter("xIdCliente");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        DctoBean dctoBean = new DctoBean();
        

        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();
        


        PagoBean pagoBean = new PagoBean();
        

        pagoBean.setIdLocal(xIdLocalUsuario);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        

        int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;
        

        dctoBean.setIdDcto(xIdDcto);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setIdTipoOrden(xIdTipoOrden);
        dctoBean.setIdLocal(xIdLocal);
        

        fachadaDctoBean = dctoBean.listaUnDcto();
        

        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();
        

        colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
        colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
        colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());
        

        FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();
        

        fachadaDctoPagoBean = colaboraDctoBean.listaCuentaDetalladoClienteFCH();
        


        double xVSaldoDctoActualizado = fachadaDctoPagoBean.getVrSaldo();
        

        PagoMedioBean pagoMedioBean = new PagoMedioBean();
        

        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoMedioBean.setIndicador(xIndicador);
        pagoMedioBean.setIdLog(xIdLogActual);
        

        pagoMedioBean.retiraPagoDctoTemporal(new Integer(xIdDcto).intValue());
        


        pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        pagoBean.setIndicador(fachadaDctoBean.getIndicador());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdDcto(fachadaDctoBean.getIdDcto());
        pagoBean.setIdLog(xIdLogActual);
        

        pagoBean.retiraPagoDctoTemporal();
        

        int xIdReciboMAX = pagoBean.maximoReciboIdLocalxIndicador() + 1;
        


        pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdRecibo(xIdReciboMAX);
        pagoBean.setIndicador(fachadaDctoBean.getIndicador());
        pagoBean.setFechaPago(xFechaPago);
        pagoBean.setVrPago(xVSaldoDctoActualizado);
        pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
        pagoBean.setEstado(estadoActivo);
        pagoBean.setIdUsuario(xIdUsuario);
        pagoBean.setVrRteFuente(xCero);
        pagoBean.setVrDescuento(xCero);
        pagoBean.setVrRteIva(xCero);
        pagoBean.setIdDcto(xIdDcto);
        pagoBean.setIdDctoNitCC(fachadaDctoBean.getIdDctoNitCC());
        pagoBean.setIdPlanilla(idMaximaPlanilla);
        pagoBean.setVrSaldo(xVSaldoDctoActualizado);
        pagoBean.setIdLog(xIdLogActual);
        pagoBean.setIdVendedor(xIdVendedor);
        

        boolean okIngresaPago = pagoBean.ingresaPago();
        

        int xEstadoOk = 1;
       
        String xIdDctoMedioOk = "";
   
        int xIdTransferencia = 3;
        int xBancoDefecto = 07;
        

        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoMedioBean.setIdRecibo(xIdReciboMAX);
        pagoMedioBean.setIndicador(fachadaDctoBean.getIndicador());
        pagoMedioBean.setVrMedio(xVSaldoDctoActualizado);
        pagoMedioBean.setEstado(xEstadoOk);
        pagoMedioBean.setIdBanco(xBancoDefecto);
        pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
        pagoMedioBean.setFechaCobro(xFechaPago);
        pagoMedioBean.setIdMedio(xIdTransferencia);
        pagoMedioBean.setIdLog(xIdLogActual);
        

        pagoMedioBean.ingresar();
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
        fachadaTerceroBean.setIdCliente(fachadaDctoBean.getIdCliente());
        fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean = pagoBean.listaUnPagoFCH();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        fachadaPagoBean.setNitCC(xIdCliente);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        

        return "/jsp/vtaFrmIngPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("Guardar") == 0)
      {
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIndicador = request.getParameter("xIndicador");
        String xIdDcto = request.getParameter("xIdDcto");
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        String xVrPago = request.getParameter("xVrPago");
        String xVrDescuento = request.getParameter("xVrDescuento");
        String xVrRteFuente = request.getParameter("xVrRteFuente");
        String xVrRteIva = request.getParameter("xVrRteIva");
        String xVrRteIca = request.getParameter("xVrRteIca");
        String xVrRteCree = request.getParameter("xVrRteCree");
        

        Validacion valida = new Validacion();
        

        valida.reasignar("VrPago", xVrPago);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VrDescuento", xVrDescuento);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VrRteFuente", xVrRteFuente);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VrRteIva", xVrRteIva);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VrRteIca", xVrRteIca);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("VR.RTECREE", xVrRteCree);
        

        valida.validarCampoDoublePositivo();
        if (!valida.isValido())
        {
          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        DctoBean dctoBean = new DctoBean();
        

        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();
        

        dctoBean.setIdDcto(xIdDcto);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setIdTipoOrden(xIdTipoOrden);
        dctoBean.setIdLocal(xIdLocal);
        

        fachadaDctoBean = dctoBean.listaUnDcto();
        

        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();
        
        colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
        colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
        colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());
        

        FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();
        

        fachadaDctoPagoBean = colaboraDctoBean.listaCuentaDetalladoClienteFCH();
        


        double xVSaldoDctoActualizado = fachadaDctoPagoBean.getVrSaldo();
        if (xVSaldoDctoActualizado < new Double(xVrPago).doubleValue() + new Double(xVrDescuento).doubleValue() + new Double(xVrRteFuente).doubleValue() + new Double(xVrRteIva).doubleValue() + new Double(xVrRteIca).doubleValue() + new Double(xVrRteCree).doubleValue())
        {
          valida.reasignar("EXCEDE VALOR A PAGAR", "");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        PagoBean pagoBean = new PagoBean();
        

        pagoBean.setIdLocal(xIdLocalUsuario);
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        

        int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;
        

        dctoBean.setIdDcto(xIdDcto);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setIdTipoOrden(xIdTipoOrden);
        dctoBean.setIdLocal(xIdLocal);
        

        fachadaDctoBean = dctoBean.listaUnDcto();
        

        PagoMedioBean pagoMedioBean = new PagoMedioBean();
        

        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoMedioBean.setIndicador(xIndicador);
        pagoMedioBean.setIdLog(xIdLogActual);
        

        pagoMedioBean.retiraPagoDctoTemporal(new Integer(xIdDcto).intValue());
        


        pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        pagoBean.setIndicador(fachadaDctoBean.getIndicador());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdDcto(fachadaDctoBean.getIdDcto());
        pagoBean.setIdLog(xIdLogActual);
        

        pagoBean.retiraPagoDctoTemporal();
        

        int xIdReciboMAX = pagoBean.maximoReciboIdLocalxIndicador() + 1;
        


        pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoBean.setIdRecibo(xIdReciboMAX);
        pagoBean.setIndicador(fachadaDctoBean.getIndicador());
        pagoBean.setFechaPago(xFechaPago);
        pagoBean.setVrPago(xVrPago);
        pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
        pagoBean.setEstado(estadoActivo);
        pagoBean.setIdUsuario(xIdUsuario);
        pagoBean.setVrRteFuente(xVrRteFuente);
        pagoBean.setVrDescuento(xVrDescuento);
        pagoBean.setVrRteIva(xVrRteIva);
        pagoBean.setVrRteIca(xVrRteIca);
        pagoBean.setIdDcto(xIdDcto);
        pagoBean.setIdDctoNitCC(fachadaDctoBean.getIdDctoNitCC());
        pagoBean.setIdPlanilla(idMaximaPlanilla);
        pagoBean.setVrSaldo(xVSaldoDctoActualizado);
        pagoBean.setIdLog(xIdLogActual);
        pagoBean.setIdReciboCruce(0);
        pagoBean.setIdVendedor(xIdVendedor);
        pagoBean.setVrRteCree(xVrRteCree);
        

        boolean okIngresaPago = pagoBean.ingresaPago();
        

        int xEstadoOk = 1;
        int xIdBancoOk =7;
        String xIdDctoMedioOk = "";
        int xIdTransferencia =3;
        

        pagoMedioBean.setIdLocal(xIdLocal);
        pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        pagoMedioBean.setIdRecibo(xIdReciboMAX);
        pagoMedioBean.setIndicador(fachadaDctoBean.getIndicador());
        pagoMedioBean.setVrMedio(xVrPago);
        pagoMedioBean.setEstado(xEstadoOk);
        pagoMedioBean.setIdBanco(xIdBancoOk);
        pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
        pagoMedioBean.setFechaCobro(strFechaVisita);
        pagoMedioBean.setIdMedio(xIdTransferencia);
        pagoMedioBean.setIdLog(xIdLogActual);
        

        pagoMedioBean.ingresar();
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
        fachadaTerceroBean.setIdCliente(fachadaDctoBean.getIdCliente());
        fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        

        pagoBean.setIdLocal(xIdLocalUsuario);
        pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean = pagoBean.listaUnPagoFCH();
        


        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        fachadaPagoBean.setNitCC(xIdCliente);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        

        return "/jsp/vtaFrmIngPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("PagoParcial") == 0)
      {
        String xIdLocal = request.getParameter("xIdLocal");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");
        String xIndicador = request.getParameter("xIndicador");
        String xIdDcto = request.getParameter("xIdDcto");
        xIdCliente = request.getParameter("xIdCliente");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
        fachadaTerceroBean.setIdCliente(xIdCliente);
        fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        

        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();
        

        DctoBean dctoBean = new DctoBean();
        
        dctoBean.setIdDcto(xIdDcto);
        dctoBean.setIndicador(xIndicador);
        dctoBean.setIdTipoOrden(xIdTipoOrden);
        dctoBean.setIdLocal(xIdLocal);
        

        fachadaDctoBean = dctoBean.listaUnDcto();
        

        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();
        

        colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
        colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
        colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
        
        colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());
        

        FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();
        

        fachadaDctoPagoBean = colaboraDctoBean.listaCuentaDetalladoClienteFCH();
        


        PagoBean pagoBean = new PagoBean();
        

        pagoBean.setIdLocal(xIdLocalUsuario);
        pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean = pagoBean.listaUnPagoFCH();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        fachadaPagoBean.setNitCC(xIdCliente);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        request.setAttribute("fachadaDctoBean", fachadaDctoBean);
        request.setAttribute("fachadaDctoPagoBean", fachadaDctoPagoBean);
        

        return "/jsp/vtaFrmIngPagoCxPPlanillaParcial.jsp";
      }
      if (accionContenedor.compareTo("Confirmar") == 0)
      {
        xIdCliente = request.getParameter("xIdCliente");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        TerceroBean terceroBean = new TerceroBean();
        

        terceroBean.setIdCliente(xIdCliente);
        terceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean = terceroBean.listaUnTerceroFCH();
        

        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaTerceroBean.setIdCliente(xIdCliente);
        fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
        

        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        

        PagoBean pagoBean = new PagoBean();
        

        pagoBean.setIdLocal(xIdLocalUsuario);
        pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
        pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean = pagoBean.listaUnPagoFCH();
        


        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        fachadaPagoBean.setNitCC(xIdCliente);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        

        return "/jsp/vtaFrmIngPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("Buscar Proveedor") == 0)
      {
        String xNombreTercero = request.getParameter("xNombreTercero");
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
        

        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        

        fachadaAgendaLogVisitaBean = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();
        if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {
          fachadaAgendaLogVisitaBean.setIdCliente(xIdCliente);
        }
        fachadaAgendaLogVisitaBean.setIdUsuario(xIdUsuario);
        

        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        fachadaTerceroBean.setNombreTercero(xNombreTercero);
        


        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        

        usuarioBean.setIdUsuario(xIdUsuario);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        

        return "/jsp/vtaFrmSelPagoCxPPlanilla.jsp";
      }
      if (accionContenedor.compareTo("Registrar") == 0)
      {
        String xIdVendedor = request.getParameter("xIdVendedor");
        String xFechaPago = request.getParameter("xFechaPago");
        

        Validacion valida = new Validacion();
        

        valida.reasignar("VENDEDOR", xIdVendedor);
        

        valida.validarCampoDouble();
        if (!valida.isValido())
        {
          valida.setDescripcionError("ERROR, EL VENDEDOR NO EXISTE");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        valida.reasignar("FECHA PAGO", xFechaPago);
        

        valida.validarCampoFecha();
        if (!valida.isValido())
        {
          valida.setDescripcionError("ERROR, FECHA PAGO CON FORMATO INCORRECTO");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        int idPeriodo = 200611;
        int estadoAtendido = 1;
        int estadoProgramada = 9;
        int idEstadoVisita = 1;
        


        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setEstado(estadoProgramada);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        

        boolean okLogOcupado = agendaLogVisitaBean.validaLogOcupado();
        if (okLogOcupado)
        {
          valida.reasignar("EXISTE UN PEDIDO O PAGO EN PROCESO", "");
          

          valida.setDescripcionError("DEBE TERMINAR O CANCELAR LO ACTIVO");
          

          request.setAttribute("validacion", valida);
          return "/jsp/gralError.jsp";
        }
        int idLog = agendaLogVisitaBean.maximoIdLog() + 1;
        

        agendaLogVisitaBean.setIdCliente(xIdVendedor);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);
        agendaLogVisitaBean.setIdPeriodo(idPeriodo);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        agendaLogVisitaBean.setIdLog(idLog);
        agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
        agendaLogVisitaBean.setEstado(estadoAtendido);
        

        boolean okRetirar = agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoProgramada);
        


        agendaLogVisitaBean.setEstado(estadoProgramada);
        

        boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();
        

        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();
        

        fachadaPagoBean.setFechaPago(xFechaPago);
        fachadaPagoBean.setIdLocal(xIdLocalUsuario);
        fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
        fachadaPagoBean.setIdVendedor(xIdVendedor);
        

        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();
        

        usuarioBean.setIdUsuario(xIdVendedor);
        

        fachadaUsuarioBean = usuarioBean.listaUsuario();
        

        usuarioBean.setIdUsuario(xIdUsuario);
        

        request.setAttribute("fachadaPagoBean", fachadaPagoBean);
        request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);
        

        return "/jsp/vtaFrmLiqPagoCxPPlanilla.jsp";
      }
    }
    return "/jsp/empty.htm";
  }
}
