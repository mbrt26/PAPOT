package co.linxsi.modelo.maestro.calificadores;

import co.linxsi.modelo.maestro.bodega.*;
import java.io.Serializable;

public class Calificadores_DTO
        implements Serializable {

    private int sk_calificador;

    public int getSk_calificador() {
        return sk_calificador;
    }

    public void setSk_calificador(int sk_calificador) {
        this.sk_calificador = sk_calificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSk_local() {
        return sk_local;
    }

    public void setSk_local(int sk_local) {
        this.sk_local = sk_local;
    }

    public int getSk_estado() {
        return sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }

    public int getSk_pondera() {
        return sk_pondera;
    }

    public void setSk_pondera(int sk_pondera) {
        this.sk_pondera = sk_pondera;
    }
    private String nombre;
    private int sk_local;
    private int sk_estado;
    private int sk_pondera;
}
