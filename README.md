# 小林聊天系统

一个基于Spring Boot + Vue3 + WebSocket的实时聊天系统。

## 技术栈

### 后端
- Spring Boot 2.7.3
- MyBatis-Plus 3.5.3.1
- WebSocket
- RabbitMQ
- JWT认证
- MySQL
- Druid连接池
- Knife4j接口文档

### 前端
- Vue 3
- Element Plus
- WebSocket

## 项目结构
```
xiaolin-chat/
├── common/          # 公共模块
├── pojo/           # 实体类模块
├── server/         # 服务端模块
│   ├── src/main/java/pers/xiaolin/
│   │   ├── config/         # 配置类
│   │   ├── controller/     # 控制器
│   │   ├── service/        # 服务层
│   │   ├── mapper/         # 数据访问层
│   │   ├── websocket/      # WebSocket相关
│   │   ├── mqmessage/      # 消息队列相关
│   │   └── interceptor/    # 拦截器
│   └── src/main/resources/
│       ├── static/         # 静态资源
│       └── mapper/         # MyBatis映射文件
└── pom.xml         # 项目依赖管理
```

## 主要功能

1. 用户管理
   - 用户登录
   - JWT token认证

2. 实时聊天
   - WebSocket实时通信
   - 消息发送和接收
   - 消息撤回（3分钟内）
   - 未读消息提醒

3. 消息管理
   - 聊天记录查询
   - 消息列表展示
   - 未读消息统计

4. 其他特性
   - 消息持久化存储
   - 消息队列异步处理
   - 实时在线状态
   - 用户搜索

## 快速开始

### 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+
- RabbitMQ 3.8+

### 数据库配置
1. 创建数据库
2. 执行SQL脚本（位于`server/src/main/resources/db/`目录）

### 配置修改
1. 修改`server/src/main/resources/application.yml`中的数据库配置
2. 修改RabbitMQ配置（如果需要）

### 运行项目
1. 编译项目
```bash
mvn clean package
```

2. 运行服务端
```bash
java -jar server/target/server-1.0-SNAPSHOT.jar
```

3. 访问系统
- 前端页面：`http://localhost:8080`
- 接口文档：`http://localhost:8080/doc.html`

## 项目特点

1. 实时通信
   - 使用WebSocket实现实时消息推送
   - 支持消息的实时发送和接收
   - 断线重连机制

2. 消息可靠性
   - 使用RabbitMQ确保消息可靠投递
   - 消息持久化存储
   - 离线消息处理

3. 安全性
   - JWT token认证
   - 密码加密存储
   - 接口访问控制

4. 性能优化
   - 数据库连接池
   - 消息异步处理
   - 分页查询优化

## 开发计划
1. 待优化功能
   - 添加群聊功能
   - 支持图片和文件传输
   - 添加消息已读状态
   - 优化消息推送机制

2. 性能优化
   - 添加消息缓存
   - 优化数据库查询
   - 添加消息压缩

## 许可证

[MIT License](LICENSE)
