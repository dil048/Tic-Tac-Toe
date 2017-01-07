import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class Tester 
{
	
	public static void main(String[] args) throws IOException
	{
	    File file = new File("instruction.txt"
	        + "");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNext())
            {
	        String s  = sc.next();
	        if(s.equals("Source:"))
	        {
	            s = "\n\nSource:";
	        }
                System.out.print(s+" ");
            }
	    sc.close();
		
	}	
}
