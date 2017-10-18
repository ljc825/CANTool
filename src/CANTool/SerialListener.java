package CANTool;
import javax.swing.JOptionPane;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import serialPort.SerialTool;


public class SerialListener implements SerialPortEventListener {
    private SerialPort serialPort;
    private String buff;
    private CANTool tool;
    public SerialListener(SerialPort serialPort,CANTool tool)
    {
    	this.serialPort = serialPort;
    	buff = "";
    	this.tool = tool;
    	
    }
	
    public void serialEvent(SerialPortEvent serialPortEvent) {
        
        switch (serialPortEvent.getEventType()) {

            case SerialPortEvent.BI: // 10 通讯中断
            	System.out.println("与串口设备通讯中断");
                break;

            case SerialPortEvent.OE: // 7 溢位（溢出）错误

            case SerialPortEvent.FE: // 9 帧错误

            case SerialPortEvent.PE: // 8 奇偶校验错误

            case SerialPortEvent.CD: // 6 载波检测

            case SerialPortEvent.CTS: // 3 清除待发送数据

            case SerialPortEvent.DSR: // 4 待发送数据准备好了

            case SerialPortEvent.RI: // 5 振铃指示

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                break;
            
            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                
                //System.out.println("found data");
                byte[] data = null;
                
                try {
                    if (serialPort == null) {
                    	System.out.println("串口对象为空！监听失败");
                    }
                    else {
                        data = SerialTool.readFromPort(serialPort);    //读取数据，存入字节数组
                        String dataString = buff + new String(data);	//与缓冲区剩余数据合并
                        
                        String[] elements = null;
                        elements = dataString.split("\r");
                        int len = elements.length;
                        if(dataString.charAt(dataString.length()-1)!='\r')
                        {
                        	buff = elements[len-1];
                        	len--;
                        }
                        else
                        {
                        	buff = "";
                        }
                        for(int i=0;i<len;i++)
                        {
                        	elements[i] = elements[i] + "\r";
                        	tool.readCommand(elements[i]);
                        }
                        if(buff.length()>512)
                        {
                        	tool.returnTheInfo(0, "");
                        	buff = "";
                        }
                        //System.out.println(new String(data));
                        //JOptionPane.showInputDialog(new String(data));
                        //String dataOriginal = new String(data);    //将字节数组数据转换位为保存了原始数据的字符串
                    }                        
                    
                } catch (Exception e) {
                    System.exit(0);
                }    
                
                break;

        }

    }

}
