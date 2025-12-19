package co.linxsi.controlador.inventario.consulta;


import co.linxsi.modelo.inventario.carga_inventario.Carga_Inventario_DTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;




/**
 * @author Monillo007 :: Visita http://monillo007.blogspot.com ::
 */
public class ControlGeneraExcel {

    public static FileOutputStream runExcel(List<Carga_Inventario_DTO> lista) throws Exception {
        /*La ruta donde se creará el archivo*/
        String rutaArchivo ="C:\\Tecnnova Software\\Tecnnova\\web\\WEB-INF\\archivos\\bajada\\excel.xls";
        final String[] encabezado = new String[8];
        encabezado[0] = "Nº BODEGA";
        encabezado[1] = "BODEGA";
        encabezado[2] = "PLU";
        encabezado[3] = "DESCRIPCION";
        encabezado[4] = "CANTIDAD";
        encabezado[5] = "COSTO";
        encabezado[6] = "SUBTOTAL";
        encabezado[7] = "EXISTENCIA";

        /*Se crea el objeto de tipo File con la ruta del archivo*/
        File archivoXLS = new File(rutaArchivo);
        /*Si el archivo existe se elimina*/
        if (archivoXLS.exists()) {
            archivoXLS.delete();
        }
        /*Se crea el archivo*/
        archivoXLS.createNewFile();

        /*Se crea el libro de excel usando el objeto de tipo Workbook*/
        HSSFWorkbook libro =  new HSSFWorkbook();
        /*Se inicializa el flujo de datos con el archivo xls*/
        FileOutputStream archivo = new FileOutputStream(archivoXLS);

        /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
        HSSFSheet hoja = libro.createSheet("Hoja1");
//        HSSFCellStyle style = (HSSFCellStyle) libro.createCellStyle();
//        style.setFillForegroundColor(HSSFColor.BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
// 
		
        /*Hacemos un ciclo para inicializar los valores de 10 filas de celdas*/
        for (int f = 0; f < (lista.size()+1); f++) {
            /*La clase Row nos permitirá crear las filas*/
            HSSFRow fila = hoja.createRow(f);

            /*Cada fila tendrá 5 celdas de datos*/
            for (int c = 0; c < encabezado.length; c++) {
                /*Creamos la celda a partir de la fila actual*/
                HSSFCell celda = fila.createCell((short) c);

                /*Si la fila es la número 0, estableceremos los encabezados*/
                if (f == 0) {

                    celda.setCellValue(encabezado[c]);

//                    celda.setCellStyle((CellStyle) style);
                } else {
                    /*Si no es la primera fila establecemos un valor*/
                    if(c==0)celda.setCellValue(lista.get(f-1).getSk_bodega());
                    if(c==1)celda.setCellValue(lista.get(f-1).getNombreBodega());
                    if(c==2)celda.setCellValue(lista.get(f-1).getSk_plu());
                    if(c==3)celda.setCellValue(lista.get(f-1).getNombrePLU());
                    if(c==4)celda.setCellValue(lista.get(f-1).getExistencia());
                    if(c==5)celda.setCellValue(lista.get(f-1).getVr_costo());
                    if(c==6)celda.setCellValue(lista.get(f-1).getExistencia()*lista.get(f-1).getVr_costo());
                    if(c==7)celda.setCellValue("");
                }
            }
        }

        /*Escribimos en el libro*/
        libro.write(archivo);
        /*Cerramos el flujo de datos*/
        archivo.close();
        /*Y abrimos el archivo con la clase Desktop*/
//        Desktop.getDesktop().open(archivoXLS);
return archivo;
    }

}
