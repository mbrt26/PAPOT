package com.solucionesweb.lasayudas;

//--
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionCostoBean;

//--
public class ProcesoGuardaOTProgreso {

    //
    public boolean guarda_OS(int xIdLocal,
            int xIdTipoOrden,
            int xIdOrden,
            int xItemPadre,
            int xIdOperacion,
            double xIdOperario,
            double xCantidadTerminada,
            double xPesoTerminado,
            double xPesoPerdido,
            int xIdCausa,
            double xPesoTara) {

        //--------------------------------------------------------------
        DctoOrdenProgresoBean dctoOrdenProgresoBean
                                                  = new DctoOrdenProgresoBean();

        //
        dctoOrdenProgresoBean.setIdLocal(xIdLocal);
        dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenProgresoBean.setIdOrden(xIdOrden);
        dctoOrdenProgresoBean.setItemPadre(xItemPadre);
        dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
        dctoOrdenProgresoBean.setIdOperario(xIdOperario);

        //---
        int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

        //---
        FachadaJobOperacionCosto fachadaJobOperacionCosto
                                               = new FachadaJobOperacionCosto();

        //
        JobOperacionCostoBean jobOperacionCostoBean =
                new JobOperacionCostoBean();

        //---
        jobOperacionCostoBean.setIdLocal(xIdLocal);
        jobOperacionCostoBean.setIdOperacion(xIdOperacion);

        //---
        fachadaJobOperacionCosto =
                jobOperacionCostoBean.listaCostoFCH();

        //---
        double xVrCostoBaseMAT =
                fachadaJobOperacionCosto.getVrCostoBaseMAT();
        double xVrCostoBaseMOD = 0;
        double xVrCostoBaseCIF = 0;

        //---
        int xIdTipoCausaRetal = 2;
        int xEstado = 1;
        double xCantidadPerdida = 0.0;

        //---
        dctoOrdenProgresoBean.setItem(xMaximoItem);
        dctoOrdenProgresoBean.setIdOperario(xIdOperario);
        dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
        dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
        dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
        dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
        dctoOrdenProgresoBean.setIdCausa(xIdCausa);
        dctoOrdenProgresoBean.setEstado(xEstado);
        dctoOrdenProgresoBean.setItemPadre(xItemPadre);
        dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
        dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
        dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
        dctoOrdenProgresoBean.setPesoTara(xPesoTara);
        dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaRetal);

        //---
        boolean okIngresa = dctoOrdenProgresoBean.ingresa();

        //
        return okIngresa;

    }

    //
    public boolean guarda_OT(int xIdLocal,
            int xIdTipoOrden,
            int xIdOrden,
            int xItemPadre,
            int xIdOperacion,
            double xIdOperario,
            double xCantidadTerminada,
            double xPesoTerminado,
            double xPesoPerdido,
            int xIdCausa,
            double xPesoTara) {

        //--------------------------------------------------------------
        DctoOrdenProgresoBean dctoOrdenProgresoBean
                                                  = new DctoOrdenProgresoBean();

        //
        dctoOrdenProgresoBean.setIdLocal(xIdLocal);
        dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
        dctoOrdenProgresoBean.setIdOrden(xIdOrden);
        dctoOrdenProgresoBean.setItemPadre(xItemPadre);
        dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
        dctoOrdenProgresoBean.setIdOperario(xIdOperario);

        //---
        int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

        //---
        FachadaJobOperacionCosto fachadaJobOperacionCosto
                                               = new FachadaJobOperacionCosto();

        //
        JobOperacionCostoBean jobOperacionCostoBean =
                new JobOperacionCostoBean();

        //---
        jobOperacionCostoBean.setIdLocal(xIdLocal);
        jobOperacionCostoBean.setIdOperacion(xIdOperacion);

        //---
        fachadaJobOperacionCosto =
                jobOperacionCostoBean.listaCostoFCH();

        //---
        double xVrCostoBaseMAT =
                fachadaJobOperacionCosto.getVrCostoBaseMAT();
        double xVrCostoBaseMOD =
                fachadaJobOperacionCosto.getVrCostoBaseMOD();
        double xVrCostoBaseCIF =
                fachadaJobOperacionCosto.getVrCostoBaseCIF();

        //---
        int xIdTipoCausaRetal = 2;
        int xEstado = 1;
        double xCantidadPerdida = 0.0;

        //---
        dctoOrdenProgresoBean.setItem(xMaximoItem);
        dctoOrdenProgresoBean.setIdOperario(xIdOperario);
        dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
        dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
        dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
        dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
        dctoOrdenProgresoBean.setIdCausa(xIdCausa);
        dctoOrdenProgresoBean.setEstado(xEstado);
        dctoOrdenProgresoBean.setItemPadre(xItemPadre);
        dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
        dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
        dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
        dctoOrdenProgresoBean.setPesoTara(xPesoTara);
        dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaRetal);

        //---
        boolean okIngresa = dctoOrdenProgresoBean.ingresa();

        //
        return okIngresa;

    }

}
