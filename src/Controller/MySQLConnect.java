/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author leope
 */
public class MySQLConnect {
    private static  String URL=" ";
    private static String userName="";
    private static  String passWord="";
    
    public  static Connection getConnection()
            {
                Connection conn =null;
                try{
                    FileInputStream info  = new FileInputStream("test\\info.properties");
                    Properties proper = new Properties();
                    proper.load(info);
                    URL =proper.getProperty("url");
                    userName = proper.getProperty("user");
                    passWord =proper.getProperty("password");
                        conn=DriverManager.getConnection(URL, userName, passWord); 
                        }catch(Exception exp)
                            {
                               exp.printStackTrace();
                            }
                return conn;
            }
    
   
}
