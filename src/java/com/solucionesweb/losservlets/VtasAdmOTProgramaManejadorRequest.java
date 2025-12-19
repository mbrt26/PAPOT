package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaJobProgramaPlusFicha;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.negocio.JobProgramaPlusFichaBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Validacion;
import com.solucionesweb.losbeans.utilidades.Day;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Esta ventana permite programar un orden de producción para las máquinas. /
 * vtaContenedorOTPrograma.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmOTProgramaManejadorRequest
        implements GralManejadorRequest {
/**
     * BUTTON--
     * ("Regresar")-retorna al menu principal /
     * ("Elaborar")-Permite ver un listado de produccion para seleccionar /
     * ("Listar")-Genera pdf de programa de produccion /
     * ("Modificar")-Permite cambiar de maquina de produccion para producto /
     * ("Modificar Maquina")-uarda cambio de maquina /
     * ("Programar")-confirma seleccion de refrencia a producir /
     * ("Confirmar")-Gaurda orden de ejecuion de las maquinas programadas /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmOTProgramaManejadorRequest() { }
    /**
     * BUTTON PARAMETER--
     * "Operacion"-Seleccione operacion /
     * "Fecha Programa"- Ingrese fecha de uso de la maquina/
     * "Maquina"-Seleccione maquina para produccion /
     * "Orden de ejecucion"-ingrese orden para funcionamiento de maquina /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        double xIdUsuario = usuarioBean.getIdUsuario();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        int xIdTipoTercero  = 1;
        int xIdTipoOrdenProduccion = 59;

        //
        Day xFechaHoy = new Day();

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println(accionContenedor);

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Regresar") == 0) {

                //
                return "/jsp/empty.htm";
            }

            // Listar
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xFechaPrograma = request.getParameter("xFechaPrograma");

                //
                GeneraPDF_Programa generaPDF_Programa = new GeneraPDF_Programa();

                //
                generaPDF_Programa.setFechaPrograma(xFechaPrograma);
                generaPDF_Programa.setNombreReporte("POTRepOTPrograma");
                generaPDF_Programa.setTituloReporte("PROGRAMACION MAQUINA" +
                                                    " FECHA " +
                                                    xFechaPrograma);

                //
                generaPDF_Programa.generaPdf(request, response);

                

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdEscala = request.getParameter("xIdEscala");
                String xVrEscala = request.getParameter("xVrEscala");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xFechaPrograma = request.getParameter("xFechaPrograma");
                String xLocalTipoOrdenOrdenItemArr[] =
                        request.getParameterValues("xLocalTipoOrdenOrdenItem");
                String xIdOrdenProgramaArr[] =
                        request.getParameterValues("xIdOrdenPrograma");
                String xCantidadPendienteArr[] =
                        request.getParameterValues("xCantidadPendiente");
                String xPesoPendienteArr[] =
                        request.getParameterValues("xPesoPendiente");

                //--------------------------------------------------------------
                if (xIdOrdenProgramaArr == null) {

                    //
                    return "/jsp/empty.htm";

                }

                //
                Validacion validacion = new Validacion();

                //--------------------------------------------------------------
                for (int i = 0; i < xIdOrdenProgramaArr.length; i++) {

                    //
                    validacion.reasignar("ORDEN EJECUCION",xIdOrdenProgramaArr[i]);

                    //
                    validacion.validarCampoEnteroPositivo();

                    //
                    if (validacion.isValido() == false) {

                       // Aqui escribe el Bean de Validacion en el Request para manejar el error
                       request.setAttribute("validacion", validacion);
                       return "/jsp/gralError.jsp";

                    }
                }

                //--------------------------------------------------------------
                JobProgramaPlusFichaBean jobProgramaPlusFichaBean
                                               = new JobProgramaPlusFichaBean();

                //
                int xEstadoProgramaConfirmado  = 1;
                int xEstadoProgramaProceso     = 99;
                int xItem                      = 1;

                //---
                String strCaracter = "~";
                int intLugarUno = 0;
                int intLugarDos = 0;
                int intLugarTres = 0;
                int intLongitud = 0;
                String xIdLocal = "";
                String xIdTipoOrdenStr = "";
                String xIdOrden = "";
                String xItemPadre = "";
                int xIdOrdenMAXPrograma = 0;
                int xIdOrdenProgramaRetirado = 0;

                //--------------------------------------------------------------
                for (int i = 0; i < xLocalTipoOrdenOrdenItemArr.length; i++) {

                    //
                    intLongitud = xLocalTipoOrdenOrdenItemArr[i].length();

                    //
                    intLugarUno = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, 1);
                    intLugarDos = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, intLugarUno + 1);
                    intLugarTres = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, intLugarDos + 1);

                    //
                    xIdLocal = xLocalTipoOrdenOrdenItemArr[i].substring(
                            0, intLugarUno);
                    xIdTipoOrdenStr = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarUno + 1, intLugarDos);
                    xIdOrden = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarDos + 1, intLugarTres);
                    xItemPadre = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarTres + 1, intLongitud);
                    
                    //---
                    jobProgramaPlusFichaBean.setIdLocal(xIdLocal);
                    jobProgramaPlusFichaBean.setIdTipoOrden(xIdTipoOrdenStr);
                    jobProgramaPlusFichaBean.setIdOrden(xIdOrden);
                    jobProgramaPlusFichaBean.setIdOperacion(xIdOperacion);
                    jobProgramaPlusFichaBean.setFechaPrograma(xFechaPrograma);
                    jobProgramaPlusFichaBean.setFechaProceso(
                                                xFechaHoy.getFechaFormateada());
                    jobProgramaPlusFichaBean.setIdUsuario(xIdUsuario);
                    jobProgramaPlusFichaBean.setItemPadre(xItemPadre);                    
                    jobProgramaPlusFichaBean.setIdEscala(xIdEscala);                    
                    jobProgramaPlusFichaBean.setItem(xItem);
                    jobProgramaPlusFichaBean.setEstadoPrograma(
                                                        xEstadoProgramaProceso);
                    jobProgramaPlusFichaBean.setVrEscala(xVrEscala);
                    jobProgramaPlusFichaBean.setIdOrdenPrograma(
                                                        xIdOrdenProgramaArr[i]);
                    jobProgramaPlusFichaBean.setCantidadPedida(
                                                        xCantidadPendienteArr[i]);
                    jobProgramaPlusFichaBean.setPesoPedido(
                                                        xPesoPendienteArr[i]);

                    //
                    if (jobProgramaPlusFichaBean.getIdOrdenPrograma() ==
                                                     xIdOrdenProgramaRetirado) {

                        //--- xEstadoProgramaProceso = 99
                       jobProgramaPlusFichaBean.retiraPrograma();


                    } else {

                       //--- xEstadoProgramaProceso = 99
                       jobProgramaPlusFichaBean.actualizaPrograma();

                    }

                    
                    //--- xEstadoProgramaConfirmado = 1
                    jobProgramaPlusFichaBean.actualizaEstado(
                                                     xEstadoProgramaConfirmado);

                }

                //
                GeneraPDF_Programa generaPDF_Programa = new GeneraPDF_Programa();

                //
                generaPDF_Programa.setFechaPrograma(xFechaPrograma);
                generaPDF_Programa.setNombreReporte("POTRepOTPrograma");
                generaPDF_Programa.setTituloReporte("PROGRAMACION MAQUINA" +
                                                    " FECHA " +
                                                    xFechaPrograma);

                //
                generaPDF_Programa.generaPdf(request, response);

            }

            // Modificar Maquina
            if (accionContenedor.compareTo("Modificar Maquina") == 0) {

                //
                String xIdEscala = request.getParameter("xIdEscala");
                String xVrEscala = request.getParameter("xVrEscala");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xFechaPrograma = request.getParameter("xFechaPrograma");
                String xLocalTipoOrdenOrdenItemArr[] =
                        request.getParameterValues("xLocalTipoOrdenOrdenItem");

                //--------------------------------------------------------------
                if (xLocalTipoOrdenOrdenItemArr == null) {

                    //
                    return "/jsp/empty.htm";

                }

                //--------------------------------------------------------------
                JobProgramaPlusFichaBean jobProgramaPlusFichaBean
                                               = new JobProgramaPlusFichaBean();

                //
                int xEstadoProgramaProceso     = 99;

                //
                jobProgramaPlusFichaBean.setEstadoPrograma(
                                                        xEstadoProgramaProceso);

                //
                jobProgramaPlusFichaBean.retira();

                //
                String strCaracter = "~";
                int intLugarUno = 0;
                int intLugarDos = 0;
                int intLugarTres = 0;
                int intLongitud = 0;
                String xIdLocal = "";
                String xIdTipoOrdenStr = "";
                String xIdOrden = "";
                String xItemPadre = "";
                int xIdOrdenMAXPrograma = 0;

                //--------------------------------------------------------------
                for (int i = 0; i < xLocalTipoOrdenOrdenItemArr.length; i++) {

                    //
                    intLongitud = xLocalTipoOrdenOrdenItemArr[i].length();

                    //
                    intLugarUno = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, 1);
                    intLugarDos = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, intLugarUno + 1);
                    intLugarTres = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, intLugarDos + 1);

                    //
                    xIdLocal = xLocalTipoOrdenOrdenItemArr[i].substring(
                            0, intLugarUno);
                    xIdTipoOrdenStr = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarUno + 1, intLugarDos);
                    xIdOrden = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarDos + 1, intLugarTres);
                    xItemPadre = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarTres + 1, intLongitud);

                    //---
                    jobProgramaPlusFichaBean.setIdLocal(xIdLocal);
                    jobProgramaPlusFichaBean.setIdTipoOrden(xIdTipoOrdenStr);
                    jobProgramaPlusFichaBean.setIdOrden(xIdOrden);
                    jobProgramaPlusFichaBean.setIdOperacion(xIdOperacion);
                    jobProgramaPlusFichaBean.setFechaPrograma(xFechaPrograma);
                    jobProgramaPlusFichaBean.setFechaProceso(
                                                xFechaHoy.getFechaFormateada());
                    jobProgramaPlusFichaBean.setIdUsuario(xIdUsuario);
                    jobProgramaPlusFichaBean.setItemPadre(xItemPadre);
                    jobProgramaPlusFichaBean.setVrEscala(xVrEscala);
                    jobProgramaPlusFichaBean.setIdEscala(xIdEscala);

                    //
                    xIdOrdenMAXPrograma =
                                     jobProgramaPlusFichaBean.listaMaxima() + 1;

                    //
                    jobProgramaPlusFichaBean.setIdOrdenPrograma(
                                                           xIdOrdenMAXPrograma);

                    //
                    jobProgramaPlusFichaBean.retiraOperacionPrograma();

                    //---
                    jobProgramaPlusFichaBean.ingresa();

                    //---
                    jobProgramaPlusFichaBean.modificaMaquina();

                }

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrdenStr);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaJobProgramaPlusFicha fachadaJobProgramaPlusFicha
                                            = new FachadaJobProgramaPlusFicha();

                //
                fachadaJobProgramaPlusFicha.setFechaPrograma(xFechaPrograma);
                fachadaJobProgramaPlusFicha.setVrEscala(xVrEscala);

                //
                request.setAttribute("fachadaJobProgramaPlusFicha",
                                                   fachadaJobProgramaPlusFicha);

                //--
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                colaboraJobEscala.setIdEscala(xIdEscala);
                colaboraJobEscala.setItem(xVrEscala);

                //
                fachadaJobEscala  = colaboraJobEscala.listaEscalaItemFCH();

                //
                fachadaJobEscala  = colaboraJobEscala.listaEscalaItemFCH();

                //
                request.setAttribute("fachadaJobEscala", fachadaJobEscala);

                //
                return "/jsp/vtaFrmConOTPrograma.jsp";

            }

            // Programar
            if (accionContenedor.compareTo("Programar") == 0) {

                //
                String xIdEscala = request.getParameter("xIdEscala");
                String xVrEscala = request.getParameter("xVrEscala");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xFechaPrograma = request.getParameter("xFechaPrograma");
                String xLocalTipoOrdenOrdenItemArr[] =
                        request.getParameterValues("xLocalTipoOrdenOrdenItem");

                //--------------------------------------------------------------
                if (xLocalTipoOrdenOrdenItemArr == null) {

                    //
                    return "/jsp/empty.htm";

                }

                //--------------------------------------------------------------
                JobProgramaPlusFichaBean jobProgramaPlusFichaBean
                                               = new JobProgramaPlusFichaBean();
                
                //
                int xEstadoProgramaProceso     = 99;
                
                //
                jobProgramaPlusFichaBean.setEstadoPrograma(
                                                        xEstadoProgramaProceso);

                //
                jobProgramaPlusFichaBean.retira();

                //
                String strCaracter = "~";
                int intLugarUno = 0;
                int intLugarDos = 0;
                int intLugarTres = 0;
                int intLongitud = 0;
                String xIdLocal = "";
                String xIdTipoOrdenStr = "";
                String xIdOrden = "";
                String xItemPadre = "";
                int xIdOrdenMAXPrograma = 0;

                //--------------------------------------------------------------
                for (int i = 0; i < xLocalTipoOrdenOrdenItemArr.length; i++) {

                    //
                    intLongitud = xLocalTipoOrdenOrdenItemArr[i].length();

                    //
                    intLugarUno = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, 1);
                    intLugarDos = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, intLugarUno + 1);
                    intLugarTres = xLocalTipoOrdenOrdenItemArr[i].indexOf(
                            strCaracter, intLugarDos + 1);

                    //
                    xIdLocal = xLocalTipoOrdenOrdenItemArr[i].substring(
                            0, intLugarUno);
                    xIdTipoOrdenStr = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarUno + 1, intLugarDos);
                    xIdOrden = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarDos + 1, intLugarTres);
                    xItemPadre = xLocalTipoOrdenOrdenItemArr[i].substring(
                            intLugarTres + 1, intLongitud);

                    //---
                    jobProgramaPlusFichaBean.setIdLocal(xIdLocal);
                    jobProgramaPlusFichaBean.setIdTipoOrden(xIdTipoOrdenStr);
                    jobProgramaPlusFichaBean.setIdOrden(xIdOrden);
                    jobProgramaPlusFichaBean.setIdOperacion(xIdOperacion);
                    jobProgramaPlusFichaBean.setFechaPrograma(xFechaPrograma);
                    jobProgramaPlusFichaBean.setFechaProceso(
                                                xFechaHoy.getFechaFormateada());
                    jobProgramaPlusFichaBean.setIdUsuario(xIdUsuario);
                    jobProgramaPlusFichaBean.setItemPadre(xItemPadre);
                    jobProgramaPlusFichaBean.setVrEscala(xVrEscala);
                    jobProgramaPlusFichaBean.setIdEscala(xIdEscala);

                    //
                    xIdOrdenMAXPrograma =
                                     jobProgramaPlusFichaBean.listaMaxima() + 1;

                    //
                    jobProgramaPlusFichaBean.setIdOrdenPrograma(
                                                           xIdOrdenMAXPrograma);

                    //---
                    jobProgramaPlusFichaBean.ingresa();

                }

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrdenStr);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaJobProgramaPlusFicha fachadaJobProgramaPlusFicha
                                            = new FachadaJobProgramaPlusFicha();

                //
                fachadaJobProgramaPlusFicha.setFechaPrograma(xFechaPrograma);
                fachadaJobProgramaPlusFicha.setVrEscala(xVrEscala);

                //
                request.setAttribute("fachadaJobProgramaPlusFicha",
                                                   fachadaJobProgramaPlusFicha);

                //--
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                colaboraJobEscala.setIdEscala(xIdEscala);
                colaboraJobEscala.setItem(xVrEscala);

                //
                fachadaJobEscala  = colaboraJobEscala.listaEscalaItemFCH();

                //
                fachadaJobEscala  = colaboraJobEscala.listaEscalaItemFCH();

                //
                request.setAttribute("fachadaJobEscala", fachadaJobEscala);

                //
                return "/jsp/vtaFrmConOTPrograma.jsp";

            }

            // Modificar
            if (accionContenedor.compareTo("Modificar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xFechaPrograma = request.getParameter("xFechaPrograma");
                String xIdEscala = request.getParameter("xIdEscala");
                String xVrEscala = request.getParameter("xVrEscala");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdLocal(xIdLocalUsuario);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrdenProduccion);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaJobProgramaPlusFicha fachadaJobProgramaPlusFicha
                                            = new FachadaJobProgramaPlusFicha();

                //
                fachadaJobProgramaPlusFicha.setIdEscala(xIdEscala);
                fachadaJobProgramaPlusFicha.setVrEscala(xVrEscala);
                fachadaJobProgramaPlusFicha.setFechaPrograma(xFechaPrograma);

                //
                request.setAttribute("fachadaJobProgramaPlusFicha",
                                                   fachadaJobProgramaPlusFicha);

                //--
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                colaboraJobEscala.setIdEscala(xIdEscala);
                colaboraJobEscala.setItem(xVrEscala);

                //
                fachadaJobEscala  = colaboraJobEscala.listaEscalaItemFCH();

                //
                request.setAttribute("fachadaJobEscala", fachadaJobEscala);

                //
                return "/jsp/vtaFrmModOTPrograma.jsp";

            }


            // Elaborar
            if (accionContenedor.compareTo("Elaborar") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xFechaPrograma = request.getParameter("xFechaPrograma");
                String xIdEscala = request.getParameter("xIdEscala");
                String xVrEscala = request.getParameter("xVrEscala");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdLocal(xIdLocalUsuario);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrdenProduccion);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaJobProgramaPlusFicha fachadaJobProgramaPlusFicha
                                            = new FachadaJobProgramaPlusFicha();

                //
                fachadaJobProgramaPlusFicha.setIdEscala(xIdEscala);
                fachadaJobProgramaPlusFicha.setVrEscala(xVrEscala);
                fachadaJobProgramaPlusFicha.setFechaPrograma(xFechaPrograma);

                //
                request.setAttribute("fachadaJobProgramaPlusFicha",
                                                   fachadaJobProgramaPlusFicha);

                //--
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                colaboraJobEscala.setIdEscala(xIdEscala);
                colaboraJobEscala.setItem(xVrEscala);

                //
                fachadaJobEscala  = colaboraJobEscala.listaEscalaItemFCH();

                //
                request.setAttribute("fachadaJobEscala", fachadaJobEscala);
 
                //
                return "/jsp/vtaFrmLstOTPrograma.jsp";

            }

        }

        //
        return "/jsp/empty.htm";
    }
}
