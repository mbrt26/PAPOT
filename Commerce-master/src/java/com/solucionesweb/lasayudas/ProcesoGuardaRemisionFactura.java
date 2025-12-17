package com.solucionesweb.lasayudas;

// Importa la clase que contiene el utilidades
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

//
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ProcesoGuardaRemisionFactura {

    //
    public DctoOrdenDetalleBean guarda(int xIdLog,
            String xIdPlu,
            double xCantidad,
            double xVrVentaUnitario,
            int xItemPadre,
            int xIdTipoOrden,
            double xIdUsuario,
            int xIdLocalUsuario,
            String xIdTercero,
            String xComentario,
            String xIdFicha,
            double xUnidadVenta,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdOrdenOrigen,
            int xItemOrden,
            double xVrVentaUnitarioSinIva,
            double xCantidadTerminada,
            double xPorcentajeIva, double pesoTerminado
    ) {

        //
        int xIdOrdenMax = 0;
        int xIdOrigenWeb = 4;
        int xEstadoDctoOrden = 1;
        int xEstadoNoMarcado = 0;
        String xIdLista = "1";
        int xIdBodega = 1;
        String xIdResponsable = "0";
        int xIdClasificacion = 0;
        double xCero = 0.0;

        //
        Day fechaHoy = new Day();

        //
        FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                = new FachadaColaboraDctoOrdenBean();

        //
        ColaboraDctoOrdenBean colaboraDctoOrdenBean
                = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdLocal(xIdLocalOrigen);
        colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenOrigen);
        colaboraDctoOrdenBean.setIdOrden(xIdOrdenOrigen);

        fachadaColaboraDctoOrdenBean
                = colaboraDctoOrdenBean.listaOrdenFCH();

        //
        double xIdVendedor
                = fachadaColaboraDctoOrdenBean.getIdVendedor();

        //
        DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

        //
        FachadaDctoOrdenBean fachadaDctoOrdenBean
                = new FachadaDctoOrdenBean();

        // Consulta si existeOrden
        dctoOrdenBean.setIdLog(xIdLog);

        //
        fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLog();

        //
        if (fachadaDctoOrdenBean.getIdOrden() > 0) {

            // SI existeOrden
            xIdOrdenMax = fachadaDctoOrdenBean.getIdOrden();

        } else {

            //
            FachadaTerceroBean fachadaTerceroBean
                    = new FachadaTerceroBean();

            //
            TerceroBean terceroBean = new TerceroBean();

            //
            terceroBean.setIdCliente(xIdTercero);

            //
            fachadaTerceroBean = terceroBean.listaUnTerceroFachada();

            //
            String xEmail = fachadaTerceroBean.getEmail();
            String xIdFormaPago
                    = fachadaTerceroBean.getIdFormaPagoStr();
            String xDireccionDespacho
                    = fachadaTerceroBean.getDireccionTercero();
            String xContacto
                    = fachadaTerceroBean.getContactoTercero();
            String xCiudadDespacho
                    = fachadaTerceroBean.getCiudadTercero();

            // NO existeOrden y se igual idLocal = idLocalInicial
            dctoOrdenBean.setIdLocal(xIdLocalUsuario);
            dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);

            //
            xIdOrdenMax
                    = dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

            //
            dctoOrdenBean.setIdLocal(xIdLocalUsuario);
            dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
            dctoOrdenBean.setIdOrden(xIdOrdenMax);
            dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
            dctoOrdenBean.setEstado(xEstadoDctoOrden);
            dctoOrdenBean.setIdCliente(xIdTercero);
            dctoOrdenBean.setIdUsuario(xIdUsuario);
            dctoOrdenBean.setIdOrigen(xIdOrigenWeb);
            dctoOrdenBean.setIdLog(xIdLog);
            dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
            dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrden).toString());
            dctoOrdenBean.setEmail(xEmail);
            dctoOrdenBean.setFormaPago(xIdFormaPago);
            dctoOrdenBean.setIdFicha(xIdFicha);
            dctoOrdenBean.setDireccionDespacho(xDireccionDespacho);
            dctoOrdenBean.setCiudadDespacho(xCiudadDespacho);
            dctoOrdenBean.setContacto(xContacto);
            dctoOrdenBean.setIdVendedor(xIdVendedor);

            //
            boolean okIngreso = dctoOrdenBean.ingresaDctosOrden();

        }

        //
        FachadaPluFicha fachadaPluFicha
                = new FachadaPluFicha();

        //
        ColaboraOrdenTrabajo colaboraOrdenTrabajo
                = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdFicha(xIdFicha);

        //
        fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

        //
        ColaboraPlu colaboraPlu = new ColaboraPlu();

        //
        colaboraPlu.setIdPlu(xIdPlu);

        //
        FachadaPluBean fachadaPluBean = new FachadaPluBean();

        //
        fachadaPluBean = colaboraPlu.listaUnPluFCH();

        // Igual Encabezado = Detalle en IdLocal
        DctoOrdenDetalleBean dctoOrdenDetalleBean
                = new DctoOrdenDetalleBean();

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);
        dctoOrdenDetalleBean.setCantidad(xCantidad);
        dctoOrdenDetalleBean.setNombrePlu(fachadaPluBean.getNombreCategoria()
                + " "
                + fachadaPluBean.getNombrePlu());
        dctoOrdenDetalleBean.setNombreUnidadMedida(
                fachadaPluBean.getIdUVentaStr());
        dctoOrdenDetalleBean.setIdPlu(xIdPlu);
        dctoOrdenDetalleBean.setIdTipo(fachadaPluBean.getIdTipo());
        dctoOrdenDetalleBean.setEstado(xEstadoNoMarcado);
        dctoOrdenDetalleBean.setPorcentajeIva(xPorcentajeIva);
        dctoOrdenDetalleBean.setVrVentaOriginal(xVrVentaUnitario);
        //dctoOrdenDetalleBean.setIdBodega(idBodega);
        dctoOrdenDetalleBean.setVrCosto(fachadaPluBean.getVrCosto());
        dctoOrdenDetalleBean.setStrIdLista(xIdLista);
        dctoOrdenDetalleBean.setStrIdReferencia(fachadaPluBean.getIdPluStr());
        dctoOrdenDetalleBean.setPesoTeorico(0);
        dctoOrdenDetalleBean.setIdLocalSugerido(xIdLocalUsuario);
        dctoOrdenDetalleBean.setIdBodegaSugerido(
                new Integer(xIdLocalUsuario).toString());
        // dctoOrdenDetalleBean.setIdEstadoRefOriginal(idEstadoRefOriginal);
        dctoOrdenDetalleBean.setIdReferenciaOriginal(
                fachadaPluBean.getIdPluStr());
        dctoOrdenDetalleBean.setVrDsctoPie(
                fachadaDctoOrdenBean.getDescuentoComercial());

        //
        ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                = new ColaboraDctoOrdenDetalleBean();

        // maximoItem
        colaboraDctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);

        //
        int maximoItem = colaboraDctoOrdenDetalleBean.maximoItem(xIdLog) + 1;

        // Valida  padre item = itemPadre o Complemento # diferentes
        if (xItemPadre == 0) {

            xItemPadre = maximoItem;

        }

        // Ingresa Historia
        dctoOrdenDetalleBean.setVrVentaUnitario(xVrVentaUnitario);
        dctoOrdenDetalleBean.setVrVentaUnitarioSinIva(xVrVentaUnitarioSinIva);
        dctoOrdenDetalleBean.setStrIdBodega(
                new Integer(xIdLocalUsuario).toString());
        dctoOrdenDetalleBean.setReferenciaCliente(
                fachadaPluFicha.getReferenciaCliente());
        dctoOrdenDetalleBean.setComentario(xComentario);
        dctoOrdenDetalleBean.setItem(maximoItem);
        dctoOrdenDetalleBean.setItemPadre(xItemPadre);
        dctoOrdenDetalleBean.setIdResponsable(xIdResponsable);
        dctoOrdenDetalleBean.setIdClasificacion(xIdClasificacion);
        dctoOrdenDetalleBean.setIdBodega(xIdBodega);
        dctoOrdenDetalleBean.setFechaEntrega(fechaHoy.getFechaFormateada());
        dctoOrdenDetalleBean.setVrImpoconsumo(
                fachadaPluBean.getVrImpoconsumo());
        dctoOrdenDetalleBean.setVrCostoIND(fachadaPluBean.getVrCostoIND()
                - fachadaPluBean.getVrImpoconsumo());
        dctoOrdenDetalleBean.setUnidadVenta(xUnidadVenta);
        dctoOrdenDetalleBean.setIdLocalOrigen(xIdLocalOrigen);
        dctoOrdenDetalleBean.setIdTipoOrdenOrigen(xIdTipoOrdenOrigen);
        dctoOrdenDetalleBean.setIdOrdenOrigen(xIdOrdenOrigen);
        dctoOrdenDetalleBean.setItemOrden(xItemOrden);
        dctoOrdenDetalleBean.setCantidadTerminada(xCantidadTerminada);
        dctoOrdenDetalleBean.setCantidadFacturada(xCero);
        dctoOrdenDetalleBean.setPesoTerminado(pesoTerminado);

        // ingresa OrdenDetalle
        boolean okIngresaDetalle
                = dctoOrdenDetalleBean.ingresaDetalleOrigen();

        //
        dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
        dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenDetalleBean.setIdLog(xIdLog);
        dctoOrdenDetalleBean.setVrDsctoPie(
                fachadaDctoOrdenBean.getDescuentoComercial());

        //--
        dctoOrdenDetalleBean.actualizaDescuentoPie();

        //
        return dctoOrdenDetalleBean;

    }

}
