/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraArteClientes;
import com.solucionesweb.losbeans.fachada.FachadaArteClientes;
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
@MultipartConfig
public class SubirArchivo extends HttpServlet {

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
        
        String xFicha = request.getParameter("xFicha");
        String xCliente = request.getParameter("xCliente");
        Part xArchivo = request.getPart("archivo");
        String xNombre = request.getParameter("nombre");
        String[] route = xNombre.split("\\\\");
        xNombre = route[route.length-1];
        InputStream is = xArchivo.getInputStream();
        File f = new File("C:\\proyectoWeb\\Arte\\"+xFicha+"_Arte_"+xCliente.replace(".","")+"_"+xNombre);
        FileOutputStream ous = new FileOutputStream(f);
        int dato = is.read();
        while(dato != -1){
            ous.write(dato);
            dato = is.read();
        }
        ous.close();
        is.close();
        
        FachadaArteClientes fachada = new FachadaArteClientes();
        ColaboraArteClientes colabora = new ColaboraArteClientes();
        String fehca = "GETDATE()";
        colabora.setIdFicha(Integer.parseInt(xFicha));
        colabora.setIdCliente(xCliente.replace(".", ""));
        colabora.setRutaArte("C:\\proyectoWeb\\Arte\\"+xFicha+"_Arte_"+xCliente.replace(".","")+"_"+xNombre);
        colabora.setFechaCreacion(fehca);
        
       fachada = colabora.listaArteCliente();
        
        if(fachada.getIdFicha() == Integer.parseInt(xFicha)){
           colabora.eliminaArte();
        }
        
        boolean xOkIngresa = colabora.ingresaArte();
        
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
