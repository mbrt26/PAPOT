package co.linxsi.controlador.maestro.local;

import co.linxsi.modelo.maestro.local.Local_DAO;
import co.linxsi.modelo.maestro.local.Local_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LocalServlet", urlPatterns = {"/LocalServlet"})
public class LocalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        Object xIdLocal = sesion.getAttribute("idLocal");
//        int xIdLocalUsuario = Integer.parseInt(String.valueOf(xIdLocal));
        Local_DAO dao = new Local_DAO();
        try {
            String xOpcion = request.getParameter("xopcion");
            if (xOpcion.equals("getDatos")) {
               /* System.out.println("getDatos");
                Local_DTO listaLocal = dao.getLocal();
                out.print(listaLocal.toString());*/
            }
            if (xOpcion.equals("guardarDatos")) {
                System.out.println("guardarDatos");
                dao.setNombreLocal(request.getParameter("nombreLocal"));
                dao.setRazonSocial(request.getParameter("razonSocial"));
                dao.setNIT(request.getParameter("nit"));
                dao.setEmail(request.getParameter("email"));
                dao.setDireccion(request.getParameter("direccion"));
                dao.setIdCiudad(request.getParameter("idCiudad"));
                dao.setTelefono(request.getParameter("telefono"));
                dao.setFax(request.getParameter("fax"));
                dao.setResFiscal1(request.getParameter("idRes1"));
                dao.setResFiscal2(request.getParameter("idRes2"));
                dao.setResFiscal3(request.getParameter("idRes3"));
                dao.setResFiscal4(request.getParameter("idRes4"));
                dao.setIdRegimen(Integer.parseInt(request.getParameter("idRegimen")));
                dao.setTipoOperacion(request.getParameter("idOperacion"));
                dao.setResolucion(request.getParameter("resolucion"));
                dao.setResolucion2(request.getParameter("resolucion2"));
                dao.actualiza();
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
}
