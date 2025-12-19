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
public class Control_Inventario_Traslado implements GralManejadorRequest{

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       BodegaDAO bdao = new BodegaDAO();
        List<BodegaDTO> lista = bdao.listaAll();
        request.setAttribute("listaBodega", lista);
        cargarProveedores(request);
        return "vista/inventario/traslado/ControlInventarioTraslado.jsp";
                                                                                                                            }

    private void cargarProveedores(HttpServletRequest request) {
        ColaboraTercero ct = new ColaboraTercero();
        ct.setIdTipoTercero(2);
        ct.setNombreTercero("%%");
        Vector listaProveedores = ct.listaAllNombre();
        request.setAttribute("listaProveedores", listaProveedores);
                                                                }
}
