package CANTool;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class CheckFormat {
	CheckFormat()
	{
		
	}
	public static boolean check(int id, long data)
	{
		Map<String, String> map = new HashMap<String, String>(); 
		map.put(Integer.toString(856), "-67588214562241744");
		map.put(Integer.toString(61), "-64");
		map.put(Integer.toString(1067), "51200");
		map.put(Integer.toString(1056), "51200");
		map.put(Integer.toString(792), "-4");
		map.put(Integer.toString(837), "-280646048022529");
		map.put(Integer.toString(915), "-220104425473");
		map.put(Integer.toString(800), "27350351740928");
		map.put(Integer.toString(801), "-63330787412410368");
		map.put(Integer.toString(797), "-16");
		map.put(Integer.toString(864), "-1099499011848");
		map.put(Integer.toString(867), "4502500119806080");
		map.put(Integer.toString(868), "-3355443200");
		
		map.put("1541", "-281474976710656");
		map.put("1792", "-1");
		map.put("1536", "-1");
		map.put("1537", "0");
		map.put("1543", "0");
		map.put("1542", "0");
		map.put("1540", "-281474976710656");
		map.put("1053", "-4097");
		map.put("1052", "-4097");
		map.put("1051", "-4097");
		map.put("1050", "-4097");
		map.put("497", "-256");
		map.put("496", "-256");
		map.put("417", "-264");
		map.put("416", "-1711276288");
		map.put("273", "-288230376151711744");
		map.put("272", "-3976");
		map.put("256", "0");
		map.put("512", "-1");
		map.put("1024", "-1");
		map.put("103", "-2");
		map.put("1544", "-1");
		map.put("1537", "-1");
		map.put("1307", "-4097");
		map.put("1306", "-4097");
		map.put("1020", "-208");
		map.put("101", "-256");
		map.put("102", "0");
		map.put("100", "0");
		map.put("201", "-281470698488832");
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
