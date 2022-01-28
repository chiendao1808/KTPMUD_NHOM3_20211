/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.HocService;
import Controller.Service.HocSinhService;
import Controller.Service.LopService;
import Model.Hoc;
import Model.HocSinh;
import Model.Lop;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

/**
 *
 * @author leope
 */
public class HocServiceImpl implements HocService {

    private LopService lopService = new LopServiceImpl();
    private HocSinhService hocSinhService = new HocSinhServiceImpl();

    @Override
    public List<Hoc> findByLop(String tenLop, String namHoc) {
        List<Hoc> listHocSinhLop = new ArrayList<>();
        Optional<Lop> lopOptional = lopService.findLop(tenLop, namHoc);
        if (!lopOptional.isPresent()) {
            return List.of();
        }
        String sql_query = "select * from hoc \n"
                + "where ten_lop =? and nam_hoc =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenLop);
            prst.setString(2, namHoc);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                listHocSinhLop.add(new Hoc(hocSinhService.findByMaHocSinh(rs.getString("ma_hoc_sinh")).get(), lopOptional.get(), false));
            }
            prst.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHocSinhLop;
    }

    @Override
    public Optional<Hoc> findHoc(String tenLop, String namHoc, String maHocSinh) {
        if (!lopService.findLop(tenLop, namHoc).isPresent() || !hocSinhService.findByMaHocSinh(maHocSinh).isPresent()) {
            return Optional.empty();
        }
        String sql_query = "select * from hoc \n"
                + "where ten_lop =? and nam_hoc =?  and ma_hoc_sinh =?  and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenLop);
            prst.setString(2, namHoc);
            prst.setString(3, maHocSinh);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                Optional<Hoc> hocOptional = Optional.ofNullable(new Hoc(hocSinhService.findByMaHocSinh(maHocSinh).get(),
                                                                                                                            lopService.findLop(tenLop, namHoc).get(),
                                                                                                                            false));
                prst.close();
                rs.close();
                return hocOptional;
            }
            prst.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean addHoc(String tenLop, String namHoc, String maHocSinh) {
        boolean addCheck = false;
        if (findHoc(tenLop, namHoc, maHocSinh).isPresent()) {
            return addCheck;
        }
        String sql_query = " insert into hoc (ten_lop,nam_hoc,ma_hoc_sinh,xoa) \n"
                + "values (?,?,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, tenLop);
            prst.setString(2, namHoc);
            prst.setString(3, maHocSinh);
            addCheck = prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCheck;
    }

    @Override
    public boolean deleteHoc(Hoc hoc) {
        boolean deleteCheck = false;
        if(!findHoc(hoc.getLop().getTenLop(), hoc.getLop().getNamHoc(),hoc.getHocSinh().getMaHocSinh()).isPresent()) return deleteCheck;
        String sql_query ="update hoc \n"
                + "set xoa= 1 \n"
                +" where ten_lop =? and nam_hoc =? and ma_hoc_sinh =?";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1, hoc.getLop().getTenLop());
            prst.setString(2, hoc.getLop().getNamHoc());
            prst.setString(3, hoc.getHocSinh().getMaHocSinh());
            deleteCheck = prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteCheck;
    }
}
