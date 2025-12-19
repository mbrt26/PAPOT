package co.linxsi.controlador.maestro.calificadores;

import co.linxsi.modelo.maestro.calificadores.Calificadores_DAO;
import co.linxsi.modelo.maestro.calificadores.Calificadores_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CalificadoresServlet", urlPatterns = {"/CalificadoresServlet"})
public class CalificadoresServlet extends HttpServlet {

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
                printTabla(out, new Calificadores_DAO());
            }
            if (xOpcion.compareTo("crear") == 0) {
                String xNombre = request.getParameter("xbodega").toUpperCase();
                int sk_pondera = Integer.parseInt(request.getParameter("sk_pondera"));
                Calificadores_DAO bdao = new Calificadores_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_calificador(bdao.Maximo() + 1);
                bdao.setSk_pondera(sk_pondera);
                bdao.setSk_estado(1);
                bdao.ingresa();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("ListaBodegaEditar") == 0) {
                String xSkMarca = request.getParameter("xskmarca");
                Calificadores_DAO bdao = new Calificadores_DAO();
                Calificadores_DTO bdto = new Calificadores_DAO();
                bdao.setSk_calificador(Integer.parseInt(xSkMarca));
                bdto = bdao.listaUno();
                out.println(bdto.getSk_calificador() + "," + bdto.getNombre()+","+bdto.getSk_pondera());
            }
            List<Calificadores_DAO> lista;
            if (xOpcion.compareTo("ActualizarNombre") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String xSkBodega = request.getParameter("skbodega");
                String xPondera = request.getParameter("pondera");
                Calificadores_DAO bdao = new Calificadores_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_estado(1);
                bdao.setSk_calificador(Integer.parseInt(xSkBodega));
                bdao.setSk_pondera(Integer.parseInt(xPondera));
                bdao.Actualiza();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("EliminaBodega") == 0) {
                String xSkBodega = request.getParameter("skbodega");
                Calificadores_DAO bdao = new Calificadores_DAO();
                bdao.setSk_calificador(Integer.parseInt(xSkBodega));
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
                + " <th width=\"15%\" align=\"left\">CALIFICADOR</th>\n"
                + " <th width=\"10%\" align=\"center\">PONDERA COSTO</th>\n"
                + "<th width=\"70%\" align=\"left\">EDITAR</th>\n </tr>\n </thead>";
        return encabezado;
    }

    private void printTabla(PrintWriter out, Calificadores_DAO bdao) {
        out.println(encabezado());
        List<Calificadores_DTO> lista = bdao.listaAll();
        for (Calificadores_DTO bdto : lista) {
            String print
                    = "<tr>\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_calificador() + ">"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getSk_calificador() + "</td>"
                    + "<td width=\"15%\" align=\"left\">" + bdto.getNombre() + "</td>"
                    + "<td width=\"10%\" align=\"center\">" + resPondera(bdto.getSk_pondera()) + "</td>"
                    + "<td width=\"70%\" align=\"left\">\n <button type=\"button\" class=\"btn-info\" onclick=\"editar(" + bdto.getSk_calificador() + ")\" name=\"xoption\" value=\"Editar\" data-toggle=\"modal\" data-target=\"#edita\"><i class=\"fas fa-edit\"></i></button></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private String resPondera(int sk_pondera) {
        String res = "";
        switch (sk_pondera) {
            case 1:
                res = "Si";
                break;
            case 2:
                res = "No";
                break;
        }
        return res;
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
