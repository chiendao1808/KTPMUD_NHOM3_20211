/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author leope
 */
@Data
@AllArgsConstructor
@NoArgsConstructor  
public class Lop {
    
    private String tenLop;  
    
    private String namHoc;
    
    private int siSo;
    
    private GiaoVien giaoVienChuNhiem;

    private boolean xoa;
    
    
}
