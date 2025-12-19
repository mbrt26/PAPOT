package co.linxsi.controlador.maestro.paro_maquina;

import co.linxsi.controlador.maestro.bodegas.*;

import co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DAO;
import co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ParoMaquinaServlet", urlPatterns = {"/ParoMaquinaServlet"})
public class ParoMaquinaServlet extends HttpServlet {

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
                printTabla(out, new Paro_Maquina_DAO());
                System.out.println("");

            }
            if (xOpcion.compareTo("crear") == 0) {
                String xNombre = request.getParameter("xbodega").toUpperCase();
                Paro_Maquina_DAO bdao = new Paro_Maquina_DAO();
                bdao.setNombre_paro_maquina(xNombre);
                bdao.setSk_paro_maquina(bdao.Maximo() + 1);
                bdao.setSk_estado(1);
                bdao.ingresa();
                printTabla(out, bdao);

            }
            if (xOpcion.compareTo("ListaBodegaEditar") == 0) {
                String xSkMarca = request.getParameter("xskmarca");
                Paro_Maquina_DAO bdao = new Paro_Maquina_DAO();
                Paro_Maquina_DTO bdto = new Paro_Maquina_DTO();
                bdao.setSk_paro_maquina(Integer.parseInt(xSkMarca));
                bdto = bdao.listaUno();
                out.println(bdto.getSk_paro_maquina() + "," + bdto.getNombre_paro_maquina());
            }
            List<Paro_Maquina_DTO> lista;
            if (xOpcion.compareTo("ActualizarNombre") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String xSkBodega = request.getParameter("skbodega");
                Paro_Maquina_DAO bdao = new Paro_Maquina_DAO();
                bdao.setNombre_paro_maquina(xNombre);
                bdao.setSk_estado(1);
                bdao.setSk_paro_maquina(Integer.parseInt(xSkBodega));
                bdao.Actualiza();
                printTabla(out, bdao);

            }
            if (xOpcion.compareTo("EliminaBodega") == 0) {
                String xSkBodega = request.getParameter("skbodega");
                Paro_Maquina_DAO bdao = new Paro_Maquina_DAO();
                bdao.setSk_paro_maquina(Integer.parseInt(xSkBodega));
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
                + " <th width=\"10%\" align=\"center\">CODIGO</th>    \n                               "
                + " <th width=\"30%\" align=\"left\">PARO MAQUINA</th>\n"
                + "<th width=\"60%\" align=\"left\">EDITAR</th>\n </tr>\n </thead>";
        return encabezado;
    }
     private void printTabla(PrintWriter out, Paro_Maquina_DAO bdao) {
        out.println(encabezado());
         List<Paro_Maquina_DTO> lista = bdao.listaAll();
        for (Paro_Maquina_DTO bdto : lista) {
            String print =
                    "<tr>\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_paro_maquina()+ ">"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getSk_paro_maquina() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre_paro_maquina()+ "</td>"
                    + "<td width=\"75%\" align=\"left\">\n <button type=\"button\" class=\"btn-info\" onclick=\"editar(" + bdto.getSk_paro_maquina()+ ")\" name=\"xoption\" value=\"Editar\" data-toggle=\"modal\" data-target=\"#edita\"><i class=\"fas fa-edit\"></i></button></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
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
}
