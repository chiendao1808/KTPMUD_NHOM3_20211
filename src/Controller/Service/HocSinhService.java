/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller.Service;

import Model.HocSinh;
import Model.Lop;
import java.util.List;
import java.sql.*;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface HocSinhService {
    
    public  List<HocSinh> findAll();
    
    public Optional<HocSinh> findByMaHocSinh( String maHocSinh);
    
    public List<HocSinh> findByTenHocSinh(String tenHocSinh);
    
    public List<HocSinh> findByLop(Lop lop);
    
    public boolean addHocSinh(HocSinh hocSinh);
    
    public boolean  updateHocSinh(HocSinh hocSinh);
    
    public boolean deleteHocSinh (String maHocSinh);
    
    
    
    
    
    
    
    
    
}
