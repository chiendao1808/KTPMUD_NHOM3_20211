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
public class Diem {
    
    private HocSinh hocSinh;
    
   private MonHoc monHoc;
    
    private LoaiDiem loaiDiem;
    
    private String namHoc;
    
    private String hocKy;
    
    private float diemSo;
    
    private boolean  xoa;
    
    
}
