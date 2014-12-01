package com.training.dt.lesson;

import java.io.*;
import java.util.regex.Pattern;

class Date {
/* Put your private data fields here. */
    /** Constructs a date with the given month, day and year. If the date is
     * not valid, the entire program will halt with an error message.
     * @param month is a month, numbered in the range 1...12.
     * @param day is between 1 and the number of days in the given month.
     * @param year is the year in question, with no digits omitted.
     */

    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    /** Constructs a Date object corresponding to the given string.
     * @param s should be a string of the form "month/day/year" where month must
     * be one or two digits, day must be one or two digits, and year must be
     * between 1 and 4 digits. If s does not match these requirements or is not
     * a valid date, the program halts with an error message.
     */
    public Date(String s) {
        if(s.isEmpty() ||  s == null){
            throw new IllegalArgumentException("Illegal argument");
        }
        String[] parts = s.split("/");
        if(parts.length != 3){
            System.out.println("Date format is not right");
            System.exit(0);
        }
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }
    /** Checks whether the given year is a leap year.
     * @return true if and only if the input year is a leap year.
     */
    public static boolean isLeapYear(int year) {
        return year % 4 ==0 && year % 100 != 0 || year % 400 ==0;
    }
    /** Returns the number of days in a given month.
     * @param month is a month, numbered in the range 1...12.
     * @param year is the year in question, with no digits omitted.
     * @return the number of days in the given month.
     */
    public static int daysInMonth(int month, int year) {
        if(month <1 || month > 13)
            throw new IllegalArgumentException("Illegal month argument");
        if(year <1 || month > 9999)
            throw new IllegalArgumentException("Illegal year argument");
        int daysInMonth = -1;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysInMonth = 31;break;
            case 2: if(isLeapYear(year)) {
                daysInMonth = 29;
                break;
            }else {
                daysInMonth = 28;
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11:
                daysInMonth = 30;break;
            default: daysInMonth = -1;
        }
        return daysInMonth;
    }
    /** Checks whether the given date is valid.
     * @return true if and only if month/day/year constitute a valid date.
     *
     * Years prior to A.D. 1 are NOT valid.
     */
    public static boolean isValidDate(int month, int day, int year) {
        int days = daysInMonth(month, year);
        if(days == -1)
            return false;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: return days >1 && days <= 31;
            case 2:
                if(isLeapYear(year))
                    return days >1 && days <= 29;
                else
                    return days >1 && days <= 28;
            case 4:
            case 6:
            case 9:
            case 11:return days >1 && days <= 30;
            default: return false;
        }
    }
    /** Returns a string representation of this date in the form month/day/year.
     * The month, day, and year are expressed in full as integers; for example,
     * 12/7/2006 or 3/21/407.
     * @return a String representation of this date.
     */
    public String toString() {
        return month + "/" + day + "/" + year; // replace this line with your solution
    }
    /** Determines whether this Date is before the Date d.
     * @return true if and only if this Date is before d.
     */
    public boolean isBefore(Date d) {
        if(this.year < d.year){
            return true;
        }else{
            if(this.month < d.month){
                return false;
            }
            else {
                if (this.day < d.day)
                    return true;
            }
        }
        return false; // replace this line with your solution
    }
    /** Determines whether this Date is after the Date d.
     * @return true if and only if this Date is after d.
     */
    public boolean isAfter(Date d) {
        if(!isValidDate(d.month, d.day, d.year))
            throw new IllegalArgumentException("Illegal date, check your date argument first");
        return !isBefore(d);
    }
    /** Returns the number of this Date in the year.
     * @return a number n in the range 1...366, inclusive, such that this Date
     * is the nth day of its year. (366 is used only for December 31 in a leap
     * year.)
     */
    public int dayInYear() {
        int daysInMonth = 0;
        for (int i = 1; i < month; i++){
            daysInMonth += daysInMonth(i, year);
        }
        return  daysInMonth + day;
    }
    /** Determines the difference in days between d and this Date. For example,
     * if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
     * If this Date occurs before d, the result is negative.
     * @return the difference in days between d and this date.
     */
    public int difference(Date d) {
        if(!isValidDate(d.month, d.day, d.year))
            throw new IllegalArgumentException("Illegal date, check your date argument first");
        if(equals(d))
            return 0;
        int diffDaysInYear = 0;
        if(isBefore(d)){
            for (int i = year + 1; i < d.year; i++){
                diffDaysInYear += isLeapYear(i) ? 366 : 365;
            }
            return -remainDays() - diffDaysInYear - d.dayInYear();
        }else {
            for (int i = d.year + 1; i < year; i++) {
                diffDaysInYear += isLeapYear(i) ? 366 : 365;
            }
            return  d.remainDays() + diffDaysInYear + dayInYear();
        }
    }

    private int remainDays(){
        int d = dayInYear();
        int r = isLeapYear(year) ? 366 : 365;
        return r - d;
    }

    private boolean equals(Date d){
        return this.month == d.month && this.day == d.day && this.year == d.year;
    }

    public static void main(String[] argv) {
        System.out.println("\nTesting constructors.");
        Date d1 = new Date(1, 1, 1);
        System.out.println("Date should be 1/1/1: " + d1);
        d1 = new Date("2/4/2");
        System.out.println("Date should be 2/4/2: " + d1);
        d1 = new Date("2/29/2000");
        System.out.println("Date should be 2/29/2000: " + d1);
        d1 = new Date("2/29/1904");
        System.out.println("Date should be 2/29/1904: " + d1);
        d1 = new Date(12, 31, 1975);
        System.out.println("Date should be 12/31/1975: " + d1);
        Date d2 = new Date("1/1/1976");
        System.out.println("Date should be 1/1/1976: " + d2);
        Date d3 = new Date("1/2/1976");
        System.out.println("Date should be 1/2/1976: " + d3);
        Date d4 = new Date("2/27/1977");
        Date d5 = new Date("8/31/2110");
/* I recommend you write code to test the isLeapYear function!*/
        System.out.println("\nTesting before and after.");
        System.out.println(d2 + " after " + d1 + " should be true: " +
                d2.isAfter(d1));
        System.out.println(d3 + " after " + d2 + " should be true: " +
                d3.isAfter(d2));
        System.out.println(d1 + " after " + d1 + " should be false: " +
                d1.isAfter(d1));
        System.out.println(d1 + " after " + d2 + " should be false: " +
                d1.isAfter(d2));
        System.out.println(d2 + " after " + d3 + " should be false: " +
                d2.isAfter(d3));
        System.out.println(d1 + " before " + d2 + " should be true: " +
                d1.isBefore(d2));
        System.out.println(d2 + " before " + d3 + " should be true: " +
                d2.isBefore(d3));
        System.out.println(d1 + " before " + d1 + " should be false: " +
                d1.isBefore(d1));
        System.out.println(d2 + " before " + d1 + " should be false: " +
                d2.isBefore(d1));
        System.out.println(d3 + " before " + d2 + " should be false: " +
                d3.isBefore(d2));
        System.out.println("\nTesting difference.");
        System.out.println(d1 + " - " + d1 + " should be 0: " +
                d1.difference(d1));
        System.out.println(d2 + " - " + d1 + " should be 1: " +
                d2.difference(d1));
        System.out.println(d3 + " - " + d1 + " should be 2: " +
                d3.difference(d1));
        System.out.println(d3 + " - " + d4 + " should be -422: " +
                d3.difference(d4));
        System.out.println(d5 + " - " + d4 + " should be 48762: " +
                d5.difference(d4));
    }
}