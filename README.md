# CanTool Requirements

### 1. 实时监听 CAN 总线和 CANToolApp

#### 1.1 Brief Description
实时监听 CAN 总线和 CANToolApp，接收设备发送的消息。

#### 1.2 Flow of Events
##### 1.2.1 Basic Flow
监听 CAN 总线：
1. 打开相应设置
2. 监听 CAN 总线

监听 CANToolApp：
1. 打开相应设置
2. 监听 CANToolApp

##### 1.2.2 Alternative Flows
None.
#### 1.3 Special Requirements
None.
#### 1.4 Pre-Conditions
None.
#### 1.5 Post-Conditions
None.
#### 1.6 Extension Points
None.


### 2. 根据 CANToolApp 的命令，做出对应的回应，如返回版本信息，置位（open）、复位（close）、设置传输速率

#### 2.1 Brief Description
根据 CANToolApp 的命令，做出对应的回应，如返回版本信息，置位（open）、复位（close）、设置传输速率等。
#### 2.2 Flow of Events
##### 2.2.1 Basic Flow
1. 监听 CANToolApp
2. 接收来自 CANToolApp 的命令
3. 对来自 CANToolApp 的命令进行校验和解析
4. 如果需要，执行 CANToolApp 的请求
5. 如果需要，向 CANToolApp 返回结果

##### 2.2.2 Alternative Flows
None.
#### 2.3 Special Requirements
None.
#### 2.4 Pre-Conditions
打开对 CANToolApp 的监听，保持与 CANToolApp 的通信。
#### 2.5 Post-Conditions
None.
#### 2.6 Extension Points
None.

### 3. 接收 CAN 总线的 CAN 信息，识别其为标准帧还是扩展帧，做出对应的处理后发送给 CANToolApp

#### 3.1 Brief Description
接收 CAN 总线的 CAN 信息，识别其为标准帧或扩展帧，做出对应的处理后发送给 CANToolApp。
#### 3.2 Flow of Events
##### 3.2.1 Basic Flow
1. 监听 CAN 总线
2. 接收来自 CAN 总线的数据，即 CAN 信息
3. 识别 CAN 信息，识别其为标准帧或扩展帧
3. 对来自 CAN 总线的数据进行解析，提取分析 CAN 信号等
4. 如果需要，对 CAN 信息进行相应的处理
5. 如果需要，向 CANToolApp 返回结果

##### 3.2.2 Alternative Flows
None.
#### 3.3 Special Requirements
None.
#### 3.4 Pre-Conditions
打开对 CAN 总线的监听，能够接收来自 CAN 总线的数据。
#### 3.5 Post-Conditions
None.
#### 3.6 Extension Points
None.

### 4. 接收 CANToolApp 的 CAN 信号和频率信息，能够按照指定的频率发送 CAN 信号到 CAN 总线

#### 4.1 Brief Description
接收 CANToolApp 的 CAN 信号和频率信息，能够按照指定的频率发送 CAN 信号到 CAN 总线
#### 4.2 Flow of Events
##### 4.2.1 Basic Flow
1. 监听 CANToolApp 和 CAN 总线
2. 接收来自 CANToolApp 的 CAN 信号和频率信息
3. 解析 CAN 信号
3. 按照要求，将接收到的 CAN 信号按照指定频率发送到 CAN 总线

##### 4.2.2 Alternative Flows
None.
#### 4.3 Special Requirements
None.
#### 4.4 Pre-Conditions
打开对 CANToolApp 的监听，保持与 CANToolApp 的通信；打开对 CAN 总线的监听，能够向 CAN 总线发送数据。
#### 4.5 Post-Conditions
None.
#### 4.6 Extension Points
None.