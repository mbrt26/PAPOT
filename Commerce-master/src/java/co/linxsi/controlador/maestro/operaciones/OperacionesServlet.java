package co.linxsi.controlador.maestro.operaciones;

import co.linxsi.modelo.maestro.accesorios.Accesorios_DAO;
import co.linxsi.modelo.maestro.accesorios.Accesorios_DTO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DAO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DAO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Paro_DAO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Paro_DTO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Retal_DAO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Retal_DTO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Unidad_DAO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Unidad_DTO;
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OperacionesServlet", urlPatterns = {"/OperacionesServlet"})
public class OperacionesServlet extends HttpServlet {

    DecimalFormat formateador = new DecimalFormat("###,###");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        Object xIdLocal = sesion.getAttribute("idLocal");
        int xIdLocalUsuario = Integer.parseInt(String.valueOf(xIdLocal));
        try {
            String xOpcion = request.getParameter("xopcion");

            if (xOpcion.compareTo("listaBodegas") == 0) {
                Operaciones_DAO bdao = new Operaciones_DAO();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("crear") == 0) {
                String xNombre = request.getParameter("xbodega").toUpperCase();
                String xServicios = request.getParameter("servicios");
                String xArriendo = request.getParameter("arriendo");
                String xManoObra = request.getParameter("mano_obra");
                String xConteo = request.getParameter("conteo");
                String xCostoRetal = request.getParameter("costo_retal");
                Operaciones_DAO bdao = new Operaciones_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_local(xIdLocalUsuario);
                int codMax = bdao.Maximo() + 1;
                bdao.setSk_operacion(codMax);
                bdao.setSk_estado(1);
                bdao.setConteo(Integer.parseInt(xConteo));
                bdao.setCostoServicios(Double.parseDouble(xServicios));
                bdao.setCostoArriendo(Double.parseDouble(xArriendo));
                bdao.setCostoManoObra(Double.parseDouble(xManoObra));
                bdao.setCosto_retal(Double.parseDouble(xCostoRetal));
                bdao.ingresa();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("MateriaEditar") == 0) {

                String xSkMarca = request.getParameter("xskmarca");
                Proceso_Materia_DAO bdao = new Proceso_Materia_DAO();
                Proceso_Materia_DTO bdto = new Proceso_Materia_DTO();
                bdao.setSk_operacion(Integer.parseInt(xSkMarca));
                bdto = bdao.listaUno();
                out.println(bdto.getSk_plu() + "," + bdto.getNombre() + "," + bdto.getCostoMateriaPrima());

            }
            if (xOpcion.compareTo("ListaBodegaEditar") == 0) {
                String xSkMarca = request.getParameter("xskmarca");
                Operaciones_DAO bdao = new Operaciones_DAO();
                Operaciones_DTO bdto = new Operaciones_DTO();
                bdao.setSk_operacion(Integer.parseInt(xSkMarca));
                bdto = bdao.listaUno();
                out.println(bdto.getSk_operacion() + "," + bdto.getNombre() + "," + bdto.getCostoServicios()
                        + "," + bdto.getCostoArriendo() + "," + bdto.getCostoManoObra() + "," + bdto.getConteo() + "," + bdto.getCosto_retal());
            }
           
            if (xOpcion.compareTo("ActualizarNombre") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String xSkBodega = request.getParameter("skbodega");
                String servicio = request.getParameter("servicio");
                String arriendo = request.getParameter("arriendo");
                String manoObra = request.getParameter("manoObra");
                String conteo = request.getParameter("conteo");
                String costo = request.getParameter("costo");
                Operaciones_DAO bdao = new Operaciones_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_estado(1);
                bdao.setSk_operacion(Integer.parseInt(xSkBodega));
                bdao.setCostoServicios(Double.parseDouble(servicio));
                bdao.setCostoArriendo(Double.parseDouble(arriendo));
                bdao.setCostoManoObra(Double.parseDouble(manoObra));
                bdao.setConteo(Integer.parseInt(conteo));
                bdao.setCosto_retal(Double.parseDouble(costo));
                bdao.Actualiza();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("ActualizarNombreMateria") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String costo = request.getParameter("costo");
                String sk_materia = request.getParameter("sk");
                Proceso_Materia_DAO bdao = new Proceso_Materia_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_estado(1);
                bdao.setCostoMateriaPrima(Double.parseDouble(costo));
                bdao.setSk_operacion(Integer.parseInt(sk_materia));
                bdao.Actualiza();
                printTablaMateria(out, bdao);
            }
            if (xOpcion.compareTo("ActualizarNombreAccesorio") == 0) {
                String xNombre = request.getParameter("xnombre").toUpperCase();
                String costo = request.getParameter("costo");
                String sk_materia = request.getParameter("sk");
                Proceso_Materia_DAO bdao = new Proceso_Materia_DAO();
                bdao.setNombre(xNombre);
                bdao.setSk_estado(1);
                bdao.setCostoMateriaPrima(Double.parseDouble(costo));
                bdao.setSk_operacion(Integer.parseInt(sk_materia));
                bdao.Actualiza();

                printTablaMateria(out, bdao);
            }
            if (xOpcion.equals("insertarParoMaquina")) {
                String sk_proceso = request.getParameter("sk_proceso");
                String sk_paro_maquina = request.getParameter("sk_paro_maquina");
                Proceso_Paro_DAO pDao = new Proceso_Paro_DAO();
                pDao.setSk_paro_maquina(Integer.parseInt(sk_paro_maquina));
                pDao.setSk_operacion(Integer.parseInt(sk_proceso));
                pDao.setSk_estado(1);
                pDao.insert();
                printTablaParo(out, pDao);
            }
            if (xOpcion.equals("insertarRetal")) {
                String sk_proceso = request.getParameter("sk_proceso");
                String sk_retal = request.getParameter("sk_retal");
                Proceso_Retal_DAO pDao = new Proceso_Retal_DAO();
                pDao.setSk_retal(Integer.parseInt(sk_retal));
                pDao.setSk_operacion(Integer.parseInt(sk_proceso));
                pDao.setSk_estado(1);
                pDao.insert();
                printTablaRetal(out, pDao);
            }
            if (xOpcion.equals("insertarUnidad")) {
                String sk_proceso = request.getParameter("sk_proceso");
                String sk_unidad = request.getParameter("sk_unidad");
                Proceso_Unidad_DAO pDao = new Proceso_Unidad_DAO();
                pDao.setSk_unidad(Integer.parseInt(sk_unidad));
                pDao.setSk_operacion(Integer.parseInt(sk_proceso));
                pDao.setSk_estado(1);
                pDao.insert();
                printTablaUnidad(out, pDao);
            }

            if (xOpcion.compareTo("EliminaBodega") == 0) {
                String xSkBodega = request.getParameter("skbodega");
                Operaciones_DAO bdao = new Operaciones_DAO();
                bdao.setSk_operacion(Integer.parseInt(xSkBodega));
                bdao.Elimina();
                printTabla(out, bdao);
            }
            if (xOpcion.compareTo("borraMateria") == 0) {
                String skMateriaProceso = request.getParameter("xskmarca");
                Proceso_Materia_DAO bdao = new Proceso_Materia_DAO();
                bdao.setSk_operacion_materia(Integer.parseInt(skMateriaProceso));
                bdao.eliminarMateria();
                printTablaMateria(out, bdao);
            }
            if (xOpcion.compareTo("eliminarParo") == 0) {
                String skProcesoParo = request.getParameter("sk_proc_paro");
                Proceso_Paro_DAO bdao = new Proceso_Paro_DAO();
                bdao.setSk_operaciones_paro(Integer.parseInt(skProcesoParo));
                bdao.eliminarParo();
            }
            if (xOpcion.compareTo("eliminarRetal") == 0) {
                String skProcesoParo = request.getParameter("sk_proc_retal");
                Proceso_Retal_DAO bdao = new Proceso_Retal_DAO();
                bdao.setSk_operacion_retal(Integer.parseInt(skProcesoParo));
                bdao.eliminarRetal();
            }
            if (xOpcion.compareTo("eliminarUnidad") == 0) {
                String skProcesoUnidad = request.getParameter("sk_proc_unidad");
                Proceso_Unidad_DAO bdao = new Proceso_Unidad_DAO();
                bdao.setSk_operacion_unidad(Integer.parseInt(skProcesoUnidad));
                bdao.eliminarUnidad();
            }
            if (xOpcion.equals("actTabParo")) {
                printTablaParo(out, new Proceso_Paro_DAO());
            }
            if (xOpcion.equals("actTabRetal")) {
                printTablaRetal(out, new Proceso_Retal_DAO());
            }
            if (xOpcion.equals("actTabUnidad")) {
                printTablaUnidad(out, new Proceso_Unidad_DAO());
            }
            if (xOpcion.equals("actTabMateria")) {
                printTablaMateria(out, new Proceso_Materia_DAO());
            }
            if (xOpcion.equals("actTabAccesorio")) {
                printTablaAccecsorio(out, new Accesorios_DAO());
            }
            if (xOpcion.equals("agregarPLU")) {
                Proceso_Materia_DAO bdao = new Proceso_Materia_DAO();
                String sk_plu = request.getParameter("sk_plu");
                String sk_operacion = request.getParameter("sk_operacion");
                bdao.setSk_plu(Integer.parseInt(sk_plu));
                bdao.setSk_operacion(Integer.parseInt(sk_operacion));
                bdao.insert();
            }

            if (xOpcion.compareTo("BuscarProductosEnModal") == 0) {
                String nombreProducto = request.getParameter("xproducto");
                ColaboraPlu pdao = new ColaboraPlu();
                out.println(EncabezadoModal());
                List<FachadaPluBean> listaProducto = pdao.listaNombrePluTop("'%" + nombreProducto + "%'");
                for (FachadaPluBean pdto : listaProducto) {
                    out.println("<tr>"
                            + " <td width=\"10%\" class=\"text-center\">" + pdto.getIdPlu() + "</td>"
                            + " <td width=\"65%\" class=\"text-left\">" + pdto.getNombreCategoria() + " " + pdto.getNombrePlu() + "</td>"
                            + "<td width=\"25%\" align=\"left\"><input class=\"form-control input-group-append col-3\" type=\"text\" id=\"CostoMat\" title=\"Escriba nuevo costo\" placeholder=\"Costo\" value =\""
                            + pdto.getVrCostoStr()
                            + "\"></td>"
                            + " <td width=\"5%\">"
                            + "<button  type=\"button\" class=\"btn-success\" data-dismiss=\"modal\" onclick=\"agregar(" + pdto.getIdPlu() + ")\" ><i class=\"fas fa-plus-circle\"></i></button>"
                            + "</td>"
                            + " </tr>");
                }
                out.println("</table>");

            }
            if (xOpcion.compareTo("crearMateria") == 0) {
                String nombreMateria = request.getParameter("matPrima");
                String costo = request.getParameter("costo");
                Proceso_Materia_DAO dao = new Proceso_Materia_DAO();
                dao.setCostoMateriaPrima(Double.parseDouble(costo));
                dao.setSk_estado(1);
                dao.setNombre(nombreMateria);
                dao.insert();
                printTablaMateria(out, new Proceso_Materia_DAO());

            }
            if (xOpcion.equals("crearAccesorio")) {
                String nombreMateria = request.getParameter("nombre");
                String costo = request.getParameter("precio");
                Accesorios_DAO dao = new Accesorios_DAO();
                dao.setPrecio(xIdLocalUsuario);
                dao.setSk_estado(1);
                dao.setNombre(nombreMateria);
                dao.insert();
                printTablaMateria(out, new Proceso_Materia_DAO());

            }
        } finally {
            out.close();
        }
    }

    private String encabezado() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + " <th width=\"10%\" class=\"text-center\">CODIGO</th>    \n                               "
                + " <th width=\"20%\" align=\"left\">NOMBRE PROCESO</th>\n"
                + " <th width=\"15%\"class=\"text-center\">COSTO SERVICIOS</th>\n"
                + " <th width=\"15%\" class=\"text-center\">COSTO ARRIENDO</th>\n"
                + " <th width=\"15%\" class=\"text-center\">MANO DE OBRA</th>\n"
                + " <th width=\"15%\" class=\"text-center\">COSTO TOTAL</th>\n"
                + " <th width=\"15%\" class=\"text-center\">CONTROL TIEMPO</th>\n"
                + "<th width=\"10\"class=\"text-center\">EDITAR</th>\n </tr>\n </thead>";

        return encabezado;
    }

    private void printTabla(PrintWriter out, Operaciones_DAO bdao) {
        out.println(encabezado());
        List<Operaciones_DTO> lista = bdao.listaAll();
        for (Operaciones_DTO bdto : lista) {
            String print
                    = ("<tr>"
                    + "<input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_operacion() + ">"
                    + "<td width=\"10%\" align=\"center\">" + bdto.getSk_operacion() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre() + "</td>"
                    + "<td width=\"15%\" align=\"center\">" + formateador.format(bdto.getCostoServicios()) + "</td>"
                    + "<td width=\"15%\" align=\"center\">" + formateador.format(bdto.getCostoArriendo()) + "</td>"
                    + "<td width=\"15%\" align=\"center\">" + formateador.format(bdto.getCostoManoObra()) + "</td>"
                    + "<td width=\"15%\" align=\"center\">" + formateador.format(bdto.getCosto_retal()) + "</td>"
                    + "<td width=\"15%\" align=\"center\">" + dameEstado(bdto) + "</td>"
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-info\" onclick=\"editar(" + bdto.getSk_operacion() + ")\" name=\"xoption\" value=\"Editar\" data-toggle=\"modal\" data-target=\"#edita\"><i class=\"fas fa-edit\"></i></td>                    "
                    + "</tr>");
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaParo(PrintWriter out, Proceso_Paro_DAO bdao) {
        out.println(encabezadoParo());
        List<Proceso_Paro_DTO> lista = bdao.listaAll();
        for (Proceso_Paro_DTO bdto : lista) {
            String print
                    = ("<tr>"
                    + "<input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_operaciones_paro() + ">"
                    + "<td width=\"10%\" align=\"center\">" + bdto.getSk_operaciones_paro() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre_operacion() + "</td>"
                    + "<td width=\"50%\" align=\"left\">" + bdto.getNombre_paro_maquina() + "</td>"
                    + "<td width=\"20%\" align=\"center\"><button type=\"button\" class=\"btn-danger\" onclick=\"eliminarParo(" + bdto.getSk_operaciones_paro() + ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-trash-alt\"></i></td>                    "
                    + "</tr>");
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

    private String encabezadoParo() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + " <th width=\"10%\" class=\"text-center\">CODIGO</th>    \n                               "
                + " <th width=\"20%\" align=\"left\">NOMBRE PROCESO</th>\n"
                + " <th width=\"50%\" align=\"left\">PARO MAQUINA</th>\n"
                + "<th width=\"20\" class=\"text-center\">RETIRAR</th>\n </tr>\n </thead>";
        return encabezado;
    }

    private String encabezadoRetal() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + " <th width=\"10%\" align=\"center\">CODIGO</th>    \n                               "
                + " <th width=\"20%\" align=\"left\">NOMBRE PROCESO</th>\n"
                + " <th width=\"50%\" align=\"left\">RETAL</th>\n"
                + "<th width=\"20\" class=\"text-center\">RETIRAR</th>\n </tr>\n </thead>";
        return encabezado;
    }

    private String encabezadoUnidades() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + " <th width=\"10%\" align=\"center\">CODIGO</th>    \n                               "
                + " <th width=\"20%\" align=\"left\">PROCESO</th>\n"
                + " <th width=\"50%\" align=\"left\">UNIDAD</th>\n"
                + "<th width=\"20\" class=\"text-center\">RETIRAR</th>\n </tr>\n </thead>";
        return encabezado;
    }

    private void printTablaRetal(PrintWriter out, Proceso_Retal_DAO bdao) {
        out.println(encabezadoRetal());
        List<Proceso_Retal_DTO> lista = bdao.listaAll_();
        for (Proceso_Retal_DTO bdto : lista) {
            String print
                    = ("<tr>"
                    + "<input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_operacion_retal() + ">"
                    + "<td width=\"10%\" align=\"center\">" + bdto.getSk_operacion_retal() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre_operacion() + "</td>"
                    + "<td width=\"50%\" align=\"left\">" + bdto.getNombre_retal() + "</td>"
                    + "<td width=\"20%\" align=\"center\"><button type=\"button\" class=\"btn-danger\" onclick=\"eliminarRetal(" + bdto.getSk_operacion_retal() + ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-trash-alt\"></i></td>                    "
                    + "</tr>");
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaUnidad(PrintWriter out, Proceso_Unidad_DAO bdao) {
        out.println(encabezadoUnidades());
        List<Proceso_Unidad_DTO> lista = bdao.listAll();
        for (Proceso_Unidad_DTO bdto : lista) {
            String print
                    = ("<tr>"
                    + "<input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_operacion_unidad() + ">"
                    + "<td width=\"10%\" align=\"center\">" + bdto.getSk_operacion_unidad() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre_operacion() + "</td>"
                    + "<td width=\"50%\" align=\"left\">" + bdto.getNombre_unidad() + "</td>"
                    + "<td width=\"20%\" align=\"center\"><button type=\"button\" class=\"btn-danger\" onclick=\"eliminarUnidad(" + bdto.getSk_operacion_unidad() + ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-trash-alt\"></i></td>                    "
                    + "</tr>");
            out.println(print);
        }
        out.println("</table>");
    }

    private String EncabezadoModal() {
        String resp = "<table class=\"table table-fixed table-sm table-striped table-hover\" >\n"
                + "                                <thead class=\"thead tituloTabla\">\n"
                + "                                    <tr>\n"
                + "                                    <th scope=\"col\" width=\"10%\">CODIGO</th>\n"
                + "                                    <th scope=\"col\" width=\"65%\">DESCRIPCION</th>\n"
                + "                                    <th scope=\"col\" width=\"20%\">COSTO</th>\n"
                + "                                    <th scope=\"col\" width=\"5%\">AGREGAR</th>\n"
                + "                                    </tr>\n"
                + "                                </thead>";

        return resp;
    }

    private String encabezadoMateria() {
        String resp = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + "                                    <tr>\n"
                + "                                    <th scope=\"col\" width=\"5%\">CODIGO</th>\n"
                + "                                    <th scope=\"col\" width=\"25%\">MATERIA PRIMA</th>\n"
                + "                                    <th scope=\"col\" width=\"20%\">COSTO</th>\n"
                + "                                    <th class=\"text-center\" scope=\"col\" width=\"10%\">EDITAR</th>\n"
                + "                                    <th class=\"text-center\" scope=\"col\" width=\"10%\">BORRAR</th>\n"
                + "                                    <th class=\"text-center\" scope=\"col\" width=\"15%\"></th>\n"
                + "                                    </tr>\n"
                + "                                </thead>";

        return resp;
    }
    private String encabezadoAcesorio() {
        String resp = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead class=\"thead-light\">\n <tr>\n                               "
                + "                                    <tr>\n"
                + "                                    <th scope=\"col\" class=\"text-center\" width=\"5%\">CODIGO</th>\n"
                + "                                    <th scope=\"col\" width=\"25%\">ACCESORIO</th>\n"
                + "                                    <th scope=\"col\" width=\"20%\">PRECIO</th>\n"
                + "                                    <th class=\"text-center\" scope=\"col\" width=\"10%\">EDITAR</th>\n"
                + "                                    <th class=\"text-center\" scope=\"col\" width=\"10%\">BORRAR</th>\n"
                + "                                    <th class=\"text-center\" scope=\"col\" width=\"15%\"></th>\n"
                + "                                    </tr>\n"
                + "                                </thead>";

        return resp;
    }

    private void printTablaMateria(PrintWriter out, Proceso_Materia_DAO bdao) {
        out.println(encabezadoMateria());
        List<Proceso_Materia_DTO> lista = bdao.listAllM();
        for (Proceso_Materia_DTO bdto : lista) {
            String print
                    = ("<tr>"
                    + "<input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_operacion_materia() + ">"
                    + "<td width=\"10%\" align=\"center\">" + bdto.getSk_operacion_materia() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombrePlu() + "</td>"
                    + "<td width=\"25%\" align=\"left\">"
                    + bdto.getCostoMateriaPrima()
                    + "</td>"
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-info\" data-toggle=\"modal\" data-target=\"#editaMateria\" onclick=\"editaMateria(" + bdto.getSk_operacion_materia() + ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-edit\"></i></td>                    "
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-danger\"  onclick=\"borraMateria(" + bdto.getSk_operacion_materia() + ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-trash\"></i></td>                    "
                    + "<td width=\"25%\" align=\"left\"></td>"
                    + "</tr>");
            out.println(print);
        }
        out.println("</table>");
    }
    private void printTablaAccecsorio(PrintWriter out, Accesorios_DAO bdao) {
        out.println(encabezadoAcesorio());
        List<Accesorios_DTO> lista = bdao.listAll();
        for (Accesorios_DTO bdto : lista) {
            String print
                    = ("<tr>"
                    + "<input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getSk_accesorio() + ">"
                    + "<td width=\"10%\" align=\"center\">" + bdto.getSk_accesorio() + "</td>"
                    + "<td width=\"20%\" align=\"left\">" + bdto.getNombre() + "</td>"
                    + "<td width=\"25%\" align=\"left\">"
                    + bdto.getPrecio()
                    + "</td>"
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-info\" data-toggle=\"modal\" data-target=\"#editaMateria\" onclick=\"editaMateria(" +  bdto.getSk_accesorio()+ ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-edit\"></i></td>                    "
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-danger\"  onclick=\"borraMateria(" + bdto.getSk_accesorio() + ")\" name=\"xoption\" value=\"borrar\"><i class=\"fas fa-trash\"></i></td>                    "
                    + "<td width=\"25%\" align=\"left\"></td>"
                    + "</tr>");
            out.println(print);
        }
        out.println("</table>");
    }


    private String dameEstado(Operaciones_DTO bdto) {
        String salida = "AUTOMATICO";
        if (bdto.getConteo() == 1) {
            salida = "MANUAL";
        }
        return salida;
    }
}
