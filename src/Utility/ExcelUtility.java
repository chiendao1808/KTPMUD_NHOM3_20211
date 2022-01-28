/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Model.HocSinh;
import java.util.List;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

/**
 *
 * @author leope
 */
public class ExcelUtility {
    
    public static void exportDanhSachHocSinh(List<HocSinh> listHocSinh)
            {
                try {
                      String excelPath ="C:\\Users\\leope\\Documents\\NetBeansProjects\\Danh_sách_học_sinh.xlsx";
                       XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
                       XSSFSheet sheet = workbook.getSheet("danh_sach_hoc_sinh");
                       
                       
               
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
                
            }
    
}
