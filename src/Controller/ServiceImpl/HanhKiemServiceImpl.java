/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.HanhKiemService;
import Controller.Service.HocSinhService;
import Model.HanhKiem;
import Model.HocSinh;
import java.util.Optional;
import java.sql.*;

/**
 *
 * @author leope
 */
public class HanhKiemServiceImpl  implements HanhKiemService{

    HocSinhService hocSinhService = new HocSinhServiceImpl();
    
    
    @Override
    public Optional<HanhKiem> findHanhKiem(String maHocSinh, String namHoc, String hocKy) {
            if(!hocSinhService.findByMaHocSinh(maHocSinh).isPresent()) return Optional.empty();
            String sql_query="select * from hanh_kiem \n"
                    + "where ma_hoc_sinh =? and nam_hoc =? and hoc_ky =? and xoa =0";
            try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,maHocSinh);
            prst.setString(2, namHoc);
            prst.setString(3,hocKy);
            ResultSet rs = prst.executeQuery();
            if(!rs.next()) return Optional.empty();
           HocSinh hocSinh = hocSinhService.findByMaHocSinh(maHocSinh).get();
           Optional<HanhKiem>hanhKiemOptional = Optional.of(new HanhKiem(hocSinh,rs.getString("nam_hoc"),
                        rs.getString("hoc_ky"),
                        rs.getString("loi_vi_pham"),
                        rs.getInt("nghi_co_phep"),
                        rs.getInt("nghi_khong_phep"),
                        false));
            prst.close();
            rs.close();
            return hanhKiemOptional;          
        } catch (Exception e) {
            e.printStackTrace();
        }
            
            return Optional.empty();
       
    }

    @Override
    public boolean addHanhKiem(HanhKiem hanhKiem) {
        boolean addCheck = false;
        Optional<HocSinh> hocSinhOptional = hocSinhService.findByMaHocSinh(hanhKiem.getHocSinh().getMaHocSinh());
        Optional<HanhKiem> hanhKiemOptional = findHanhKiem(hanhKiem.getHocSinh().getMaHocSinh(), hanhKiem.getNamHoc(),hanhKiem.getHocKy());
        if(!hocSinhOptional.isPresent() || hanhKiemOptional.isPresent()) return addCheck;
        String sql_query =" insert into hanh_kiem (ma_hoc_sinh,nam_hoc, hoc_ky,loi_vi_pham,nghi_co_phep,nghi_khong_phep, xoa) \n"
                + " values (?,?,?,?,?,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,hocSinhOptional.get().getMaHocSinh());
            prst.setString(2,hanhKiem.getNamHoc());
            prst.setString(3,hanhKiem.getHocKy()); 
            prst.setString(4, hanhKiem.getLoiViPham());
            prst.setInt(5, hanhKiem.getNghiCoPhep());
            prst.setInt(6, hanhKiem.getNghiKhongPhep());
            addCheck =prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCheck;
    }

    @Override
    public boolean updateHanhKiem(HanhKiem hanhKiem) {
        boolean updateCheck = false;
        Optional<HanhKiem> hanhKiemOptional = findHanhKiem(hanhKiem.getHocSinh().getMaHocSinh(),hanhKiem.getNamHoc(),hanhKiem.getHocKy());
        if(!hanhKiemOptional.isPresent()) return updateCheck;
        String sql_query = "update hanh_kiem \n"
                + "set loi_vi_pham =?, nghi_co_phep =?, nghi_khong_phep=? \n"
                + "where ma_hoc_sinh =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,hanhKiem.getLoiViPham());
            prst.setInt(2, hanhKiem.getNghiCoPhep());
            prst.setInt(3, hanhKiem.getNghiKhongPhep());
            prst.setString(4, hanhKiem.getHocSinh().getMaHocSinh());
            updateCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCheck;
        
    }
    
    
    
    
    
    
    
    
    
}
