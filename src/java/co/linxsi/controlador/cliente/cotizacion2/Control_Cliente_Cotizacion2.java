/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.cliente.cotizacion2;

import co.linxsi.controlador.cliente.cotizacion.*;
import co.linxsi.modelo.cliente.cotizacion.DAO_Dcto_Cot;
import co.linxsi.modelo.cliente.cotizacion.DAO_Dcto_Detalles_Cot;
import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Desarrollador .
 */
public class Control_Cliente_Cotizacion2 implements GralManejadorRequest {

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String xOption = request.getParameter("xoption");
        boolean ingresoExito = false;
        boolean ingresoExito2 = false;
        boolean consecutivoHistorico= false;
        String[] datosCliente = null;
        int maxIdDcto = 0;
        if (!(xOption == null)) {
            try {

                datosCliente = request.getParameter("xIdCliente3").split("~");

                String[] xItemArr = request.getParameterValues("idPluCot");
                String idEmisor = request.getParameter("listaFirmante");
                String encabezado = revizarTilde(request.getParameter("xEncabezado"));
                String piePagina = revizarTilde(request.getParameter("xPiePagina"));
                String cargoEmisor = revizarTilde(request.getParameter("xCargo"));

                DAO_Dcto_Cot daoDoc = new DAO_Dcto_Cot();

                daoDoc.setCargoEmisor(cargoEmisor);
                daoDoc.setIdEmisor(Integer.parseInt(idEmisor));
                daoDoc.setIdCliente(Integer.parseInt(datosCliente[0]));
                daoDoc.setNombreCliente(datosCliente[1]);
                daoDoc.setContacto(datosCliente[2]);
                daoDoc.setEncabezado(encabezado);
                daoDoc.setPiePagina(piePagina);
                maxIdDcto = daoDoc.getMaxDcto();
                daoDoc.setIdDcto(maxIdDcto);
                ingresoExito = daoDoc.ingresarDocumento();

                int i = 0;
                //Inicio ciclo Para ingresar detalles de cotización
                if (ingresoExito) {
                    for (String plu : xItemArr) {
                        i++;
                        String[] datosPlu = plu.split("~");
                        DAO_Dcto_Detalles_Cot detalles = new DAO_Dcto_Detalles_Cot();
                        detalles.setIdDcto(maxIdDcto);
                        detalles.setItem(i);
                        detalles.setMedida(datosPlu[2] + " x " + datosPlu[3]);
                        detalles.setCalibre(Double.parseDouble(datosPlu[4]));
                        detalles.setNombreReferencia(datosPlu[1]);
                        detalles.setIdPlu(Integer.parseInt(datosPlu[0]));
                        detalles.setNombreMaterial(datosPlu[6]);
                        detalles.setCantidad(Double.parseDouble(datosPlu[5]));
                        detalles.setPrecio(Double.parseDouble(datosPlu[7]));
                        ingresoExito2 = detalles.ingresarDocumentoDetalle();
                    }

                }
            } catch (Exception e) {
                if (xOption != null && xOption.equals("ImprimirPDFReporte")) {
                    String idCliente = request.getParameter("listaClientes4");
                    
                    imprimirPDFPantallaReporte(idCliente, request, response);
                } else {
                    maxIdDcto = Integer.parseInt(xOption);
                    consecutivoHistorico=true;
                    
                }
            }

        }
        if ((ingresoExito && ingresoExito2 && xOption != null) || consecutivoHistorico) {
            imprimirPDFPantalla(maxIdDcto, request, response);
        }

        return "vista/cotizacion/Control_Cliente_Cotizacion2.jsp";
    }

    private void imprimirPDFPantalla(int idOrden, HttpServletRequest request, HttpServletResponse response) {

        GeneraPDFCotizacion gpdf = new GeneraPDFCotizacion(idOrden);

        gpdf.setNombreReporte("RepPDFCotizacion");
        try {
            gpdf.generaPdf(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Control_Cliente_Cotizacion2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Control_Cliente_Cotizacion2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void imprimirPDFPantallaReporte(String idCliente, HttpServletRequest request, HttpServletResponse response) {

        GeneraPDFReporteMargenes gpdf = new GeneraPDFReporteMargenes(idCliente);

        gpdf.setNombreReporte("RepPDFMargenesReferencias");
        
        try {
            gpdf.generaPdf(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Control_Cliente_Cotizacion2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Control_Cliente_Cotizacion2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
       /**
     * Metodo para ajustar las vocales con tilde y la Ã±,Ã.
     *
     * @param aRevizar
     * @return
     */
    private String revizarTilde(final String aRevizar) {
        return aRevizar.replace("Ã¡", "á").replace("Ã", "Á").replace("Ã©", "é").replace("Ã", "É").replace("Ã­", "í").replace("Ã", "Í").replace("Ã³", "ó").replace("Ã", "Ó").replace("Ãº", "ú").replace("Ã", "Ú").replace("Ã±", "ñ").replace("Ã", "Ñ");
    }

}
