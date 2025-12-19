package com.solucionesweb.lasayudas;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Imprime
{
   public static void main (String[] args) throws PrinterException
   {
      // Creamos un objeto de impresión.
      PrinterJob job = PrinterJob.getPrinterJob();

      // Hacemos imprimible el objeto ObjetoAImprimir
      job.setPrintable(new ObjetoAImprimir());

      //Pondrá algo tipo Información job: sun.awt.windows.WPrinterJob@4a5ab2
      System.out.println("Información job: " + job.toString());

      //Abre el cuadro de diálogo de la impresora, si queremos que imprima
      //directamente sin cuadro de diálogo quitamos el if...
      if (job.printDialog())
      {
        //Imprime, llama a la función print del objeto a imprimir
        //en nuestro caso el Objeto ObjetoAImprimir
         try { job.print(); }
         catch (PrinterException e) { System.out.println("Error de impresión: " + e); }
      }
   }
}
