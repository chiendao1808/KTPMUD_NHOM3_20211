/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Controller.Service.DiemService;
import Controller.Service.GiaoVienService;
import Controller.Service.HanhKiemService;
import Controller.Service.HocService;
import Controller.Service.HocSinhService;
import Controller.Service.LoaiDiemService;
import Controller.Service.LopService;
import Controller.Service.MonHocService;
import Controller.ServiceImpl.DiemServiceImpl;
import Controller.ServiceImpl.GiaoVienServiceImpl;
import Controller.ServiceImpl.HanhKiemServiceImpl;
import Controller.ServiceImpl.HocServiceImpl;
import Controller.ServiceImpl.HocSinhServiceImpl;
import Controller.ServiceImpl.LoaiDiemServiceImpl;
import Controller.ServiceImpl.LopServiceImpl;
import Controller.ServiceImpl.MonHocServiceImpl;
import Controller.Validation.DateAndTimeUtils;
import Model.Diem;
import Model.GiaoVien;
import Model.HanhKiem;
import Model.HocSinh;
import Model.LoaiDiem;
import Model.Lop;
import Model.MonHoc;
import java.util.Date;
import javax.swing.text.DateFormatter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author leope
 */

public class TestApp {
    
    public static void main(String[] args) throws Exception {
    

//        HocSinhService hocSinhService = new HocSinhServiceImpl();
//        hocSinhService.findByTenHocSinh("Chiến").stream().forEach(System.out::println);
            DiemService diemService = new DiemServiceImpl();
//      List<Diem> listDiemMon = diemService.findByMonHoc("HS000001","TH7", "2020-2021", "I");
//        System.out.println(diemService.processDiemMon(listDiemMon));      
        System.out.println(diemService.processDiemTBHocKy("HS000001","2020-2021","I"));
       String hocluc = diemService.processDanhGiaHocLuc("HS000001","2020-2021","I");
        System.out.println(hocluc);
        //asdf
        
          
            
            
        
            
           
            
      
      
            
               
   
      
    
        
        
        
        
  
      
      
     
      
    }
}
