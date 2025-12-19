/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.maestro.operaciones;

import co.linxsi.modelo.maestro.operaciones.Operaciones_DAO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DAO;
import co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DTO;
import co.linxsi.modelo.maestro.retales.Retales_DAO;
import co.linxsi.modelo.maestro.retales.Retales_DTO;
import co.linxsi.modelo.maestro.unidades.Unidades_DAO;
import co.linxsi.modelo.maestro.unidades.Unidades_DTO;
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
public class Control_Maestro_Operaciones implements GralManejadorRequest {

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Operaciones_DAO daoOP = new Operaciones_DAO();
        List<Operaciones_DTO> listaProcesos = daoOP.listaAll();
        request.setAttribute("listaProceso", listaProcesos);
        Paro_Maquina_DAO dao = new Paro_Maquina_DAO();
        List<Paro_Maquina_DTO> listaParo = dao.listaAllO();
        request.setAttribute("listaParo", listaParo);
        Unidades_DAO uDao = new Unidades_DAO();
        List<Unidades_DTO> listUnd = uDao.listaAllO();
        request.setAttribute("listaUnidad", listUnd);
        Retales_DAO rDao = new Retales_DAO();
        List<Retales_DTO> listaRetales = rDao.listaAllO();
        request.setAttribute("listaRetales", listaRetales);
        return "vista/maestros/Operaciones/ControlMaestroOperaciones.jsp";
    }

}
