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

//
import com.solucionesweb.lasayudas.ProcesoIp;

import com.solucionesweb.lasayudas.ProcesoIngresoComprobante;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

//
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoCuadre;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.lasayudas.ProcesoGuardaSubcuenta;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

//
import com.solucionesweb.losbeans.negocio.TipoOrdenBean;

import com.solucionesweb.losbeans.negocio.DctoCuadreBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;
/** 
 * Donde se muestran y se ingresan los egresos e ingresos/
 * vtaContenedorContableComprobante.jsp/
 *
 *  Este servlet implementa la interface GralManejadorRequest /
 */
//
public class VtasAdmContableComprobanteManejadorRequest
                                               implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-Permite retornar al menu principal /
     * ("Crear")-Permite crear un comprobante /vtaFrmIngContableComprobanteIndicador.jsp/
     * ("Listar")-Permite visualizar una lista de comprobantes contables /vtaFrmLstContableComprobante.jsp/
     * ("Legalizar")-Permite crear pdf del comprobante /vtaFrmIngContableComprobanteIndicador.jsp /
     * 
     * Metodo contructor por defecto, es decir, sin parametros  /
     */

    public VtasAdmContableComprobanteManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Concepto"- Seleccion de concepto de comprobante/
     * "Dcto Referencia"- Numero de referencia/
     * "Fecha Cuadre"- Fecha elaboracion cuadre del comprobante /
     * "Tercero"- seleccion de  tercero /
     * "Descripcion"- Detalle comprobante/
     * "V. Comprobante"- Valor del comprobante/
     * 
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        String accionContenedor = request.getParameter("accionContenedor");
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");

        //---
        int xIdTipoOrdenProceso = new Integer(xIdTipoOrden).intValue() + 50;

        //
        int xNivelConIndicador51 = 51;
        int xNivelConIndicador52 = 52;
        int xNivelConIndicador7  = 7;

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
        String xIdUsuario = usuarioBean.getIdUsuarioSf0();
        int xIdNivel = usuarioBean.getIdNivel();

        int xEstadoActivo = 9;
        int xEstadoSuspendido = 8;

        //
        int idPeriodo = 200611;
        int estadoAtendido = 1; // visitaActiva
        int estadoProgramada = 9; // visitaProgramada
        int idEstadoVisita = 1; // Programada

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
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        fachadaAgendaLogVisitaBean = agendaLogVisitaBean.listaLogActivo();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        // valida orden diferente en proceso
        boolean xOrdenDiferente =
                agendaLogVisitaBean.validaDiferenteOrdenProceso(
                xIdTipoOrdenProceso);

        //
        if (xOrdenDiferente) {

            // validacion
            Validacion validacion = new Validacion();

            //
            validacion.setDescripcionError("ERROR, DEBE TERMINAR PROCESO ACTIVO");

            //
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";

        }

        //
        if (accionContenedor.compareTo("Crear") == 0) {

            //
            int idLog = agendaLogVisitaBean.maximoIdLog() + 1;

            


            //
            agendaLogVisitaBean.setIdUsuario(xIdUsuario);
            agendaLogVisitaBean.setIdCliente(xIdUsuario);
            agendaLogVisitaBean.setIdPeriodo(idPeriodo);
            agendaLogVisitaBean.setFechaVisita(strFechaVisita);
            agendaLogVisitaBean.setIdLog(idLog);
            agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
            agendaLogVisitaBean.setEstado(estadoAtendido);


            //
            boolean okRetirar =
                    agendaLogVisitaBean.actualizaLogVisitaUsuario(
                    estadoProgramada);

            // estadoActivo = 9
            agendaLogVisitaBean.setEstado(estadoProgramada);

            //
            boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

            //
            xIdLogActual = idLog;


        } else {

            //
            agendaLogVisitaBean.setEstado(xEstadoSuspendido);
            agendaLogVisitaBean.setIdLog(xIdLogActual);

            //
            fachadaAgendaLogVisitaBean =
                    agendaLogVisitaBean.listaLogSuspendidoFCH(
                    xIdTipoOrdenProceso);

            //
            if (fachadaAgendaLogVisitaBean.getIdLog() > 0) {
                xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
            }

        }

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {


            // Regresar
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            // tomar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");

                //
                GeneraPDFComprobante generaPDF = new GeneraPDFComprobante();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(new Integer(xIdOrden).intValue());
                generaPDF.setIdTipoOrden(new Integer(xIdTipoOrden).intValue());
                generaPDF.setTituloReporte("COMPROBANTE");
                generaPDF.setNombreReporte("RepEmpresaComprobante");

                //
                generaPDF.generaPdf(request, response);

            }

            // Listar
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdAlcance = request.getParameter("xIdAlcance");

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

                //
                request.setAttribute("fachadaTipoOrdenSubcuenta",
                        fachadaTipoOrdenSubcuenta);

                //
                return "/jsp/vtaFrmLstContableComprobante.jsp";

            }


            // Legalizar
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdLog = request.getParameter("xIdLog");
                String xDescripcion = request.getParameter("xDescripcion");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");
                String xIdAlcance  = request.getParameter("xIdAlcance");
                String xIndicadorMostrador = "1";
                String xIdSubcuentaArr[]   =
                                     request.getParameterValues("xIdSubcuenta");
                String xVrUnitarioArr[] =
                        request.getParameterValues("xVrUnitario");

                String xFechaComprobante = request.getParameter("xFechaCorte");

                 //
                 DctoCuadreBean cuadreBean = new DctoCuadreBean();

                 //
                 cuadreBean.setFechaComprobante(xFechaComprobante);
                 cuadreBean.setIndicador(new Integer(xIndicadorMostrador).intValue());
                 
                 //
                 FachadaDctoCuadre fachadaCuadreBean= new FachadaDctoCuadre();

                 fachadaCuadreBean = cuadreBean.estadoCuadre();
                 int xEstadoCuadre = fachadaCuadreBean.getEstadoCuadre();

                 Validacion validacion = new Validacion();


