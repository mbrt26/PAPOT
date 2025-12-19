package co.linxsi.controlador.touch.principal;

/*   Document   :TouchPrincipalServlet
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.*/
import co.linxsi.modelo.touch.principal.Touch_Principal_DAO;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TouchPrincipalServlet", urlPatterns = {"/TouchPrincipalServlet"})
public class TouchPrincipalServlet extends HttpServlet {

    private final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SimpleDateFormat sdf4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private final SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    private final SimpleDateFormat sdfSQL2 = new SimpleDateFormat("yyyyMMdd");
    private String idOperacion;
    private String numeroOrden;
    private UsuarioBean usuarioBean;
    private String mensaje = "";
    private String pattern = "###,###.##";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private Touch_Principal_DAO dao = new Touch_Principal_DAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String xOpcion = request.getParameter("xopcion");
            numeroOrden = request.getParameter("numeroOrden");
            idOperacion = request.getParameter("idOperacion");
            if (xOpcion.equals("printOrdenTrabajo")) {
                printOrdenTrabajo(out);
            }
            if (xOpcion.equals("printFechaFinMaquina")) {
                String idMaquina = request.getParameter("idMaquina");
                printFechaFinMaquina(out, idMaquina);

          
                
            }

            if (xOpcion.equals("printPesoBascula")) {
                String ip = request.getRemoteAddr();
                printPeso(out,ip);

            }

        } finally {
            out.close();
        }
    }

    private String encabezado() {
        String encabezado = "<tr>\n"
                + "                                        <td width=\"5%\" bgcolor=\"#FFFFFF\" align=\"left\">O.T.</td>\n"
                + "                                        <td bgcolor=\"#FFFFFF\" align=\"25%\">REFERENCIA CLIENTE</td>\n"
                + "                                        <td bgcolor=\"#FFFFFF\" align=\"5%\">CAN.PED.</td>\n"
                + "                                        <td bgcolor=\"#FFFFFF\" align=\"5%\">KG.PED</td>\n"
                + "                                        <td bgcolor=\"#FFFFFF\" align=\"5%\">MAQUINA</td>\n"
                + "                                    </tr>";

        return encabezado;
    }

    private String pieTablaParo() {
        String pie = "</table></div>";
        return pie;
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

    private void printOrdenTrabajo(PrintWriter out) {

        DecimalFormat df0 = new DecimalFormat("###,###,###,###");
        List<FachadaPluFicha> listaPedido = dao.listaOTProgramaTouch_Operacion_Pedido(idOperacion, numeroOrden);
        StringBuilder sb = new StringBuilder();
        sb.append(encabezado());

        for (FachadaPluFicha pedido : listaPedido) {
            sb.append("<tr>");
            sb.append("  <td width=\"5%\" align=\"left\" bgcolor=\"" + pedido.getIdColor() + "\">" + pedido.getNumeroOrden() + "-" + pedido.getItemPadre() + "\n"
                    + "                                            <br><input type=\"radio\" name=\"xIdLogItemPadre\" value=\"" + pedido.getIdLog() + "~" + pedido.getItemPadre() + "\" style=\"width:30px;height:30px;border-width:thin;border-style:solid;border-color:green;color:#000000;\" checked/>\n"
                    + "                                        </td>");

            sb.append("<td bgcolor=\"" + pedido.getIdColor() + "\" align=\"25%\">" + pedido.getReferenciaCliente() + "</td>\n");
            sb.append("<td bgcolor=\"" + pedido.getIdColor() + "\" align=\"5%\">" + df0.format(pedido.getCantidadPendiente()) + "</td>");
            sb.append("<td bgcolor=\"" + pedido.getIdColor() + "\" align=\"5%\">" + df0.format(pedido.getPesoPendiente()) + "</td>");
            sb.append("<td bgcolor=\"" + pedido.getIdColor() + "\" align=\"5%\">" + pedido.getNombreItem() + "</td>");
            sb.append("</tr>");
        }

        out.print(sb.toString());

    }

    private void printFechaFinMaquina(PrintWriter out, String idMaquina) {
        System.out.println("Print Fecha Fin Maquina");
        Date fecha = dao.getDateByMachine(idMaquina);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaMaquina = sdf.format(fecha);
        out.print(fechaMaquina);
    }
    private void printPeso(PrintWriter out, String ip) {
        System.out.println("Print Peso Bascula");
        Double peso = dao.getWeightByIP(ip);

        out.print(peso);
    }

}
