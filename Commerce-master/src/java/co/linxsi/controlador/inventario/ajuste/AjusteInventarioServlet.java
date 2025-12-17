    package co.linxsi.controlador.inventario.ajuste;

import co.linxsi.controlador.maestro.bodegas.*;
import co.linxsi.modelo.maestro.bodega.BodegaDAO;
import co.linxsi.modelo.maestro.bodega.BodegaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AjusteInventarioServlet", urlPatterns = {"/AjusteInventarioServlet"})
public class AjusteInventarioServlet extends HttpServlet {

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
                printTabla(out, new BodegaDAO());

            }
            if (xOpcion.compareTo("crear") == 0) {
                String xNombre = request.getParameter("xbodega").toUpperCase();
                String xTipoBodega = request.getParameter("xsk_tipo_bodega");
                BodegaDAO bdao = new BodegaDAO();
                bdao.setNombre(xNombre);
                bdao.setSk_local(xIdLocalUsuario);
                bdao.setSk_bodega(bdao.Maximo() + 1);
                bdao.setSk_estado(1);
                bdao.setActualiza(0);
                bdao.setSk_tipo_bodega(Integer.parseInt(xTipoBodega));
                bdao.ingresa();
                printTabla(out, bdao);

            }
            if (xOpcion.compareTo("ListaBodegaEditar") == 0) {
                String xSkMarca = request.getParameter("xskmarca");
                BodegaDAO bdao = new BodegaDAO();
                BodegaDTO bdto = new BodegaDTO();
                bdao.setSk_bodega(Integer.parseInt(xSkMarca));
                bdto = bdao.listaUno();
                out.println(bdto.getSk_bodega() + "," + bdto.getNombre()+ "," +bdto.getSk_tipo_bodega());
            }
            List<BodegaDTO> lista;
            if (xOpcion.compareTo("ActualizarNombre") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String xSkBodega = request.getParameter("skbodega");
                String xTipoBodega = request.getParameter("xTipoBodega");
                BodegaDAO bdao = new BodegaDAO();
                bdao.setNombre(xNombre);
                bdao.setSk_estado(1);
                bdao.setSk_bodega(Integer.parseInt(xSkBodega));
                bdao.setSk_tipo_bodega(Integer.parseInt(xTipoBodega));
                bdao.Actualiza();
                printTabla(out, bdao);

            }
            if (xOpcion.compareTo("EliminaBodega") == 0) {
                String xSkBodega = request.getParameter("skbodega");
                BodegaDAO bdao = new BodegaDAO();
                bdao.setSk_bodega(Integer.parseInt(xSkBodega));
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
                + " <th width=\"10%\" class=\"text-center\">CODIGO</th>    \n                               "
                + " <th width=\"30%\" align=\"left\">NOMBRE BODEGA</th>\n"
                + " <th width=\"20%\" align=\"left\">CALIFICADOR</th>\n"
                + "<th width=\"60%\" align=\"left\">EDITAR</th>\n </tr>\n </thead>";
        return encabezado;
    }
     private void printTabla(PrintWriter out, BodegaDAO bdao) {
        out.println(encabezado());
         List<BodegaDTO> lista = bdao.listaAll();
        for (BodegaDTO bdto : lista) {
            String print =
                    "<tr>\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_bodega() + ">"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getSk_bodega() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombreTipoBodega()+"</td>"
                    + "<td width=\"75%\" align=\"left\">\n <button type=\"button\" class=\"btn-info\" onclick=\"editar(" + bdto.getSk_bodega() + ")\" name=\"xoption\" value=\"Editar\" data-toggle=\"modal\" data-target=\"#edita\"><i class=\"fas fa-edit\"></i></button></td>"
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
