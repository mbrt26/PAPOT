package com.solucionesweb.losbeans.utilidades;

import co.linxsi.modelo.cliente.cotizacion2.DAO_Referencia2;
import co.linxsi.modelo.cliente.cotizacion2.mixmanager.DAO_Machine_Option;
import co.linxsi.modelo.cliente.cotizacion2.mixmanager.DAO_Mix_Option;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

public class FichaTecnica implements IFichaTecnica {

    private DAO_Referencia2 daoRef = new DAO_Referencia2();
    private ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

    public FichaTecnica(DAO_Referencia2 daoRef, ColaboraOrdenTrabajo colaboraOrdenTrabajo) {
        this.daoRef = daoRef;
        this.colaboraOrdenTrabajo = colaboraOrdenTrabajo;
    }

    public FichaTecnica() {
    }

    //
    public double factorDensidad(String xIdCliente,
            int xIdFicha) {
        String codigoMezcla = "643";
        String codigoMaquina = "600";
        int xIdOperacion = 2;
        DAO_Mix_Option daoMixOption = new DAO_Mix_Option();
        DAO_Machine_Option dAO_Machine_Option = new DAO_Machine_Option();

        int idMezclaSeleccionada = (int) colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                codigoMezcla);
        int idMachine = (int) colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                codigoMaquina);
        int numberOfLayers = dAO_Machine_Option.getnumberOfLayersFromIdMachine(idMachine);

        return daoMixOption.getFactorDensidad(idMezclaSeleccionada, numberOfLayers);
    }

    //
    public double pesoMillar(String xIdCliente,
            int xIdFicha) {
        String codigoMezcla = "643";
        String codigoMaquina = "600";
        int xIdOperacion = 2;
        DAO_Mix_Option daoMixOption = new DAO_Mix_Option();
  DAO_Machine_Option dAO_Machine_Option = new DAO_Machine_Option();
            int idMezclaSeleccionada = (int) colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                codigoMezcla);
        int idMachine = (int) colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                codigoMaquina);
        int numberOfLayers = dAO_Machine_Option.getnumberOfLayersFromIdMachine(idMachine);

        return daoMixOption.getPesoMillar(xIdFicha,idMezclaSeleccionada,numberOfLayers) / 1000;
    }

    //
    public double pesoPedido(String xIdCliente,
            int xIdFicha,
            double xCantidadPedido) {

        double xPesoMillar = pesoMillar(xIdCliente,
                xIdFicha);

        //
        //----
        String xPorcentajeRetal = "511";
        int xIdOperacion = 2;

        //
        double xRetal = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                xPorcentajeRetal);

        //
        return xCantidadPedido * xPesoMillar * (1 + xRetal / 100);

    }

    //
    public double pesoComplemento(String xIdCliente,
            int xIdFicha) {

        double xAncho = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha, 2, "501");//501 Ancho
        double xCalibre = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha, 2, "503");//503 Calibre
        double xFuelle1 = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha, 2, "508");//508 Fuelle 1
        double xFuelle2 = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha, 2, "504");//508 Fuelle 2
        double xTipoRollo = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha, 2, "602");//602 Tipo de Rollo
        double factor = xTipoRollo == 1.0 ? 0.5 : 1;
        double xDensidad = daoRef.getFactorDensidad(xIdFicha);

        //
        return (xAncho + xFuelle1 + xFuelle2) * xCalibre * 100 * xDensidad * factor;

    }

    //
    public double metroPedido(String xIdCliente,
            int xIdFicha,
            double xCantidadPedido) {

        //
        double xPesoPedido = pesoPedido(xIdCliente,
                xIdFicha,
                xCantidadPedido);

        //
        double xPesoComplemento = pesoComplemento(xIdCliente,
                xIdFicha);
        //
        if (xPesoComplemento == 0.0) {

            //
            return 0.0;

        }

        //
        return (xPesoPedido / (xPesoComplemento / 1000));

    }

    //
    public double metroRollo(String xIdCliente,
            int xIdFicha,
            double xCantidadPedido) {

        //
        double xMetroPedido = metroPedido(xIdCliente,
                xIdFicha,
                xCantidadPedido);

        //---- numero Rollo
        String xNumeroRollo = "606";
        int xIdOperacion = 2;

        //
        //
        double xNumeroRolloPedido = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                xNumeroRollo);

        //
        if (xNumeroRolloPedido == 0.0) {

            //
            return 0.0;

        }

        //
        return (xMetroPedido / xNumeroRolloPedido);

    }

    //
    public double pesoRollo(String xIdCliente,
            int xIdFicha,
            double xCantidadPedido) {

        //
        double xPesoPedido = pesoPedido(xIdCliente,
                xIdFicha,
                xCantidadPedido);

        //---- numero Rollo
        String xNumeroRollo = "606";
        int xIdOperacion = 2;

        //
        ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        double xNumeroRolloPedido = colaboraOrdenTrabajo.valorEscala(xIdCliente,
                xIdFicha,
                xIdOperacion,
                xNumeroRollo);

        //
        if (xNumeroRolloPedido == 0.0) {

            //
            return 0.0;

        }

        //
        return (xPesoPedido / xNumeroRolloPedido);

    }
}
