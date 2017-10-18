package CANTool;

import gnu.io.SerialPort;
import serialPort.SerialTool;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SerialPort serialPort = SerialTool.openPort("COM12", 115200);
		CANTool tool = new CANTool(serialPort);
		SerialListener listener = new SerialListener(serialPort,tool);
		tool.addListener(listener);
	}

}
