/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.inventario.consulta;

import co.linxsi.modelo.inventario.carga_inventario.Carga_Inventario_DTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ControlLeerExcel {

    public List<Carga_Inventario_DTO> leerExcel(String filePath) {

        FileInputStream file = null;
        List<Carga_Inventario_DTO> lista = new ArrayList();
        try {
   
            file = new FileInputStream(new File(filePath));
            // leer archivo excel
            HSSFWorkbook worbook = new HSSFWorkbook(file);
            //obtener la hoja que se va leer
            HSSFSheet sheet = worbook.getSheetAt(0);
            //obtener todas las filas de la hoja excel
            int rowCount = sheet.getPhysicalNumberOfRows();
            // se recorre cada fila hasta el final
            for (int i = 0; i < rowCount; i++) {
                HSSFRow row = sheet.getRow(i);
                //se obtiene las celdas por fila
                Iterator<Cell> cellIterator = row.cellIterator();

                //se recorre cada celda
                int j = 0;
                Carga_Inventario_DTO cdto = new Carga_Inventario_DTO();
                while (cellIterator.hasNext()) {
                    // se obtiene la celda en específico y se la imprime
                    HSSFCell cell = (HSSFCell) cellIterator.next();

                    if (i > 0) //para omitir el encabezado
                    {
                        switch (j) {
                            case 0:
                                cdto.setSk_bodega((int) cell.getNumericCellValue());
                                break;
                            case 1:
                                cdto.setNombreBodega(cell.getStringCellValue());
                                break;
                            case 2:
                                cdto.setSk_plu((int) cell.getNumericCellValue());
                                break;
                            case 3:
                                cdto.setNombrePLU(cell.getStringCellValue());
                                break;
                            case 4:
                                cdto.setExistencia_teorica(cell.getNumericCellValue());
                                break;
                            case 7:
                                cdto.setExistencia(cell.getNumericCellValue());
                                lista.add(cdto);
                                break;
                        }

                        j++;
                    }
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + " | ");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + " | ");
                            break;
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlLeerExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlLeerExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return lista;
        }

    }    }
