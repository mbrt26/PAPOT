/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.inventario.traslado;

import co.linxsi.modelo.maestro.bodega.BodegaDAO;
import co.linxsi.modelo.maestro.bodega.BodegaDTO;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Desarrollador
 */
public class Controller_Inventario_Traslado implements GralManejadorRequest {

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String xOption = request.getParameter("xoption");
//      String xOrden = request.getParameter("campoNumeroOrden");

        if (!xOption.equals(null)) {
            GeneraPDFInventarioTraslado gpdf = new GeneraPDFInventarioTraslado();
            gpdf.setOrden(xOption);
            gpdf.setNombreReporte("RepPdfTraslado");
            gpdf.generaPdf(request, response);
        }

        return "/ControlInventarioTraslado.ctr";
    }

}
