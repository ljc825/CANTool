package CANTool;

import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SendDataToSerialPortFailure;
import serialException.SerialPortOutputStreamCloseFailure;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;
import serialPort.SerialTool;
import gnu.io.SerialPort;

public class CANTool {
	private SerialPort serialPort;
	private int state;
	private int speed;
	
	public CANTool(SerialPort serialPort)
	{
		this.serialPort = serialPort;
		state = 0;
		speed = 10;
	}
	
	public void addListener(SerialListener listener)
	{
		try {
			SerialTool.addListener(serialPort, listener);
		} catch (TooManyListeners e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readCommand(String command)
	{
		if(command == null || command.length() == 0)
			returnTheInfo(0,"");
		char type = command.charAt(0);
		command = command.substring(0,command.length()-1);
		if(type=='V' && command.length() == 1)
		{
			returnTheInfo(1,"SV2.5-HV2.0");
		}
		else if(type=='O' && command.charAt(1) == '1' && command.length() == 2)
		{
			open();
		}
		else if(type=='C' && command.length() == 1)
		{
			close();
		}
		else if(type=='S' && command.length() == 2)
		{
			changeSpeed(command.charAt(1));
		}
		else if(type=='T')
		{
			try {
				sendExtendedFrame(command);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type=='t')
		{
			try {
				sendStandardFrame(command);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			returnTheInfo(0,"");
		}
		
	}
	
	public void sendStandardFrame(String command) throws Exception, Exception 
	{
		if(state == 0)
		{
			returnTheInfo(0,"");
			return;
		}
		int templen = command.length();
		for(int i=1;i<templen;i++)
		{
			char tempchar = command.charAt(i);
			if(!((tempchar>='0'&&tempchar<='9')||(tempchar>='A'&&tempchar<='F')))
			{
				returnTheInfo(0,"");
				return;
			}
		}
		if(templen<=4)
		{
			returnTheInfo(0,"");
			return;
		}
		String idString = command.substring(1, 4);
		String lenString = command.substring(4, 5);
		int id = Integer.parseInt(idString, 16);
		int len = Integer.parseInt(lenString, 16);
		if(len<=0||len>8||templen!=9+len*2)
		{
			returnTheInfo(0,"");
			return;
		}
		String data_16 = command.substring(5, 5+len*2);
		String timeString = command.substring(5+len*2,9+len*2);
		String data_2 = "";
		for(int i=0;i<len*2;i++)
		{
			data_2 = data_2 + Integer.toBinaryString(Integer.parseInt(data_16.substring(i,i+1), 16));
		}
		int time = Integer.parseInt(timeString, 16);
		if(CheckFormat.check(id,Long.parseUnsignedLong(data_16,16)))
		{
			returnTheInfo(1,"");
			if(time == 0)
			{
				System.out.println(idString+lenString+data_16);
			}
			else
			{
				Thread th = new Thread();
				for(int i=0;i<100;i++)
				{
					System.out.println(idString+lenString+data_16);
					try {
						th.sleep(time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				th.stop();
			}
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	public void sendExtendedFrame(String command) throws Exception, Exception 
	{
		if(state == 0)
		{
			returnTheInfo(0,"");
			return;
		}
		int templen = command.length();
		for(int i=1;i<templen;i++)
		{
			char tempchar = command.charAt(i);
			if(!((tempchar>='0'&&tempchar<='9')||(tempchar>='A'&&tempchar<='F')))
			{
				returnTheInfo(0,"");
				return;
			}
		}
		if(templen<=9)
		{
			returnTheInfo(0,"");
			return;
		}
		String idString = command.substring(1, 9);
		
		String lenString = command.substring(9, 10);
		
		int id = Integer.parseInt(idString, 16);
		int len = Integer.parseInt(lenString, 16);
		if(len<=0||len>8||templen!=14+len*2)
		{
			returnTheInfo(0,"");
			return;
		}
		String data_16 = command.substring(10, 10+len*2);
		String timeString = command.substring(10+len*2,14+len*2);
		String data_2 = "";
		for(int i=0;i<len*2;i++)
		{
			data_2 = data_2 + Integer.toBinaryString(Integer.parseInt(data_16.substring(i,i+1), 16));
		}
		int time = Integer.parseInt(timeString, 16);
		if(CheckFormat.check(id,Long.parseUnsignedLong(data_16,16)))
		{
			returnTheInfo(1,"");
			if(time == 0)
			{
				System.out.println(idString+lenString+data_16);
			}
			else
			{
				Thread th = new Thread();
				for(int i=0;i<100;i++)
				{
					System.out.println(idString+lenString+data_16);
					try {
						th.sleep(time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				th.stop();
			}
		}
		else
		{
			returnTheInfo(0,"");
		}
		
		
	}

	public void changeSpeed(char c) 
	{
		if(state == 0)
		{
			int level = (int)(c-'0');
			if(level<0 || level >8)
				returnTheInfo(0,"");
			else
			{
				int num[]={10,20,50,100,125,250,500,800,1000};
				speed=num[level];
				System.out.println(speed);
				returnTheInfo(1,"");
			}
			
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	public void close() 
	{
		if(state == 1)
		{
			state = 0;
			returnTheInfo(1,"");
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	public void open() 
	{
		if(state == 0)
		{
			state = 1;
			returnTheInfo(1,"");
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	public void returnTheInfo(int flag,String message)
	{
		if(flag==1)
		{
			message = message + "\r";
		}
		else
		{
			message = message + (char)(0x07);
		}
		try {
			SerialTool.sendToPort(serialPort, message.getBytes());
		} catch (SendDataToSerialPortFailure
				| SerialPortOutputStreamCloseFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
