/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.Diem;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface DiemService {
    
    public List<Diem>findAllDiemHocSinh(String maHocSinh, String namHoc, String hocKy);
    
    public List<Diem> findByMonHoc(String maHocSinh, String maMonHoc, String namHoc, String hocKy);
    
    public Optional<Diem> findDiemChiTiet(String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy);
    
    public boolean addDiem (String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy, float diemSo);
    
    public boolean updateDiem (String maHocSinh, String maMonHoc, String maLoaiDiem, String namHoc, String hocKy, float diemSo);
    
    
    
    
}
