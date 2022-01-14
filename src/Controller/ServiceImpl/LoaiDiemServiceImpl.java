/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.LoaiDiemService;
import Model.LoaiDiem;
import java.util.Optional;
import java.sql.*;

/**
 *
 * @author leope
 */
public class LoaiDiemServiceImpl implements LoaiDiemService {

    @Override
    public Optional<LoaiDiem> findLoaiDiem(String maLoaiDiem) {
        String sql_query = "select * from loai_diem\n"
                + "where ma_loai_diem =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, maLoaiDiem);
            ResultSet rs = prst.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            Optional<LoaiDiem> loaiDiemOptional = Optional.ofNullable(new LoaiDiem(rs.getString("ma_loai_diem"), rs.getString("ten_loai_diem"), rs.getInt("trong_so"), false));
            prst.close();
            rs.close();
            return loaiDiemOptional;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean addLoaiDiem(LoaiDiem loaiDiem) {
       boolean addCheck = false;
       Optional<LoaiDiem> loaiDiemOptional = findLoaiDiem(loaiDiem.getMaLoaiDiem());
       if(loaiDiemOptional.isPresent()) return addCheck;
       String sql_query ="insert into loai_diem (ma_loai_diem, ten_loai_diem, trong_so, xoa) \n "
               + "values (?,?,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, loaiDiem.getMaLoaiDiem());
            prst.setString(2, loaiDiem.getTenLoaiDiem());
            prst.setInt(3, loaiDiem.getTrongSo());
            addCheck = prst.execute();
            prst.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       return addCheck;
    }
    
    
    
    

}
