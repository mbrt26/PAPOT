package co.linxsi.controlador.touch.edicion;

/*   Document   :TouchEdicionOrdenes
    Author     : Edgar J. García L.*/
import co.linxsi.controlador.inventario.traslado.TrasladoInventarioServlet;
import co.linxsi.modelo.touch.edicion.Touch_Edicion_OT_DAO;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TouchEdicionOrdenServlet", urlPatterns = {"/TouchEdicionOrdenServlet"})
public class TouchEditarOrdenServlet extends HttpServlet {

    private final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyyMMdd");

    private UsuarioBean usuarioBean;
    private String mensaje = "";
    private String pattern = "###,###.##";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession();

        usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");

        try {
            String xOpcion = request.getParameter("xopcion");

            if (xOpcion.equals("getOneOT")) {
                printOneOT(request, out);
            } else if (xOpcion.equals("getOneOT2")) {
                printOneOT2(request, out);
            } else if (xOpcion.equals("printHistorial")) {
                printHistorial(request, out);

            } else if (xOpcion.equals("deleteMove")) {
                String idOrden = request.getParameter("idOrden");
                String idOperacion = request.getParameter("idOperacion");
                String item = request.getParameter("item");
                FachadaDctoOrdenProgreso dcto = new FachadaDctoOrdenProgreso();
                dcto.setIdOrden(idOrden);
                dcto.setIdOperacion(idOperacion);
                dcto.setItem(item);
                delete(dcto);
                printHistorial(request, out);
            } else if (xOpcion.equals("save")) {
                String idOrden = request.getParameter("idOrden");
                String item = request.getParameter("item");
                String fechaInicio = request.getParameter("fechaInicio");
                String fechaFin = request.getParameter("fechaFin");
                fechaInicio = fechaInicio.length() < 17 ? fechaInicio + ":00" : fechaInicio;
                fechaFin = fechaFin.length() < 17 ? fechaFin + ":00" : fechaFin;
                String cantFin = request.getParameter("cantFin");
                String pesoFin = request.getParameter("pesoFin");
                String tara = request.getParameter("tara");
                String retal = request.getParameter("retal");
                String tiempoPerdido = request.getParameter("tiempoPerdido");
                String idCausaRetal = request.getParameter("idCausaRetal");
                String idCausaParo = request.getParameter("idCausaParo");
                String idOperador = request.getParameter("idOperador");
                String idOperacion = request.getParameter("idOperacion");
                String idMaquina = request.getParameter("idMaquina");
                String idTurno = request.getParameter("idTurno");
                String fechaProduccion = request.getParameter("fechaProduccion");
                fechaProduccion = fechaProduccion.length() < 17 ? fechaProduccion + ":00" : fechaProduccion;
                String observacion = request.getParameter("observacion").trim();
                FachadaDctoOrdenProgreso dcto = new FachadaDctoOrdenProgreso();
                dcto.setIdOrden(idOrden);
                dcto.setItem(item);
                dcto.setFechaInicio(fechaInicio);
                dcto.setFechaFin(fechaFin);
                dcto.setCantidadTerminada(cantFin);
                dcto.setPesoTerminado(pesoFin);
                dcto.setPesoTara(tara);
                dcto.setPesoPerdido(retal);
                dcto.setIdOperario(idOperador);
                dcto.setIdOperacion(idOperacion);
                dcto.setIdMaquina(idMaquina);
                dcto.setObservacion(observacion);
                dcto.setIdUsuario(usuarioBean.getIdUsuarioStr());
                dcto.setTiempoPerdido(Double.parseDouble(tiempoPerdido));
                dcto.setIdTurno(Integer.parseInt(idTurno));
                dcto.setFechaProduccion(fechaProduccion);
                dcto.setIdCausa(idCausaParo.equals("") || idCausaParo.equals("0")
                        ? (idCausaRetal.equals("0") || idCausaRetal.equals("") ? "0" : idCausaRetal)
                        : idCausaParo);

                boolean saved = save(dcto);
            }

        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

    private void printOneOT(HttpServletRequest request, PrintWriter out) {
        Touch_Edicion_OT_DAO dao = new Touch_Edicion_OT_DAO();
        String idOperacion = request.getParameter("idOperacion");
        String idOrden = request.getParameter("idOrden");
        String item = request.getParameter("item");

        FachadaDctoOrdenProgreso fachadaOrdenProgreso = dao.getOneRecord(idOperacion, idOrden, item);

        out.print(fachadaOrdenProgreso.getOne());
    }

    private void printOneOT2(HttpServletRequest request, PrintWriter out) {
        Touch_Edicion_OT_DAO dao = new Touch_Edicion_OT_DAO();
        String idOperacion = request.getParameter("idOperacion");
        String numeroOrden = request.getParameter("numeroOrden");
        String item = request.getParameter("item");
        String idOrden = dao.dameKeyOrden(numeroOrden);

        FachadaDctoOrdenProgreso fachadaOrdenProgreso = dao.getOneRecord(idOperacion, idOrden, item);

        out.print(fachadaOrdenProgreso.getOne());
    }

    private void printHistorial(HttpServletRequest request, PrintWriter out) {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String idProceso = request.getParameter("idProceso").trim();
        String idOperario = request.getParameter("idOperador").trim();
        String ot = request.getParameter("idDcto").trim();
        ot = ot.equals("") ? "0" : ot;
        List<FachadaDctoOrdenProgreso> listaHistorial;
        try {
            fechaInicial = sdfSQL.format(sdf2.parse(fechaInicial));
            fechaFinal = sdfSQL.format(sdf2.parse(fechaFinal));
        } catch (ParseException ex) {
            Logger.getLogger(TrasladoInventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Touch_Edicion_OT_DAO dao = new Touch_Edicion_OT_DAO();
        dao.setFechaInicial(fechaInicial);
        dao.setFechaFinal(fechaFinal);
        dao.setSk_proceso(Integer.parseInt(idProceso));
        dao.setSk_Operario(Integer.parseInt(idOperario));

        dao.setIdDcto(Integer.parseInt(ot));
        {
            String encabezado
                    = "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \"  col-md-12 cellspacing=\"0\">\n "
                    + "<thead>\n <tr>\n                               "
                    + "                                    <th class=\"text-center\"  width=\"6%\">OT</th> \n"
                    + "                                    <th class=\"text-center\"  width=\"3%\">Item</th> \n"
                    + "                                    <th class=\"text-center\"  width=\"13%\">Fecha Inicio</th> \n"
                    + "                                    <th class=\"text-center\"  width=\"13%\">Fecha Fin</th> \n"
                    + "                                    <th class=\"text-left\" width=\"7%\">Proceso</th> \n"
                    + "                                    <th class=\"text-left\" width=\"25%\">Referencia</th> \n"
                    + "                                    <th class=\"text-left\" width=\"18%\">Operador</th> \n"
                    + "                                    <th class=\"text-left\" width=\"3%\">Cant. Finalizada</th> \n"
                    + "                                    <th class=\"text-left\" width=\"3%\">Peso (Kg) Finalizado</th> \n"
                    + "                                    <th class=\"text-left\" width=\"3%\">Tara (Kg)</th> \n"
                    + "                                    <th class=\"text-center\" width=\"3%\">Retal (Kg)</th> \n"
                    + "                                    <th class=\"text-center\" width=\"3%\">T.Perdido (min)</th> \n"
                    + "                                    <th class=\"text-center\" width=\"5%\" COLSPAN=\"2\">ACCIONES</th> \n"
                    + " \n </thead>\n";
            String cuerpo = "";
            listaHistorial = dao.listHistorialEdicion();
            int i = 1;
            for (FachadaDctoOrdenProgreso lista : listaHistorial) {
                cuerpo += "<tr>"
                        + " <td  class=\"text-center\">" + lista.getNumeroOrdenStr() + "</td>\n"
                        + " <td  class=\"text-center\">" + lista.getItem() + "</td>\n"
                        + " <td  class=\"text-center\">" + lista.getFechaInicio() + "</td>\n"
                        + " <td  class=\"text-center\">" + lista.getFechaFin() + "</td>\n"
                        + " <td  class=\"text-left\">" + lista.getNombreOperacion() + "</td>\n"
                        + " <td   class=\"text-left\" > " + lista.getReferenciaCliente() + "</td>\n"
                        + " <td   class=\"text-left\"   > " + lista.getNombreOperario() + "</td>\n"
                        + " <td   class=\"text-right\"   > " + lista.getCantidadTerminadaDf2() + "</td>\n"
                        + " <td   class=\"text-right\"   > " + lista.getPesoTerminadoDf2() + "</td>\n"
                        + " <td   class=\"text-right\" > " + lista.getPesoTaraDf2() + "</td>\n"
                        + " <td   class=\"text-right\" > " + lista.getPesoPerdidoStr() + "</td>\n"
                        + " <td   class=\"text-right\" > " + lista.getTiempoPerdido() + "</td>\n"
                        + " <td   class=\"text-center\" ><button type=\"button\" id= \"botonEditar" + i + "\"  onclick=\"getOneRecord(" + lista.getIdOperacion() + "," + lista.getIdOrden() + "," + lista.getItem() + ")\" class=\"btn btn-primary btn-sm \" data-toggle=\"modal\" data-target=\"#modal-ref-cliente\" "
                        + "onclick=\"guardarBorrar(" + i + "," + "," + "," + ")\">\n"
                        + "<i class=\"fas fa-edit\"></i></button>" + "</td>\n"
                        + " <td   class=\"text-center\" ><button type=\"button\" id= \"botonBorrar" + i + "\" class=\"btn btn-danger btn-sm \"  onclick=\"colocarDatosModalDelete(" + lista.getIdOrden() + "," + lista.getIdOperacion() + "," + lista.getItem() + ")\" data-toggle=\"modal\" data-target=\"#modal-delete\" "
                        + "onclick=\"guardarBorrar(" + i + "," + "," + "," + ")\">\n"
                        + "<i class=\"fas fa-trash-alt\"></i></button>" + "</td>\n"
                        + "</tr>";
                i++;
            }

            String pie = "</table>";
            String salida = encabezado + cuerpo + pie;
            out.print(salida);
        }

    }

    private boolean save(FachadaDctoOrdenProgreso dcto) {
        System.out.println("save");
        Touch_Edicion_OT_DAO dao = new Touch_Edicion_OT_DAO();
        return dao.Actualiza(dcto);

    }

    private boolean delete(FachadaDctoOrdenProgreso dcto) {
        System.out.println("delete");
        Touch_Edicion_OT_DAO dao = new Touch_Edicion_OT_DAO();
        return dao.delete(dcto);

    }
}
