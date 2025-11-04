package Utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class readExcel {
    private static String filePath;


    public readExcel(String filePath){

        this  .filePath=filePath;
    }


    public static String getValue(String key) {

        try {

            FileInputStream fis = new FileInputStream(filePath);
            Workbook wb = new XSSFWorkbook(fis);

            Sheet sh = wb.getSheet("Sheet1");
//            Row row = sh.getRow();


            for (Row row : sh) {
                if (row.getCell(0).toString().equalsIgnoreCase(key)) {
                    return row.getCell(1).toString();
                }


            }
            wb.close();
            fis.close();



        } catch (IOException e) {
            System.out.println("Error reading Excel: " + e.getMessage());

        }
return null;
    }

}


