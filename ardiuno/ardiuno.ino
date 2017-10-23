String input = "";         
boolean flag = false;  
int cnt=0;
int state=0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  input.reserve(200);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(input.length()>150)
  {
    input = "";   
    returnTheInfo(0,"");
  }
  if (flag) 
  {
   // Serial.println(input);
    readCommand(input);
    input="";
    flag=false;
  }
  cnt++;
  delay(10);
  if(cnt%100==0)
  {
    wt(cnt/100);
  }
  if(cnt>20000)
    cnt-=20000;
}

void print(int x)
{
    if(x<10)
        Serial.write('0'+x);
    else
        Serial.write('A'+x-10);
}
void Transform(unsigned long long x,int k)
{
    for(int i=k-1;i>=0;i--)
    {
        print((x>>(i*4))&15);
    }
}
void Transform1(unsigned long long x,int k)
{
    for(int i=0;i<k;i+=2)
    {
        print((x>>((i+1)*4))&15);
        print((x>>(i*4))&15);
    }
}

void wt(int k)
{
  if(state==0)
    return;
  Serial.write('t');
  Transform(100,3);
  Transform(8,1);
  k%=200;
  unsigned long long a=k*40;
  unsigned long long b=200-k;
  Transform1(a,4);
  Transform1(b,2);
  Transform1(0,10);
  Serial.write('\r');
}

void serialEvent() 
{
  while (Serial.available()) 
  {
    char inChar=(char)Serial.read();
    input+=inChar;
    if (inChar=='\r') 
    {
      flag=true;
    }
  }
}

void readCommand(String command)
{
  if(command==NULL||command.length()==0)
      returnTheInfo(0,"");
  char type=command.charAt(0);
  command = command.substring(0,command.length()-1);
  if(type=='V'&&command.length()==1)
  {
    returnTheInfo(1,"SV2.5-HV2.0");
  }
  else if(type=='O'&&command.charAt(1)=='1'&&command.length()==2)
  {
    open();
  }
  else if(type=='C'&&command.length()==1)
  {
    close();
  }
  else if(type=='S'&&command.length()==2)
  {
    changeSpeed(command.charAt(1));
  }
  else if(type=='T')
  {
    sendExtendedFrame(command);
  }
  else if(type=='t')
  {
    sendStandardFrame(command);
  }
  else 
  {
    returnTheInfo(0,"");
  }
}

void sendStandardFrame(String command) 
{
  if(state==0)
  {
    returnTheInfo(0,"");
    return;
  }
  int templen=command.length();
  for(int i=1;i<templen;i++)
  {
    char tempchar=command.charAt(i);
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
  int len=command.charAt(4)-'0';
  if(len<=0||len>8||templen!=9+len*2)
  {
    returnTheInfo(0,"");
    return;
  }
  returnTheInfo(1,"");
}

void sendExtendedFrame(String command) 
{
  if(state==0)
  {
    returnTheInfo(0,"");
    return;
  }
  int templen=command.length();
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
  int len=command.charAt(9)-'0';
  if(len<=0||len>8||templen!=14+len*2)
  {
    returnTheInfo(0,"");
    return;
  }
  returnTheInfo(1,"");
}

void changeSpeed(char c) 
{
  if(state==0)
  {
    int level=(int)(c-'0');
    if(level<0||level >8)
      returnTheInfo(0,"");
    else
    {
      returnTheInfo(1,"");
    }
  }
  else
  {
    returnTheInfo(0,"");
  }
}

void close() 
{
  if(state==1)
  {
    state=0;
    returnTheInfo(1,"");
  }
  else
  {
    returnTheInfo(0,"");
  }
}

void open() 
{
  if(state==0)
  {
    state=1;
    returnTheInfo(1,"");
  }
  else
  {
    returnTheInfo(0,"");
  }
}

void returnTheInfo(int flag,String message)
{
  if(flag==1)
  {
      message=message+"\r";
  }
  else
  {
      message=message+(char)7;
  }
  int len=message.length();
  for(int i=0;i<len;i++)
    Serial.write(message[i]);
}


