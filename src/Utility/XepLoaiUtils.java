/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author leope
 */
public class XepLoaiUtils {
    
    public static String processXepLoai (String hocLuc, String hanhKiem)
            {
                if(hocLuc.equals("Giỏi"))
                    {
                        if(hanhKiem.equals("Tốt")) return "Giỏi";
                        if(hanhKiem.equals("Khá")) return "Khá";
                    }
                if(hocLuc.equals("Khá"))
                    {
                        if(hanhKiem.equals("Tốt") || hanhKiem.equals("Khá")) return "Khá";
                    }
                return hocLuc ;
            }
}

