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
public class GiaoVien {
    private String maGiaoVien;
    
    private String tenGiaoVien;
    
    private String gioiTinh;
    
    private Date ngaySinh;
    
    private String soDienThoai;

    private String diaChi;

    private String chucVu;
    
    private boolean xoa;

        
}
