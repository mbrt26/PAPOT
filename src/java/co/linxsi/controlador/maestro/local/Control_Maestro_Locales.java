/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.maestro.local;

import co.linxsi.controlador.maestro.calificadores.*;
import co.linxsi.modelo.maestro.bodega.BodegaDAO;
import co.linxsi.modelo.maestro.bodega.BodegaDTO;
import co.linxsi.modelo.maestro.local.Local_DAO;
import co.linxsi.modelo.maestro.local.Local_DTO;
import com.solucionesweb.losbeans.fachada.FachadaRegimenBean;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;
import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Desarrollador
 */
public class Control_Maestro_Locales implements GralManejadorRequest {
    
    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xOption = request.getParameter("xoption");
              Local_DAO localDAO = new Local_DAO();
        if ("guardar".equals(xOption)) {
            System.out.println("Guardar");
                localDAO.setNombreLocal(request.getParameter("nombreLocal"));
                localDAO.setRazonSocial(request.getParameter("razonSocial"));
                localDAO.setNIT(request.getParameter("nit"));
                localDAO.setEmail(request.getParameter("email"));
                localDAO.setDireccion(request.getParameter("direccion"));
                localDAO.setIdCiudad(request.getParameter("idCiudad"));
                localDAO.setTelefono(request.getParameter("telefono"));
                localDAO.setFax(request.getParameter("fax"));
                localDAO.setResFiscal1(request.getParameter("idRes1"));
                localDAO.setResFiscal2(request.getParameter("idRes2"));
                localDAO.setResFiscal3(request.getParameter("idRes3"));
                localDAO.setResFiscal4(request.getParameter("idRes4"));
                localDAO.setIdRegimen(Integer.parseInt(request.getParameter("idRegimen")));
                localDAO.setTipoOperacion(request.getParameter("idOperacion"));
                localDAO.setResolucion(request.getParameter("resolucion"));
                localDAO.setResolucion2(request.getParameter("resolucion2"));
                localDAO.actualiza();
        }
            
        System.out.println("Abriendo Maestro Locales de Empresa");
        TerceroBean tercero = new TerceroBean();
 
        ArrayList<FachadaTerceroBean> listaOperaciones = tercero.listaOperaciones();
        ArrayList<FachadaTerceroBean> listaResponsabilidades = tercero.listaResponsabilidades();
        List<Local_DTO> listaRegimenes = localDAO.listaRegimen();
        List<Local_DTO> listaCiudades = localDAO.listaCiudades();
        Local_DTO dtoDatosEmpresa = localDAO.getLocal();
        //Se envian la lista de Lista de Operaciones Permitidas
        request.setAttribute("listaOperaciones", listaOperaciones);
      
        //Se envian la lista de responsabilidades
        request.setAttribute("listaResponsabilidades", listaResponsabilidades);
        //Se envian lista de regimenes
        request.setAttribute("listaRegimenes", listaRegimenes);
        //Se envian lista de ciudades y departamento
        request.setAttribute("listaCiudades", listaCiudades);
        //Se envian datos seleccion usuario
        request.setAttribute("datosSeleccion", dtoDatosEmpresa);
     
        return "vista/maestros/local/VistaMaestroLocal.jsp";
    }
    
}
