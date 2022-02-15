/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.Hoc;
import Model.HocSinh;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface HocService {
    
     public List<Hoc> findAllHoc ();
    
    public List<Hoc> findByLop(String tenLop, String namHoc);
    
    public Optional<Hoc> findHoc(String tenLop, String namHoc, String maHocSinh);
    
    public Optional<Hoc> findByYearHoc(String maHocSinh, String namHoc);
    
    public boolean addHoc(String tenLop, String namHoc, String maHocSinh);
    
    public boolean deleteHoc(Hoc hoc);
    
    
}
