package CANTool;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CheckFormat {
	CheckFormat()
	{
		
	}
	public static boolean check(int id,long data)
	{
		Map<String, String> map = new HashMap<String, String>(); 
		map.put(Integer.toString(856), Long.toString(-2131502033));
		map.put(Integer.toString(61), Long.toString(-64));
		map.put(Integer.toString(1067), Long.toString(-51201));
		map.put(Integer.toString(1056), Long.toString(-51201));
		map.put(Integer.toString(792), Long.toString(-4));
		map.put(Integer.toString(837), Long.toString(-65344));
		map.put(Integer.toString(915), Long.toString(-1061093428));
		map.put(Integer.toString(800), Long.toString(-6369));
		map.put(Integer.toString(801), Long.toString(1113859));
		map.put(Integer.toString(797), Long.toString(-16));
		map.put(Integer.toString(864), Long.toString(12615687));
		map.put(Integer.toString(867), Long.toString(-3276673));
		map.put(Integer.toString(868), Long.toString(939524096));
		data&=Long.parseLong(map.get(Integer.toString(id)));
		if(data==0)
			return true;
		else
			return false;
	}
}
