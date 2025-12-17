package com.solucionesweb.lasayudas;

import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el utilidades
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcesoOrdenCompra {

    public boolean guarda(int xIdLocal,
                          int xIdTipoOrdenCompraProceso,
                          int xIdLogActual,
                          String xIpTx) {

        //
        int xIdOrdenMax = 0;
        int xCero       = 0;
        String xNada    = "";

        //
        Day fechaHoy = new Day();

        //
        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

        //
        FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

        // Consulta si existeOrden
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        dctoOrdenBean.setIdLog(xIdLogActual);

        //
        fachadaDctoOrdenBean = dctoOrdenBean.listaOrdenFCH();

        // NO existeOrden y se igual idLocal = idLocalInicial
        dctoOrdenBean.setIdLocal(xIdLocal);

        //
        int xIdOrdenOld   = fachadaDctoOrdenBean.getIdOrden();

        //
        xIdOrdenMax =
                dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

        //
        int xIdPeriodo     = 200611;
        int xIdEstadoTx    = 1;
        int estadoAtendido = 1; // visitaActiva
        int estadoProgramada = 9; // visitaProgramada
        int idEstadoVisita = 1; // Programada

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        int xIdLogNew = agendaLogVisitaBean.maximoIdLog() + 1;

        //
        agendaLogVisitaBean.setIdUsuario(fachadaDctoOrdenBean.getIdUsuario());
        agendaLogVisitaBean.setIdCliente(fachadaDctoOrdenBean.getIdCliente());
        agendaLogVisitaBean.setIdPeriodo(xIdPeriodo);
        agendaLogVisitaBean.setFechaVisita(fechaHoy.getFechaFormateada());
        agendaLogVisitaBean.setIdLog(xIdLogNew);
        agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
        agendaLogVisitaBean.setEstado(estadoAtendido);
        agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);

        //
        boolean okIngresoLog = agendaLogVisitaBean.ingresa();

        //
        dctoOrdenBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        dctoOrdenBean.setIdOrden(xIdOrdenMax);
        dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
        dctoOrdenBean.setEstado(fachadaDctoOrdenBean.getEstado());
        dctoOrdenBean.setIdCliente(fachadaDctoOrdenBean.getIdCliente());
        dctoOrdenBean.setIdUsuario(fachadaDctoOrdenBean.getIdUsuario());
        dctoOrdenBean.setIdOrigen(fachadaDctoOrdenBean.getIdOrigen());
        dctoOrdenBean.setIdLog(xIdLogNew);
        dctoOrdenBean.setFechaEntrega(fachadaDctoOrdenBean.getFechaEntrega());
        dctoOrdenBean.setTipoDcto(
                             new Integer(xIdTipoOrdenCompraProceso).toString());
        dctoOrdenBean.setEmail(fachadaDctoOrdenBean.getEmail());
        dctoOrdenBean.setFormaPago(fachadaDctoOrdenBean.getFormaPago());
        dctoOrdenBean.setDiasHistoria(
                fachadaDctoOrdenBean.getDiasHistoria());
        dctoOrdenBean.setDiasInventario(
                fachadaDctoOrdenBean.getDiasInventario());
        dctoOrdenBean.setObservacion(fachadaDctoOrdenBean.getObservacion());

        //
        boolean okIngresoOrden = dctoOrdenBean.ingresaDctosOrden();

        //----------------------------------------------------------------------
        DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenOld);

        // actualiza
        boolean okIngresaDetalle =
                       dctoOrdenDetalleBean.actualiza(xIdTipoOrdenCompraProceso,
                                                      xIdOrdenMax);

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocal);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);

        // retiraCantidadPedidaCero
        dctoOrdenDetalleBean.retiraCantidadPedidaCero();


           //---
           JhDate jhDate         = new JhDate();

           //
           String fechaTxHM      = null;

           try {
                 fechaTxHM = jhDate.getDate(4, false);
           } catch (Exception ex) {
                    Logger.getLogger(ProcesoOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
           }

        //
            estadoAtendido = 1; // visitaActiva
            idEstadoVisita = 8; // Proceso

        //
        agendaLogVisitaBean.setFechaVisita(fechaHoy.getFechaFormateada());
        agendaLogVisitaBean.setIdLog(xIdLogNew);
        agendaLogVisitaBean.setEstado(idEstadoVisita);
        agendaLogVisitaBean.setIdEstadoVisita(estadoAtendido);
        agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTx);
        agendaLogVisitaBean.setIdLocal(xIdLocal);
        agendaLogVisitaBean.setIpTx(xIpTx);
        agendaLogVisitaBean.setFechaTx(fechaTxHM);

        //
        boolean okFinaliza =
                agendaLogVisitaBean.finalizaVisita();

        // Consulta si existeOrden
        dctoOrdenBean.setIdLocal(xIdLocal);
        dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
        dctoOrdenBean.setIdLog(xIdLogActual);
        dctoOrdenBean.setDiasHistoria(xCero);
        dctoOrdenBean.setDiasInventario(xCero);
        dctoOrdenBean.setObservacion(xNada);

        //
        dctoOrdenBean.guardaResurtido();

        //
        return okIngresaDetalle;

    }
}

