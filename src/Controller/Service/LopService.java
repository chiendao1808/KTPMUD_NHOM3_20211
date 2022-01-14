/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;

import Model.Lop;
import java.util.Optional;
import java.sql.*;
import java.util.List;

/**
 *
 * @author leope
 */
public interface LopService {
    
    
    public List<Lop> findAll();
    
   public Optional<Lop> findLop(String tenLop, String namHoc);
   
   public boolean addLop(Lop lop);
   
    
}
