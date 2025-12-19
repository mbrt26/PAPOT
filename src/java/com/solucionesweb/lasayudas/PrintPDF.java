package com.solucionesweb.lasayudas;

import javax.print.*;
import javax.print.attribute.*;
import java.io.*;

public class PrintPDF  {
public static void main ( String args [  ]  )   {
try {
String filename = "C:\\Comercial_NEW\\BDCotizacion\\001-9-000009.pdf";
PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet (  ) ;
DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
PrintService printService [  ]  = PrintServiceLookup.lookupPrintServices ( flavor, pras ) ;
//PrintService printService [  ]  = PrintServiceLookup.getPrintServices (  ) ;
//PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService (  ) ;
//PrintService service = ServiceUI.printDialog ( null, 200, 200, printService, defaultService, flavor, pras ) ;
// if ( printService.length != 0 )   {
DocPrintJob job = printService [ 0 ] .createPrintJob (  ) ;
FileInputStream fis = new FileInputStream ( filename ) ;
DocAttributeSet das = new HashDocAttributeSet (  ) ;
Doc doc = new SimpleDoc ( fis, flavor, das ) ;
job.print(doc, pras) ;
Thread.sleep ( 10000 ) ;
// }
System.exit ( 0 ) ;
}
catch ( Exception e )  {
System.out.println ( "Exception is " + e ) ;
}
}
}
