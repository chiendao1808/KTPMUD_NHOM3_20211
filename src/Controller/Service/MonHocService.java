/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.MonHoc;
import java.util.List;

/**
 *
 * @author leope
 */
public interface MonHocService {
    
    public List<MonHoc>findAll();
    
    public List<MonHoc> findByTeMonHoc(String tenMonHoc);
    
    
    
}
