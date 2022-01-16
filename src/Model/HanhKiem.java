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
public class HanhKiem {
    
    private HocSinh hocSinh;
    
    private String namHoc;
    
    private String hocKy;
    
    private String loiViPham;
    
    private int nghiCoPhep;
    
    private int nghiKhongPhep;
    
    private boolean xoa;
    
    
}
