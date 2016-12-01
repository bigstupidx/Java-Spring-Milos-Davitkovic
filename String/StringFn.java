package com.java.String;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Controller
public class StringFn {

	public StringFn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param inputString
	 * @param delimiter
	 * @return
	 */
	public String[] splitString(String inputString, String delimiter) {
		return inputString.split(Pattern.quote(delimiter));		// String[] output = phone.split("-");
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public String sortCharInStringAlphabetically(String string) {
		String newString = string
		 .chars()
		    .distinct()
		    .mapToObj(c -> String.valueOf((char)c))
		    .sorted()
		    .collect(Collectors.joining());
		return newString;
	}

	/**
	 * // Test this string.
	final String test = "DEFINE:A=TWO";

	// Call between, before and after methods.
	System.out.println(between(test, "DEFINE:", "=")); 
	Output: A
	System.out.println(between(test, ":", "="));
	Output: A
	 * @param value
	 * @param a
	 * @param b
	 * @return Substring between the two strings.
	 */
	public String between(String value, String a, String b) {
		// Return a substring between the two strings.
		int posA = value.indexOf(a);
		if (posA == -1) {
			return "";
		}
		int posB = value.lastIndexOf(b);
		if (posB == -1) {
			return "";
		}
		int adjustedPosA = posA + a.length();
		if (adjustedPosA >= posB) {
			return "";
		}
		return value.substring(adjustedPosA, posB);
	}

	/**
	 * // Test this string.
	final String test = "DEFINE:A=TWO";
	 * System.out.println(before(test, ":"));
	 * Output: DEFINE
	System.out.println(before(test, "="));
	Output: DEFINE:A
	 * @param value
	 * @param a
	 * @return Substring containing all characters before a string.
	 */
	public String before(String value, String a) {
		// Return substring containing all characters before a string.
		int posA = value.indexOf(a);
		if (posA == -1) {
			return "";
		}
		return value.substring(0, posA);
	}

	/**
	 * // Test this string.
	final String test = "DEFINE:A=TWO";
	System.out.println(after(test, ":"));
	Output: A=TWO
	System.out.println(after(test, "DEFINE:"));
	Output: A=TWO
	System.out.println(after(test, "="));
	Output: TWO
	 * @param value
	 * @param a
	 * @return Substring containing all characters after a string.
	 */
	public String after(String value, String a) {
		// Returns a substring containing all characters after a string.
		int posA = value.lastIndexOf(a);
		if (posA == -1) {
			return "";
		}
		int adjustedPosA = posA + a.length();
		if (adjustedPosA >= value.length()) {
			return "";
		}
		return value.substring(adjustedPosA);
	}

	/**
	 * Get right characters from a string, change the begin index.
	 * // Test the right method.
	String value = "website";
	String result = right(value, 4);
	System.out.println(result);
	Output: site

	value = "Java Virtual Machine";
	result = right(value, 7);
	System.out.println(result);
	Output: Machine
	 * @param value
	 * @param length
	 * @return String with right characters from a string
	 */
	public static String right(String value, int length) {
		// To get right characters from a string, change the begin index.
		return value.substring(value.length() - length);
	}

	/**
	 * String value = "One two three four five.";
	System.out.println(value);
	Output: One two three four five.

	// Test firstWords on the first 3 and 4 words.
	String words3 = firstWords(value, 3);
	System.out.println(words3);
	Output: One two three
	String words4 = firstWords(value, 4);
	System.out.println(words4);
	Output: One two three four

	 * @param input
	 * @param words
	 * @return String sentence with specified number of words, from input sentence
	 */
	public String firstWords(String input, int words) {
		for (int i = 0; i < input.length(); i++) {
			// When a space is encountered, reduce words remaining by 1.
			if (input.charAt(i) == ' ') {
				words--;
			}
			// If no more words remaining, return a substring.
			if (words == 0) {
				return input.substring(0, i);
			}
		}
		// Error case.
		return "";
	}

	/**
	 * String test = "apple";
	// ... Truncate to 3 characters.
	String result1 = truncate(test, 3);
	System.out.println(result1);

	// ... Truncate to larger sizes: no exception occurs.
	String result2 = truncate(test, 10);
	System.out.println(result2);

	String result3 = truncate(test, 5);
	System.out.println(result3);

	Output:
	app
	apple
	apple
	 * @param value
	 * @param length
	 * @return
	 */
	public String truncate(String value, int length) {
		// Ensure String length is longer than requested size.
		if (value.length() > length) {
			return value.substring(0, length);
		} else {
			return value;
		}
	}

	/**
	 * Function remove all whitespaces from input String
	 * String value = " Hi,\r\n\t\thow are  you?";
	// Test our methods.
	String result = removeAllWhitespace(value);
	System.out.println(result);
	Output: Hi,howareyou?
	 * @param value
	 * @return String witout whitespaces
	 */
	public String removeAllWhitespace(String value) {
		// Remove all whitespace characters.
		return value.replaceAll("\\s", "");
	}

	/**
	 * String value = " Hi,\r\n\t\thow are  you?";
	// Test our methods.
	 * result = collapseWhitespace(value);
	System.out.println(result);
	output: Hi, how are you?
	 * @param value
	 * @return
	 */
	public String collapseWhitespace(String value) {
		// Replace all whitespace blocks with single spaces.
		return value.replaceAll("\\s+", " ");
	}
	
	public String eval(List<Integer> list, Predicate<Integer> predicate) {
		String output = "";
	      for(Integer n: list) {
			
	         if(predicate.test(n)) {
	        	 output += n + " ";
	         }
	      }
	      return output;
	   }
	
	public String evenNumbers(List<Integer> list) {
		return eval(list, n-> n%2 == 0 );
	}
	
	public String oddNumbers(List<Integer> list) {
		return eval(list, n-> n%2 != 0 );
	}
	
	public String greaterThenNumbers(List<Integer> list, Integer number) {
		return eval(list, n-> n > number );
	}
	
	/**
	 * Java 8
	 * @param lines
	 * @param filter
	 * @return List of String without specified filter item
	 */
	public List<String> getListWithout(List<String> lines, String filter) {
		List<String> result = lines.stream() 			//convert list to stream
			.filter(line -> !filter. equals (line))	//filters the line, equals to "mkyong"
			.collect(Collectors.toList());			//collect the output and convert streams to a List
		return result;
    }

}
