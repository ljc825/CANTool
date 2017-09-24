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