//Valida si la caja está cerrada (estadoCuadre = 1)
                         
                 
    
                 if (xEstadoCuadre==1){

                    validacion.setValido(false);
		    validacion.setNombreCampo("ESTADO CUADRE");
		    validacion.setDescripcionError("La caja se encuentra cerrada");
		    validacion.setSolucion("Seleccione la opción de apertura de caja");

                    request.setAttribute("validacion", validacion);
   		    return "/jsp/gralError.jsp";
                 
                      }
                   validacion.setValido(true);
                   validacion.setCodigoError("noError");
                 

                 

             
                // validacion  

                if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                for (int indice = 0; indice
                        < xVrUnitarioArr.length; indice++) {

                    //
                    validacion.reasignar("V.COMPROBANTE",
                            xVrUnitarioArr[indice]);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                }

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaSubcuenta procesoGuardaSubcuenta = new ProcesoGuardaSubcuenta();

                //
                for (int indice = 0; indice
                        < xVrUnitarioArr.length; indice++) {

                    //
                    if (new Double(xVrUnitarioArr[indice]).doubleValue() >= 0) {

                        //
                        procesoGuardaSubcuenta.guarda(xIdLogActual,
                                xIdSubcuentaArr[indice],
                                xVrUnitarioArr[indice],
                                xIdTipoOrdenProceso,
                                xIdUsuario,
                                xIdLocalUsuario,
                                xIdTercero,
                                xFechaCorte);
                    }
                }

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoComprobante proceso = new ProcesoIngresoComprobante();

                //
                fachadaDctoBean = proceso.ingresa(
                        new Integer(xIdLocalUsuario).intValue(),
                        new Integer(xIdTipoOrden).intValue(),
                        new Integer(xIdLog).intValue(),
                        xIdTipoOrdenProceso,
                        xFechaCorte,
                        xDescripcion,
                        xIdDctoNitCC,
                        new Integer(xIdAlcance).intValue(),
                        new Integer(xIndicadorMostrador).intValue());

                //
                int xIdEstadoTxSinTx = 1;
                int tareaVisita = 6;
                int estadoTerminado = 1;

                // finaliza
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaDctoBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
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
                GeneraPDFComprobante generaPDF = new GeneraPDFComprobante();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("COMPROBANTE");
                generaPDF.setNombreReporte("RepEmpresaComprobante");

                //
                generaPDF.generaPdf(request, response);


            }

            // Crear
            if (accionContenedor.compareTo("Crear") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xDescripcion = request.getParameter("xDescripcion");
                String xIdLog = request.getParameter("xIdLog");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");
                String xIdAlcance = request.getParameter("xIdAlcance");


                //
                if ((accionContenedor.compareTo("Elaborar") == 0)) {

                    //
                    xIdLogActual = new Integer(xIdLog).intValue();

                }

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("CONCEPTO", xIdTipoOrden);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("DCTO.REF", xIdDctoNitCC);

                //
                validacion.validarCampoString();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("FECHA DCTO", xFechaCorte);

                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("TERCERO", xIdTercero);

                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("DESCRIPCION", xDescripcion);

                validacion.validarCampoString();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setObservacion(xDescripcion);
                fachadaDctoOrdenBean.setIdDctoNitCC(xIdDctoNitCC);
                fachadaDctoOrdenBean.setIdAlcance(xIdAlcance);

                //---
                FachadaTipoOrden fachadaTipoOrden = new FachadaTipoOrden();

                //
                TipoOrdenBean tipoOrdenBean = new TipoOrdenBean();

                //
                tipoOrdenBean.setIdTipoOrden(xIdTipoOrden);

                //
                fachadaTipoOrden = tipoOrdenBean.listaUnFCH();

                //---
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(xIdTercero);

                //
                fachadaTerceroBean =
                        colaboraTercero.listaUnTerceroUnionFCH();

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);
                request.setAttribute("fachadaTipoOrden",
                        fachadaTipoOrden);
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);

                //
                if ((xIdNivel == xNivelConIndicador51)
                        || (xIdNivel == xNivelConIndicador52)
                        || (xIdNivel == xNivelConIndicador7)) {

                    //
                    return "/jsp/vtaFrmIngContableComprobanteIndicador.jsp";

                } else {

                    //
                    return "/jsp/vtaFrmIngContableComprobante.jsp";

                }
            }
        }

        return "/jsp/empty.htm";

    }
}
