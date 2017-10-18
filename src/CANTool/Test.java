package CANTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import gnu.io.SerialPort;
import serialPort.SerialTool;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SerialPort serialPort = SerialTool.openPort("COM12", 115200);
		CANTool tool = new CANTool(serialPort);
		SerialListener listener = new SerialListener(serialPort,tool);
		tool.addListener(listener);
		InputStream in1 = new FileInputStream(new File("test1.txt"));
		InputStream in2 = new FileInputStream(new File("test2.txt"));
		InputStream in3 = new FileInputStream(new File("test3.txt"));
		InputStream in4 = new FileInputStream(new File("test4.txt"));
		InputStream in5 = new FileInputStream(new File("test5.txt"));
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(in1);
		Scanner scan2 = new Scanner(in2);
		Scanner scan3 = new Scanner(in3);
		Scanner scan4 = new Scanner(in4);
		Scanner scan5 = new Scanner(in5);
		Thread thread = new Thread();
		String tempString;
		int time;
		//1固定时间间隔，2随机时间间隔，3快速，4单信息测试曲线,5多格式数据测试
		while(scan.hasNext())
		{
			int temp = scan.nextInt();
			if(temp==1)
			{
				for(int i=0;i<200;i++)
				{
					tempString=scan1.next()+"\r";
					SerialTool.sendToPort(serialPort, tempString.getBytes());
					thread.sleep(200);
				}
			}
			else if(temp==2)
			{
				for(int i=0;i<200;i++)
				{
					time=scan2.nextInt();
					tempString=scan2.next()+"\r";
					SerialTool.sendToPort(serialPort, tempString.getBytes());
					thread.sleep(time);
				}
			}
			else if(temp==3)
			{
				for(int i=0;i<500;i++)
				{
					tempString=scan3.next()+"\r";
					SerialTool.sendToPort(serialPort, tempString.getBytes());
					thread.sleep(5);
				}
			}
			else if(temp==4)
			{
				for(int i=0;i<500;i++)
				{
					tempString=scan4.next()+"\r";
					SerialTool.sendToPort(serialPort, tempString.getBytes());
					thread.sleep(50);
				}
			}
			else if(temp==5)
			{
				for(int i=0;i<1000;i++)
				{
					tempString=scan5.next()+"\r";
					SerialTool.sendToPort(serialPort, tempString.getBytes());
					thread.sleep(100);
				}
			}
		}
		
	}

}
