package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.fachada.FachadaContableCree;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.ContableCreeBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VtasAdmCatalogoClienteManejadorRequest
        implements GralManejadorRequest {

    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTipoTerceroCliente = "1";
        int xIndicadorInicial = 1;
        String xIdRutaInicial = "01";
        String xCiudadTerceroInicial = "";

        String accionContenedor = request.getParameter("accionContenedor");

        System.out.println("accionContenedor :" + accionContenedor);
        boolean xOkIngreso;
        if (accionContenedor != null) {
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }
            if (accionContenedor.compareTo("Consultar") == 0) {
                String nombreTercero = request.getParameter("nombreTercero");
                nombreTercero = "'%" + nombreTercero.trim().toUpperCase() + "%'";

                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                fachadaTerceroBean.setNombreTercero(nombreTercero);
                fachadaTerceroBean.setIdTipoTercero(idTipoTerceroCliente);

                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                return "/jsp/vtaFrmSelCatalogoCliente.jsp";
            }
            if (accionContenedor.compareTo("Listar") == 0) {
                String nombreTercero = request.getParameter("nombreTercero");
                nombreTercero = "'%" + nombreTercero.trim().toUpperCase() + "%'";

                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                fachadaTerceroBean.setNombreTercero(nombreTercero);
                fachadaTerceroBean.setIdTipoTercero(idTipoTerceroCliente);

                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment;filename=archivo.xls");

                return "/jsp/vtaFrmLstSelCatalogoCliente.jsp";
            }

            boolean xOkElimino;
            if (accionContenedor.compareTo("Eliminar") == 0) {

                String radIdTercero = request.getParameter("radIdTercero");
                if (radIdTercero == null) {
                    return "/jsp/vtaContenedorCatalogoCliente.jsp";
                }
                TerceroBean terceroBean = new TerceroBean();
                terceroBean.setIdTercero(radIdTercero);

                xOkElimino = terceroBean.eliminaTercero();
            }

            if (accionContenedor.compareTo("Seleccionar") == 0) {

                String xIdTipoTercero = request.getParameter("xIdTipoTercero");
                String xIdCliente = request.getParameter("xIdCliente");

                if (xIdCliente == null) {
                    return "/jsp/vtaContenedorCatalogoCliente.jsp";
                }
                TerceroBean terceroBean = new TerceroBean();

                terceroBean.setIdCliente(xIdCliente);
                terceroBean.setIdTipoTercero(xIdTipoTercero);

                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

                HttpSession sesion = request.getSession(true);

                sesion.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                sesion.setAttribute("fachadaTerceroTipoIdTercero", fachadaTerceroBean);

                sesion.setAttribute("fachadaTerceroIdPersona", fachadaTerceroBean);
                sesion.setAttribute("fachadaTerceroIdAutoRetenedor", fachadaTerceroBean);

                sesion.setAttribute("fachadaTerceroIdRteIva", fachadaTerceroBean);

                sesion.setAttribute("fachadaTerceroIdRteIvaVrBase", fachadaTerceroBean);

                sesion.setAttribute("fachadaTerceroIdRteFuenteVrBase", fachadaTerceroBean);

                sesion.setAttribute("fachadaTerceroIdRegimen", fachadaTerceroBean);
                sesion.setAttribute("fachadaTerceroTipoTercero", fachadaTerceroBean);
                sesion.setAttribute("fachadaTerceroDptoCiudad", fachadaTerceroBean);

                return "/jsp/vtaFrmModCatalogoCliente.jsp";
            }

            boolean xOkActualizo;

            if (accionContenedor.compareTo("Modificar") == 0) {
                String xIdTipoTercero = request.getParameter("xIdTipoTercero");

                String nombreTercero = request.getParameter("nombreTercero");
                String xIdCliente = request.getParameter("xIdCliente");
                String tipoIdTercero = request.getParameter("tipoIdTercero");
                String xDigitoVerificacion = request.getParameter("xDigitoVerificacion");

                String idTipoTercero = idTipoTerceroCliente;
                String idPersona = request.getParameter("idPersona");
                String idAutoRetenedor = request.getParameter("idAutoRetenedor");
                String idRegimen = request.getParameter("idRegimen");
                String direccionTercero = request.getParameter("direccionTercero");
                String idDptoCiudad = request.getParameter("idDptoCiudad");
                String telefonoFijo = request.getParameter("telefonoFijo");
                String telefonoCelular = request.getParameter("telefonoCelular");
                String telefonoFax = request.getParameter("telefonoFax");
                String email = request.getParameter("email");
                String idFormaPago = request.getParameter("idFormaPago");
                String xIndicador = request.getParameter("xIndicador");
                String xContactoTercero = request.getParameter("xContactoTercero");
                String xIdListaPrecio = request.getParameter("xIdListaPrecio");
                String xNombreEmpresa = request.getParameter("xNombreEmpresa");
                String xCupoCredito = request.getParameter("xCupoCredito");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xEstado = request.getParameter("xEstado");
                String xIdRteIva = request.getParameter("xIdRteIva");
                String xIdRteIvaVrBase = request.getParameter("xIdRteIvaVrBase");
                String xIdRteFuenteVrBase = request.getParameter("xIdRteFuenteVrBase");
                String xIdClase = request.getParameter("idClase");
                String xIdRteCree = request.getParameter("xIdRteCree");
                String xFechaMaxFacturacion = request.getParameter("xIdFechaMax");
                int idOperacionFactura = Integer.parseInt(request.getParameter("xIdOp"));
                String[] idRes = new String[10];

                for (int i = 1; i < 11; i++) {
                    idRes[i-1] = request.getParameter("xIdRes" + i);
                }
                if (xIdCliente != null) {
                    Validacion validacion = new Validacion();

                    validacion.reasignar("NIT/CEDULA", xIdCliente);
                    validacion.validarCampoDouble();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("nombreTercero", nombreTercero.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("tipoIdTercero", tipoIdTercero.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idTipoTercero", idTipoTercero.trim());
                    validacion.validarCampoEntero();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("Digito Verificacion", xDigitoVerificacion);
                    validacion.validarCampoDoublePositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("ACTIVIDAD ECONOMICA", xIdRteCree);
                    validacion.validarCampoDoublePositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idRegimen", idRegimen.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("direccionTercero", direccionTercero.trim().toUpperCase());

                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idDptoCiudad", idDptoCiudad.trim());
                    validacion.validarCampoEntero();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("telefonoFijo", telefonoFijo.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("indicador", xIndicador);
                    validacion.validarCampoEnteroIndicador();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idFormaPago", idFormaPago.trim());
                    validacion.validarCampoEnteroPositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("ListaPrecio", xIdListaPrecio);
                    validacion.validarCampoEntero();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("Vendedor", xIdVendedor);
                    validacion.validarCampoDoublePositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    FachadaContableCree fachadaContableCree = new FachadaContableCree();

                    ContableCreeBean contableCreeBean = new ContableCreeBean();

                    contableCreeBean.setIdRteCree(xIdRteCree);

                    fachadaContableCree = contableCreeBean.listaUnFCH();
                    if (fachadaContableCree.getIdRteCree() == 0) {
                        validacion.reasignar("NO EXISTE ACTIVIDAD ECONOMICA " + xIdRteCree, fachadaContableCree.getIdRteCreeStr());

                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    TerceroBean terceroBean = new TerceroBean();

                    terceroBean.setIdCliente(xIdCliente);
                    terceroBean.setIdTipoTercero(xIdTipoTercero);
                    terceroBean.setNombreTercero(nombreTercero);
                    terceroBean.setIdTercero(xIdCliente);
                    terceroBean.setTipoIdTercero(tipoIdTercero);
                    terceroBean.setDigitoVerificacion(xDigitoVerificacion);
                    terceroBean.setIdTipoTercero(idTipoTercero);
                    terceroBean.setIdPersona(idPersona);
                    terceroBean.setIdAutoRetenedor(idAutoRetenedor);
                    terceroBean.setIdRegimen(idRegimen);
                    terceroBean.setDireccionTercero(direccionTercero);
                    terceroBean.setIdDptoCiudad(idDptoCiudad);
                    terceroBean.setTelefonoFijo(telefonoFijo);
                    terceroBean.setTelefonoCelular(telefonoCelular);
                    terceroBean.setTelefonoFax(telefonoFax);
                    terceroBean.setEmail(email);
                    terceroBean.setIdFormaPago(idFormaPago);
                    terceroBean.setEstado(xEstado);
                    terceroBean.setNombreEmpresa(xNombreEmpresa);
                    terceroBean.setContactoTercero(xContactoTercero);
                    terceroBean.setIdListaPrecio(xIdListaPrecio);
                    terceroBean.setNombreEmpresa(xNombreEmpresa);
                    terceroBean.setCupoCredito(xCupoCredito);
                    terceroBean.setIndicador(xIndicador);
                    terceroBean.setIdVendedor(xIdVendedor);
                    terceroBean.setIdRteIva(xIdRteIva);
                    terceroBean.setIdRteIvaVrBase(xIdRteIvaVrBase);
                    terceroBean.setIdRteFuenteVrBase(xIdRteFuenteVrBase);
                    terceroBean.setIdRteCree(Integer.parseInt(xIdRteCree));
                    terceroBean.setDiaMaxFacturacion(Integer.parseInt(xFechaMaxFacturacion));
                    xOkActualizo = terceroBean.actualiza();
                    terceroBean.setIdOperacionFactura(idOperacionFactura);
                    terceroBean.setIdResponsabilidades(idRes);
                    terceroBean.deleteParFactura();
                    xOkActualizo = xOkActualizo && terceroBean.ingresaParFactura();

                }
            }

            if (accionContenedor.compareTo("Ingresar") == 0) {
                String xIdCliente = request.getParameter("xIdCliente");
                String nombreTercero = request.getParameter("nombreTercero");
                String idTercero = request.getParameter("xIdCliente");
                String tipoIdTercero = request.getParameter("tipoIdTercero");
                String digitoVerificacion = request.getParameter("xDigitoVerificacion");

                String idTipoTercero = idTipoTerceroCliente;
                String idPersona = request.getParameter("idPersona");
                String idAutoRetenedor = request.getParameter("idAutoRetenedor");
                String idRegimen = request.getParameter("idRegimen");
                String direccionTercero = request.getParameter("direccionTercero");
                String idDptoCiudad = request.getParameter("idDptoCiudad");
                String telefonoFijo = request.getParameter("telefonoFijo");
                String telefonoCelular = request.getParameter("telefonoCelular");
                String telefonoFax = request.getParameter("telefonoFax");
                String email = request.getParameter("email");
                String idFormaPago = request.getParameter("idFormaPago");
                String xContactoTercero = request.getParameter("xContactoTercero");

                String xIdListaPrecio = "1";

                String xNombreEmpresa = nombreTercero;
                String xCupoCredito = request.getParameter("xCupoCredito");
                String xIdVendedor = "0";

                String xIdRteIva = request.getParameter("xIdRteIva");
                String xIdRteIvaVrBase = request.getParameter("xIdRteIvaVrBase");
                String xIdRteFuenteVrBase = request.getParameter("xIdRteFuenteVrBase");
                String xIdClase = request.getParameter("idClase");
                String xIdRteCree = request.getParameter("xIdRteCree");
                int xFechaMaxFacturacion = Integer.parseInt(request.getParameter("xIdFechaMax"));
                int idOperacionFactura = Integer.parseInt(request.getParameter("xIdOp"));
                String[] idRes = new String[10];

                for (int i = 1; i < 11; i++) {
                    idRes[i - 1] = request.getParameter("xIdRes" + i);
                }

                int estadoActivo = 1;
                if (xIdCliente != null) {
                    Validacion validacion = new Validacion();

                    validacion.reasignar("NOMBRE", nombreTercero.trim());

                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("NIT/CC", idTercero);
                    validacion.validarCampoDouble();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("tipoIdTercero", tipoIdTercero.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idTipoTercero", idTipoTercero.trim());
                    validacion.validarCampoEntero();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("ACTIVIDAD ECONOMICA", xIdRteCree);
                    validacion.validarCampoDoublePositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idRegimen", idRegimen.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("DIRECCION", direccionTercero.trim().toUpperCase());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idDptoCiudad", idDptoCiudad.trim());
                    validacion.validarCampoEntero();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("telefonoFijo", telefonoFijo.trim());
                    validacion.validarCampoString();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("idFormaPago", idFormaPago.trim());
                    validacion.validarCampoEnteroPositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("ListaPrecio", xIdListaPrecio);
                    validacion.validarCampoEntero();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("CupoCredito", xCupoCredito);
                    validacion.validarCampoEnteroPositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    validacion.reasignar("Vendedor", xIdVendedor);
                    validacion.validarCampoDoublePositivo();
                    if (!validacion.isValido()) {
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    FachadaContableCree fachadaContableCree = new FachadaContableCree();

                    ContableCreeBean contableCreeBean = new ContableCreeBean();

                    contableCreeBean.setIdRteCree(xIdRteCree);

                    fachadaContableCree = contableCreeBean.listaUnFCH();
                    if (fachadaContableCree.getIdRteCree() == 0) {
                        validacion.reasignar("NO EXISTE ACTIVIDAD ECONOMICA " + xIdRteCree, fachadaContableCree.getIdRteCreeStr());

                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";
                    }
                    TerceroBean terceroBean = new TerceroBean();

                    terceroBean.setIdCliente(xIdCliente);
                    terceroBean.setIdTercero(idTercero);
                    terceroBean.setTipoIdTercero(tipoIdTercero);
                    terceroBean.setDigitoVerificacion(digitoVerificacion);
                    terceroBean.setIdTipoTercero(idTipoTercero);
                    terceroBean.setIdPersona(idPersona);
                    terceroBean.setIdAutoRetenedor(idAutoRetenedor);
                    terceroBean.setIdRegimen(idRegimen);
                    terceroBean.setNombreTercero(nombreTercero);
                    terceroBean.setDireccionTercero(direccionTercero);
                    terceroBean.setIdDptoCiudad(idDptoCiudad);
                    terceroBean.setTelefonoFijo(telefonoFijo);
                    terceroBean.setTelefonoCelular(telefonoCelular);
                    terceroBean.setTelefonoFax(telefonoFax);
                    terceroBean.setEmail(email);
                    terceroBean.setIdFormaPago(idFormaPago);
                    terceroBean.setEstado(estadoActivo);
                    terceroBean.setEstado(estadoActivo);
                    terceroBean.setIdRuta(xIdRutaInicial);
                    terceroBean.setNombreEmpresa(xNombreEmpresa);
                    terceroBean.setCupoCredito(xCupoCredito);
                    terceroBean.setIndicador(xIndicadorInicial);
                    terceroBean.setCiudadTercero(xCiudadTerceroInicial);
                    terceroBean.setContactoTercero(xContactoTercero);
                    terceroBean.setIdListaPrecio(xIdListaPrecio);
                    terceroBean.setIdVendedor(xIdVendedor);
                    terceroBean.setIdRteIva(xIdRteIva);
                    terceroBean.setIdRteIvaVrBase(xIdRteIvaVrBase);
                    terceroBean.setIdRteFuenteVrBase(xIdRteFuenteVrBase);
                    terceroBean.setIdRteCree(xIdRteCree);
                    terceroBean.setIdClase(xIdClase);
                    terceroBean.setFechaCreacion("GETDATE()");
                    terceroBean.setDiaMaxFacturacion(xFechaMaxFacturacion);

                    xOkIngreso = terceroBean.ingresaTercero();

                    //Este anexo se coloca para guardar el Tipo de Operacion y las responsabilidades fiscales para dar cumplimiento a los requisitos exigidos por la DIAN
                    terceroBean.setIdOperacionFactura(idOperacionFactura);
                    terceroBean.setIdResponsabilidades(idRes);
                    xOkIngreso = xOkIngreso && terceroBean.ingresaParFactura();

                }
            }
        }
        return "/jsp/vtaContenedorCatalogoCliente.jsp";
    }
}
