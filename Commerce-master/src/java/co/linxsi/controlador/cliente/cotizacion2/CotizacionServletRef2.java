package co.linxsi.controlador.cliente.cotizacion2;

import co.linxsi.modelo.cliente.cotizacion.DAO_Dcto_Cot;
import co.linxsi.modelo.cliente.cotizacion.DAO_Referencia;
import co.linxsi.modelo.cliente.cotizacion.DTO_Dcto;
import co.linxsi.modelo.cliente.cotizacion.DTO_Referencia;
import co.linxsi.modelo.cliente.cotizacion2.DAO_Referencia2;
import co.linxsi.modelo.divisas.Divisas_DAO;
import co.linxsi.modelo.divisas.Divisas_DTO;
import co.linxsi.modelo.maestro.accesorios.Accesorios_DTO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CotizacionServletRef2", urlPatterns = {"/CotizacionServletRef2"})
public class CotizacionServletRef2 extends HttpServlet {

    private final DecimalFormat df = new DecimalFormat();
    private final DecimalFormat df2 = new DecimalFormat("######.##");
    private DAO_Referencia2 dao = new DAO_Referencia2();
    private static Map<String, List<Operaciones_DTO>> mapaProcesos = new HashMap<String, List<Operaciones_DTO>>();
    private static Map<String, List<Proceso_Materia_DTO>> mapaMaterias = new HashMap<String, List<Proceso_Materia_DTO>>();
    private static Map<String, List<Accesorios_DTO>> mapaAccesorios = new HashMap<String, List<Accesorios_DTO>>();
    private static Double costoTotalMaterias = 0.0;
    private static Double costoTotalProcesos = 0.0;
    private static Double costoTotalAccesorios = 0.0;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private final SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyyMMdd");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession(true);
        Object xIdLocal = sesion.getAttribute("idLocal");
        String idSesion = sesion.getId();

        try {
            String xOpcion = request.getParameter("xopcion");

            if (xOpcion.equals("lista")) {
                DAO_Referencia dao = new DAO_Referencia();
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                dao.setIdCliente(idCliente);
                printTabla(out, dao);

            }
            if (xOpcion.equals("listaReportRevenue")) {
                DAO_Referencia2 dao = new DAO_Referencia2();
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));

