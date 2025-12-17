package co.plasticosunion;

import co.linxsi.controlador.facturacionelectronica.XMLDocument;
import co.linxsi.modelo.facturacionelectronica.FEDtlDTO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author A
 */
public class XMLDocumentTest {
    
    private XMLDocument xml= new XMLDocument();

    @Test
    public void testformatOrdenesCompra1() {
        // Crear algunos objetos de ejemplo de FEDtlDTO con un valor nulo
        List<FEDtlDTO> listaOrdenes = new ArrayList<>();

        FEDtlDTO orden1 = new FEDtlDTO();
        orden1.setOrcenCompra("OC123");

        FEDtlDTO orden2 = new FEDtlDTO();
        orden2.setOrcenCompra(null); // Este valor es nulo

        FEDtlDTO orden3 = new FEDtlDTO();
        orden3.setOrcenCompra("OC789");

        listaOrdenes.add(orden1);
        listaOrdenes.add(orden2);
        listaOrdenes.add(orden3);

        String resultado = xml.formatOrdenesCompra(listaOrdenes);

        // Verificar que el resultado no contiene nulos
        assertEquals("OC123;OC789", resultado);
    }
    
    
    @Test
    public void testformatOrdenesCompra2() {
        // Crear algunos objetos de ejemplo de FEDtlDTO con un valor nulo
        List<FEDtlDTO> listaOrdenes = new ArrayList<>();

        FEDtlDTO orden1 = new FEDtlDTO();
        orden1.setOrcenCompra("OC123");

        FEDtlDTO orden2 = new FEDtlDTO();
        orden2.setOrcenCompra(null); // Este valor es nulo

        FEDtlDTO orden3 = new FEDtlDTO();
        orden3.setOrcenCompra("");

        listaOrdenes.add(orden1);
        listaOrdenes.add(orden2);
        listaOrdenes.add(orden3);
    
        
        String resultado = xml.formatOrdenesCompra(listaOrdenes);

        // Verificar que el resultado no contiene nulos
        assertEquals("OC123", resultado);
    }
}
