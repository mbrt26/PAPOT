

package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
import java.util.logging.Level;
import java.util.logging.Logger;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.negocio.TipoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;



// Importa la clase que contiene el ProcesoIngreso
import com.solucionesweb.lasayudas.ProcesoIngresoNotaComprobante;
import com.solucionesweb.lasayudas.ProcesoGuardaNotaSubcuenta;


// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.lasayudas.ProcesoIngresoComprobante;
import com.solucionesweb.lasayudas.ProcesoIp;



// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

//Importa el bean de utilidades
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;


import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.JhDate;
import com.solucionesweb.losbeans.utilidades.Validacion;

/**
 * Permite ver reportes de comprobantes de ingreso y egreso /
 * vtaContenedorEmpresaRevIngreso.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmCuadreCajaRevIngresoManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-retorna al menu principal /
     * ("Consultar")-Permite ver un reporte /
     * ("Confirmar")-permite reversar ingreso /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmCuadreCajaRevIngresoManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Fecha Inicial"-ingreso fecha inicial  para ver el reporte /
     * "Fecha final"-ingreso fecha limite para ver un reporte /
     * 
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        String accionContenedor = request.getParameter("accionContenedor");


 //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

         //
        JhDate jhDate = new JhDate();
        //
        String fechaTxHM = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmContableComprobanteManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //
        int xEstadoActivo = 9;
        int xEstadoSuspendido = 8;

        //
        int idPeriodo = 200611;
        int estadoAtendido = 1; // visitaActiva
        int estadoProgramada = 9; // visitaProgramada
        int idEstadoVisita = 1; // Programada



        //---
       int xSignoOperacionNota = -1;


        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
        String xIdUsuario = usuarioBean.getIdUsuarioSf0();

         //
        String idUsuario               = usuarioBean.getIdUsuarioStr();
        // int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {


            // Regresar
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

             // Salir
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            
              // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {


                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdLog = request.getParameter("xIdLog");
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdDcto      = request.getParameter("xIdDcto");
                String xIndicador   = request.getParameter("xIndicador");
                String xIdOrden     = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xVrUnitario = request.getParameter("xVrUnitario");
                String xIdSubcuenta   = request.getParameter("xIdSubcuenta");
                String xDescripcion = request.getParameter("xDescripcion");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");
                String xIdAlcance  = request.getParameter("xIdAlcance");

                //
                int xIdTipoOrdenNota = new Integer(xIdTipoOrden).intValue();
                

                int xIdTipoOrdenProceso = new Integer(xIdTipoOrden).intValue() + 5000;



                 //
                int xIdLogProceso = new Integer(xIdLog).intValue();

                int xIdLogActual =
                       agendaLogVisitaBean.maximoIdLog()+1;
                
                 //
            agendaLogVisitaBean.setIdUsuario(xIdUsuario);
            agendaLogVisitaBean.setIdCliente(xIdTercero);
            agendaLogVisitaBean.setIdPeriodo(idPeriodo);
            agendaLogVisitaBean.setFechaVisita(strFechaVisita);
            agendaLogVisitaBean.setIdLog(xIdLogActual);
            agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
            agendaLogVisitaBean.setEstado(estadoAtendido);

             agendaLogVisitaBean.setEstado(estadoProgramada);

            //
            boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

                 // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();
                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNota);

                // Valida si existe una nota previa
                boolean existePedido = dctoOrdenBean.existePedido();

                if (existePedido) {

                    //
                    validacion.reasignar("REVERSION", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("REVERSION YA CONFIRMADA");
                    validacion.setSolucion("Iniciar REVERSION");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }


                //
                ProcesoGuardaNotaSubcuenta procesoGuardaNotaSubcuenta = new ProcesoGuardaNotaSubcuenta();

                    //
                    if (new Double(xVrUnitario).doubleValue() >= 0) {

                        //
                        procesoGuardaNotaSubcuenta.guarda(xIdLogProceso,
                                xIdLogActual,
                                xIdSubcuenta,
                                xVrUnitario,
                                xIdTipoOrdenProceso,
                                xIdUsuario,
                                xIdLocalUsuario,
                                xIdTercero,
                                xFechaCorte,
                                xSignoOperacionNota);
                    }
             

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoNotaComprobante proceso = new ProcesoIngresoNotaComprobante();

                //
                fachadaDctoBean = proceso.ingresa(
                        new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenNota,
                        xIdLogActual,
                        xIdTipoOrdenProceso,
                        xFechaCorte,
                        xDescripcion,
                        xIdDctoNitCC,
                        new Integer(xIdAlcance).intValue(),
                        new Integer(xIndicador).intValue(),
                        xIdOrden);

                //
                int xIdEstadoTxSinTx = 1;
                int tareaVisita = 6;
                int estadoTerminado = 1;

                // finaliza
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                            fachadaDctoBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setIdLocal(fachadaDctoBean.getIdLocalStr());
                agendaLogVisitaBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
                agendaLogVisitaBean.setEstado(estadoTerminado);

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String ipTx = procesoIp.getIpTx(request);

                //
                agendaLogVisitaBean.setIpTx(ipTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finaliza();


                 //
                GeneraPDFNotaComprobante generaPDF = new GeneraPDFNotaComprobante();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("COMPROBANTE");
                generaPDF.setNombreReporte("RepEmpresaComprobante");

                //
                generaPDF.generaPdf(request, response);

               return "/jsp/empty.htm";
  
            }



             // detalla Comprobante
            if (accionContenedor.compareTo("detallarComprobante") == 0) {
                      

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdAlcance = request.getParameter("xIdAlcance");


                //Dcto ref, fecha Dcto, Tercero, vr comprobante
                DctoBean dctoBean = new DctoBean();

                dctoBean.setIdOrden(xIdOrden);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                fachadaDctoBean = dctoBean.listaUnDctoOrdenSinIndicador();


                //Descripcion
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                dctoOrdenBean.setIdOrden(xIdOrden);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenBean.setIdLocal(xIdLocal);

                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                
                fachadaDctoOrdenBean= dctoOrdenBean.listaDctoOrden();
                

                //Subcuenta
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();
                
                dctoOrdenDetalleBean.setIdOrden(xIdOrden);
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();
                 
                fachadaDctoOrdenDetalleBean = dctoOrdenDetalleBean.listaUnDctoDetalle();
               

                //Concepto
                TipoOrdenBean tipoOrdenBean = new TipoOrdenBean();
                tipoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                
                FachadaTipoOrden fachadaTipoOrden = new FachadaTipoOrden();
                fachadaTipoOrden = tipoOrdenBean.listaUnFCH();


                //FachadaTipoOrdenSubcuenta fachadaTipoOrdenSubcuenta = new FachadaTipoOrdenSubcuenta();
                //fachadaTipoOrdenSubcuenta.setIdAlcance(xIdAlcance);

                //
               
                request.setAttribute("fachadaTipoOrden",
                        fachadaTipoOrden);
                request.setAttribute("fachadaDctoBean",
                        fachadaDctoBean);
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);
                 


               

                    //
                    return "/jsp/vtaFrmIngRevComprobante.jsp";

                
            
             

            }



            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //

                String xFechaCorte = request.getParameter("xFechaFinal");
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdAlcance  = request.getParameter("xIdAlcance");


                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA CORTE", xFechaCorte);
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaTipoOrdenSubcuenta fachadaTipoOrdenSubcuenta
                                              = new FachadaTipoOrdenSubcuenta();

                //
                fachadaTipoOrdenSubcuenta.setIdLocal(xIdLocalUsuario);
                fachadaTipoOrdenSubcuenta.setFechaDcto(xFechaCorte);
                fachadaTipoOrdenSubcuenta.setIdAlcance(xIdAlcance);
                fachadaTipoOrdenSubcuenta.setFechaInicial(xFechaInicial);
                fachadaTipoOrdenSubcuenta.setFechaFinal(xFechaFinal);


                //
                request.setAttribute("fachadaTipoOrdenSubcuenta",
                        fachadaTipoOrdenSubcuenta);

             
                //
                return "/jsp/vtaFrmLstHistoricoComprobante.jsp";

            }

        }

        return "/jsp/empty.htm";

    }
}
