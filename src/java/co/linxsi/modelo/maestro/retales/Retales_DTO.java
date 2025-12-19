
package co.linxsi.modelo.maestro.retales;

import co.linxsi.modelo.maestro.paro_maquina.*;
import java.io.Serializable;

/**
 *
 * @author Desarrollador
 */


public class Retales_DTO  implements Serializable {
    int sk_retal;
    String nombre_retal;
    int sk_estado;

    public int getSk_retal() {
        return sk_retal;
    }

    public void setSk_retal(int sk_retal) {
        this.sk_retal = sk_retal;
    }

    public String getNombre_retal() {
        return nombre_retal;
    }

    public void setNombre_retal(String nombre_retal) {
        this.nombre_retal = nombre_retal;
    }

    public int getSk_estado() {
        return sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }

  

   }
