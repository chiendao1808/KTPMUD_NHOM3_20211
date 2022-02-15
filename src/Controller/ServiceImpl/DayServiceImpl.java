/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.DayService;
import Controller.Service.GiaoVienService;
import Controller.Service.LopService;
import Controller.Service.MonHocService;
import Model.Day;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author leope
 */
public class DayServiceImpl  implements DayService{
    
    private GiaoVienService giaoVienService = new GiaoVienServiceImpl();
    private LopService lopService = new LopServiceImpl();
    private MonHocService monHocService = new MonHocServiceImpl();
    

    @Override
    public List<Day> findAll() {
        List<Day> listDay = new ArrayList<>();
        String sql_query ="select * from day \n"
                + "where xoa = 0";
        try {
            PreparedStatement prst =MainApp.getConnection().prepareCall(sql_query);
            ResultSet rs =prst.executeQuery();
            while(rs.next())
                {
                    listDay.add(new Day (giaoVienService.findByMaGiaoVien(rs.getString("ma_giao_vien")).get(),
                                                          monHocService.findByMaMonHoc(rs.getString("ma_mon_hoc")).get(),
                                                          lopService.findLop(rs.getString("ten_lop"),rs.getString("nam_hoc")).get(),
                                                          false));
                }
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDay;
    }
}
