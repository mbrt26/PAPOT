/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.maestro.impuestos;

import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Control_Maestro_Impuestos implements GralManejadorRequest {
    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "vista/maestros/impuesto/ControlMaestroImpuesto.jsp";
    }
}
