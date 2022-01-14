/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.MySQLConnect;
import Controller.Service.HocSinhService;
import Controller.Service.LopService;
import Model.HocSinh;
import Model.Lop;
import java.sql.ResultSet;
import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author leope
 */
public class HocSinhServiceImpl implements HocSinhService {

    private final LopService lopService = new LopServiceImpl();

    @Override
    public List<HocSinh> findAll() {
        String sql_query = "select * from hoc_sinh where xoa =0";
        List<HocSinh> listHS = new ArrayList<>();
        ResultSet rs = null;
        try {
            //    conn = MySQLConnect.getConnection();
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            rs = prst.executeQuery();
            while (rs.next()) {
                listHS.add(new HocSinh(rs.getString("ma_hoc_sinh"),
                        rs.getString("ten_hoc_sinh"),
                        rs.getString("gioi_tinh"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("dia_chi"),
                        rs.getString("ten_phu_huynh"),
                        rs.getString("so_dien_thoai"),
                        rs.getBoolean("xoa")));
            }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHS;
    }

    @Override
    public Optional<HocSinh> findByMaHocSinh(String maHocSinh) {
        List<HocSinh> listHocSinh = this.findAll();
        Optional<HocSinh> hocSinhOptional = listHocSinh.stream().filter(hocSinh -> hocSinh.getMaHocSinh().equals(maHocSinh)).findAny();
        return hocSinhOptional.isPresent() ? hocSinhOptional : Optional.empty();
    }

    @Override
    public List<HocSinh> findByTenHocSinh(String tenHocSinh) {
        String sql_query = "select * from hoc_sinh where hoc_sinh.ten_hoc_sinh like concat('%',?,'%') and hoc_sinh.xoa=0";
        List<HocSinh> listHocSinh = null;
        try {
            //  conn =MySQLConnect.getConnection();
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                listHocSinh.add(new HocSinh(rs.getString("ma_hoc_sinh"),
                        rs.getString("ten_hoc_sinh"),
                        rs.getString("gioi_tinh"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("dia_chi"),
                        rs.getString("ten_phu_huynh"),
                        rs.getString("so_dien_thoai"),
                        rs.getBoolean("xoa")));
            }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHocSinh;

    }

    @Override
    public List<HocSinh> findByLop(Lop lop) {
        List<HocSinh> listHocSinh = new ArrayList<>();
        Optional<Lop> lopOptional = lopService.findLop(lop.getTenLop(), lop.getNamHoc());
        if (!lopOptional.isPresent()) {
            return listHocSinh;
        }
        String sql_query = " select hoc.ma_hoc_sinh from hoc"
                + " where hoc.ten_lop =? and hoc.nien_khoa =? and hoc.nam_hoc =? and xoa=0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, lop.getTenLop());
            prst.setString(2, lop.getNamHoc());
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                listHocSinh.add(findByMaHocSinh(rs.getString("ma_hoc_sinh")).get());
            }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listHocSinh;
    }

    @Override
    public boolean addHocSinh(HocSinh hocSinh) {
        Optional<HocSinh> hocSinhOptional = this.findByMaHocSinh(hocSinh.getMaHocSinh());
        if (hocSinhOptional.isPresent()) {
            return false;
        }
        String sql_query = "insert into hoc_sinh values (?,?,?,?,?,?,?,0)";
        boolean checkUpdate = false;
        try {

            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, hocSinh.getMaHocSinh());
            prst.setString(2, hocSinh.getTenHocSinh());
            prst.setString(3, hocSinh.getGioiTinh());
            prst.setDate(4, (Date) hocSinh.getNgaySinh());
            prst.setString(5, hocSinh.getDiaChi());
            prst.setString(6, hocSinh.getTenPhuHuynh());
            prst.setString(7, hocSinh.getSoDienThoai());
            if (prst.execute()) {
                checkUpdate = true;
            }
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUpdate;
    }

    @Override
    public boolean updateHocSinh(HocSinh hocSinh) {
        boolean updateCheck = false;
        Optional<HocSinh> hocSinhOptional = findByMaHocSinh(hocSinh.getMaHocSinh());
        if (!hocSinhOptional.isPresent()) {
            return updateCheck;
        }
        String sql_query = "update hoc_sinh \n"
                + " set ma_hoc_sinh =?,"
                + " ten_hoc_sinh= ?, "
                + " gioi_tinh = ?, "
                + " ngay_sinh =?, "
                + " dia_chi =?,  "
                + " ten_phu_huynh =?, "
                + " so_dien_thoai =? \n "
                + " where ma_hoc_sinh =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, hocSinh.getMaHocSinh());
            prst.setString(2, hocSinh.getTenHocSinh());
            prst.setString(3, hocSinh.getGioiTinh());
            prst.setDate(4, (Date) hocSinh.getNgaySinh());
            prst.setString(5, hocSinh.getDiaChi());
            prst.setString(6, hocSinh.getTenPhuHuynh());
            prst.setString(7, hocSinh.getSoDienThoai());
            prst.setString(8, hocSinh.getMaHocSinh());
            updateCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCheck;
    }

    @Override
    public boolean deleteHocSinh(HocSinh hocSinh) {
        boolean deleteCheck = false;
        Optional<HocSinh> hocSinhOptional = findByMaHocSinh(hocSinh.getMaHocSinh());
        if (!hocSinhOptional.isPresent()) {
            return deleteCheck;
        }
        String sql_query = "update hoc_sinh \n"
                + "set xoa = 1 \n"
                + "where ma_hoc_sinh  =? ";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, hocSinh.getMaHocSinh());
            deleteCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteCheck;
    }

}
