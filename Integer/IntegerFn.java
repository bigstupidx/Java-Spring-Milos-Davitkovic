package com.java.Integer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Controller;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Controller
public class IntegerFn {

	public IntegerFn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Integer> eval(List<Integer> list, Predicate<Integer> predicate) {
		List<Integer> outputList = new ArrayList<>();
	      for(Integer n: list) {
			
	         if(predicate.test(n)) {
	        	 outputList.add(n);
	         }
	      }
	      return outputList;
	   }
	
	/**
	 * 
	 * @param list
	 * @return List of Integer with even numbers only
	 */
	public List<Integer> evenNumbers(List<Integer> list) {
		return eval(list, n-> n%2 == 0 );
	}
	
	/**
	 * 
	 * @param list
	 * @return List of Integer with odd numbers only
	 */
	public List<Integer> oddNumbers(List<Integer> list) {
		return eval(list, n-> n%2 != 0 );
	}
	
	public List<Integer> greaterThenNumbers(List<Integer> list, Integer number) {
		return eval(list, n-> n > number );
	}
	
	

}
