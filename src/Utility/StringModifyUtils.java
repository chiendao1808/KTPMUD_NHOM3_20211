/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Model.Diem;
import java.util.List;

/**
 *
 * @author leope
 */
public class StringModifyUtils {
    
    public static String diemProcessToDisplay(List<Diem> listDiem)
            {
                StringBuilder sb =new StringBuilder();
                 int size = listDiem.size();
                 if(size <= 0 ) return "";
                 for(int i=0 ;i<size-1;i++)
                     {
                         sb.append(listDiem.get(i).getDiemSo()).append("-");
                     }
                 sb.append(listDiem.get(size-1).getDiemSo());
                 return sb.toString();
            }
    
   public static String generateMaMonHoc(String tenMon, String khoi)
           {
               String [] monHocChar  = tenMon.split(" ");
               StringBuilder sb = new StringBuilder();
               sb.append(monHocChar[0].charAt(0)).append(monHocChar[1].charAt(0)).append(khoi);
               return sb.toString();
           }
}
