package java8test;

import java.util.Iterator;

class IterableImpl implements Iterable<Integer>{

	@Override
	public Iterator<Integer> iterator() {
		System.out.println("iterator");
		return null;
	}
}