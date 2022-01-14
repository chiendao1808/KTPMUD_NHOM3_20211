/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Service;
import App.MainApp;
import java.sql.*;



/**
 *
 * @author leope
 */
public class LoginService {
       
    public static  boolean login(String tenDangNhap, String matKhau)
            {
                String login_query = " select giao_vien.ten_dang_nhap, giao_vien.mat_khau  from giao_vien "
                        + " where ten_dang_nhap =?  and mat_khau =? and xoa =0 ";
                boolean loginCheck = false;
                try {
                PreparedStatement prst =MainApp.getConnection().prepareCall(login_query);
                prst.setString(1,tenDangNhap);
                prst.setString(2, matKhau);
                ResultSet rs=  prst.executeQuery();
                if(rs.next()) loginCheck =true;
                prst.close();
                rs.close();                
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return loginCheck;
            }
    
}
