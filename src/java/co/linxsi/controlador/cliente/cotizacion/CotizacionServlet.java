package co.linxsi.controlador.cliente.cotizacion;

import co.linxsi.modelo.cliente.cotizacion.DAO_Cliente;
import co.linxsi.modelo.cliente.cotizacion.DTO_Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CotizacionServlet", urlPatterns = {"/CotizacionServlet"})
public class CotizacionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        Object xIdLocal = sesion.getAttribute("idLocal");
     
        try {
            String xOpcion = request.getParameter("xopcion");
            if (xOpcion.compareTo("lista") == 0) {
                printTabla(out, new DAO_Cliente());

            }
            if (xOpcion.equals("listaModal")) {
                DAO_Cliente dao = new DAO_Cliente();
                dao.setRazonSocial(request.getParameter("nombre"));
                printTablaModal(out, dao);

            }
            if (xOpcion.equals("printClienteCot")) {
                DAO_Cliente dao = new DAO_Cliente();
                dao.setNit(Integer.parseInt(request.getParameter("nit")));
                printCliente(out, dao);

            }
            if (xOpcion.compareTo("crear") == 0) {
                String razon = request.getParameter("razon").toUpperCase();
                String nit = request.getParameter("nit");
                String contacto = request.getParameter("contacto");
                DAO_Cliente bdao = new DAO_Cliente();
                bdao.setRazonSocial(razon);
                bdao.setContacto(contacto);
                bdao.setNit(Integer.parseInt(nit));
                bdao.setCreado("GETDATE()");
                bdao.ingresa();
                printTabla(out, bdao);

            }
            if (xOpcion.compareTo("ListaEditar") == 0) {
                String nit = request.getParameter("nit");
                DAO_Cliente dao = new DAO_Cliente();
                dao.setNit(Integer.parseInt(nit));
                DTO_Cliente dto = dao.getUnCliente();
                out.println(dto.getNit() + "," + dto.getRazonSocial() + "," + dto.getContacto());
            }

            if (xOpcion.equals("Actualizar")) {
                String xNombre = request.getParameter("razon").toUpperCase();
                int nit = Integer.parseInt(request.getParameter("nit"));
                String contacto = request.getParameter("contacto");
                DAO_Cliente dao = new DAO_Cliente();
                dao.setRazonSocial(xNombre);
                dao.setContacto(contacto);
                dao.setNit(nit);
                dao.actualizar();
                printTabla(out, dao);

            }

        } finally {
            out.close();
        }
    }

    private String encabezado() {
        String encabezado = "<table id=\"tablaClientes\" border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"3%\">Nº</th> \n"
                + "                                        <th class=\"text-center\" width=\"10%\">NIT/CC</th> \n"
                + "                                        <th class=\"text-left\" width=\"35%\">Razon Social</th> \n"
                + "                                        <th class=\"text-left\" width=\"35%\">Contacto</th> \n"
                + "                                        <th class=\"text-left\" width=\"10%\">Fecha Registro</th> \n"
                + "                                        <th class=\"text-center\" width=\"10%\">Acción</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String encabezadoModal() {
        String encabezado = "<table id=\"tablaClientesModal\" border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"3%\">Nº</th> \n"
                + "                                        <th class=\"text-center\" width=\"8%\">NIT/CC</th> \n"
                + "                                        <th class=\"text-left\" width=\"20%\">Razon Social</th> \n"
                + "                                        <th class=\"text-left\" width=\"20%\">Contacto   </th> \n"
                + "                                        <th class=\"text-center\" width=\"3%\"></th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private void printTabla(PrintWriter out, DAO_Cliente bdao) {
        out.println(encabezado());
        List<DTO_Cliente> lista = bdao.getAll();
        int i = 1;
        for (DTO_Cliente bdto : lista) {
            String print
                    = "<tr>\n"
                    + "<td align=\"center\">" + i++ + "</td>"
                    + "<td align=\"center\">" + bdto.getNit() + "</td>"
                    + "<td width=\"35%\" align=\"left\">" + bdto.getRazonSocial() + "</td>"
                    + "<td width=\"35%\" align=\"left\">" + bdto.getContacto() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getCreado() + "</td>"
                    + "<td width=\"10%\" align=\"center\">\n <button type=\"button\" class=\"btn-success\" onclick=\"editarCliente(" + bdto.getNit() + ")\" name=\"xoption\" value=\"Editar\" data-toggle=\"modal\" data-target=\"#modal-act-cliente\"><i class=\"fas fa-edit\"></i></button></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaModal(PrintWriter out, DAO_Cliente bdao) {
        out.println(encabezadoModal());

        List<DTO_Cliente> lista = bdao.getAllBusqueda();
        int i = 1;
        for (DTO_Cliente dto : lista) {
            String selected = i == 1 ? " checked" : "";
            String print
                    = "<tr>\n"
                    + "<td align=\"center\">" + i++ + "</td>"
                    + "<td align=\"center\">" + dto.getNit() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + dto.getRazonSocial() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + dto.getContacto() + "</td>"
                    + "<td width=\"3%\"  align=\"center\"><input type=\"radio\"  " + selected + " name=\"xoption\" value=\"Editar\" onclick=\"guardarSeleccion(" + dto.getNit() + ")\"></input></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private void printCliente(PrintWriter out, DAO_Cliente bdao) {
        DTO_Cliente dto = bdao.getUnCliente();
        String print
                = "<strong>" + dto.getRazonSocial() + "</strong><br>"
                + dto.getContacto() + "<br>"
                + "Nit/CC: " + dto.getNit()+ "<input type=\"text\" hidden  name=\"xIdCliente3\"  value=\"" + dto.getNit()
                +"~"+dto.getRazonSocial()
                +"~"+dto.getContacto()+
                "\"></input>";
        out.println(print);

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
