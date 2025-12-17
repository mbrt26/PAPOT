package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
 */
public class VtasControlAdmCopiaDeSeguridadManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasControlAdmCopiaDeSeguridadManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, FileNotFoundException, IOException {

try{

        String ejecuta = "cmd.exe /K C:\\proyectoWeb\\Commerce\\web\\WEB-INF\\scritpCopiaBD\\copiaBD.bat";


             Process aplicacion = Runtime.getRuntime().exec(ejecuta);

            /* InputStream is = aplicacion.getInputStream();

             BufferedReader br = new BufferedReader(new InputStreamReader(is));

             String aux = br.readLine();

             System.out.println(aux);*/
             
               
}
catch(FileNotFoundException ejecuta){

    System.out.println("Error......." );

    ejecuta.printStackTrace();

    

}
                  /*
            Validacion validacion = new Validacion();

            //
            validacion.reasignar("ERROR", "");

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";*/

                  
        return "/jsp/empty.htm";
    }


}
