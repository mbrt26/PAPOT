package co.linxsi.controlador.touch.paro;

/*   Document   :TouchRetalServlet
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.*/
import co.linxsi.controlador.inventario.traslado.TrasladoInventarioServlet;
import co.linxsi.modelo.maestro.maquinas.Maquinas_DAO;
import co.linxsi.modelo.maestro.maquinas.Maquinas_DTO;
import co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DAO;
import co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DTO;
import co.linxsi.modelo.touch.retal.Touch_Retal_DAO;
import co.linxsi.modelo.touch.retal.Touch_Retal_DTO;
import com.solucionesweb.lasayudas.ProcesoIngresoRetal;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOperacionPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionCostoBean;
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

@WebServlet(name = "TouchParoServlet", urlPatterns = {"/TouchParoServlet"})
public class TouchParoServlet extends HttpServlet {

    private final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyyMMdd");
    private String sk_proceso;
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

        sk_proceso = request.getParameter("sk_proceso");
        try {
            String xOpcion = request.getParameter("xopcion");

            if (xOpcion.equals("printParos")) {
                printListParo(out);
            }
            if (xOpcion.equals("printListaParoDesplegable")) {
                printListaParoDesplegable(out);
            }
            if (xOpcion.equals("printOperadores")) {
                printListaOperarios(out);
            }
            if (xOpcion.equals("printMaquinas")) {
                printListaMaquinas(out);
            }
            if (xOpcion.equals("printMaquinas")) {
                printListaMaquinas(out);
            }
            if (xOpcion.equals("printMaquinasReporte")) {
                printListaMaquinasReporte(out);
            }
            if (xOpcion.equals("guardarRetal")) {
                guardarRetal(request, out);
            }
            if (xOpcion.equals("printHistorial")) {
                printHistorial(request, out);
            }
            if (xOpcion.equals("printHistorialGlobal")) {
                printHistorialGlobal(request, out);
            }
            if (xOpcion.equals("printBarras")) {
                printGraficoBarrasGlobal(request, out);
            }
            if (xOpcion.equals("printPie")) {
                printGraficoPieGlobal(request, out);
            }

        } finally {
            out.close();
        }
    }

    private void guardarRetal(HttpServletRequest request, PrintWriter out) {
        boolean resultado = false;
        String idDcto = request.getParameter("idDcto");
        String idRetal = request.getParameter("idRetal");
        String idOperador = request.getParameter("idOperador");
        String comentario = request.getParameter("comentario");
        String cantidad = request.getParameter("cantidad");
        String idProceso = request.getParameter("idProceso");
        String idMaquina = request.getParameter("idMaquina");
        String turno = request.getParameter("listaTurnos");
        String fechaProduccion = request.getParameter("fechaProduccion");

        resultado = ingresarRetal(idDcto, cantidad, idOperador, idProceso,
                idRetal, comentario, idMaquina, turno, fechaProduccion, request);
        out.print(String.valueOf(resultado) + "," + mensaje);
    }

    private String Encabezado() {
        return " <ul id = \"listaRetal\"  class=\"list-group col-md-12\">\n"
                + "                                   "
                + "\n";

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

    private void printListParo(PrintWriter out) {
        Paro_Maquina_DAO dao = new Paro_Maquina_DAO();
        dao.setSk_operacion(Integer.parseInt(sk_proceso));
        List<Paro_Maquina_DTO> Lista = dao.listParoByProcess();
        out.println(Encabezado());
        int i = 1;
        String estado = "checked";
        for (Paro_Maquina_DTO t : Lista) {
            out.println(" <li class=\"list-group-item list-group-item-warning list-group-item-action\"   > "
                    + " <div class=\"input-group mb-0\">\n"
                    + "<div class=\"input-group-prepend\">\n"
                    + "<input id = \"radioButtonR" + i + "\" onclick=\"copiarRetal(" + i + ")\" " + estado + " name = \"radioButtonR\" type=\"radio\" style=\"cursor:pointer;\"  value =\"" + t.getSk_paro_maquina() + "\">\n"
                    + "</div>\n"
                    + " <input id = \"inputR" + i + "\" value=\"" + t.getNombre_paro_maquina() + "\" hidden=\"true\"> <label type=\"text\" class=\"form-control form-control-warning bg-transparent border-0 text-md-left\" >"
                    + t.getNombre_paro_maquina()
                    + "</label>\n"
                    + "</div>\n"
                    + "</li>\n");
            estado = "";
            i++;
        }
        out.println("\" </ul>  \";");
    }

    private void printListaParoDesplegable(PrintWriter out) {
        Paro_Maquina_DAO dao = new Paro_Maquina_DAO();
        dao.setSk_operacion(Integer.parseInt(sk_proceso));
        List<Paro_Maquina_DTO> lista = dao.listParoByProcess();
        out.println("<select id=\"listaParo\" title =\"Paro\" class=\"custom-select col-md-3\"\">\n");

        for (Paro_Maquina_DTO paro : lista) {
            out.println("<option value=\"" + paro.getSk_paro_maquina() + "\">" + paro.getNombre_paro_maquina() + "</option>\n");

        }
        out.println("\"</select>\";");
    }

    private void printListaOperarios(PrintWriter out) {
        Touch_Retal_DAO dao = new Touch_Retal_DAO();
        dao.setSk_proceso(Integer.parseInt(sk_proceso));
        List<Touch_Retal_DTO> Lista = dao.getListOperatorByProcess();
        out.println(Encabezado());
        int i = 1;
        String estado = "checked";
        for (Touch_Retal_DTO t : Lista) {
            out.println(
                    " <li class=\"list-group-item list-group-item-success list-group-item-action\"> "
                    + " <div class=\"input-group mb-0\">\n"
                    + "<div class=\"input-group-prepend\">\n"
                    + "<input id = \"radioButtonO" + i + "\" onclick=\"copiarOperador(" + i + ")\" " + estado + " name = \"radioButtonO\"type=\"radio\" style=\"cursor:pointer;\" value =\"" + t.getSk_Operario() + "\">\n"
                    + "</div>\n"
                    + " <input id = \"inputO" + i + "\" id=\"idOperadorSelected\" value=\"" + t.getNombreOperario() + "\" hidden=\"true\"> <label type=\"text\"   class=\"form-control form-control-success bg-transparent border-0 text-md-left\" >"
                    + t.getNombreOperario()
                    + "</label>\n"
                    + "</div>\n"
                    + "</li>\n");
            i++;
            estado = "";
        }
        out.println("\" </ul>\";");
    }

    private void printListaMaquinas(PrintWriter out) {
        Maquinas_DAO dao = new Maquinas_DAO();
        dao.setSk_operacion(Integer.parseInt(sk_proceso));
        List<Maquinas_DTO> Lista = dao.listaAllMachineByProcess();
        out.println("<select id=\"listaMaquinas\" title =\"Maquina\" class=\"custom-select col-md-3\"\">\n");
        int i = 1;
        for (Maquinas_DTO t : Lista) {
            out.println("<option value=\"" + t.getSk_maquina() + "\">" + t.getNombreMaquina() + "</option>\n");
            i++;
        }
        out.println("\"</select>\";");
    }

    private void printListaMaquinasReporte(PrintWriter out) {
        Maquinas_DAO dao = new Maquinas_DAO();
        dao.setSk_operacion(Integer.parseInt(sk_proceso));
        List<Maquinas_DTO> Lista = dao.listaAllMachineByProcess();
        out.println("<select id=\"listaMaquinas\" class=\"custom-select col-md-3\"\">\n");
        out.println("<option class=\"text-md-center\" value =\"0\" >TODAS</option>    ");
//        int i = 1;
        for (Maquinas_DTO t : Lista) {
            out.println("<option value=\"" + t.getSk_maquina() + "\">" + t.getNombreMaquina() + "</option>\n");
//            i++;
        }
        out.println("\"</select>\";");
    }

    private boolean ingresarRetal(String xNumeroOrdenStr, String xPesoPerdido, String xIdOperario,
            String xIdOperacion, String xIdCausa, String comentario, String idMaquina, String turno,
            String fechaProduccion,
            HttpServletRequest request) {

        String xIdLocal = "1";
        String xIdTipoOrden = "59";
//        String xNumeroOrdenStr = request.getParameter("");
        String xCantidadTerminada = "0.0";
        String xPesoTerminado = "0.0";
//        String xPesoPerdido = request.getParameter("cantidad");
//        String xIdOperario = request.getParameter("xIdOperario");
        //xIdCausa = "6";
        String xEstado = "1";
//        String xIdOperacion = request.getParameter("ixProceso");
        String xPesoTara = "0.0";
        //
        int xItemPadre = 1;
        int xIdBodegaSiguienteRetales = 666;

        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                = new FachadaColaboraDctoOrdenBean();

        //
        ColaboraDctoOrdenBean colaboraDctoOrdenBean
                = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdLocal(xIdLocal);
        colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
        colaboraDctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);

        //
        fachadaColaboraDctoOrdenBean
                = colaboraDctoOrdenBean.listaOTFCH();

        //--------------------------------------------------------------
        //consulta para ver si existe la orden
        int xIdLog = fachadaColaboraDctoOrdenBean.getIdLog();

        if (xIdLog == 0) {
            mensaje = "No existe Orden Trabajo: " + xNumeroOrdenStr;
            return false;
        }
        //
        //---
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setIdLog(xIdLog);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //---
        fachadaAgendaLogVisitaBean
                = agendaLogVisitaBean.listaLogFachada();

        //
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        //
        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

        //
        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

        //
        dctoOrdenBean.setIdLog(xIdLog);

        //
        fachadaDctoOrdenBean
                = dctoOrdenBean.listaDctoOrdenIdLog();

        //
        int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
        int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
        int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
        int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

        //
        int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
        String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
        String xIdTipoTerceroProveedor = "2";

        //
        fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
        fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
        fachadaDctoOrdenBean.setItemPadre(xItemPadre);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

        //
        String xIdTipoOrdenCotizacion = "59";

        //
        String xIdTipoTerceroCliente = "1";

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        ColaboraTercero colaboraTercero = new ColaboraTercero();

        //
        fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
        colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

        //
        colaboraTercero.setIdCliente(
                fachadaAgendaLogVisitaBean.getIdCliente());

        //
        fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

        //
        FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                = new FachadaDctoOrdenDetalleBean();

        //
        ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
        colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                xIdTipoOrdenCotizacion);

        //
        fachadaDctoOrdenDetalleBean
                = colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

        //
        Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
        Double xCantidadPerdida = 0.0;

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaDctoOrdenDetalleBean",
                fachadaDctoOrdenDetalleBean);

        //--------------------------------------------------------------
        DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

        //
        dctoOrdenProgresoBean.setIdLocal(xIdLocal);
        dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenProgresoBean.setIdOrden(
                fachadaDctoOrdenBean.getIdOrden());
        dctoOrdenProgresoBean.setItemPadre(xItemPadre);
        dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

        if (xIdOperacion.equals("4") || xIdOperacion.equals("5") || xIdOperacion.equals("6")) {
            fechaProduccion = fechaProduccion.length() < 17 ? fechaProduccion + ":00" : fechaProduccion;
            fechaProduccion = fechaProduccion.replace("T", " ");

        } else {
            fechaProduccion = "";
            turno = "0";
        }
        dctoOrdenProgresoBean.setFechaProduccion(fechaProduccion);
        dctoOrdenProgresoBean.setIdTurno(Integer.parseInt(turno));
        //---
        int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

        //--------------------------------------------------------------
        FachadaJobOperacionCosto fachadaJobOperacionCosto
                = new FachadaJobOperacionCosto();

        //
        JobOperacionCostoBean jobOperacionCostoBean
                = new JobOperacionCostoBean();

        //---
        jobOperacionCostoBean.setIdLocal(xIdLocal);
        jobOperacionCostoBean.setIdOperacion(xIdOperacion);

        //---
        fachadaJobOperacionCosto
                = jobOperacionCostoBean.listaCostoFCH();

        //---
        double xVrCostoBaseMAT
                = fachadaJobOperacionCosto.getVrCostoBaseMAT();
        double xVrCostoBaseMOD
                = fachadaJobOperacionCosto.getVrCostoBaseMOD();
        double xVrCostoBaseCIF
                = fachadaJobOperacionCosto.getVrCostoBaseCIF();

        //---
        int xIdTipoCausaRetal = 2;

        //
        double xIdUsuario = usuarioBean.getIdUsuario();
        //--------------------------------------------------------------
        dctoOrdenProgresoBean.setItem(xMaximoItem);
        dctoOrdenProgresoBean.setIdOperario(xIdOperario);
        dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
        dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
        dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
        dctoOrdenProgresoBean.setTiempoPerdido(Double.parseDouble(xPesoPerdido));
        dctoOrdenProgresoBean.setIdCausa(xIdCausa);
        dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaRetal);
        dctoOrdenProgresoBean.setEstado(xEstado);
        dctoOrdenProgresoBean.setItemPadre(xItemPadre);
        dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
        dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
        dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
        dctoOrdenProgresoBean.setPesoTara(xPesoTara);
        dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
        dctoOrdenProgresoBean.setCantidadPendiente(0);
        dctoOrdenProgresoBean.setObservacion(comentario);
        dctoOrdenProgresoBean.setIdMaquina(idMaquina);

        //---
        dctoOrdenProgresoBean.ingresaInicio();

        //--------------------------------------------------------------
        ColaboraOperacionPlu colaboraOperacionPlu
                = new ColaboraOperacionPlu();

        //
        colaboraOperacionPlu.setIdFicha(xIdFicha);
        colaboraOperacionPlu.setIdOperacion(xIdOperacion);

        //
        double xTotalMP = colaboraOperacionPlu.totalMP();

        //
        ProcesoIngresoRetal proceso = new ProcesoIngresoRetal();
        int xIdTipoOrdenTransformacion = 11;
        //
        proceso.ingresaRetal(new Integer(xIdLocal).intValue(),
                xIdTipoOrdenTransformacion,
                xIdUsuario,
                xIdFicha,
                new Integer(xIdOperacion).intValue(),
                new Double(xPesoPerdido).doubleValue(),
                new Double(xPesoPerdido).doubleValue(),
                xTotalMP,
                xIdCliente,
                xIdOrdenOrigen,
                xIdLocalOrigen,
                xIdTipoOrdenOrigen,
                xNumeroOrden,
                new Integer(xItemPadre).intValue());

        //--------------------------------------------------------------
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

        //
        dctoOrdenDetalleBean.actualizaOT_Almacen();

        //--- actualizaPedidoPendiente ---------------------------------
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenDetalleBean.setIdLog(xIdLog);
        dctoOrdenDetalleBean.setItem(xItemPadre);

        //
        dctoOrdenDetalleBean.actualizaPedidoPendiente();

        return true;
    }

    private List<Touch_Retal_DTO> printHistorial(HttpServletRequest request, PrintWriter out) {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String idProceso = request.getParameter("idProceso");
        String idMaquina = request.getParameter("idMaquina");
        String idOperario = request.getParameter("idOperador");
        String ot = request.getParameter("idDcto").trim();
        String agrupar = request.getParameter("agrupar");
        Double totalProducido = 0.0;
        Double totalRetal = 0.0;
        Double porcentaje = 0.0;
        List<Touch_Retal_DTO> listaHistorial;
        boolean xAgrupar = Boolean.parseBoolean(agrupar);
        if (ot.equals("")) {
            ot = "0";
        }
        try {
            fechaInicial = sdfSQL.format(sdf2.parse(fechaInicial));
            fechaFinal = sdfSQL.format(sdf2.parse(fechaFinal));
        } catch (ParseException ex) {
            Logger.getLogger(TrasladoInventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Touch_Retal_DAO dao = new Touch_Retal_DAO();
        dao.setFechaInicial(fechaInicial);
        dao.setFechaFinal(fechaFinal);
        dao.setSk_proceso(Integer.parseInt(idProceso));
        dao.setSk_Operario(Integer.parseInt(idOperario));
        dao.setSk_Maquina(Integer.parseInt(idMaquina));
        dao.setIdDcto(Integer.parseInt(ot));
        if (xAgrupar) {
            System.out.println("Iniciando Agrupamiento");
            String encabezado
                    = //"<div class=\"card-header bg-light card border-primary mb-3 col-md-12\" id=\"headingTwo\">"
                    "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \" cellspacing=\"0\">\n "
                    + "<thead>\n <tr>\n                               "
                    + "                                    <th class=\"text-center\"  width=\"3%\">Nº</th> \n"
                    + "                                    <th class=\"text-center\"  width=\"5%\">OT</th> \n"
                    + "                                    <th class=\"text-left\"  width=\"5%\">PROCESO </th> \n"
                    + "                                    <th class=\"text-left\"  width=\"12%\">MAQUINA</th> \n"
                    + "                                    <th class=\"text-center\" width=\"4%\">PRODUCIDO(Kg)</th> \n"
                    + "                                    <th class=\"text-center\" width=\"4%\">RETAL(Kg)</th> \n"
                    + "                                    <th class=\"text-center\" width=\"30%\">%RETAL</th> \n"
                    + " \n </thead>\n";

            String cuerpo = "";
            listaHistorial = dao.listHistorialAgrupado();
            int i = 1;
            for (Touch_Retal_DTO lista : listaHistorial) {
                String barra = "";
                if (lista.getPorcentajeRetal() > 7) {
                    barra = "<div class=\"" + dameColorBarra(lista.getPorcentajeRetal()) + "\" role=\"progressbar\" style=\"width: " + lista.getPorcentajeRetal() + "%\" aria-valuenow=\"" + lista.getPorcentajeRetal() + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\">" + lista.getPorcentajeRetal() + "%</div></div>";
                } else {
                    barra = "<div class=\"" + dameColorBarra(lista.getPorcentajeRetal()) + "\" role=\"progressbar\" style=\"width: " + lista.getPorcentajeRetal() + "%\" aria-valuenow=\"" + lista.getPorcentajeRetal() + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\"></div>" + lista.getPorcentajeRetal() + "%</div>\n";
                }
                cuerpo += "<tr>"
                        + " <td class=\"text-center\">" + i + "</td>\n"
                        + " <td class=\"text-center\">" + lista.getIdDcto() + "</td>\n"
                        + "<td class=\"text-left\">" + lista.getNombreProceso() + "</td>\n"
                        + " <td class=\"text-left\" > " + lista.getNombreMaquina() + "</td>\n"
                        + " <td class=\"text-center\"> " + lista.getPesoProducido() + "</td>\n"
                        + " <td class=\"text-center\"> " + lista.getPesoPerdido() + "</td>\n"
                        //    + " <td   class=\"text-center\"> " + lista.getPorcentajeRetal() + " %</td>\n"
                        + " <td   class=\"text-center\">"
                        + "<div class=\"container\">\n"
                        + "<div class=\"\">\n"
                        //+ "<span>" + lista.getPorcentajeRetal() + "%</span>\n"
                        + "<span></span></div>\n"
                        + "<div class=\"progress progress-sm\">\n"
                        + barra
                        + "</div>"
                        + "</td>\n"
                        + "</tr> ";
                i++;
                totalProducido += lista.getPesoProducido();
                totalRetal += lista.getPesoPerdido();
            }
            if (totalRetal == 0) {
                porcentaje = 0.0;
            } else {
                porcentaje = totalRetal * 100 / (totalProducido + totalRetal);
            }

            String totales
                    = "<tr>"
                    + " <td  class=\"text-center\"></td>\n"
                    + " <td  class=\"text-center\" style=\"font-weight: bold\"> </td>\n"
                    + " <td  class=\"text-center\" style=\"font-weight: bold\"> </td>\n"
                    + " <td  class=\"text-right\" style=\"font-weight: bold\">TOTALES:</td>\n"
                    + " <td  class=\"text-center\" style=\"font-weight: bold\">" + String.valueOf(decimalFormat.format(totalProducido)).replace(",", ".") + "</td>\n"
                    + " <td  class=\"text-center\" style=\"font-weight: bold\">" + String.valueOf(decimalFormat.format(totalRetal)).replace(",", ".") + "</td>\n"
                    + " <td  class=\"text-center\"><div class=\"container\">"
                    + "<div class=\"progress progress-sm\">\n"
                    + dameBarra(porcentaje)
                    + "</div></td>\n"
                    + "</div></tr> ";
            String pie = "</table>";
            //+ "</div>  ";
            String salida = encabezado + cuerpo + totales + pie;
            out.println(salida);

        } else {
            String encabezado
                    = "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \"  col-md-12 cellspacing=\"0\">\n "
                    + "<thead>\n <tr>\n                               "
                    + "                                    <th class=\"text-center\"  width=\"3%\">Nº</th> \n"
                    + "                                    <th class=\"text-center\"  width=\"13%\">FECHA </th> \n"
                    + "                                    <th class=\"text-center\"  width=\"6%\">OT</th> \n"
                    + "                                    <th class=\"text-left\" width=\"7%\">PROCESO</th> \n"
                    + "                                    <th class=\"text-left\" width=\"18%\">MAQUINA</th> \n"
                    + "                                    <th class=\"text-left\" width=\"25%\">OPERADOR</th> \n"
                    + "                                    <th class=\"text-left\" width=\"20%\">CAUSA RETAL</th> \n"
                    + "                                    <th class=\"text-center\" width=\"5%\">PESO(Kg)</th> \n"
                    + " \n </thead>\n";
            String cuerpo = "";
            listaHistorial = dao.listHistorial();
            int i = 1;
            for (Touch_Retal_DTO lista : listaHistorial) {
                cuerpo += "<tr>"
                        + " <td  class=\"text-center\">" + i + "</td>\n"
                        + " <td  class=\"text-center\">" + lista.getFecha() + "</td>\n"
                        + " <td  class=\"text-center\">" + lista.getIdDcto() + "</td>\n"
                        + " <td  class=\"text-left\">" + lista.getNombreProceso() + "</td>\n"
                        + " <td   class=\"text-left\" > " + lista.getNombreMaquina() + "</td>\n"
                        + " <td   class=\"text-left\"   > " + lista.getNombreOperario() + "</td>\n"
                        + " <td   class=\"text-left\"   > " + lista.getNombre_retal() + "</td>\n"
                        + " <td   class=\"text-center\" > " + lista.getPesoPerdido() + "</td>\n"
                        + "</tr> ";
                i++;
            }
            String totales = "<tr>"
                    + " <td  class=\"text-center\"></td>\n"
                    + " <td  class=\"text-center\"></td>\n"
                    + " <td  class=\"text-center\"></td>\n"
                    + " <td  class=\"text-center\"></td>\n"
                    + " <td  class=\"text-left\" ></td>\n"
                    + " <td  class=\"text-left\" ></td>\n"
                    + " <td  class=\"text-right\"   style=\"font-weight: bold\" > TOTAL: </td>\n"
                    + " <td  class=\"text-center\"  style=\"font-weight: bold\"> " + dao.sumRetalHistorial().getPesoPerdido() + "</td>\n"
                    + "</tr> ";
            String pie = "</table>";
            //+ "</div> ";
            String salida = encabezado + cuerpo + totales + pie;
            out.println(salida);
        }
        return listaHistorial;
    }

    private List<Touch_Retal_DTO> printHistorialGlobal(HttpServletRequest request, PrintWriter out) {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String idProceso = request.getParameter("idProceso");
        String idMaquina = request.getParameter("idMaquina");
        String idOperario = request.getParameter("idOperador");
        String ot = request.getParameter("idDcto").trim();
        String agrupar = request.getParameter("agrupar");
        Double totalProducido = 0.0;
        Double totalRetal = 0.0;
        Double porcentaje = 0.0;
        List<Touch_Retal_DTO> listaHistorial;
        boolean xAgrupar = Boolean.parseBoolean(agrupar);
        if (ot.equals("")) {
            ot = "0";
        }
        try {
            fechaInicial = sdfSQL.format(sdf2.parse(fechaInicial));
            fechaFinal = sdfSQL.format(sdf2.parse(fechaFinal));
        } catch (ParseException ex) {
            Logger.getLogger(TrasladoInventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Touch_Retal_DAO dao = new Touch_Retal_DAO();
        dao.setFechaInicial(fechaInicial);
        dao.setFechaFinal(fechaFinal);
        dao.setSk_proceso(Integer.parseInt(idProceso));
        dao.setSk_Operario(Integer.parseInt(idOperario));
        dao.setSk_Maquina(Integer.parseInt(idMaquina));
        dao.setIdDcto(Integer.parseInt(ot));

        String encabezado
                = "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \"  col-md-12 cellspacing=\"0\">\n "
                + "<thead>\n <tr>\n                               "
                + "                                    <th class=\"text-center\"  width=\"2%\">Nº</th> \n"
                + "                                    <th class=\"text-left\" width=\"12%\">MAQUINA</th> \n"
                + "                                    <th class=\"text-center\" width=\"5%\"> RETAL (Kg)</th> \n"
                + "                                    <th class=\"text-center\" width=\"5%\"> % RETAL</th> \n"
                + "                                    <th class=\"text-center\" width=\"30%\"></th> \n"
                + " \n </thead>\n";
        String cuerpo = "";
        listaHistorial = dao.listGlobalAgrupado();
        double sumaTotal = dao.sumRetalHistorial().getPesoPerdido();
        int i = 1;
        int j = 1;
        for (Touch_Retal_DTO lista : listaHistorial) {

            String barra = "";
            double porcentajeBarra = lista.getPesoPerdido() * 100 / sumaTotal;
            porcentajeBarra = Double.parseDouble(decimalFormat.format(porcentajeBarra).replace(",", "."));
            if (j > 8) {
                j = 1;
            }
            if (porcentajeBarra > 7) {
                barra = "<div class=\"" + dameColorBarraIterativa(j) + "\" role=\"progressbar\" style=\"width: " + porcentajeBarra + "%\" aria-valuenow=\"" + porcentajeBarra + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\">" + porcentajeBarra + "%</div></div>";
            } else {
                barra = "<div class=\"" + dameColorBarraIterativa(j) + "\" role=\"progressbar\" style=\"width: " + porcentajeBarra + "%\" aria-valuenow=\"" + porcentajeBarra + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\"></div>" + porcentajeBarra + "%</div>\n";
            }
            j++;
            cuerpo += "<tr>"
                    + " <td  class=\"text-center\">" + i + "</td>\n"
                    + " <td   class=\"text-left\" > " + lista.getNombreMaquina() + "</td>\n"
                    + " <td   class=\"text-center\" > " + lista.getPesoPerdido() + "</td>\n"
                    + " <td   class=\"text-center\" > " + String.valueOf(decimalFormat.format(porcentajeBarra)).replace(",", ".") + "</td>\n"
                    + " <td   class=\"text-center\">"
                    + "<div class=\"container\">\n"
                    + "<div class=\"\">\n"
                    + "<span></span></div>\n"
                    + "<div class=\"progress progress-sm\">\n"
                    + barra
                    + "</div>"
                    + "</td>\n"
                    + "</tr> ";
            i++;
        }
        String totales = "<tr>"
                + " <td  class=\"text-left\" ></td>\n"
                + " <td  class=\"text-right\"   style=\"font-weight: bold\" > TOTAL: </td>\n"
                + " <td  class=\"text-center\"  style=\"font-weight: bold\"> " + sumaTotal + "</td>\n"
                + " <td  class=\"text-left\" ></td>\n"
                + " <td  class=\"text-left\" ></td>\n"
                + "</tr> ";
        String pie = "</table>";
        //+ "</div> ";
        String salida = encabezado + cuerpo + totales + pie;
        out.println(salida);

        return listaHistorial;
    }

    private String dameColorBarra(double porcentaje) {
        String color = "";
        if (porcentaje >= 0 && porcentaje <= 3) {
            color = "progress-bar progress-bar-striped bg-success progress-bar-animated";
        }
        if (porcentaje > 3 && porcentaje <= 5) {
            color = "progress-bar progress-bar-striped bg-warning progress-bar-animated";
        }
        if (porcentaje > 5 && porcentaje <= 100) {
            color = "progress-bar progress-bar-striped bg-danger progress-bar-animated";
        }
        return color;
    }

    private String dameColorBarraIterativa(int i) {
        String color = "";
        if (i == 1) {
            color = "progress-bar progress-bar-striped bg-danger progress-bar-animated";

        }
        if (i == 2) {
            color = "progress-bar progress-bar-striped bg-info progress-bar-animated";

        }
        if (i == 3) {
            color = "progress-bar progress-bar-striped bg-warning progress-bar-animated";

        }
        if (i == 4) {
            color = "progress-bar progress-bar-striped bg-dark progress-bar-animated";
        }
        if (i == 5) {
            color = "progress-bar progress-bar-striped bg-success progress-bar-animated";

        }
        if (i == 6) {
            color = "progress-bar progress-bar-striped bg-secondary progress-bar-animated";
        }
        if (i == 7) {
            color = "progress-bar progress-bar-striped bg-primary progress-bar-animated";

        }
        if (i == 8) {
            color = "progress-bar progress-bar-striped purpura progress-bar-animated";

        }
        return color;
    }

    private String dameBarra(Double porcentaje) {
        String barra = "";
        String porcentajeS = String.valueOf(decimalFormat.format(porcentaje)).replace(",", ".");
        if (porcentaje > 7) {
            barra = "<div class=\"" + dameColorBarra(porcentaje) + "\" role=\"progressbar\" style=\"width: " + porcentaje + "%\" aria-valuenow=\"" + porcentaje + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\">" + porcentajeS + "%</div></div>";
        } else {
            barra = "<div class=\"" + dameColorBarra(porcentaje) + "\" role=\"progressbar\" style=\"width: " + porcentaje + "%\" aria-valuenow=\"" + porcentaje + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\"></div> " + porcentajeS + "%</div>\n";
        }
        return barra;
    }

//    private String dameBarraGlobal(Double porcentaje, int iterator) {
//        String barra = "";
//        String porcentajeS = String.valueOf(decimalFormat.format(porcentaje)).replace(",", ".");
//        if (porcentaje > 7) {
//            barra = "<div class=\"" + dameColorBarraIterativa(iterator) + "\" role=\"progressbar\" style=\"width: " + porcentaje + "%\" aria-valuenow=\"" + porcentaje + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\">" + porcentajeS + "%</div></div>";
//        } else {
//            barra = "<div class=\"" + dameColorBarraIterativa(iterator) + "\" role=\"progressbar\" style=\"width: " + porcentaje + "%\" aria-valuenow=\"" + porcentaje + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"font-weight:bold\"></div> " + porcentajeS + "%</div>\n";
//        }
//        return barra;
//    }
    private void printGraficoBarrasGlobal(HttpServletRequest request, PrintWriter out) {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String idProceso = request.getParameter("idProceso");
        String idMaquina = request.getParameter("idMaquina");
        String idOperario = request.getParameter("idOperador");
        String ot = request.getParameter("idDcto").trim();

        if (ot.equals("")) {
            ot = "0";
        }
        try {
            fechaInicial = sdfSQL.format(sdf2.parse(fechaInicial));
            fechaFinal = sdfSQL.format(sdf2.parse(fechaFinal));
        } catch (ParseException ex) {
            Logger.getLogger(TrasladoInventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Touch_Retal_DAO dao = new Touch_Retal_DAO();
        dao.setFechaInicial(fechaInicial);
        dao.setFechaFinal(fechaFinal);
        dao.setSk_proceso(Integer.parseInt(idProceso));
        dao.setSk_Operario(Integer.parseInt(idOperario));
        dao.setSk_Maquina(Integer.parseInt(idMaquina));
        dao.setIdDcto(Integer.parseInt(ot));

        List<Touch_Retal_DTO> listaMaquinas = dao.listGlobalAgrupadoBarras();
        String listaMaquinasBarras = "";
        String listaRetalBarras = "";
        String separador = ",";
        int i = 0;
        int lenghtLista = listaMaquinas.size();
        for (Touch_Retal_DTO lista : listaMaquinas) {
            i++;
            if (i == lenghtLista) {
                separador = "";
            }
            listaMaquinasBarras += lista.getNombreMaquina() + separador;
            listaRetalBarras += lista.getPesoPerdido() + separador;
        }

        String cadenaSalida = listaMaquinasBarras + "*" + listaRetalBarras;
        out.println(cadenaSalida);

    }

    private void printGraficoPieGlobal(HttpServletRequest request, PrintWriter out) {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String idProceso = request.getParameter("idProceso");
        String idMaquina = request.getParameter("idMaquina");
        String idOperario = request.getParameter("idOperador");
        String ot = request.getParameter("idDcto").trim();
        if (ot.equals("")) {
            ot = "0";
        }
        try {
            fechaInicial = sdfSQL.format(sdf2.parse(fechaInicial));
            fechaFinal = sdfSQL.format(sdf2.parse(fechaFinal));
        } catch (ParseException ex) {
            Logger.getLogger(TrasladoInventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Touch_Retal_DAO dao = new Touch_Retal_DAO();
        dao.setFechaInicial(fechaInicial);
        dao.setFechaFinal(fechaFinal);
        dao.setSk_proceso(Integer.parseInt(idProceso));
        dao.setSk_Operario(Integer.parseInt(idOperario));
        dao.setSk_Maquina(Integer.parseInt(idMaquina));
        dao.setIdDcto(Integer.parseInt(ot));

        List<Touch_Retal_DTO> listaMaquinas = dao.listGlobalAgrupadoPie();
        String listaRetal = "";
        String listaRetalPie = "";
        String separador = ",";
        int i = 0;
        int lenghtLista = listaMaquinas.size();
        for (Touch_Retal_DTO lista : listaMaquinas) {
            i++;
            if (i == lenghtLista) {
                separador = "";
            }
            listaRetal += lista.getNombre_retal() + separador;
            listaRetalPie += lista.getPesoPerdido() + separador;
        }
        String cadenaSalida = listaRetal + "*" + listaRetalPie;
        out.println(cadenaSalida);

    }
}
