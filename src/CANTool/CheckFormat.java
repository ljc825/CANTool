package CANTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class CheckFormat {
	CheckFormat()
	{
		
	}
	public static boolean check(int id, long data) 
	{
		Map<String, String> map = new HashMap<String, String>(); 
		InputStream in1 = null;
		try {
			in1 = new FileInputStream(new File("data2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scan1 = new Scanner(in1);
		String aString,b;
		while(scan1.hasNext())
		{
			aString=scan1.next();
			b=scan1.next();
			map.put(aString,b);
		}
		
		String dataString = map.get(Integer.toString(id));
		if(dataString==null)
				return false;
		data&=Long.parseLong(map.get(Integer.toString(id)));
		if(data==0)
			return true;
		else
			return false;
	}
}
