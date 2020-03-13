package java8test.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
	Date now=new Date();
	LocalDate now_l=LocalDate.now();
	System.out.println("Date.toString()"+now.toString());
	System.out.println("LocalDate.toString()"+now_l.toString());
	System.out.println(now_l.toEpochDay());
	System.out.println(now_l.getDayOfMonth());
	
	System.out.println(now_l.plusDays(-2));
    }
}
