/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.linxsi.modelo.touch.turno;

/**
 *
 * @author Edgar García
 */
public class Turno_DTO {
    private int id;
    private String nombre;
    private String estado;

    public Turno_DTO(int id, String name, String state) {
        this.id = id;
        this.nombre = name;
        this.estado = state;
    }

    Turno_DTO() {
       
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nombre;
    }

    public String getState() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public void setState(String state) {
        this.estado = state;
    }
}
