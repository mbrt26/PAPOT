    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.maestro.unidades;

import co.linxsi.controlador.maestro.retales.*;
import co.linxsi.modelo.maestro.bodega.BodegaDAO;
import co.linxsi.modelo.maestro.bodega.BodegaDTO;
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
public class Control_Maestro_Unidades implements GralManejadorRequest{

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BodegaDAO bdao = new BodegaDAO();
        List<BodegaDTO> lista = bdao.listaTiposBodegas();
        request.setAttribute("listaTipoBodega", lista);
        return "vista/maestros/Unidades/ControlMaestroUnidades.jsp";
}

}
    