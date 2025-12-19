package com.solucionesweb.losservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public interface GralManejadorRequest{

  /**
   * Metodo que promete que en alguna parte se implementará este metodo
   * a traves de la clausula implements
   */

  String generaPdf(HttpServletRequest request,
                       HttpServletResponse response)
         throws ServletException, IOException;
}