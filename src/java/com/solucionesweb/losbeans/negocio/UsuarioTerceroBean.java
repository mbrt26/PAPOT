package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioTercero;

// Importa los paquetes de java
import java.io.*;
import java.io.IOException.*;

public class UsuarioTerceroBean extends FachadaUsuarioTercero
                                                  implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public UsuarioTerceroBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

}