                dao.setIdCliente(idCliente);
                printTablaReporteMargen(out, dao);

            }
            if (xOpcion.equals("listaReportRevenue2")) {
                DAO_Referencia2 dao = new DAO_Referencia2();
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                //DATOS DE TABLA DE DETALLES
                String[] vectorSkPLU = request.getParameter("plus").split("#");
                String[] vectorCosto = request.getParameter("costos").split("#");

                savePLUS(vectorSkPLU, vectorCosto);
                dao.setIdCliente(idCliente);
                printTablaReporteMargen2(out, dao);

            }
            if (xOpcion.equals("listaReportRevenue3")) {
                DAO_Referencia2 dao = new DAO_Referencia2();
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                //DATOS DE TABLA DE DETALLES
                String[] vectorSkPLU = request.getParameter("plus").split("#");
                String[] vectorCosto = request.getParameter("costos").split("#");
                Divisas_DAO xdao = new Divisas_DAO();
                xdao.setVrActual(Double.parseDouble( request.getParameter("divisaActual")));
                xdao.setVrFuturo(Double.parseDouble( request.getParameter("divisaFuturo")));
                xdao.save();
                savePLUS(vectorSkPLU, vectorCosto);
                dao.setIdCliente(idCliente);
                
                printTablaReporteMargen2(out, dao);

            }
            if (xOpcion.equals("listaMatPrimas")) {

               
                printTablaMatPrima(out);

            }
            if (xOpcion.equals("listaMatPrimasDivisas")) {

                
                printTablaMatPrimaDivisas(out);

            }
            if (xOpcion.equals("listaCot")) {
                DAO_Referencia dao = new DAO_Referencia();
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                dao.setIdCliente(idCliente);
                printTablaCot(out, dao);

            }
            if (xOpcion.equals("printHistDct")) {
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                fechaInicial = sdfSQL.format(sdf.parse(fechaInicial));
                fechaFinal = sdfSQL.format(sdf.parse(fechaFinal));
                DAO_Dcto_Cot dao = new DAO_Dcto_Cot();

                printTablaHistorialDcto(out, dao.getHistorial(idCliente, fechaInicial, fechaFinal));

            }
            if (xOpcion.equals("traerPeso")) {
                String idCliente = request.getParameter("idCliente");
                int ficha = Integer.parseInt(request.getParameter("ficha"));
                FichaTecnica fichaTecnica = new FichaTecnica();
                double pesoMillar = fichaTecnica.pesoMillar(idCliente, ficha);
                FachadaPluFicha fp = new FachadaPluFicha();
                fp.setPesoMillar(pesoMillar * 1000);
                out.print(fp.getPesoMillarDf2());
            }
            if (xOpcion.equals("aggMateria")) {
                List<Proceso_Materia_DTO> listaMP = null;
                String nombre = request.getParameter("nombre");
                int idMateria = Integer.parseInt(request.getParameter("id"));
                double bache = Double.parseDouble(request.getParameter("bache"));
                double retal = Double.parseDouble(request.getParameter("retal"));
                double cant = Double.parseDouble(request.getParameter("cant"));
                double costo = Double.parseDouble(request.getParameter("costo"));
                double cantTotal = bache * (1 + retal / 100);
                if (mapaMaterias.containsKey(idSesion)) {
                    listaMP = mapaMaterias.get(idSesion);
                } else {
                    listaMP = new ArrayList<Proceso_Materia_DTO>();
                }
                Double totalPorcion = 0.0;
                Double costoTotal = 0.0;
                for (Proceso_Materia_DTO dto : listaMP) {
                    totalPorcion += dto.getCantidad();
                }
                totalPorcion += cant;
                Proceso_Materia_DTO dtoMP = new Proceso_Materia_DTO();
                dtoMP.setSk_operacion_materia(idMateria);
                dtoMP.setNombre(nombre);
                dtoMP.setCantidad(cant);
                dtoMP.setCostoMateriaPrima(costo);

                listaMP.add(dtoMP);

                for (Proceso_Materia_DTO dto : listaMP) {
                    dto.setBache(cantTotal * (dto.getCantidad() / totalPorcion));
                    costoTotal += (dto.getCostoMateriaPrima() * dto.getBache());
                }
                costoTotalMaterias = costoTotal;
                mapaMaterias.put(idSesion, listaMP);
//                printTabla(out, listaMP);

            }
            if (xOpcion.equals("aggProceso")) {
                double costoTotal = 0.0;
                List<Operaciones_DTO> listaProceso = null;
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");

                if (mapaProcesos.containsKey(idSesion)) {
                    listaProceso = mapaProcesos.get(idSesion);
                } else {
                    listaProceso = new ArrayList<Operaciones_DTO>();
                }
                double cant = Double.parseDouble(request.getParameter("cant"));
                double costo = Double.parseDouble(request.getParameter("costo"));

                Operaciones_DTO dto = new Operaciones_DTO();
                dto.setSk_operacion(id);
                dto.setNombre_operacion(nombre);
                dto.setCantidad(cant);
                dto.setCosto_retal(costo);
                listaProceso.add(dto);
                //Calculo de costo Total de proceso de fabricacion
                for (Operaciones_DTO proceso : listaProceso) {
                    costoTotal += proceso.getCantidad() * proceso.getCosto_retal();
                }
                costoTotalProcesos = costoTotal;
                mapaProcesos.put(idSesion, listaProceso);
                printTablaProceso(out, listaProceso);

            }
            if (xOpcion.equals("listaProcesoEdit")) {
                double costoTotal = 0.0;
                int idPlu = Integer.parseInt(request.getParameter("idPlu"));
                DAO_Referencia dao = new DAO_Referencia();
                List<Operaciones_DTO> listaProceso = dao.getProcesoByRef(idPlu);
                for (Operaciones_DTO proceso : listaProceso) {
                    costoTotal += proceso.getCantidad() * proceso.getCosto_retal();
                }
                costoTotalProcesos = costoTotal;
                mapaProcesos.remove(idSesion);
                mapaProcesos.put(idSesion, listaProceso);
                printTablaProceso(out, listaProceso);
            }
            if (xOpcion.equals("removerMateria")) {
                double costoTotal = 0.0;
                int id = Integer.parseInt(request.getParameter("idMateria"));
                List<Proceso_Materia_DTO> lista = mapaMaterias.get(idSesion);
                for (Proceso_Materia_DTO materia : lista) {
                    if (materia.getSk_operacion_materia() == id) {
                        lista.remove(lista.indexOf(materia));
                        break;
                    }
                }

                Double totalPorcion = 0.0;

                for (Proceso_Materia_DTO dto : lista) {
                    totalPorcion += dto.getCantidad();
                }

                double bache = Double.parseDouble(request.getParameter("bache"));
                double retal = Double.parseDouble(request.getParameter("retal"));
                double cantTotal = bache * (1 + retal / 100);

                for (Proceso_Materia_DTO dto : lista) {
                    dto.setBache(cantTotal * (dto.getCantidad() / totalPorcion));
                    costoTotal += (dto.getCostoMateriaPrima() * dto.getBache());
                }

                costoTotalMaterias = costoTotal;
                mapaMaterias.put(idSesion, lista);
//                printTabla(out, lista);
            }
            if (xOpcion.equals("removerProceso")) {
                double costoTotal = 0.0;
                int id = Integer.parseInt(request.getParameter("idProceso"));
                List<Operaciones_DTO> lista = mapaProcesos.get(idSesion);
                for (Operaciones_DTO proceso : lista) {
                    if (proceso.getSk_operacion() == id) {
                        lista.remove(lista.indexOf(proceso));
                        break;
                    }

                }
                for (Operaciones_DTO proceso : lista) {
                    costoTotal += proceso.getCantidad() * proceso.getCosto_retal();
                }

                costoTotalProcesos = costoTotal;
                mapaProcesos.put(idSesion, lista);
                printTablaProceso(out, lista);
            }
            if (xOpcion.equals("removerAccesorio")) {
                double costoTotal = 0.0;
                int id = Integer.parseInt(request.getParameter("id"));
                List<Accesorios_DTO> lista = mapaAccesorios.get(idSesion);
                for (Accesorios_DTO accesorio : lista) {
                    if (accesorio.getSk_accesorio() == id) {
                        lista.remove(lista.indexOf(accesorio));
                        break;
                    }

                }
                for (Accesorios_DTO accesorio : lista) {
                    costoTotal += accesorio.getPrecio();
                }

                costoTotalAccesorios = costoTotal;
                mapaAccesorios.put(idSesion, lista);
                printTablaAccesorio(out, lista);
            }
            if (xOpcion.equals("listaMateriaEdit")) {

                int idPlu = Integer.parseInt(request.getParameter("idPlu"));
                DAO_Referencia dao = new DAO_Referencia();
                List<Proceso_Materia_DTO> listaMP = dao.getMateriaByRef(idPlu);

                Double costoTotal = 0.0;

                for (Proceso_Materia_DTO dto : listaMP) {

                    costoTotal += (dto.getCostoMateriaPrima() * dto.getBache());
                }
                costoTotalMaterias = costoTotal;
                mapaMaterias.remove(idSesion);
                mapaMaterias.put(idSesion, listaMP);
//                printTabla(out, listaMP);

            }
            if (xOpcion.equals("listaAccEdit")) {

                int idPlu = Integer.parseInt(request.getParameter("idPlu"));
                DAO_Referencia dao = new DAO_Referencia();
                List<Accesorios_DTO> listaAcc = dao.getAccesorioByRef(idPlu);
                Double costoTotal = 0.0;
                for (Accesorios_DTO dto : listaAcc) {
                    costoTotal += dto.getPrecio();
                }
                costoTotalAccesorios = costoTotal;
                mapaAccesorios.remove(idSesion);
                mapaAccesorios.put(idSesion, listaAcc);
                printTablaAccesorio(out, listaAcc);

            }
            if (xOpcion.equals("limpProcesos")) {
                List<Operaciones_DTO> listaProceso = new ArrayList<Operaciones_DTO>();
                listaProceso.clear();
                mapaProcesos.remove(idSesion);
                printTablaProceso(out, listaProceso);

            }
            if (xOpcion.equals("limpMaterias")) {
                List<Proceso_Materia_DTO> listaMateria = new ArrayList<Proceso_Materia_DTO>();
                listaMateria.clear();
                mapaMaterias.remove(idSesion);
//                printTabla(out, listaMateria);

            }
            if (xOpcion.equals("limpAcc")) {
                List<Accesorios_DTO> listaAccesorios = new ArrayList<Accesorios_DTO>();
                listaAccesorios.clear();
                mapaAccesorios.remove(idSesion);
                printTablaAccesorio(out, listaAccesorios);

            }

            if (xOpcion.equals("aggAccesorio")) {
                double costoTotal = 0.0;
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                double costo = Double.parseDouble(request.getParameter("costo"));
                List<Accesorios_DTO> listaAccesorio = null;
                if (mapaAccesorios.containsKey(idSesion)) {
                    listaAccesorio = mapaAccesorios.get(idSesion);
                } else {
                    listaAccesorio = new ArrayList<Accesorios_DTO>();
                }

                Accesorios_DTO dto = new Accesorios_DTO();
                dto.setSk_accesorio(id);
                dto.setNombre_operacion(nombre);
                dto.setPrecio(costo);
                listaAccesorio.add(dto);
                for (Accesorios_DTO acc : listaAccesorio) {
                    costoTotal += acc.getPrecio();
                }
                costoTotalAccesorios = costoTotal;
                mapaAccesorios.put(idSesion, listaAccesorio);
                printTablaAccesorio(out, listaAccesorio);

            }
            if (xOpcion.equals("printCostMP")) {
                out.print((costoTotalMaterias));

            }
            if (xOpcion.equals("printCostProc")) {

                out.print(costoTotalProcesos);

            }
            if (xOpcion.equals("printCostAcc")) {

                out.print(costoTotalAccesorios);

            }
            if (xOpcion.equals("crear")) {
                String idCliente = request.getParameter("idCliente");
                String nombre = request.getParameter("nombre");
                String ancho = request.getParameter("ancho");
                String largo = request.getParameter("largo");
                String calibre = request.getParameter("calibre");
                String referencia = request.getParameter("referencia");
                String pesoMillar = request.getParameter("pesoMillar");
                String ficha = request.getParameter("ficha");
                String bache = request.getParameter("bache");
                String retal = request.getParameter("retal");
                DAO_Referencia bdao = new DAO_Referencia();
                int xidPlu = Integer.parseInt(request.getParameter("idPlu"));
                String flete = request.getParameter("flete");
                String precio = request.getParameter("precio");
                String precioVenta = request.getParameter("precioVenta");
                String material = request.getParameter("material");
                String observacion = request.getParameter("observacion");
                bdao.setNombreReferencia(nombre);
                bdao.setAncho(Double.parseDouble(ancho));
                bdao.setLargo(Double.parseDouble(largo));
                bdao.setCalibre(Double.parseDouble(calibre));
                bdao.setReferencia(referencia);
                bdao.setPesoMillar(Double.parseDouble(pesoMillar));
                bdao.setIdCliente(Integer.parseInt(idCliente));
                bdao.setFicha(Integer.parseInt(ficha));
                bdao.setBache(Double.parseDouble(bache));
                bdao.setRetal(Double.parseDouble(retal));
                bdao.setFlete(Double.parseDouble(flete));
                bdao.setPrecio(Double.parseDouble(precio));
                bdao.setPrecioVenta(Double.parseDouble(precioVenta));
                bdao.setMaterial(material);
                bdao.setObservacion(observacion);
                int idPlu = xidPlu == 0 ? bdao.getMaxRef() : xidPlu;
                bdao.setIdPlu(idPlu);
                boolean resultado = bdao.ingresa();
                if (bdao.borrarProcesos(idPlu) && resultado && mapaProcesos.containsKey(idSesion)) {
                    List<Operaciones_DTO> listaProceso = mapaProcesos.get(idSesion);
                    for (Operaciones_DTO dto : listaProceso) {
                        bdao.ingresarProcesosReferencia(dto, idPlu);
                    }

                }
                if (bdao.borrarMaterias(idPlu) && resultado && mapaMaterias.containsKey(idSesion)) {
                    List<Proceso_Materia_DTO> listaMateria = mapaMaterias.get(idSesion);
                    for (Proceso_Materia_DTO dto : listaMateria) {
                        bdao.ingresarMateriasReferencia(dto, idPlu);
                    }
                    if (bdao.borrarAccesorios(idPlu) && resultado && mapaAccesorios.containsKey(idSesion)) {
                        List<Accesorios_DTO> listaAccesorios = mapaAccesorios.get(idSesion);
                        for (Accesorios_DTO dto : listaAccesorios) {
                            bdao.ingresarAccesorioReferencia(dto, idPlu);
                        }
                    }

                }
                printTabla(out, bdao);
            }
            if (xOpcion.equals("ListaEditar")) {
                String idPLU = request.getParameter("idPLU");
                DAO_Referencia dao = new DAO_Referencia();
                dao.setIdPlu(Integer.parseInt(idPLU));
                DTO_Referencia dto = dao.getOneRefByClient();
                out.println(dto.getNombreReferencia()
                        + ","
                        + dto.getAncho()
                        + "," + dto.getLargo()
                        + "," + dto.getCalibre()
                        + "," + dto.getReferencia()
                        + "," + dto.getPesoMillar()
                        + "," + dto.getFicha()
                        + "," + dto.getBache()
                        + "," + dto.getRetal()
                        + "," + dto.getIdPlu()
                        + "," + dto.getFlete()
                        + "," + dto.getPrecio()
                        + "," + dto.getMaterial()
                        + "," + dto.getObservacion()
                        + ",");
            }
            if (xOpcion.equals("listaHistorico")) {

                mapaProcesos.remove(idSesion);
                mapaMaterias.remove(idSesion);
                mapaAccesorios.remove(idSesion);
                String idCliente = request.getParameter("idCliente");
                DAO_Referencia dao = new DAO_Referencia();
                dao.setIdCliente(Integer.parseInt(idCliente));
                printListaHistorico(out, dao);

            }

        } catch (ParseException ex) {
            Logger.getLogger(CotizacionServletRef2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void printTablaHistorialDcto(PrintWriter out, List<DTO_Dcto> lista) {
        out.println(encabezadoDctosHistorial());
        df.setMaximumFractionDigits(2);

        for (DTO_Dcto dto : lista) {
            String print
                    = "<tr>\n"
                    + "<td width=\"5%\" align=\"center\">" + dto.getIdDcto() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + dto.getFecha() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + dto.getIdCliente() + "</td>"
                    + "<td width=\"7%\" align=\"left\">" + dto.getNombreCliente() + "</td>"
                    + "<td width=\"7%\" align=\"left\">" + dto.getContacto() + "</td>"
                    + "<td width=\"7%\" align=\"left\">" + dto.getNombreEmisor() + "</td>"
                    + "<td width=\"5%\" align=\"center\"><button type=\"submit\" class=\"btn-danger\"  name=\"xoption\" value = \"" + dto.getIdDcto() + "\"  ><i class=\"fa fa-file-pdf\"></i></button></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private String encabezado() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"3%\">Nº</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Ficha</th> \n"
                + "                                        <th class=\"text-left\" width=\"25%\">Nombre Referencia</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Ancho</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Largo</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Calibre</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Referencia</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">P.Millar</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Precio</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Acción</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String headerReportMargin() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"4%\">FICHA</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">ULT PEDIDO</th> \n"
                + "                                        <th class=\"text-left\" width=\"15%\">REFERENCIA</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">VOLUMEN</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">P. MILLAR</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">COSTO</th> \n"
                + "                                        <th class=\"text-center\" width=\"7%\">PRECIO VTA</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">MARGEN</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String encabezadoCot() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"3%\">Nº</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Selección</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Ficha</th> \n"
                + "                                        <th class=\"text-left\" width=\"23%\">Nombre Referencia</th> \n"
                + "                                        <th class=\"text-right\" width=\"5%\">Ancho</th> \n"
                + "                                        <th class=\"text-right\" width=\"5%\">Largo</th> \n"
                + "                                        <th class=\"text-right\" width=\"5%\">Calibre</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Referencia</th> \n"
                + "                                        <th class=\"text-right\" width=\"5%\">Cant Min</th> \n"
                + "                                        <th class=\"text-right\" width=\"7%\">Precio Venta</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String encabezadoMatPrima() {
        Divisas_DAO dao = new Divisas_DAO();
        Divisas_DTO dto = dao.getDivisa(1);
        String encabezado = " <div id=\"panelDivisas\" class=\"input-group mb-2 input-group-sm\"> \n"
                + "<div class=\"input-group-prepend\">\n"
                + "<span class=\"input-group-text\" style=\"font-weight: bold\">Divisa Actual</span>\n"
                + "</div>\n"
                + "<input  name = \"divisaActual\" class=\"form-control input-group-append col-md-4 text-md-center\"  id=\"divisaActual\" type=\"number\" required autofocus=\"autofocus\" onchange=\"calcularCostoSegunDivisas()\" value =\"" + dto.getVrActual() + "\" placeholder=\"Divisa actual\" >\n"
                + "<div class=\"input-group-prepend\">\n"
                + "<span class=\"input-group-text\" style=\"font-weight:bold\">Divisa Proyeccion</span>\n"
                + "</div>\n"
                + "<input class=\"form-control input-group-append col-md-4 text-md-center\"  type=\"number\" required autofocus=\"autofocus\"  value =\"" + dto.getVrFuturo() + "\" onchange=\"calcularCostoSegunDivisas()\" id=\"divisaFuturo\"  title=\"\" placeholder=\"Divisa proyeccion\">\n"
                + "</div>"
                + "<table border=\"0\" id=\"tablaMateria\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"5%\">PLU</th> \n"
                + "                                        <th class=\"text-left\" width=\"20%\">NOMBRE</th> \n"
                + "                                        <th class=\"text-left\" width=\"10%\">COSTO</th> \n"
                + "                                        <th class=\"text-center\" width=\"3%\">DOLARIZADO</th> \n"
                + "                                        <th class=\"text-left\" width=\"20%\"></th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String encabezadoProcesos() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-left\" width=\"25%\">Nombre</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Porción</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Costo</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Acción</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String encabezadoDctosHistorial() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-center\" width=\"5%\">IdDcto</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Fecha</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Nit/CC</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Cliente</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Contacto</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Firmante</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Acción</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private String encabezadoAccesorios() {
        String encabezado = "<table border=\"0\" width=\"100%\" class=\"table table-sm table-striped\">\n "
                + "<thead>\n"
                + "                                    <tr>\n"
                + "                                        <th class=\"text-left\" width=\"25%\">Nombre</th> \n"
                + "                                        <th class=\"text-left\" width=\"5%\">Costo</th> \n"
                + "                                        <th class=\"text-center\" width=\"5%\">Acción</th> \n"
                + "                                    </tr></thead>";
        return encabezado;
    }

    private void printTabla(PrintWriter out, DAO_Referencia bdao) {
        out.println(encabezado());
        List<DTO_Referencia> lista = bdao.getAllByCliente();

        for (DTO_Referencia bdto : lista) {
            String print
                    = "<tr>\n"
                    + "<td width=\"3%\" align=\"center\">" + bdto.getIdPlu() + "</td>"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getFicha() + "</td>"
                    + "<td width=\"25%\" align=\"left\">" + bdto.getNombreReferencia() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + bdto.getAncho() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + bdto.getLargo() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + bdto.getCalibre() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + bdto.getReferencia() + "</td>"
                    + "<td width=\"5%\" align=\"left\">" + bdto.getPesoMillar() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + bdto.getPrecio() + "</td>"
                    + "<td width=\"10%\" align=\"left\"><button type=\"button\" class=\"btn-success\"  name=\"xoption\" onclick = traerUnRef(\"" + bdto.getIdPlu() + "\") value=\"Editar\" data-toggle=\"modal\" data-target=\"#modal-ref-cliente\"><i class=\"fas fa-edit\"></i></button></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaReporteMargen(PrintWriter out, DAO_Referencia2 bdao) {
        out.println(headerReportMargin());
        List<DTO_Referencia> lista = bdao.getRefersByClientsRevenue();

        for (DTO_Referencia bdto : lista) {
            String linkFicha = bdto.getIdPedido() != 0 ? "<td width=\"4%\" align=\"center\">"
                    + "     <a href=\"GralControladorServlet?nombrePaginaRequest="
                    + "/jsp/vtaContenedorFichaTecnica.jsp&accionContenedor=Consultar Pedido2&xNumeroOrden="
                    + bdto.getIdPedido() + "\">" + bdto.getFicha() + "</a></td>" : "<td width=\"4%\" align=\"center\">"
                    + bdto.getFicha() + "</td>";

            String margen = bdto.getMargen() > 18 ? "<td width=\"5%\" align=\"right\" class=\"text-success\">" + df2.format(bdto.getMargen()) + " %</td>"
                    : "<td width=\"5%\" align=\"right\" class=\"text-danger\">" + df2.format(bdto.getMargen()) + " %</td>";
            String print
                    = "<tr>\n"
                    + linkFicha
                    + "<td width=\"5%\" align=\"center\">" + bdto.getIdPedido() + "</td>"
                    + "<td width=\"25%\" align=\"left\">" + bdto.getReferencia() + " " + bdto.getNombreReferencia() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getVolumen()) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getPesoMillar()) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getCosto()) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getPrecioVenta()) + "</td>"
                    + margen
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }
    private void printTablaReporteMargen2(PrintWriter out, DAO_Referencia2 bdao) {
        out.println(headerReportMargin());
        List<DTO_Referencia> lista = bdao.getRefersByClientsRevenue2();

        for (DTO_Referencia bdto : lista) {
            String linkFicha = bdto.getIdPedido() != 0 ? "<td width=\"4%\" align=\"center\">"
                    + "     <a href=\"GralControladorServlet?nombrePaginaRequest="
                    + "/jsp/vtaContenedorFichaTecnica.jsp&accionContenedor=Consultar Pedido2&xNumeroOrden="
                    + bdto.getIdPedido() + "\">" + bdto.getFicha() + "</a></td>" : "<td width=\"4%\" align=\"center\">"
                    + bdto.getFicha() + "</td>";

            String margen = bdto.getMargen() > 18 ? "<td width=\"5%\" align=\"right\" class=\"text-success\">" + df2.format(bdto.getMargen()) + " %</td>"
                    : "<td width=\"5%\" align=\"right\" class=\"text-danger\">" + df2.format(bdto.getMargen()) + " %</td>";
            String print
                    = "<tr>\n"
                    + linkFicha
                    + "<td width=\"5%\" align=\"center\">" + bdto.getIdPedido() + "</td>"
                    + "<td width=\"25%\" align=\"left\">" + bdto.getReferencia() + " " + bdto.getNombreReferencia() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getVolumen()) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getPesoMillar()) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getCosto()) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getPrecioVenta()) + "</td>"
                    + margen
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaCot(PrintWriter out, DAO_Referencia bdao) {
        out.println(encabezadoCot());
        List<DTO_Referencia> lista = bdao.getAllByCliente();
        String checked = "";
        if (lista.size() == 1) {
            checked = "checked";
        }
        for (DTO_Referencia bdto : lista) {
            double cantidad = (bdto.getBache() / bdto.getPesoMillar()) * 1000;
            String print
                    = "<tr>\n"
                    + "<td width=\"3%\" align=\"center\">" + bdto.getIdPlu() + "</td>"
                    + "<td width=\"5%\"  align=\"center\">"
                    + " <input class=\"form-check-input bn-success\"   type=\"checkbox\" name=\"idPluCot\""
                    + " value=\"" + bdto.getIdPlu()
                    + "~" + bdto.getNombreReferencia()
                    + "~" + bdto.getAncho()
                    + "~" + bdto.getLargo()
                    + "~" + bdto.getCalibre()
                    + "~" + cantidad
                    + "~" + bdto.getMaterial()
                    + "~" + bdto.getPrecioVenta()
                    + " \""
                    + checked + "></input>"
                    + "</td >"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getFicha() + "</td>"
                    + "<td width=\"23%\" align=\"left\">" + bdto.getNombreReferencia() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + bdto.getAncho() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + bdto.getLargo() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + bdto.getCalibre() + "</td>"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getReferencia() + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(cantidad) + "</td>"
                    + "<td width=\"5%\" align=\"right\">" + df2.format(bdto.getPrecioVenta()) + "</td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaMatPrima(PrintWriter out) {
        out.println(encabezadoMatPrima());

        DAO_Referencia2 dao = new DAO_Referencia2();
        List<FachadaPluBean> lista = dao.getMateriaAll();
        int i = 0;
        for (FachadaPluBean bdto : lista) {
            String print
                    = "<tr>\n"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getIdPlu() + "<input id=\"plu" + i + "\" hidden=\"true\" value=\"" + bdto.getIdPlu() + "\" ></input></td>"
                    + "<td width=\"15%\" align=\"left\">" + bdto.getNombrePlu() + "</td>"
                    + "<td width=\"22%\" align=\"center\"><div class=\"input-group mb-0 input-group-sm\"> <input type=\"number\" min=\"1\" class=\"form-control input-group-append col-md-7\" id=\"mat" + i++ + "\" value=\"" + bdto.getVrCosto() + "\"></input></div></td>"
                    + "<td width=\"15%\" align=\"center\">" + (bdto.getDolarizado()== 1?"Si":"No") + "</td>"
                    + "<td width=\"10%\" align=\"center\"></td>"
                    + "</tr>";
            out.println(print);

        }
        out.println("</table>");
    }

    private void printTablaMatPrimaDivisas(PrintWriter out) {
        out.println(encabezadoMatPrima());

        DAO_Referencia2 dao = new DAO_Referencia2();
        List<FachadaPluBean> lista = dao.getMateriaDolarizados();
       
        int i = 0;
        for (FachadaPluBean bdto : lista) {
            String print
                    = "<tr>\n"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getIdPlu() + "<input id=\"plu" + i + "\" hidden=\"true\" value=\"" + bdto.getIdPlu() + "\" ></input></td>"
                    + "<td width=\"15%\" align=\"left\">" + bdto.getNombrePlu() + "</td>"
                    + "<td width=\"22%\" align=\"center\"><div  class=\"input-group mb-0 input-group-sm\"> <input type=\"number\" min=\"1\" class=\"form-control input-group-append col-md-7\" id=\"mat" + i + "\" value=\"" + bdto.getVrCosto() + "\"></input></div></td>"
                    + "<td hidden width=\"22%\" align=\"center\"><div  class=\"input-group mb-0 input-group-sm\"> <input readonly=\"readonly\"type=\"number\" min=\"1\" class=\"form-control input-group-append col-md-7\" id=\"xmat" + i++ + "\" value=\"" + bdto.getVrCosto() + "\"></input></div></td>"
                    + "<td width=\"15%\" align=\"center\">" + (bdto.getDolarizado()== 1?"Si":"No")+ "</td>"
                    + "<td width=\"10%\" align=\"center\"></td>"
                    + "</tr>";
            out.println(print);

        }
        out.println("</table>");
    }

    private void printTablaProceso(PrintWriter out, List<Operaciones_DTO> lista) {
        out.println(encabezadoProcesos());
        df.setMaximumFractionDigits(2);

        for (Operaciones_DTO bdto : lista) {
            String print
                    = "<tr>\n"
                    + "<td width=\"25%\" align=\"left\">" + bdto.getNombre() + "</td>"
                    + "<td width=\"5%\" align=\"center\">" + bdto.getCantidad() + "</td>"
                    + "<td width=\"5%\" align=\"center\">" + df.format(bdto.getCosto_retal()) + "</td>"
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-danger\"  name=\"xoption\" onclick = removerProceso(\"" + bdto.getSk_operacion() + "\")  ><i class=\"fas fa-trash\"></i></button></td>"
                    + "</tr>";
            out.println(print);
        }
        out.println("</table>");
    }

    private void printTablaAccesorio(PrintWriter out, List<Accesorios_DTO> lista) {
        out.println(encabezadoAccesorios());
        df.setMaximumFractionDigits(2);

        for (Accesorios_DTO dto : lista) {
            String print
                    = "<tr>\n"
                    + "<td width=\"25%\" align=\"left\">" + dto.getNombre() + "</td>"
                    + "<td width=\"5%\" align=\"center\">" + df.format(dto.getPrecio()) + "</td>"
                    + "<td width=\"10%\" align=\"center\"><button type=\"button\" class=\"btn-danger\"  name=\"xoption\" onclick = removerAccesorio(\"" + dto.getSk_accesorio() + "\") ><i class=\"fas fa-trash\"></i></button></td>"
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

    private void printListaHistorico(PrintWriter out, DAO_Referencia dao) {

        List<DTO_Referencia> lista = dao.getHistoryByCliente();
        if (!lista.isEmpty()) {
            out.println("<div class=\"input-group-prepend\">\n"
                    + "<span class=\"input-group-text\" >Historial</span>\n"
                    + "</div>  ");

            out.println("<select title=\"Referencia\" class=\"custom-select  col-md-12\" name=\"listaRefHistorico\" onchange=\"colocarReferencia()\" id=\"selRefHistorico\">\n");
            out.println("<option class=\"text-md-center\" value =\"0\" >Seleccione Referencia</option>");
            for (DTO_Referencia dto : lista) {
                StringBuilder sb = new StringBuilder();
                sb.append(dto.getFicha());
                sb.append(",");
                sb.append(dto.getNombreReferencia());
                sb.append(",");
                sb.append(dto.getAncho());
                sb.append(",");
                sb.append(dto.getLargo());
                sb.append(",");
                sb.append(dto.getCalibre());
                sb.append(",");
                sb.append(dto.getReferencia());
                sb.append(",");
                String texto = "<option class=\"text-md-center\" value =\"" + sb.toString() + "\" >" + dto.getFicha() + "-" + dto.getNombreReferencia() + "</option>";
                out.println(texto);
            }
            out.println("  </select><button   id =\"btnBuscaPesMillar\"  class=\"btn btn-success btn btn-primary btn-sm \"  onclick=\"traePesoMillar()\" type=\"button\" title=\"Buscar\"><i class=\"fa fa-history\"></i></button>  ");
        } else {
            out.print("nulo");
        }

    }

    private void savePLUS(String[] codes, String[] costs) {
        System.out.println("Saving Plus");
        int i = 0;
        for (String plu : codes) {
            dao.setCosto(Float.parseFloat(costs[i++]));
            dao.setIdPlu(Integer.parseInt(plu));
            dao.actualizaVrCostoPredictPLU();
        }

    }
}
