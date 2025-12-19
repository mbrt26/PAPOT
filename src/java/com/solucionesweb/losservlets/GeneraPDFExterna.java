package com.solucionesweb.losservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.HashMap;
import java.util.Map;

//
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenVentaBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaOrdenVenta;

// Importa el bean de colaboraciones
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa el bean de colaboraciones
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;
import java.util.Vector;

public class GeneraPDFExterna implements GralManejadorRequest {

    //
    private int idOrden;
    private int idLocal;
    private int idTipoOrden;
    private int idLog;
    private int idControl;
    private int idOperacion;
    private int itemPadre;
    private int idControlTipo;
    private String nombreReporte;
    private String tituloReporte;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //
        int xIdTipoTeceroCliente = 2;

        //
        String reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" +
                getNombreReporte();

        String pathPDF =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();
        
        //----------------------------------------------------------------------
        JobOperacionBean jobOperacionBean = new JobOperacionBean();
        
        //
        FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();
        
        //
        jobOperacionBean.setIdOperacion(getIdOperacion());
        
        //
        fachadaJobOperacion = jobOperacionBean.listaUnaOperacionFCH();
        
        //
        String xNombreOperacion = fachadaJobOperacion.getNombreOperacion();

        //----------------------------------------------------------------------
        ColaboraOrdenVentaBean colaboraOrdenVentaBean = new ColaboraOrdenVentaBean();

        //
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        fachadaOrdenVenta = colaboraOrdenVentaBean.listaUnLocalResolucion();

        //
        String xRegimen = fachadaOrdenVenta.getRegimen().trim();

        // Local
        pars.put("p_regimen", xRegimen);


        String strDireccionCiudad = fachadaOrdenVenta.getDireccion().trim()
                + " " + fachadaOrdenVenta.getCiudad().trim()
                + " " + fachadaOrdenVenta.getTelefono().trim()
                + " " + fachadaOrdenVenta.getTelefonoFax().trim() ;
        //
        pars.put("p_nombreLocal", fachadaOrdenVenta.getNombreLocal().trim());
        pars.put("p_razonSocial", fachadaOrdenVenta.getRazonSocial());
        pars.put("p_telefono", fachadaOrdenVenta.getTelefono());
        pars.put("p_nit", fachadaOrdenVenta.getNit());
        pars.put("p_email", fachadaOrdenVenta.getEmail());
        pars.put("p_direccion", strDireccionCiudad);
        
        
        //- Importa el bean de fachada------------------------------------------
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso 
                                               = new FachadaDctoOrdenProgreso();

        //
        ColaboraOrdenProgreso colaboraOrdenProgreso = new ColaboraOrdenProgreso();


        //---- DetallaOrden
        colaboraOrdenProgreso.setIdLocal(getIdLocal());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrden());
        colaboraOrdenProgreso.setIdOrden(geIdOrden());
        colaboraOrdenProgreso.setIdOperacion(getIdOperacion());
        colaboraOrdenProgreso.setItemPadre(getItemPadre());
        colaboraOrdenProgreso.setIdControl(getIdControl());
        colaboraOrdenProgreso.setIdControlTipo(getIdControlTipo());

        //
        fachadaDctoOrdenProgreso =
                               colaboraOrdenProgreso.listaUnOTExternaCopiaFCH();

        //--------
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        ColaboraTercero colaboraTercero = new ColaboraTercero();

        //
        colaboraTercero.setIdCliente(fachadaDctoOrdenProgreso.getIdOperarioSf0());
        colaboraTercero.setIdTipoTercero(xIdTipoTeceroCliente);

        //
        fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

        // Tercero
        pars.put("p_idTercero", fachadaTerceroBean.getIdTerceroSf0() + "-"
                + fachadaTerceroBean.getDigitoVerificacionStr());
        pars.put("p_direccionTercero",
                fachadaTerceroBean.getDireccionTercero().trim());
        pars.put("p_telefonoFijo", "TEL: "
                + fachadaTerceroBean.getTelefonoFijo());
        pars.put("p_ciudadTercero", fachadaTerceroBean.getCiudadTercero());
        pars.put("p_nombreTercero", fachadaTerceroBean.getNombreTercero());

        //
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

        // LiquidaOrden
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdOrden(geIdOrden());
        colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

        //
        fachadaDctoBean =
                colaboraDctoBean.listaUnDctoFCH();

        //
        String xTextoFactura = getTituloReporte() + " : " + 
                               xNombreOperacion ;
        String xObservacion  = "";
        String xFechaOrden  = "";
        String xNombreVendedor  = "";

        //
        String xIdDcto       = fachadaDctoBean.getIdDctoSf0();

        //
        pars.put("p_textoFactura", xTextoFactura);
        pars.put("p_idControl", getIdControl());
        pars.put("p_observacion", "OBSERVACIONES " + xObservacion);
        pars.put("p_fechaOrden", xFechaOrden);

        //---- DetallaOrden
        colaboraOrdenProgreso.setIdLocal(getIdLocal());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrden());
        colaboraOrdenProgreso.setIdOrden(geIdOrden());
        colaboraOrdenProgreso.setIdOperacion(getIdOperacion());
        colaboraOrdenProgreso.setItemPadre(getItemPadre());
        colaboraOrdenProgreso.setIdControl(getIdControl());
        colaboraOrdenProgreso.setIdControlTipo(getIdControlTipo());

        //1-Llenar el datasource con la informacion de la base de datos
        Vector lista = colaboraOrdenProgreso.listaUnOTExternaCopia();

        //
        JRBeanCollectionDataSource dataSource;

        //
        dataSource = new JRBeanCollectionDataSource(lista, false);

        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciïón (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);

            //------------------------------------------------------------------
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint,
            servletOutputStream);

            //
            servletOutputStream.flush();
            servletOutputStream.close();

            //
            System.out.println("Done!");


        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        //
        return "";

    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public int geIdOrden() {
        return idOrden;
    }

    public String geIdOrdenStr() {
        return new Integer(geIdOrden()).toString();
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setIdOrden(String idOrdenStr) {
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public int getIdControl() {
        return idControl;
    }

    public void setIdControl(int idControl) {
        this.idControl = idControl;
    }
    
    public void setIdControl(String idControlStr) {
        this.idControl = new Integer(idControlStr).intValue();
    }    

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }
    
    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }     

    public int getItemPadre() {
        return itemPadre;
    }

    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    public int getIdControlTipo() {
        return idControlTipo;
    }

    public void setIdControlTipo(int idControlTipo) {
        this.idControlTipo = idControlTipo;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }
}
