# CanTool Requirements

### 1. 实时监听 CAN 总线和 CANToolApp

#### 1.1 Brief Description
实时监听 CAN 总线和 CANToolApp，接收设备发送的消息。

#### 1.2 Flow of Events
None.
##### 1.2.1 Basic Flow
None.
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
None.
#### 2.2 Flow of Events
None.
##### 2.2.1 Basic Flow
None.
##### 2.2.2 Alternative Flows
None.
#### 2.3 Special Requirements
None.
#### 2.4 Pre-Conditions
None.
#### 2.5 Post-Conditions
None.
#### 2.6 Extension Points
None.

### 3. 接收 CAN 总线的 CAN 信息，识别其为标准帧或扩展帧，做出对应的处理后发送给 CANToolApp

#### 3.1 Brief Description
None.
#### 3.2 Flow of Events
None.
##### 3.2.1 Basic Flow
None.
##### 3.2.2 Alternative Flows
None.
#### 3.3 Special Requirements
None.
#### 3.4 Pre-Conditions
None.
#### 3.5 Post-Conditions
None.
#### 3.6 Extension Points
None.

### 4. 接收 CANToolApp 的 CAN 信号和频率信息，能够按照指定的频率发送 CAN 信号到 CAN 总线

#### 4.1 Brief Description
None.
#### 4.2 Flow of Events
None.
##### 4.2.1 Basic Flow
None.
##### 4.2.2 Alternative Flows
None.
#### 4.3 Special Requirements
None.
#### 4.4 Pre-Conditions
None.
#### 4.5 Post-Conditions
None.
#### 4.6 Extension Points
None.