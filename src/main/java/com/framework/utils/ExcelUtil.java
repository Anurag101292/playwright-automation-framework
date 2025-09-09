package com.framework.utils;
import java.io.FileInputStream; import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtil {
    public static String readCell(String file,String sheet,int row,int col){
        try(FileInputStream fis=new FileInputStream(file);
            XSSFWorkbook wb=new XSSFWorkbook(fis))
        {
            return wb.getSheet(sheet).getRow(row).getCell(col).toString();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
