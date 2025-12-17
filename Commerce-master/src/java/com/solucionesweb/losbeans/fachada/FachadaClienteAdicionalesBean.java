package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaClienteAdicionalesBean implements IConstantes {

    // Propiedades
    private String idCliente;
    private String email;

    // Metodos
    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public void setEmail( String email )
    {
        if (email == null) {
           this.email = STRINGVACIO;
        } else {
        this.email = email ;
        }
    }

    public String getEmail()
    {
        return email;
    }

    public FachadaClienteAdicionalesBean() { }

}