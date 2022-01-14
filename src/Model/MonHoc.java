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
public class MonHoc {
    
    private String maMonHoc;
    
    private String tenMon;
    
    private int trongSo;
    
    private int khoi; // khối
    
    private boolean xoa;
    
    
    
}
