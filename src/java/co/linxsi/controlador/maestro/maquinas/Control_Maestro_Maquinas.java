/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.maestro.maquinas;

import co.linxsi.modelo.maestro.operaciones.Operaciones_DAO;
import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Desarrollador
 */
public class Control_Maestro_Maquinas implements GralManejadorRequest {

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Operaciones_DAO mdao = new Operaciones_DAO();
        List listaOperaciones = mdao.listaAll();
        request.setAttribute("listaOperaciones", listaOperaciones);
        return "vista/maestros/Maquinas/ControlMaestroMaquinas.jsp";

    }
}
