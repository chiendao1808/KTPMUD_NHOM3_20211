/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leope
 */
public class Validator {
    
    private static final String DATE_PATTERN="^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
    private static final String PHONE_PATTERN ="(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    private static final String EMAIL_PATTERN ="^[a-zA-Z]([\\w-]+)@([\\w]+\\.[\\w]+|[\\w]+\\.([\\w]{2,})+)$";
    
    // xác thực ngày tháng 
    public static  boolean dateValidator(String date)
            {
                try {
                    DateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formatter.setLenient(true);
                    Date dateToValidate = formatter.parse(date);
                    Calendar calendarTime= DateAndTimeUtils.convertDateToCal(dateToValidate);
                    int year = calendarTime.get(Calendar.YEAR);               
                    if(year<1900||year>2021)
                        {
                            return false;
                        }                           
                } catch (Exception e) {
                    return false;
                }
                return true;
                }
    
    // xác minh ngày tháng bằng regrex
    public static boolean dateValidator2(String date)
            {
                try {
                  Pattern pattern = Pattern.compile(DATE_PATTERN);
                  Matcher matcher = pattern.matcher(date);
                  if(!matcher.matches())
                  return false;
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
    
    public static boolean phoneValidator(String phoneNumber)
    {
        try {
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher match = pattern.matcher(phoneNumber);
            if(!match.matches()) return  false;
        } catch (Exception e) {
            return  false;
        }
        return  true;
    }
    
    public static boolean emailValidator(String email)
    {
        try {
            Pattern pattern =Pattern.compile(EMAIL_PATTERN);
            Matcher matcher =pattern.matcher(email);
            if(!matcher.matches()) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    
    
    
 
}
