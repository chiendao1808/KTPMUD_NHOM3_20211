/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author leope
 */
public class DateAndTimeUtils {
    
    
    
    public static String convertDateToStr(String date, String pattern)
            {
                return new SimpleDateFormat(pattern).format(date);
            }
    
    public static String convertDateToStr(Date date)
            {
              return new SimpleDateFormat("dd/MM/yyyy").format(date);
            }
    
    public static Date convertStrToDate(String dateStr) throws ParseException
          {
              return new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dateStr).getTime()); // convert to sql.Date
          }
    
   
      
     public static Calendar convertDateToCal(Date date)
             {
                 Calendar cal = Calendar.getInstance();
                 cal.setTime(date);
                 return cal;
             }
}
