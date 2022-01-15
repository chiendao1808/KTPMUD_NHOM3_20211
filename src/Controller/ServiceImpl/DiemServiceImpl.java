/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ServiceImpl;

import App.MainApp;
import Controller.Service.DiemService;
import Controller.Service.HocSinhService;
import Controller.Service.LoaiDiemService;
import Controller.Service.MonHocService;
import Model.Diem;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Optional;

/**
 *
 * @author leope
 */
public class DiemServiceImpl implements DiemService{
    
    private HocSinhService hocSinhService = new HocSinhServiceImpl();
    private MonHocService monHocService = new MonHocServiceImpl();
    private LoaiDiemService loaiDiemService =  new LoaiDiemServiceImpl();

    @Override
    public List<Diem> findAllDiemHocSinh(String maHocSinh, String namHoc, char hocKy) { // tìm các điểm của học sinh trong một học kỳ
        if(!hocSinhService.findByMaHocSinh(maHocSinh).isPresent()) return List.of();
        List<Diem> listDiemHocSinh = new ArrayList<>();
        String sql_query ="select * from diem\n"
                + "where ma_hoc_sinh = ? and nam_hoc =?  and hoc_ky =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,maHocSinh);
            prst.setString(2, namHoc);
            prst.setString(3, String.valueOf(hocKy));
            ResultSet rs = prst.executeQuery();
            while(rs.next())
                {
                  listDiemHocSinh.add(new Diem (hocSinhService.findByMaHocSinh(maHocSinh).get(),
                                                                            monHocService.findByMaMonHoc(rs.getString("ma_mon_hoc")).get(),
                                                                            loaiDiemService.findLoaiDiem(rs.getString("ma_loai_diem")).get(),
                                                                            rs.getString("nam_hoc"),
                                                                            rs.getString("hoc_ky").charAt(0),
                                                                            rs.getFloat("diem_so"),
                                                                            false));
                }         
            prst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDiemHocSinh;
    }

    @Override
    public List<Diem> findByMonHoc(String maHocSinh,String maMonHoc, String namHoc, char hocKy) {
        List<Diem> listDiemTheoMonHoc = findAllDiemHocSinh(maHocSinh, namHoc, hocKy).stream().filter(diem-> diem.getMonHoc().getMaMonHoc().equals(maMonHoc)).toList();
        return listDiemTheoMonHoc;
    }

    @Override
    public Optional<Diem> findDiemChiTiet(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, char hocKy) {
      Optional<Diem> diemOptional = findAllDiemHocSinh(maHocSinh, namHoc, hocKy).stream().filter((t) -> {
          return (t.getMonHoc().getMaMonHoc().equals(maMonHoc)  && t.getLoaiDiem().getMaLoaiDiem().equals(maLoaiDiem)); //To change body of generated lambdas, choose Tools | Templates.
      }).findAny();
      return diemOptional;
    }

    
   
    @Override
    public boolean addDiem(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, char hocKy,float diemSo) {     
        if(!hocSinhService.findByMaHocSinh(maHocSinh).isPresent() ||  !monHocService.findByMaMonHoc(maMonHoc).isPresent() || !loaiDiemService.findLoaiDiem(maLoaiDiem).isPresent())
           return false;
        else if (findDiemChiTiet(maHocSinh, maMonHoc, maLoaiDiem, namHoc, hocKy).isPresent()) return false;
        boolean addCheck = false;
        String sql_query =" insert into diem (ma_hoc_sinh, ma_mon_hoc,ma_loai_diem, nam_hoc, hoc_ky ,diem_so,xoa)"
                + "values (?,?,?,?,?,?,0)";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,maHocSinh);
            prst.setString(2, maMonHoc);
            prst.setString(3, maLoaiDiem);
            prst.setString(4, namHoc);
            prst.setString(5, String.valueOf(hocKy));
            prst.setFloat(6, diemSo);
            addCheck =prst .execute();
            prst.close();            
        } catch (Exception e) {
            e.printStackTrace();
        }
       return addCheck;
    }

    @Override
    public boolean updateDiem(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, char hocKy, float diemSo) {
       boolean updateCheck = false;
      Optional<Diem> diemChiTiet = findDiemChiTiet(maHocSinh, maMonHoc, maLoaiDiem, namHoc, hocKy);
      if(!diemChiTiet.isPresent()) return updateCheck;
      String sql_query ="update diem \n"
              + "set diem_so =? \n"
              + "where ma_hoc_sinh =? and ma_mon_hoc =?  and ma_loai_diem =?  and nam_hoc =? and hoc_ky =?  and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setFloat(1, diemSo);
            prst.setString(2, maHocSinh);
            prst.setString(3, maMonHoc);
            prst.setString(4, maLoaiDiem);
            prst.setString(5, namHoc);
            prst.setString (6, String.valueOf(hocKy));
            updateCheck= prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
       return updateCheck;
    }
    
    
    
    
    
    

    
    
    
}
