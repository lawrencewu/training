package com.training.dt.lesson;
import sun.misc.Regexp;
import java.util.regex.Pattern;

/**
 * Created by lawrence on 11/17/2014.
 */
public class Utils {
    private static Pattern datePattern = Pattern.compile("\\d{1,2}\\\\d{1,2}\\\\d{1,4}");
    private static Pattern yearPattern = Pattern.compile("\\d{1,4}");
    private static Pattern monthPattern = Pattern.compile("\\d{1}|11|12");
    public static void verify(String s) {
        try{
            if(s.isEmpty() || s == null)
                throw new Exception("Date cannot be empty");
            if(!datePattern.matcher(s).matches())
                throw new Exception("Date format is wrong");
        }catch(Exception ex){

        }
    }

    public static  void verifyYear(int year){
        try{
            if(year < 1 )
                throw new Exception("Year cannot be less than 1");
            if(!yearPattern.matcher(year + "").matches())
                throw new Exception("Year format is wrong");
        }catch(Exception ex){

        }
    }

    public static  void verifyMonth(int month){
        try {
            if (!monthPattern.matcher(month + "").matches())
                throw new Exception("Month format is wrong");
        }catch (Exception ex){

        }
    }
}

