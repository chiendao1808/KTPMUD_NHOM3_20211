/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller.Service;

import Model.GiaoVien;
import Model.Lop;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface GiaoVienService {
    
    public List<GiaoVien> findAllGiaoVien();
    
    public Optional<GiaoVien> findByMaGiaoVien(String maGiaoVien);
    
    public List<GiaoVien> findByTenGiaoVien(String tenGiaoVien);
    
    public Optional<GiaoVien> findByTenDangNhap(String tenDangNhap);
    
    public boolean addGiaoVien(GiaoVien giaoVien, String tenDangNhap,String matKhau);
    
    public List<Lop> findAllLopDay(String maGiaoVien);
    
    public boolean updateGiaoVien(GiaoVien giaoVien);
    
    public boolean deleteGiaoVien(GiaoVien giaoVien);
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
