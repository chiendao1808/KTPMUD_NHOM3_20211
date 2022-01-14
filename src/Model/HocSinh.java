/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
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
public class HocSinh {
    private String maHocSinh;
    
    private String tenHocSinh;
    
    private String gioiTinh;
    
    private Date ngaySinh;
    
    private String diaChi;
    
    private String tenPhuHuynh;
    
    private String  soDienThoai;
    
    private boolean xoa;
        
  
}
