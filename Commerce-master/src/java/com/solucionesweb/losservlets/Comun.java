/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraClientesBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Plasticauca Sistemas
 */
public class Comun extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String opcion = request.getParameter("opcion");
        
        ColaboraClientesBean ccb = new ColaboraClientesBean();
        
        if(opcion.compareTo("activar") == 0){
            String xIdFicha = request.getParameter("xficha");
            String xIdCliente = request.getParameter("xcliente");
            
            
            ccb.setIdCliente(xIdCliente);
            
            boolean xOkActiva = ccb.activaRefCliente(xIdFicha);
            
            if(xOkActiva == true){
                out.println("ACTIVO");
            }else{
                out.println("Error");
            }
        }
            
        if(opcion.compareTo("inactivar") == 0){
            String xIdFicha = request.getParameter("xficha");
            String xIdCliente = request.getParameter("xcliente");                      
            
            ccb.setIdCliente(xIdCliente);
            
            boolean xOkInactiva = ccb.inactivaRefCliente(xIdFicha);
            
            if(xOkInactiva == true){
                out.println("INACTIVO");
            }else{
                out.println("Error");
            }
            
        }
        
       
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
