package java8test.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

	LocalDateTime.parse("2019-12-02T20:18:23");
    }
    
}
