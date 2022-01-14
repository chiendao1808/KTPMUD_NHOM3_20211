/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author leope
 */
@Data
@AllArgsConstructor
public class Day {
    
    private GiaoVien giaoVien;
    
   private MonHoc monHoc;
    
   private Lop lop;
    
    private boolean xoa;
    
    
    
}
