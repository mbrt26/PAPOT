package co.plasticosunion;

 
import co.linxsi.controlador.facturacionelectronica.FacturacionElectronicaController;
import co.linxsi.modelo.facturacionelectronica.FacturacionElectronicaDAO;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edgar J García L
 */
public class FacturacionElectronicaDAOTest {

    @Test
    public void noExisteDct() {
        FacturacionElectronicaDAO fact = new FacturacionElectronicaDAO();
        boolean existeDocumento = fact.existeDctoFacturaElectronica(15804100);
        Assert.assertEquals(false, existeDocumento);

    }

    @Test
    public void verificarDctoFENoExistente() {

        FacturacionElectronicaController feController = new FacturacionElectronicaController();
        boolean result = feController.verificarGuardarDctoFalloTransmision(9955217, 9, 48514, new Exception("Ex"));
        Assert.assertEquals(true, result);

    }

    @Test
    public void verificarDctoNCNoExistente() {
        FacturacionElectronicaController feController = new FacturacionElectronicaController();
        boolean result = feController.verificarGuardarDctoFalloTransmision(9955216, 29, 3000, new Exception("Ex"));
        Assert.assertEquals(true, result);

    }

    @Test
    public void documentoFEInferiorAlMinimo() {
   
        boolean result = FacturacionElectronicaController.verificarGuardarDctoFalloTransmision(991105, 9, 29738, new Exception("Ex"));
        Assert.assertFalse(result);

    }
    @Test
    public void documentoNCInferiorAlMinimo() {
   
        boolean result =  FacturacionElectronicaController.verificarGuardarDctoFalloTransmision(951109, 29, 2698, new Exception("Ex"));
        Assert.assertFalse(result);

    }
}
