
package co.linxsi.modelo.maestro.paro_maquina;

import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import java.io.Serializable;

/**
 *
 * @author Desarrollador
 */


public class Paro_Maquina_DTO  extends Operaciones_DTO implements Serializable {

    private int sk_paro_maquina;
    private String nombre_paro_maquina;
    private int sk_estado;

    public int getSk_paro_maquina() {
        return sk_paro_maquina;
    }

    public void setSk_paro_maquina(int sk_paro_maquina) {
        this.sk_paro_maquina = sk_paro_maquina;
    }

    public String getNombre_paro_maquina() {
        return nombre_paro_maquina;
    }

    public void setNombre_paro_maquina(String nombre_paro_maquina) {
        this.nombre_paro_maquina = nombre_paro_maquina;
    }

    public int getSk_estado() {
        return sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }


}
