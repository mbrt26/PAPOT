
package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.negocio.DctoCuadreBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoCuadre;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

import com.solucionesweb.losbeans.utilidades.Day;

//
import com.solucionesweb.losbeans.utilidades.Validacion;
/** 
 * el sistema muestra la interfaz  con un reporte de cuadres y su estado/
 * vtaContenedorCierreCuadreCaja.jsp/
 *
 *  Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmCierreCuadreCajaManejadorRequest
                                                 implements GralManejadorRequest
{
   /**
     * BUTTON--
     * ("Regresar")-Permite retornar al menu principal /
     * ("Cierre")-permite cerrar cuadres de caja/
     * ("Listar")-Permite ver un reporte de cuadres de caja /
     * ("Apertura")-Permite abrir cuadres de caja /
     * 
     * Metodo contructor por defecto, es decir, sin parametros  /
     */
  public VtasAdmCierreCuadreCajaManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "Fecha Cuadre"-Ingreso de fecha  de cuadre /
     * 
     */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException   {

    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor );

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario               = usuarioBean.getIdUsuarioStr();

    //


    Day fechaCuadre                = new Day();
    String fechaCuadreStr           = fechaCuadre.getFechaFormateada();

    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();
    int xIndicadorInicial          = usuarioBean.getIndicadorInicial();
    int xIndicadorFinal            = usuarioBean.getIndicadorFinal();



    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Regresar") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        // Cerrar caja
	    if (accionContenedor.compareTo("Cierre") == 0 ) {

            //
            String xFechaCierre        = request.getParameter("xFechaInicial");
            String xIndicadorMostrador =
                                    request.getParameter("xIndicadorMostrador");

            int indicador= new Integer(xIndicadorMostrador).intValue();

            int xCajaAbierta = 2;
  
            DctoCuadreBean cuadreBean = new DctoCuadreBean();

            FachadaDctoCuadre fachadaDctoCuadre = new FachadaDctoCuadre();
            
            cuadreBean.setIdLocal(xIdLocalUsuario);
            cuadreBean.setFechaCuadre(xFechaCierre);
            cuadreBean.setIdUsuario(idUsuario);
            cuadreBean.setIndicador(indicador);
            cuadreBean.setFechaOperacion(fechaCuadreStr);
            cuadreBean.setFechaComprobante(xFechaCierre);

            fachadaDctoCuadre = cuadreBean.estadoCuadre();

            int estado = fachadaDctoCuadre.getEstadoCuadre();



            if(estado != xCajaAbierta){

            cuadreBean.cierreCaja();

            }else{

            cuadreBean.actualizaCaja();
            }

          
             return "/jsp/empty.htm";
        }
	}

    //Abrir caja
     if (accionContenedor.compareTo("Apertura") == 0 ) {

            String xFechaCierre        = request.getParameter("xFechaInicial");
            String xIndicadorMostrador =
                                    request.getParameter("xIndicadorMostrador");

            int indicador= new Integer(xIndicadorMostrador).intValue();

            DctoCuadreBean cuadreBean = new DctoCuadreBean();
            
            cuadreBean.setFechaCuadre(xFechaCierre);
            cuadreBean.setIndicador(indicador);
            
            cuadreBean.aperturaCaja();


            return "/jsp/empty.htm";
     }
    
    
//Listar
      if (accionContenedor.compareTo("Listar") == 0 ) {


          //
                FachadaDctoCuadre fachadaDctoCuadre
                                              = new FachadaDctoCuadre();

                //
                fachadaDctoCuadre.setIndicadorInicial(xIndicadorInicial);
                fachadaDctoCuadre.setIndicadorFinal(xIndicadorFinal);
                

                //
                request.setAttribute("fachadaDctoCuadre",
                        fachadaDctoCuadre);

                //
                return "/jsp/vtaFrmLstCierreCaja.jsp";
       	    

        }


    //
    return "/jsp/empty.htm";

  }
}