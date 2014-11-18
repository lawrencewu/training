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
            if(s.isEmpty() || s == null)
                 new Exception("Date cannot be empty");
            if(!datePattern.matcher(s).matches())
                 new Exception("Date format is wrong");
    }

    public static  void verifyYear(int year){
            if(year < 1 )
                 new Exception("Year cannot be less than 1");
            if(!yearPattern.matcher(year + "").matches())
                 new Exception("Year format is wrong");
    }

    public static  void verifyMonth(int month){
            if (!monthPattern.matcher(month + "").matches())
                 new Exception("Month format is wrong");
    }
}

