/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.MonHocService;
import Model.MonHoc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Optional;

/**
 *
 * @author leope
 */
public class MonHocServiceImpl implements MonHocService{

    @Override
    public List<MonHoc> findAll() {
    List<MonHoc> listMonHocs = new ArrayList<>();
    String sql_query = "select * from mon_hoc \n"
            + "where xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            ResultSet rs = prst .executeQuery();
            while(rs.next())
                {
                    listMonHocs.add(new MonHoc(rs.getString("ma_mon_hoc"),rs.getString("ten_mon"),rs.getInt("trong_so"),rs.getInt("khoi"), false));
                }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return listMonHocs;
    }

    @Override
    public List<MonHoc> findByTenMonHoc(String tenMonHoc) {
      List<MonHoc> listMonHocs = new ArrayList<>();
      String sql_query = "select * from mon_hoc \n"
              + "where ten_mon like concat('%',?,'%') and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenMonHoc);
            ResultSet rs = prst .executeQuery();
            while(rs.next())
                {
                   listMonHocs.add(new MonHoc(rs.getString("ma_mon_hoc"),rs.getString("ten_mon"),rs.getInt("trong_so"),rs.getInt("khoi"), false));
                }
            prst.close();
           rs.close();           
        } catch (Exception e) {
        }
        return listMonHocs;
    }

    @Override
    public Optional<MonHoc> findByMaMonHoc(String maMonHoc) {
       List<MonHoc> listMonHoc = findAll();
       Optional<MonHoc> monHocOptional = listMonHoc.stream().filter(mon -> mon.getMaMonHoc().equals(maMonHoc)).findAny();
       return monHocOptional;
    }

    @Override
    public boolean addMonHoc(MonHoc monHoc) {
        boolean addCheck = false;
        if(findByMaMonHoc(monHoc.getMaMonHoc()).isPresent()) return addCheck;
        String sql_query ="insert into mon_hoc (ma_mon_hoc,ten_mon, trong_so, khoi, xoa)\n"
                + "values (?,?,?,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,monHoc.getMaMonHoc());
            prst.setString(2, monHoc.getTenMon());
            prst.setInt(3, monHoc.getTrongSo());
            prst.setInt(4, monHoc.getKhoi());
            addCheck =prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCheck;
    }
    
    
    
    
    
    
    
    
    
    
}
