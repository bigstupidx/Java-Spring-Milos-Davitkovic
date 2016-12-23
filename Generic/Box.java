package netgloo.com.java.Generic;

/**
 *   	  Box<Integer> integerBox = new Box<Integer>();
	      Box<String> stringBox = new Box<String>();

	      integerBox.add(new Integer(10));
	      stringBox.add(new String("Hello World"));

	      System.out.printf("Integer Value :%d\n\n", integerBox.get());
	      System.out.printf("String Value :%s\n", stringBox.get());

 * @author Milos Davitkovic
 *
 * @param <T>
 */
public class Box<T> {
	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

}
