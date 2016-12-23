package netgloo.com.java.Number;

import org.springframework.stereotype.Controller;

@Controller
public class NumbersFn {
	
	public NumbersFn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer fromDoubleToInteger(Double inputValue) {
		// a null-safe solution:
//		return (inputValue == null)? null : Integer.valueOf(inputValue.intValue());
		// Math.round() handles odd duck cases, like infinity and NaN, with relative grace.
		return inputValue == null ? null : Integer.valueOf((int) Math.round(inputValue));
	}

}
