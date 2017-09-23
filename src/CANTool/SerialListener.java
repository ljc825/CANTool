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

            case SerialPortEvent.BI: // 10 ͨѶ�ж�
            	System.out.println("�봮���豸ͨѶ�ж�");
                break;

            case SerialPortEvent.OE: // 7 ��λ�����������

            case SerialPortEvent.FE: // 9 ֡����

            case SerialPortEvent.PE: // 8 ��żУ�����

            case SerialPortEvent.CD: // 6 �ز����

            case SerialPortEvent.CTS: // 3 �������������

            case SerialPortEvent.DSR: // 4 ����������׼������

            case SerialPortEvent.RI: // 5 ����ָʾ

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 ��������������
                break;
            
            case SerialPortEvent.DATA_AVAILABLE: // 1 ���ڴ��ڿ�������
                
                //System.out.println("found data");
                byte[] data = null;
                
                try {
                    if (serialPort == null) {
                    	System.out.println("���ڶ���Ϊ�գ�����ʧ��");
                    }
                    else {
                        data = SerialTool.readFromPort(serialPort);    //��ȡ���ݣ������ֽ�����
                        String dataString = buff + new String(data);	//�뻺����ʣ�����ݺϲ�
                        
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
                        //System.out.println(new String(data));
                        //JOptionPane.showInputDialog(new String(data));
                        //String dataOriginal = new String(data);    //���ֽ���������ת��λΪ������ԭʼ���ݵ��ַ���
                    }                        
                    
                } catch (Exception e) {
                    System.exit(0);
                }    
                
                break;

        }

    }

}