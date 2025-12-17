package com.solucionesweb.losservlets;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import java.util.*;

import com.solucionesweb.lasayudas.ProcesoImportaContable;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;

// Importa la clase que contiene el LocalIpBean
import com.solucionesweb.losbeans.negocio.LocalIpBean;
import java.util.Iterator;
import java.util.Vector;

public class GralServletContable extends TimerTask implements ServletContextListener {


    //
    ProcesoImportaContable procesoImportaContable
                           = new ProcesoImportaContable();

    //
    private Timer timer;

    // PLASTICAUCA
    int xIdLocalBase           = 12;

    // CEDIPLAST
    //int xIdLocalBase             = 51;

    //
    public void contextInitialized(ServletContextEvent evt) {

        // Iniciamos el timer
        int  numeroMinutos         = 1;

        //
        timer                      = new Timer();

        //
        //timer.schedule(this, 0, numeroMinutos * 60 * 1000 );  // Ejemplo: Cada numeroMinutos
        timer.schedule(this, 0, numeroMinutos * 60 * 100 );  // Ejemplo: Cada numeroMinutos

    }

    public void contextDestroyed(ServletContextEvent evt) {
        timer.cancel();
    }

    public void run() {

       try {

{ //----------------------------------------------------------------------- inicio thread ---
                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                //
                LocalIpBean localIpBean = new LocalIpBean();

                //
                localIpBean.setIdLocalPadre(xIdLocalBase);

                //
                Vector vecLocalBean     = localIpBean.listaAllHijo();

                //---listaAllHijo
                Iterator iteratorBean  = vecLocalBean.iterator();

                //---
                while (iteratorBean.hasNext()) {

                      //
                      fachadaLocalIp = (FachadaLocalIp)iteratorBean.next();

                        //
                      String xHostName = fachadaLocalIp.getIp();
                      String xIdPuertoLocal = ":"
                        + fachadaLocalIp.getPuertoHttp();
                      //

                      
                      ProcesoImportaContable procesoImportaContable
                                          = new ProcesoImportaContable();

                       //
                       procesoImportaContable.guarda(fachadaLocalIp.getIdLocal()); //timer 1,,12,15,18,1
 

                       //
                       procesoImportaContable.start();

                }
            }


       //
       procesoImportaContable.guarda(xIdLocalBase); //timer 1,,12,15,18,1
       

       } catch (Exception exc)  {
       	 exc.printStackTrace();
       }
    }
}