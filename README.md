# Mock Http

模拟HTTP接口的桩程序项目

## 主要功能

1. 通过镜像接口，检视请求内容
1. 自定义模拟http接口的请求地址与响应内容

### 镜像接口的访问示例

该接口会返回请求的详细信息，展示接收到的请求的地址、参数、头信息和消息体。  
具体的请求与响应的HTTP报文如下：

#### 请求HTTP报文

```http
POST /mirror/test/abc?a=1 HTTP/1.1
Host: <ip>:<port>
Content-Type: application/json
b:2

{
    "c": "3"
}
```

#### 响应HTTP报文

```http
HTTP/1.1 200 OK
Content-Type: text/html;charset=UTF-8
Content-Length: 160

{
    "uri": "/test/abc",
    "params": {
        "a": "1"
    },
    "headers": {
        "b": "2",
        "Content-Type": "application/json"
    },
    "body": "{\"c\":\"3\"}",
    "timestamp": 1564216653411
}
```

#### 响应字段说明

| **响应字段**  | **说明**      | **示例**                                         |
|-----------|-------------|------------------------------------------------|
| uri       | 接收到的请求的地址   | "/test/abc"                                    |
| params    | 接收到的请求的查询参数 | \{"a":"1"\}                                    |
| headers   | 接收到的请求的头参数  | \{"b":"2","Content\-Type":"application/json"\} |
| body      | 接收到的请求的消息体  | "\{\\"c\\":\\"3\\"\}"                          |
| timestamp | 接收到的请求的时间戳  | 1564216653411                                  |


## TODO

1. [x] 镜像接口
1. [x] 自定义规则的管理接口
1. [ ] 自定义规则的访问接口
1. [ ] 请求日志的查询
1. [ ] 内存缓存机制
1. [ ] 自定义规则的有效期管理
1. [ ] 用户体系
1. [ ] 用户组体系



