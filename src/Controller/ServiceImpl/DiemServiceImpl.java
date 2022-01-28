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
import Model.LoaiDiem;
import Model.MonHoc;
import java.awt.FileDialog;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.AbstractList;
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
    public List<Diem> findAllDiemHocSinh(String maHocSinh, String namHoc, String hocKy) { // tìm các điểm của học sinh trong một học kỳ
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
                                                                            rs.getString("hoc_ky"),
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
    public List<Diem> findAllDiemByMonHoc(String maHocSinh,String maMonHoc, String namHoc, String hocKy) {
        List<Diem> listDiemTheoMonHoc = findAllDiemHocSinh(maHocSinh, namHoc, hocKy).stream().filter(diem-> diem.getMonHoc().getMaMonHoc().equals(maMonHoc)).toList();
        return listDiemTheoMonHoc;
    }

    @Override
    public Optional<Diem> findDiemChiTiet(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy) {
      Optional<Diem> diemOptional = findAllDiemHocSinh(maHocSinh, namHoc, hocKy).stream().filter((t) -> {
          return (t.getMonHoc().getMaMonHoc().equals(maMonHoc)  && t.getLoaiDiem().getMaLoaiDiem().equals(maLoaiDiem)); //To change body of generated lambdas, choose Tools | Templates.
      }).findAny();
      return diemOptional;
    }

    
   
    @Override
    public boolean addDiem(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy,float diemSo) {     
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
            prst.setString(5, hocKy);
            prst.setFloat(6, diemSo);
            addCheck =prst .execute();
            prst.close();            
        } catch (Exception e) {
            e.printStackTrace();
        }
       return addCheck;
    }

    @Override
    public boolean updateDiem(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy, float diemSo) {
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
            prst.setString (6, hocKy);
            updateCheck= prst.execute();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
       return updateCheck;
    }

    
    // tìm tất cả môn học mà một học sinh học trong 1 học kỳ 
    @Override
    public List<MonHoc> findAllMonHocHocKy(String maHocSinh, String namHoc, String hocKy) { 
       List<MonHoc>listMonHocHocKy = new ArrayList<>();
       String sql_query ="select distinct ma_mon_hoc from diem \n"
               + "where ma_hoc_sinh =? and nam_hoc =? and hoc_ky =? and xoa =0";
        try {
            PreparedStatement prst = MainApp.getConnection().prepareCall(sql_query);
            prst.setString(1,maHocSinh);
            prst.setString(2, namHoc);
            prst.setString(3, hocKy);
            ResultSet rs = prst.executeQuery();
            while(rs.next())
                {
                    String maMonHoc =rs.getString("ma_mon_hoc");
                    listMonHocHocKy.add(monHocService.findByMaMonHoc(maMonHoc).get());
                }          
        } catch (Exception e) {
            e.printStackTrace();
        }
       return listMonHocHocKy;
    }
    
    
    
    
    // xử lý điểm trung bình của một môn học trong 1 học kỳ 
    @Override
    public float processDiemTBMon(List<Diem> listDiemMon) {
        float tong = 0f;
        int tongTrongSo =0 ;
        for(Diem diem : listDiemMon)
            {
                tong= tong+ diem.getDiemSo()* diem.getLoaiDiem().getTrongSo();
                tongTrongSo += diem.getLoaiDiem().getTrongSo();
            }           
      return (float) tong/tongTrongSo; 
    }

    
       // xử lý điểm trung bình của một học học kỳ 
    @Override
    public float processDiemTBHocKy(String maHocSinh, String namHoc, String hocKy) {
        List<MonHoc> listMonHocHocKy = findAllMonHocHocKy(maHocSinh, namHoc, hocKy);
        float diemTong =0f;
        int tongTrongSo=0;
        for(MonHoc monHoc : listMonHocHocKy)
            {
                float diemTBMon = processDiemTBMon(findAllDiemByMonHoc(maHocSinh, monHoc.getMaMonHoc(), namHoc, hocKy));
                diemTong +=diemTBMon*monHoc.getTrongSo();
                tongTrongSo+=monHoc.getTrongSo();
            }
        return (float)diemTong/tongTrongSo;
    }

    
    // lấy ra danh sách điểm trung bình các môn học trong học kỳ 
    @Override
    public List<Diem> getListDiemTB(String maHocSinh, String namHoc, String hocKy) {
        List<Diem> listDiemTB = new ArrayList<>();
        if(!hocSinhService.findByMaHocSinh(maHocSinh).isPresent()) return listDiemTB;
        List<MonHoc> listMonHoc = findAllMonHocHocKy(maHocSinh, namHoc, hocKy);
        for(MonHoc monHoc : listMonHoc)
            {
                listDiemTB.add(new Diem(hocSinhService.findByMaHocSinh(maHocSinh).get(), 
                                                             monHoc,
                                                              new LoaiDiem("TB","Điểm trung bình",0, true),
                                                             namHoc,
                                                             hocKy,
                                                             processDiemTBMon(findAllDiemByMonHoc(maHocSinh, monHoc.getMaMonHoc(), namHoc, hocKy))
                                                             ,true));
            }
        return listDiemTB;
    }

    
    
    // đánh giá học lực thông qua điểm trung bình trong một học kỳ
    @Override
    public String processDanhGiaHocLuc(String maHocSinh, String namHoc, String HocKy) {
        List<MonHoc> listMonHocHocKy = findAllMonHocHocKy(maHocSinh, namHoc, HocKy);
        // cắm cờ đánh dấu học lực
        boolean flagGioi = true;
        boolean flagKha = true;
        boolean flagTB = true;
        boolean flagYeu = true;
        float diemTongKet =processDiemTBHocKy(maHocSinh, namHoc, HocKy);
        List<Diem> listDiemTB = getListDiemTB(maHocSinh, namHoc, HocKy);
        float diemTBToan  = listDiemTB.stream().filter(toan -> toan.getMonHoc().getMaMonHoc().contains("TH")).findAny().get().getDiemSo();
        float diemTBVan = listDiemTB.stream().filter(toan -> toan.getMonHoc().getMaMonHoc().contains("NV")).findAny().get().getDiemSo();
        if(diemTongKet>=8.0f )
            {
              Optional<Diem> diem1Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<6.5f && diem.getDiemSo()>=5f)).findAny();
              Optional<Diem> diem2Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<5.0f && diem.getDiemSo()>=3.5f)).findAny();
              Optional<Diem> diem3Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<3.5f && diem.getDiemSo()>=2.0f)).findAny();
              Optional<Diem> diem4Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<2.0f)).findAny();
              if(diem1Optional.isPresent() || (diemTBToan<8.0f && diemTBVan <8.0f))
              flagGioi =false;       
              if(diem2Optional.isPresent() || ( diemTBToan <6.5f && diemTBVan<6.5f )) flagKha =false;
              if(diem3Optional.isPresent() || (diemTBToan<5.0f && diemTBVan<5.0f)) flagTB =false;
              if(diem4Optional.isPresent()) flagYeu =false;
            }
        else if(diemTongKet>=6.5f && diemTongKet<8.0f)
            {
                Optional<Diem> diem1Optional = listDiemTB.stream().filter(diem -> (diem.getDiemSo()<5.0f && diem.getDiemSo()>=3.5f)).findAny();
                Optional<Diem> diem2Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<3.5f && diem.getDiemSo()>=2.0f)).findAny();
                Optional<Diem> diem3Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<2.0f)).findAny();
                if(diem1Optional.isPresent() || (diemTBToan <6.5f && diemTBVan <6.5f))
                    flagKha =false;  
                if(diem2Optional.isPresent() || (diemTBToan<5.0f && diemTBVan<5.0f)) flagTB =false;
                if(diem3Optional.isPresent()) flagYeu =false;
                
            }
        else if(diemTongKet>=5.0f && diemTongKet<6.5f)
            {
              Optional<Diem> diem1Optional = listDiemTB.stream().filter(diem -> (diem.getDiemSo()<3.5f && diem.getDiemSo()>=2.0f)).findAny();
              Optional<Diem> diem2Optional = listDiemTB.stream().filter(diem ->( diem.getDiemSo()<2.0f)).findAny();
              if(diem1Optional.isPresent() || (diemTBToan<5.0f && diemTBVan<5.0f))
                  flagTB =false;
              if(diem2Optional.isPresent()) flagTB = flagYeu=false;
            }
        else if(diemTongKet >=3.5f && diemTongKet<5.0f)
        {
            Optional<Diem> diemOptional = listDiemTB.stream().filter(diem -> (diem.getDiemSo()<2.0f)).findAny();
            if(diemOptional.isPresent())
                flagYeu =false;
        }
       if(flagGioi ==true) return "Giỏi";
       if(flagKha==true) return "Khá";
       if(flagTB==true) return "Trung bình";
       if(flagYeu ==true) return "Yếu";
      return "Không đủ điều kiện lên lớp";
    }
}
