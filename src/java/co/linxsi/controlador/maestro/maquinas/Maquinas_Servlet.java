package co.linxsi.controlador.maestro.maquinas;

import co.linxsi.modelo.maestro.maquinas.Maquinas_DAO;
import co.linxsi.modelo.maestro.maquinas.Maquinas_DTO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "MaquinasServlet", urlPatterns = {"/MaquinasServlet"})
public class Maquinas_Servlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        Object xIdLocal = sesion.getAttribute("idLocal");
        int xIdLocalUsuario = Integer.parseInt(String.valueOf(xIdLocal));
        try {
            String xOpcion = request.getParameter("xopcion");
            String nombreInventario = "";
            if (xOpcion.compareTo("listaBodegas") == 0) {
                Maquinas_DAO bdao = new Maquinas_DAO();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("crear") == 0) {
                String xNombre = request.getParameter("xbodega").toUpperCase();
                String xSK_Operacion = request.getParameter("xIdOperacion").toUpperCase();
                String xSkOperacion = request.getParameter("sk_operacion");
                String tMontaje = request.getParameter("tMontaje");
                String xCap = request.getParameter("capInstalada");
                String xVel = request.getParameter("velocidad");
                Maquinas_DAO bdao = new Maquinas_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_local(xIdLocalUsuario);
                int codMax = bdao.Maximo() + 1;
                bdao.setSk_maquina(codMax);
                bdao.setSk_estado(1);
                bdao.setSk_operacion(Integer.parseInt(xSK_Operacion));
                bdao.setCapacidadInstalada(Double.parseDouble(xCap));
                bdao.setTiempoMontaje(Double.parseDouble(tMontaje));
                bdao.setVelocidad(Double.parseDouble(xVel));
                bdao.ingresa();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("ListaBodegaEditar") == 0) {
                String xSkMarca = request.getParameter("xskmarca");
                Maquinas_DAO bdao = new Maquinas_DAO();
                Maquinas_DTO bdto = new Maquinas_DTO();
                bdao.setSk_maquina(Integer.parseInt(xSkMarca));
                bdto = bdao.listaUno();
                out.println(bdto.getSk_maquina() + "," + bdto.getNombreMaquina() + ","
                        + bdto.getSk_operacion() + "," + bdto.getTiempoMontaje() + ","
                        + bdto.getCapacidadInstalada() + "," + bdto.getVelocidad());
            }
            List<Operaciones_DTO> lista;
            if (xOpcion.compareTo("ActualizarNombre") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String xSkBodega = request.getParameter("skbodega");
                String xSkOperacion = request.getParameter("sk_operacion");
                String tMontaje = request.getParameter("tMontaje");
                String xCap = request.getParameter("capacidad");
                String xVel = request.getParameter("velocidad");
                Maquinas_DAO bdao = new Maquinas_DAO();
                bdao.setNombreMaquina(xNombre);
                bdao.setSk_estado(1);
                bdao.setSk_maquina(Integer.parseInt(xSkBodega));
                bdao.setSk_operacion(Integer.parseInt(xSkOperacion));
                bdao.setCapacidadInstalada(Double.parseDouble(xCap));
                bdao.setTiempoMontaje(Double.parseDouble(tMontaje));
                bdao.setVelocidad(Double.parseDouble(xVel));
                bdao.Actualiza();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("EliminaBodega") == 0) {
                String xSkBodega = request.getParameter("skbodega");
                Maquinas_DAO bdao = new Maquinas_DAO();
                bdao.setSk_operacion(Integer.parseInt(xSkBodega));
                bdao.Elimina();
                printTabla(out, bdao);
            }
        } finally {
            out.close();
        }
    }
    private String encabezado() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + " <th width=\"5%\" align=\"center\">CODIGO</th>    \n                               "
                + " <th width=\"20%\" align=\"left\">OPERACION</th>\n"
                + " <th width=\"30%\" align=\"left\">NOMBRE MAQUINA</th>\n"
                + " <th width=\"15%\" align=\"center\">VR. ESTANDAR</th>\n"
                + " <th hidden=\"true\" width=\"0%\" align=\"center\">CAPACIDAD</th>\n"
                + " <th hidden=\"true\" width=\"0%\" align=\"center\">VELOCIDAD</th>\n"
                + "<th width=\"30%\" align=\"left\">EDITAR</th>\n </tr>\n </thead>";
        return encabezado;
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
    private void printTabla(PrintWriter out, Maquinas_DAO bdao) {
        out.println(encabezado());
        List<Maquinas_DTO> lista = bdao.listaAllM();
        for (Maquinas_DTO bdto : lista) {
            String print = "<tr>\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_operacion() + ">"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getSk_maquina() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre() + "</td>"
                    + "<td width=\"30%\" align=\"left\">" + bdto.getNombreMaquina() + "</td>"
                    + "<td width=\"15%\" class=\"text-md-center\">" + bdto.getTiempoMontaje() + "</td>"
                    + "<td  hidden=\"true\" width=\"0%\" align=\"center\">" + bdto.getCapacidadInstalada() + "</td>"
                    + "<td hidden=\"true\" width=\"0%\" align=\"center\">" + bdto.getVelocidad() + "</td>"
                    + "<td width=\"30%\" align=\"left\">\n <button type=\"button\" class=\"btn-info\" onclick=\"editar(" + bdto.getSk_maquina() + ")\" name=\"xoption\" value=\"Editar\" data-toggle=\"modal\" data-target=\"#edita\"><i class=\"fas fa-edit\"></i></button></td></tr>";
        out.println(print);
        }
        out.println("</table>");
    }
}
