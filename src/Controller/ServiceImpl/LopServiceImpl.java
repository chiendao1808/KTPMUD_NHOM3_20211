/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.GiaoVienService;
import Controller.Service.LopService;
import Model.GiaoVien;
import Model.Lop;
import java.util.Optional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leope
 */
public class LopServiceImpl implements LopService {

    private GiaoVienService giaoVienService = new GiaoVienServiceImpl();

    @Override
    public List<Lop> findAll() {
       List<Lop> listLop = new ArrayList<>();
       String sql_query  = "select * from lop \n"
               + "where xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            ResultSet rs = prst.executeQuery();
            while(rs.next())
                {
                    listLop.add(new Lop (rs.getString("ten_lop"),
                                                         rs.getString("nam_hoc"),
                                                           rs.getInt("si_so"),
                                                         giaoVienService.findByMaGiaoVien(rs.getString("ma_gv_chu_nhiem")).get(),
                                                          false));
                }
            prst.close();
            rs.close();
        } catch (Exception e) {
        }
       
       return listLop;
    }
    
  
    @Override
    public Optional<Lop> findLop(String tenLop, String namHoc) {
        String sql_query = "select *  from lop \n"
                + " where ten_lop =? and nam_hoc =? and xoa= 0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenLop);
            prst.setString(2, namHoc);
            ResultSet rs = prst.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            GiaoVien giaoVienChuNhiem = giaoVienService.findByMaGiaoVien(rs.getString("ma_gv_chu_nhiem")).get();
            Optional<Lop> lopOptional = Optional.ofNullable(new Lop(rs.getString("ten_lop"),
                    rs.getString("nam_hoc"),
                    rs.getInt("si_so"),
                    giaoVienChuNhiem,
                    false));
            prst.close();
            rs.close();
            return lopOptional;
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean addLop(Lop lop) {
        boolean addCheck = false;
        Optional<GiaoVien> giaoVienOptional = giaoVienService.findByMaGiaoVien(lop.getGiaoVienChuNhiem().getMaGiaoVien());
        Optional<Lop> lopOptional = findLop(lop.getTenLop(), lop.getNamHoc());
        if (!(giaoVienOptional.isPresent() && !lopOptional.isPresent())) {
            return addCheck;
        }
        String sql_query = " insert into lop (ten_lop,nam_hoc,si_so,ma_gv_chu_nhiem,xoa)\n"
                + "values (?,?,0,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, lop.getTenLop());
            prst.setString(2, lop.getNamHoc());
            prst.setString(3, lop.getGiaoVienChuNhiem().getMaGiaoVien());
            addCheck = prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCheck;
    }

    @Override
    public boolean updateLop(Lop lop) {
        boolean updateCheck = true;
        if(!findLop(lop.getTenLop(),lop.getNamHoc()).isPresent()) return false;
        String sql_query ="update lop \n"
                + "set si_so =? ,ma_gv_chu_nhiem=? \n"
                + "where ten_lop =? and nam_hoc =?  and xoa =0";
        try {
            PreparedStatement prst =MainApp.getConnection().prepareCall(sql_query);
            prst.setInt(1,lop.getSiSo());
            prst.setString(2, lop.getGiaoVienChuNhiem().getMaGiaoVien());
            prst.setString(3, lop.getTenLop());
            prst.setString(4, lop.getNamHoc());
            updateCheck=prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCheck;
    }
    
    

}
