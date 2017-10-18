在现代汽车控制技术中，一般会采用CAN总线对车内的多个电子控制元件（ECU）进行控制，调节汽车行驶中各种参数（温度、速度等），一般通过CANTool装置实现CAN数据的显示及控制。为了方便用户使用，现在要针对CANTool开发对应的CANToolApp（Windows版，移动平台版），使用户能够更加直观了解CAN总线传递到CANTool上的各种实时数据及其实际物理含义，并且方便用户对各种实际物理值进行实时的更改。

![](https://i.imgur.com/gKhwwrU.png)

上图为整个系统中的数据传输实例，can总线收集、汇总各个ECU的实时数据，通过CANTool传递到以UART或蓝牙串口连接的上位机中的CANToolApp中，并在CANToolApp以可视化手段实时的显示这些数据，用户同样可以通过上位机中的CANToolApp设定各种物理量的实际数值，传递到CANTool，发送到CAN总线上，最终将信息传达到对应物理量的电子控制元件（ECU），更改该项物理值。

上位机与CANTool装置之间的信息传送方式使用ASCII码（字符串）格式+ \r（即0x0d）方式进行信息交换。

即，每一个\r，代表了上位机的一条命令的结束，可以接收到的命令进行解析。
现在对各个装置间的数据传输内容、传输格式、传输方向以及对应功能做简要说明。
CAN总线数据    dir    CANTool装置     dir    CANToolApp      功能说明

无             -       
















在实际开发中，分别有小组模拟实现CANTool和实现CANToolApp的Windows和Android版本

根据问题描述文档，我们可以大致确定这两个软件分别的功能需求。
CANToolApp
1、获取CANToolApp中所有可用的COM口列表，让用户选择映射有CANTool的COM，与CANTool连接，并进行初始设置，通过CANToolApp可以改变CANTool的运行状态（open，close）,CAN传输速率，并将这些配置信息保存。
2、能够以数值方式、仪表盘、实时曲线图等多种不同的方式显示CAN信号中的物理值及其实时变化。
3、可以保存接收到的所有CAN信息，可以加载本地的CAN信息和CAN信号数据库，并以树状结构显示在GUI界面中，可以显示一条CAN信息的物理结构及信号分布情况。
4、可以实现本地的CAN信息和CAN信号数据库/JSON或XML的相互转化。
5、可以根据用户输入的物理量数值组装CAN信息，可以发送CAN信息，可以设置CAN信息的发送频率。
6、可以将将所有CAN信息实时数据、CAN设定信息等 通过WEB API方式更新到远程数据库。
7、......
CANTool

1、实时监听CAN总线和CANToolApp
2、根据CANToolApp的命令，做出对应的回应，如返回版本信息，置位（open）、复位（close）、设置传输速率。
3、接收CAN总线的CAN信息，识别其为标准帧或扩展帧，做出对应的处理后发送给CANToolApp。
4、接收CANToolApp的CAN信号和频率信息，能够按照指定的频率发送CAN信号到CAN总线。


目前对需求中存在疑问的地方：
1、CANTool如何区分CAN标准帧和扩展帧？
CANTool装置不需要单独的识别CAN标准帧和扩展帧，CANToolApp接收和发送给CANTool装置的CAN信息中，以大写T开始的CAN信息是CAN扩展帧信息，以小写t开始的CAN信息是CAN标准帧信息。
2、小端序、大端序的详细样例？文档中给的样例比较难以理解

我们以Intel的字节、位的排列表为例

![](https://i.imgur.com/5JE3IqX.png)

Intel的排序方式小端序，即起始位置存放数据最低位(LSB），即顺序读入的最右一位数据，数据位的排列顺序为：从起始位置开始，以低编号到高编号的顺序访问字节，同一个字节中从低位到高位存放数据，以16|12为例，12位数据的存放位置依次为16、17、18、19、20、21、22、23、24、25、26、27,16为LSB，27为MSB。

Motorola的排序方式为大端序，即起始位置存放数据最高位(MSB），即顺序读入的最左一位数据，数据位的排列顺序为：从起始位置开。

始，以低编号到高编号的顺序访问字节，同一个字节中从高位到低位存放数据，以23|12为例，12位数据的存放位置依次为23、22、21、20、19、18、17、16、31、30、29、28，23为MSB，28为LSB。

3、如何实现CANTool装置与CANToolApp的实时交互（输入输出重定向、进程间通信）？

需要实现实时通信，可是使用Arduino的硬件配合串口/蓝牙来实现虚拟的CANTool装置，需要编写Arduino的C/C++语言程序，此方式可以适用于Android/Windows开发。或使用纯软件的com0com0虚拟串口方式来模拟，此方法只适用于Windows APP开发。


4、CANTool模拟装置如何模拟监听的CAN总线发送CAN信息（预定义文档、随机函数）？

可以通过自己定义的数据模拟CAN总线的信息。但需要能够满足测试CAN信号的不同类型、排列方式及bit信息长度的Little Endian/Big Endian的需要。

5、CANToolApp是否需要实现Web API？

不需要实现。