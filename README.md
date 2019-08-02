# Mock Http

模拟HTTP接口的桩程序项目

![Total visitor](https://visitor-count-badge.herokuapp.com/total.svg?repo_id=cwt9562.mock-http)

## 主要功能

1. 通过镜像接口，检视请求内容
1. 自定义模拟http接口的请求地址与响应内容

## 镜像接口的访问示例

该接口会返回请求的详细信息，展示接收到的请求的地址、参数、头信息和消息体。  
具体的请求与响应的HTTP报文如下：

### 请求HTTP报文

```http
POST /mirror/test/abc?a=1 HTTP/1.1
Host: <ip>:<port>
Content-Type: application/json
b:2

{
    "c": "3"
}
```

### 响应HTTP报文

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

### 响应字段说明

| **响应字段**  | **说明**      | **示例**                                         |
|-----------|-------------|------------------------------------------------|
| uri       | 接收到的请求的地址   | "/test/abc"                                    |
| params    | 接收到的请求的查询参数 | \{"a":"1"\}                                    |
| headers   | 接收到的请求的头参数  | \{"b":"2","Content\-Type":"application/json"\} |
| body      | 接收到的请求的消息体  | "\{\\"c\\":\\"3\\"\}"                          |
| timestamp | 接收到的请求的时间戳  | 1564216653411                                  |


## 架构

1. 服务端
    1. 应用框架
        1. Spring Boot
    1. 数据库
        1. Sqlite
    1. 连接池
        1. Druid
    1. 持久化框架
        1. Mybatis-Plus
1. 客户端
    1. 待建设



## TODO

### 功能

1. [x] 镜像接口
1. [x] 自定义规则的管理接口
1. [x] 自定义规则的访问接口
1. [ ] 支持通配符
1. [ ] 自定义规则的管理界面
1. [ ] 请求日志的查询
1. [ ] 内存缓存机制
1. [ ] 自定义规则的有效期管理
1. [ ] 用户体系
1. [ ] 用户组体系

### 架构

1. [x] 单元测试使用内存数据库
1. [ ] 引入Maven打包机制，打包成zip
1. [ ] 建设前端架构
1. [ ] 前端打包进jar包
