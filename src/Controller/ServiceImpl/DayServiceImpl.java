/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import Controller.Service.DayService;
import Controller.Service.GiaoVienService;
import Controller.Service.LopService;
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
    

    @Override
    public List<Day> findAll() {
        List<Day> listDay = new ArrayList<>();
        String sql_query ="select * from day \n"
                + "where xoa = 0;";
        try {
           
        } catch (Exception e) {
        }
        return listDay;
    }
}
