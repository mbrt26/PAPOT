package co.linxsi.controlador.cliente.cotizacion;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losservlets.GralManejadorRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class GeneraPDFCotizacion implements GralManejadorRequest {

    private String nombreReporte;
      private JDBCAccess pc;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    private String orden = "";

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public GeneraPDFCotizacion(int idDcto) {
        this.pc = new JDBCAccess(DATA_SOURCE_NAME);
        this.orden = String.valueOf(idDcto);
    }

    public String getNombreReporte() {
        return this.nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    @Override
    public String generaPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = null;
        ServletOutputStream servletOutputStream = null;
        try {
            try {
                servletOutputStream = response.getOutputStream();

                connection = new JDBCAccess(DATA_SOURCE_NAME).getConnection();
            } catch (NamingException ex) {
                Logger.getLogger(GeneraPDFCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GeneraPDFCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }

            Map pars = new HashMap();

            String reportName = "C:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" + getNombreReporte() + ".jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportName);
            pars.put("orden1", getOrden());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pars, connection);

            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

            if (servletOutputStream != null) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }

            System.out.println("Done!");

            this.pc.cleanup(connection, null, null);

        } catch (JRException ex) {
            Logger.getLogger(GeneraPDFCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Done";
    }
}
