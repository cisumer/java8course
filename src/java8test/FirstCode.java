package java8test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class FirstCode {
	public static void main(String[] args) {
		List<Character> list=Arrays.asList('H','e','l','l','o','w','o','r','l','d');
		//Java1.5
		for(Character i : list){
			System.out.print(i);
		}
				//list.sort((Character a,Character b)->{return a.compareTo(b);});
		System.out.println("=================");
		//Java1.8
		
		list.forEach(System.out::print);
		System.out.println();
		list.sort((a,b)->{
			System.out.println(a+" "+b);
			return a.compareTo(b);
		});
		list.forEach(System.out::print);
	}
}
