package com.solucionesweb.controller.programacion;

import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaJobProgramaPlusFicha;
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;
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


public class ControlProgramacionOTProduccion implements GralManejadorRequest{

    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //
        int xIdTipoOrden = 9;
        int xIdEscalaMaquina = 0;

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
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        int xIdTipoTercero = 1;
        int xEstadoSuspendido = 8;

        //---
        String xIdOperacion = request.getParameter("xIdOperacion");
        String xFechaPrograma = request.getParameter("xFechaPrograma");

        //---
        if (xIdOperacion == null) {

            //
            xIdOperacion = "0";
            xFechaPrograma = strFechaVisita;

        }
        //
        FachadaJobEscala fachadaBean = new FachadaJobEscala();

        //
        ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

        //
        colaboraJobEscala.setIdOperacion(xIdOperacion);

        //
        fachadaBean = colaboraJobEscala.listaEscalaMaquinaFCH();

        //
        xIdEscalaMaquina = fachadaBean.getIdEscala();

        //
        FachadaJobProgramaPlusFicha fachadaJobProgramaPlusFicha = new FachadaJobProgramaPlusFicha();

        //
        fachadaJobProgramaPlusFicha.setFechaPrograma(xFechaPrograma);
        fachadaJobProgramaPlusFicha.setIdOperacion(xIdOperacion);
        fachadaJobProgramaPlusFicha.setIdEscala(xIdEscalaMaquina);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaJobProgramaPlusFicha",
                fachadaJobProgramaPlusFicha);

        
        
        return "vista/programacion/ControlProgramacionOTProduccion.jsp";
    }
    
}
