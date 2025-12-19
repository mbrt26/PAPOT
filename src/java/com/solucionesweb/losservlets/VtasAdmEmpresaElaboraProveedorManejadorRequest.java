package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el ContableRetencionBean
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraHistoriaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;

// Importa la clase que contiene el FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaAgendaProgramacionBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene ProcesoGuardaReferencia
import com.solucionesweb.lasayudas.ProcesoGuardaPluCompra;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaElaboraProveedorManejadorRequest
                                              implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmEmpresaElaboraProveedorManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException,IOException  {

    //
    int xIdTipoTerceroCliente  = 2;
    int xIdTipoOrdenCotizacion = 10;
    int xIdConceptoRFCompra    = 1;
    String xIdListaPrecioGral  = "1";
    int   estadoRetira         = 1;
    int estadoVisita           = 9;

    //
    int estadoActivo           = 9;
    Day day                    = new Day();
    String strFechaVisita      = day.getFechaFormateada();

    //
    HttpSession sesion         = request.getSession();
    UsuarioBean usuarioBean    =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String idUsuario           = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario        = usuarioBean.getIdLocalUsuario();
    String strIdLista          = usuarioBean.getStrIdLista();

    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
            return "/jsp/empty.htm";
        }

        // Regresar
	    if (accionContenedor.compareTo("Regresar") == 0 ) {

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean        =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Retorna a seleccionar cliente
           if (fachadaAgendaLogVisitaBean.getIdCliente()==null) {

              return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

           }

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";

        }

        // Confirmar Cantidad
	    if (accionContenedor.compareTo("Confirmar Cantidad") == 0 )  {

           //
           String idClasificacion    = "1";
           String idResponsable      = "1";
           String idLog              = request.getParameter("idLog");
           String itemStr            = request.getParameter("item");
           String cantidad           = request.getParameter("cantidad");
           String vrVentaUnitario    = request.getParameter("vrVentaUnitario");
           String vrSubtotal         = request.getParameter("chkVrSubtotal");

           //
           Validacion validacion     = new Validacion();

           //
           validacion.reasignar("cantidad",cantidad);
           validacion.validarCampoDouble();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";
           }

           //
           validacion.reasignar("vrSubtotal",vrSubtotal);
           validacion.validarCampoDoublePositivo();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";
           }

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean        =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(idLog);
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           //
           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           //
           String strIdLocal                =
                                           fachadaDctoOrdenBean.getIdLocalStr();
           String xIdTipoOrden              =
                                       fachadaDctoOrdenBean.getIdTipoOrdenStr();
           String xIdOrden                  =
                                           fachadaDctoOrdenBean.getIdOrdenStr();
           String xItem                     = itemStr;

           //
           DctoOrdenDetalleBean dctoOrdenDetalleBean
                                            = new DctoOrdenDetalleBean();

           //
           double xVrCosto                  =
                                         (new Double(vrSubtotal).doubleValue() /
                                          new Double(cantidad).doubleValue());
           
           //
           dctoOrdenDetalleBean.setItem(itemStr);
           dctoOrdenDetalleBean.setCantidad(cantidad);
           dctoOrdenDetalleBean.setVrCosto(xVrCosto);

           //
           dctoOrdenDetalleBean.modificaCompra(strIdLocal,
                                               xIdTipoOrden,
                                               xIdOrden,
                                               xItem);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           ColaboraTercero       colaboraTercero = new ColaboraTercero();

           //
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdTipoOrden(xIdTipoOrdenCotizacion);

           //
           fachadaTerceroBean         = colaboraTercero.listaTerceroFCH();

           //
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";


        }

        // ModificarItem
	    if (accionContenedor.compareTo("ModificarItem") == 0 )  {

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

           //
           fachadaAgendaLogVisitaBean        =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           int idLog                  = fachadaAgendaLogVisitaBean.getIdLog();

           //
           String itemStr             = request.getParameter("item");
           int tipoOrdenCotizacion    = 10;   // orden cotizacion

	       if ( itemStr != null ) {

              //
              FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                                              new FachadaDctoOrdenDetalleBean();

	          //
	          ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                                             new ColaboraDctoOrdenDetalleBean();


              //
              colaboraDctoOrdenDetalleBean.setItem(itemStr);
              colaboraDctoOrdenDetalleBean.setIdTipoOrden(tipoOrdenCotizacion);

              //
              fachadaDctoOrdenDetalleBean =
                             colaboraDctoOrdenDetalleBean.itemLogFachada(idLog);

              //
              fachadaDctoOrdenDetalleBean.setItem(itemStr);
              fachadaDctoOrdenDetalleBean.getCantidad();

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("fachadaDctoOrdenDetalleBean",
                                                   fachadaDctoOrdenDetalleBean);

              //
              request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

              //
              FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

              //
              fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
              fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
              fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

              //
              return "/jsp/vtaFrmModEmpresaElaboraProveedorRetiraPlu.jsp";

           }

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           //
           ColaboraTercero       colaboraTercero = new ColaboraTercero();

           //
           colaboraTercero.setIdTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdTercero(
                                     fachadaAgendaLogVisitaBean.getIdCliente());

           //
           fachadaTerceroBean         = colaboraTercero.listaTerceroFCH();

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";

        }

        // Validar
	    if (accionContenedor.compareTo("+Productos") == 0 )  {

	       //
	       String idLinea        = request.getParameter("idLinea");
	       //String xIdListaPrecio = request.getParameter("xIdListaPrecio");

	       //
           String strCadena = idLinea.trim();
           int    lonCadena = strCadena.length();
           int    posCadena = strCadena.indexOf('+',0);
           String xNombrePlu= "";

           //
           if (posCadena>0) {

              //
              idLinea       = strCadena.substring(0,posCadena).trim();
              xNombrePlu    = strCadena.substring(posCadena+1,lonCadena).trim();

           } else {

             int xIdPlu    = 0;
             String strIdPlu = strCadena;

		     try {
   		         xIdPlu = new Integer(strIdPlu).intValue();
		  	 }
		     catch(NumberFormatException nfe) {
			  xNombrePlu   = idLinea;
              idLinea      = "";
    		}
           }

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                         = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean    =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean =
                                              new FachadaColaboraHistoriaBean();

           //
      	   fachadaColaboraHistoriaBean.setIdLinea(idLinea);
      	   fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

           //
           request.setAttribute("fachadaColaboraHistoriaBean",
                                                   fachadaColaboraHistoriaBean);
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           //
           ColaboraTercero       colaboraTercero
                                         = new ColaboraTercero();

           //
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           //
           fachadaTerceroBean           = colaboraTercero.listaTerceroFCH();

           //
           fachadaTerceroBean.setIdListaPrecio(xIdListaPrecioGral);


           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           return "/jsp/vtaFrmSelEmpresaElaboraProveedorAdicionaPlu.jsp";

        }

        // Confirmar
	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           //
           int idResponsable         = 1;
           int idClasificacion       = 1;
           String referenciaCliente  = "1";
           String comentario         = "";

           //
           String arrIdReferencia[]  =
                                  request.getParameterValues("chkIdReferencia");

           //
           String arrcantidad[]      =
                                  request.getParameterValues("chkCantidad");

           //
           String ArrVrVentaUnitario[]  =
                               request.getParameterValues("chkVrVentaUnitario");

           String ArrVrSubtotal[]  =
                               request.getParameterValues("chkVrSubtotal");

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

           //
           fachadaAgendaLogVisitaBean        =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           int idLog                         =
                                          fachadaAgendaLogVisitaBean.getIdLog();
           double xIdUsuario                 =
                                      fachadaAgendaLogVisitaBean.getIdUsuario();
           String xIdTercero                 =
                                      fachadaAgendaLogVisitaBean.getIdCliente();

           // Retorna a seleccionar cliente
           if (fachadaAgendaLogVisitaBean.getIdCliente()==null) {

              request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
              return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

           }

           //
           Validacion valida           = new Validacion();

           // Validar
           for (int indice=0;indice<arrcantidad.length;indice++){

                //
                if (arrcantidad[indice].length()==0) continue;

                //
                valida.reasignar("Cantidad", arrcantidad[indice]);

                //
    	        valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VrSubtotal", ArrVrSubtotal[indice]);

                //
    	        valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

           }

           //
           ProcesoGuardaPluCompra proceso = new ProcesoGuardaPluCompra();

           //
           for (int indice=0;indice<arrcantidad.length;indice++){

                //
                if (arrcantidad[indice].length()==0) continue;

                //
                double xCantidad        =
                                new Double(arrcantidad[indice]).doubleValue();
                double xVrSubtotal      =
                         new Double(ArrVrSubtotal[indice]).doubleValue();

                double xVrCosto         = ( xVrSubtotal / xCantidad );


                //valida el idTercero sea el mismo para todos
                String strIdReferencia  = arrIdReferencia[indice];
                int xItemPadre          = 0;
                String xComentario      = "ninguno";
                String xIdResponsable   = "0";
                int xIdClasificacion    = 0;

                //
                int maximoItem    = proceso.guarda(idLog,
                                                   strIdReferencia,
                                                   xCantidad,
                                                   xVrCosto,
                                                   xItemPadre,
                                                   xIdTipoOrdenCotizacion,
                                                   xIdUsuario,
                                                   xIdLocalUsuario,
                                                   xIdTercero,
                                                   xComentario,
                                                   xIdResponsable,
                                                   xIdClasificacion);

           }

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           //
           ColaboraTercero       colaboraTercero
                                         = new ColaboraTercero();

           //
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());

           //
           fachadaTerceroBean           = colaboraTercero.listaTerceroFCH();

           //
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";

        }

        // Retirar
	    if (accionContenedor.compareTo("Retirar") == 0 )  {

	       //
           String idLog              = request.getParameter("idLog");
           String itemStr            = request.getParameter("item");

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                         = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean    =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           int estadoRetirar          = 1;   // marcado retirar

	       if ( itemStr != null ) {

	          //
              DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

              //
              dctoOrdenBean.setIdLog(idLog);
              dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

              //
              FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

              //
              fachadaDctoOrdenBean =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

              //
              String strIdLocal             =
                                           fachadaDctoOrdenBean.getIdLocalStr();
              String xIdTipoOrden           =
                                       fachadaDctoOrdenBean.getIdTipoOrdenStr();
              String xIdOrden               =
                                           fachadaDctoOrdenBean.getIdOrdenStr();
              String xItem                  = itemStr;

              //
              DctoOrdenDetalleBean dctoOrdenDetalleBean
                                            = new DctoOrdenDetalleBean();

              //
              dctoOrdenDetalleBean.setEstado(estadoRetirar);

              // modificaEstado
              dctoOrdenDetalleBean.modificaEstado(strIdLocal,
                                                  xIdTipoOrden,
                                                  xIdOrden,
                                                  xItem);

              //
              FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

              //
              fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
              fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
              fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

              //
              ColaboraTercero       colaboraTercero
                                         = new ColaboraTercero();

              //
              colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
              colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());

              //
              fachadaTerceroBean           = colaboraTercero.listaTerceroFCH();

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
              return "/jsp/vtaFrmConEmpresaProveedorConfirmaRetiraPlu.jsp";

           }

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";

        }

        // Finalizar
	    if (accionContenedor.compareTo("Finalizar") == 0 ) {

           //
           String idSucursal       = "00";

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                         = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean    =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           int idLog                  = fachadaAgendaLogVisitaBean.getIdLog();
           int idTipoOrdenCotizacion  = 10;
           String marcadoEspecifico   = "01";

           //
           ColaboraTercero colaboraTercero
                                      = new ColaboraTercero();
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

           //
           FachadaTerceroBean fachadaTerceroBean
                                      = new FachadaTerceroBean();

           //
           fachadaTerceroBean         =  colaboraTercero.listaTerceroFCH();

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           request.setAttribute("fachadaTerceroBean",
                                                    fachadaTerceroBean);

           //
           ColaboraDctoOrdenBean colaboraDctoOrdenBean
                                       = new ColaboraDctoOrdenBean();

           //
           colaboraDctoOrdenBean.setIdTipoOrden(idTipoOrdenCotizacion);
           colaboraDctoOrdenBean.setIdLog(idLog);

           //
           FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                                       = new FachadaColaboraDctoOrdenBean();

           fachadaColaboraDctoOrdenBean
                                     = colaboraDctoOrdenBean.liquidaCompraFCH();

           //
           ContableRetencionBean contableRetencionBean
                                       = new ContableRetencionBean();

           //
           int xIdRteFuenteVrBase      = 0;

           //
           double xVrRetencion         =
                     contableRetencionBean.calculaRetencion(
                               fachadaTerceroBean.getIdAutoRetenedor(),
                               xIdConceptoRFCompra,
                               fachadaColaboraDctoOrdenBean.getVrCostoSinIva(),
                               xIdRteFuenteVrBase);

           // Retorna a seleccionar cliente
           if (fachadaTerceroBean.getIdCliente()==null) {

              return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

            }

            fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(xVrRetencion);
            fachadaColaboraDctoOrdenBean.setVrCostoIva(
                               fachadaColaboraDctoOrdenBean.getVrCostoConIva() -
                               fachadaColaboraDctoOrdenBean.getVrCostoSinIva());
            
            request.setAttribute("fachadaColaboraDctoOrdenBean",
                                                  fachadaColaboraDctoOrdenBean);

            //
            return "/jsp/vtaFrmSelEmpresaFinalizaPedidoProveedor.jsp";

        }

        // Suspender
	    if (accionContenedor.compareTo("Suspender") == 0 ) {

           //
           int estadoSuspendido    = 8;
           int xIdEstadoVisita     = 1;

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoVisita);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                         = new FachadaAgendaLogVisitaBean();

           //
           fachadaAgendaLogVisitaBean    =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           int xIdLogActual              =
                                          fachadaAgendaLogVisitaBean.getIdLog();
           int idTipoOrdenCotizacion     = 10;


           // finalizaVisita
           agendaLogVisitaBean.setIdLog(xIdLogActual);
           agendaLogVisitaBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           agendaLogVisitaBean.setIdUsuario(
                                     fachadaAgendaLogVisitaBean.getIdUsuario());
           agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisita);
           agendaLogVisitaBean.setEstado(estadoSuspendido);

           //
           boolean okLog       =
                agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoVisita);


        }

        // Suspender
	    if (accionContenedor.compareTo("Cotizar") == 0 ) {

           //
           int estadoCotizar       = 13;
           int xIdEstadoVisita     = 1;

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoVisita);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                         = new FachadaAgendaLogVisitaBean();

           //
           fachadaAgendaLogVisitaBean    =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           int xIdLogActual              =
                                          fachadaAgendaLogVisitaBean.getIdLog();
           int idTipoOrdenCotizacion     = 10;


           // finalizaVisita
           agendaLogVisitaBean.setIdLog(xIdLogActual);
           agendaLogVisitaBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           agendaLogVisitaBean.setIdUsuario(
                                     fachadaAgendaLogVisitaBean.getIdUsuario());
           agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisita);
           agendaLogVisitaBean.setEstado(estadoCotizar);

           //
           boolean okLog       =
                agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoVisita);


        }

        // Confirma Retiro
	    if (accionContenedor.compareTo("Confirmar Retiro") == 0 )  {

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean        =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           DctoOrdenBean  dctoOrdenBean = new DctoOrdenBean();
           dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());

           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                              = new FachadaDctoOrdenBean();
           fachadaDctoOrdenBean               =
                                            dctoOrdenBean.listaDctoOrdenIdLog();

           //
           DctoOrdenDetalleBean dctoOrdenDetalleBean
                                              = new DctoOrdenDetalleBean();
           dctoOrdenDetalleBean.setEstado(estadoRetira);
           dctoOrdenDetalleBean.setIdLocal(
                                         fachadaAgendaLogVisitaBean.getIdLog());

           //
           dctoOrdenDetalleBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
           dctoOrdenDetalleBean.setIdTipoOrden(
                                         fachadaDctoOrdenBean.getIdTipoOrden());
           dctoOrdenDetalleBean.setIdOrden(fachadaDctoOrdenBean.getIdOrden());


           // retiraArticulosMarcados
           boolean okRetiro = dctoOrdenDetalleBean.retiraArticulosMarcados();

           // validaArticulosxOrden
           boolean okDetalle = dctoOrdenDetalleBean.validaArticulosxOrden();

           if (!okDetalle) {

              // Retira DctoOrden
              dctoOrdenBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
              dctoOrdenBean.setIdTipoOrden(
                                         fachadaDctoOrdenBean.getIdTipoOrden());
              dctoOrdenBean.setIdOrden(fachadaDctoOrdenBean.getIdOrden());

              //
              dctoOrdenBean.retiraDctoOrden();

           }

           // Retorna a Contenedor
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           fachadaAgendaLogVisitaBean     =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           // Retorna a seleccionar cliente
           if (fachadaAgendaLogVisitaBean.getIdCliente()==null) {

              return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

           }

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";

        }
    }

    //
    return "/jsp/empty.htm";

  }
}