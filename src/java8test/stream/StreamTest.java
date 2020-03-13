package java8test.stream;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class StreamTest {

	public static void main(String[] args) {
		List<Character> list=Arrays.asList('H','e','l','l','o','w','o','r','l','d');
		list.stream().filter(a -> a>108).forEach(System.out::println);
		list.stream().map(Character::new);
	}
}
