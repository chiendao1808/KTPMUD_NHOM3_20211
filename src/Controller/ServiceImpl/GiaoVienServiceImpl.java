/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.MySQLConnect;
import Controller.Service.DayService;
import Controller.Service.GiaoVienService;
import Controller.Validation.DateAndTimeUtils;
import Model.Day;
import Model.GiaoVien;
import Model.Lop;
import com.mysql.cj.MysqlConnection;
import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author leope
 */
public class GiaoVienServiceImpl implements GiaoVienService {

    //injection
   
    
    @Override
    public List<GiaoVien> findAllGiaoVien() {
        String sql_query = "select * from giao_vien where giao_vien.xoa =0";
        List<GiaoVien> listGiaoVien = new ArrayList<>();
        ResultSet rs = null;
        try {
            //   conn = MySQLConnect.getConnection();
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            rs = prst.executeQuery();
            while (rs.next()) {
                listGiaoVien.add(new GiaoVien(rs.getString("ma_giao_vien"),
                        rs.getString("ten_giao_vien"),
                        rs.getString("gioi_tinh"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("chuc_vu"),
                        rs.getBoolean("xoa")));
            }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGiaoVien;

    }

    @Override
    public Optional<GiaoVien> findByMaGiaoVien(String maGiaoVien) {
        List<GiaoVien> listGiaoVien = this.findAllGiaoVien();
        Optional<GiaoVien> giaoVienOptional = listGiaoVien.stream().filter(giaoVien -> giaoVien.getMaGiaoVien().equals(maGiaoVien)).findAny();
        return giaoVienOptional.isPresent() ? giaoVienOptional : Optional.empty();
    }

    @Override
    public List<GiaoVien> findByTenGiaoVien(String tenGiaoVien) {
        String sql_query = "select * from giao_vien where giao_vien.ten_giao_vien like concat('%',?,'%') and giao_vien.xoa=0";
        List<GiaoVien> listGiaoVien = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenGiaoVien);
            rs = prst.executeQuery();
            while (rs.next()) {
                listGiaoVien.add(new GiaoVien(rs.getString("ma_giao_vien"),
                        rs.getString("ten_giao_vien"),
                        rs.getString("gioi_tinh"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("chuc_vu"),
                        rs.getBoolean("xoa")));
            }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGiaoVien;
    }

    @Override
    public Optional<GiaoVien> findByTenDangNhap(String tenDangNhap) {
        String sql_query = "select * from giao_vien "
                + "where giao_vien.ten_dang_nhap =? and giao_vien.xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenDangNhap);
            ResultSet rs = prst.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            Optional<GiaoVien> giaoOptional = Optional.of(new GiaoVien(rs.getString("ma_giao_vien"),
                    rs.getString("ten_giao_vien"),
                    rs.getString("gioi_tinh"),
                    rs.getDate("ngay_sinh"),
                    rs.getString("so_dien_thoai"),
                    rs.getString("dia_chi"),
                    rs.getString("chuc_vu"),
                    rs.getBoolean("xoa")));
            prst.close();
            rs.close();
            return giaoOptional;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    
     @Override
    public Optional<Lop> findAllLopChuNhiem(String maGiaoVien, String namHoc) {
            Optional<GiaoVien> giaoVienOptional = findByMaGiaoVien(maGiaoVien);
            Optional<Lop> lopOptional = Optional.empty();
            if(!giaoVienOptional.isPresent()) return Optional.empty();
            String sql_query ="select ten_lop , nam_hoc, si_so from lop \n"
                    + "where ma_gv_chu_nhiem = ? and nam_hoc =? and xoa =0";
            try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, maGiaoVien);
            prst.setString(2, namHoc);
            ResultSet rs = prst.executeQuery();
            if(rs.next()) lopOptional= Optional.ofNullable(new Lop(rs.getString("ten_lop"),rs.getString("nam_hoc"),rs.getInt("si_so"),findByMaGiaoVien(maGiaoVien).get(), false));
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return lopOptional;
    }

    @Override
    public boolean addGiaoVien(GiaoVien giaoVien, String tenDangNhap, String matKhau) {
        boolean addCheck = false;
        Optional<GiaoVien> giaoVienOptional = findByMaGiaoVien(giaoVien.getMaGiaoVien());
        if (giaoVienOptional.isPresent()) {
            return addCheck;
        }
        String sql_query = " insert into giao_vien(ma_giao_vien, ten_giao_vien, gioi_tinh, ngay_sinh, so_dien_thoai,dia_chi,chuc_vu,ten_dang_nhap,mat_khau,xoa) \n"
                + "values (?,?,?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, giaoVien.getMaGiaoVien()); // set ma_giao_vien
            prst.setString(2, giaoVien.getTenGiaoVien()); // set ten_giao_vien
            prst.setString(3, giaoVien.getGioiTinh()); // set gioi_tinh           
            prst.setDate(4, (Date) giaoVien.getNgaySinh()); // set  ngay_sinh
            prst.setString(5, giaoVien.getSoDienThoai()); // set so_dien_thoai
            prst.setString(6, giaoVien.getDiaChi());// set dia_chi
            prst.setString(7, giaoVien.getChucVu()); // set chuc_vu
            prst.setString(8, tenDangNhap); // set ten_dang_nhap
            prst.setString(9, matKhau); // set mat_khau
            addCheck = prst.execute();
            prst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return addCheck;

    }

    @Override
    public boolean updateGiaoVien(GiaoVien giaoVien) {
        Optional giaoVienOptional = findByMaGiaoVien(giaoVien.getMaGiaoVien());
        boolean updateCheck = false;
        if (!giaoVienOptional.isPresent()) {
            return updateCheck;
        }
        String sql_query = "update  giao_vien \n"
                + "set ma_giao_vien =?, ten_giao_vien =?, gioi_tinh=?, ngay_sinh= ?, so_dien_thoai=?, dia_chi =? \n"
                + "where ma_giao_vien =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, giaoVien.getMaGiaoVien());
            prst.setString(2, giaoVien.getTenGiaoVien());
            prst.setString(3, giaoVien.getGioiTinh());
            prst.setDate(4, (Date) giaoVien.getNgaySinh());
            prst.setString(5, giaoVien.getSoDienThoai());
            prst.setString(6, giaoVien.getDiaChi());
            prst.setString(7, giaoVien.getMaGiaoVien());
            updateCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCheck;
    }

    @Override
    public List<Lop> findAllLopDay(String maGiaoVien, String namHoc) {    
       DayService dayService =new DayServiceImpl();
       List<Day> listDay  = dayService.findAll();
       List<Lop> lopDay = new ArrayList<>();
        for(Day day: listDay)
            {
                if(day.getGiaoVien().getMaGiaoVien().equals(maGiaoVien) && day.getLop().getNamHoc().equals(namHoc))
                lopDay.add(day.getLop());
            }
     return lopDay;
        }

    @Override
    public List<String> getTaiKhoanGiaoVien(String maGiaoVien) {
       List<String> taiKhoan = new ArrayList<>();
       if(!findByMaGiaoVien(maGiaoVien).isPresent()) return taiKhoan;
       String sql_query ="select ma_giao_vien,ten_dang_nhap, mat_khau from giao_vien \n"
               + "where ma_giao_vien =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, maGiaoVien);
            ResultSet rs = prst.executeQuery();
            if(rs.next()) {
                taiKhoan.add(rs.getString("ten_dang_nhap"));
                taiKhoan.add(rs.getString("mat_khau")); 
            }     
        } catch (Exception e) {
        }
        return taiKhoan;
    }
  
    @Override
    public boolean updateMatKhauGiaoVien(String maGiaoVien, String matKhau) {
        boolean setCheck =true;
        if(findByMaGiaoVien(maGiaoVien).isEmpty()) return setCheck;
        String sql_query ="update giao_vien \n"
                + "set mat_khau=? \n"
                + "where ma_giao_vien =? and xoa=0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, matKhau);
            prst.setString(2, maGiaoVien);
            setCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
            setCheck=false;
        }       
        return setCheck;
    }
    
    @Override
    public boolean deleteGiaoVien(GiaoVien giaoVien) {
        boolean deleteCheck = false;
        Optional<GiaoVien> giaoVienOptional = findByMaGiaoVien(giaoVien.getMaGiaoVien());
        if(!giaoVienOptional.isPresent()) return deleteCheck;
        String sql_query ="update giao_vien \n"
                + "set xoa =1 \n"
                + "where ma_giao_vien = ?";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, giaoVien.getMaGiaoVien());
            deleteCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteCheck;
    }
    
    

}
