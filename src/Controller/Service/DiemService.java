/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.Diem;
import Model.MonHoc;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface DiemService {
    
    public List<Diem>findAllDiemHocSinh(String maHocSinh, String namHoc, String hocKy);
    
    public List<Diem> findAllDiemByMonHoc(String maHocSinh, String maMonHoc, String namHoc, String hocKy);
    
    public Optional<Diem> findDiemChiTiet(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy);
    
    public boolean addDiem (String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy, float diemSo);
    
    public boolean updateDiem (String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy, float diemSo);
    
    public List<MonHoc> findAllMonHocHocKy(String maHocSinh, String namHoc, String hocKy);
    
    public float processDiemTBMon(List<Diem> listDiem);
    
    public float processDiemTBHocKy(String maHocSinh, String namHoc, String hocKy);
    
    public List<Diem> getListDiemTB(String maHocSinh, String namHoc, String hocKy);
    
    public String processDanhGiaHocLuc(String maHocSinh, String namHoc, String HocKy);
    
    
    
    
    
    
    
}
