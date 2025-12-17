/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.operaciones;

/**
 *
 * @author Desarrollador
 */
public class Proceso_Paro_DTO extends Operaciones_DTO{
  
    private int sk_operaciones_paro;

   

    public int getSk_operaciones_paro() {
        return sk_operaciones_paro;
    }

    public void setSk_operaciones_paro(int sk_operaciones_paro) {
        this.sk_operaciones_paro = sk_operaciones_paro;
    }

    public int getSk_paro_maquina() {
        return sk_paro_maquina;
    }

    public void setSk_paro_maquina(int sk_paro_maquina) {
        this.sk_paro_maquina = sk_paro_maquina;
    }
    private int sk_paro_maquina;


    

}
