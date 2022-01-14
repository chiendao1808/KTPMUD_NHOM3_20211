package App;


import Controller.MySQLConnect;
import Controller.Service.HocSinhService;
import Controller.ServiceImpl.HocSinhServiceImpl;
import Controller.Validation.DateAndTimeUtils;
import Controller.Validation.Validator;
import Model.HocSinh;
import View.LoginFrame;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.awt.EventQueue;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author leope
 */
public class MainApp {

    
    private static Connection conn = MySQLConnect.getConnection();
    /**
     * @param args the command line arguments
     */
    public static Connection getConnection()
            {
                return conn;
            }
    
    public static void main(String[] args) {
       //  TODO code application logic here
      Thread thread=  new Thread(new Runnable() {
            @Override
            public void run() {                       
                new LoginFrame().setVisible(true);
            }
        });
      thread.start();
//   HocSinhService hsService = new HocSinhServiceImpl();
//   List<HocSinh> list = hsService.findAll();
//   //list.stream().forEach(System.out::println);
//        System.out.println(list);
//     
   
        
        
    }
    
}
