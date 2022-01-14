/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.LoaiDiem;
import java.util.Optional;

/**
 *
 * @author leope
 */
public interface LoaiDiemService {
    
    public Optional<LoaiDiem> findLoaiDiem(String maLoaiDiem);
    
    public boolean addLoaiDiem(LoaiDiem loaiDiem);
    
    
}
