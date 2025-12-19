package co.linxsi.controlador.inventario.traslado;

import co.linxsi.modelo.inventario.traslado.Traslado_DAO;
import co.linxsi.modelo.inventario.traslado.Traslado_DTO;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "TrasladoInventarioServlet", urlPatterns = {"/TrasladoInventarioServlet"})
public class TrasladoInventarioServlet extends HttpServlet {

    private final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyyMMdd");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        String idSesion = sesion.getId();
        Traslado_DAO dao = new Traslado_DAO();
        try {
            String xOpcion = request.getParameter("xopcion");
            String nombreInventario = "";
            if (xOpcion.compareTo("listaBodegas") == 0) {

            }
            //Crear un registro para reservar la orden en la tabla ordenes y obtener el numero para posteriores usos
            if (xOpcion.equals("crear")) {
                int maxOrden = dao.maxOrden();
                maxOrden++;
                dao.setIdLocal(xIdLocalUsuario);
                dao.setOrden(maxOrden);
                dao.setIdUsuario(Double.parseDouble(idUsuario));
                dao.insertNuevaOrden();
                int numDocumento = dao.getNumDocumento();
                out.print(maxOrden + "," + numDocumento);
            }
            //Ejecuta el traslado finalizando la orden
            if (xOpcion.equals("ejecutarTraslado")) {
                System.out.println("Ejecutar Traslado");
                String ordenOrigen = request.getParameter("idOrdenOrigen"); //Orden del documento de origen (Numero de documento consecutivo)
                String ordenDestino = request.getParameter("idOrdenDestino"); //Orden del documento Destino(Numero de documento consecutivo)
                String idOrdenTraslado = request.getParameter("idOrdenTraslado"); //Orden del documento de traslado
                String observaciones = request.getParameter("observaciones"); //Orden del documento de traslado
                dao.setOrden(Integer.parseInt(ordenOrigen));
                dao.setNumOrdenOrigen(dao.dameKeyOrden());
                dao.setOrden(Integer.parseInt(ordenDestino));
                dao.setNumOrdenDestino(dao.dameKeyOrden());
                dao.setOrden(Integer.parseInt(idOrdenTraslado));
                dao.setObservacion(observaciones);
                boolean resultadoTraslado = dao.ejecutarTraslado();// Ejecutamos el traslado

                // Si el traslado es exitoso
                if (resultadoTraslado) {
                    if (dao.debitarTraslado()) { //Debitamos de la orden origen
                        dao.setNumOrdenOrigen(Integer.parseInt(ordenOrigen));
                        dao.setNumOrdenDestino(Integer.parseInt(ordenDestino));
                        dao.insertarObservacion();
                    }
                }
                printAlert(out);
            }
            //Permite carga lo datos del PLU seleccionado al modal
            if (xOpcion.equals("cargarPLU")) {
                String position = request.getParameter("item");
                String orden = request.getParameter("orden");
                String idOperacion = request.getParameter("idOperacion");
                dao.setItem(Integer.parseInt(position));
                dao.setOrden(Integer.parseInt(orden));
                dao.setNumOrdenOrigen(dao.dameKeyOrden());
                dao.setSk_operacion(Integer.parseInt(idOperacion));
                Traslado_DTO dto = dao.getRegistro();
                out.print(dto.getItem() 
                        + "," + dto.getIdPlu() 
                        + "," + dto.getNombreReferenciaCliente() 
                        + "," + dto.getCantidadOrigen() 
                        + "," + dto.getPesoTerminado() 
                        + "," + dto.getSk_operacion());
            }
            if (xOpcion.equals("printHistorial")) {
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");

                try {
                    fechaInicial = sdfSQL.format(sdf2.parse(fechaInicial));
                    fechaFinal = sdfSQL.format(sdf2.parse(fechaFinal));
                } catch (ParseException ex) {
                    Logger.getLogger(TrasladoInventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                String ordenBusqueda = request.getParameter("ordenBusqueda");
                if (ordenBusqueda.equals("")) {
                    ordenBusqueda = "%%";
                }
                String tipoDoc = request.getParameter("tipoDoc");
                dao.setOrdenSTR(ordenBusqueda);
                dao.setTipoOrden(tipoDoc);
                dao.setFechaInicial(fechaInicial);
                dao.setFechaFinal(fechaFinal);
                printTableIngresos(request, dao, out);
            }

            //Realiza un movimiento de forma individual tomado de la orden origen hacia la orden de traslado para posterior proceso
            if (xOpcion.equals("moverPLU")) {
                String position = request.getParameter("item");
                String referenciaCliente = request.getParameter("campoNombreProducto");
                String orden = request.getParameter("orden");//Orden del documento de origen (Numero de documento consecutivo)
                String ordenDestino = request.getParameter("idOrdenDestino");//Orden del documento Destino(Numero de documento consecutivo)
                String idPLU = request.getParameter("idPLU");
                String cantidad = request.getParameter("cantidad");
                String peso = request.getParameter("peso");
                String idOperacion = request.getParameter("idOperacion");
                String idOrdenTraslado = request.getParameter("idOrdenTraslado");//Orden del documento de traslado
                dao.setItem(Integer.parseInt(position));
                dao.setIdPlu(Integer.parseInt(idPLU));
                dao.setOrden(Integer.parseInt(orden));
                dao.setNumOrdenOrigen(dao.dameKeyOrden());
                dao.setOrden(Integer.parseInt(ordenDestino));
                dao.setNumOrdenDestino(dao.dameKeyOrden());
                dao.setSk_operacion(Integer.parseInt(idOperacion));
                dao.setCantidadDestino(Double.parseDouble(cantidad));
                dao.setPesoTerminado(Double.parseDouble(peso));
                dao.setOrden(Integer.parseInt(idOrdenTraslado));
                dao.setReferencia(referenciaCliente);
                dao.insertPLU();

            }
            //Permite buscar los elementos de la orden origen y mostrarlos en html
            if (xOpcion.equals("buscarOrden")) {
                System.out.println("Ingresado en modulo de busqueda de orden");
                String ordenOrigen = request.getParameter("ordenOrigen");
                int numOrden = Integer.parseInt(ordenOrigen);
                dao.setOrden(numOrden);
                int keyPrimaryOrden = dao.dameKeyOrden();
                dao.setNumOrdenOrigen(keyPrimaryOrden);
                printTabla(out, dao);
            }
            //Permite buscar los elementos de la orden destino y mostrarlos en html
            if (xOpcion.equals("buscarOrdenDestino")) {
                System.out.println("Ingresado en modulo de busqueda de orden destino");
                String ordenDestino = request.getParameter("ordenDestino");
                String orden = request.getParameter("idOrdenTraslado");
                int numOrden = Integer.parseInt(ordenDestino);
                dao.setNumOrdenDestino(numOrden);
                dao.setOrden(numOrden);
                int keyPrimaryOrden = dao.dameKeyOrden();
                dao.setNumOrdenDestino(keyPrimaryOrden);
                dao.setOrden(Integer.parseInt(orden));
                printTablaDestino(out, dao);
            }
            //Permite buscar el nombre del cliente del producto del pedido
            if (xOpcion.equals("referenciaCliente")) {
                System.out.println("Ingresado en modulo de referenciaCliente");
                String ordenOrigen = request.getParameter("ordenOrigen");
                int numOrden = Integer.parseInt(ordenOrigen);
                dao.setOrden(numOrden);
                int keyPrimaryOrden = dao.dameKeyOrden();
                dao.setNumOrdenOrigen(keyPrimaryOrden);
                String nombreReferencia = dao.getNombreReferencia().getNombreReferenciaCliente();
                out.print(nombreReferencia + ",0");
            }
            //Permite buscar el nombre del cliente del producto del pedido
            if (xOpcion.equals("borrarPLU")) {
                System.out.println("Borrar PLU");
                String ordenTraslado = request.getParameter("ordenTraslado");
                String item = request.getParameter("item");
                String plu = request.getParameter("plu");
                String operacion = request.getParameter("operacion");
                dao.setOrden(Integer.parseInt(ordenTraslado));
                dao.setItem(Integer.parseInt(item));
                dao.setIdPlu(Integer.parseInt(plu));
                dao.setSk_operacion(Integer.parseInt(operacion));
                boolean res = dao.borrarPLU();

            }
            if (xOpcion.equals("borrarPLUS")) {
                System.out.println("Borrar PLUS");
                String orden = request.getParameter("orden");
                dao.setOrden(Integer.parseInt(orden));
                boolean res = dao.borrarPLUS();
            }
        } finally {
            out.close();
        }
    }

    private String encabezado() {
        String encabezado = "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \" cellspacing=\"0\">\n "
                + "<thead>\n <tr>\n                               "
                + " <th width=\"5%\" class=\"text-center\">ITEM</th>    \n                               "
                + " <th width=\"5%\" class=\"text-center\">Fecha Inicio</th>    \n                               "
                + " <th width=\"5%\" class=\"text-center\">Fecha Fin</th>    \n                               "
                + " <th width=\"15%\" class=\"text-center\">PROCESO</th>\n"
                + " <th width=\"10%\" class =\"text-center\">Cant Finalizada</th>\n"
                + " <th width=\"10%\" class =\"text-center\">Peso Finalizado</th>\n"
                + "<th width=\"10%\" class =\"text-center\">TRASLADAR</th>\n "
                + "</thead>";
        return encabezado;
    }

    private String encabezadoDestino() {

        String encabezado
                = "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \" cellspacing=\"0\">\n "
                + "<thead>\n <tr>\n                               "
                + " <th width=\"5%\" class=\"text-center\">ITEM</th>    \n                               "
                + " <th width=\"5%\" class=\"text-center\">Fecha Inicio</th>    \n                               "
                + " <th width=\"5%\" class=\"text-center\">Fecha Fin</th>    \n                               "
                + " <th width=\"15%\" class=\"text-center\">PROCESO</th>\n"
                + " <th width=\"10%\" class =\"text-center\">Cant Finalizada</th>\n"
                + " <th width=\"10%\" class =\"text-center\">Peso Finalizado</th>\n"
                + "<th width=\"10%\" class =\"text-center\">RETIRAR</th>\n "
                + "</thead>";
        return encabezado;
    }

    private void printTabla(PrintWriter out, Traslado_DAO dao) {
        out.println(encabezado());
        List<Traslado_DTO> listaDTOTraslado = dao.damePLUOrdenOrigen();
        int i = 0;
        for (Traslado_DTO bdto : listaDTOTraslado) {

            String print
                    = "<tr >\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getIdPlu() + ">"
                    + "<td  align=\"center\">" + bdto.getItem() + "</td>"
                    + "<td class=\"text-center\">" + bdto.getFechaInicial() + "</td>"
                    + "<td class=\"text-center\">" + bdto.getFechaFinal() + "</td>"
                    + "<td  class=\"text-center\">" + bdto.getNombre_operacion() + "</td>"
                    + "<td class =\"text-center\">" + bdto.getCantidadOrigen() + "</td>"
                    + "<td class =\"text-center\">" + bdto.getPesoTerminado() + "</td>"
                    + "<td class =\"text-center\">\n  "
                    + "<button type=\"button\" id=\"botonTraslado" + i + "\" title=\"Trasladar este elemento\" class=\"btn btn-success btn-sm \" data-toggle=\"modal\" data-target=\"#exampleModal\" "
                    + "onclick=\"editar(" + bdto.getItem() + "," + bdto.getSk_operacion() + " , '" + i + "')\">\n"
                    + "<span class=\"fa fa-truck-loading\"></span></button> "
                    + "<button type=\"button\" id=\"botonTrasladoSpinner" + i + "\" hidden = \"true\" title=\"Trasladando\" class=\"btn btn-success btn-sm \"  "
                    + "onclick=\"editar(" + bdto.getItem() + "," + bdto.getSk_operacion() + " , '" + i + "')\">\n"
                    + "<span class=\"spinner-border spinner-border-sm\"  role=\"status\" aria-hidden=\"true\"></span></button> "
                    + "</td>"
                    + "</tr>";
            out.println(print);
            i++;
        }
//        out.println(encabezado());
        out.println("</table>");
    }

    private void printTablaDestino(PrintWriter out, Traslado_DAO dao) {
        out.println(encabezadoDestino());
        List<Traslado_DTO> listaDTOTraslado2 = dao.damePLUOrdenDestinoAgregado();
        int i = 1;
        for (Traslado_DTO bdto : listaDTOTraslado2) {
            String print
                    = "<tr class=\"table-warning\">\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getIdPlu() + ">"
                    + "<td  align=\"center\">" + bdto.getItem() + "</td>"
                    + "<td class=\"text-center\">-</td>"
                    + "<td class=\"text-center\">-</td>"
                    + "<td  class=\"text-center\">" + bdto.getNombre_operacion() + "</td>"
                    + "<td class =\"text-center\">" + bdto.getCantidadOrigen() + "</td>"
                    + "<td class =\"text-center\">" + bdto.getPesoTerminado() + "</td>"
                    + "<td class =\"text-center\">\n "
                    + " <button type=\"button\" id= \"botonBorrar" + i + "\" class=\"btn btn-danger btn-sm \" data-toggle=\"modal\" data-target=\"#bd-example-modal-sm\" "
                    + "onclick=\"guardarBorrar(" + i + "," + bdto.getItem() + "," + bdto.getSk_operacion() + "," + bdto.getIdPlu() + ")\">\n"
                    + "<i class=\"fas fa-trash-alt\"></i></button>"
                    + " <button type=\"button\" hidden =\"true\" id= \"botonSpinnerBorrar" + i + "\" class=\"btn btn-danger btn-sm \"  "
                    + ">\n"
                    + "<span class=\"spinner-border spinner-border-sm\"  role=\"status\" aria-hidden=\"true\"></span></button>"
                    + " </td>"
                    + "</tr>";
            out.println(print);
            i++;
        }
        List<Traslado_DTO> listaDTOTraslado = dao.damePLUOrdenDestino();
        for (Traslado_DTO bdto : listaDTOTraslado) {
            String print
                    = "<tr >\n <input type=\"hidden\" name=\"skmarca\" id=\"skmarca\" value=" + bdto.getIdPlu() + ">"
                    + "<td  align=\"center\">" + bdto.getItem() + "</td>"
                    + "<td class=\"text-center\">" + bdto.getFechaInicial() + "</td>"
                    + "<td class=\"text-center\">" + bdto.getFechaFinal() + "</td>"
                    + "<td  class=\"text-center\">" + bdto.getNombre_operacion() + "</td>"
                    + "<td class =\"text-center\">" + bdto.getCantidadOrigen() + "</td>"
                    + "<td class =\"text-center\">" + bdto.getPesoTerminado() + "</td>"
                    + "<td class =\"text-center\">\n  <button type=\"button\" class=\"btn btn-danger btn-sm \" disabled=\"true\""
                    + "onclick=\"guardarBorrar(" + bdto.getItem() + "," + bdto.getSk_operacion() + "," + bdto.getIdPlu() + ")\">\n"
                    + "<i class=\"fas fa-trash-alt\"></i></button> </td>"
                    + "</tr>";
            out.println(print);
        }

        out.println("</table>");
    }

    public void printAlert(PrintWriter out) {
        String alert = "   <div    class=\"alert alert-success alert-dismissible fade show\"  role=\"alert\">\n"
                + "                <h6 class=\"text-center\"><center><i class=\"fas fa-check-circle\"></i></i> ¡Traslado Exitoso! </center></h6>"
                + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                + "                <span aria-hidden=\"true\">&times;</span>\n"
                + "                </button>\n"
                + "        </div>";
        out.println(alert);
    }

    public void printTableIngresos(HttpServletRequest request, Traslado_DAO dao, PrintWriter out) {
        String encabezado
                = "<table id=\"dataTable\"  border=\"0\" width=\"100%\" class=\"table-sm table-striped table-bordered table-sm table-responsive \" cellspacing=\"0\">\n "
                + "<thead>\n <tr>\n                               "
                + "                                    <th class=\"text-center\"  width=\"5%\">Nº</th> \n"
                + "                                    <th class=\"text-center\"  width=\"10%\">FECHA </th> \n"
                + "                                    <th class=\"text-center\"  width=\"10%\">Nº TRASLADO</th> \n"
                + "                                    <th class=\"text-center\" width=\"10%\">ORDEN ORIGEN</th> \n"
                + "                                    <th class=\"text-center\" width=\"12%\">ORDEN DESTINO</th> \n"
                + "                                    <th class=\"text-center\" width=\"30%\">OBSERVACION</th> \n"
                + "                                    <th class=\"text-center\" width=\"5%\">ACCION</th> \n"
                + "                                    <th width=\"30%\" align=\"left\">&nbsp;</th>   </tr>  \n </thead>\n";
        String cuerpo = "";

        List<Traslado_DTO> listaHistorial = dao.dameHistorial();

        int i = 1;
        for (Traslado_DTO lista : listaHistorial) {
            String botonAccion = "<button id=\"botonPDF" + i + "\"  class=\"btn btn-danger btn-sm \" type=\"submit\" onclick=\"colocarEspera(" + i + ")\" title=\"Imprimir\" name = \"xoption\" value =\"" + lista.getOrden() + "\" style=\"cursor:pointer;\">\n"
                    + "<span class=\"far fa-file-pdf\"></span></button>"
                    + "<button id=\"spinnerPDF" + i + "\" hidden = \"true\" class=\"btn btn-danger btn-sm \" title=\"Cargando\" name = \"xoption\" value =\"" + lista.getOrden() + "\" style=\"cursor:pointer;\">"
                    + "<span class=\"spinner-border spinner-border-sm\"  role=\"status\" aria-hidden=\"true\"></span></button> ";
            if (lista.getObservacion() == null) {
                botonAccion = "<button id=\"botonEditarOrden" + i + "\" type=\"button\"  class=\"btn btn-primary btn-sm \"  onclick=\"editarOrden(" + i + "," + lista.getOrden() + "," + lista.getNumeroDocumento() + ")\" title=\"Editar\" name = \"xoption\" style=\"cursor:pointer;\">\n"
                        + "<i class=\"fas fa-pen-square\"></i></button>"
                        + "<button id=\"spinnerEditar" + i + "\" type=\"button\" hidden = \"true\" class=\"btn btn-primary btn-sm \" title=\"Cargando\" name = \"xoption\"  style=\"cursor:pointer;\">"
                        + "<span class=\"spinner-border spinner-border-sm\"  role=\"status\" aria-hidden=\"true\"></span></button> ";

            }
            cuerpo += "<tr>"
                    + " <td  class=\"text-center\" width=\"5%\" align=\"center\">" + i + "</td>\n"
                    + " <td  class=\"text-center\" width=\"5%\" align=\"center\">" + lista.getFechaOrden() + "</td>\n"
                    + " <td  class=\"text-center\" width=\"5%\" align=\"center\">" + lista.getNumeroDocumento() + "</td>\n"
                    + " <td  class=\"text-center\" width=\"10%\" align=\"center\">" + lista.getNumOrdenOrigen() + "</td>\n"
                    + " <td   class=\"text-center\"  width=\"10%\"> " + lista.getNumOrdenDestino() + "</td>\n"
                    + " <td   class=\"text-left\"  width=\"10%\" > " + lista.getObservacion() + "</td>\n"
                    + "<td width=\"5%\" class =\"text-center\">\n  "
                    + botonAccion
                    + "</td>"
                    + "<td width=\"0%\" align=\"center\">&nbsp</td>\n"
                    + "</tr> ";
            i++;
        }
        String totales = "";
        String pie = "</table>  ";
        String salida = encabezado + cuerpo + totales + pie;
        out.println(salida);
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
