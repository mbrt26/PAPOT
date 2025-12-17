package com.solucionesweb.lasayudas;

//
import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class JDBCSoftlandAccess   implements Serializable  {

  // jndiName es el nombre del JNDI name para del Data source a suar en esta conexion.
  private String jndiName = "SoftlandAccess";

  // Crea un nuevo JDBCAcces
  public JDBCSoftlandAccess(String jndiName) {
    this.jndiName = jndiName;
  }

  // Consigue una conexion JNDI
  public Connection getConnection() throws NamingException, SQLException {
    Context initCtx = null;
    try
    {
      // Obtiene el Contexto Inicial JNDI
      initCtx = new InitialContext();

      // Ejecuta el lookup para obtener en Resource Manager Connection Factory
      DataSource ds = (javax.sql.DataSource)
        initCtx.lookup("java:comp/env/jdbc/" + jndiName);

      // Invoca factory para obtener una connection.
      return ds.getConnection();

    }

    finally {
      // Don't forget to close the naming context
      if (initCtx != null) {
        initCtx.close();
      }
    }
  }

  // Siempre limpia las conexiones, aun cuando se presente una SQL exception
  public void cleanup(Connection databaseConnection,
               Statement statement1,
               Statement statement2) {
    try
    {
      // Cierra la statement1
      if (statement1 != null) {
        statement1.close();
      }
      // Cierra la statement2
      if (statement2 != null) {
        statement2.close();
      }
      if (databaseConnection != null) {
        databaseConnection.close();
      }
    }

    catch (SQLException sqle)
    {
		System.out.println("SQLException :" + sqle);
	}
	catch (Exception e)
	{
		System.out.println("Exception :" + e);
	}

    finally {
      // Se asegura que se cierre la conexion aun cuando
      // pueda haberse presentado un fallo en el cierre de una statament
    }
  }
}