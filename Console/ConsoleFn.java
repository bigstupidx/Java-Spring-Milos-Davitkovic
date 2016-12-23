package netgloo.com.java.Console;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;

@Controller
public class ConsoleFn {

	public ConsoleFn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void clrscr(){
	    //Clears Screen in java
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	   } 
	
	public final void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	/**
	 * Read Console and Print read on Console
	 * @throws IOException
	 */
	public void ReadConsole() throws IOException {
		 InputStreamReader cin = null;
	      try {
	         cin = new InputStreamReader(System.in);
	         System.out.println("Enter characters, 'q' to quit.");
	         char c;
	         do {
	            c = (char) cin.read();
	            System.out.print(c);
	         } while(c != 'q');
	      }finally {
	         if (cin != null) {
	            cin.close();
	         }
	      }
	}

}
