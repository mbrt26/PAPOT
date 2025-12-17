package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import co.linxsi.modelo.cliente.cotizacion2.DAO_Referencia2;
import co.linxsi.modelo.cliente.cotizacion2.mixmanager.DAO_Machine_Option;
import co.linxsi.modelo.cliente.cotizacion2.mixmanager.DAO_Mix_Option;
import com.solucionesweb.losbeans.colaboraciones.ColaboraArteClientes;
import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import com.solucionesweb.losbeans.fachada.FachadaArteClientes;
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.JobProgramaPlusFichaBean;
import com.solucionesweb.losbeans.negocio.PluFichaBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpSession;

/**
 * Esta opcion permite consultar la ficha tecnica del pedido, que es el orden de
 * produccion de pedido / vtaContenedorFichaTecnica.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmFichaTecnicaManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON-- ("Consultar Pedido")-Permite consultar ficha tecnica de un
     * pedido / ("Salir")-("Regresar")-Retorna al menu principal / Seleccionar
     * item / ("Imprimir Ficha")-Permite generar pdf ficha / ("Imprimir
     * Pedido")-Permite generar pdf pedido
     *
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmFichaTecnicaManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER-- "#Pedido"-Ingreso numero pedido que se desea ver /
     * "Parametros"-Ingresos de parametros para produccion /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Regresar") == 0) {

                //
                return "/GralControladorServlet?nombrePaginaRequest=/potPermisoAdmFichaTecnica.ctr";
            }

            //
            if (accionContenedor.compareTo("Salir") == 0) {

                //
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Imprimir Pedido") == 0) {

                //
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xItemPadre = request.getParameter("xItem");

                //
                GeneraPDF_Pedido generaPDFPedido = new GeneraPDF_Pedido();

                //
                generaPDFPedido.setNumeroOrden(xNumeroOrden);
                generaPDFPedido.setItemPadre(xItemPadre);
                generaPDFPedido.setNombreReporte("POTRepOrdenPedido");

                //
                generaPDFPedido.generaPdf(request, response);

            }

            if (accionContenedor.compareTo("Imp. Arte") == 0) {

                //
                String xFicha = request.getParameter("xIdFicha");
                String xCLiente = request.getParameter("xIdCliente");

                ColaboraArteClientes colabora = new ColaboraArteClientes();
                FachadaArteClientes fac = new FachadaArteClientes();

                colabora.setIdFicha(Integer.parseInt(xFicha));
                colabora.setIdCliente(xCLiente);

                fac = colabora.listaArteCliente();
                String tipoarchivo = "";
                String[] routes = fac.getRutaArte().split("\\\\");
                String xRuta = fac.getRutaArte();
                // for (String xRuta1 : xRuta) {
                  tipoarchivo = routes[routes.length- 1].trim().replaceAll(" ", "_");
                // }
                FileInputStream archivo = new FileInputStream(xRuta);
                int longitud = archivo.available();
                byte[] datos = new byte[longitud];
                archivo.read(datos);
                archivo.close();
                response.setContentType("application/jpg");
                /* setContentType Método que nos sirve para fijar el tipo de contenido que se va a devolver en la respuesta del servlet.*/
                response.setHeader("Content-Disposition", "attachment;filename=" +tipoarchivo );
                // Cookie waitCookie = new Cookie("waitingCookie", "done");//Esa cookie se enviará al cliente cuando su archivo esté listo para descargar.
                //response.addCookie(waitCookie);//Se envia la cookie
                ServletOutputStream ouputStream = response.getOutputStream(); // getOutputStream que retorna un ServletOutputStream para enviar datos binarios al cliente. ServletOutputStream para enviar datos binarios al cliente.
                ouputStream.write(datos); //Colocamos el archivo en el Buffer
                ouputStream.flush(); //Liberamos el Buffer
                ouputStream.close();//Cerramos el Buffer 
