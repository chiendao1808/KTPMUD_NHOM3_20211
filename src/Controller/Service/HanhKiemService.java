/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.HanhKiem;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface HanhKiemService {
    
    public Optional<HanhKiem> findHanhKiem(String maHocSinh, String namHoc, char hocKy);
    
    public boolean addHanhKiem(HanhKiem hanhKiem);
    
    public boolean updateHanhKiem(HanhKiem hanhKiem);
    
    
    
}
