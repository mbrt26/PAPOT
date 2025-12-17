/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.touch.paro;

import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.negocio.LocalCajaBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.Validacion;
import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Desarrollador
 */
public class Controller_Produccion_Touch_Paro implements GralManejadorRequest {

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String xoption = request.getParameter("xoption");
        String destino = "vista/Touch/VistaProduccionTouchParo.jsp";

        if (xoption != null) {
            if (xoption.equals("volver")) {
//
                int xIdTipoOrden = 9;
                int xIdTipoOrdenProceso = xIdTipoOrden + 50;
      

                // ------------------------------- Valida conexion IP ------------------
                String ipTx = null;

                //Aca se obtiene la Ip de donde se esta ingresando
                ProcesoIp obtieneIp = new ProcesoIp();

                //
                ipTx = obtieneIp.getIpTx(request);

                //Aca obtendo la ip de la base de datos
                FachadaLocalCaja fachadaLocalCaja = new FachadaLocalCaja();

                LocalCajaBean localCajaBean = new LocalCajaBean();

                localCajaBean.setIpLocal(ipTx);

                ///
                fachadaLocalCaja = localCajaBean.listaFCH();

                //--
                if (fachadaLocalCaja.getIpLocal() == null) {

                    // validacion
                    Validacion validacion = new Validacion();

                    validacion.setDescripcionError("OPERACION NO AUTORIZADA DESDE CONEXION EXTERNA");

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                Day day = new Day();
                String strFechaVisita = day.getFechaFormateada();

                //
                HttpSession sesion = request.getSession();
                UsuarioBean usuarioBean
                        = (UsuarioBean) sesion.getAttribute("usuarioBean");

                //
                String idUsuario = usuarioBean.getIdUsuarioStr();
                int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

                //
                int xIdTipoTercero = 1;
                int xEstadoSuspendido = 8;

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdLocal(xIdLocalUsuario);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrdenProceso);
                fachadaPluFicha.setFechaPrograma(strFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

            }
            destino = "/jsp/vtaContenedorOTProductoTouch.jsp";
        }
        return destino;
    }
}
