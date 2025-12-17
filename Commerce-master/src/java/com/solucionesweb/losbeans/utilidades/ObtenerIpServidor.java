/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solucionesweb.losbeans.utilidades;

import java.net.InetAddress;

/**
 *
 * @author mcommerce1
 */
public class ObtenerIpServidor {

    private String ipServidor;

    public String getIpServidor() {
        try {

            InetAddress servidor = InetAddress.getLocalHost();

            this.ipServidor = servidor.getHostAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipServidor;
    }
}