//                if(tipoarchivo.equals("jpg")){
//                    GeneraPDFArte dFArte = new GeneraPDFArte();
//                
//                dFArte.setFicha(xFicha);
//                dFArte.setIdCliente(xCLiente);                        
//                
//                dFArte.generaPdf(request, response);
//                }else{
//                    request.setAttribute("fachada", fac);
//                
//                return "/jsp/arte.jsp";
//                }

            }

            //
            if (accionContenedor.compareTo("Imprimir Ficha") == 0) {

                //
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xItemPadre = request.getParameter("xItem");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setItemPadre(xItemPadre);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaUnOTFCH(
                        new Integer(xItemPadre).intValue());

                //
                int xIdLocal = fachadaPluFicha.getIdLocal();
                int xIdTipoOrden = fachadaPluFicha.getIdTipoOrden();
                int xIdOrden = fachadaPluFicha.getIdOrden();

                //
                if (fachadaPluFicha.getPesoPedido() == 0) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar(""
                            + " EXTRUSION PARA CALCULAR PESO PEDIDO, NUMERO Y PESO ROLLO ", "");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                int xIdOrdenPrograma = 50;

                //
                JobProgramaPlusFichaBean jobProgramaPlusFichaBean
                        = new JobProgramaPlusFichaBean();

                //
                jobProgramaPlusFichaBean.setIdLocal(xIdLocal);
                jobProgramaPlusFichaBean.setIdTipoOrden(xIdTipoOrden);
                jobProgramaPlusFichaBean.setIdOrden(xIdOrden);
                jobProgramaPlusFichaBean.setItemPadre(xItemPadre);
                jobProgramaPlusFichaBean.setFechaProceso(strFechaVisita);
                jobProgramaPlusFichaBean.setFechaPrograma(strFechaVisita);
                jobProgramaPlusFichaBean.setIdUsuario(xIdUsuario);
                jobProgramaPlusFichaBean.setIdOrdenPrograma(xIdOrdenPrograma);

                //
                jobProgramaPlusFichaBean.retiraFichaPrograma();

                //
                jobProgramaPlusFichaBean.ingresaAllOperacion();

                //--------------------------------------------------------------
                GeneraPDF_OT generaPDF = new GeneraPDF_OT();
                DAO_Machine_Option dAO_Machine_Option = new DAO_Machine_Option();
                DAO_Mix_Option daoMixOption = new DAO_Mix_Option();
                
                   int xIdFicha = Integer.parseInt(request.getParameter("xIdFicha"));
                String xIdCliente = request.getParameter("xIdCliente");
                String codigoMaquina = "600";
                int xIdOperacion = 2;
                int idMachine = (int) colaboraOrdenTrabajo.valorEscala(xIdCliente,
                        xIdFicha,
                        xIdOperacion,
                        codigoMaquina);
                int numberOfLayers = dAO_Machine_Option.getnumberOfLayersFromIdMachine(idMachine);

                int idMix = (int) colaboraOrdenTrabajo.valorEscala(xIdCliente, xIdFicha, xIdOperacion, "643");
                int idOption = daoMixOption.getOptionId(idMix, numberOfLayers);
                
                //
                generaPDF.setNumeroOrden(xNumeroOrden);
                generaPDF.setItemPadre(xItemPadre);
                generaPDF.setNombreReporte("POTRepOrdenTrabajo");
                generaPDF.setIdMix(idMix);
                generaPDF.setIdOption(idOption);
                generaPDF.generaPdf(request, response);

            }

            // Modificar
            if (accionContenedor.compareTo("Modificar") == 0) {

                //
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdFicha = request.getParameter("xIdFicha");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xCantidadPedido = request.getParameter("xCantidadPedido");
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xItemPadre = request.getParameter("xItem");

                //--------------------------------------------------------------
                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                FachadaPluFicha fachadaPluFicha;

                //
                Validacion validacion = new Validacion();

                //--------------------------------------------------------------
                String xIdEscala = "";
                String xVrEscala = "";
                String xTextoEscala = "";
                String xItem = "";
                String xIdTipoEscala = "";
                String xNombreEscala = "";
                String strCaracter = "~";
                int intLugarUno = 0;
                int intLugarDos = 0;
                int intLugarTres = 0;
                int intLongitud = 0;
                int xIdTipoOrdenPedido = 59;

                //
                Enumeration listaAtributos = request.getParameterNames();

                //
                Vector vectorBean = new Vector();

                //
                Iterator iteratorBean;

                //
                while (listaAtributos.hasMoreElements()) {

                    //
                    String nombreAtributo = (String) listaAtributos.nextElement();

                    //
                    intLongitud = nombreAtributo.length();

                    //
                    intLugarUno = nombreAtributo.indexOf(strCaracter);
                    intLugarDos = nombreAtributo.indexOf(strCaracter, intLugarUno + 1);
                    intLugarTres = nombreAtributo.indexOf(strCaracter, intLugarDos + 1);

                    //
                    if ((intLugarUno > 0) && (intLugarDos > 0) && (intLugarTres > 0)) {

                        //
                        xIdEscala = nombreAtributo.substring(0, intLugarUno);
                        xItem = nombreAtributo.substring(intLugarUno + 1, intLugarDos);
                        xIdTipoEscala = nombreAtributo.substring(intLugarDos + 1, intLugarTres);
                        xNombreEscala = nombreAtributo.substring(intLugarTres + 1, intLongitud);

                        //
                        xVrEscala = request.getParameter(nombreAtributo);

                        /*---
                        System.out.println(" ------- ");
                        System.out.println(" xIdEscala " + xIdEscala);
                        System.out.println(" xItem " + xItem);
                        System.out.println(" xIdTipoEscala " + xIdTipoEscala);
                        System.out.println(" xNombreEscala " + xNombreEscala);
                        System.out.println(" xVrEscala " + xVrEscala);*/
                        //
                        if (xNombreEscala.compareTo("textoEscala") == 0) {

                            //
                            xTextoEscala = xVrEscala;
                            xVrEscala = "0";

                        } else {

                            try {

                                //
                                double valor = Double.parseDouble(xVrEscala);

                                //
                                xTextoEscala = "";

                            } catch (NumberFormatException nfe) {

                                //
                                xTextoEscala = xVrEscala;
                                xVrEscala = "0";

                            }
                        }

                        //
                        fachadaPluFicha = new FachadaPluFicha();

                        //
                        fachadaPluFicha.setIdEscala(xIdEscala);
                        fachadaPluFicha.setVrEscala(xVrEscala);
                        fachadaPluFicha.setItem(xItem);
                        fachadaPluFicha.setTextoEscala(xTextoEscala);

                        //
                        vectorBean.add(fachadaPluFicha);

                    }
                }

                //
                boolean xOkIngreso = false;

                //
                int xEstadoOk = 1;

                //
                PluFichaBean pluFichaBean = new PluFichaBean();

                //
                iteratorBean = vectorBean.iterator();

                //
                while (iteratorBean.hasNext()) {

                    //
                    fachadaPluFicha = (FachadaPluFicha) iteratorBean.next();

                    //
                    pluFichaBean.setIdCliente(xIdCliente);
                    pluFichaBean.setIdFicha(xIdFicha);
                    pluFichaBean.setIdOperacion(xIdOperacion);
                    pluFichaBean.setIdEscala(fachadaPluFicha.getIdEscala());
                    pluFichaBean.setItem(fachadaPluFicha.getItem());
                    pluFichaBean.setVrEscala(
                            fachadaPluFicha.getVrEscala());
                    pluFichaBean.setTextoEscala(
                            fachadaPluFicha.getTextoEscala());
                    pluFichaBean.setEstado(xEstadoOk);

                    //
                    if (fachadaPluFicha.getTextoEscala().trim().length() > 0) {

                        //
                        xOkIngreso = pluFichaBean.actualizaTextoEscala();

                    } else {

                        //
                        xOkIngreso = pluFichaBean.actualizaVrEscala();
                    }
                }

                
                DAO_Referencia2 daoFichaRef = new DAO_Referencia2();
                if(xIdOperacion.equals("2")){
                daoFichaRef.saveDensidad(xIdFicha);
                
                }
                if (xIdOperacion.equals("5")||xIdOperacion.equals("2")) {
                    if (daoFichaRef.saveVolumenParams(xIdFicha)) {
                        daoFichaRef.saveVolumenNomParams(xIdFicha);//refrescar volumen nominal si el volumen de trabajo es correctamente actualizado o insertado
                    }
                
                }
                //--------------------------------------------------------------
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdCliente(xIdCliente);
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setCantidad(xCantidadPedido);

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xFactorDensidad = fichaTecnica.factorDensidad(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setFactorDensidad(xFactorDensidad);

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoPedido(xPesoPedido);

                //
                double xPesoMillar = fichaTecnica.pesoMillar(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoMillar(xPesoMillar * 1000);

                //
                double xPesoComplemento = fichaTecnica.pesoComplemento(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoComplemento(xPesoComplemento);

                //
                double xMetroPedido = fichaTecnica.metroPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroPedido(xMetroPedido);

                //
                double xMetroRollo = fichaTecnica.metroRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroRollo(xMetroRollo);

                //
                double xPesoRollo = fichaTecnica.pesoRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoRollo(xPesoRollo);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);
                fachadaPluFicha.setItem(xItemPadre);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                /*
                ProcesoPesoPedido procesoPesoPedido = new ProcesoPesoPedido();

                procesoPesoPedido.listaPedido();*/
                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setPesoPedido(xPesoPedido);
                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenPedido);
                dctoOrdenDetalleBean.setNumeroOrden(xNumeroOrden);
                dctoOrdenDetalleBean.setItem(xItemPadre);

                //--
                dctoOrdenDetalleBean.actualizaPesoPedido();

                //--------------------------------------------------------------
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmModFichaTecnica.jsp";

            }

            if (accionContenedor.compareTo("traePedido") == 0) {

                //
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdFicha = request.getParameter("xIdFicha");
                String xCantidadPedido = request.getParameter("xCantidadPedido");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdCliente(xIdCliente);
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setCantidad(xCantidadPedido);

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xFactorDensidad = fichaTecnica.factorDensidad(xIdCliente,
                        new Integer(xIdFicha).intValue());

                fachadaPluFicha.setFactorDensidad(xFactorDensidad);

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoPedido(xPesoPedido);

                //
                double xPesoMillar = fichaTecnica.pesoMillar(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoMillar(xPesoMillar * 1000);

                //
                double xPesoComplemento = fichaTecnica.pesoComplemento(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoComplemento(xPesoComplemento);

                //
                double xMetroPedido = fichaTecnica.metroPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroPedido(xMetroPedido);

                //
                double xMetroRollo = fichaTecnica.metroRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroRollo(xMetroRollo);

                //
                double xPesoRollo = fichaTecnica.pesoRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoRollo(xPesoRollo);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmModFichaTecnica.jsp";

            }

            //
            if (accionContenedor.compareTo("trae") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdFicha = request.getParameter("xIdficha");
                String xItem = request.getParameter("xItem");
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xAccionBoton = request.getParameter("xAccionBoton");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                String xIdCliente = "";
                double xCantidadPedido = 1;

                //---
                if (xAccionBoton.compareTo("traeFicha") == 0) {

                    //
                    colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                    //
                    fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                    //
                    xIdCliente = fachadaPluFicha.getIdCliente();
                    xIdFicha = fachadaPluFicha.getIdFichaStr();

                }

                //---
                if (xAccionBoton.compareTo("traePedido") == 0) {

                    //
                    colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                    colaboraOrdenTrabajo.setItem(xItem);
                    colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);

                    //
                    fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                    //
                    xIdCliente = fachadaPluFicha.getIdCliente();
                    xIdFicha = fachadaPluFicha.getIdFichaStr();
                    xCantidadPedido = fachadaPluFicha.getCantidad();

                }

                //
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xFactorDensidad = fichaTecnica.factorDensidad(xIdCliente,
                        new Integer(xIdFicha).intValue());

                fachadaPluFicha.setFactorDensidad(xFactorDensidad);

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoPedido(xPesoPedido);

                //
                double xPesoMillar = fichaTecnica.pesoMillar(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoMillar(xPesoMillar * 1000);

                //
                double xPesoComplemento = fichaTecnica.pesoComplemento(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoComplemento(xPesoComplemento);

                //
                double xMetroPedido = fichaTecnica.metroPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroPedido(xMetroPedido);

                //
                double xMetroRollo = fichaTecnica.metroRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroRollo(xMetroRollo);

                //
                double xPesoRollo = fichaTecnica.pesoRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoRollo(xPesoRollo);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setItem(xItem);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmModFichaTecnica.jsp";

            }

            if (accionContenedor.compareTo("cambia") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdFicha = request.getParameter("xIdFicha");
                String xItem = request.getParameter("xItem");
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xIdCliente = request.getParameter("xIdCliente");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setItem(xItem);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                double xCantidadPedido = fachadaPluFicha.getCantidad();

                //
                fachadaPluFicha.setCantidad(xCantidadPedido);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setItem(xItem);

                //
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xFactorDensidad = fichaTecnica.factorDensidad(xIdCliente,
                        new Integer(xIdFicha).intValue());

                fachadaPluFicha.setFactorDensidad(xFactorDensidad);

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoPedido(xPesoPedido);

                //
                double xPesoMillar = fichaTecnica.pesoMillar(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoMillar(xPesoMillar * 1000);

                //
                double xPesoComplemento = fichaTecnica.pesoComplemento(xIdCliente,
                        new Integer(xIdFicha).intValue());

                //
                fachadaPluFicha.setPesoComplemento(xPesoComplemento);

                //
                double xMetroPedido = fichaTecnica.metroPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroPedido(xMetroPedido);

                //
                double xMetroRollo = fichaTecnica.metroRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setMetroRollo(xMetroRollo);

                //
                double xPesoRollo = fichaTecnica.pesoRollo(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidadPedido).doubleValue());

                //
                fachadaPluFicha.setPesoRollo(xPesoRollo);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdCliente(xIdCliente);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmCamFichaTecnica.jsp";

            }

            // Consultar Pedido
            if (accionContenedor.compareTo("Consultar Pedido") == 0) {

                //
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xAccionBoton = "traePedido";
                int xIdOperacion = 1;
                int xItem = 1;

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#PEDIDO", xNumeroOrden);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setItem(xItem);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                //
                if (fachadaPluFicha.getIdPlu() == 0) {

                    //
                    validacion.setDescripcionError("NO EXISTE EL #PEDIDO " + xNumeroOrden);
                    validacion.setSolucion("INTENTAR CON OTRO #PEDIDO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                double xCantidadPedido = fachadaPluFicha.getCantidad();
                int xIdFicha = fachadaPluFicha.getIdFicha();

                //
                fachadaPluFicha.setCantidad(xCantidadPedido);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setAccionBoton(xAccionBoton);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdFicha(xIdFicha);
                fachadaDctoOrdenBean.setIdCliente(fachadaPluFicha.getIdCliente());

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmLstFichaTecnica.jsp";

            }
            if (accionContenedor.compareTo("Consultar Pedido2") == 0) {

                //
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xAccionBoton = "traePedido";
                int xIdOperacion = 1;
                int xItem = 1;

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#PEDIDO", xNumeroOrden);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setItem(xItem);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                //
                if (fachadaPluFicha.getIdPlu() == 0) {

                    //
                    validacion.setDescripcionError("NO EXISTE EL #PEDIDO " + xNumeroOrden);
                    validacion.setSolucion("INTENTAR CON OTRO #PEDIDO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                double xCantidadPedido = fachadaPluFicha.getCantidad();
                int xIdFicha = fachadaPluFicha.getIdFicha();

                //
                fachadaPluFicha.setCantidad(xCantidadPedido);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setAccionBoton(xAccionBoton);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdFicha(xIdFicha);
                fachadaDctoOrdenBean.setIdCliente(fachadaPluFicha.getIdCliente());

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmLstFichaTecnica.jsp";

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar Ficha") == 0) {

                //
                String xIdFicha = request.getParameter("xIdFicha");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xAccionBoton = "traeFicha";
                String xCantidadPedido = "1";

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#FICHA", xIdFicha);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                if (fachadaPluFicha.getIdPlu() == 0) {

                    //
                    validacion.setDescripcionError("NO EXISTE LA #FICHA " + xIdFicha);
                    validacion.setSolucion("INTENTAR CON OTRO #FICHA");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                fachadaPluFicha.setCantidad(xCantidadPedido);
                fachadaPluFicha.setAccionBoton(xAccionBoton);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdFicha(xIdFicha);
                fachadaDctoOrdenBean.setIdCliente(fachadaPluFicha.getIdCliente());

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmLstFichaTecnica.jsp";

            }
        }
        //
        return "/jsp/empty.htm";
    }
}
