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
		char type = command.charAt(0);
		if(type=='V')
		{
			returnTheInfo(1,"SV2.5-HV2.0");
		}
		else if(type=='O')
		{
			open();
		}
		else if(type=='C')
		{
			close();
		}
		else if(type=='S')
		{
			changeSpeed(command.charAt(1));
		}
		else if(type=='T')
		{
			sendExtendedFrame(command);
		}
		else
		{
			sendStandardFrame(command);
		}
		
	}
	
	private void sendStandardFrame(String command) 
	{
		String idString = command.substring(1, 4);
		String lenString = command.substring(4, 5);
		int id = Integer.parseInt(idString, 16);
		int len = Integer.parseInt(lenString, 16);
		String data_16 = command.substring(5, 5+len*2);
		String timeString = command.substring(5+len*2);
		String data_2 = "";
		for(int i=0;i<len*2;i++)
		{
			data_2 = data_2 + Integer.toBinaryString(Integer.parseInt(data_16.substring(i,i+1), 16));
		}
		int time = Integer.parseInt(timeString, 16);
		if(true)
		{
			returnTheInfo(1,"OK");
			if(time == 0)
			{
				System.out.println(idString+lenString+timeString);
			}
			else
			{
				Thread th = new Thread();
				for(int i=0;i<100;i++)
				{
					System.out.println(idString+lenString+timeString);
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

	private void sendExtendedFrame(String command) 
	{
		String idString = command.substring(1, 9);
		String lenString = command.substring(9, 10);
		int id = Integer.parseInt(idString, 16);
		int len = Integer.parseInt(lenString, 16);
		String data_16 = command.substring(10, 10+len*2);
		String timeString = command.substring(10+len*2);
		String data_2 = "";
		for(int i=0;i<len*2;i++)
		{
			data_2 = data_2 + Integer.toBinaryString(Integer.parseInt(data_16.substring(i,i+1), 16));
		}
		int time = Integer.parseInt(timeString, 16);
		if(true)
		{
			returnTheInfo(1,"OK");
			if(time == 0)
			{
				System.out.println(idString+lenString+timeString);
			}
			else
			{
				Thread th = new Thread();
				for(int i=0;i<100;i++)
				{
					System.out.println(idString+lenString+timeString);
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

	private void changeSpeed(char c) 
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
				returnTheInfo(1,"OK");
			}
			
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	private void close() 
	{
		if(state == 1)
		{
			state = 0;
			returnTheInfo(1,"OK");
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	private void open() 
	{
		if(state == 0)
		{
			state = 1;
			returnTheInfo(1,"OK");
		}
		else
		{
			returnTheInfo(0,"");
		}
		
	}

	private void returnTheInfo(int flag,String message)
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
