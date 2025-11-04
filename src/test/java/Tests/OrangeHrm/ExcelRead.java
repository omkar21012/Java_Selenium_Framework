package Tests.OrangeHrm;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelRead {


    public String getValue(String key) throws FileNotFoundException {

        try{
        FileInputStream FIS  =new FileInputStream("src/test/resources/TestData.xlsx");
        Workbook wb = new XSSFWorkbook(FIS);

        Sheet sheet = wb.getSheet("Sheet1");

        for(int i=0;i<=sheet.getLastRowNum();i++)
        {
            Row row = sheet.getRow(i);

            Cell keyCell = row.getCell(0); // column 0 = key
            Cell valueCell = row.getCell(1); // column 1 = value
            if (keyCell != null && keyCell.toString().equalsIgnoreCase(key)) {
                return (valueCell != null) ? valueCell.toString() : "";
            }


        }








        {
        }
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }}


